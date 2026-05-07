package threads;
import data.*;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.hardware.port.SensorPort;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ReadDistance implements Runnable {

    EV3UltrasonicSensor usSensorA = new EV3UltrasonicSensor(SensorPort.S1);
    SampleProvider distance = usSensorA.getDistanceMode();
    float[] usSample = new float[distance.sampleSize()];

    private static float distanceForward;
    private static boolean turnAway;


@Override
public void run(){

    while(Robot.getRun()==1){

        distance.fetchSample(usSample,0);
        distanceForward = usSample[0];
        turnAway = distanceForward < 0.30f;

        if(turnAway){
            RunLego.peruuta();
            System.out.println("Esine edessä!" + distanceForward);
        }
        else{
            Robot.getSpeed();
        }

        try{
            if(turnAway){
                Thread.sleep(1000);
            }
            else{
                Thread.sleep(1000);
            }
        } catch(InterruptedException e){
            e.printStackTrace();
        }



           try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
        }
    }

    

        public static float getDistanceForward(){
            return distanceForward;
        }

        public static void setDistanceForward(float distanceForward){
            ReadDistance.distanceForward=distanceForward;
        }
        public static void setDistanceForward(String distanceForward){
            try{
                ReadDistance.distanceForward=Float.parseFloat(distanceForward);
            }
            catch(Exception e){

            }
    }
}
