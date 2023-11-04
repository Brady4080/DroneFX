package main.java.surelyhuman.jdrone.control.physical.tello.demos;

import java.io.IOException;

import main.java.surelyhuman.jdrone.control.physical.tello.TelloDrone;

public class TelloCameraStreamRecordDemo {

	public static void record() throws IOException, InterruptedException {
		TelloDrone tello = new TelloDrone();
		tello.activateSDK();
		tello.streamOn();
		tello.streamRecordOn();
		tello.hoverInPlace(60);
		tello.streamOff();
		tello.streamRecordOff();
		tello.end();
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		record();
		System.exit(0);
	}
}
