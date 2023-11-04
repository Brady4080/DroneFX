package main.java.surelyhuman.jdrone.control.physical.tello;

import java.io.IOException;

import main.java.surelyhuman.jdrone.control.physical.tello.TelloDrone;


public class TelloFlight {

    public static int width;
    public static int length;
    public static int height;

    public static void getDist(int width, int length, int height) {
        TelloFlight.width = width;
        TelloFlight.length = length;
        TelloFlight.height = height;
    }

    public static void flight() throws InterruptedException, IOException {
        TelloDrone tello = new TelloDrone();
        tello.activateSDK();
        //tello.missionPadOn();
        //tello.hoverInPlace(10);
        tello.takeoff();
        /*
        int initialYaw = tello.getAttitudeYaw();
        int yawDiff = 0 - initialYaw;

        if(yawDiff > 0) {
            tello.turnCW(yawDiff);
        }
        else {
            int ccDiff = -yawDiff;
            tello.turnCW(ccDiff);
        }

        */
        if (width < 0) {
            tello.flyLeft(-1 * width);
        } else if (width == 0){
            tello.flyForward(width);
        } else {
            tello.flyRight(width);
        }

        if (length < 0) {
            tello.flyBackward(-1 * length);
        } else {
            tello.flyForward(length);
        }


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
