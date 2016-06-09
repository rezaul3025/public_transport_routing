/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.here.ptr.graph.domain;

import java.util.List;

/**
 *
 * @author rkarim
 */
public class Graph {
    private List<Station> stations;
    private List<Edge> edges;
    
    public Graph(List<Station> stations, List<Edge> edges){
        this.stations = stations;
        this.edges = edges;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    
}
