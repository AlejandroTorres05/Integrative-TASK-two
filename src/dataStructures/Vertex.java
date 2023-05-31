package dataStructures;

import java.util.HashMap;

public class Vertex<E>{

    private E data;
    private HashMap<Vertex<E>, Integer> adjacency;
    private Vertex<E> previous;
    private Vertex<E> parent;
    private boolean visited;
    private int distance;
    private int iTime;
    private int fTime;

    public Vertex(E data) {
        this.data = data;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public HashMap<Vertex<E>, Integer> getAdjacency() {
        return adjacency;
    }

    public void addToAdjacency (Vertex<E> vertex, int weight){
        this.adjacency.put(vertex,weight);
    }

    public Vertex<E> getParent() {
        return parent;
    }

    public void setParent(Vertex<E> parent) {
        this.parent = parent;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
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

    public Vertex<E> getPrevious() {
        return previous;
    }

    public void setPrevious(Vertex<E> previous) {
        this.previous = previous;
    }

    public void addDistance(int distance){
        this.distance += distance;
    }

    public boolean hasEdge (Vertex<E> destination){
        return this.adjacency.containsKey(destination);
    }

    public void deleteEdges (Vertex<E> destination){
        for (Vertex<E> key : this.adjacency.keySet()){
            //
        }
    }



    @Override
    public boolean equals(Object obj) {
        Vertex<E> o = (Vertex<E>)obj;
        return this.data.equals(o.data);
    }
}

