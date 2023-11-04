package main.java.surelyhuman.jdrone.control.physical.tello.demos;

import java.io.IOException;

import main.java.surelyhuman.jdrone.control.physical.tello.TelloDrone;

public class TelloCameraStreamPlayDemo {

	private static void stream() throws IOException, InterruptedException  {
		TelloDrone tello = new TelloDrone();
		tello.activateSDK();
		tello.streamOn();
		tello.streamViewOn();
		tello.hoverInPlace(60);
		tello.streamOff();
		tello.streamViewOff();
		tello.end();
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		stream();
		System.exit(0);
	}
}
