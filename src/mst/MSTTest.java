package mst;

import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MSTTest {

    public static void main(String[] args) {
        String inputPath = "data/input.json";
        List<Graph> graphs = new ArrayList<>();
        boolean allPassed = true;

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
                MSTResult prim = Prim.run(g);
                MSTResult kruskal = Kruskal.run(g);

                boolean costEqual = prim.getTotalCost() == kruskal.getTotalCost();
                boolean edgeCountOk = prim.getMstEdges().size() == g.getNodeCount() - 1;
                boolean notEmpty = !prim.getMstEdges().isEmpty() && !kruskal.getMstEdges().isEmpty();

                if (costEqual && edgeCountOk && notEmpty) {
                    System.out.printf("Graph %d passed | Cost = %d%n", g.getId(), prim.getTotalCost());
                } else {
                    allPassed = false;
                    System.out.printf("Graph %d failed:%n", g.getId());
                    if (!costEqual) System.out.println("   → total_cost mismatch");
                    if (!edgeCountOk) System.out.println("   → wrong edge count");
                    if (!notEmpty) System.out.println("   → empty MST result");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            allPassed = false;
        }

        if (allPassed) {
            System.out.println("\nAll tests passed successfully!");
        } else {
            System.out.println("\nSome tests failed");
        }
    }
}