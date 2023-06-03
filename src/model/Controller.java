package model;

import java.util.HashMap;

public class Controller {

    private citiesMap citiesMap;
    private HashMap<String,Service> services;
    private int graphType;

    public Controller(int option){
        this.citiesMap = new citiesMap(option);
        this.services = new HashMap<>();
        this.graphType = option;
    }

    public String registeredCities(){
        return this.citiesMap.registeredCities();
    }

    public void registerCity(String city){
        this.citiesMap.registerCity(city);
    }

    public void registerWay(String start, String end){
        this.citiesMap.registerWay(start, end);
    }

    public String calculateMinWay(){
        this.citiesMap.calculateMinWay("Cali");
        return this.citiesMap.getLastWay();
    }

    public String getDeviceList(String name){
        return this.services.get(name).getDevicesList();
    }

    public void addDevice(String device, String city){
        this.services.get(city).addDevice(device);
    }

    public void addEdge(String device1, String device2, String city){
        this.services.get(city).addEdge(device1, device2);
    }

    public String minSpanningTree(String city){
        return this.services.get(city).getMinimumSpanningTree();
    }
}
