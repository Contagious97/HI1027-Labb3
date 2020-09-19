package org.example;

abstract public class FillableShape extends Shape{
    private boolean filled;

    protected FillableShape(){
        this.filled = false;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }
}
