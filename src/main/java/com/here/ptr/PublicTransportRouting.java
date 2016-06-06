/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.here.ptr;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author rkarim
 */
public class PublicTransportRouting {

    private Map<String, Integer> transportGraph = new HashMap<String, Integer>();

    public PublicTransportRouting() {

    }

    public PublicTransportRouting(Map<String, Integer> transportGraph) {
        this.transportGraph = transportGraph;
    }
    
    public void findGivenRoute(){
        
    }
    
    public Map<String, Integer> getTransportGraph(){
        return transportGraph;
    }
}
