package dataStructures;

import exceptions.VertexDoesntExistException;

import java.util.ArrayList;

public interface GraphInterface <E>{

    Vertex<E> getVertex (E goal) throws VertexDoesntExistException;
    void addVertex (E element);
    void deleteVertex (E element);
    ArrayList<Vertex<E>> getAdjacency (E goal) throws VertexDoesntExistException;
    void createEdge (E source, E destination);
    void deleteEdge (E source, E destination) throws VertexDoesntExistException;
    void bfs (Vertex<E> start);
    void dfs ();
    void dfsVisit(Vertex<E> u);
}
