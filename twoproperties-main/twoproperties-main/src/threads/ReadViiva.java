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

    URL url=null;
    URL url2=null;
    HttpURLConnection conn = null;
    InputStreamReader isr = null;
    BufferedReader br=null;
    HttpURLConnection conn2 = null;
    InputStreamReader isr2 = null;
    BufferedReader br2=null;

    String s=null;

    private static float vari;
    private static boolean vaista;

    @Override
    public void run(){

        while(Robot.getRun()==1){
            viiva.fetchSample(lSample,0);
            vari = lSample[0];
            vaista = vari < 0.3f;

            try{
                if(vaista){
                    Thread.sleep(100);
                }
                else{
                    Thread.sleep(100);
                }
            } catch(InterruptedException e){
                e.printStackTrace();
            }

            try{
                url = new URL("http://192.168.0.13:8080/rest/lego/getvalues/"); //+Robot.getRun()+"/"+Robot.getSpeed()+"/"+Robot.getTurn()
                conn = (HttpURLConnection)url.openConnection();
                InputStream is=null;
                try{
                    is=conn.getInputStream();
                }
                catch(Exception e){
                    System.out.println("Exception conn.getInputSteam()");
                    e.printStackTrace();
                    System.out.println("Cannot get InputStream!");
               }
                isr = new InputStreamReader(is);
                br = new BufferedReader(isr);
                while((s=br.readline())!=null){
                    String [] values=s.split("#");
                    setVari(values[0]);
                }

                br.close();
                isr.close();
                is.close();
                conn.disconnect();
            }
            catch(Exception e){
                e.printStackTrace();
                System.out.println("Some problem!");
            }
            /* try{
                url2 = new URL("http://192.168.0.13:8080/rest/lego/setvalues/");//+getVari()
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

    public static float getVari(){
        return vari;
    }

    public static void setVari(float vari){
        this.vari=vari;
    }
}