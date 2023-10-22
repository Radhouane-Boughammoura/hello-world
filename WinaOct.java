package winapackage;

/*Dr. Radhouane Boughammoura
 * phd in computer science from FSEG Sfax
 * Associate professor {ISIMa}
 * University of Monastir
 * Tunisia
 */

public class WinaOct <Key extends Comparable<Key>, Value> {

	Node root;
	
	public class Node {
		Key x, y, z;   //x -y -z coordinates
		Node up_NW,up_NE, up_SE, up_SW,
			down_NW, down_NE, down_SE, down_SW;  //eight subtrees
		Value value;	//associated data
	
	
		Node (Key x, Key y, Key z, Value value) {
			this.x = x;
			this.y = y;
			this.z = z;
		
			this.value = value;
		}
	}
	
	/*
	 * insert (x, y, z) into appropriate octant
	 * 
	 */
	
	public void insert(Key x, Key y, Key z, Value value){
		root = insert (root, x, y, z, value);
	}
	
	private Node insert(Node h, Key x, Key y, Key z, Value value){
		if( h == null) return new Node (x, y, z, value);
		else if( less(x, h.x) && less(y, h.y) && less(z, h.z) ) h.down_SW = insert(h.down_SW, x, y, z, value);
		else if ( less(x, h.x) && less(y, h.y) && ! less(z, h.z)) h.up_SW = insert(h.up_SW, x, y, z, value);
		else if ( less(x, h.x) && ! less(y, h.y) && less(z, h.z) ) h.down_NW = insert(h.down_NW, x, y, z, value);
		else if ( less(x, h.x) && ! less(y, h.y) && ! less(z, h.z)) h.up_NW = insert(h.up_NW, x, y, z, value);
		else if ( ! less(x, h.x) && less(y, h.y) && less(z, h.z)) h.down_SE = insert(h.down_SE, x, y, z, value);
		else if ( !less(x, h.x) && less(y, h.y) && !less(z, h.z)) h.up_SE = insert(h.up_SE, x, y, z, value);
		else if ( !less(x, h.x) && !less(y, h.y) && less(z, h.z)) h.down_NE = insert(h.down_NE, x, y, z, value);
		else if ( ! less(x, h.x) && !less(y, h.y) && !less(z, h.z)) h.up_NE = insert(h.up_NE, x, y, z, value);
		return h;	
	}
	
	/* **********************************************************
	 * Range search
	 * **********************************************************/
	public void query3D(WinaInterval3D<Key> box){
		query3D(root, box);
	}
	private void query3D(Node h, WinaInterval3D<Key> box){
		if(h == null) return;
		Key xmin = box.intervalX.min();
		Key ymin = box.intervalY.min();
		Key zmin = box.intervalZ.min();
		
		Key xmax = box.intervalX.max();
		Key ymax = box.intervalY.max();
		Key zmax = box.intervalZ.max();
		
		if(box.contains(h.x, h.y, h.z))
			System.out.println("	("+ h.x + "," + h.y + ","+ h.z + ")" + h.value);
		if( less( xmin, h.x) && less( ymin, h.y) && !less(zmin, h.z) ) query3D(h.up_SW, box);//ok
		if( less(xmin, h.x) && !less( ymax, h.y) && !less(zmin, h.z)) query3D(h.up_NW, box);  //ok
		if( !less(xmax, h.x) && less(ymin, h.y) && !less( zmin, h.z)) query3D(h.up_SE, box);// ok
		if( !less(xmax, h.x) && !less(ymax, h.y) && !less(zmin, h.z)) query3D(h.up_NE, box);// ok
		
		if( less(xmin, h.x) && less(ymin, h.y) && less(zmin, h.z)) query3D(h.down_SW, box);//ok
		if( less(xmin, h.x) && !less(ymax, h.y) && less(zmin, h.z)) query3D(h.down_NW, box); //ok
		if( !less(xmax, h.x) && less(ymin, h.y) && less(zmin, h.z)) query3D(h.down_SE, box); //ok
		if( !less(xmin, h.x) && !less(ymax, h.y) && less(zmin, h.z)) query3D(h.down_NE, box);//ok
	}
	
	/* **********************************************************
	 * Helper comparaison function
	 ************************************************************/
	private boolean less(Key k1, Key k2){ return k1.compareTo(k2) < 0;}
	private boolean eq( Key k1, Key k2){ return k1.compareTo(k2) == 0;}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// test all the program
		int M = Integer.parseInt(args[0]); // M queries
		int N = Integer.parseInt(args[1]); // N points
		
		WinaOct<Integer, String> st = new WinaOct<Integer, String>();
		
		//insert N random points in the box
		for(int i=0; i < N; i++) {
			Integer x = (int) (100 * Math.random());
			Integer y = (int) (100 * Math.random());
			Integer z = (int) (100 * Math.random());
			
			st.insert(x, y, z, "P" + i);
		}
		System.out.println("Done preprocessing " + N + " points");
		
		// do some range searches
		for (int i=0; i < M; i++) {
			Integer xmin = (int) (100 * Math.random());
			Integer xmax = xmin + (int) (10 * Math.random());
			
			Integer ymin = (int) (100 * Math.random());
			Integer ymax = ymin + (int) (20 * Math.random());
			
			Integer zmin = (int) (10 * Math.random());
			Integer zmax = zmin + (int) (10 * Math.random());
			
			WinaInterval<Integer> intX = new WinaInterval<Integer>(xmin, xmax);
			WinaInterval<Integer> intY = new WinaInterval<Integer>(ymin, ymax);
			WinaInterval<Integer> intZ = new WinaInterval<Integer>(zmin, zmax);
			WinaInterval3D<Integer> box = new WinaInterval3D<Integer>(intX, intY, intZ);
			
			System.out.println(box + " : ");
			
			long start = System.nanoTime();
			st.query3D(box);
			long end = System.nanoTime();
			
			long delay = end - start;
			
			System.out.println("time delay in nano:" + delay);
		}
	}

}
