package dataStructures;

public class EdgeAM<V> {
    private final VertexAM<V> source;
    private final VertexAM<V> destination;
    private int weight;

    public EdgeAM(VertexAM<V> source, VertexAM<V> destination){
        this(source, destination, 0);
    }

    public EdgeAM(VertexAM<V> source, VertexAM<V> destination, int weight){
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public VertexAM<V> getSource() {
        return source;
    }

    public VertexAM<V> getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}

