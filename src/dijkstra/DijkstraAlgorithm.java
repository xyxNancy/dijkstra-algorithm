/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dijkstra;

import java.util.Vector;

class Node {

    public final int START  = 1;
    public final int END    = -1;
    public final int NORMAL = 0;

    private String name;        //name of node
    private int type;           //type in graph
    private Vector<Edge> edges; //array of edges

    public Node(String name) {
        this.name = name;
        this.type = this.NORMAL;
        this.edges = new Vector<Edge>();
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void addEdge(Edge edge) {
        this.edges.add(edge);
    }

    public void removeEdge(Edge edge) {
        this.edges.remove(edge);
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
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }
    
}

class Edge {
    private String name;    //name of edge
    private int length;     //length of edge
    private Node startNode; //starting node
    private Node endNode;   //ending node

    public Edge(String name, int length, Node startNode, Node endNode) {
        this.name = name;
        this.length = length;
        this.startNode = startNode;
        this.endNode = endNode;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Edge other = (Edge) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }

}

class MatrixGraph {
    private int numNodes;       //number of nodes
    private Node arrayNode[];   //array of nodes
    private Edge matrixEdge[][];//matrix of distance

    public MatrixGraph(int numNodes) {
        this.numNodes = numNodes;
        this.arrayNode = new Node[numNodes];
        this.matrixEdge = new Edge[numNodes][numNodes];
    }

    public Edge getEdge(int row, int col) {
        return this.matrixEdge[row][col];
    }

    public int getPosNode(Node node) {
        for (int i = 0; i < numNodes; i++)
            if (arrayNode[i].equals(node))
                return i;
        return -1;
    }

}

public class DijkstraAlgorithm {

    public static void main(String[] args) {
    }
}
