package org.example;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Shape {

    Color color;
    double diameter;

    public Circle(double diameter){
        this.diameter = 30;
    }
    @Override
    public void paint(GraphicsContext gc){
        gc.setStroke(color);
        gc.setLineWidth(2);
        gc.strokeOval(60, 60, 30, 30);

    }

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }
}
