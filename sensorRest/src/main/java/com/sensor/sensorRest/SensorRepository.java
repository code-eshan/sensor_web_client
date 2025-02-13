package com.sensor.sensorRest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;

//Sensor Repository which deals with the back-end operations
public class SensorRepository {

	// Find your Account Sid and Token at twilio.com/user/account
	public static final String ACCOUNT_SID = "AC4e0e176e52c013ba033f1d82c1104799";
	public static final String AUTH_TOKEN = "018a0e9b17cf947ccd0d1de939ef081e";

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
				s.setStatus(rs.getString(2));
				s.setFloorNum(rs.getInt(3));
				s.setRoomNum(rs.getInt(4));
				s.setsLevel(rs.getInt(5));
				s.setcLevel(rs.getInt(6));

				// When a sensor has high smoke or co2 level the server automatically sends
				// email
				// to the recipient address.
				if (s.getsLevel() > 5 || s.getcLevel() > 5) {

					// sendMail function sends email to the recipient with the parameter
					// id of the sensor.
					sendMail("fashionhubafproject@gmail.com", s.getId());

					// sendSMS function sends sms to the recipient with the parameter
					// id of the sensor
					sendSMS(s.getId());

				}

				// objects are added inside the sensors list
				sensors.add(s);
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		// the list is returned
		return sensors;
	}

	//Method to add the sensor details
	public void add(Sensor sensor) throws SQLException {

		String query = "insert into sensor (id,status,floorNum,roomNum,sLevel,cLevel) values (?,?,?,?,?,?)";
		PreparedStatement stm = (PreparedStatement) con.prepareStatement(query);
		stm.setInt(1, sensor.getId());
		stm.setString(2, sensor.isStatus());
		stm.setInt(3, sensor.getFloorNum());
		stm.setInt(4, sensor.getRoomNum());
		stm.setInt(5, sensor.getsLevel());
		stm.setInt(6, sensor.getcLevel());
		stm.execute();

		stm.close();
//				
	}

	//method to update the sensor details
	public void update(Sensor newSensor) throws SQLException {

		String query1 = "Update sensor set id = '" + newSensor.getId() + "', floorNum = '" + newSensor.getFloorNum()
				+ "', roomNum = '" + newSensor.getRoomNum() + "', sLevel = '" + newSensor.getsLevel() + "', cLevel = '"
				+ newSensor.getsLevel() + "' where id = '" + newSensor.getId() + "' ";
		PreparedStatement stm = (PreparedStatement) con.prepareStatement(query1);

		stm.execute();

		stm.close();

	}

	//Method to delete the sensor details
	public void delete(int id) throws SQLException {
		String query2 = "delete from sensor where id = '" + id + "' ";
		PreparedStatement stm = (PreparedStatement) con.prepareStatement(query2);

		stm.execute();

		stm.close();

	}

	//Method to check the details
	public void check() throws InterruptedException, SQLException {
		int i;
		for (i = 0; i >= 0; i++) {
			Thread.sleep(10000);

			String query = "select *  from sensor";

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {

				int num1 = Integer.parseInt(rs.getString("sLevel"));
				int num2 = Integer.parseInt(rs.getString("cLevel"));

				if (num1 > 5 || num2 > 5) {

					String query1 = "Update sensor set status = 'Active' where id = '" + rs.getString("id") + "' ";

					PreparedStatement stm1 = (PreparedStatement) con.prepareStatement(query1);

					stm1.execute();

					i = -1;
				} else {

					String query3 = "Update sensor set status = 'Inactive' where id = '" + rs.getString("id") + "' ";

					PreparedStatement stm3 = (PreparedStatement) con.prepareStatement(query3);

					stm3.execute();

				}

			}

		}

	}

	// method that helps to send sms.
	public void sendSMS(int sensorID) {

		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

		com.twilio.rest.api.v2010.account.Message message = com.twilio.rest.api.v2010.account.Message
				.creator(new PhoneNumber("+94775751416"), new PhoneNumber("+12057406151"),
						"CAUTION !!! SENSOR " + sensorID + " is showing high smoke or CO2 Level.")
				.create();

		System.out.println(message.getSid());

		System.out.println("DONE SENT SMS");
	}

	// method that helps to send email.
	public static void sendMail(String recipient, int sensorID) {
		System.out.println("Preparing to send email.");

		// A properties object where properties are put inside to access with the
		// session
		Properties properties = new Properties();

		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");

		final String myAccountEmail = "fashionhubafproject@gmail.com";
		final String password = "Fashion@hub123";

		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myAccountEmail, password);
			}

		});

		// preparing message that is going to be accessed with the transport method
		Message message = prepareMessage(session, myAccountEmail, recipient, sensorID);

		try {
			Transport.send(message);
			System.out.println("Message sent successfully.");
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	// Prepare message method
	private static Message prepareMessage(Session session, String myAccountEmail, String recipient, int sensorID) {
		Message message = new MimeMessage(session);
		try {

			// From address, recipient address, subject and the body is set and then
			// returned
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setSubject("CAUTION SENSOR " + sensorID + " IS AT RISK!");
			message.setText(
					"Hey, \nSensor " + sensorID + " is at risk!! \nDetected high smoke or co2 level.\nThank You.\n\n"
							+ "~THIS IS AN AUTOGENERATED REPLY FROM THE SERVER DO NOT REPLY~");
			return message;

		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
