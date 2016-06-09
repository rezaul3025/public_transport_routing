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
import java.util.Scanner;

/**
 *
 * @author rkarim
 */
public class PublicTransportRoutingRunner {

    public static void main(String[] arg) {
        List<Station> stations = new ArrayList<Station>();
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
            System.out.println("For exit please, type E");
            System.out.print("Enter the edge (like, <source> -> <destination>:<travel time>) : ");
            Scanner stdinEdge = new Scanner(System.in);
            String edge = stdinEdge.nextLine();

            if (edge.equalsIgnoreCase("E")) {
                System.exit(i);
            }

            if (edge.contains("->") && edge.contains(":")) {
                String sourceStation = edge.split("->")[0];
                String distStation = edge.split("->")[1].split(":")[0];
                int travelTime = 0;
                try {
                    travelTime = Integer.parseInt(edge.split(":")[1]);
                } catch (NumberFormatException npe) {
                    System.err.println("Invalid input, travel time must be integer" + edge);
                }

                stations.add(new Station(sourceStation));
                stations.add(new Station(distStation));
                edges.add(new Edge(i, new Station(sourceStation), new Station(distStation), travelTime));

            } else {
                System.err.println("Invalid input, it must be this format: <source> -> <destination>:<travel time>" + edge);
            }

            //System.out.println(edge);
            //stdinEdge.close();
        }

        Graph graph = new Graph(stations, edges);
        PublicTransportRouting ptr = new PublicTransportRouting(graph);
        if (stations.size() > 0 && edges.size() > 0) {
            System.out.print("Enter query  (like, route <source -> destination) : ");
            Scanner queryStrSdtin = new Scanner(System.in);
            String queryStr = queryStrSdtin.nextLine();
            if (queryStr.contains("route") && queryStr.contains("->") && queryStr.split("\\s+").length == 2) {
                String source = queryStr.split("\\s+")[1].split("->")[0];
                String target = queryStr.split("\\s+")[1].split("->")[1];
                //System.out.println(source +" ,"+target);
                ptr.runSrearch(new Station(source));
                LinkedList<Station> paths = ptr.getShortestRouteStations(new Station(target));

                System.out.println("Shortest route is " + ptr.getFormattedRoute(paths, new Station(target)));

            } else {
                System.out.println("Query format invalid : " + queryStr);
            }
            
            System.out.print("Enter nearby query  (like, <source>, <maximum travel time>) : ");
            Scanner queryStrSdtinNearBy = new Scanner(System.in);
             String queryStrNearBy = queryStrSdtinNearBy.nextLine();
            if (queryStrNearBy.contains("nearby") && queryStrNearBy.contains(",") && queryStrNearBy.split("\\s+").length == 2) {
                String source = queryStrNearBy.split("\\s+")[1].split(",")[0];
                String time = queryStrNearBy.split("\\s+")[1].split(",")[1];
                System.out.println("Nearby routes are "+String.join(",", ptr.getNearByStations(new Station(source), Integer.valueOf(time))));
                
            } else {
                System.out.println("Query format invalid : " + queryStr);
            }
        }
    }
}
