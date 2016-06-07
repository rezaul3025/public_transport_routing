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
    private List<Node> nodes;
    private List<Edge> edges;
    
    public Graph(List<Node> nodes, List<Edge> edges){
        this.nodes = nodes;
        this.edges = edges;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }
}
