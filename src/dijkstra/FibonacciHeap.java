/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dijkstra;

import java.util.ArrayList;

/**
 *
 * @author Linh
 */
public class FibonacciHeap {
    // For Fibonacci heap
    private Vertex minRoot;
    private int count;
    private int maxRank;

    public FibonacciHeap() {
	this.minRoot = null;
	this.maxRank = 0;
    }

    public FibonacciHeap(Vertex minRoot) {
	this.minRoot = minRoot;
	this.minRoot.setParent(null);
	this.minRoot.setChildren(null);
	this.minRoot.setLeftSibling(null);
	this.minRoot.setRightSibling(null);
	this.minRoot.setRank(0);
	this.maxRank = 0;
    }

    // method for Fibonacci heap
    public boolean isEmpty() {
	return (this.minRoot == null);
    }

    public boolean insertVertex(Vertex node) {
	if (node == null)
	    return false;
	if (this.minRoot == null) {
	    // Have no root
	    this.minRoot = node;
	    this.minRoot.setParent(null);
	} else {
	    this.minRoot.addSibling(node);
	    if (this.minRoot.getKey() > node.getKey())
		this.minRoot = node;
	}
	//this.count++;
	return true;
    }

    public void decreaseKey(int delta, Vertex vertex) {
	vertex.setKey(delta);
	// check position of vertex
	Vertex parent = vertex.getParent();
	if (parent == null) {
	    // right position check new min root
	    if (vertex.getKey() < this.minRoot.getKey())
		this.minRoot = vertex;
	    return;
	} else if (parent.getKey() < delta)
	    // still right position
	    return;

	Vertex node = vertex;
	while (true) {
	    node.remove();
	    this.insertVertex(node);
	    if (parent.getParent() == null)
		// parent is root
		break;
	    else if (!parent.isMark()) {
		// parent is not mark
		parent.setMark(true);
		break;
	    } else {
		// parent is mark
		node = parent;
		parent = parent.getParent();
	    }
	}
    }

    public Vertex findMin() {
	return this.minRoot;
    }

    public Vertex deleteMin() {
	if (this.minRoot != null)
	    count--;
	else
	    return null;
	// Make children of minRoot new roots
	if (this.minRoot.getChildren() != null) {
	    Vertex temp = this.minRoot.getChildren();
	    while (temp != null) {
		temp.remove();
		this.insertVertex(temp);
		temp = this.minRoot.getChildren();
	    }
	}

	Vertex min = this.minRoot;
	// Case: delete last node
	if (this.minRoot.getRightSibling() == this.minRoot) {
	    this.count = 0;
	    this.maxRank = 0;
	    this.minRoot.remove();
	    this.minRoot = null;
	    return min;
	}

	// Merge root with same rank
	ArrayList<Vertex> rankRoots = new ArrayList<Vertex>(this.maxRank + 1);
	for (int i = 0; i < this.maxRank + 1; i++)
	    rankRoots.add(null);
	this.maxRank = 0;
	Vertex curNode = this.minRoot.getRightSibling();
	int curRank;
	do {
	    curRank = curNode.getRank();
	    Vertex cur = curNode;
	    curNode = curNode.getRightSibling();
	    while (rankRoots.get(curRank) != null) {
		// have root with same rank
		Vertex add = rankRoots.get(curRank);
		if (cur.getKey() > add.getKey()) {
		    Vertex temp = cur;
		    cur = add;
		    add = temp;
		}
		add.removeSibling();
		cur.addChild(add);
		rankRoots.set(curRank, null);
		curRank++;
		if (curRank >= rankRoots.size())
		    rankRoots.add(null);
	    }
	    rankRoots.set(curRank, cur);
	} while (curNode != this.minRoot);

	// Remove minRoot and find new minRoot
	this.minRoot.remove();
	this.minRoot = null;
	for (int i = 0; i < rankRoots.size(); i++) {
	    Vertex temp = rankRoots.get(i);
	    if (temp != null) {
		temp.setLeftSibling(temp);
		temp.setRightSibling(temp);
		insertVertex(temp);
		if (i>this.maxRank)
		    this.maxRank = i;
	    }
	}
	return min;
    }

}
