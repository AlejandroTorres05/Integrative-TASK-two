package exceptions;

public class VertexDoesntExistException extends RuntimeException{
    public VertexDoesntExistException(String reason){
        super(reason);
    }

    public VertexDoesntExistException (){
        super("Vertex doesnt exist at the graph");
    }
}

