package org.example;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Line extends Shape {
    double x1,x2,y1,y2;
    Color color;

    public Line(double x1, double x2, double y1, double y2, Color color){
        this.x1 = x1;
        this.setX(x1);
        this.y1 = y1;
        this.setY(y1);
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
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

    public double getX1() {
        return x1;
    }

    public double getY1() {
        return y1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

    @Override
    public void paint(GraphicsContext gc){
        gc.setStroke(color);
        gc.setLineWidth(5);
        gc.strokeLine(x1,y1,x2,y2);
    }
    @Override
    public void constrain(
            double boxX, double boxY,
            double boxWidth, double boxHeight) {
        // If outside the box - calculate new dx and dy
        double dx = this.getDx(),dy = this.getDy();
        if (x1 < boxX) {
            dx = Math.abs(this.getDx());
        } else if (x2 > boxWidth) {
            dx = -Math.abs(this.getDx());
        }
        if (y1 < boxY) {
            dy = Math.abs(this.getDy());
        } else if (y2 > boxHeight) {
            dy = -Math.abs(this.getDy());
        }

        this.setVelocity(dx,dy);
    }
    @Override
    public void move(long elapsedTimeNs) {
        x1 += this.getDx() * elapsedTimeNs / BILLION;
        y1 += this.getDy() * elapsedTimeNs / BILLION;
        x2 += this.getDx() * elapsedTimeNs / BILLION;
        y2 += this.getDy() * elapsedTimeNs / BILLION;
    }
}
