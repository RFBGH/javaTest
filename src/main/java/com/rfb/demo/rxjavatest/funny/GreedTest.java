package com.rfb.demo.rxjavatest.funny;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GreedTest {

    private int n;
    private int capacity;
    private int distancePerUnit;
    private int distance;
    private List<Station> stations;

    private List<Integer> minCostPreStations = new ArrayList<Integer>();


    public GreedTest(int n, int capacity, int distancePerUnit, int distance, List<Station> stations) {
        this.n = n;
        this.capacity = capacity;
        this.distancePerUnit = distancePerUnit;
        this.distance = distance;
        this.stations = stations;
    }



    public void findResult(){

        Station finalStation = new Station();
        finalStation.distance = distance;
        finalStation.costPerUnit = 0;
        stations.add(finalStation);
        Collections.sort(stations);

        for(int i = 0, size = stations.size(); i < size; i++){
            minCostPreStations.add(Integer.MAX_VALUE);
        }

        int front = 0;

        Node startNode = new Node();
        startNode.i = 0;
        startNode.cost = 0;
        startNode.pre = -1;

        List<Node> nodes = new ArrayList<Node>();
        nodes.add(startNode);
        while(front < nodes.size()){

            Node curNode = nodes.get(front);
            int i = curNode.i;
            Station curStation = stations.get(i);

            for(int next = i+1, size = stations.size(); next < size; next++){

                Station nextStation = stations.get(next);
                int nextDistance = nextStation.distance - curStation.distance;
                int costUnit = nextDistance / distancePerUnit;
                if(costUnit > capacity){
                    break;
                }

                int cost = curNode.cost + costUnit * curStation.costPerUnit;
                if(cost > minCostPreStations.get(next)){
                    continue;
                }

                Node nextNode = new Node();
                nextNode.i = next;
                nextNode.pre = front;
                nextNode.cost = cost;
            }
        }

        int minValue = minCostPreStations.get(minCostPreStations.size()-1);
        if(minValue!= Integer.MAX_VALUE){
            System.out.println(minValue);
        }else{

            int lastPosition = 0;
            for(Node node:nodes){
                if(node.i > lastPosition){
                    lastPosition = node.i;
                }
            }

            Station station = stations.get(lastPosition);
            System.out.println(station.distance + capacity * distancePerUnit);
        }
    }

    private static class Node{
        int i;
        int cost;
        int pre;
    }

    public static class Station implements Comparable<Station>{
        int distance;
        int costPerUnit;

        @Override
        public int compareTo(Station o) {
            if(distance < o.distance){
                return -1;
            }

            if(distance > o.distance){
                return 1;
            }

            return 0;
        }
    }
}
