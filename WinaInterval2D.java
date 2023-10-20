package winapackage;


/******************************************************************************
 *  Compilation:  javac Interval2D.java
 *  Execution:    java Interval2D
 *
 *  Implementation of 2D interval.
 *
 ******************************************************************************/

public class WinaInterval2D<Key extends Comparable<Key>> { 
    public final WinaInterval<Key> intervalX;   // x-interval
    public final WinaInterval<Key> intervalY;   // y-interval
   
    public WinaInterval2D(WinaInterval<Key> intervalX, WinaInterval<Key> intervalY) {
        this.intervalX = intervalX;
        this.intervalY = intervalY;
    }

    // does this 2D interval a intersect b?
    public boolean intersects(WinaInterval2D<Key> b) {
        if (intervalX.intersects(b.intervalX)) return true;
        if (intervalY.intersects(b.intervalY)) return true;
        return false;
    }

    // does this 2D interval contain (x, y)?
    public boolean contains(Key x, Key y) {
        return intervalX.contains(x) && intervalY.contains(y);
    }

    // return string representation
    public String toString() {
        return intervalX + " x " + intervalY;
    }



    // test client
    public static void main(String[] args) {
    	WinaInterval<Double> intervalX = new WinaInterval<Double>(0.0, 1.0);
    	WinaInterval<Double> intervalY = new WinaInterval<Double>(5.0, 6.0);
    	WinaInterval2D<Double> box1 = new WinaInterval2D<Double>(intervalX, intervalY);
        intervalX = new WinaInterval<Double>(-5.0, 5.0);
        intervalY = new WinaInterval<Double>(3.0, 7.0);
        WinaInterval2D<Double> box2 = new WinaInterval2D<Double>(intervalX, intervalY);
        System.out.println("box1 = " + box1);
        System.out.println("box2 = " + box2);
        System.out.println(box1.contains(0.5, 5.5));
        System.out.println(!box1.contains(1.5, 5.5));
        System.out.println(box1.intersects(box2));
        System.out.println(box2.intersects(box1));
    }
}

