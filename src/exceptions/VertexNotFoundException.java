package exceptions;

public class VertexNotFoundException extends Exception{
    public VertexNotFoundException(String vertexName){
        super("The vertex " + vertexName + " does not exist in the graph.");
    }
}