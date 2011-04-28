/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dijkstra;

/**
 *
 * @author Linh
 */
enum EdgeType {
    SINGLE, FIRST, SECOND
}

public class Edge {
    //For draw
    EdgeType edgeType;

    //Data structure
    private Vertex tail;
    private Vertex head;
    private int length;
    State edgeState;

    public Edge(Vertex head, Vertex tail, int length) {
	this.tail = tail;
	this.head = head;
	this.length = length;
	this.edgeType = EdgeType.SINGLE;
	this.edgeState = State.UNLABELED;
    }

    @Override
    public String toString() {
	return "Edge{" + "edgeType=" + edgeType + "length=" + length + '}';
    }

    public Vertex getHead() {
	return head;
    }

    public void setHead(Vertex head) {
	this.head = head;
    }

    public int getLength() {
	return length;
    }

    public void setLength(int length) {
	this.length = length;
    }

    public Vertex getTail() {
	return tail;
    }

    public void setTail(Vertex tail) {
	this.tail = tail;
    }
    
}
