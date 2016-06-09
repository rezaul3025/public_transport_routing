/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.here.ptr;

import com.here.ptr.graph.domain.Edge;
import com.here.ptr.graph.domain.Graph;
import com.here.ptr.graph.domain.Station;
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

    private List<Station> stations;
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

        stations = new ArrayList<Station>();
        edges = new ArrayList<Edge>();

        String[] testDataArr = {"A -> B: 240", "A -> C: 70", "A -> D: 120", "C -> B: 60", "D -> E: 480", "C -> E: 240",
            "B -> E: 210", "E -> A: 300"};

        int edgeId = 1;
        for (String testData : testDataArr) {
            Station sourceStation = new Station(testData.split("->")[0].trim());
            Station distStation = new Station(testData.split("->")[1].split(":")[0].trim());
            stations.add(sourceStation);
            stations.add(sourceStation);
            Edge edge = new Edge(edgeId, sourceStation, distStation, Integer.valueOf(testData.split("->")[1].split(":")[1].trim()));
            edges.add(edge);
            edgeId++;
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testResult() {
        Graph graph = new Graph(stations, edges);
        PublicTransportRouting ptr = new PublicTransportRouting(graph);
        ptr.runSrearch(new Station("A"));
        LinkedList<Station> stations = ptr.getShortestRouteStations(new Station("B"));

        assertNotNull(stations);
        assertTrue(stations.size() > 0);

        System.out.println("Shortest route is " + ptr.getFormattedRoute(stations, new Station("B")));

        List<String> nearByStations = ptr.getNearByStations(new Station("A"), 130);
        assertNotNull(nearByStations);
        assertTrue(nearByStations.size() > 0);
        String nearByStationsStr = String.join(",", nearByStations);
        nearByStationsStr = !nearByStationsStr.isEmpty()?nearByStationsStr:" : No nearby stations found";
        System.out.println("Nearby routes are "+nearByStationsStr );

    }

}
