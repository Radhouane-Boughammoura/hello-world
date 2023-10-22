package winapackage;

public class WinaInterval3D<Key extends Comparable<Key>> {
	
	public final WinaInterval<Key> intervalX; //x-interval
	public final WinaInterval<Key> intervalY; //y-interval
	public final WinaInterval<Key> intervalZ; //z-interval
	
	public WinaInterval3D(WinaInterval<Key> intervalX, WinaInterval<Key> intervalY, WinaInterval<Key> intervalZ){
		this.intervalX = intervalX;
		this.intervalY = intervalY;
		this.intervalZ = intervalZ;
	}
	
	// does this 3D interval intersect a intersect b ?
	public boolean intersects(WinaInterval3D<Key> b){
		if(intervalX.intersects(b.intervalX)) return true;
		if(intervalY.intersects(b.intervalY)) return true;
		if(intervalZ.intersects(b.intervalZ)) return true;
		return false;
	}
	
	// does this 3D interval contains (x,y,z)
	public boolean contains(Key x, Key y, Key z){
		return intervalX.contains(x) && intervalY.contains(y) && intervalZ.contains(z);
	}
	
	//return string representation
	public String toString(){
		return intervalX + "x" + intervalY + "x" + intervalZ;
	}
	
	//test client
	public static void main(String[] args) {
		WinaInterval<Double> intervalX = new WinaInterval<Double>(0.0, 1.0);
		WinaInterval<Double> intervalY = new WinaInterval<Double>(5.0, 6.0);
		WinaInterval<Double> intervalZ = new WinaInterval<Double>(2.0, 4.0);
		
		WinaInterval3D<Double> box1 = new WinaInterval3D<Double>(intervalX, intervalY, intervalZ);
		
		intervalX = new WinaInterval<Double>(-5.0, 5.0);
		intervalY = new WinaInterval<Double>(3.0, 7.0);
		intervalZ = new WinaInterval<Double>(4.0, 8.0);
		
		WinaInterval3D<Double> box2 = new WinaInterval3D<Double>(intervalX, intervalY, intervalZ);
		
		System.out.println(box1);
		System.out.println(box2);
		
		System.out.println(box1.contains(0.5, 5.5, 6.0));
		System.out.println(!box1.contains(1.5, 5.5, 6.0));
		
		System.out.println(box1.intersects(box2));
		System.out.println(box2.intersects(box1));		
	}

}
