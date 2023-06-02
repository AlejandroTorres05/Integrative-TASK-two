package dataStructures;

import exceptions.VertexNotFoundException;

import java.util.*;

public class GraphAL<V> implements GraphInterfaceAL<V> {

    private final Map<V, VertexAL<V>> vertices;
    private final List<EdgeAL<V>> edges;

    private final boolean bidirectional;

    private static int time;

    public GraphAL(boolean bidirectional){
        vertices = new HashMap<>();
        edges = new ArrayList<>();

        this.bidirectional = bidirectional;
    }

    private VertexAL<V> createVertex(V vertex){
        if (vertices.containsKey(vertex))
            return vertices.get(vertex);

        return new VertexAL<>(vertex);
    }

    @Override
    public void addVertex(V vertex) {
        if (!vertices.containsKey(vertex)){
            vertices.put(vertex, createVertex(vertex));
        }
    }

    private EdgeAL<V> createEdge(VertexAL<V> source, VertexAL<V> destination, int weight){
        return new EdgeAL<>(source, destination, weight);
    }

    @Override
    public void addEdge(V source, V destination) {
        addEdge(source, destination, 0);
    }

    @Override
    public void addEdge(V source, V destination, int weight){
        addVertex(source);
        addVertex(destination);

        VertexAL<V> sourceObj = createVertex(source);
        VertexAL<V> destinationObj = createVertex(destination);

        EdgeAL<V> sourceToDestination = createEdge(sourceObj, destinationObj, weight);
        EdgeAL<V> destinationToSource = createEdge(destinationObj, sourceObj, weight);

        vertices.get(source).addEdge(sourceToDestination);
        edges.add(sourceToDestination);

        if (bidirectional && !source.equals(destination)){
            vertices.get(destination).addEdge(destinationToSource);
            edges.add(destinationToSource);
        }
    }

    @Override
    public void deleteVertex(V vertex) {
        if (!hasVertex(vertex))
            return;

        Iterator<EdgeAL<V>> edgeIterator = edges.iterator();

        while(edgeIterator.hasNext()){
            EdgeAL<V> edge = edgeIterator.next();
            V sourceVertex = edge.getSource().getVertex();
            V destinationVertex = edge.getDestination().getVertex();

            if (destinationVertex.equals(vertex)){
                edge.getDestination().removeEdge(edge);
                edgeIterator.remove();
            }

            if (sourceVertex.equals(vertex))
                edgeIterator.remove();
        }

        vertices.remove(vertex);
    }

    @Override
    public void deleteEdge(V source, V destination) {
        if (!hasVertex(source) || !hasVertex(destination) || !hasEdge(source, destination))
            return;

        EdgeAL<V> edgeToDelete = vertices.get(source).getEdge(destination);
        vertices.get(source).removeEdge(edgeToDelete);

        if (bidirectional){
            edgeToDelete = vertices.get(destination).getEdge(source);
            vertices.get(destination).removeEdge(edgeToDelete);
        }

        edges.remove(edgeToDelete);
    }

    @Override
    public int getVertexCount() {
        return vertices.size();
    }

    @Override
    public int getEdgesCount() {
        if (bidirectional)
            return edges.size()/2;

        return edges.size();
    }

    @Override
    public boolean hasVertex(V vertex) {
        return vertices.containsKey(vertex);
    }

    @Override
    public boolean hasEdge(V source, V destination) {
        if (!hasVertex(source) || !hasVertex(destination))
            return false;

        return vertices.get(source).isConnected(destination);
    }

    @Override
    public VertexAL<V> getVertex(V vertex) throws VertexNotFoundException {
        if (!hasVertex(vertex))
            throw new VertexNotFoundException(vertex.toString());

        return vertices.get(vertex);
    }

    @Override
    public Integer getEdgeWeight(V source, V destination) throws VertexNotFoundException {
        if (!hasVertex(destination))
            throw new VertexNotFoundException(destination.toString());

        return getVertex(source).getEdgeWeight(destination);
    }

    @Override
    public String toString() {
        StringBuilder adjacencyList = new StringBuilder();

        for (V vertex : vertices.keySet()){
            adjacencyList.append(vertex.toString()).append(" :");

            for (EdgeAL<V> edge : vertices.get(vertex).getEdges()){
                adjacencyList.append(" ").append(edge.getDestination().toString());
            }

            adjacencyList.append("\n");
        }

        return adjacencyList.toString();
    }

    @Override
    public void bfs(V vertex){
        VertexAL<V> startVertex = vertices.get(vertex);

        for (V vertexIterator : vertices.keySet()){
            vertices.get(vertexIterator).setVisited(false);
            vertices.get(vertexIterator).setDistance(Integer.MAX_VALUE);
            vertices.get(vertexIterator).setPrevious(null);
        }

        startVertex.setVisited(true);
        startVertex.setDistance(0);
        startVertex.setPrevious(null);

        Queue<VertexAL<V>> queue = new LinkedList<>();
        queue.add(startVertex);

        while(!queue.isEmpty()){
            VertexAL<V> visitedVertex = queue.remove();

            for (EdgeAL<V> edge : visitedVertex.getEdges()){
                VertexAL<V> adjacentVertex = edge.getDestination();

                if (!adjacentVertex.isVisited()){;
                    adjacentVertex.setVisited(true);
                    adjacentVertex.setDistance(visitedVertex.getDistance()+1);
                    adjacentVertex.setPrevious(visitedVertex);

                    queue.add(adjacentVertex);
                }
            }

            visitedVertex.setVisited(true);
        }
    }

