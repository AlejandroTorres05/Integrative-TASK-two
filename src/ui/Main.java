package ui;

import model.Controller;

import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);
    private  Controller controller;
    static String lastCity;

    public Main(int option){
        this.controller = new Controller(option);
    }

    public static void main(String[] args) {
        System.out.println("Hello, welcome to the system."
                + "\nPlease type an option for the structure"
                + "\n1. Adjacent List graph"
                + "\n2. Adjacent Matrix graph");
        int option = sc.nextInt();

        Main main = new Main(option);
    }

    public void registerCity(){

    }

    public void registerWay(){

    }

    public void minWay(){

    }

    public void addDevice(){

    }

    public void addEdge(){

    }

    public void minSpanningTree(){

    }

}