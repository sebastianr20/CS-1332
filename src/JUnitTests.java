import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * My tests
 *
 * @author Vincent Huang
 * @version 1.0
 */

public class JUnitTests {

    private final String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    private Graph graph;

    //----------------------
    //GRAPH CREATION METHODS
    //----------------------

    private Set<Vertex<String>> populateVert(int num) {
        Set<Vertex<String>> vertices = new HashSet<>();
        for(int i = 0; i < num; i++) {
            vertices.add(new Vertex<>(alphabet[i]));
        }
        return vertices;
    }

    private Set<Edge<String>> toEdge(String[] from, String[] to, int[] weights, boolean directed) {
        if (from.length != to.length || to.length != weights.length) {
            throw new IllegalArgumentException("Unequal array lengths");
        }
        Set<Edge<String>> edges = new LinkedHashSet<>();
        for(int i = 0; i < from.length; i++) {
            edges.add(new Edge<>(new Vertex<>(from[i]), new Vertex<>(to[i]), weights[i]));
            if (!directed) {
                edges.add(new Edge<>(new Vertex<>(to[i]), new Vertex<>(from[i]), weights[i]));
            }
        }
        return edges;
    }

    private List<Vertex<String>> toVertexList(String[] arr) {
        List<Vertex<String>> list = new ArrayList<>();
        for(String s : arr) {
            list.add(new Vertex<>(s));
        }
        return list;
    }

    private Map<Vertex<String>, Integer> toVertexDist(int[] dists) {
        Map<Vertex<String>, Integer> map = new HashMap<>();
        for(int i = 0; i < dists.length; i++) {
            map.put(new Vertex<String>(alphabet[i]), dists[i]);
        }
        return map;
    }

    //----------------------
    //GRAPHS
    //----------------------

    private Graph<String> singleNodeGraph() {
        Set<Vertex<String>> vertices = populateVert(1);
        return new Graph<>(vertices, new HashSet<>());
    }

    private Graph<String> triforceGraph() {
        Set<Vertex<String>> vertices = populateVert(6);
        String[] from = {"A", "A", "B", "B", "B", "C", "C", "D", "E"};
        String[] to   = {"B", "C", "C", "D", "E", "E", "F", "E", "F"};
        int[] weights = {  1,   2,   4,   5,   3,   6,   7,   8,   9};
        Set<Edge<String>> edges = toEdge(from, to, weights, false);
        return new Graph<>(vertices, edges);
    }

    private Graph<String> disconnectGraph() {
        Set<Vertex<String>> vertices = populateVert(5);
        String[] from = {"A", "A", "B", "D"};
        String[] to   = {"B", "C", "C", "E"};
        int[] weights = {  1,   2,   2,   5};
        Set<Edge<String>> edges = toEdge(from, to, weights, false);
        return new Graph<>(vertices, edges);
    }

    private Graph<String> selfLoopGraph() {
        Set<Vertex<String>> vertices = populateVert(3);
        String[] from = {"A", "A", "B", "B", "C"};
        String[] to   = {"B", "C", "B", "B", "C"};
        int[] weights = {  1,   2,   2,   3,   3};
        Set<Edge<String>> edges = toEdge(from, to, weights, true);
        return new Graph<>(vertices, edges);
    }

    private Graph<String> singleSelfLoopGraph() {
        Set<Vertex<String>> vertices = populateVert(1);
        String[] from = {"A"};
        String[] to   = {"A"};
        int[] weights = {  1};
        Set<Edge<String>> edges = toEdge(from, to, weights, true);
        return new Graph<>(vertices, edges);
    }

    private Graph<String> parallelEdgeGraph() {
        Set<Vertex<String>> vertices = populateVert(3);
        String[] from = {"A", "A", "A", "B", "C", "C"};
        String[] to   = {"B", "B", "C", "A", "A", "B"};
        int[] weights = {  1,   2,   3,   3,   3,   2};
        Set<Edge<String>> edges = toEdge(from, to, weights, true);
        return new Graph<>(vertices, edges);
    }

