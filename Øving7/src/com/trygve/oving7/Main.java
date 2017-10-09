package com.trygve.oving7;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws Exception{

        NodePath nodePath = new NodePath("L7g1");
        System.out.println(nodePath.getStartLog());
        System.out.println(nodePath.showAll());
        System.out.println(nodePath.navigate(0, 6));

        NodePath nodePath2 = new NodePath("L7g5");
        System.out.println(nodePath2.getStartLog());
        System.out.println(nodePath2.showTopological());

		/*  Du trenger L7Skandinavia og L7Skandinavia-navn fila for å kjøre denne delen!
        GPS gps = new GPS("L7Skandinavia","L7Skandinavia-navn");
        System.out.println(gps.getStartLog());
        System.out.println(gps.navigate("Drammen", "Helsinki"));
		*/

    }
}
