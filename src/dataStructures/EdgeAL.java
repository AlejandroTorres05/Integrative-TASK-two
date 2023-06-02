package dataStructures;

public class EdgeAL<V> {
    private final VertexAL<V> source;
    private final VertexAL<V> destination;
    private int weight;

    public EdgeAL(VertexAL<V> source, VertexAL<V> destination){
        this(source, destination, 0);
    }

    public EdgeAL(VertexAL<V> source, VertexAL<V> destination, int weight){
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public VertexAL<V> getSource() {
        return source;
    }

    public VertexAL<V> getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}