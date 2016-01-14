package ctrl;

import java.util.Random;
import vue.drawings.PoneStock;

/**
 *
 * @author localwsp
 */
public class AI {
    
    private GameCtrl gameCtrl;
    private final int MAX_DEPTH = 7;
    private Random rand = new Random();
    private int sleep = 0;
    private int player;
    private PoneStock poneStock;
    private int[][] grid;

    public AI(GameCtrl gameCtrl) {
        this.gameCtrl = gameCtrl;
    }
    
    public void update() {
        if(sleep == 0)
            return;
        else if (sleep == 1) {
            --sleep;
            this.play(player, poneStock, grid, false);
        } else
            --sleep;
    }
    
    public boolean play(int player, PoneStock poneStock, int[][] grid, boolean external) {
        if(external) {
            this.player = player;
            this.poneStock = poneStock;
            this.grid = grid;
            sleep = 100;
        }
        else {
            Integer maxVal = null;
            Integer maxValCo = null;
            //System.out.println("co: "+0+" val: "+maxVal);
            for (int co = 0; co < 7; ++co) {
                if (gameCtrl.legalMove(co)) {
                    int val = getVal(player, true, co, grid, 0);
                    //System.out.println("co: "+co+" val: "+val);
                    if (maxVal == null || val >= maxVal) {
                        if (maxVal != null && val == maxVal) {
                            if (rand.nextBoolean()) {
                                maxVal = val;
                                maxValCo = co;
                            }
                        } else {
                            maxVal = val;
                            maxValCo = co;
                        }
                    }
                }
            }
            if (maxValCo != null) {
                poneStock.autoMove(maxValCo);
                return true;
            }
        }
        return false;
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
    
    public boolean win(int[][] grid) {
        return (winColumn(grid) 
                || winLine(grid) 
                || winDiagonal(grid));
    }
    
    public boolean legalMove(int column, int[][] grid) {
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
