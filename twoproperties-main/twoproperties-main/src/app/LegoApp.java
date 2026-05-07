package app;

import threads.*;

public class LegoApp {

	public static void main(String[] args) {

		RunLego runLego = new RunLego();
		ReadData readData = new ReadData();
		ReadDistance readDistance = new ReadDistance();
		ReadEdge readEdge = new ReadEdge();
		ReadViiva readViiva = new ReadViiva();
		SendData sendData = new SendData();
		

		System.out.println("Run in Threads");

		Thread runLegoThread = new Thread(runLego);
		Thread readDataThread = new Thread(readData);
		Thread readDistanceThread = new Thread(readDistance);
		Thread readEdgeThread = new Thread(readEdge);
		Thread readViivaThread = new Thread(readViiva);
		Thread sendDataThread = new Thread(sendData);
		

		runLegoThread.start(); //Saikeen kaynnistys
		readDataThread.start(); //Virtuaalikone aloittaa saikeen kun ehtii
		readDistanceThread.start();
		readEdgeThread.start();
		readViivaThread.start();
		sendDataThread.start();
		

		

	}
}
