package mst;

import java.util.List;

public class MSTResult {
    private int graphId;
    private List<Edge> mstEdges;
    private int totalCost;
    private int operationsCount;
    private double executionTimeMs;

    public MSTResult(int graphId, List<Edge> mstEdges, int totalCost, int operationsCount, double executionTimeMs) {
        this.graphId = graphId;
        this.mstEdges = mstEdges;
        this.totalCost = totalCost;
        this.operationsCount = operationsCount;
        this.executionTimeMs = executionTimeMs;
    }

    public int getGraphId() { return graphId; }
    public List<Edge> getMstEdges() { return mstEdges; }
    public int getTotalCost() { return totalCost; }
    public int getOperationsCount() { return operationsCount; }
    public double getExecutionTimeMs() { return executionTimeMs; }

    @Override
    public String toString() {
        return String.format("Graph %d | cost=%d | edges=%d | ops=%d | time=%.2f ms",
                graphId, totalCost, mstEdges.size(), operationsCount, executionTimeMs);
    }
}