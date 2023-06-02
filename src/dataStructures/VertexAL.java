package dataStructures;

import java.util.ArrayList;
import java.util.List;

public class VertexAL<V> {
    private final V vertex;
    private final List<EdgeAL<V>> edges;

    private boolean visited;
    private Integer distance;
    private VertexAL<V> previous;

    private int iTime;
    private int fTime;

    public VertexAL(V vertex){
        this.vertex = vertex;
        edges = new ArrayList<>();
    }

    public V getVertex() {
        return vertex;
    }

    public boolean isConnected(V destinationVertex){
        for (EdgeAL<V> edge : edges){
            if (destinationVertex.equals(edge.getDestination().getVertex()))
                return true;
        }

        return false;
    }

    public Integer getEdgeWeight(V destinationVertex) {
        for (EdgeAL<V> edge : edges){
            if (destinationVertex.equals(edge.getDestination().getVertex()))
                return edge.getWeight();
        }

        return null;
    }

    public EdgeAL<V> getEdge(V destination){
        for (EdgeAL<V> edge : edges){
            if (edge.getDestination().getVertex().equals(destination))
                return edge;
        }

        return null;
    }

    public List<EdgeAL<V>> getEdges() {
        return edges;
    }

    public void addEdge(EdgeAL<V> edge){
        edges.add(edge);
    }

    public void removeEdge(EdgeAL<V> edge){
        edges.remove(edge);
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public VertexAL<V> getPrevious() {
        return previous;
    }

    public void setPrevious(VertexAL<V> previous) {
        this.previous = previous;
    }

    public int getITime() {
        return iTime;
    }

    public void setITime(int iTime) {
        this.iTime = iTime;
    }

    public int getFTime() {
        return fTime;
    }

    public void setFTime(int fTime) {
        this.fTime = fTime;
    }

    @Override
    public String toString() {
        return vertex.toString();
    }
}
