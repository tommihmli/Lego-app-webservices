package threads;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;

import data.Robot;
            


public class SendData implements Runnable {
    URL url = null;
	HttpURLConnection conn = null;
	InputStreamReader isr = null;
	BufferedReader br=null;

	String s=null;
    @Override
    public void run() {
	while (true) {
        try {
            
            float v = ReadViiva.getVari();
            if(Float.isInfinite(v)|| Float.isNaN(v)){
                v=0;
            }
            float sy = ReadEdge.getSyvyys();
            if(Float.isInfinite(sy)|| Float.isNaN(sy)){
                sy=0;
            }
            float df = ReadDistance.getDistanceForward();
            if(Float.isInfinite(df) || Float.isNaN(df)){
                df=0;
            }
            
            
            url = new URL("http://192.168.0.13:8080/rest/lego/setvalues/"
            +Robot.getRun() + "/"
            +Robot.getSpeed() + "/"
            +Robot.getTurn() + "/" 
            + v + "/"
            + sy + "/"
            + df); 

            conn = (HttpURLConnection)url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type","application/json");
            conn.setDoOutput(true);


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
            br = new BufferedReader(isr);

                while ((s = br.readLine()) != null) {
                System.out.println(s);
                }
                br.close();
				isr.close();
				is.close();
            conn.disconnect();
			Thread.sleep(500);
        }

        catch(Exception e) {

            e.printStackTrace();

            System.out.println("Some problem!");

        }
    }
}
}