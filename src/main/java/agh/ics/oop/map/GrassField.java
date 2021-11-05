package agh.ics.oop.map;

import agh.ics.oop.Vector2d;
import agh.ics.oop.map.element.Grass;
import agh.ics.oop.map.element.IMapElement;

import java.util.Random;

public class GrassField extends AbstractWorldMap {

    private MapBoundary boundary;

    public GrassField(int grassQuantity) {
        this.boundary = new MapBoundary();

        final double range = Math.sqrt(grassQuantity * 10);
        long seed = new Random().nextLong();
        seed = -4476009104255617078L;
        Random random = new Random(seed);
        System.out.println("seed: " + seed);

        int cnt = 0;
        while (cnt < grassQuantity) {
            int x = (int) (random.nextDouble() * range);
            int y = (int) (random.nextDouble() * range);
            Vector2d position = new Vector2d(x, y);
            if (!isOccupied(position)) {
                Grass grass = new Grass(this, new Vector2d(x, y));
                place(grass);
                cnt += 1;
            }
        }
    }

    @Override
    public Vector2d getLowerLeft() {
        return boundary.getLowerLeft();
    }

    @Override
    public Vector2d getUpperRight() {
        return boundary.getUpperRight();
    }

    @Override
    public boolean place(IMapElement element) {
        if (super.place(element)) {
            if (element instanceof IObservable) {
                ((IObservable) element).addObserver(boundary);
            }
            boundary.addElement(element);
            return true;
        }

        return false;
    }
}
