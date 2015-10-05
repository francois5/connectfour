package model;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author localwsp
 */
public interface Graphic {

  Double getX();

  Double getY();

  Double getWidth();

  Double getHeight();
  
  void scale(Double factor);
  
  void move(Double directionX, Double directionY, Scene scene);

  void moveTo(Double x, Double y, Scene scene);

  boolean isOn(Double x, Double y);

  void paintOn(GraphicsContext gc);
}