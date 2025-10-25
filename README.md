# Assignment 3: Optimization of a City Transportation Network (MST)

## Objective
The goal of this project is to **optimize a city’s transportation network** by determining the **minimum set of roads** connecting all city districts with the lowest total construction cost.  
The task involves implementing and comparing **Prim’s** and **Kruskal’s** algorithms for finding the **Minimum Spanning Tree (MST)**.

---

## Task Description
The transportation network is modeled as a **weighted undirected graph**, where:
- **Vertices** represent city districts
- **Edges** represent potential roads
- **Weights** represent road construction costs

The requirements include:
1. Reading input data from a JSON file.
2. Implementing both **Prim’s** and **Kruskal’s** algorithms.
3. Recording:
    - MST edge list
    - Total MST cost
    - Number of vertices and edges
    - Operation count (comparisons, unions, etc.)
    - Execution time (ms)
4. Comparing both algorithms’ results.

---

## Input Data

| Graph ID | Graph Name   | Vertices | Edges |
|-----------|--------------|----------|-------|
| 1 | SmallGraph  | 4  | 5  |
| 2 | MediumGraph | 10 | 15 |
| 3 | LargeGraph  | 20 | 29 |

All graphs were provided in JSON format, with varying sizes and densities to test both **correctness** and **performance**.

---

## Algorithms Implementation

Both algorithms were implemented in **Java** using custom classes:
- `Graph.java` — representation of the network graph
- `Edge.java` — defines an edge between vertices
- `PrimAlgorithm.java` and `KruskalAlgorithm.java` — contain respective MST logic

Automated testing verified:
- Identical MST cost for both algorithms
- Acyclic and connected MSTs
- Correct number of edges (`V - 1`)
- Handling of disconnected graphs

---

## Results Summary

| Graph | Algorithm | MST Cost | Execution Time (ms) | Operations Count |
|--------|------------|----------|---------------------|------------------|
| **SmallGraph** | Prim | 6 | 0.034 | 15 |
|  | Kruskal | 6 | 0.913 | 25 |
| **MediumGraph** | Prim | 25 | 0.043 | 135 |
|  | Kruskal | 25 | 0.029 | 86 |
| **LargeGraph** | Prim | 69 | 0.153 | 551 |
|  | Kruskal | 69 | 0.051 | 164 |

---

## Analysis and Comparison

### Theoretical Background
- **Prim’s Algorithm**:
    - Works efficiently on dense graphs.
    - Relies on adjacency structures and priority queues.
- **Kruskal’s Algorithm**:
    - Performs better on sparse graphs.
    - Uses edge sorting and union-find operations.

### Observations in Practice
- Both algorithms produced **identical MST costs** across all datasets — confirming correctness.
- **Prim’s algorithm** showed higher operation counts for larger graphs, as it iterates through all vertices and updates priorities.
- **Kruskal’s algorithm** consistently achieved **lower execution times**, especially for larger and sparser graphs, due to efficient sorting and union-find operations.

---

## Performance Discussion

| Graph Density | More Efficient Algorithm | Reason |
|----------------|--------------------------|---------|
| Small (4–6 nodes) | **Prim** | Low overhead, simple structure |
| Medium (10 nodes) | **Kruskal** | Fewer operations and faster sorting |
| Large (20+ nodes) | **Kruskal** | Better scalability with union-find |

---

## Conclusion

- Both algorithms correctly identified the Minimum Spanning Tree for all test cases.
- **Kruskal’s algorithm** demonstrated **better overall performance**, especially for medium and large graphs.
- **Prim’s algorithm** remains a strong choice for **dense graphs or adjacency-based implementations**.

> **Final Insight:**  
> For real-world transportation networks (typically sparse), **Kruskal’s algorithm** offers superior performance and simpler scalability.

---

## References
- Cormen, Leiserson, Rivest, Stein — *Introduction to Algorithms*, 3rd Edition
- GeeksforGeeks — [Minimum Spanning Tree (Prim’s and Kruskal’s Algorithms)](https://www.geeksforgeeks.org/)

---

**Author:** Sabira Kurbankhozha  
**Course:** Optimization and Graph Algorithms  
**Date:** 25 October 2025