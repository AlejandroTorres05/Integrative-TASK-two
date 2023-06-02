package dataStructures;

import exceptions.VertexNotFoundException;

import java.util.List;

public interface GraphInterfaceAM<V> {
    void addVertex(V vertex);
    void addEdge(V source, V destination);
    void addEdge(V source, V destination, int weight);
    void deleteVertex(V vertex);
    void deleteEdge(V source, V destination);
    int getVertexCount();
    int getEdgesCount();
    boolean hasVertex(V vertex);
    boolean hasEdge(V source, V destination);
    VertexAM<V> getVertex(V vertex) throws VertexNotFoundException;
    Integer getEdgeWeight (V source, V destination) throws VertexNotFoundException;
    void bfs(V vertex);
    void dfs();
    void dijkstra(V vertex);
    Integer[][] floydWarshall();
    List<String> prim(V vertex);
    List<String> kruskal();
}