package model;

import dataStructures.GraphAL;
import dataStructures.GraphAM;

public class citiesMap {

    private GraphAL<String> cities;
    private GraphAM<String> theCities;
    private int graphType;

    public citiesMap (int option){

        if(option == 1){
            cities = new GraphAL<>(false);
            cities.addVertex("Cali");
        }else if (option == 2) {
            theCities = new GraphAM<>(false);
            theCities.addVertex("Cali");
        }
        this.graphType = option;
    }

    public String registeredCities(){
        String message = "";
        if (graphType == 1){

        }
        if (graphType == 2){

        }

        return message;
    }

    public void registerCity(String city){

    }

    public void registerWay(String start, String end){

    }

    public void calculateMinWay (String destination){

    }

    public int getLastDistance (){
        return -1;
    }

    public String getLastWay (){
        return "";
    }

}
