package org.example;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends FillableShape {

    double diameter;

    public Circle(double x, double y, double diameter, Color color){
        this.diameter = diameter;
        setVelocity(30,30);
        setX(x);
        setY(x);
        setColor(color);
    }

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    @Override
    public void paint(GraphicsContext gc){
        gc.setStroke(getColor());

        gc.setLineWidth(2);
        if (isFilled()){
            gc.setFill(getColor());
            gc.fillOval(getX()-diameter/2, getY()-diameter/2, diameter, diameter);
        }
        else gc.strokeOval(getX()-diameter/2, getY()-diameter/2, diameter, diameter);

    }
    @Override
    public void constrain(
            double boxX, double boxY,
            double boxWidth, double boxHeight) {
        // If outside the box - calculate new dx and dy
        double dx = getDx(),dy = getDy();
        if (getX()-diameter/2 < boxX) {
            dx = Math.abs(getDx());
        } else if (getX()+diameter/2 > boxWidth) {
            dx = -Math.abs(getDx());
        }
        if (getY()-diameter/2 < boxY) {
            dy = Math.abs(getDy());
        } else if (getY() + diameter/2> boxHeight) {
            dy = -Math.abs(getDy());
        }

        setVelocity(dx,dy);
    }

    @Override
    public void move(long elapsedTimeNs) {

        setX(getX() + getDx()*elapsedTimeNs/BILLION);
        setY(getY() + getDy()*elapsedTimeNs/BILLION);

    }
}
