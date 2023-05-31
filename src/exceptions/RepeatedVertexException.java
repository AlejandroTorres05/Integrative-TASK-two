package exceptions;

public class RepeatedVertexException extends RuntimeException{

    public RepeatedVertexException (String reason){
        super(reason);
    }

    public RepeatedVertexException (){
        super("The vertex is registered in the system yet");
    }
}

