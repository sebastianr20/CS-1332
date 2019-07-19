import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * A set of basic tests to test your graph algorithm implementations.
 *
 * These tests are NOT exhaustive. Wait for Vincent's to ensure code coverage.
 *
 * First time making JUnits, let me know if you find anything wrong!
 *
 * @author Anthony Zheng
 * @version 1.0
 */
public class JUnitTests2 {

    private Graph<Character> directedGraph1;
    private Graph<Character> directedGraph2;
    private Graph<Character> undirectedGraph1;
    private Graph<Character> undirectedGraph2;
    private Graph<Character> primsGraph1;
    private Graph<Character> primsGraph2;
    public static final int TIMEOUT = 200;
    private static boolean passed = true;

    @Before
    public void init() {
        directedGraph1 = createDirectedGraph1();
        directedGraph2 = createDirectedGraph2();
        undirectedGraph1 = createUndirectedGraph1();
        undirectedGraph2 = createUndirectedGraph2();
        primsGraph1 = createPrimsGraph1();
        primsGraph2 = createPrimsGraph2();
    }

    /**
     * Changes static variable to false if any tests fail
     */
    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            passed = false;
        }
    };

    /**
     * Creates a directed graph.
     *
     * @return the completed graph
     */
    private Graph<Character> createDirectedGraph1() {
        Set<Vertex<Character>> vertices = new HashSet<>();
        String alphabet = "ABCDE";
        for (int i = 0; i < alphabet.length(); i++) {
            vertices.add(new Vertex<>(alphabet.charAt(i)));
        }

        Set<Edge<Character>> edges = new LinkedHashSet<>();
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('B'), 1));
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('C'), 2));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('C'), 2));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('E'), 1));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('C'), 3));

        return new Graph<>(vertices, edges);
    }

    /**
     * Creates a directed graph.
     *
     * @return the completed graph
     */
    private Graph<Character> createDirectedGraph2() {
        Set<Vertex<Character>> vertices = new HashSet<>();
        String alphabet = "ABC";
        for (int i = 0; i < alphabet.length(); i++) {
            vertices.add(new Vertex<>(alphabet.charAt(i)));
        }

        Set<Edge<Character>> edges = new LinkedHashSet<>();
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('A'), 5));
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('B'), 3));
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('B'), 1));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('C'), 2));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('A'), 4));

        return new Graph<>(vertices, edges);
    }

    /**
     * Creates an undirected graph.
     *
     * @return the completed graph
     */
    private Graph<Character> createUndirectedGraph1() {
        Set<Vertex<Character>> vertices = new HashSet<>();
        String alphabet = "ABCDE";
        for (int i = 0; i < alphabet.length(); i++) {
            vertices.add(new Vertex<>(alphabet.charAt(i)));
        }

        Set<Edge<Character>> edges = new LinkedHashSet<>();

        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('B'), 1));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('A'), 1));

        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('C'), 3));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('A'), 3));

        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('C'), 1));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('B'), 1));

        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('D'), 0));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('C'), 0));

        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('E'), 4));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('C'), 4));

        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('E'), 4));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('D'), 4));

        return new Graph<>(vertices, edges);
    }

    /**
     * Creates an undirected graph.
     *
     * @return the completed graph
     */
    private Graph<Character> createUndirectedGraph2() {
        Set<Vertex<Character>> vertices = new HashSet<>();
        String alphabet = "ABCDEFGH";
        for (int i = 0; i < alphabet.length(); i++) {
            vertices.add(new Vertex<>(alphabet.charAt(i)));
        }

        Set<Edge<Character>> edges = new LinkedHashSet<>();

        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('B'), 5));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('A'), 5));

        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('C'), 2));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('A'), 2));

        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('E'), 3));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('B'), 3));

        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('D'), 2));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('C'), 2));

        edges.add(new Edge<>(new Vertex<>('F'), new Vertex<>('E'), 2));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('F'), 2));

        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('E'), 3));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('D'), 3));

        edges.add(new Edge<>(new Vertex<>('G'), new Vertex<>('E'), 5));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('G'), 5));

        edges.add(new Edge<>(new Vertex<>('G'), new Vertex<>('D'), 9));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('G'), 9));

        edges.add(new Edge<>(new Vertex<>('F'), new Vertex<>('H'), 7));
        edges.add(new Edge<>(new Vertex<>('H'), new Vertex<>('F'), 7));

        edges.add(new Edge<>(new Vertex<>('G'), new Vertex<>('H'), 1));
        edges.add(new Edge<>(new Vertex<>('H'), new Vertex<>('G'), 1));

        return new Graph<>(vertices, edges);
    }

    /**
     * Creates an undirected graph.
     *
     * @return the completed graph
     */
    private Graph<Character> createPrimsGraph1() {
        Set<Vertex<Character>> vertices = new HashSet<>();
        String alphabet = "ABCDEFGHI";
        for (int i = 0; i < alphabet.length(); i++) {
            vertices.add(new Vertex<>(alphabet.charAt(i)));
        }

        Set<Edge<Character>> edges = new LinkedHashSet<>();

        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('B'), 4));
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('H'), 9));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('A'), 4));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('H'), 11));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('C'), 8));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('B'), 8));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('I'), 2));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('F'), 4));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('D'), 7));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('C'), 7));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('F'), 14));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('E'), 9));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('D'), 9));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('F'), 10));
        edges.add(new Edge<>(new Vertex<>('F'), new Vertex<>('G'), 2));
        edges.add(new Edge<>(new Vertex<>('F'), new Vertex<>('C'), 4));
        edges.add(new Edge<>(new Vertex<>('F'), new Vertex<>('D'), 14));
        edges.add(new Edge<>(new Vertex<>('F'), new Vertex<>('E'), 10));
        edges.add(new Edge<>(new Vertex<>('G'), new Vertex<>('F'), 2));
        edges.add(new Edge<>(new Vertex<>('G'), new Vertex<>('I'), 6));
        edges.add(new Edge<>(new Vertex<>('G'), new Vertex<>('H'), 1));
        edges.add(new Edge<>(new Vertex<>('H'), new Vertex<>('A'), 9));
        edges.add(new Edge<>(new Vertex<>('H'), new Vertex<>('B'), 11));
        edges.add(new Edge<>(new Vertex<>('H'), new Vertex<>('I'), 7));
        edges.add(new Edge<>(new Vertex<>('H'), new Vertex<>('G'), 1));
        edges.add(new Edge<>(new Vertex<>('I'), new Vertex<>('H'), 7));
        edges.add(new Edge<>(new Vertex<>('I'), new Vertex<>('C'), 2));
        edges.add(new Edge<>(new Vertex<>('I'), new Vertex<>('G'), 6));

        return new Graph<>(vertices, edges);
    }

    /**
     * Creates an undirected graph.
     *
     * @return the completed graph
     */
    private Graph<Character> createPrimsGraph2() {
        Set<Vertex<Character>> vertices = new HashSet<>();
        String alphabet = "ABCD";
        for (int i = 0; i < alphabet.length(); i++) {
            vertices.add(new Vertex<>(alphabet.charAt(i)));
        }

        Set<Edge<Character>> edges = new LinkedHashSet<>();

        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('B'), 1));
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('B'), 2));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('C'), 3));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('C'), 4));

        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('A'), 1));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('A'), 2));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('B'), 3));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('B'), 4));

        return new Graph<>(vertices, edges);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testDFS_nullStart() {
        GraphAlgorithms.dfs(null, directedGraph1);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testDFS_nullGraph() {
        GraphAlgorithms.dfs(new Vertex<>('A'), null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testDFS_startNotInGraph() {
        GraphAlgorithms.dfs(new Vertex<>('Z'), directedGraph1);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testDijkstras_nullStart() {
        GraphAlgorithms.dijkstras(null, directedGraph1);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testDijkstras_nullGraph() {
        GraphAlgorithms.dijkstras(new Vertex<>('A'), null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testDijkstras_startNotInGraph() {
        GraphAlgorithms.dijkstras(new Vertex<>('Z'), directedGraph1);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testPrims_nullStart() {
        GraphAlgorithms.prims(null, directedGraph1);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testPrims_nullGraph() {
        GraphAlgorithms.prims(new Vertex<>('A'), null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testPrims_startNotInGraph() {
        GraphAlgorithms.prims(new Vertex<>('Z'), directedGraph1);
    }

    @Test(timeout = TIMEOUT)
    public void testDFS_directed1_A() {
        List<Vertex<Character>> dfsActual = GraphAlgorithms.dfs(
                new Vertex<>('A'), directedGraph1);

        List<Vertex<Character>> dfsExpected = new LinkedList<>();
        dfsExpected.add(new Vertex<>('A'));
        dfsExpected.add(new Vertex<>('B'));
        dfsExpected.add(new Vertex<>('C'));
        dfsExpected.add(new Vertex<>('E'));

        assertEquals(dfsExpected, dfsActual);
    }

    @Test(timeout = TIMEOUT)
    public void testDFS_directed1_B() {
        List<Vertex<Character>> dfsActual = GraphAlgorithms.dfs(
                new Vertex<>('B'), directedGraph1);

        List<Vertex<Character>> dfsExpected = new LinkedList<>();
        dfsExpected.add(new Vertex<>('B'));
        dfsExpected.add(new Vertex<>('C'));
        dfsExpected.add(new Vertex<>('E'));

        assertEquals(dfsExpected, dfsActual);
    }

    @Test(timeout = TIMEOUT)
    public void testDFS_directed1_C() {
        List<Vertex<Character>> dfsActual = GraphAlgorithms.dfs(
                new Vertex<>('C'), directedGraph1);

        List<Vertex<Character>> dfsExpected = new LinkedList<>();
        dfsExpected.add(new Vertex<>('C'));
        dfsExpected.add(new Vertex<>('E'));

        assertEquals(dfsExpected, dfsActual);
    }

    @Test(timeout = TIMEOUT)
    public void testDFS_directed1_D() {
        List<Vertex<Character>> dfsActual = GraphAlgorithms.dfs(
                new Vertex<>('D'), directedGraph1);

        List<Vertex<Character>> dfsExpected = new LinkedList<>();
        dfsExpected.add(new Vertex<>('D'));
        dfsExpected.add(new Vertex<>('C'));
        dfsExpected.add(new Vertex<>('E'));

        assertEquals(dfsExpected, dfsActual);
    }

    @Test(timeout = TIMEOUT)
    public void testDFS_directed1_E() {
        List<Vertex<Character>> dfsActual = GraphAlgorithms.dfs(
                new Vertex<>('E'), directedGraph1);

        List<Vertex<Character>> dfsExpected = new LinkedList<>();
        dfsExpected.add(new Vertex<>('E'));

        assertEquals(dfsExpected, dfsActual);
    }

    @Test(timeout = TIMEOUT)
    public void testDFS_directed2_A() {
        List<Vertex<Character>> dfsActual = GraphAlgorithms.dfs(
                new Vertex<>('A'), directedGraph2);

        List<Vertex<Character>> dfsExpected = new LinkedList<>();
        dfsExpected.add(new Vertex<>('A'));
        dfsExpected.add(new Vertex<>('B'));
        dfsExpected.add(new Vertex<>('C'));

        assertEquals(dfsExpected, dfsActual);
    }

    @Test(timeout = TIMEOUT)
    public void testDFS_directed2_B() {
        List<Vertex<Character>> dfsActual = GraphAlgorithms.dfs(
                new Vertex<>('B'), directedGraph2);

        List<Vertex<Character>> dfsExpected = new LinkedList<>();
        dfsExpected.add(new Vertex<>('B'));
        dfsExpected.add(new Vertex<>('C'));
        dfsExpected.add(new Vertex<>('A'));

        assertEquals(dfsExpected, dfsActual);
    }

    @Test(timeout = TIMEOUT)
    public void testDFS_directed2_C() {
        List<Vertex<Character>> dfsActual = GraphAlgorithms.dfs(
                new Vertex<>('C'), directedGraph2);

        List<Vertex<Character>> dfsExpected = new LinkedList<>();
        dfsExpected.add(new Vertex<>('C'));
        dfsExpected.add(new Vertex<>('A'));
        dfsExpected.add(new Vertex<>('B'));

        assertEquals(dfsExpected, dfsActual);
    }

    @Test(timeout = TIMEOUT)
    public void testDFS_undirected1_A() {
        List<Vertex<Character>> dfsActual = GraphAlgorithms.dfs(
                new Vertex<>('A'), undirectedGraph1);

        List<Vertex<Character>> dfsExpected = new LinkedList<>();
        dfsExpected.add(new Vertex<>('A'));
        dfsExpected.add(new Vertex<>('B'));
        dfsExpected.add(new Vertex<>('C'));
        dfsExpected.add(new Vertex<>('D'));
        dfsExpected.add(new Vertex<>('E'));

        assertEquals(dfsExpected, dfsActual);
    }

    @Test(timeout = TIMEOUT)
    public void testDFS_undirected1_B() {
        List<Vertex<Character>> dfsActual = GraphAlgorithms.dfs(
                new Vertex<>('B'), undirectedGraph1);

        List<Vertex<Character>> dfsExpected = new LinkedList<>();
        dfsExpected.add(new Vertex<>('B'));
        dfsExpected.add(new Vertex<>('A'));
        dfsExpected.add(new Vertex<>('C'));
        dfsExpected.add(new Vertex<>('D'));
        dfsExpected.add(new Vertex<>('E'));

        assertEquals(dfsExpected, dfsActual);
    }

    @Test(timeout = TIMEOUT)
    public void testDFS_undirected1_C() {
        List<Vertex<Character>> dfsActual = GraphAlgorithms.dfs(
                new Vertex<>('C'), undirectedGraph1);

        List<Vertex<Character>> dfsExpected = new LinkedList<>();
        dfsExpected.add(new Vertex<>('C'));
        dfsExpected.add(new Vertex<>('A'));
        dfsExpected.add(new Vertex<>('B'));
        dfsExpected.add(new Vertex<>('D'));
        dfsExpected.add(new Vertex<>('E'));

        assertEquals(dfsExpected, dfsActual);
    }

    @Test(timeout = TIMEOUT)
    public void testDFS_undirected1_D() {
        List<Vertex<Character>> dfsActual = GraphAlgorithms.dfs(
                new Vertex<>('D'), undirectedGraph1);

        List<Vertex<Character>> dfsExpected = new LinkedList<>();
        dfsExpected.add(new Vertex<>('D'));
        dfsExpected.add(new Vertex<>('C'));
        dfsExpected.add(new Vertex<>('A'));
        dfsExpected.add(new Vertex<>('B'));
        dfsExpected.add(new Vertex<>('E'));

        assertEquals(dfsExpected, dfsActual);
    }

    @Test(timeout = TIMEOUT)
    public void testDFS_undirected1_E() {
        List<Vertex<Character>> dfsActual = GraphAlgorithms.dfs(
                new Vertex<>('E'), undirectedGraph1);

        List<Vertex<Character>> dfsExpected = new LinkedList<>();
        dfsExpected.add(new Vertex<>('E'));
        dfsExpected.add(new Vertex<>('C'));
        dfsExpected.add(new Vertex<>('A'));
        dfsExpected.add(new Vertex<>('B'));
        dfsExpected.add(new Vertex<>('D'));

        assertEquals(dfsExpected, dfsActual);
    }

    @Test(timeout = TIMEOUT)
    public void testDFS_undirected2_A() {
        List<Vertex<Character>> dfsActual = GraphAlgorithms.dfs(
                new Vertex<>('A'), undirectedGraph2);

        List<Vertex<Character>> dfsExpected = new LinkedList<>();
        dfsExpected.add(new Vertex<>('A'));
        dfsExpected.add(new Vertex<>('B'));
        dfsExpected.add(new Vertex<>('E'));
        dfsExpected.add(new Vertex<>('F'));
        dfsExpected.add(new Vertex<>('H'));
        dfsExpected.add(new Vertex<>('G'));
        dfsExpected.add(new Vertex<>('D'));
        dfsExpected.add(new Vertex<>('C'));

        assertEquals(dfsExpected, dfsActual);
    }

    @Test(timeout = TIMEOUT)
    public void testDFS_undirected2_B() {
        List<Vertex<Character>> dfsActual = GraphAlgorithms.dfs(
                new Vertex<>('B'), undirectedGraph2);

        List<Vertex<Character>> dfsExpected = new LinkedList<>();
        dfsExpected.add(new Vertex<>('B'));
        dfsExpected.add(new Vertex<>('A'));
        dfsExpected.add(new Vertex<>('C'));
        dfsExpected.add(new Vertex<>('D'));
        dfsExpected.add(new Vertex<>('E'));
        dfsExpected.add(new Vertex<>('F'));
        dfsExpected.add(new Vertex<>('H'));
        dfsExpected.add(new Vertex<>('G'));

        assertEquals(dfsExpected, dfsActual);
    }

    @Test(timeout = TIMEOUT)
    public void testDFS_undirected2_C() {
        List<Vertex<Character>> dfsActual = GraphAlgorithms.dfs(
                new Vertex<>('C'), undirectedGraph2);

        List<Vertex<Character>> dfsExpected = new LinkedList<>();
        dfsExpected.add(new Vertex<>('C'));
        dfsExpected.add(new Vertex<>('A'));
        dfsExpected.add(new Vertex<>('B'));
        dfsExpected.add(new Vertex<>('E'));
        dfsExpected.add(new Vertex<>('F'));
        dfsExpected.add(new Vertex<>('H'));
        dfsExpected.add(new Vertex<>('G'));
        dfsExpected.add(new Vertex<>('D'));

        assertEquals(dfsExpected, dfsActual);
    }

    @Test(timeout = TIMEOUT)
    public void testDFS_undirected2_D() {
        List<Vertex<Character>> dfsActual = GraphAlgorithms.dfs(
                new Vertex<>('D'), undirectedGraph2);

        List<Vertex<Character>> dfsExpected = new LinkedList<>();
        dfsExpected.add(new Vertex<>('D'));
        dfsExpected.add(new Vertex<>('C'));
        dfsExpected.add(new Vertex<>('A'));
        dfsExpected.add(new Vertex<>('B'));
        dfsExpected.add(new Vertex<>('E'));
        dfsExpected.add(new Vertex<>('F'));
        dfsExpected.add(new Vertex<>('H'));
        dfsExpected.add(new Vertex<>('G'));

        assertEquals(dfsExpected, dfsActual);
    }

    @Test(timeout = TIMEOUT)
    public void testDFS_undirected2_E() {
        List<Vertex<Character>> dfsActual = GraphAlgorithms.dfs(
                new Vertex<>('E'), undirectedGraph2);

        List<Vertex<Character>> dfsExpected = new LinkedList<>();
        dfsExpected.add(new Vertex<>('E'));
        dfsExpected.add(new Vertex<>('B'));
        dfsExpected.add(new Vertex<>('A'));
        dfsExpected.add(new Vertex<>('C'));
        dfsExpected.add(new Vertex<>('D'));
        dfsExpected.add(new Vertex<>('G'));
        dfsExpected.add(new Vertex<>('H'));
        dfsExpected.add(new Vertex<>('F'));

        assertEquals(dfsExpected, dfsActual);
    }

    @Test(timeout = TIMEOUT)
    public void testDFS_undirected2_F() {
        List<Vertex<Character>> dfsActual = GraphAlgorithms.dfs(
                new Vertex<>('F'), undirectedGraph2);

        List<Vertex<Character>> dfsExpected = new LinkedList<>();
        dfsExpected.add(new Vertex<>('F'));
        dfsExpected.add(new Vertex<>('E'));
        dfsExpected.add(new Vertex<>('B'));
        dfsExpected.add(new Vertex<>('A'));
        dfsExpected.add(new Vertex<>('C'));
        dfsExpected.add(new Vertex<>('D'));
        dfsExpected.add(new Vertex<>('G'));
        dfsExpected.add(new Vertex<>('H'));

        assertEquals(dfsExpected, dfsActual);
    }

    @Test(timeout = TIMEOUT)
    public void testDijkstras_directed1_A() {
        Map<Vertex<Character>, Integer> dijkActual = GraphAlgorithms.dijkstras(
                new Vertex<>('A'), directedGraph1);
        Map<Vertex<Character>, Integer> dijkExpected = new HashMap<>();
        dijkExpected.put(new Vertex<>('A'), 0);
        dijkExpected.put(new Vertex<>('B'), 1);
        dijkExpected.put(new Vertex<>('C'), 2);
        dijkExpected.put(new Vertex<>('D'), Integer.MAX_VALUE);
        dijkExpected.put(new Vertex<>('E'), 3);

        assertEquals(dijkExpected, dijkActual);
    }

    @Test(timeout = TIMEOUT)
    public void testDijkstras_directed1_B() {
        Map<Vertex<Character>, Integer> dijkActual = GraphAlgorithms.dijkstras(
                new Vertex<>('B'), directedGraph1);
        Map<Vertex<Character>, Integer> dijkExpected = new HashMap<>();
        dijkExpected.put(new Vertex<>('A'), Integer.MAX_VALUE);
        dijkExpected.put(new Vertex<>('B'), 0);
        dijkExpected.put(new Vertex<>('C'), 2);
        dijkExpected.put(new Vertex<>('D'), Integer.MAX_VALUE);
        dijkExpected.put(new Vertex<>('E'), 3);

        assertEquals(dijkExpected, dijkActual);
    }

    @Test(timeout = TIMEOUT)
    public void testDijkstras_directed1_C() {
        Map<Vertex<Character>, Integer> dijkActual = GraphAlgorithms.dijkstras(
                new Vertex<>('C'), directedGraph1);
        Map<Vertex<Character>, Integer> dijkExpected = new HashMap<>();
        dijkExpected.put(new Vertex<>('A'), Integer.MAX_VALUE);
        dijkExpected.put(new Vertex<>('B'), Integer.MAX_VALUE);
        dijkExpected.put(new Vertex<>('C'), 0);
        dijkExpected.put(new Vertex<>('D'), Integer.MAX_VALUE);
        dijkExpected.put(new Vertex<>('E'), 1);

        assertEquals(dijkExpected, dijkActual);
    }

    @Test(timeout = TIMEOUT)
    public void testDijkstras_directed1_D() {
        Map<Vertex<Character>, Integer> dijkActual = GraphAlgorithms.dijkstras(
                new Vertex<>('D'), directedGraph1);
        Map<Vertex<Character>, Integer> dijkExpected = new HashMap<>();
        dijkExpected.put(new Vertex<>('A'), Integer.MAX_VALUE);
        dijkExpected.put(new Vertex<>('B'), Integer.MAX_VALUE);
        dijkExpected.put(new Vertex<>('C'), 3);
        dijkExpected.put(new Vertex<>('D'), 0);
        dijkExpected.put(new Vertex<>('E'), 4);

        assertEquals(dijkExpected, dijkActual);
    }

    @Test(timeout = TIMEOUT)
    public void testDijkstras_directed1_E() {
        Map<Vertex<Character>, Integer> dijkActual = GraphAlgorithms.dijkstras(
                new Vertex<>('E'), directedGraph1);
        Map<Vertex<Character>, Integer> dijkExpected = new HashMap<>();
        dijkExpected.put(new Vertex<>('A'), Integer.MAX_VALUE);
        dijkExpected.put(new Vertex<>('B'), Integer.MAX_VALUE);
        dijkExpected.put(new Vertex<>('C'), Integer.MAX_VALUE);
        dijkExpected.put(new Vertex<>('D'), Integer.MAX_VALUE);
        dijkExpected.put(new Vertex<>('E'), 0);

        assertEquals(dijkExpected, dijkActual);
    }

    @Test(timeout = TIMEOUT)
    public void testDijkstras_directed2_A() {
        Map<Vertex<Character>, Integer> dijkActual = GraphAlgorithms.dijkstras(
                new Vertex<>('A'), directedGraph2);
        Map<Vertex<Character>, Integer> dijkExpected = new HashMap<>();
        dijkExpected.put(new Vertex<>('A'), 0);
        dijkExpected.put(new Vertex<>('B'), 1);
        dijkExpected.put(new Vertex<>('C'), 3);

        assertEquals(dijkExpected, dijkActual);
    }

    @Test(timeout = TIMEOUT)
    public void testDijkstras_directed2_B() {
        Map<Vertex<Character>, Integer> dijkActual = GraphAlgorithms.dijkstras(
                new Vertex<>('B'), directedGraph2);
        Map<Vertex<Character>, Integer> dijkExpected = new HashMap<>();
        dijkExpected.put(new Vertex<>('A'), 6);
        dijkExpected.put(new Vertex<>('B'), 0);
        dijkExpected.put(new Vertex<>('C'), 2);

        assertEquals(dijkExpected, dijkActual);
    }

    @Test(timeout = TIMEOUT)
    public void testDijkstras_directed2_C() {
        Map<Vertex<Character>, Integer> dijkActual = GraphAlgorithms.dijkstras(
                new Vertex<>('C'), directedGraph2);
        Map<Vertex<Character>, Integer> dijkExpected = new HashMap<>();
        dijkExpected.put(new Vertex<>('A'), 4);
        dijkExpected.put(new Vertex<>('B'), 5);
        dijkExpected.put(new Vertex<>('C'), 0);

        assertEquals(dijkExpected, dijkActual);
    }

    @Test(timeout = TIMEOUT)
    public void testDijkstras_undirected1_A() {
        Map<Vertex<Character>, Integer> dijkActual = GraphAlgorithms.dijkstras(
                new Vertex<>('A'), undirectedGraph1);
        Map<Vertex<Character>, Integer> dijkExpected = new HashMap<>();
        dijkExpected.put(new Vertex<>('A'), 0);
        dijkExpected.put(new Vertex<>('B'), 1);
        dijkExpected.put(new Vertex<>('C'), 2);
        dijkExpected.put(new Vertex<>('D'), 2);
        dijkExpected.put(new Vertex<>('E'), 6);

        assertEquals(dijkExpected, dijkActual);
    }

    @Test(timeout = TIMEOUT)
    public void testDijkstras_undirected1_B() {
        Map<Vertex<Character>, Integer> dijkActual = GraphAlgorithms.dijkstras(
                new Vertex<>('B'), undirectedGraph1);
        Map<Vertex<Character>, Integer> dijkExpected = new HashMap<>();
        dijkExpected.put(new Vertex<>('A'), 1);
        dijkExpected.put(new Vertex<>('B'), 0);
        dijkExpected.put(new Vertex<>('C'), 1);
        dijkExpected.put(new Vertex<>('D'), 1);
        dijkExpected.put(new Vertex<>('E'), 5);

        assertEquals(dijkExpected, dijkActual);
    }

    @Test(timeout = TIMEOUT)
    public void testDijkstras_undirected1_C() {
        Map<Vertex<Character>, Integer> dijkActual = GraphAlgorithms.dijkstras(
                new Vertex<>('C'), undirectedGraph1);
        Map<Vertex<Character>, Integer> dijkExpected = new HashMap<>();
        dijkExpected.put(new Vertex<>('A'), 2);
        dijkExpected.put(new Vertex<>('B'), 1);
        dijkExpected.put(new Vertex<>('C'), 0);
        dijkExpected.put(new Vertex<>('D'), 0);
        dijkExpected.put(new Vertex<>('E'), 4);

        assertEquals(dijkExpected, dijkActual);
    }

    @Test(timeout = TIMEOUT)
    public void testDijkstras_undirected1_D() {
        Map<Vertex<Character>, Integer> dijkActual = GraphAlgorithms.dijkstras(
                new Vertex<>('D'), undirectedGraph1);
        Map<Vertex<Character>, Integer> dijkExpected = new HashMap<>();
        dijkExpected.put(new Vertex<>('A'), 2);
        dijkExpected.put(new Vertex<>('B'), 1);
        dijkExpected.put(new Vertex<>('C'), 0);
        dijkExpected.put(new Vertex<>('D'), 0);
        dijkExpected.put(new Vertex<>('E'), 4);

        assertEquals(dijkExpected, dijkActual);
    }

    @Test(timeout = TIMEOUT)
    public void testDijkstras_undirected1_E() {
        Map<Vertex<Character>, Integer> dijkActual = GraphAlgorithms.dijkstras(
                new Vertex<>('E'), undirectedGraph1);
        Map<Vertex<Character>, Integer> dijkExpected = new HashMap<>();
        dijkExpected.put(new Vertex<>('A'), 6);
        dijkExpected.put(new Vertex<>('B'), 5);
        dijkExpected.put(new Vertex<>('C'), 4);
        dijkExpected.put(new Vertex<>('D'), 4);
        dijkExpected.put(new Vertex<>('E'), 0);

        assertEquals(dijkExpected, dijkActual);
    }

    @Test(timeout = TIMEOUT)
    public void testDijkstras_undirected2_A() {
        Map<Vertex<Character>, Integer> dijkActual = GraphAlgorithms.dijkstras(
                new Vertex<>('A'), undirectedGraph2);
        Map<Vertex<Character>, Integer> dijkExpected = new HashMap<>();
        dijkExpected.put(new Vertex<>('A'), 0);
        dijkExpected.put(new Vertex<>('B'), 5);
        dijkExpected.put(new Vertex<>('C'), 2);
        dijkExpected.put(new Vertex<>('D'), 4);
        dijkExpected.put(new Vertex<>('E'), 7);
        dijkExpected.put(new Vertex<>('F'), 9);
        dijkExpected.put(new Vertex<>('G'), 12);
        dijkExpected.put(new Vertex<>('H'), 13);

        assertEquals(dijkExpected, dijkActual);
    }

    @Test(timeout = TIMEOUT)
    public void testDijkstras_undirected2_B() {
        Map<Vertex<Character>, Integer> dijkActual = GraphAlgorithms.dijkstras(
                new Vertex<>('B'), undirectedGraph2);
        Map<Vertex<Character>, Integer> dijkExpected = new HashMap<>();
        dijkExpected.put(new Vertex<>('A'), 5);
        dijkExpected.put(new Vertex<>('B'), 0);
        dijkExpected.put(new Vertex<>('C'), 7);
        dijkExpected.put(new Vertex<>('D'), 6);
        dijkExpected.put(new Vertex<>('E'), 3);
        dijkExpected.put(new Vertex<>('F'), 5);
        dijkExpected.put(new Vertex<>('G'), 8);
        dijkExpected.put(new Vertex<>('H'), 9);

        assertEquals(dijkExpected, dijkActual);
    }

    @Test(timeout = TIMEOUT)
    public void testDijkstras_undirected2_C() {
        Map<Vertex<Character>, Integer> dijkActual = GraphAlgorithms.dijkstras(
                new Vertex<>('C'), undirectedGraph2);
        Map<Vertex<Character>, Integer> dijkExpected = new HashMap<>();
        dijkExpected.put(new Vertex<>('A'), 2);
        dijkExpected.put(new Vertex<>('B'), 7);
        dijkExpected.put(new Vertex<>('C'), 0);
        dijkExpected.put(new Vertex<>('D'), 2);
        dijkExpected.put(new Vertex<>('E'), 5);
        dijkExpected.put(new Vertex<>('F'), 7);
        dijkExpected.put(new Vertex<>('G'), 10);
        dijkExpected.put(new Vertex<>('H'), 11);

        assertEquals(dijkExpected, dijkActual);
    }

    @Test(timeout = TIMEOUT)
    public void testDijkstras_undirected2_D() {
        Map<Vertex<Character>, Integer> dijkActual = GraphAlgorithms.dijkstras(
                new Vertex<>('D'), undirectedGraph2);
        Map<Vertex<Character>, Integer> dijkExpected = new HashMap<>();
        dijkExpected.put(new Vertex<>('A'), 4);
        dijkExpected.put(new Vertex<>('B'), 6);
        dijkExpected.put(new Vertex<>('C'), 2);
        dijkExpected.put(new Vertex<>('D'), 0);
        dijkExpected.put(new Vertex<>('E'), 3);
        dijkExpected.put(new Vertex<>('F'), 5);
        dijkExpected.put(new Vertex<>('G'), 8);
        dijkExpected.put(new Vertex<>('H'), 9);

        assertEquals(dijkExpected, dijkActual);
    }

    @Test(timeout = TIMEOUT)
    public void testDijkstras_undirected2_E() {
        Map<Vertex<Character>, Integer> dijkActual = GraphAlgorithms.dijkstras(
                new Vertex<>('E'), undirectedGraph2);
        Map<Vertex<Character>, Integer> dijkExpected = new HashMap<>();
        dijkExpected.put(new Vertex<>('A'), 7);
        dijkExpected.put(new Vertex<>('B'), 3);
        dijkExpected.put(new Vertex<>('C'), 5);
        dijkExpected.put(new Vertex<>('D'), 3);
        dijkExpected.put(new Vertex<>('E'), 0);
        dijkExpected.put(new Vertex<>('F'), 2);
        dijkExpected.put(new Vertex<>('G'), 5);
        dijkExpected.put(new Vertex<>('H'), 6);

        assertEquals(dijkExpected, dijkActual);
    }

    @Test(timeout = TIMEOUT)
    public void testDijkstras_undirected2_F() {
        Map<Vertex<Character>, Integer> dijkActual = GraphAlgorithms.dijkstras(
                new Vertex<>('F'), undirectedGraph2);
        Map<Vertex<Character>, Integer> dijkExpected = new HashMap<>();
        dijkExpected.put(new Vertex<>('A'), 9);
        dijkExpected.put(new Vertex<>('B'), 5);
        dijkExpected.put(new Vertex<>('C'), 7);
        dijkExpected.put(new Vertex<>('D'), 5);
        dijkExpected.put(new Vertex<>('E'), 2);
        dijkExpected.put(new Vertex<>('F'), 0);
        dijkExpected.put(new Vertex<>('G'), 7);
        dijkExpected.put(new Vertex<>('H'), 7);

        assertEquals(dijkExpected, dijkActual);
    }

    @Test(timeout = TIMEOUT)
    public void testPrims_1B() {
        Set<Edge<Character>> mstActual = GraphAlgorithms.prims(
                new Vertex<>('B'), primsGraph1);
        Set<Edge<Character>> mstExpected = new HashSet<>();
        mstExpected.add(new Edge<>(new Vertex<>('A'), new Vertex<>('B'), 4));
        mstExpected.add(new Edge<>(new Vertex<>('C'), new Vertex<>('B'), 8));
        mstExpected.add(new Edge<>(new Vertex<>('C'), new Vertex<>('I'), 2));
        mstExpected.add(new Edge<>(new Vertex<>('C'), new Vertex<>('F'), 4));
        mstExpected.add(new Edge<>(new Vertex<>('F'), new Vertex<>('G'), 2));
        mstExpected.add(new Edge<>(new Vertex<>('H'), new Vertex<>('G'), 1));
        mstExpected.add(new Edge<>(new Vertex<>('C'), new Vertex<>('D'), 7));
        mstExpected.add(new Edge<>(new Vertex<>('D'), new Vertex<>('E'), 9));

        mstExpected.add(new Edge<>(new Vertex<>('B'), new Vertex<>('A'), 4));
        mstExpected.add(new Edge<>(new Vertex<>('B'), new Vertex<>('C'), 8));
        mstExpected.add(new Edge<>(new Vertex<>('I'), new Vertex<>('C'), 2));
        mstExpected.add(new Edge<>(new Vertex<>('F'), new Vertex<>('C'), 4));
        mstExpected.add(new Edge<>(new Vertex<>('G'), new Vertex<>('F'), 2));
        mstExpected.add(new Edge<>(new Vertex<>('G'), new Vertex<>('H'), 1));
        mstExpected.add(new Edge<>(new Vertex<>('D'), new Vertex<>('C'), 7));
        mstExpected.add(new Edge<>(new Vertex<>('E'), new Vertex<>('D'), 9));

        assertEquals(mstExpected, mstActual);
    }

    @Test(timeout = TIMEOUT)
    public void testPrims_2C() {
        Set<Edge<Character>> mstActual = GraphAlgorithms.prims(
                new Vertex<>('C'), primsGraph2);

        assertNull(mstActual);
    }

    @Test(timeout = TIMEOUT)
    public void testPrims_directed2_A() {
        Set<Edge<Character>> mstActual = GraphAlgorithms.prims(
                new Vertex<>('A'), directedGraph2);
        Set<Edge<Character>> mstExpected = new HashSet<>();
        mstExpected.add(new Edge<>(new Vertex<>('A'), new Vertex<>('B'), 1));
        mstExpected.add(new Edge<>(new Vertex<>('B'), new Vertex<>('C'), 2));

        mstExpected.add(new Edge<>(new Vertex<>('B'), new Vertex<>('A'), 1));
        mstExpected.add(new Edge<>(new Vertex<>('C'), new Vertex<>('B'), 2));

        assertEquals(mstExpected, mstActual);
    }

    @Test(timeout = TIMEOUT)
    public void testPrims_directed2_B() {
        Set<Edge<Character>> mstActual = GraphAlgorithms.prims(
                new Vertex<>('B'), directedGraph2);
        Set<Edge<Character>> mstExpected = new HashSet<>();
        mstExpected.add(new Edge<>(new Vertex<>('B'), new Vertex<>('C'), 2));
        mstExpected.add(new Edge<>(new Vertex<>('C'), new Vertex<>('A'), 4));

        mstExpected.add(new Edge<>(new Vertex<>('C'), new Vertex<>('B'), 2));
        mstExpected.add(new Edge<>(new Vertex<>('A'), new Vertex<>('C'), 4));

        assertEquals(mstExpected, mstActual);
    }

    @Test(timeout = TIMEOUT)
    public void testPrims_directed2_C() {
        Set<Edge<Character>> mstActual = GraphAlgorithms.prims(
                new Vertex<>('C'), directedGraph2);
        Set<Edge<Character>> mstExpected = new HashSet<>();
        mstExpected.add(new Edge<>(new Vertex<>('B'), new Vertex<>('A'), 1));
        mstExpected.add(new Edge<>(new Vertex<>('C'), new Vertex<>('A'), 4));

        mstExpected.add(new Edge<>(new Vertex<>('A'), new Vertex<>('B'), 1));
        mstExpected.add(new Edge<>(new Vertex<>('A'), new Vertex<>('C'), 4));

        assertEquals(mstExpected, mstActual);
    }

    /**
     * Prints a message if user passes all given tests
     *
     * ASCII art from the online ASCII Art Archive
     */
    @AfterClass
    public static void testPassed() {
        if (passed) {
            System.out.println("             ________________________________________________");
            System.out.println("            /                                                \\");
            System.out.println("           |    _________________________________________     |");
            System.out.println("           |   |                                         |    |");
            System.out.println("           |   |  C:\\> _ PASSED!                         |    |");
            System.out.println("           |   |                                         |    |");
            System.out.println("           |   |                                         |    |");
            System.out.println("           |   |                                         |    |");
            System.out.println("           |   |                                         |    |");
            System.out.println("           |   |                                         |    |");
            System.out.println("           |   |                                         |    |");
            System.out.println("           |   |                                         |    |");
            System.out.println("           |   |_________________________________________|    |");
            System.out.println("           |                                                  |");
            System.out.println("            \\_________________________________________________/");
            System.out.println("                   \\___________________________________/");
            System.out.println("                ___________________________________________");
            System.out.println("             _-'    .-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.  --- `-_");
            System.out.println("          _-'.-.-. .---.-.-.-.-.-.-.-.-.-.-.-.-.-.-.--.  .-.-.`-_");
            System.out.println("       _-'.-.-.-. .---.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-`__`. .-.-.-.`-_");
            System.out.println("    _-'.-.-.-.-. .-----.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-----. .-.-.-.-.`-_");
            System.out.println(" _-'.-.-.-.-.-. .---.-. .-------------------------. .-.---. .---.-.-.-.`-_");
            System.out.println(":-------------------------------------------------------------------------:");
            System.out.println("`---._.-------------------------------------------------------------._.---'");
        }
    }
}