package dataStructures;

import java.util.HashMap;
import java.util.Map;

public class DisjointSet<V> {
    private Map<V, V> parent;
    private Map<V, Integer> rank;

    public DisjointSet() {
        this.parent = new HashMap<>();
        this.rank = new HashMap<>();
    }

    public void makeSet(V element) {
        parent.put(element, element);
        rank.put(element, 0);
    }

    public V find(V target) {
        if (!parent.containsKey(target))
            return null;

        if (!target.equals(parent.get(target)))
            parent.put(target, find(parent.get(target)));

        return parent.get(target);
    }

    public void union(V targetX, V targetY) {
        V setX = find(targetX);
        V setY = find(targetY);

        if (setX == null || setY == null || setX.equals(setY))
            return;

        if (rank.get(setX) < rank.get(setY))
            parent.put(setX, setY);
        else if (rank.get(setX) > rank.get(setY))
            parent.put(setY, setX);
        else {
            parent.put(setY, setX);
            rank.put(setX, rank.get(setX) + 1);
        }
    }
}