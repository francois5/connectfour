/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

/**
 *
 * @author seb
 */
public class GameGrid extends Pane {
    public static int NBCOLUMNS = 8;
    
    private Image image = new Image("grid.png");
    private ImageView imageView = new ImageView();
    private DoubleProperty width;
    private DoubleProperty height;
    private List<GridElement> grid = new ArrayList<GridElement>();
    private Pane parent;
    
    public GameGrid(double width, double height, Pane parent) {
        this.parent = parent;
        this.width = new SimpleDoubleProperty(width);
        this.height = new SimpleDoubleProperty(height);
    }
    
    public void initFrontImage() {
        imageView.setImage(image);
        getChildren().add(imageView);
        reposFrontImage(800d,800d);
    }
    
    private void reposFrontImage(Double width, Double height) {
        imageView.setTranslateX(0);
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);
    }
    
    private void setSizeListeners() {
        parent.widthProperty().addListener((ObservableValue<? extends Number> observableValue,
                Number oldSceneWidth, Number newSceneWidth) -> {
                    notifySceneWidth((double)newSceneWidth);
                    reposFrontImage((double)newSceneWidth, parent.getHeight());
                });

        parent.heightProperty().addListener((ObservableValue<? extends Number> observableValue,
                Number oldSceneHeight, Number newSceneHeight) -> {
                    notifySceneHeight((double)newSceneHeight);
                    reposFrontImage(parent.getWidth(), (double)newSceneHeight);
                });
    }
    
    public void init(Double width, Double height) {
        this.grid = buildGameGrid(width, height);
        setSizeListeners();
    }
    
    private List<GridElement> buildGameGrid(Double width, Double height) {
        for(int i = 0; i < NBCOLUMNS; ++i) {
            grid.add(new GridElement(parent, width, height, true, i));
        }
        grid.add(new GridElement(parent, width, height, false, 0));
        return grid;
    }
    
    public List<Shape> getGrid() {
        List<Shape> list = new ArrayList<>();
        for(GridElement gE : grid) {
            list.add(gE.getGridElementShape());
        }
        return list;
    }
    
    
    public void notifySceneWidth(Double newSceneWidth) {
        for(GridElement gElement : grid) {
            gElement.notifySceneWidth(newSceneWidth);
        }
    }

    public void notifySceneHeight(Double newSceneHeight) {
        for(GridElement gElement : grid) {
            gElement.notifySceneHeight(newSceneHeight);
        }
    }
}
