import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.ws.rs.core.MediaType;

import java.io.*;


public class TempPutHelper {
	
	public static void tempPutter(String inputTemp){
		
		try {
			
			URL uri = new URL("http://sample-env.hkje8xcpmh.us-west-2.elasticbeanstalk.com/TempConv/CtoF/update_temp");
			HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("PUT");
			conn.setRequestProperty("Content-Type", MediaType.TEXT_PLAIN);

			
			OutputStream os = conn.getOutputStream();
			os.write(inputTemp.getBytes());
			os.flush();
			
			if(conn.getResponseCode() != HttpURLConnection.HTTP_CREATED){
				throw new RuntimeException("Failed : HTTP Error : " + conn.getResponseCode());
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String output;
			System.out.println("Output from server: \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			conn.disconnect();	
			
			
		}catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }
	}
	

}
