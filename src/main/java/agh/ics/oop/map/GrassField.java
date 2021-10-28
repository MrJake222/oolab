package agh.ics.oop.map;

import agh.ics.oop.Vector2d;
import agh.ics.oop.map.element.Grass;
import agh.ics.oop.map.element.IMapElement;

public class GrassField extends AbstractWorldMap {

    public GrassField(int grassQuantity) {
        super(0, 0);

        final double range = Math.sqrt(grassQuantity * 10);

        int cnt = 0;
        while (cnt < grassQuantity) {
            int x = (int) (Math.random() * range);
            int y = (int) (Math.random() * range);
            Vector2d position = new Vector2d(x, y);
            if (!isOccupied(position)) {
                Grass grass = new Grass(this, new Vector2d(x, y));
                place(grass);
                cnt += 1;
            }
        }
    }

    @Override
    public boolean place(IMapElement element) {
        if (super.place(element)) {
            upperRight = upperRight.upperRight(element.getPosition());
            lowerLeft = lowerLeft.lowerLeft(element.getPosition());
            return true;
        }

        return false;
    }
}
