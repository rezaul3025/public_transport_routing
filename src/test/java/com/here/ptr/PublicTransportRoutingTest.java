/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.here.ptr;

import java.util.HashMap;
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
        Map<String, Integer> testRoute = new HashMap<String, Integer>();
        testRoute.put("AB", 300);
        testRoute.put("CB", 30);
        testRoute.put("AD", 300);
        testRoute.put("EF", 300);
        testRoute.put("AC", 50);
        testRoute.put("CA", 67);
        testRouteGraph = new PublicTransportRouting(testRoute);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSomeMethod() {
        // TODO review the generated test code and remove the default call to fail.
        assertTrue(testRouteGraph.getTransportGraph().size() >0);
        testRouteGraph.findGivenRoute();
    }

}
