package mst;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String inputPath = "data/input.json";
        String outputPath = "data/output.json";

        List<Graph> graphs = new ArrayList<>();
        JSONArray resultsArray = new JSONArray();

        try {
            String content = new String(Files.readAllBytes(Paths.get(inputPath)));
            JSONObject json = new JSONObject(content);
            JSONArray graphArray = json.getJSONArray("graphs");

            for (int i = 0; i < graphArray.length(); i++) {
                JSONObject gObj = graphArray.getJSONObject(i);
                int id = gObj.getInt("id");
                Graph g = new Graph(id);

                JSONArray nodes = gObj.getJSONArray("nodes");
                for (int j = 0; j < nodes.length(); j++) {
                    g.addNode(nodes.getString(j));
                }

                JSONArray edges = gObj.getJSONArray("edges");
                for (int j = 0; j < edges.length(); j++) {
                    JSONObject e = edges.getJSONObject(j);
                    g.addEdge(e.getString("from"), e.getString("to"), e.getInt("weight"));
                }

                graphs.add(g);
            }

            for (Graph g : graphs) {
                MSTResult primRes = Prim.run(g);
                MSTResult kruskalRes = Kruskal.run(g);

                JSONObject graphResult = new JSONObject();
                graphResult.put("graph_id", g.getId());

                JSONObject stats = new JSONObject();
                stats.put("vertices", g.getNodeCount());
                stats.put("edges", g.getEdgeCount());
                graphResult.put("input_stats", stats);

                JSONObject prim = new JSONObject();
                prim.put("mst_edges", toJsonEdges(primRes.getMstEdges()));
                prim.put("total_cost", primRes.getTotalCost());
                prim.put("operations_count", primRes.getOperationsCount());
                prim.put("execution_time_ms", primRes.getExecutionTimeMs());
                graphResult.put("prim", prim);

                JSONObject kruskal = new JSONObject();
                kruskal.put("mst_edges", toJsonEdges(kruskalRes.getMstEdges()));
                kruskal.put("total_cost", kruskalRes.getTotalCost());
                kruskal.put("operations_count", kruskalRes.getOperationsCount());
                kruskal.put("execution_time_ms", kruskalRes.getExecutionTimeMs());
                graphResult.put("kruskal", kruskal);

                resultsArray.put(graphResult);
            }

            JSONObject output = new JSONObject();
            output.put("results", resultsArray);

            try (FileWriter file = new FileWriter(outputPath)) {
                file.write(output.toString(2));
            }

            System.out.println("Saved in " + outputPath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static JSONArray toJsonEdges(List<Edge> edges) {
        JSONArray arr = new JSONArray();
        for (Edge e : edges) {
            JSONObject obj = new JSONObject();
            obj.put("from", e.getFrom());
            obj.put("to", e.getTo());
            obj.put("weight", e.getWeight());
            arr.put(obj);
        }
        return arr;
    }
}