    @Override
    public void dfs(){
        for (V u : vertices.keySet()){
            vertices.get(u).setVisited(false);
            vertices.get(u).setPrevious(null);
        }

        time = 0;

        for (V u : vertices.keySet()){
            if (!vertices.get(u).isVisited())
                dfsVisit(vertices.get(u));
        }
    }

    private void dfsVisit(VertexAL<V> u) {
        time += 1;
        u.setITime(time);
        u.setVisited(true);

        for (EdgeAL<V> edge : u.getEdges()){
            VertexAL<V> v = edge.getDestination();

            if (!v.isVisited()){
                v.setPrevious(u);
                dfsVisit(v);
            }
        }

        u.setVisited(true);
        time +=1;
        u.setFTime(time);
    }

    @Override
    public void dijkstra(V vertexKey){
        VertexAL<V> source = vertices.get(vertexKey);

        source.setDistance(0);

        PriorityQueue<VertexAL<V>> Q = new PriorityQueue<>(Comparator.comparingInt(VertexAL::getDistance));

        for (V vertexIterator : vertices.keySet()){
            VertexAL<V> vertex = vertices.get(vertexIterator);

            if (!vertex.equals(source))
                vertex.setDistance(Integer.MAX_VALUE);

            vertex.setPrevious(null);
            Q.add(vertex);
        }

        while (!Q.isEmpty()){
            VertexAL<V> u = Q.poll();

            for (EdgeAL<V> edge : u.getEdges()){
                VertexAL<V> v = edge.getDestination();

                int alt = Integer.MAX_VALUE;
                if (u.getDistance() != Integer.MAX_VALUE)
                    alt = u.getDistance() + edge.getWeight();

                if (alt < v.getDistance()){
                    Q.remove(v);

                    v.setDistance(alt);
                    v.setPrevious(u);

                    Q.add(v);
                }

            }
        }


    }

    @Override
    public Integer[][] floydWarshall(){
        Integer[][] dist = new Integer[vertices.size()][vertices.size()];

        int index1 = 0;
        int index2 = 0;

        for (V vertexIterator1 : vertices.keySet()){
            index2 = 0;
            for (V vertexIterator2 : vertices.keySet()){
                if (hasEdge(vertexIterator1, vertexIterator2)){
                    EdgeAL<V> tempEdge = vertices.get(vertexIterator1).getEdge(vertexIterator2);
                    dist[index1][index2] = tempEdge.getWeight();
                } else {
                    dist[index1][index2] = Integer.MAX_VALUE;
                }
                index2++;
            }
            System.out.println();
            index1++;
        }

        for (int i = 0; i < vertices.size(); i++)
            dist[i][i] = 0;

        for (int k = 0; k < vertices.size(); k++){
            for (int i = 0; i < vertices.size(); i++){
                for (int j = 0; j < vertices.size(); j++){

                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE){
                        if (dist[i][j] > dist[i][k] + dist[k][j])
                            dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        return dist;
    }

    @Override
    public List<String> prim(V vertex){
        for (V vertexKey : vertices.keySet()){
            VertexAL<V> vertexIterator = vertices.get(vertexKey);
            vertexIterator.setDistance(Integer.MAX_VALUE);
            vertexIterator.setPrevious(null);
            vertexIterator.setVisited(false);
        }

        vertices.get(vertex).setDistance(0);
        vertices.get(vertex).setPrevious(null);

        PriorityQueue<VertexAL<V>> Q = new PriorityQueue<>(
                Comparator.comparingInt(VertexAL::getDistance));

        for (V vertexKey : vertices.keySet())
            Q.add(vertices.get(vertexKey));

        while(!Q.isEmpty()){
            VertexAL<V> source = Q.poll();

            for (EdgeAL<V> edge : source.getEdges()){
                VertexAL<V> destination = edge.getDestination();

                if (!destination.isVisited() && edge.getWeight() < destination.getDistance()){
                    Q.remove(destination);

                    destination.setDistance(edge.getWeight());
                    destination.setPrevious(source);

                    Q.add(destination);
                }
            }

            source.setVisited(true);
        }

        return primResult();
    }

    private List<String> primResult(){
        List<String> minimumSpanningTree = new ArrayList<>();

        for (V vertexKey : vertices.keySet()){
            VertexAL<V> vertex = vertices.get(vertexKey);

            if (vertex.getPrevious() != null){
                String minimumPath = vertex.getPrevious().getVertex().toString() +
                        ", " + vertex.getVertex().toString() +
                        ", Cost: " + vertex.getDistance();
                minimumSpanningTree.add(minimumPath);
            }
        }

        return minimumSpanningTree;
    }

    @Override
    public List<String> kruskal(){
        DisjointSet<V> disjointSet = new DisjointSet<>();
        for (VertexAL<V> vertex : vertices.values()) {
            disjointSet.makeSet(vertex.getVertex());
        }

        List<EdgeAL<V>> sortedEdges = new ArrayList<>(edges);
        sortedEdges.sort(Comparator.comparing(EdgeAL::getWeight));

        List<String> minimumSpanningTree = new ArrayList<>();

        for (EdgeAL<V> edge : sortedEdges) {
            V source = edge.getSource().getVertex();
            V destination = edge.getDestination().getVertex();

            if (disjointSet.find(source) != disjointSet.find(destination)) {
                minimumSpanningTree.add(source +", " + destination + ", Cost: " + edge.getWeight());
                disjointSet.union(source, destination);
            }
        }

        return minimumSpanningTree;
    }

}
