package model;

import dataStructures.GraphAL;
import dataStructures.GraphAM;

public class Service {

    private String name;
    private String city;
    private GraphAM<String> hardwareDevices;
    private GraphAL<String> devices;

    public Service (int option){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDevicesList (){
        return "";
    }
    public void addDevice (String device){

    }

    public void addEdge (String device1, String device2){

    }

    public String getMinimumSpanningTree (){
        return "";
    }
}
