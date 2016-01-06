/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 *
 * @author seb
 */
public class AnimLabel extends Label{
    private String textLabel;
    private Timeline blinker;
    private FadeTransition fader;
    private SequentialTransition blinkThenFade;
    private SequentialTransition blink;
    
    public AnimLabel(String textLabel) {
        this.textLabel = textLabel;
        blinker = createBlinker(this);
        fader = createFader(this);
        blinkThenFade = new SequentialTransition(
                this,
                blinker,
                fader
        );
        blink = new SequentialTransition(
            this,
            blinker
        );
    }
    
    public SequentialTransition getBlinkThenFade() {
        return blinkThenFade;
    }
    
    public SequentialTransition getBlink() {
        return blink;
    }
    
    private Timeline createBlinker(Node node) {
        Timeline blink = new Timeline(
                new KeyFrame(
                        Duration.seconds(0),
                        new KeyValue(
                                node.opacityProperty(), 
                                1, 
                                Interpolator.DISCRETE
                        )
                ),
                new KeyFrame(
                        Duration.seconds(0.5),
                        new KeyValue(
                                node.opacityProperty(), 
                                0, 
                                Interpolator.DISCRETE
                        )
                ),
                new KeyFrame(
                        Duration.seconds(1),
                        new KeyValue(
                                node.opacityProperty(), 
                                1, 
                                Interpolator.DISCRETE
                        )
                )
        );
        blink.setCycleCount(3);

        return blink;
    }
    
    private FadeTransition createFader(Node node) {
        FadeTransition fade = new FadeTransition(Duration.seconds(2), node);
        fade.setFromValue(1);
        fade.setToValue(0);

        return fade;
    }
}
