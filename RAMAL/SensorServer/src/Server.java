import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;


public class Server extends UnicastRemoteObject implements ServerInt {
	
	
	

	
	protected Server() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public void addItem(Sensor sensor) throws Exception {
		
		
		
		final String POST_PARAMS = 
				"{\n" + "\"id\": "+sensor.id+",\r\n" +
		        "    \"floorNum\": "+sensor.floorNum+",\r\n" +
		        "    \"roomNum\": "+sensor.roomNum+",\r\n" +
		        "    \"sLevel\":"+sensor.sLevel+",\r\n" +
		        "    \"cLevel\": "+sensor.cLevel+"" + "\n}";
		    System.out.println(POST_PARAMS);
		    
		    URL obj = new URL("http://localhost:8080/sensor_service/rest/sensors/");
		    HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
		    postConnection.setRequestMethod("POST");
		 
		    postConnection.setRequestProperty("Content-Type", "application/json");
		    postConnection.setDoOutput(true);
		    OutputStream os = postConnection.getOutputStream();
		    os.write(POST_PARAMS.getBytes());
		    os.flush();
		    os.close();
		    int responseCode = postConnection.getResponseCode();
		    System.out.println("POST Response Code :  " + responseCode);
		    System.out.println("POST Response Message : " + postConnection.getResponseMessage());
		    int req=HttpURLConnection.HTTP_CREATED ;
		    
		    if (responseCode == (req-1)) { //success
		        BufferedReader in = new BufferedReader(new InputStreamReader(
		            postConnection.getInputStream()));
		        String inputLine;
		        StringBuffer response = new StringBuffer();
		        while ((inputLine = in .readLine()) != null) {
		            response.append(inputLine);
		        } in .close();
		        // print result
		        System.out.println(response.toString());
		        
          
                
		    } else {
		        System.out.println("Can not Insert");
		    }
			

	}

	public ArrayList<Sensor> getItems() throws Exception {
		
		ArrayList<Sensor> faList = new ArrayList<Sensor>();
        JSONArray myResponse ;
        try {
        	String url = "http://localhost:8080/sensor_service/rest/sensors/";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            // optional default is GET
            con.setRequestMethod("GET");
            //add request header
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
            	response.append(inputLine);
            }
            in.close();
            
            //print in String
            System.out.println(response.toString());
            
            //Read JSON response and print JSONArray 
          // JSONObject myResponse = new JSONObject(response.toString());
              myResponse = new JSONArray(response.toString());
            System.out.println("result after Reading JSON Response");
           
            JSONObject sensor;
                
                for (int i = 0; i < myResponse.length(); i++) {
                    sensor =  myResponse.getJSONObject(i);
                   
                    Sensor currentSensor = new Sensor();
                    
                    currentSensor.setId(Integer.valueOf(sensor.getInt("id")));
                    currentSensor.setStatus(sensor.getString("status"));
                    currentSensor.setFloorNum(Integer.valueOf(sensor.getInt("floorNum")));
                    currentSensor.setRoomNum(Integer.valueOf(sensor.getInt("roomNum")));
                    currentSensor.setsLevel(Integer.valueOf(sensor.getInt("sLevel")));
                    currentSensor.setcLevel(Integer.valueOf(sensor.getInt("cLevel")));
                   
                    faList.add(currentSensor);
                  
                }
               
		} catch (Exception e) {
			// TODO: handle exception
		}
	
		return faList;
	}
	
	public void updateItem(Sensor sensor) throws Exception {
		
		ArrayList<Sensor> faList = new ArrayList<Sensor>();
		final String POST_PARAMS = 
				"{\n" + "\"id\": "+sensor.id+",\r\n" +
		        "    \"floorNum\": "+sensor.floorNum+",\r\n" +
		        "    \"roomNum\": "+sensor.roomNum+",\r\n" +
		        "    \"sLevel\":"+sensor.sLevel+",\r\n" +
		        "    \"cLevel\": "+sensor.cLevel+"" + "\n}";
		    System.out.println(POST_PARAMS);
		    URL obj = new URL("http://localhost:8080/sensor_service/rest/sensors/"+sensor.id);
		    HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
		    postConnection.setRequestMethod("DELETE");

		    postConnection.setRequestProperty("Content-Type", "application/json");
		    postConnection.setDoOutput(true);
		    OutputStream os = postConnection.getOutputStream();
		    os.write(POST_PARAMS.getBytes());
		    os.flush();
		    os.close();
		    int responseCode = postConnection.getResponseCode();
		    System.out.println("POST Response Code :  " + responseCode);
		    System.out.println("POST Response Message : " + postConnection.getResponseMessage());
		    int req=HttpURLConnection.HTTP_CREATED ;
		    
		    if (responseCode == (req-1)) { //success
		        BufferedReader in = new BufferedReader(new InputStreamReader(
		            postConnection.getInputStream()));
		        String inputLine;
		        StringBuffer response = new StringBuffer();
		        while ((inputLine = in .readLine()) != null) {
		            response.append(inputLine);
		        } in .close();
		        
		        // print result
		        System.out.println(response.toString());
		       
	}
	
	
		
		 
	}	 
		 public void checkSensor() throws Exception {
			 String url = ("http://localhost:8080/sensor_service/rest/sensors/check");
		        URL obj = new URL(url);
		        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		        // optional default is GET
		        con.setRequestMethod("GET");
		        //add request header
		        con.setRequestProperty("User-Agent", "Mozilla/5.0");
		        int responseCode = con.getResponseCode();
		        System.out.println("\nSending 'GET' request to URL : " + url);
		        System.out.println("Response Code : " + responseCode);
		        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		        String inputLine;
		        StringBuffer response = new StringBuffer();
		        while ((inputLine = in.readLine()) != null) {
		        	response.append(inputLine);
		        }
		        in.close();
		        
		        //print in String
		        System.out.println(response.toString());
				// TODO Auto-generated method stub
				
			}
			
			
			
			
	public static void main(String[] args) throws Exception {
System.out.println("Server is started");
		
System.setProperty("java.security.policy", "file:allowall.policy");
		
		Server Hello = new Server();			   		   
		   Naming.rebind("ADD", Hello);
		 
		 System.out.println("Server is running");
	}

	public void deleteItem(int id) throws Exception {
		
		String url = ("http://localhost:8080/sensor_service/rest/sensors/"+id);
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
        	response.append(inputLine);
        }
        in.close();
        
        //print in String
        System.out.println(response.toString());
		// TODO Auto-generated method stub
		
	}



	

}
