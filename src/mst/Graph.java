package mst;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private int id;
    private List<String> nodes;
    private List<Edge> edges;

    public Graph(int id) {
        this.id = id;
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    public void addNode(String node) {
        if (!nodes.contains(node)) {
            nodes.add(node);
        }
    }

    public void addEdge(String from, String to, int weight) {
        edges.add(new Edge(from, to, weight));
        addNode(from);
        addNode(to);
    }

    public int getId() {
        return id;
    }

    public List<String> getNodes() {
        return nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public int getNodeCount() {
        return nodes.size();
    }

    public int getEdgeCount() {
        return edges.size();
    }

    @Override
    public String toString() {
        return "Graph " + id + " | Nodes: " + nodes.size() + ", Edges: " + edges.size();
    }
}