/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author seb
 */
public class GameGrid extends Parent {
    public static int NBCOLUMNS = 7;
    public static final int HEIGHT_BOTTOM = 50;
    
    private double posX;
    private double posY;
    private double width;
    private double height;
    private ArrayList<Rectangle> columns = new ArrayList<>();
    private Rectangle bottom;
    
    public GameGrid(double posX, double posY, double width, double height) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        addColumns();
        for(Rectangle r : columns) {
            this.getChildren().add(r);
        }
        addBottom();
        colorGrid(Color.WHITE, Color.WHITE);
    }
    
    public GameGrid() {
        this(100,100,500,600);
    }
    
    private void addColumns() {
        columns = new ArrayList<>();
        double startX = posX;
        for(int i = 0; i < NBCOLUMNS; ++i) {
            Rectangle rect = new Rectangle(
                startX,
                posY,
                width/NBCOLUMNS,
                height
            );
            startX += width/NBCOLUMNS;
            columns.add(rect);
        }
    }
    
    private void addBottom() {
        bottom = new Rectangle(
            posX, height + posY, width, HEIGHT_BOTTOM
        );
        this.getChildren().add(bottom);
    }
    
    private void colorGrid(Color colorColumns, Color colorBottom) {
        for(Rectangle r : columns) {
            r.setFill(colorColumns);
        }
        bottom.setFill(colorBottom);
    }
    
}