    private Graph<String> wheelGraph() {
        Set<Vertex<String>> vertices = populateVert(6);
        String[] from = {"A", "A", "A", "B", "B", "C", "C", "C", "D", "E"};
        String[] to   = {"B", "C", "D", "C", "E", "D", "E", "F", "F", "F"};
        int[] weights = {  1,   2,   5,   3,   1,   0,   0,   7,   2,   9};
        Set<Edge<String>> edges = toEdge(from, to, weights, false);
        return new Graph<>(vertices, edges);
    }

    private Graph<String> scaleGraph() {
        Set<Vertex<String>> vertices = populateVert(16);
        String[] from = {"A", "A", "B", "B", "B", "C", "C", "D", "E", "E", "E", "F", "F", "G", "G", "G", "H", "H", "H", "I", "J", "J", "K", "K", "L", "L", "M", "M", "M", "N", "O", "O", "P", "P"};
        String[] to   = {"B", "C", "A", "D", "E", "D", "E", "C", "B", "C", "F", "G", "L", "F", "H", "J", "G", "I", "K", "J", "G", "I", "H", "J", "M", "O", "L", "N", "P", "O", "L", "N", "M", "O"};
        int[] weights = {  1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1};
        Set<Edge<String>> edges = toEdge(from, to, weights, true);
        return new Graph<>(vertices, edges);
    }

    private Graph<String> LGraph() {
        Set<Vertex<String>> vertices = populateVert(5);
        String[] from = {"A", "A", "B", "D"};
        String[] to   = {"B", "C", "D", "E"};
        int[] weights = {  1,   1,   1,   1};
        Set<Edge<String>> edges = toEdge(from, to, weights, true);
        return new Graph<>(vertices, edges);
    }

    private Graph<String> parallelAndSelfLoopGraph() {
        Set<Vertex<String>> vertices = populateVert(3);
        String[] from = {"A", "A", "A", "A", "B", "B", "B", "B", "B", "B", "B", "B", "C", "C", "C", "C", "C", "C"};
        String[] to   = {"A", "B", "C", "C", "A", "A", "B", "B", "C", "C", "C", "C", "A", "A", "A", "B", "B", "C"};
        int[] weights = {  1,   1,   3,   2,   1,   1,   2,   1,   3,   6,   2,   3,   3,   3,   1,   7,   4,   2};
        Set<Edge<String>> edges = toEdge(from, to, weights, false);
        return new Graph<>(vertices, edges);
    }

    //----------------------
    //TESTS
    //----------------------

    @Test(expected = IllegalArgumentException.class)
    public void e_DFS1() {
        graph = singleNodeGraph();
        GraphAlgorithms.dfs(null, graph);
    }

