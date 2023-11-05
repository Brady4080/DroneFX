package main.java.surelyhuman.jdrone.control.physical.tello.demos;

import main.java.surelyhuman.jdrone.control.physical.tello.TelloDrone;

import java.io.IOException;

public class TelloFlightDemo2 {

	public static void flight() throws InterruptedException, IOException {
		TelloDrone tello = new TelloDrone();
		tello.activateSDK();
		tello.takeoff();

		tello.flyForward(50);
		tello.flyLeft(50);
		tello.turnCW(180);

		// pass 2
		tello.flyForward(50);
		tello.flyRight(50);
		tello.turnCW(180);

		// pass 3
		tello.flyForward(50);
		tello.flyLeft(50);
		tello.turnCW(180);

		// pass 4
		tello.flyForward(50);
		tello.flyRight(50);
		tello.turnCW(180);

		tello.end();
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		flight();
		System.exit(0);
	}

}
