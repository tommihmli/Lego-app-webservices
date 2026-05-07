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

public class ReadEdge implements Runnable{

    EV3UltrasonicSensor usSensorB = new EV3UltrasonicSensor(SensorPort.S3);
    SampleProvider syva = usSensorB.getDistanceMode();
    float[] usBSample = new float[syva.sampleSize()];

    private static float syvyys;
    private static boolean peruutus;

    @Override
    public void run(){

        while(Robot.getRun()==1){
            usSensorB.fetchSample(usBSample,0);
            syvyys = usBSample[0];
            peruutus = 0.07f < syvyys;


            if(peruutus){
                RunLego.peruuta();
                System.out.println("Reuna!" + syvyys);
            }
            else{
                Robot.getSpeed();
            }

            try{
                if(peruutus){
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
    

        public static float getSyvyys(){
            return syvyys;
        }

        public static void setSyvyys(float syvyys){
            ReadEdge.syvyys=syvyys;
        }

        public static void setSyvyys(String syvyys){
            try{
                ReadEdge.syvyys=Float.parseFloat(syvyys);
            }
            catch(Exception e){

            }
        }
    
}