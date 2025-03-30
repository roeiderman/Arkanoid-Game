/**
 * @author roei derman 322467184.
 * GameEnvironment class
 */
package game;
import java.util.ArrayList;
import java.util.List;
import Geometry.Point;
import Geometry.Line;
import collisions.Collidable;
import collisions.CollisionInfo;

/**
 * GameEnvironment class.
 */
public class GameEnvironment {
    private final List<Collidable> collidableList;

    /**
     * constructor.
     */
    public GameEnvironment() {
        this.collidableList = new ArrayList<>();
    }
    /**
     * add the given collidable to the environment.
     * @param c - collidable object to add
     */
    public void addCollidable(Collidable c) {
        if (c != null) {
            this.collidableList.add(c);
        }
    }
    /**
     * if this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur
     * @param trajectory - line
     * @return info of the closet collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Collidable> copyCollidableList = new ArrayList<>(this.collidableList);
        if (trajectory == null) {
            return null;
        }
        double min;
        Point minDis;
        int minIndex = 0;
        if (copyCollidableList.isEmpty()) {
            return null;
        }
        List<Collidable> cols = new ArrayList<>();
        List<Double> distances = new ArrayList<>();
        List<Point> points = new ArrayList<>();
        for (Collidable c : copyCollidableList) {
            minDis = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (minDis != null) {
                distances.add(minDis.distance(trajectory.start()));
                points.add(minDis);
                cols.add(c);
            }
        }
        if (!distances.isEmpty()) {
            min = distances.get(0);
            for (int i = 1; i < distances.size(); i++) {
                if (distances.get(i) < min) {
                    min = distances.get(i);
                    minIndex = i;
                }
            }
            return new CollisionInfo(points.get(minIndex), cols.get(minIndex));
        } else {
            return null;
        }
    }

    /**
     * remove collidable.
     * @param c - collidable to remove
     */
    public void removeCol(Collidable c) {
        if (c != null) {
            this.collidableList.remove(c);
        }
    }
}
