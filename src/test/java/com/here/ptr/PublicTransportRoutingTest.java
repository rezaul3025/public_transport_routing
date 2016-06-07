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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
        for (int i = 0; i < 11; i++) {
            Node location = new Node("Node_" + i);
            nodes.add(location);
        }

        addLane("Edge_0", 0, 1, 85);
        addLane("Edge_1", 0, 2, 217);
        addLane("Edge_2", 0, 4, 173);
        addLane("Edge_3", 2, 6, 186);
        addLane("Edge_4", 2, 7, 103);
        addLane("Edge_5", 3, 7, 183);
        addLane("Edge_6", 5, 8, 250);
        addLane("Edge_7", 8, 9, 84);
        addLane("Edge_8", 7, 9, 167);
        addLane("Edge_9", 4, 9, 502);
        addLane("Edge_10", 9, 10, 40);
        addLane("Edge_11", 1, 10, 600);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSomeMethod() {
        Graph graph = new Graph(nodes, edges);
        PublicTransportRouting ptr = new PublicTransportRouting(graph);
        ptr.runSrearch(nodes.get(0));
        LinkedList<Node> path = ptr.getPath(nodes.get(10));

        assertNotNull(path);
        assertTrue(path.size() > 0);

        for (Node node : path) {
            System.out.println(node);
        }
    }

    private void addLane(String laneId, int sourceLocNo, int destLocNo,
            int duration) {
        Edge lane = new Edge(laneId, nodes.get(sourceLocNo), nodes.get(destLocNo), duration);
        edges.add(lane);
    }
}
