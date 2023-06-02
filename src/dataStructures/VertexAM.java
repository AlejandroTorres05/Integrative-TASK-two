package dataStructures;

public class VertexAM<V> {
    private final V vertex;
    private int index;

    private boolean visited;
    private Integer distance;
    private VertexAM<V> previous;

    private int iTime;
    private int fTime;

    public VertexAM(V vertex, int index){
        this.vertex = vertex;
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void reduceIndex() {
        index--;
    }

    public V getVertex() {
        return vertex;
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

    public VertexAM<V> getPrevious() {
        return previous;
    }

    public void setPrevious(VertexAM<V> previous) {
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