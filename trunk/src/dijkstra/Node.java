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
public class Node {
    //For drawing
    private int x_cor;
    private int y_cor;

    //Data structure
    private Node parent;
    private Node leftSibling;
    private Node rightSibling;
    private Node children;
    private Node pred;

    public  ArrayList<Edge> incomingEdges;
    public  ArrayList<Edge> outgoingEdges;

    private int data;
    private int key;
    public int rank;
    State state;

    public Node() {
    }

    public Node(int data, int key) {
	this.data = data;
	this.key = key;
	this.parent = null;
	this.leftSibling = null;
	this.rightSibling = null;
	this.children = null;
	this.pred = null;
	this.rank = 0;
	this.state = State.UNLABELED;
    }

    public Node(int x_cor, int y_cor, int data, int key) {
	this.x_cor = x_cor;
	this.y_cor = y_cor;
	this.data = data;
	this.key = key;
	this.parent = null;
	this.leftSibling = null;
	this.rightSibling = null;
	this.children = null;
	this.pred = null;
	this.rank = 0;
	this.state = State.UNLABELED;
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	final Node other = (Node) obj;
	if (this.x_cor != other.x_cor) {
	    return false;
	}
	if (this.y_cor != other.y_cor) {
	    return false;
	}
	return true;
    }

    @Override
    public int hashCode() {
	int hash = 5;
	hash = 29 * hash + this.x_cor;
	hash = 29 * hash + this.y_cor;
	return hash;
    }

    @Override
    public String toString() {
	return "Node{" + "x_cor=" + x_cor + "y_cor=" + y_cor + '}';
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

    public Node getParent() {
	return parent;
    }

    public void setParent(Node parent) {
	this.parent = parent;
    }

    public Node getPred() {
	return pred;
    }

    public void setPred(Node pred) {
	this.pred = pred;
    }

    public Node getChildren() {
	return children;
    }

    public void setChildren(Node children) {
	this.children = children;
    }

    public Node getLeftSibling() {
	return leftSibling;
    }

    public void setLeftSibling(Node leftSibling) {
	this.leftSibling = leftSibling;
    }

    public Node getRightSibling() {
	return rightSibling;
    }

    public void setRightSibling(Node rightSibling) {
	this.rightSibling = rightSibling;
    }

    public boolean addChild(Node child) {
	if (this.children != null) {
	    this.children.addSibling(child);
	} else {
	    this.children = child;
	    child.setParent(this);
	    this.rank = 1;
	}
	return true;
    }

    public boolean addSibling(Node sibling) {
	Node temp = this.rightMostSibling();
	if (temp == null)
	    return false;

	temp.setRightSibling(sibling);
	sibling.setLeftSibling(temp);
	sibling.setParent(this.parent);
	sibling.setRightSibling(null);

	if (this.parent != null)
	    this.parent.rank++;

	return true;
    }

    public boolean remove() {
	if (this.parent != null) {
	    this.parent.rank--;
	    if (this.leftSibling != null)
		this.parent.setChildren(this.leftSibling);
	    else if (this.rightSibling != null)
		this.parent.setChildren(this.rightSibling);
	    else
		this.parent.setChildren(null);
	}

	if (this.leftSibling != null)
	    this.leftSibling.setRightSibling(this.rightSibling);
	if (this.rightSibling != null)
	    this.rightSibling.setLeftSibling(this.leftSibling);

	this.leftSibling = null;
	this.rightSibling = null;
	this.parent = null;

	return true;
    }

    public void addIncomingEdge(Edge edge) {
	this.incomingEdges.add(edge);
    }

    public void addOutgoingEdge(Edge edge) {
	this.outgoingEdges.add(edge);
    }

    public Node rightMostSibling() {
	if (this == null) return null;
	Node temp = this;
	while (temp.getRightSibling() != null)
	    temp = temp.getRightSibling();
	return temp;
    }

    public Node leftMostSibling() {
	if (this == null) return null;
	Node temp = this;
	while (temp.getLeftSibling() != null)
	    temp = temp.getLeftSibling();
	return temp;
    }

}
