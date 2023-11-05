package main.java.surelyhuman.jdrone.control.physical.tello;

import java.io.IOException;

import main.java.surelyhuman.jdrone.control.physical.tello.TelloDrone;

public class ScanFarm {
    public static int width;
    public static int length;
    public static int height;
    public static int ccWidth;
    public static int ccLength;
    public static int ccHeight;

    public static void getDist(int width, int length, int height) {
        ScanFarm.width = width;
        ScanFarm.length = length;
        ScanFarm.height = height;
    }

    public static void ccLocation(int ccWidth, int ccLength, int ccHeight){
        ScanFarm.ccWidth = ccWidth;
        ScanFarm.ccLength = ccLength;
        ScanFarm.ccHeight = ccHeight;
    }

    public static void flight() throws InterruptedException, IOException {
        TelloDrone tello = new TelloDrone();
        tello.activateSDK();
        tello.takeoff();

        if (width < 0) {
            tello.flyLeft((-1 * width) - 100);
        } else if (width == 0){
            tello.flyForward(width);
        } else {
            tello.flyRight(width - 100);
        }

        if (length < 0) {
            tello.flyBackward((-1 * length) - 100);
        } else {
            tello.flyForward(length - 100);
        }

        //pass 1
        tello.flyForward(400);
        tello.flyLeft(100);
        tello.turnCW(180);

        // pass 2
        tello.flyForward(400);
        tello.flyRight(100);
        tello.turnCW(180);

        // pass 3
        tello.flyForward(400);
        tello.flyLeft(100);
        tello.turnCW(180);

        // pass 4
        tello.flyForward(400);
        tello.flyRight(100);
        tello.turnCW(180);

        //pass 5
        tello.flyForward(400);
        tello.flyLeft(100);
        tello.turnCW(180);

        // pass 6
        tello.flyForward(400);
        tello.turnCW(180);

        // 700 500 0
        // Return to command center

        double ccLocWtemp = ccWidth - 700;
        double ccLocLtemp = 500 - ccLength;

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

