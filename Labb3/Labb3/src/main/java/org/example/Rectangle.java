package org.example;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends FillableShape {

    private double height, width;

    public Rectangle(double height, double width, double x, double y, Color color){
        this.height = height;
        this.width = width;
        setX(x);
        setY(y);
        setColor(color);
        setFilled(false);
        setVelocity(30,30);
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }


    @Override
    public void paint(GraphicsContext gc){
        gc.setLineWidth(2);

        if (this.isFilled()){
            gc.setFill(getColor());
            gc.fillRect(getX()-width/2,getY()-height/2,width,height);
        }
        else{
            gc.setStroke(getColor());
            gc.strokeRect(getX()-width/2,getY()-height/2,width,height);
        }

    }
    @Override
    public void move(long elapsedTimeNs) {
        setX(getX()+getDx()*elapsedTimeNs/BILLION);
        setY(getY()+getDy()*elapsedTimeNs/BILLION);

    }
    @Override
    public void constrain(
            double boxX, double boxY,
            double boxWidth, double boxHeight) {
        double dx = getDx(), dy = getDy();
        // If outside the box - calculate new dx and dy
        if (getX()-width/2 < boxX) {
            dx = Math.abs(getDx());
        } else if (getX()+width/2 > boxWidth) {
            dx = -Math.abs(getDx());
        }
        if (getY()-height/2 < boxY) {
            dy = Math.abs(getDy());
        } else if (getY()+height/2 > boxHeight) {
            dy = -Math.abs(getDy());
        }
        setVelocity(dx,dy);
    }

}
