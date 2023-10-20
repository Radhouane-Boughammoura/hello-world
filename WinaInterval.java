package winapackage;


/******************************************************************************
 *  Compilation:  javac WinaInterval.java
 *  Execution:    java WinaInterval
 *
 *  Implementation of an interval.
 *
 ******************************************************************************/

public class WinaInterval<Key extends Comparable<Key>> { 
    private final Key min;    // min endpoint
    private final Key max;    // max endpoint

    public WinaInterval(Key min, Key max) {
        if (less(max, min)) throw new RuntimeException("Illegal argument");
        this.min = min;
        this.max = max;
    }

    // return min endpoint
    public Key min() {
        return min;
    }

    // return max endpoint
    public Key max() {
        return max;
    }

    // is x between min and max
    public boolean contains(Key x) {
        return !less(x, min) && !less(max, x);
    }

    // does this interval a intersect interval b?
    public boolean intersects(WinaInterval<Key> b) {
        WinaInterval<Key> a  = this;
        if (less(a.max, b.min)) return false;
        if (less(b.max, a.min)) return false;
        return true;
    }

    // does this interval a equal interval b?
    public boolean equals(WinaInterval<Key> b) {
        WinaInterval<Key> a  = this;
        return a.min.equals(b.min) && a.max.equals(b.max);
    }


    // comparison helper functions
    private boolean less(Key x, Key y) {
        return x.compareTo(y) < 0;
    }

    // return string representation
    public String toString() {
        return "[" + min + ", " + max + "]";
    }



    // test client
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);

        WinaInterval<Integer> a = new WinaInterval<Integer>(5, 17);
        WinaInterval<Integer> b = new WinaInterval<Integer>(5, 17);
        WinaInterval<Integer> c = new WinaInterval<Integer>(5, 18);
        System.out.println(a.equals(b));
        System.out.println(!a.equals(c));
        System.out.println(!b.equals(c));


        // generate n random points in [-1, 2] and compute
        // fraction that lies in [0, 1]
        WinaInterval<Double> interval = new WinaInterval<Double>(0.0, 1.0);
        int count = 0;
        for (int i = 0; i < n; i++) {
            double x = 3 * Math.random() - 1.0;
            if (interval.contains(x)) count++;
        }
        System.out.println("fraction = " + (1.0 * count / n));
    }
}
