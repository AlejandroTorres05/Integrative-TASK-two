package dataStructures;

public enum GraphType {
    // Bidirectional, multi-edge, loops
    SIMPLE_GRAPH(new boolean[]{true, false, false}),
    MULTI_GRAPH(new boolean[]{true, true, false}),
    PSEUDO_GRAPH(new boolean[]{true, true, true}),
    DIRECTED_GRAPH(new boolean[]{false, false, true}),
    DIRECTED_MULTI_GRAPH(new boolean[]{false, true, true});

    final boolean[] permissions;

    GraphType (boolean[] permissions){
        this.permissions = permissions;
    }
}
