/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dijkstra;

/**
 *
 * @author Linh
 */
public class FibonacciHeap {
    private Node[] listByRank;
    private Node minRoot;

    public FibonacciHeap() {
	this.listByRank = new Node[100];
	this.minRoot = null;
    }

    public FibonacciHeap(Node minRoot) {
	this.listByRank = new Node[100];
	this.minRoot = minRoot;
	this.minRoot.setParent(null);
	this.minRoot.setChildren(null);
	this.minRoot.setLeftSibling(null);
	this.minRoot.setRightSibling(null);
	this.minRoot.rank = 0;
    }

    public boolean isEmpty() {
	return (this.minRoot == null);
    }

    public boolean insertVertex(Node node) {
	if (node == null)
	    return false;
	if (this.minRoot == null) {
	    this.minRoot = node;
	    this.minRoot.setParent(null);
	    this.minRoot.setChildren(null);
	    this.minRoot.setLeftSibling(null);
	    this.minRoot.setRightSibling(null);
	    this.minRoot.rank = 0;
	} else {
	    this.minRoot.addSibling(node);
	    if (this.minRoot.getKey() > node.getKey())
		this.minRoot = node;
	}
	return true;
    }

    public void decreaseKey(int delta, Node vertex) {
	vertex.setKey(delta);
	if (vertex.getParent() != null) {
	    vertex.remove();
	    this.minRoot.addSibling(vertex);
	}
	if (vertex.getKey() < this.minRoot.getKey())
	    this.minRoot = vertex;
    }

    public Node findMin() {
	return this.minRoot;
    }

    public Node deleteMin() {
	Node temp = null;
	Node nextTemp = null;

	if (this.minRoot.getChildren() != null)
	    temp = this.minRoot.getChildren().leftMostSibling();

	//Add minRoot's child to root list
	while (temp != null) {
	    nextTemp = temp.getRightSibling();
	    temp.remove();
	    this.minRoot.addSibling(temp);
	    temp = nextTemp;
	}

	//Remove minRoot
	Node node = this.minRoot;
	temp = this.minRoot.leftMostSibling();
	if (temp == this.minRoot) {
	    if (this.minRoot.getRightSibling() != null)
		temp = this.minRoot.getRightSibling();
	    else {
		this.minRoot.remove();
		this.minRoot = null;
		return node;
	    }
	}
	this.minRoot.remove();
	this.minRoot = temp;
	for (int i = 0; i < 100; i++)
	    listByRank[i] = null;
	while (temp != null) {
	    if (temp.getKey() < this.minRoot.getKey())
		this.minRoot = temp;
	    nextTemp = temp.getRightSibling();
	    link(temp);
	    temp = nextTemp;
	}
	return node;
    }

    private boolean link(Node root) {
	if (listByRank[root.rank] == null) {
	    listByRank[root.rank] = root;
	    return false;
	} else {
	    Node linkVertex = listByRank[root.rank];
	    listByRank[root.rank] = null;
	    if ((root.getKey() < linkVertex.getKey()) || root == this.minRoot) {
		linkVertex.remove();
		root.addChild(linkVertex);
		if (listByRank[root.rank] != null)
		    link(root);
		else
		    listByRank[root.rank] = root;
	    } else {
		root.remove();
		linkVertex.addChild(root);
		if (listByRank[linkVertex.rank] != null)
		    link(linkVertex);
		else
		    listByRank[linkVertex.rank] = linkVertex;
	    }
	    return true;
	}
    }
}
