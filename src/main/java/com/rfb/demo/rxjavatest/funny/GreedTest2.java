package com.rfb.demo.rxjavatest.funny;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GreedTest2 {

    private int n;
    private int capacity;
    private int distancePerUnit;
    private int distance;
    private List<Station> stations;

    private List<Integer> minCostPreStations = new ArrayList<>();


    public GreedTest2(int n, int capacity, int distancePerUnit, int distance, List<Station> stations) {
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
            if(i == 0){
                minCostPreStations.add(0);
            }else{
                minCostPreStations.add(Integer.MAX_VALUE);
            }
        }

        for(int i = 0, size = stations.size(); i < size; i++){

            Station curStation = stations.get(i);
            for(int next = i+1; next < size; next++){

                Station nextStation = stations.get(next);
                int nextDistance = nextStation.distance - curStation.distance;
                int nextCostCapacity = nextDistance / distancePerUnit;
                if(nextCostCapacity > capacity){
                    break;
                }

                int lastCost = minCostPreStations.get(i);
                int cost = lastCost + nextCostCapacity * nextStation.costPerUnit;
                if(cost > minCostPreStations.get(next)){
                    continue;
                }

                minCostPreStations.set(next, cost);
            }
        }

        int lastPosition = minCostPreStations.size()-1;
        for(; lastPosition >= 0; lastPosition--){
            if(minCostPreStations.get(lastPosition) != Integer.MAX_VALUE){
                break;
            }
        }


        if(lastPosition == minCostPreStations.size()){
            System.out.println(minCostPreStations.get(lastPosition));
        }else{
            System.out.println(stations.get(lastPosition).distance + capacity*distancePerUnit);

        }
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
