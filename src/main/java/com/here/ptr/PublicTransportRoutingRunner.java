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
import java.util.Scanner;

/**
 *
 * @author rkarim
 */
public class PublicTransportRoutingRunner {

    public static void main(String[] arg) {
        List<Node> nodes = new ArrayList<Node>();
        List<Edge> edges = new ArrayList<Edge>();

        Scanner stdin = new Scanner(System.in);
        int noOfedges = 0;
        System.out.print("Enter number of edges : ");
        try {
            noOfedges = stdin.nextInt();

        } catch (java.util.InputMismatchException ex) {
            System.err.println("Invalid input : " + ex);
        }
        
       // stdin.close();

        for (int i = 0; i < noOfedges; i++) {
            System.out.print("Enter the edge (like, <source> -> <destination>:<travel time>) : ");
            Scanner stdinEdge = new Scanner(System.in);
            String edge = stdinEdge.nextLine();

            if (edge.equalsIgnoreCase("E")) {
                System.exit(i);
            }

            if (edge.contains("->") && edge.contains(":")) {
                String sourceNode = edge.split("->")[0];
                String distNode = edge.split("->")[1].split(":")[0];
                int travelTime = 0;
                try {
                    travelTime = Integer.parseInt(edge.split(":")[1]);
                } catch (NumberFormatException npe) {
                    System.err.println("Invalid input, travel time must be integer" + edge);
                    continue;
                }

                nodes.add(new Node(sourceNode));
                nodes.add(new Node(distNode));
                edges.add(new Edge(i, new Node(sourceNode), new Node(distNode), travelTime));

            } else {
                System.err.println("Invalid input, it be this format: <source> -> <destination>:<travel time>" + edge);
                continue;
            }

            System.out.println(edge);
            //stdinEdge.close();
        }

        Graph graph = new Graph(nodes, edges);
        PublicTransportRouting ptr = new PublicTransportRouting(graph);
        if (nodes.size() > 0 && edges.size() > 0) {
            System.out.print("Enter query  (like, route <source -> destination) : ");
            Scanner queryStrSdtin = new Scanner(System.in);
            String queryStr = queryStrSdtin.nextLine();
            if (queryStr.contains("route") && queryStr.contains("->")) {
                String source = queryStr.split("\\s+")[1].split("->")[0];
                String target = queryStr.split("\\s+")[1].split("->")[1];
                System.out.println(source +" ,"+target);
                ptr.runSrearch(new Node(source));
                LinkedList<Node> path = ptr.getPath(new Node(target));
                
                int totalShortestTravelTime = 0;
                
                for(Node node: path){
                    System.out.println(node);
                    //totalShortestTravelTime +=node
                }
                System.out.println("dis:"+ptr.getShortestDistance(new Node(target)));
                
                
            } else {
                System.out.println("Query format invalid : " + queryStr);
            }
            
           // queryStrSdtin.close();
        }

        //String test = "route   A -> B ";
        //System.out.println(test.split("\\s+")[0]);
    }

}
