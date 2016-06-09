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
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author rkarim
 */
public class PublicTransportRouting {

    private final List<Station> stations;
    private final List<Edge> edges;
    private Set<Station> settledStations;
    private Set<Station> unSettledStations;
    private Map<Station, Station> predecessors;
    private Map<Station, Integer> distance;
    private Station queryStationSource;
    private Station queryStationDist;

    public PublicTransportRouting(Graph graph) {
        this.stations = new ArrayList<>(graph.getStations());
        this.edges = new ArrayList<>(graph.getEdges());
    }

    public void runSrearch(Station source) {
        settledStations = new HashSet<>();
        unSettledStations = new HashSet<>();
        distance = new HashMap<>();
        predecessors = new HashMap<>();
        queryStationSource = source;
        distance.put(source, 0);
        unSettledStations.add(source);
        while (unSettledStations.size() > 0) {
            Station station = getMinimum(unSettledStations);
            settledStations.add(station);
            unSettledStations.remove(station);
            findMinimalDistances(station);
        }
    }

    public LinkedList<Station> getShortestRouteStations(Station target) {
        LinkedList<Station> route = new LinkedList<>();
        queryStationDist = target;
        Station step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        route.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            route.add(step);
        }
        // Put it into the correct order
        Collections.reverse(route);
        return route;
    }

    public String getFormattedRoute(LinkedList<Station> shortestRoutes, Station destination) {
        List<String> routes = new ArrayList<>();

        String formattedRoute = "Error: No route from " + queryStationSource + " to " + queryStationDist;

        if (shortestRoutes != null) {
            for (Station station : shortestRoutes) {
                routes.add(station.getName());
            }

            formattedRoute = String.join("->", routes) + ":" + getShortestDistance(destination);
        }

        return formattedRoute;
    }

    public List<String> getNearByStations(Station sourceStation, int weight) {
        LinkedList<Edge> nearbyRoutes = new LinkedList<>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(sourceStation) && edge.getDestination().equals(queryStationDist)) {
                edge.setWeight(getShortestDistance(queryStationDist));
            }
            if (edge.getSource().equals(sourceStation) && edge.getWeight() <= weight) {
                nearbyRoutes.add(edge);
            }
        }
        
        Collections.sort(nearbyRoutes, (Edge e1, Edge e2) -> e1.getWeight() - e2.getWeight());
        
        List<String> formattedNearbyRoutes = new ArrayList<>();
        
        for(Edge route : nearbyRoutes){
            formattedNearbyRoutes.add(route.getDestination()+":"+route.getWeight());
        }
        
        return formattedNearbyRoutes;
    }

    private Station getMinimum(Set<Station> stations) {
        Station minimum = null;
        for (Station station : stations) {
            if (minimum == null) {
                minimum = station;
            } else if (getShortestDistance(station) < getShortestDistance(minimum)) {
                minimum = station;
            }
        }
        return minimum;
    }

    private void findMinimalDistances(Station station) {
        List<Station> adjacentStations = getNeighbors(station);
        for (Station target : adjacentStations) {
            if (getShortestDistance(target) > getShortestDistance(station)
                    + getDistance(station, target)) {
                distance.put(target, getShortestDistance(station)
                        + getDistance(station, target));
                predecessors.put(target, station);
                unSettledStations.add(target);
            }
        }

    }

    private int getDistance(Station station, Station target) {
        for (Edge edge : edges) {
            if (edge.getSource().equals(station)
                    && edge.getDestination().equals(target)) {
                return edge.getWeight();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    public int getShortestDistance(Station destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    private List<Station> getNeighbors(Station station) {
        List<Station> neighbors = new ArrayList<>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(station) && !isSettled(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }

    private boolean isSettled(Station station) {
        return settledStations.contains(station);
    }
}
