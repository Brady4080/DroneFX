package main.java.surelyhuman.jdrone.control.physical.tello;

import java.io.IOException;

import main.java.surelyhuman.jdrone.control.physical.tello.TelloDrone;


public class TelloFlight {

    public static int width;
    public static int length;
    public static int height;
    public static int ccWidth;
    public static int ccLength;
    public static int ccHeight;
    public static int tdWidth;
    public static int tdLength;
    public static int tdHeight;

    public static void getDist(int width, int length, int height) {
        TelloFlight.width = width;
        TelloFlight.length = length;
        TelloFlight.height = height;
    }

    public static void ccLocation(int ccWidth, int ccLength, int ccHeight){
        TelloFlight.ccWidth = ccWidth;
        TelloFlight.ccLength = ccLength;
        TelloFlight.ccHeight = ccHeight;
    }

    public static void telloDest(int tdWidth, int tdLength, int tdHeight){
        TelloFlight.tdWidth = tdWidth;
        TelloFlight.tdLength = tdLength;
        TelloFlight.tdHeight = tdHeight;
    }

    public static void flight() throws InterruptedException, IOException {
        TelloDrone tello = new TelloDrone();
        tello.activateSDK();
        tello.takeoff();

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

        tello.turnCW(90);
        tello.turnCW(90);
        tello.turnCW(90);
        tello.turnCW(90);

        // Return to command center

        double ccLocWtemp = ccWidth - tdWidth;
        double ccLocLtemp = tdLength - ccLength;

        int ccLocW = (int) ccLocWtemp;
        int ccLocL = (int) ccLocLtemp;

        if (ccLocW < 0) {
            tello.flyLeft(-1 * ccLocW);
        } else if (ccLocW == 0){
            tello.flyForward(ccLocW);
        } else {
            tello.flyRight(ccLocW);
        }

        if (ccLocL < 0) {
            tello.flyBackward(-1 * ccLocL);
        } else {
            tello.flyForward(ccLocL);
        }

        tello.land();
        tello.end();
    }


    public static void main(String[] args) throws InterruptedException, IOException {
        flight();
        System.exit(0);
    }

}
