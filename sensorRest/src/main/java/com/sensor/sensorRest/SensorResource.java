package com.sensor.sensorRest;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//path is set as sensors
@Path("sensors")
public class SensorResource {

	// A sensor repository object to access the repository
	SensorRepository repo = new SensorRepository();

	// This is a GET request method which gives data type in a JSON format
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Sensor> getSensors() {

		System.out.println("Get Sensor Called.....");

		// accesses the method getSensors() in the repository
		return repo.getSensors();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public void addSensor(Sensor newSensor) throws SQLException {
		repo.add(newSensor);

	}
	
	@Path("{id}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public void updateSensor(Sensor newSensor) throws SQLException {
		repo.update(newSensor);

	}
	
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteSensor(@PathParam("id") int id) throws SQLException {
		
		repo.delete(id);
	}

	@Path("check")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public void ckeckSensor() throws SQLException, InterruptedException {
		
		repo.check();
	}
	

}
