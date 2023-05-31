package exceptions;

public class InconsistentGraphStructureException extends RuntimeException {

    public InconsistentGraphStructureException (String reason){
        super(reason);
    }

    public InconsistentGraphStructureException (){
        super("Inconsistent structure");
    }
}
