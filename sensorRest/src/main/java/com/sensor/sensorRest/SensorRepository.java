package com.sensor.sensorRest;

import java.util.ArrayList;
import java.util.List;

public class SensorRepository {

	List<Sensor> sensors;
	
	public SensorRepository() {
		
		sensors = new ArrayList<>();
		
		Sensor s1 = new Sensor();
		s1.setId(1);
		s1.setStatus(false);
		s1.setFloorNum(1);
		s1.setRoomNum(100);
		s1.setsLevel(5);
		s1.setcLevel(7);
		
		Sensor s2 = new Sensor();
		s2.setId(2);
		s2.setStatus(true);
		s2.setFloorNum(2);
		s2.setRoomNum(200);
		s2.setsLevel(6);
		s2.setcLevel(8);
		
		sensors.add(s1);
		sensors.add(s2);
	}
	
	public List<Sensor> getSensors() {
		return sensors;
	}
	
	public Sensor getSensor(int id) {
		
		Sensor s1 = null;
		
		for(Sensor s : sensors) {
			if(s.getId() == id) {
				return s;
			}
		}
		
		return null;
	}

	public void create(Sensor s1) {
		
		sensors.add(s1);
		
	}
	
}
