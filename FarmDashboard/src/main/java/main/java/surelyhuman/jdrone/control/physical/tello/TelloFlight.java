package main.java.surelyhuman.jdrone.control.physical.tello;

import java.io.IOException;

import main.java.surelyhuman.jdrone.control.physical.tello.TelloDrone;

public class TelloFlight {

    public static void flight() throws InterruptedException, IOException {
        TelloDrone tello = new TelloDrone();
        tello.activateSDK();
        //tello.missionPadOn();
        tello.hoverInPlace(10);
        tello.takeoff();

        //getCoords(width, length, height);






        //tello.flyForward(100);
        //tello.flyBackward(100);
        //tello.turnCW(180);

        tello.land();
        tello.end();
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        flight();
        System.exit(0);
    }

}
