package main.structures;

public class Vector2d {
    public final int x;
    public final int y;

    public Vector2d(int x, int y){
        this.x = x;
        this.y = y;
    }

    public boolean precedes(Vector2d other){
        return this.x <= other.x && this.y <= other.y;
    }

    public boolean follows(Vector2d other){
        return this.x >= other.x && this.y >= other.y;
    }

    public Vector2d upperRight(Vector2d other){
        int newX = (this.x >= other.x)?this.x:other.x;
        int newY = (this.y >= other.y)?this.y:other.y;
        return new Vector2d(newX, newY);
    }

    public Vector2d lowerLeft(Vector2d other){
        int newX = (this.x <= other.x)?this.x:other.x;
        int newY = (this.y <= other.y)?this.y:other.y;
        return new Vector2d(newX, newY);
    }

    public Vector2d add(Vector2d other){
        return new Vector2d(this.x+other.x, this.y+other.y);
    }

    public Vector2d subtract(Vector2d other){
        return new Vector2d(this.x-other.x, this.y-other.y);
    }

    public Vector2d opposite(){
        return new Vector2d(-1*this.x,-1*this.y);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj instanceof Vector2d){
            Vector2d that = (Vector2d)obj;
            return that.x == this.x && that.y == this.y;
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 13;

        hash += this.x * 37;
        hash += this.y * 17;

        return hash;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)",this.x, this.y);
    }
}
