package radhouanepackage;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.applet.*;

import radhouanepackage.MyQuadTree.Node;

public class QuadTreeDisplay extends Applet {

	private MyQuadTree<Integer, String> qtree = new MyQuadTree<Integer, String>(); 
		
	public void paint(Graphics g) {
        //renderQuadTree(g, );
    }
    private void render(Graphics g) {
        setBackground(Color.white);        
        System.out.println("-----------");
        g.drawRect(10, 10, 20, 20);
    }
    /** Draw the quadtree root on the applet
     *  it subdividing the enclosing rectangle  
     */
    public void query2D(Interval2D<Integer> rect) {
        query2D(qtree.root, rect);
    }
    /**@param node MyQuadtree.Node to represent
    *  @param x top left of rectangle
    *  @param y top right of rectangle
    *  @param width width of rectangle
    *  @param height height of rectangle
    *  
    */
	
	// painting is realized like query2D
	
    private void query2D(Node h, Interval2D<Integer> rect) {
        if (h == null) return;
        Integer xmin = rect.intervalX.min();
        Integer ymin = rect.intervalY.min();
        Integer xmax = rect.intervalX.max();
        Integer ymax = rect.intervalY.max();
        if (rect.contains(h.x, h.y)
            System.out.println("    (" + h.x + ", " + h.y + ") " + h.value);
        if ( less(xmin, h.x) &&  less(ymin, h.y)) query2D(h.SW, rect);
        if ( less(xmin, h.x) && !less(ymax, h.y)) query2D(h.NW, rect);
        if (!less(xmax, h.x) &&  less(ymin, h.y)) query2D(h.SE, rect);
        if (!less(xmax, h.x) && !less(ymax, h.y)) query2D(h.NE, rect);
    }
    
}
