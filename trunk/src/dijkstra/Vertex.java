/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dijkstra;

import java.util.ArrayList;

enum State {
    LABELED, UNLABELED, SCANNED
}
/**
 *
 * @author Linh
 */
public class Vertex {
    // For drawing
    private int x_cor;
    private int y_cor;

    // For a vertical in Fibonacci heap
    private Vertex parent;
    private Vertex leftSibling;
    private Vertex rightSibling;
    private Vertex children;
    private int data;
    private int key;
    private boolean mark;
    private int rank;
    
    // For graph
    private Vertex pred;
    public  ArrayList<Edge> incomingEdges;
    public  ArrayList<Edge> outgoingEdges;
    State state;

    public Vertex(int x_cor, int y_cor, int data, int key) {
	this.x_cor = x_cor;
	this.y_cor = y_cor;
	this.data = data;
	this.key = key;
	this.incomingEdges = new ArrayList<Edge>();
	this.outgoingEdges = new ArrayList<Edge>();
	this.parent = null;
	this.children = null;
	this.pred = null;
	this.mark = false;
	this.rank = 0;
	this.state = State.UNLABELED;
	this.sibling();
    }

    private void sibling() {
	this.leftSibling = this.rightSibling = this;
    }

    @Override
    public String toString() {
	return "Node{" + "incomingEdges=" + incomingEdges + "outgoingEdges=" + outgoingEdges + "data=" + data + "state=" + state + '}';
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	final Vertex other = (Vertex) obj;
	if (this.x_cor != other.x_cor) {
	    return false;
	}
	if (this.y_cor != other.y_cor) {
	    return false;
	}
	if (this.data != other.data) {
	    return false;
	}
	if (this.key != other.key) {
	    return false;
	}
	return true;
    }

    @Override
    public int hashCode() {
	int hash = 7;
	hash = 37 * hash + this.x_cor;
	hash = 37 * hash + this.y_cor;
	hash = 37 * hash + this.data;
	hash = 37 * hash + this.key;
	return hash;
    }
    
    public int getX_cor() {
	return x_cor;
    }

    public int getY_cor() {
	return y_cor;
    }

    public int getData() {
	return data;
    }

    public void setData(int data) {
	this.data = data;
    }

    public int getKey() {
	return key;
    }

    public void setKey(int key) {
	this.key = key;
    }

    public Vertex getParent() {
	return parent;
    }

    public void setParent(Vertex parent) {
	this.parent = parent;
    }

    public Vertex getPred() {
	return pred;
    }

    public void setPred(Vertex pred) {
	this.pred = pred;
    }

    public Vertex getChildren() {
	return children;
    }

    public void setChildren(Vertex children) {
	this.children = children;
    }

    public Vertex getLeftSibling() {
	return leftSibling;
    }

    public void setLeftSibling(Vertex leftSibling) {
	this.leftSibling = leftSibling;
    }

    public Vertex getRightSibling() {
	return rightSibling;
    }

    public void setRightSibling(Vertex rightSibling) {
	this.rightSibling = rightSibling;
    }

    public boolean isMark() {
	return mark;
    }

    public void setMark(boolean mark) {
	this.mark = mark;
    }

    public int getRank() {
	return rank;
    }

    public void setRank(int rank) {
	this.rank = rank;
    }

    // method for drawing
    public Edge getIncomingEdge(Vertex head) {
	for (Edge edge : incomingEdges)
	    if (edge.getHead().equals(head))
		return edge;
	return null;
    }

    public Edge getOutgoingEdge(Vertex tail) {
	for (Edge edge : incomingEdges)
	    if (edge.getTail().equals(tail))
		return edge;
	return null;
    }

    // method for a node in Fibonacci heap
    public boolean isSingle() {
	return (this == this.rightSibling);
    }

    public void addChild(Vertex child) {
	if (this.children != null)
	    this.children.addSibling(child);
	else
	    this.children = child;
	child.setParent(this);
	child.mark = false;
	this.rank++;
    }

    public void addSibling(Vertex sibling) {
	if (sibling == null)
	    return;

	Vertex tLeft = this.leftSibling;
	Vertex sLeft = sibling.getLeftSibling();

	tLeft.setRightSibling(sLeft);
	sLeft.setRightSibling(this);

	this.setLeftSibling(sLeft);
	sibling.setLeftSibling(tLeft);
    }

    public void removeSibling() {
	this.getLeftSibling().setRightSibling(this.rightSibling);
	this.getRightSibling().setLeftSibling(this.leftSibling);
	this.setRightSibling(this);
	this.setLeftSibling(this);
    }

    public void remove() {
	if (this.parent != null) {
	    // node have parent
	    if (this == this.rightSibling)
		this.parent.setChildren(null);
	    else
		this.parent.setChildren(this.rightSibling);
	    this.parent.rank--;
	}

	if (this != this.rightSibling) this.removeSibling();
	this.setParent(null);
	this.mark = false;
    }
}
