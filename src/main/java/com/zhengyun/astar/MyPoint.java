package com.zhengyun.astar;

import java.util.Objects;

/**
 * Created by 听风 on 2017/12/21.
 */
public class MyPoint {

    private int x;

    private int y;

    private MyPoint parent;

    private double h;//估值

    private double g;//实际值

    private double cost;

    public MyPoint(int x, int y){
        this.x = x;
        this.y = y;
    }

    public MyPoint(int x, int y, MyPoint target){
        this.x = x;
        this.y = y;
        calcH(target);
    }

    public String getKey(){
        return x + "_" + y;
    }

    public double getCost(){
        return cost;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public MyPoint getParent() {
        return parent;
    }

    public void setParent(MyPoint parent) {
        this.parent = parent;
    }

    public void calcH(MyPoint target) {
        h = Math.abs(x - target.x) + Math.abs(y - target.y);
    }

    public void setH(int h) {
        this.h = h;
    }

    public double getG() {
        return g;
    }

    public void setG(double g) {
        this.g = g;
        this.cost = g + this.h;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyPoint myPoint = (MyPoint) o;
        return x == myPoint.x &&
                y == myPoint.y;
    }

    @Override
    public int hashCode() {

        return Objects.hash(x, y);
    }
}