    @Test(expected = IllegalArgumentException.class)
    public void e_DFS2() {
        GraphAlgorithms.dfs(new Vertex<String>("A"), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void e_DFS3() {
        graph = singleNodeGraph();
        GraphAlgorithms.dfs(new Vertex<String>("B"), graph);
    }

    @Test
    public void DFS1() {
        graph = singleNodeGraph();
        List<Vertex<String>> result = GraphAlgorithms.dfs(new Vertex<String>("A"), graph);
        List<Vertex<String>> expected = toVertexList(new String[]{"A"});
        assertEquals(expected, result);
    }

    @Test
    public void DFS2() {
        graph = triforceGraph();

        List<Vertex<String>> result = GraphAlgorithms.dfs(new Vertex<String>("A"), graph);
        List<Vertex<String>> expected = toVertexList(new String[]{"A", "B", "C", "E", "D", "F"});
        assertEquals(expected, result);

        result = GraphAlgorithms.dfs(new Vertex<String>("B"), graph);
        expected = toVertexList(new String[]{"B", "A", "C", "E", "D", "F"});
        assertEquals(expected, result);

        result = GraphAlgorithms.dfs(new Vertex<String>("C"), graph);
        expected = toVertexList(new String[]{"C", "A", "B", "D", "E", "F"});
        assertEquals(expected, result);

        result = GraphAlgorithms.dfs(new Vertex<String>("D"), graph);
        expected = toVertexList(new String[]{"D", "B", "A", "C", "E", "F"});
        assertEquals(expected, result);

        result = GraphAlgorithms.dfs(new Vertex<String>("E"), graph);
        expected = toVertexList(new String[]{"E", "B", "A", "C", "F", "D"});
        assertEquals(expected, result);

        result = GraphAlgorithms.dfs(new Vertex<String>("F"), graph);
        expected = toVertexList(new String[]{"F", "C", "A", "B", "D", "E"});
        assertEquals(expected, result);
    }

    @Test
    public void DFS3() {
        graph = disconnectGraph();

        List<Vertex<String>> result = GraphAlgorithms.dfs(new Vertex<String>("A"), graph);
        List<Vertex<String>> expected = toVertexList(new String[]{"A", "B", "C"});
        assertEquals(expected, result);

        result = GraphAlgorithms.dfs(new Vertex<String>("B"), graph);
        expected = toVertexList(new String[]{"B", "A", "C"});
        assertEquals(expected, result);

        result = GraphAlgorithms.dfs(new Vertex<String>("C"), graph);
        expected = toVertexList(new String[]{"C", "A", "B"});
        assertEquals(expected, result);

        result = GraphAlgorithms.dfs(new Vertex<String>("D"), graph);
        expected = toVertexList(new String[]{"D", "E"});
        assertEquals(expected, result);

        result = GraphAlgorithms.dfs(new Vertex<String>("E"), graph);
        expected = toVertexList(new String[]{"E", "D"});
        assertEquals(expected, result);
    }

    @Test
    public void DFS4() {
        graph = selfLoopGraph();

        List<Vertex<String>> result = GraphAlgorithms.dfs(new Vertex<String>("A"), graph);
        List<Vertex<String>> expected = toVertexList(new String[]{"A", "B", "C"});
        assertEquals(expected, result);

        result = GraphAlgorithms.dfs(new Vertex<String>("B"), graph);
        expected = toVertexList(new String[]{"B"});
        assertEquals(expected, result);

        result = GraphAlgorithms.dfs(new Vertex<String>("C"), graph);
        expected = toVertexList(new String[]{"C"});
        assertEquals(expected, result);
    }

    @Test
    public void DFS5() {
        graph = singleSelfLoopGraph();

        List<Vertex<String>> result = GraphAlgorithms.dfs(new Vertex<String>("A"), graph);
        List<Vertex<String>> expected = toVertexList(new String[]{"A"});
        assertEquals(expected, result);
    }

    @Test
    public void DFS6() {
        graph = parallelEdgeGraph();

        List<Vertex<String>> result = GraphAlgorithms.dfs(new Vertex<String>("A"), graph);
        List<Vertex<String>> expected = toVertexList(new String[]{"A", "B", "C"});
        assertEquals(expected, result);

        result = GraphAlgorithms.dfs(new Vertex<String>("B"), graph);
        expected = toVertexList(new String[]{"B", "A", "C"});
        assertEquals(expected, result);

        result = GraphAlgorithms.dfs(new Vertex<String>("C"), graph);
        expected = toVertexList(new String[]{"C", "A", "B"});
        assertEquals(expected, result);
    }

    @Test
    public void DFS7() {
        graph = wheelGraph();

        List<Vertex<String>> result = GraphAlgorithms.dfs(new Vertex<String>("A"), graph);
        List<Vertex<String>> expected = toVertexList(new String[]{"A", "B", "C", "D", "F", "E"});
        assertEquals(expected, result);

        result = GraphAlgorithms.dfs(new Vertex<String>("B"), graph);
        expected = toVertexList(new String[]{"B", "A", "C", "D", "F", "E"});
        assertEquals(expected, result);

        result = GraphAlgorithms.dfs(new Vertex<String>("C"), graph);
        expected = toVertexList(new String[]{"C", "A", "B", "E", "F", "D"});
        assertEquals(expected, result);

        result = GraphAlgorithms.dfs(new Vertex<String>("D"), graph);
        expected = toVertexList(new String[]{"D", "A", "B", "C", "E", "F"});
        assertEquals(expected, result);

        result = GraphAlgorithms.dfs(new Vertex<String>("E"), graph);
        expected = toVertexList(new String[]{"E", "B", "A", "C", "D", "F"});
        assertEquals(expected, result);

        result = GraphAlgorithms.dfs(new Vertex<String>("F"), graph);
        expected = toVertexList(new String[]{"F", "C", "A", "B", "E", "D"});
        assertEquals(expected, result);
    }

    @Test
    public void DFS8() {
        graph = scaleGraph();

        List<Vertex<String>> result = GraphAlgorithms.dfs(new Vertex<String>("A"), graph);
        List<Vertex<String>> expected = toVertexList(new String[]{"A", "B", "D", "C", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P"});
        assertEquals(expected, result);

        result = GraphAlgorithms.dfs(new Vertex<String>("B"), graph);
        expected = toVertexList(new String[]{"B", "A", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P"});
        assertEquals(expected, result);

        result = GraphAlgorithms.dfs(new Vertex<String>("C"), graph);
        expected = toVertexList(new String[]{"C", "D", "E", "B", "A", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P"});
        assertEquals(expected, result);

        result = GraphAlgorithms.dfs(new Vertex<String>("D"), graph);
        expected = toVertexList(new String[]{"D", "C", "E", "B", "A", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P"});
        assertEquals(expected, result);

        result = GraphAlgorithms.dfs(new Vertex<String>("E"), graph);
        expected = toVertexList(new String[]{"E", "B", "A", "C", "D", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P"});
        assertEquals(expected, result);

        result = GraphAlgorithms.dfs(new Vertex<String>("F"), graph);
        expected = toVertexList(new String[]{"F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P"});
        assertEquals(expected, result);

        result = GraphAlgorithms.dfs(new Vertex<String>("G"), graph);
        expected = toVertexList(new String[]{"G", "F", "L", "M", "N", "O", "P", "H", "I", "J", "K"});
        assertEquals(expected, result);

        result = GraphAlgorithms.dfs(new Vertex<String>("H"), graph);
        expected = toVertexList(new String[]{"H", "G", "F", "L", "M", "N", "O", "P", "J", "I", "K"});
        assertEquals(expected, result);

        result = GraphAlgorithms.dfs(new Vertex<String>("I"), graph);
        expected = toVertexList(new String[]{"I", "J", "G", "F", "L", "M", "N", "O", "P", "H", "K"});
        assertEquals(expected, result);

        result = GraphAlgorithms.dfs(new Vertex<String>("J"), graph);
        expected = toVertexList(new String[]{"J", "G", "F", "L", "M", "N", "O", "P", "H", "I", "K"});
        assertEquals(expected, result);

        result = GraphAlgorithms.dfs(new Vertex<String>("K"), graph);
        expected = toVertexList(new String[]{"K", "H", "G", "F", "L", "M", "N", "O", "P", "J", "I"});
        assertEquals(expected, result);

        result = GraphAlgorithms.dfs(new Vertex<String>("L"), graph);
        expected = toVertexList(new String[]{"L", "M", "N", "O", "P"});
        assertEquals(expected, result);

        result = GraphAlgorithms.dfs(new Vertex<String>("M"), graph);
        expected = toVertexList(new String[]{"M", "L", "O", "N", "P"});
        assertEquals(expected, result);

        result = GraphAlgorithms.dfs(new Vertex<String>("N"), graph);
        expected = toVertexList(new String[]{"N", "O", "L", "M", "P"});
        assertEquals(expected, result);

        result = GraphAlgorithms.dfs(new Vertex<String>("O"), graph);
        expected = toVertexList(new String[]{"O", "L", "M", "N", "P"});
        assertEquals(expected, result);

        result = GraphAlgorithms.dfs(new Vertex<String>("P"), graph);
        expected = toVertexList(new String[]{"P", "M", "L", "O", "N"});
        assertEquals(expected, result);
    }

    @Test
    public void DFS9() {
        graph = LGraph();

        List<Vertex<String>> result = GraphAlgorithms.dfs(new Vertex<String>("A"), graph);
        List<Vertex<String>> expected = toVertexList(new String[]{"A", "B", "D", "E", "C"});
        assertEquals(expected, result);

        result = GraphAlgorithms.dfs(new Vertex<String>("B"), graph);
        expected = toVertexList(new String[]{"B", "D", "E"});
        assertEquals(expected, result);

        result = GraphAlgorithms.dfs(new Vertex<String>("C"), graph);
        expected = toVertexList(new String[]{"C"});
        assertEquals(expected, result);
        result = GraphAlgorithms.dfs(new Vertex<String>("D"), graph);
        expected = toVertexList(new String[]{"D", "E"});
        assertEquals(expected, result);

        result = GraphAlgorithms.dfs(new Vertex<String>("E"), graph);
        expected = toVertexList(new String[]{"E"});
        assertEquals(expected, result);
    }


    @Test(expected = IllegalArgumentException.class)
    public void e_DJK1() {
        graph = singleNodeGraph();
        GraphAlgorithms.dijkstras(null, graph);
    }

    @Test(expected = IllegalArgumentException.class)
    public void e_DJK2() {
        GraphAlgorithms.dijkstras(new Vertex<String>("A"), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void e_DJK3() {
        graph = singleNodeGraph();
        GraphAlgorithms.dijkstras(new Vertex<String>("B"), graph);
    }

    @Test
    public void DJK1() {
        graph = singleNodeGraph();

        Map<Vertex<String>, Integer> result = GraphAlgorithms.dijkstras(new Vertex<String>("A"), graph);
        Map<Vertex<String>, Integer> expected = toVertexDist(new int[]{0});
        assertEquals(expected, result);
    }

    @Test
    public void DJK2() {
        graph = triforceGraph();
        Map<Vertex<String>, Integer> result;
        Map<Vertex<String>, Integer> expected;

        int[][] expectedArr = {
                {0, 1, 2, 6, 4, 9},
                {1, 0, 3, 5, 3, 10},
                {2, 3, 0, 8, 6, 7},
                {6, 5, 8, 0, 8, 15},
                {4, 3, 6, 8, 0, 9},
                {9, 10, 7, 15, 9, 0}
        };

        for(int i = 0; i < expectedArr.length; i++) {
            result = GraphAlgorithms.dijkstras(new Vertex<String>(alphabet[i]), graph);
            expected = toVertexDist(expectedArr[i]);
            assertEquals(expected, result);
        }
    }

    @Test
    public void DJK3() {
        graph = disconnectGraph();
        Map<Vertex<String>, Integer> result;
        Map<Vertex<String>, Integer> expected;

        int[][] expectedArr = {
                {0, 1, 2, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {1, 0, 2, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {2, 2, 0, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 5},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 5, 0}
        };

        for(int i = 0; i < expectedArr.length; i++) {
            result = GraphAlgorithms.dijkstras(new Vertex<String>(alphabet[i]), graph);
            expected = toVertexDist(expectedArr[i]);
            assertEquals(expected, result);
        }
    }

    @Test
    public void DJK4() {
        graph = selfLoopGraph();
        Map<Vertex<String>, Integer> result;
        Map<Vertex<String>, Integer> expected;

        int[][] expectedArr = {
                {0, 1, 2},
                {Integer.MAX_VALUE, 0, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, 0}
        };

        for(int i = 0; i < expectedArr.length; i++) {
            result = GraphAlgorithms.dijkstras(new Vertex<String>(alphabet[i]), graph);
            expected = toVertexDist(expectedArr[i]);
            assertEquals(expected, result);
        }
    }


    @Test
    public void DJK5() {
        graph = singleSelfLoopGraph();
        Map<Vertex<String>, Integer> result;
        Map<Vertex<String>, Integer> expected;

        int[][] expectedArr = {
                {0}
        };

        for(int i = 0; i < expectedArr.length; i++) {
            result = GraphAlgorithms.dijkstras(new Vertex<String>(alphabet[i]), graph);
            expected = toVertexDist(expectedArr[i]);
            assertEquals(expected, result);
        }
    }


    @Test
    public void DJK6() {
        graph = parallelEdgeGraph();
        Map<Vertex<String>, Integer> result;
        Map<Vertex<String>, Integer> expected;

        int[][] expectedArr = {
                {0, 1, 3},
                {3, 0, 6},
                {3, 2, 0}
        };

        for(int i = 0; i < expectedArr.length; i++) {
            result = GraphAlgorithms.dijkstras(new Vertex<String>(alphabet[i]), graph);
            expected = toVertexDist(expectedArr[i]);
            assertEquals(expected, result);
        }
    }


    @Test
    public void DJK7() {
        graph = wheelGraph();
        Map<Vertex<String>, Integer> result;
        Map<Vertex<String>, Integer> expected;

        int[][] expectedArr = {
                {0, 1, 2, 2, 2, 4},
                {1, 0, 1, 1, 1, 3},
                {2, 1, 0, 0, 0, 2},
                {2, 1, 0, 0, 0, 2},
                {2, 1, 0, 0, 0, 2},
                {4, 3, 2, 2, 2, 0}
        };

        for(int i = 0; i < expectedArr.length; i++) {
            result = GraphAlgorithms.dijkstras(new Vertex<String>(alphabet[i]), graph);
            expected = toVertexDist(expectedArr[i]);
            assertEquals(expected, result);
        }
    }


    @Test
    public void DJK8() {
        graph = scaleGraph();
        Map<Vertex<String>, Integer> result;
        Map<Vertex<String>, Integer> expected;

        int[][] expectedArr = {
                {0, 1, 1, 2, 2, 3, 4, 5, 6, 5, 6, 4, 5, 6, 5, 6},
                {1, 0, 2, 1, 1, 2, 3, 4, 5, 4, 5, 3, 4, 5, 4, 5},
                {3, 2, 0, 1, 1, 2, 3, 4, 5, 4, 5, 3, 4, 5, 4, 5},
                {4, 3, 1, 0, 2, 3, 4, 5, 6, 5, 6, 4, 5, 6, 5, 6},
                {2, 1, 1, 2, 0, 1, 2, 3, 4, 3, 4, 2, 3, 4, 3, 4},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 1, 2, 3, 2, 3, 1, 2, 3, 2, 3},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 1, 0, 1, 2, 1, 2, 2, 3, 4, 3, 4},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 2, 1, 0, 1, 2, 1, 3, 4, 5, 4, 5},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 3, 2, 3, 0, 1, 4, 4, 5, 6, 5, 6},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 2, 1, 2, 1, 0, 3, 3, 4, 5, 4, 5},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 3, 2, 1, 2, 1, 0, 4, 5, 6, 5, 6},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 1, 2, 1, 2},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 1, 0, 1, 2, 1},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 2, 3, 0, 1, 4},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 1, 2, 1, 0, 3},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 2, 1, 2, 1, 0}
        };

        for(int i = 0; i < expectedArr.length; i++) {
            result = GraphAlgorithms.dijkstras(new Vertex<String>(alphabet[i]), graph);
            expected = toVertexDist(expectedArr[i]);
            assertEquals(expected, result);
        }
    }

    @Test
    public void DJK9() {
        graph = LGraph();
        Map<Vertex<String>, Integer> result;
        Map<Vertex<String>, Integer> expected;

        int[][] expectedArr = {
                {0, 1, 1, 2, 3},
                {Integer.MAX_VALUE, 0, Integer.MAX_VALUE, 1, 2},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 1},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0}
        };

        for(int i = 0; i < expectedArr.length; i++) {
            result = GraphAlgorithms.dijkstras(new Vertex<String>(alphabet[i]), graph);
            expected = toVertexDist(expectedArr[i]);
            assertEquals(expected, result);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void e_PRM1() {
        graph = singleNodeGraph();
        GraphAlgorithms.prims(null, graph);
    }

    @Test(expected = IllegalArgumentException.class)
    public void e_PRM2() {
        GraphAlgorithms.prims(new Vertex<String>("A"), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void e_PRM3() {
        graph = singleNodeGraph();
        GraphAlgorithms.prims(new Vertex<String>("B"), graph);
    }

    @Test
    public void PRM1() {
        graph = singleNodeGraph();
        assertEquals(new HashSet<>(), GraphAlgorithms.prims(new Vertex<>("A"), graph));
    }

    @Test
    public void PRM2() {
        graph = triforceGraph();

        String[] from = {"A", "A", "B", "B", "C"};
        String[] to   = {"B", "C", "D", "E", "F"};
        int[] weights = {  1,   2,   5,   3,   7};
        Set<Edge<String>> expected = toEdge(from, to, weights, false);
        Set<Edge<String>> actual = GraphAlgorithms.prims(new Vertex<>("A"), graph);
        assertEquals(expected, actual);

        actual = GraphAlgorithms.prims(new Vertex<>("B"), graph);
        assertEquals(expected, actual);

        actual = GraphAlgorithms.prims(new Vertex<>("C"), graph);
        assertEquals(expected, actual);

        actual = GraphAlgorithms.prims(new Vertex<>("D"), graph);
        assertEquals(expected, actual);

        actual = GraphAlgorithms.prims(new Vertex<>("E"), graph);
        assertEquals(expected, actual);

        actual = GraphAlgorithms.prims(new Vertex<>("F"), graph);
        assertEquals(expected, actual);
    }

    @Test
    public void PRM3() {
        graph = disconnectGraph();
        assertEquals(null, GraphAlgorithms.prims(new Vertex<>("A"), graph));
        assertEquals(null, GraphAlgorithms.prims(new Vertex<>("B"), graph));
        assertEquals(null, GraphAlgorithms.prims(new Vertex<>("C"), graph));
        assertEquals(null, GraphAlgorithms.prims(new Vertex<>("D"), graph));
        assertEquals(null, GraphAlgorithms.prims(new Vertex<>("E"), graph));
    }

    @Test
    public void PRM4() {
        graph = singleSelfLoopGraph();
        assertEquals(new HashSet<>(), GraphAlgorithms.prims(new Vertex<>("A"), graph));
    }

    @Test
    public void PRM5() {
        graph = wheelGraph();

        String[] from = {"A", "B", "C", "C", "D"};
        String[] to   = {"B", "E", "D", "E", "F"};
        int[] weights = {  1,   1,   0,   0,   2};
        Set<Edge<String>> expected = toEdge(from, to, weights, false);
        Set<Edge<String>> actual = GraphAlgorithms.prims(new Vertex<>("A"), graph);
        assertEquals(expected, actual);

        actual = GraphAlgorithms.prims(new Vertex<>("B"), graph);
        assertEquals(expected, actual);

        actual = GraphAlgorithms.prims(new Vertex<>("C"), graph);
        assertEquals(expected, actual);

        actual = GraphAlgorithms.prims(new Vertex<>("D"), graph);
        assertEquals(expected, actual);

        actual = GraphAlgorithms.prims(new Vertex<>("E"), graph);
        assertEquals(expected, actual);

        actual = GraphAlgorithms.prims(new Vertex<>("F"), graph);
        assertEquals(expected, actual);
    }

    @Test
    public void PRM6() {
        graph = parallelAndSelfLoopGraph();

        String[] from = {"A", "C"};
        String[] to   = {"B", "A"};
        int[] weights = {  1,   1};
        Set<Edge<String>> expected = toEdge(from, to, weights, false);
        Set<Edge<String>> actual = GraphAlgorithms.prims(new Vertex<>("A"), graph);
        assertEquals(expected, actual);

        actual = GraphAlgorithms.prims(new Vertex<>("B"), graph);
        assertEquals(expected, actual);

        actual = GraphAlgorithms.prims(new Vertex<>("C"), graph);
        assertEquals(expected, actual);
    }





}