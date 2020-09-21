package org.example;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Line extends Shape {
    double x2,y2;


    public Line(double x, double x2, double y, double y2, Color color){
        this.setX(x);
        this.setY(y);
        this.x2 = x2;
        this.y2 = y2;
        this.setColor(color);
        this.setVelocity(30,30);
    }

    public double getX2(){
        return x2;
    }

    public double getY2(){
        return y2;
    }

    public void setX2(double newX2){
        x2 = newX2;
    }

    public void setY2(double newY2){
        y2 = newY2;
    }

    @Override
    public void paint(GraphicsContext gc){
        gc.setStroke(getColor());
        gc.setLineWidth(5);
        gc.strokeLine(getX(),getY(),x2,y2);
    }
    @Override
    public void constrain(
            double boxX, double boxY,
            double boxWidth, double boxHeight) {
        // If outside the box - calculate new dx and dy
        double dx = getDx(),dy = getDy();
        if (getX() < boxX) {
            dx = Math.abs(getDx());
        } else if (x2 > boxWidth) {
            dx = -Math.abs(getDx());
        }
        if (getY()< boxY) {
            dy = Math.abs(getDy());
        } else if (y2 > boxHeight) {
            dy = -Math.abs(getDy());
        }

        this.setVelocity(dx,dy);
    }
    @Override
    public void move(long elapsedTimeNs) {
        setX(getX()+getDx() * elapsedTimeNs / BILLION);
        setY(getY()+getDy() * elapsedTimeNs / BILLION);
        x2 += getDx() * elapsedTimeNs / BILLION;
        y2 += getDy() * elapsedTimeNs / BILLION;
    }
}
