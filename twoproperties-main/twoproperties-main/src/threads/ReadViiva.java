package threads;
import data.*;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.hardware.port.SensorPort;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ReadViiva implements Runnable{

    EV3ColorSensor lSensor = new EV3ColorSensor(SensorPort.S2);
    SampleProvider viiva = lSensor.getRedMode();
    float[] lSample = new float[viiva.sampleSize()];

    private static float vari;
    private static boolean vaista;

    @Override
    public void run(){

        while(Robot.getRun()==1){
            viiva.fetchSample(lSample,0);
            vari = lSample[0];
            vaista = vari < 0.3f;

            if(vaista){
                RunLego.peruuta();
                System.out.println("Viiva! " + vari);
            }
            else{
                Robot.getSpeed();
            }

            try{
                if(vaista){
                    Thread.sleep(1000);
                }
                else{
                    Thread.sleep(1000);
                }
            } 
            catch(InterruptedException e){
                e.printStackTrace();
            }

            
            try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

        }
    }

    public static float getVari(){
        return vari;
    }

    public static void setVari(float vari){
        ReadViiva.vari=vari;
    }

    public static void setVari(String vari){
        try{
            ReadViiva.vari=Float.parseFloat(vari);
        }
        catch(Exception e){

        }
    }

}