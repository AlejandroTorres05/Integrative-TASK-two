package dataStructures;

import exceptions.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
public class AdjacencyListGraph <E>
        implements GraphInterface<E> {

    private HashMap<E, Vertex<E>> vertices;
    private int time;
    private boolean bidirectional;
    private  boolean multiEdge;
    private boolean loops;

    public AdjacencyListGraph(int selection){
        boolean[] permissions = GraphType.values()[selection].permissions;
        this.bidirectional = permissions[0];
        this.multiEdge = permissions[1];
        this.loops = permissions[2];

        vertices = new HashMap<>();
    }

    @Override
    public Vertex<E> getVertex(E goal) throws VertexDoesntExistException {
        if (!vertices.containsKey(goal))
            throw new VertexDoesntExistException();

        return vertices.get(goal);
    }

    @Override
    public ArrayList<Vertex<E>> getAdjacency(E goal) throws VertexDoesntExistException {
        if (!vertices.containsKey(goal))
            throw new VertexDoesntExistException();

        return vertices.get(goal).getAdjacency();
    }

    @Override
    public void addVertex(E element) {
        if (vertices.containsKey(element))
            throw new RepeatedVertexException();
        this.vertices.put(element, createVertex(element));
    }

    private Vertex<E> createVertex(E element){
        return new Vertex<>(element);
    }

    @Override
    public void createEdge(E source, E destination) {

        if (!loops && source.equals(destination))
            throw new InconsistentGraphStructureException("Loops");

        if (!multiEdge && hasEdge(source, destination))
            throw new InconsistentGraphStructureException("MultiEdge");

        if (!vertices.containsKey(source))
            addVertex(source);

        if (!vertices.containsKey(destination))
            addVertex(destination);

        vertices.get(source).addToAdjacency(this.vertices.get(destination));

        if (bidirectional && !loops)
            vertices.get(destination).addToAdjacency(this.vertices.get(source));
    }

    public boolean hasEdge (E source, E destination){
        return vertices.get(source).hasEdge(vertices.get(destination));
    }

    @Override
    public void deleteEdge(E source, E destination) throws VertexDoesntExistException{
        if (!vertices.containsKey(source))
            throw new VertexDoesntExistException("The source vertex doesn't exits");
        if (!vertices.containsKey(destination))
            throw new VertexDoesntExistException("The destination vertex doesn't exist");

        vertices.get(source).deleteEdges(vertices.get(destination));
    }

    @Override
    public void deleteVertex(E element) {
        if (!vertices.containsKey(element))
            throw new VertexDoesntExistException("The following vertex isn't saved in the system");

        for (E key: vertices.keySet()){
            if (vertices.get(key).hasEdge(vertices.get(element)))
                deleteEdge(key, element);
        }

        vertices.remove(element);
    }

    @Override
    public void bfs(Vertex<E> start) {

        for (E u: vertices.keySet()){
            vertices.get(u).setVisited(false);
            vertices.get(u).setParent(null);
            vertices.get(u).setDistance(Integer.MAX_VALUE);
        }

        start.setVisited(true);
        start.setDistance(0);
        Queue<Vertex<E>> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()){
            Vertex<E> u = queue.remove();
            ArrayList<Vertex<E>> adjacency = u.getAdjacency();
            for (int i = 0; i<adjacency.size(); i++){
                if (!adjacency.get(i).isVisited()){
                    adjacency.get(i).setVisited(true);
                    adjacency.get(i).addDistance(1);
                    adjacency.get(i).setParent(u);
                    queue.add(adjacency.get(i));
                }
            }
            u.setVisited(true);
        }
    }

    @Override
    public void dfs() {
        for (E u:vertices.keySet()){
            vertices.get(u).setVisited(false);
            vertices.get(u).setParent(null);
        }
        this.time = 0;

        for (E u: vertices.keySet()){
            if (!vertices.get(u).isVisited()){
                dfsVisit(vertices.get(u));
            }
        }
    }

    @Override
    public void dfsVisit(Vertex<E> u) {
        this.time += 1;
        u.setITime(this.time);
        u.setVisited(true);

        for (Vertex<E> v : u.getAdjacency()){
            if (!v.isVisited()){
                v.setParent(u);
                dfsVisit(v);
            }
        }

        u.setVisited(true);
        time += 1;
        u.setFTime(time);
    }
}

