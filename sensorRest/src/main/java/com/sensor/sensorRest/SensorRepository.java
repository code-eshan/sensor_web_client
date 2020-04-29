package com.sensor.sensorRest;

import java.util.ArrayList;
import java.sql.*;
import java.util.List;

//Sensor Repository which deals with the back-end operations
public class SensorRepository {

	// Connection object that handles the connection for the database
	Connection con = null;

	public SensorRepository() {

		// Attributes that has URL User-name and Password which helps
		// to connect with the database
		String url = "jdbc:mysql://localhost:3306/sensordb";
		String username = "root";
		String password = "";

		try {

			// Access for JDBC Driver
			Class.forName("com.mysql.jdbc.Driver");
			// Connect to the database
			con = DriverManager.getConnection(url, username, password);

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	// Method which returns the sensor lists
	public List<Sensor> getSensors() {

		// Sensor lists objects
		List<Sensor> sensors = new ArrayList<>();

		// SQL query
		String sql = "select * from sensor";
		try {

			// statement object
			Statement st = con.createStatement();
			// query gets executed and the results get passed onto the ResultSet object
			ResultSet rs = st.executeQuery(sql);

			// each records are accessed and set into an object
			while (rs.next()) {
				Sensor s = new Sensor();
				s.setId(rs.getInt(1));
				s.setStatus(rs.getBoolean(2));
				s.setFloorNum(rs.getInt(3));
				s.setRoomNum(rs.getInt(4));
				s.setsLevel(rs.getInt(5));
				s.setcLevel(rs.getInt(6));

				// objects are added inside the sensors list
				sensors.add(s);
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		// the list is returned
		return sensors;
	}

	
	  public Sensor getSensor(int id) {
	  
	  String sql = "select * from sensor where id="+ id; Sensor s = new Sensor();
	  try {
	  
	  Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql);
	  if(rs.next()) {
	  
	  s.setId(rs.getInt(1)); s.setStatus(rs.getBoolean(2));
	  s.setFloorNum(rs.getInt(3)); s.setRoomNum(rs.getInt(4));
	  s.setsLevel(rs.getInt(5)); s.setcLevel(rs.getInt(6));
	  
	  
	  }
	  
	  } catch (Exception e) { System.out.println(e); }
	  
	  return s;
	  
	  }
	  
	  public void create(Sensor s1) {
	  
	  String sql = "insert into sensor values (?,?,?,?,?,?)";
	  
	  try {
	  
	  PreparedStatement st = con.prepareStatement(sql);
	  
	  st.setInt(1, s1.getId()); st.setBoolean(2, s1.isStatus()); st.setInt(3,
	  s1.getFloorNum()); st.setInt(4, s1.getRoomNum()); st.setInt(5,
	  s1.getsLevel()); st.setInt(6, s1.getcLevel());
	  
	  st.executeUpdate();
	  
	  
	  
	  } catch (Exception e) { System.out.println(e); } }

}
