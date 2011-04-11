/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dijkstra;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Linh
 */
public class jHeapPanel extends JPanel {

    public FibonacciHeap heap;
    private static final Color Normal = Color.GREEN;
    private static final Color Mark = Color.BLUE;
    private static final Color Min = Color.RED;
    private static final Color Text = Color.BLACK;
    private static final int radius = 25;

    public jHeapPanel() {
	super();
	this.heap = null;
    }

    @Override
    protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	this.drawHeap(g);
    }

    private void drawHeap(Graphics g) {
	if (this.heap == null)
	    return;
	if (!this.heap.isEmpty()) {
	    Color c = g.getColor();
	    int i = this.drawNode(g, this.heap.findMin(), 50, 40);
	    g.setColor(c);
	}
    }

    private int drawNode(Graphics g, Node node, int x, int y) {
	int delta = 0;
	Node temp = node;
	do {
	    if (temp == this.heap.findMin())
		g.setColor(Min);
	    else if (temp.isMark())
		g.setColor(Mark);
	    else
		g.setColor(Normal);
	    
	    g.fillOval(x + delta, y, radius, radius);
	    g.setColor(Text);
	    g.drawString(Integer.toString(temp.getData()), x + delta + 2*radius/5, y + 3*radius/4);
	    g.drawString(Integer.toString(temp.getKey()), x + delta + radius, y);
	    int i = 0;
	    if (temp.getChildren() != null)
		i = this.drawNode(g, temp.getChildren(), x + delta, y + radius + 5);
	    if (i>0)
		delta += i;
	    else
		delta += radius + 20;
	    temp = temp.getRightSibling();
	} while (temp != node);
	return delta;
    }

}
