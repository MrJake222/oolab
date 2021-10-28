package agh.ics.oop;

public class Animal {

    private IWorldMap map;
    private Vector2d position;
    private MapDirection mapDirection;

    public Animal(IWorldMap map, Vector2d position) {
        this.map = map;
        this.position = position;
        this.mapDirection = MapDirection.NORTH;
    }

    public Animal(IWorldMap map) {
        this(map, new Vector2d(2, 2));
    }

    public Animal() {
        this(new RectangularMap(5, 5));
    }

    @Override
    public String toString() {
        // return "{Zwięrzę na "+this.position+" kierunek "+this.mapDirection+"}";
        return this.mapDirection.getSymbol();
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public Vector2d getPosition() {
        return position;
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
                if (map.canMoveTo(newPos)) position = newPos;
                break;
            }

            case BACKWARD: {
                Vector2d newPos = position.add(mapDirection.toUnitVector().opposite());
                if (map.canMoveTo(newPos)) position = newPos;
                break;
            }
        }
    }
}
