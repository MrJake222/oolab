package agh.ics.oop;

public class Animal {

    private static final Vector2d MAP_TOP = new Vector2d(4, 4);
    private static final Vector2d MAP_BOTTOM = new Vector2d(0, 0);
    private static boolean isValidMapPos(Vector2d position) {
        return position.precedes(MAP_TOP) && position.follows(MAP_BOTTOM);
    }

    private MapDirection mapDirection;
    private Vector2d position;

    public Animal() {
        this.mapDirection = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
    }

    @Override
    public String toString() {
        return "{Zwięrzę na "+this.position+" kierunek "+this.mapDirection+"}";
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public MapDirection getDirection() {
        return mapDirection;
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT: {
                mapDirection = mapDirection.next();
                break;
            }

            case LEFT: {
                mapDirection = mapDirection.previous();
                break;
            }

            case FORWARD: {
                Vector2d newPos = position.add(mapDirection.toUnitVector());
                if (isValidMapPos(newPos)) position = newPos;
                break;
            }

            case BACKWARD: {
                Vector2d newPos = position.add(mapDirection.toUnitVector().opposite());
                if (isValidMapPos(newPos)) position = newPos;
                break;
            }
        }
    }
}
