package main.structures;

public enum MapDirection {
    NORTH(new Vector2d(0,1)),
    NORTH_EAST(new Vector2d(1,1)),
    EAST(new Vector2d(1,0)),
    SOUTH_EAST(new Vector2d(1,-1)),
    SOUTH(new Vector2d(0,-1)),
    SOUTH_WEST(new Vector2d(-1,-1)),
    WEST(new Vector2d(-1,0 )),
    NORTH_WEST(new Vector2d(-1,1));

    private Vector2d unitVector;

    MapDirection(Vector2d unitVector){
        this.unitVector = unitVector;
    }

    public MapDirection next(){
        MapDirection directions[] = MapDirection.values();
        return directions[(this.ordinal()+1)%directions.length];
    }

    public Vector2d toUnitVector(){
        return this.unitVector;
    }
}
