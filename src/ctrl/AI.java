package ctrl;

import model.PoneStock;

/**
 *
 * @author localwsp
 */
public class AI {
    
    private GameCtrl gameCtrl;
    private final int MAX_DEPTH = 7;

    public AI(GameCtrl gameCtrl) {
        this.gameCtrl = gameCtrl;
    }
    
    public void play(int player, PoneStock poneStock, int[][] grid) {
        int maxVal = getVal(player, true, 0, grid, 0);
        int maxValCo = 0;
        //System.out.println("co: "+0+" val: "+maxVal);
        for(int co = 1; co < 7; ++co) {
            if(gameCtrl.legalMove(co)) {
                int val = getVal(player, true, co, grid, 0);
                //System.out.println("co: "+co+" val: "+val);
                if(val > maxVal) {
                    maxVal = val;
                    maxValCo = co;
                }
            }
        }
        poneStock.autoMove(maxValCo);
    }
    
    private int getVal(int player, boolean turn, int column, int[][] grid, int depth) {
        if(depth == MAX_DEPTH)
            return 0;
        int val = 0;
        if(turn)
            makeMove(player, column, grid);
        else
            makeMove(otherPlayer(player), column, grid);
        //this.gameCtrl.printGrid(grid);
        if(turn && win(grid))
            val = 1000;
        else if(!turn && win(grid))
            val = -1000;
        else {
            for(int co = 0; co < 7; ++co)
                if(legalMove(co, grid))
                    val += getVal(player, !turn, co, grid, depth+1);
        }
        undoMove(column, grid);
        return val / (depth*10+1);
    }
    
    private boolean win(int[][] grid) {
        return (winColumn(grid) 
                || winLine(grid) 
                || winDiagonal(grid));
    }
    
    private boolean legalMove(int column, int[][] grid) {
        return grid[0][column] == 0;
    }
    
    private boolean makeMove(int player, int column, int[][] grid) {
        for(int li = 1; li < 6; ++li) {
            if(grid[li][column] != 0) {
                grid[li-1][column] = player;
                return true;
            }
        }
        grid[5][column] = player;
        return true;
    }
    
    private boolean undoMove(int column, int[][] grid) {
        for(int li = 0; li < 6; ++li) {
            if(grid[li][column] != 0) {
                grid[li][column] = 0;
                return true;
            }
        }
        return false;
    }
    
    
    private boolean winLine(int[][] grid) {
        int countSameColor = 0;
        int curColor = 0;
        int precColor = 0;
        for(int li = 0; li < grid.length; ++li) {
            countSameColor = 0;
            precColor = 0;
            for(int co = 0; co < grid[0].length; ++co) {
                curColor = grid[li][co];
                if(curColor != 0) {
                    if(curColor == precColor)
                        ++countSameColor;
                    else
                        countSameColor = 0;
                    if(countSameColor == 3)
                        return true;
                }
                precColor = curColor;
            }
        }
        return false;
    }
    
    private boolean winColumn(int[][] grid) {
        int countSameColor = 0;
        int curColor = 0;
        int precColor = 0;
        for(int co = 0; co < grid[0].length; ++co) {
            countSameColor = 0;
            precColor = 0;
            for(int li = 0; li < grid.length; ++li) {
                curColor = grid[li][co];
                if(curColor != 0) {
                    if(curColor == precColor)
                        ++countSameColor;
                    else
                        countSameColor = 0;
                    if(countSameColor == 3)
                        return true;
                }
                precColor = curColor;
            }
        }
        return false;
    }
    
    private boolean winDiagonal(int[][] grid) {
        return (winDiagonalLeft(grid) 
        || winDiagonalRight(grid) );
    }
    
    private boolean winDiagonalLeft(int[][] grid) {
        for (int i = 0; i < 2; ++i) {
            for (int li = 0; li < grid.length ; ++li) {
                int curColor = 0;
                int countSameColor = 0;
                int precColor = 0;
                for (int co = 0; co < grid[0].length; ++co) {
                    if(i == 0) {
                        if((li + co) < grid.length)
                            curColor = grid[li + co][co];
                        else
                            curColor = 0;
                    }
                    else {
                        if((li - co) >= 0)
                            curColor = grid[li - co][co];
                        else
                            curColor = 0;
                    }
                    if (curColor != 0) {
                        if (curColor == precColor) {
                            ++countSameColor;
                        } else {
                            countSameColor = 0;
                        }
                        if (countSameColor == 3) {
                            return true;
                        }
                    }
                    precColor = curColor;
                }
            }
        }
        return false;
    }
    
    private int invertSeven(int n) {
        return Math.abs(n-6);
    }
    
    private boolean winDiagonalRight(int[][] grid) {
        for (int i = 0; i < 2; ++i) {
            for (int li = 0; li < grid.length ; ++li) {
                int curColor = 0;
                int countSameColor = 0;
                int precColor = 0;
                for (int co = grid[0].length-1; co >= 0 ; --co) {
                    if(i == 0) {
                        if((li + invertSeven(co)) < grid.length)
                            curColor = grid[li + invertSeven(co)][co];
                        else
                            curColor = 0;
                    }
                    else {
                        if((li - invertSeven(co)) >= 0)
                            curColor = grid[li - invertSeven(co)][co];
                        else
                            curColor = 0;
                    }
                    if (curColor != 0) {
                        if (curColor == precColor) {
                            ++countSameColor;
                        } else {
                            countSameColor = 0;
                        }
                        if (countSameColor == 3) {
                            return true;
                        }
                    }
                    precColor = curColor;
                }
            }
        }
        return false;
    }

    private int otherPlayer(int player) {
        if(player == 1)
            return 2;
        else
            return 1;
    }
}
