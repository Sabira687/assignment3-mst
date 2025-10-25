package mst;

import java.util.*;

public class Kruskal {

    public static MSTResult run(Graph graph) {
        long startTime = System.nanoTime();
        int[] operations = {0};

        List<Edge> edges = new ArrayList<>(graph.getEdges());
        Collections.sort(edges);
        List<Edge> mstEdges = new ArrayList<>();

        Map<String, String> parent = new HashMap<>();

        for (String node : graph.getNodes()) {
            parent.put(node, node);
        }

        java.util.function.Function<String, String> find = new java.util.function.Function<>() {
            @Override
            public String apply(String node) {
                operations[0]++;
                if (!parent.get(node).equals(node))
                    parent.put(node, this.apply(parent.get(node)));
                return parent.get(node);
            }
        };

        java.util.function.BiConsumer<String, String> union = (a, b) -> {
            operations[0]++;
            String rootA = find.apply(a);
            String rootB = find.apply(b);
            if (!rootA.equals(rootB)) parent.put(rootA, rootB);
        };

        int totalCost = 0;

        for (Edge e : edges) {
            String rootU = find.apply(e.getFrom());
            String rootV = find.apply(e.getTo());

            if (!rootU.equals(rootV)) {
                mstEdges.add(e);
                totalCost += e.getWeight();
                union.accept(rootU, rootV);
            }
        }

        long endTime = System.nanoTime();
        double execTimeMs = (endTime - startTime) / 1_000_000.0;

        return new MSTResult(graph.getId(), mstEdges, totalCost, operations[0], execTimeMs);
    }
}