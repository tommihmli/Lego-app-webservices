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

    URL url = null;
    URL url2=null;
	HttpURLConnection conn = null;
	InputStreamReader isr = null;
	BufferedReader br=null;
    HttpURLConnection conn2 = null;
    InputStreamReader isr2 = null;
    BufferedReader br2=null;
    String l=null;

    private static float syvyys;
    private static boolean peruutus;

    @Override
    public void run(){

        while(Robot.getRun()==1){
            usSensorB.fetchSample(usBSample,0);
            syvyys = usBSample[0];
            peruutus = 0.20f < syvyys;


            if(peruutus){
                Robot.turnLeft();
            }
            else{
                Robot.getSpeed();
            }

            try{
                if(peruutus){
                    Thread.sleep(100);
                }
                else{
                    Thread.sleep(100);
                }
            } catch(InterruptedException e){
                e.printStackTrace();
            }

            try{
                url = new URL("http://192.168.0.13:8080/rest/lego/getvalues/");//+Robot.getRun()+"/"+Robot.getSpeed()+"/"+Robot.getTurn()
                conn = (HttpURLConnection)url.openConnection();
                InputStream is=null;
                try {
                    is=conn.getInputStream();
                }
                catch (Exception e) {
                    System.out.println("Exception conn.getInputSteam()");
                    e.printStackTrace();
                    System.out.println("Cannot get InputStream!");
                }
                isr = new InputStreamReader(is);
	      		br=new BufferedReader(isr);
				while ((l=br.readLine())!=null){
					String [] values=l.split("#");
					setSyvyys(values[0]);
                }
                br.close();
				isr.close();
				is.close();
				conn.disconnect();
            }catch (Exception e) {
                e.printStackTrace();
                System.out.println("Some problem!");
            }
            /* try{
                url2 = new URL("http://192.168.0.13:8080/rest/lego/setvalues/");//+getSyvyys()
                conn2 = (HttpURLConnection)url2.openConnection();
                InputStream is2=null;
                try{
                    is2=conn2.getInputStream();
                }
                catch(Exception e){
                    System.out.println("Exception conn2.getInputSteam()");
                    e.printStackTrace();
                    System.out.println("Cannot get InputStream!");
               }
                isr2 = new InputStreamReader(is2);
                br2 = new BufferedReader(isr2);

                br2.close();
                isr2.close();
                is2.close();
                conn2.disconnect();
            }
            catch(Exception e){
                e.printStackTrace();
                System.out.println("Some problem!");
            } */

           try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

        
        }   
    }

    public static float getSyvyys(){
        return syvyys;
    }

    public static void setSyvyys(float syvyys){
        this.syvyys=syvyys;
    }
}