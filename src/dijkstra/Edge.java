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
    private Node tail;
    private Node head;
    private int length;

    public Edge(Node head, Node tail, int length) {
	this.tail = tail;
	this.head = head;
	this.length = length;
	this.edgeType = EdgeType.SINGLE;
    }

    public Node getHead() {
	return head;
    }

    public void setHead(Node head) {
	this.head = head;
    }

    public int getLength() {
	return length;
    }

    public void setLength(int length) {
	this.length = length;
    }

    public Node getTail() {
	return tail;
    }

    public void setTail(Node tail) {
	this.tail = tail;
    }

    public boolean checkStage() {
	if (head == null || tail == null)
	    return false;
	if (head.state == State.LABELED && tail.state == State.LABELED)
	    return true;
	return false;
    }

}
