package dataStructures;

import exceptions.VertexNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTestAL {
    GraphAL<Integer> integerGraph;
    GraphAL<String> stringGraph;

    public void setUpBidirectionalGraph1() {
        integerGraph = new GraphAL<>(true);
    }

    public void setUpBidirectionalGraph2() {
        integerGraph = new GraphAL<>(true);

        for (int i = 1; i <= 10; i++){
            integerGraph.addVertex(i);
        }

        integerGraph.addEdge(1,2,3);
        integerGraph.addEdge(1,3,8);
        integerGraph.addEdge(2,3,2);
        integerGraph.addEdge(2,8,5);
        integerGraph.addEdge(2,9,9);
        integerGraph.addEdge(3,8,1);
        integerGraph.addEdge(4,5,6);
        integerGraph.addEdge(4,7,7);
        integerGraph.addEdge(4,9,4);
        integerGraph.addEdge(4,10,10);
        integerGraph.addEdge(5,6,2);
        integerGraph.addEdge(5,7,6);
        integerGraph.addEdge(6,7,8);
        integerGraph.addEdge(7,10,3);
        integerGraph.addEdge(8,9,9);
        integerGraph.addEdge(8,10,5);
    }

    public void setUpDirectionalGraph1() {
        integerGraph = new GraphAL<>(false);
    }

    public void setUpDirectionalGraph2() {
        integerGraph = new GraphAL<>(false);

        for (int i = 1; i <= 9; i++){
            integerGraph.addVertex(i);
        }

        integerGraph.addEdge(1,2,8);
        integerGraph.addEdge(1,3,3);
        integerGraph.addEdge(1,6,13);
        integerGraph.addEdge(2,3,2);
        integerGraph.addEdge(2,4,1);
        integerGraph.addEdge(3,2,3);
        integerGraph.addEdge(3,4,9);
        integerGraph.addEdge(3,5,2);
        integerGraph.addEdge(4,5,4);
        integerGraph.addEdge(4,7,6);
        integerGraph.addEdge(4,8,2);
        integerGraph.addEdge(5,1,5);
        integerGraph.addEdge(5,4,6);
        integerGraph.addEdge(5,6,5);
        integerGraph.addEdge(5,9,4);
        integerGraph.addEdge(6,7,1);
        integerGraph.addEdge(6,9,7);
        integerGraph.addEdge(7,5,3);
        integerGraph.addEdge(7,8,4);
        integerGraph.addEdge(8,9,1);
        integerGraph.addEdge(9,7,5);
    }

    public void setUpDirectionalGraph3() {
        stringGraph = new GraphAL<>(false);

        stringGraph.addVertex("u");
        stringGraph.addVertex("v");
        stringGraph.addVertex("y");
        stringGraph.addVertex("x");
        stringGraph.addVertex("w");
        stringGraph.addVertex("z");

        stringGraph.addEdge("u", "v", 2);
        stringGraph.addEdge("v", "y", 2);
        stringGraph.addEdge("y", "x", 5);
        stringGraph.addEdge("u", "x", 3);
        stringGraph.addEdge("x", "v", 6);
        stringGraph.addEdge("w", "y", 8);
        stringGraph.addEdge("w", "z", 1);
        stringGraph.addEdge("z", "z", 4);
    }

    @Test
    public void addEdgeTest1() throws VertexNotFoundException {
        setUpBidirectionalGraph1();
        integerGraph.addVertex(2);
        integerGraph.addVertex(4);

        integerGraph.addEdge(2,4,5);

        assertTrue(integerGraph.hasEdge(2,4));
        assertTrue(integerGraph.hasEdge(4,2));

        assertEquals(5, integerGraph.getEdgeWeight(2,4));
    }

    @Test
    public void addEdgeTest2() throws VertexNotFoundException {
        setUpDirectionalGraph1();
        integerGraph.addVertex(6);

        integerGraph.addEdge(6,2);

        assertTrue(integerGraph.hasEdge(6,2));
        assertFalse(integerGraph.hasEdge(2,6));

        assertEquals(0, integerGraph.getEdgeWeight(6,2));
    }

    @Test
    public void addEdgeTest3() throws VertexNotFoundException {
        setUpDirectionalGraph1();

        integerGraph.addEdge(8,9,25);
        integerGraph.addEdge(2,9,12);

        assertTrue(integerGraph.hasEdge(8,9));
        assertTrue(integerGraph.hasEdge(2,9));
        assertFalse(integerGraph.hasEdge(9,8));
        assertFalse(integerGraph.hasEdge(9,2));

        assertEquals(25, integerGraph.getEdgeWeight(8,9));
        assertEquals(12, integerGraph.getEdgeWeight(2,9));
    }

    @Test
    public void deleteVertexTest1(){
        setUpBidirectionalGraph1();
        integerGraph.addVertex(20);

        integerGraph.deleteVertex(20);

        assertFalse(integerGraph.hasVertex(20));
        assertEquals(0, integerGraph.getVertexCount());
        assertThrows(VertexNotFoundException.class,() -> integerGraph.getVertex(20));
    }

    @Test
    public void deleteVertexTest2(){
        setUpDirectionalGraph1();
        integerGraph.addVertex(12);
        integerGraph.addVertex(8);
        integerGraph.addVertex(9);

        integerGraph.deleteVertex(10);

        assertEquals(3, integerGraph.getVertexCount());
    }

    @Test
    public void deleteVertexTest3() throws VertexNotFoundException {
        setUpDirectionalGraph1();
        integerGraph.addEdge(2,10);
        integerGraph.addEdge(2,5);
        integerGraph.addEdge(2,8);

        integerGraph.deleteVertex(2);

        assertFalse(integerGraph.hasEdge(10,2));
        assertFalse(integerGraph.hasEdge(5,2));
        assertFalse(integerGraph.hasEdge(8,2));


        assertFalse(integerGraph.hasVertex(2));
        assertEquals(0, integerGraph.getEdgesCount());
    }

    @Test
    public void deleteEdgeTest1() throws VertexNotFoundException {
        setUpBidirectionalGraph1();
        integerGraph.addEdge(2,6);
        integerGraph.addEdge(2,7);

        integerGraph.deleteEdge(2,6);

        assertFalse(integerGraph.hasEdge(2,6));
        assertFalse(integerGraph.hasEdge(6,2));
    }

    @Test
    public void deleteEdgeTest2() throws VertexNotFoundException {
        setUpDirectionalGraph1();
        integerGraph.addEdge(10,4);
        integerGraph.addEdge(4,10);

        integerGraph.deleteEdge(10,4);

        assertTrue(integerGraph.hasEdge(4,10));
        assertFalse(integerGraph.hasEdge(10,4));
    }

    @Test
    public void deleteEdgeTest3() throws VertexNotFoundException {
        setUpDirectionalGraph1();
        integerGraph.addEdge(20,10);
        integerGraph.addEdge(9,20);
        integerGraph.addEdge(5,8);

        integerGraph.deleteEdge(5,10);

        assertTrue(integerGraph.hasEdge(20,10));
        assertTrue(integerGraph.hasEdge(9,20));
        assertTrue(integerGraph.hasEdge(5,8));
        assertEquals(3, integerGraph.getEdgesCount());
    }

    @Test
    public void queriesTest1(){
        setUpDirectionalGraph1();
        integerGraph.addVertex(5);
        integerGraph.addVertex(4);
        integerGraph.addVertex(10);
        integerGraph.addVertex(2);
        integerGraph.addVertex(14);

        int vertexNumber = integerGraph.getVertexCount();
        boolean vertex5Exists = integerGraph.hasVertex(5);
        boolean vertex2Exists = integerGraph.hasVertex(2);
        boolean vertex4Exists = integerGraph.hasVertex(4);

        assertEquals(5, vertexNumber);
        assertTrue(vertex4Exists);
        assertTrue(vertex5Exists);
        assertTrue(vertex2Exists);
    }

    @Test
    public void queriesTest2(){
        setUpBidirectionalGraph1();
        integerGraph.addEdge(2,10);
        integerGraph.addEdge(2,6);
        integerGraph.addEdge(1,8);
        integerGraph.addEdge(8,16);

        int edgeNumber = integerGraph.getEdgesCount();
        boolean edge2to10 = integerGraph.hasEdge(2,10);
        boolean edge10to2 = integerGraph.hasEdge(2,10);
        boolean edge2to16 = integerGraph.hasEdge(2,16);

        assertEquals(4, edgeNumber);
        assertTrue(edge2to10);
        assertTrue(edge10to2);
        assertFalse(edge2to16);
    }

    @Test
    public void queriesTest3() throws VertexNotFoundException {
        setUpDirectionalGraph1();
        integerGraph.addEdge(9,15);
        integerGraph.addEdge(9,12);
        integerGraph.addEdge(9,8);
        integerGraph.addEdge(8,2);
        integerGraph.addEdge(4,6);

        boolean edge9to15 = integerGraph.hasEdge(9,15);
        boolean edge9to12 = integerGraph.hasEdge(9,12);
        boolean edge9to8 = integerGraph.hasEdge(9,8);

        assertTrue(edge9to15);
        assertTrue(edge9to12);
        assertTrue(edge9to8);
    }

    @Test
    public void bfsTest1() throws VertexNotFoundException {
        setUpBidirectionalGraph2();

        integerGraph.bfs(8);

        assertNull(integerGraph.getVertex(8).getPrevious());
        assertEquals(7, integerGraph.getVertex(6).getPrevious().getVertex());
        assertEquals(8, integerGraph.getVertex(2).getPrevious().getVertex());
        assertEquals(9, integerGraph.getVertex(4).getPrevious().getVertex());
        assertEquals(8, integerGraph.getVertex(10).getPrevious().getVertex());

        assertEquals(1, integerGraph.getVertex(2).getDistance());
        assertEquals(2, integerGraph.getVertex(7).getDistance());
        assertEquals(3, integerGraph.getVertex(5).getDistance());
        assertEquals(1, integerGraph.getVertex(9).getDistance());
        assertEquals(2, integerGraph.getVertex(1).getDistance());
    }

    @Test
    public void bfsTest2() throws VertexNotFoundException {
        setUpDirectionalGraph2();

        integerGraph.bfs(3);

        assertEquals(3, integerGraph.getVertex(2).getPrevious().getVertex());
        assertEquals(5, integerGraph.getVertex(1).getPrevious().getVertex());
        assertNull(integerGraph.getVertex(3).getPrevious());
        assertEquals(3, integerGraph.getVertex(4).getPrevious().getVertex());
        assertEquals(5, integerGraph.getVertex(9).getPrevious().getVertex());

        assertEquals(2, integerGraph.getVertex(1).getDistance());
        assertEquals(1, integerGraph.getVertex(2).getDistance());
        assertEquals(0, integerGraph.getVertex(3).getDistance());
        assertEquals(2, integerGraph.getVertex(6).getDistance());
        assertEquals(2, integerGraph.getVertex(9).getDistance());
    }

    @Test
    public void bfsTest3() throws VertexNotFoundException {
        setUpDirectionalGraph2();

        integerGraph.bfs(1);

        assertEquals(1, integerGraph.getVertex(6).getPrevious().getVertex());
        assertEquals(1, integerGraph.getVertex(2).getPrevious().getVertex());
        assertNull(integerGraph.getVertex(1).getPrevious());
        assertEquals(6, integerGraph.getVertex(9).getPrevious().getVertex());
        assertEquals(2, integerGraph.getVertex(4).getPrevious().getVertex());

        assertEquals(2, integerGraph.getVertex(9).getDistance());
        assertEquals(1, integerGraph.getVertex(3).getDistance());
        assertEquals(3, integerGraph.getVertex(8).getDistance());
        assertEquals(2, integerGraph.getVertex(5).getDistance());
        assertEquals(0, integerGraph.getVertex(1).getDistance());
    }

    @Test
    public void dfsTest1() throws VertexNotFoundException {
        setUpBidirectionalGraph2();

        integerGraph.dfs();

        assertEquals(9, integerGraph.getVertex(7).getITime());
        assertEquals(2, integerGraph.getVertex(2).getITime());
        assertEquals(8, integerGraph.getVertex(6).getITime());
        assertEquals(4, integerGraph.getVertex(8).getITime());

        assertEquals(18, integerGraph.getVertex(3).getFTime());
        assertEquals(12, integerGraph.getVertex(7).getFTime());
        assertEquals(20, integerGraph.getVertex(1).getFTime());
        assertEquals(16, integerGraph.getVertex(9).getFTime());

        assertNull(integerGraph.getVertex(1).getPrevious());
        assertEquals(1, integerGraph.getVertex(2).getPrevious().getVertex());
        assertEquals(6, integerGraph.getVertex(7).getPrevious().getVertex());
        assertEquals(8, integerGraph.getVertex(9).getPrevious().getVertex());
    }

    @Test
    public void dfsTest2() throws VertexNotFoundException {
        setUpDirectionalGraph2();

        integerGraph.dfs();

        assertEquals(3, integerGraph.getVertex(3).getITime());
        assertEquals(5, integerGraph.getVertex(5).getITime());
        assertEquals(7, integerGraph.getVertex(7).getITime());
        assertEquals(9, integerGraph.getVertex(9).getITime());

        assertEquals(18, integerGraph.getVertex(1).getFTime());
        assertEquals(13, integerGraph.getVertex(6).getFTime());
        assertEquals(16, integerGraph.getVertex(3).getFTime());
        assertEquals(15, integerGraph.getVertex(4).getFTime());

        assertNull(integerGraph.getVertex(1).getPrevious());
        assertEquals(2, integerGraph.getVertex(3).getPrevious().getVertex());
        assertEquals(8, integerGraph.getVertex(9).getPrevious().getVertex());
        assertEquals(6, integerGraph.getVertex(7).getPrevious().getVertex());
    }

    @Test
    public void dfsTest3(){
        setUpDirectionalGraph3();

        stringGraph.dfs();

        try {
            assertEquals(9, stringGraph.getVertex("w").getITime());
            assertEquals(4, stringGraph.getVertex("x").getITime());
            assertEquals(1, stringGraph.getVertex("u").getITime());
            assertEquals(10, stringGraph.getVertex("z").getITime());

            assertEquals(6, stringGraph.getVertex("y").getFTime());
            assertEquals(8, stringGraph.getVertex("u").getFTime());
            assertEquals(5, stringGraph.getVertex("x").getFTime());
            assertEquals(12, stringGraph.getVertex("w").getFTime());

            assertNull(stringGraph.getVertex("u").getPrevious());
            assertNull(stringGraph.getVertex("w").getPrevious());
            assertEquals("y", stringGraph.getVertex("x").getPrevious().getVertex());
            assertEquals("w", stringGraph.getVertex("z").getPrevious().getVertex());

        } catch (VertexNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void dijkstraTest1() throws VertexNotFoundException {
        setUpBidirectionalGraph2();

        integerGraph.dijkstra(1);

        assertEquals(1,integerGraph.getVertex(2).getPrevious().getVertex());
        assertEquals(3,integerGraph.getVertex(2).getDistance());

        assertEquals(2,integerGraph.getVertex(9).getPrevious().getVertex());
        assertEquals(12,integerGraph.getVertex(9).getDistance());

        assertEquals(3,integerGraph.getVertex(8).getPrevious().getVertex());
        assertEquals(6,integerGraph.getVertex(8).getDistance());

        assertEquals(9,integerGraph.getVertex(4).getPrevious().getVertex());
        assertEquals(16,integerGraph.getVertex(4).getDistance());
    }

    @Test
    public void dijkstraTest2() throws VertexNotFoundException {
        setUpBidirectionalGraph2();

        integerGraph.dijkstra(4);

        assertEquals(9,integerGraph.getVertex(8).getPrevious().getVertex());
        assertEquals(13,integerGraph.getVertex(8).getDistance());

        assertNull(integerGraph.getVertex(4).getPrevious());
        assertEquals(0,integerGraph.getVertex(4).getDistance());

        assertEquals(4,integerGraph.getVertex(9).getPrevious().getVertex());
        assertEquals(4,integerGraph.getVertex(9).getDistance());

        assertEquals(2,integerGraph.getVertex(1).getPrevious().getVertex());
        assertEquals(16,integerGraph.getVertex(1).getDistance());
    }

    @Test
    public void dijkstraTest3() throws VertexNotFoundException {
        setUpDirectionalGraph2();

        integerGraph.dijkstra(1);

        assertEquals(5,integerGraph.getVertex(6).getPrevious().getVertex());
        assertEquals(10,integerGraph.getVertex(6).getDistance());

        assertEquals(5,integerGraph.getVertex(9).getPrevious().getVertex());
        assertEquals(9,integerGraph.getVertex(9).getDistance());

        assertNull(integerGraph.getVertex(1).getPrevious());
        assertEquals(0,integerGraph.getVertex(1).getDistance());

        assertEquals(3,integerGraph.getVertex(5).getPrevious().getVertex());
        assertEquals(5,integerGraph.getVertex(5).getDistance());
    }

    @Test
    public void floydWarshallTest1(){
        setUpBidirectionalGraph2();

        Integer[][] actualResult = integerGraph.floydWarshall();
        Integer[][] expectedResult = new Integer[][]{
                {0, 3, 5, 16, 20, 22, 14, 6, 12, 11},
                {3, 0, 2, 13, 17, 19, 11, 3, 9, 8},
                {5, 2, 0, 14, 15, 17, 9, 1, 10, 6},
                {16, 13, 14, 0, 6, 8, 7, 13, 4, 10},
                {20, 17, 15, 6, 0, 2, 6, 14, 10, 9},
                {22, 19, 17, 8, 2, 0, 8, 16, 12, 11},
                {14, 11, 9, 7, 6, 8, 0, 8, 11, 3},
                {6, 3, 1, 13, 14, 16, 8, 0, 9, 5},
                {12, 9, 10, 4, 10, 12, 11, 9, 0, 14},
                {11, 8, 6, 10, 9, 11, 3, 5, 14, 0}
        };

        assertTrue(Arrays.deepEquals(actualResult, expectedResult));
    }

    @Test
    public void floydWarshallTest2(){
        setUpDirectionalGraph2();

        Integer[][] actualResult = integerGraph.floydWarshall();
        Integer[][] expectedResult = new Integer[][]{
                {0, 6, 3, 7, 5, 10, 11, 9, 9},
                {9, 0, 2, 1, 4, 9, 7, 3, 4},
                {7, 3, 0, 4, 2, 7, 8, 6, 6},
                {9, 15, 12, 0, 4, 9, 6, 2, 3},
                {5, 11, 8, 6, 0, 5, 6, 8, 4},
                {9, 15, 12, 10, 4, 0, 1, 5, 6},
                {8, 14, 11, 9, 3, 8, 0, 4, 5},
                {14, 20, 17, 15, 9, 14, 6, 0, 1},
                {13, 19, 16, 14, 8, 13, 5, 9, 0}
        };

        assertTrue(Arrays.deepEquals(actualResult, expectedResult));
    }

    @Test
    public void floydWarshallTest3(){
        setUpDirectionalGraph3();

        Integer[][] actualResult = stringGraph.floydWarshall();
        Integer[][] expectedResult = new Integer[][]{
                {0, 2, Integer.MAX_VALUE, 3, 4, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, 0, Integer.MAX_VALUE, 7, 2, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, 19, 0, 13, 8, 1},
                {Integer.MAX_VALUE, 6, Integer.MAX_VALUE, 0, 8, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, 11, Integer.MAX_VALUE, 5, 0, Integer.MAX_VALUE,},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0}
        };

        assertTrue(Arrays.deepEquals(actualResult, expectedResult));
    }

    @Test
    public void primTest1(){
        setUpBidirectionalGraph2();

        List<String> minimumSpanningTree = integerGraph.prim(6);

        String connection1 = "8, 3, Cost: 1";
        String connection2 = "7, 10, Cost: 3";
        String connection3 = "6, 5, Cost: 2";
        String connection4 = "5, 7, Cost: 6";

        assertTrue(minimumSpanningTree.contains(connection1));
        assertTrue(minimumSpanningTree.contains(connection2));
        assertTrue(minimumSpanningTree.contains(connection3));
        assertTrue(minimumSpanningTree.contains(connection4));
    }

    @Test
    public void primTest2(){
        setUpDirectionalGraph2();

        List<String> minimumSpanningTree = integerGraph.prim(1);

        String connection1 = "2, 4, Cost: 1";
        String connection2 = "8, 9, Cost: 1";
        String connection3 = "6, 7, Cost: 1";
        String connection4 = "5, 6, Cost: 5";

        assertTrue(minimumSpanningTree.contains(connection1));
        assertTrue(minimumSpanningTree.contains(connection2));
        assertTrue(minimumSpanningTree.contains(connection3));
        assertTrue(minimumSpanningTree.contains(connection4));
    }

    @Test
    public void primTest3(){
        setUpDirectionalGraph3();

        List<String> minimumSpanningTree = stringGraph.prim("u");

        String connection1 = "u, v, Cost: 2";
        String connection2 = "u, x, Cost: 3";
        String connection3 = "v, y, Cost: 2";
        String connection4 = "w, z, Cost: 1";

        assertTrue(minimumSpanningTree.contains(connection1));
        assertTrue(minimumSpanningTree.contains(connection2));
        assertTrue(minimumSpanningTree.contains(connection3));
        assertTrue(minimumSpanningTree.contains(connection4));
    }

    @Test
    public void kruskalTest1(){
        setUpBidirectionalGraph2();

        List<String> minimumSpanningTree = integerGraph.kruskal();

        String connection1 = "1, 2, Cost: 3";
        String connection2 = "2, 3, Cost: 2";
        String connection3 = "8, 10, Cost: 5";
        String connection4 = "4, 9, Cost: 4";

        assertTrue(minimumSpanningTree.contains(connection1));
        assertTrue(minimumSpanningTree.contains(connection2));
        assertTrue(minimumSpanningTree.contains(connection3));
        assertTrue(minimumSpanningTree.contains(connection4));
    }

    @Test
    public void kruskalTest2(){
        setUpDirectionalGraph2();

        List<String> minimumSpanningTree = integerGraph.kruskal();

        String connection1 = "8, 9, Cost: 1";
        String connection2 = "4, 8, Cost: 2";
        String connection3 = "3, 5, Cost: 2";
        String connection4 = "2, 3, Cost: 2";

        assertTrue(minimumSpanningTree.contains(connection1));
        assertTrue(minimumSpanningTree.contains(connection2));
        assertTrue(minimumSpanningTree.contains(connection3));
        assertTrue(minimumSpanningTree.contains(connection4));
    }

    @Test
    public void kruskalTest3(){
        setUpDirectionalGraph3();

        List<String> minimumSpanningTree = stringGraph.kruskal();

        String connection1 = "u, v, Cost: 2";
        String connection2 = "u, x, Cost: 3";
        String connection3 = "v, y, Cost: 2";
        String connection4 = "w, z, Cost: 1";

        assertTrue(minimumSpanningTree.contains(connection1));
        assertTrue(minimumSpanningTree.contains(connection2));
        assertTrue(minimumSpanningTree.contains(connection3));
        assertTrue(minimumSpanningTree.contains(connection4));
    }
}