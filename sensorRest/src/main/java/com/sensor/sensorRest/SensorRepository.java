package com.sensor.sensorRest;

import java.util.ArrayList;
import java.sql.*;
import java.util.List;

public class SensorRepository {

	
	Connection con = null;
	
	public SensorRepository() {
		
		String url = "jdbc:mysql://localhost:3306/sensordb";
		String username = "root";
		String password = "";
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url,username,password);
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	public List<Sensor> getSensors() {
		
		List<Sensor> sensors = new ArrayList<>();
		
		String sql = "select * from sensor";
		try {
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				Sensor s = new Sensor();
				s.setId(rs.getInt(1));
				s.setStatus(rs.getBoolean(2));
				s.setFloorNum(rs.getInt(3));
				s.setRoomNum(rs.getInt(4));
				s.setsLevel(rs.getInt(5));
				s.setcLevel(rs.getInt(6));
				
				sensors.add(s);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		
		return sensors;
	}
	
	public Sensor getSensor(int id) {
		
		String sql = "select * from sensor where id="+ id;
		Sensor s = new Sensor();
		try {
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				
				s.setId(rs.getInt(1));
				s.setStatus(rs.getBoolean(2));
				s.setFloorNum(rs.getInt(3));
				s.setRoomNum(rs.getInt(4));
				s.setsLevel(rs.getInt(5));
				s.setcLevel(rs.getInt(6));
				
				
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return s;
		
	}

	public void create(Sensor s1) {
		
		String sql = "insert into sensor values (?,?,?,?,?,?)";
		
		try {
			
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1, s1.getId());
			st.setBoolean(2, s1.isStatus());
			st.setInt(3, s1.getFloorNum());
			st.setInt(4, s1.getRoomNum());
			st.setInt(5, s1.getsLevel());
			st.setInt(6, s1.getcLevel());
			
			st.executeUpdate();
			
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
}
