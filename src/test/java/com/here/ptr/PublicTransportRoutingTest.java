/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.here.ptr;

import com.here.ptr.graph.domain.Edge;
import com.here.ptr.graph.domain.Graph;
import com.here.ptr.graph.domain.Node;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rkarim
 */
public class PublicTransportRoutingTest {

    private PublicTransportRouting testRouteGraph;

    private List<Node> nodes;
    private List<Edge> edges;

    public PublicTransportRoutingTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        nodes = new ArrayList<Node>();
        edges = new ArrayList<Edge>();

        //Add all nodes
        /*for (int i = 0; i < 11; i++) {
            Node node = new Node("Node_" + i);
            nodes.add(node);
        }*/
        String[] testDataArr = {"A -> B: 240", "A -> C: 70", "A -> D: 120", "C -> B: 60", "D -> E: 480", "C -> E: 240",
            "B -> E: 210", "E -> A: 300"};

        int edgeId = 1;
        for (String testData : testDataArr) {
            Node sourceNode = new Node(testData.split("->")[0].trim());
            Node distNode = new Node(testData.split("->")[1].split(":")[0].trim());
            nodes.add(sourceNode);
            nodes.add(sourceNode);
            Edge edge = new Edge(edgeId, sourceNode, distNode, Integer.valueOf(testData.split("->")[1].split(":")[1].trim()));
            edges.add(edge);
            edgeId++;
        }


        /* addEdge(0, 0, 1, 85);
        addEdge(1, 0, 2, 217);
        addEdge(2, 0, 4, 173);
        addEdge(3, 2, 6, 186);
        addEdge(4, 2, 7, 103);
        addEdge(5, 3, 7, 183);
        addEdge(6, 5, 8, 250);
        addEdge(7, 8, 9, 84);
        addEdge(8, 7, 9, 167);
        addEdge(9, 4, 9, 502);
        addEdge(10, 9, 10, 40);
        addEdge(11, 1, 10, 600);*/
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testResult() {
        Graph graph = new Graph(nodes, edges);
        PublicTransportRouting ptr = new PublicTransportRouting(graph);
        ptr.runSrearch(new Node("A"));
        LinkedList<Node> paths = ptr.getShortestPath(new Node("B"));

        assertNotNull(paths);
        assertTrue(paths.size() > 0);
        
        for(Node node: ptr.getNeighbors(new Node("A"))){
            System.out.println("node"+node.getName());
        }

        System.out.println("Shortest route is "+ptr.getFormattedPath(paths, new Node("B")));
        
        
    }

    private void addEdge(int edgeId, int sourceNodeLocNo, int destNodeLocNo,
            int duration) {
        Edge edge = new Edge(edgeId, nodes.get(sourceNodeLocNo), nodes.get(destNodeLocNo), duration);
        edges.add(edge);
    }
}
