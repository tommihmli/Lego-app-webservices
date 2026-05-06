package threads;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import data.Robot;

public class SendData implements Runnable {
    URL url = null;
	HttpURLConnection conn = null;
	InputStreamReader isr = null;
	BufferedReader br=null;

	String s=null;
    @Override
    public void run() {

        try {

            url = new URL("http://10.65.120.31:8080/rest/lego/setvalues/"+Robot.getRun()+"/"+Robot.getSpeed()+"/"+Robot.getTurn());

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
            br = new BufferedReader(isr);

                while ((s = br.readLine()) != null) {
                System.out.println(s);
                }
                br.close();
				isr.close();
				is.close();
            conn.disconnect();

        }

        catch(Exception e) {

            e.printStackTrace();

            System.out.println("Some problem!");

        }
}
}
