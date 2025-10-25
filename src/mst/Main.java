package mst;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "data/input.json";
        List<Graph> graphs = new ArrayList<>();

        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
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
                    String from = e.getString("from");
                    String to = e.getString("to");
                    int weight = e.getInt("weight");
                    g.addEdge(from, to, weight);
                }

                graphs.add(g);
            }

            for (Graph g : graphs) {
                System.out.println(g);
                System.out.println("Nodes: " + g.getNodes());
                System.out.println("Edges: " + g.getEdges());
                System.out.println("----");
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Graph g : graphs) {
            System.out.println("\nRunning Prim on Graph " + g.getId());
            MSTResult result = Prim.run(g);
            System.out.println(result);
            System.out.println("MST Edges: " + result.getMstEdges());
        }
    }
}