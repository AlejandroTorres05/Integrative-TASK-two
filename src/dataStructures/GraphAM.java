package dataStructures;

import exceptions.VertexNotFoundException;

import java.util.*;

public class GraphAM<V> implements GraphInterfaceAM<V>{

    private Map<V, VertexAM<V>> vertices;
    private List<List<Integer>> adjacencyMatrix;

    private final boolean bidirectional;
    private static int time;

    public GraphAM(boolean bidirectional){
        vertices = new HashMap<>();
        adjacencyMatrix = new ArrayList<>();

        this.bidirectional = bidirectional;
    }


    private VertexAM<V> createVertex(V vertex){
        if (vertices.containsKey(vertex))
            return vertices.get(vertex);

        return new VertexAM<>(vertex, adjacencyMatrix.size());
    }


    @Override
    public void addVertex(V vertex) {
        if (vertices.containsKey(vertex))
            return;

        vertices.put(vertex, createVertex(vertex));

        for (List<Integer> column : adjacencyMatrix){
            column.add(Integer.MAX_VALUE);
        }

        List<Integer> newVertexList = new ArrayList<>(
                Collections.nCopies(adjacencyMatrix.size() + 1, Integer.MAX_VALUE));
        adjacencyMatrix.add(newVertexList);
    }

    @Override
    public void addEdge(V source, V destination) {
        addEdge(source, destination, 0);
    }

    @Override
    public void addEdge(V source, V destination, int weight) {
        addVertex(source);
        addVertex(destination);

        int sourceIndex = vertices.get(source).getIndex();
        int destIndex = vertices.get(destination).getIndex();

        adjacencyMatrix.get(sourceIndex).set(destIndex, weight);

        if (bidirectional && !source.equals(destination))
            adjacencyMatrix.get(destIndex).set(sourceIndex,weight);
    }

    @Override
    public void deleteVertex(V vertex) {
        if (!hasVertex(vertex))
            return;

        int vertexIndex = vertices.get(vertex).getIndex();

        for (List<Integer> column: adjacencyMatrix){
            column.remove(vertexIndex);
        }

        adjacencyMatrix.remove(vertexIndex);
        vertices.remove(vertex);

        int actualIndex = -1;
        for (V vertexIterator : vertices.keySet()){
            if (vertices.get(vertexIterator).getIndex() != actualIndex+1)
                vertices.get(vertexIterator).reduceIndex();
            actualIndex++;
        }
    }

    @Override
    public void deleteEdge(V source, V destination) {
        if (!hasVertex(source) || !hasVertex(destination))
            return;

        int sourceIndex = vertices.get(source).getIndex();
        int destIndex = vertices.get(destination).getIndex();

        adjacencyMatrix.get(sourceIndex).set(destIndex, Integer.MAX_VALUE);

        if (bidirectional)
            adjacencyMatrix.get(destIndex).set(sourceIndex, Integer.MAX_VALUE);
    }

    @Override
    public int getVertexCount() {
        return vertices.size();
    }

    @Override
    public int getEdgesCount() {
        int edgesCount = 0;

        for (List<Integer> column : adjacencyMatrix){
            for (Integer row : column){
                if (row != Integer.MAX_VALUE)
                    edgesCount++;
            }
        }

        if (bidirectional)
            edgesCount = edgesCount/2;

        return edgesCount;
    }

    @Override
    public boolean hasVertex(V vertex) {
        return vertices.containsKey(vertex);
    }

    @Override
    public boolean hasEdge(V source, V destination) {
        if (!hasVertex(source) || !hasVertex(destination))
            return false;

        int sourceIndex = vertices.get(source).getIndex();
        int destIndex = vertices.get(destination).getIndex();

        return adjacencyMatrix.get(sourceIndex).get(destIndex) != Integer.MAX_VALUE;
    }

    @Override
    public VertexAM<V> getVertex(V vertex) throws VertexNotFoundException {
        if (!hasVertex(vertex))
            throw new VertexNotFoundException(vertex.toString());

        return vertices.get(vertex);
    }

    @Override
    public Integer getEdgeWeight(V source, V destination) throws VertexNotFoundException {
        if (!hasVertex(source))
            throw new VertexNotFoundException(source.toString());
        if (!hasVertex(destination))
            throw new VertexNotFoundException(destination.toString());

        int sourceIndex = vertices.get(source).getIndex();
        int destIndex = vertices.get(destination).getIndex();

        return adjacencyMatrix.get(sourceIndex).get(destIndex);
    }

    private VertexAM<V> searchVertex(int index){
        for (V vertexKey : vertices.keySet()){
            if (vertices.get(vertexKey).getIndex() == index)
                return vertices.get(vertexKey);
        }

        return null;
    }

