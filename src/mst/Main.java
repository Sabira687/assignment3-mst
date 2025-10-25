package mst;

public class Main {
    public static void main(String[] args) {
        Graph g = new Graph(1);
        g.addEdge("A", "B", 4);
        g.addEdge("A", "C", 3);
        g.addEdge("B", "C", 2);
        g.addEdge("C", "D", 5);

        System.out.println(g);
        System.out.println("Nodes: " + g.getNodes());
        System.out.println("Edges: " + g.getEdges());
    }
}