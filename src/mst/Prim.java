package mst;

import java.util.*;
import java.time.Instant;
import java.time.Duration;

public class Prim {

    public static MSTResult run(Graph graph) {
        long startTime = System.nanoTime();
        int operations = 0;

        List<String> nodes = graph.getNodes();
        List<Edge> edges = graph.getEdges();
        List<Edge> mstEdges = new ArrayList<>();

        if (nodes.isEmpty()) {
            return new MSTResult(graph.getId(), mstEdges, 0, 0, 0);
        }

        Set<String> visited = new HashSet<>();
        visited.add(nodes.get(0));

        int totalCost = 0;

        while (visited.size() < nodes.size()) {
            Edge minEdge = null;
            int minWeight = Integer.MAX_VALUE;

            for (Edge e : edges) {
                operations++;
                boolean oneIn = visited.contains(e.getFrom());
                boolean oneOut = visited.contains(e.getTo());

                if (oneIn ^ oneOut) {
                    if (e.getWeight() < minWeight) {
                        minWeight = e.getWeight();
                        minEdge = e;
                    }
                }
            }

            if (minEdge == null) break;

            mstEdges.add(minEdge);
            totalCost += minEdge.getWeight();
            visited.add(minEdge.getFrom());
            visited.add(minEdge.getTo());
        }

        long endTime = System.nanoTime();
        double execTimeMs = (endTime - startTime) / 1_000_000.0;

        return new MSTResult(graph.getId(), mstEdges, totalCost, operations, execTimeMs);
    }
}