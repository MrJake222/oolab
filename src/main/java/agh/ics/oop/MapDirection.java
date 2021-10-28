package agh.ics.oop;

public enum MapDirection {
    NORTH("Północ"),
    SOUTH("Południe"),
    WEST("Zachód"),
    EAST("Wschód");

    private final String description;

    MapDirection(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }

    public MapDirection next() {
        switch (this) {
            case NORTH: return EAST;
            case EAST: return SOUTH;
            case SOUTH: return WEST;
            case WEST: return NORTH;
        }
        throw new AssertionError("No next case defined for "+this);
    }

    public MapDirection previous() {
        switch (this) {
            case NORTH: return WEST;
            case WEST: return SOUTH;
            case SOUTH: return EAST;
            case EAST: return NORTH;
        }
        throw new AssertionError("No previous case defined for "+this);
    }

    public Vector2d toUnitVector() {
        switch (this) {
            case NORTH: return new Vector2d(0,1);
            case EAST: return new Vector2d(1,0);
            case SOUTH: return new Vector2d(0,-1);
            case WEST: return new Vector2d(-1,0);
        }
        throw new AssertionError("No toUnitVector case defined for "+this);
    }
}