    @Override
    public void bfs(V vertex) {
        VertexAM<V> startVertex = vertices.get(vertex);

        for (V vertexIterator : vertices.keySet()){
            vertices.get(vertexIterator).setVisited(false);
            vertices.get(vertexIterator).setDistance(Integer.MAX_VALUE);
            vertices.get(vertexIterator).setPrevious(null);
        }

        startVertex.setVisited(true);
        startVertex.setDistance(0);
        startVertex.setPrevious(null);

        Queue<VertexAM<V>> queue = new LinkedList<>();
        queue.add(startVertex);

        while(!queue.isEmpty()){
            VertexAM<V> visitedVertex = queue.remove();

            for (int i = 0; i < adjacencyMatrix.size(); i++){
                int edge = adjacencyMatrix.get(visitedVertex.getIndex()).get(i);

                if (edge != Integer.MAX_VALUE){
                    VertexAM<V> adjacentVertex = searchVertex(i);

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
    }

    @Override
    public void dfs() {
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

    private void dfsVisit(VertexAM<V> u) {
        time += 1;
        u.setITime(time);
        u.setVisited(true);

        for (int i = 0; i < adjacencyMatrix.size(); i++) {
            int edge = adjacencyMatrix.get(u.getIndex()).get(i);
            if (edge != Integer.MAX_VALUE){
                VertexAM<V> v = searchVertex(i);

                if (!v.isVisited()){
                    v.setPrevious(u);
                    dfsVisit(v);
                }

            }
        }
        u.setVisited(true);
        time++;
        u.setFTime(time);
    }

    @Override
    public void dijkstra(V vertexKey) {
        VertexAM<V> source = vertices.get(vertexKey);

        source.setDistance(0);

        PriorityQueue<VertexAM<V>> Q = new PriorityQueue<>(Comparator.comparingInt(VertexAM::getDistance));

        for (V vertexIterator : vertices.keySet()){
            VertexAM<V> vertex = vertices.get(vertexIterator);

            if (!vertex.equals(source))
                vertex.setDistance(Integer.MAX_VALUE);

            vertex.setPrevious(null);
            Q.add(vertex);
        }

        while (!Q.isEmpty()) {
            VertexAM<V> u = Q.poll();

            // por cada vertice de u
            for (int i = 0; i < adjacencyMatrix.size(); i++){
                int edge = adjacencyMatrix.get(u.getIndex()).get(i);

                if (edge != Integer.MAX_VALUE){
                    VertexAM<V> v = searchVertex(i);

                    int alt = Integer.MAX_VALUE;
                    if (u.getDistance() != Integer.MAX_VALUE)
                        alt = u.getDistance() + edge;

                    if (alt < v.getDistance()){
                        Q.remove(v);

                        v.setDistance(alt);
                        v.setPrevious(u);

                        Q.add(v);
                    }
                }
            }

        }
    }

    @Override
    public Integer[][] floydWarshall() {
        Integer[][] dist = new Integer[vertices.size()][vertices.size()];

        int index1 = 0;
        int index2 = 0;

        for (V vertexIterator1 : vertices.keySet()){
            index2 = 0;

            for (V vertexIterator2 : vertices.keySet()){
                if (hasEdge(vertexIterator1, vertexIterator2)){
                    Integer weight = adjacencyMatrix.get(
                            vertices.get(vertexIterator1).getIndex()).get(vertices.get(vertexIterator2).getIndex());
                    dist[index1][index2] = weight;
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
    public List<String> prim(V vertex) {
        for (V vertexKey : vertices.keySet()){
            VertexAM<V> vertexIterator = vertices.get(vertexKey);
            vertexIterator.setDistance(Integer.MAX_VALUE);
            vertexIterator.setPrevious(null);
            vertexIterator.setVisited(false);
        }

        vertices.get(vertex).setDistance(0);
        vertices.get(vertex).setPrevious(null);

        PriorityQueue<VertexAM<V>> Q = new PriorityQueue<>(
                Comparator.comparingInt(VertexAM::getDistance));

        for (V vertexKey : vertices.keySet())
            Q.add(vertices.get(vertexKey));

        while(!Q.isEmpty()){
            VertexAM<V> source = Q.poll();

            for (int i = 0; i < adjacencyMatrix.size(); i++){
                VertexAM<V> destination = searchVertex(i);
                Integer edgeWeight = adjacencyMatrix.get(source.getIndex()).get(destination.getIndex());

                if (edgeWeight != Integer.MAX_VALUE) {

                    if (!destination.isVisited() && edgeWeight < destination.getDistance()){
                        Q.remove(destination);

                        destination.setDistance(edgeWeight);
                        destination.setPrevious(source);

                        Q.add(destination);
                    }

                }

            }

            source.setVisited(true);
        }

        return primResult();
    }

    private List<String> primResult(){
        List<String> minimumSpanningTree = new ArrayList<>();

        for (V vertexKey : vertices.keySet()){
            VertexAM<V> vertex = vertices.get(vertexKey);

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
    public List<String> kruskal() {
        DisjointSet<V> disjointSet = new DisjointSet<>();
        for (VertexAM<V> vertex : vertices.values()) {
            disjointSet.makeSet(vertex.getVertex());
        }

        List<EdgeAM<V>> sortedEdges = new ArrayList<>();

        for (int i = 0; i < adjacencyMatrix.size(); i++){
            for (int j = 0; j < adjacencyMatrix.size(); j++){
                Integer edgeWeight = adjacencyMatrix.get(i).get(j);

                if (edgeWeight != Integer.MAX_VALUE) {
                    EdgeAM<V> edge = new EdgeAM<>(searchVertex(i), searchVertex(j), edgeWeight);
                    sortedEdges.add(edge);
                }

            }
        }

        sortedEdges.sort(Comparator.comparing(EdgeAM::getWeight));

        List<String> minimumSpanningTree = new ArrayList<>();

        for (EdgeAM<V> edge : sortedEdges) {
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
