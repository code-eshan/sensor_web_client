import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.awt.Color;

public class AddAlarm extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					//frame.dispose();
					AddAlarm frame = new AddAlarm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddAlarm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 860, 557);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add New Sensor");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setBounds(144, 13, 448, 49);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Sensor ID:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(106, 97, 137, 36);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Floor Number");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(106, 175, 137, 36);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Room Number");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(129, 254, 100, 29);
		contentPane.add(lblNewLabel_3);
		
		
		JLabel lblSmokeLevel = new JLabel("Smoke Level");
		lblSmokeLevel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSmokeLevel.setHorizontalAlignment(SwingConstants.CENTER);
		lblSmokeLevel.setBounds(90, 315, 180, 36);
		contentPane.add(lblSmokeLevel);
		
		JLabel lblCoLevel = new JLabel("Co2 Level");
		lblCoLevel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCoLevel.setHorizontalAlignment(SwingConstants.CENTER);
		lblCoLevel.setBounds(106, 381, 151, 36);
		contentPane.add(lblCoLevel);
		
		
		textField = new JTextField();
		textField.setBounds(374, 98, 213, 36);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(374, 176, 213, 36);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(374, 251, 213, 36);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(379, 375, 213, 36);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(374, 315, 213, 36);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		JButton btnNewButton = new JButton("Add Sensor");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
			
				
				Sensor sen = new Sensor();
				sen.setId(Integer.parseInt(textField.getText()));
				sen.setStatus("Inactive");
				sen.setFloorNum(Integer.parseInt(textField_1.getText()));
				sen.setRoomNum(Integer.parseInt(textField_2.getText()));
				sen.setsLevel(Integer.parseInt(textField_3.getText()));
				sen.setcLevel(Integer.parseInt(textField_4.getText()));
				
				try {
					Server cp = new Server();
					
					cp.addItem(sen);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(422, 450, 130, 36);
		contentPane.add(btnNewButton);
		
		JButton btnUpdateSensor = new JButton("Update Sensor");
		btnUpdateSensor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				Sensor sen = new Sensor();
				sen.setId(Integer.parseInt(textField.getText()));
				
				sen.setFloorNum(Integer.parseInt(textField_1.getText()));
				sen.setRoomNum(Integer.parseInt(textField_2.getText()));
				sen.setsLevel(Integer.parseInt(textField_3.getText()));
				sen.setcLevel(Integer.parseInt(textField_4.getText()));
				
				try {
					Server cp1 = new Server();
					
					cp1.updateItem(sen);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnUpdateSensor.setBounds(564, 451, 122, 35);
		contentPane.add(btnUpdateSensor);
		
		JButton btnDeleteSensor = new JButton("Delete Sensor");
		btnDeleteSensor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
			
				try {
					Server cp = new Server();
					cp.deleteItem(Integer.parseInt(textField.getText()));
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
		
			}
		});
		btnDeleteSensor.setBounds(698, 450, 122, 36);
		contentPane.add(btnDeleteSensor);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				Login h = new Login();
				
				h.main(null);
			
			
			}
		});
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLogout.setBounds(678, 78, 112, 43);
		contentPane.add(btnLogout);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_4.setForeground(new Color(255, 0, 0));
		lblNewLabel_4.setBounds(27, 417, 334, 67);
		contentPane.add(lblNewLabel_4);

		
		


	}
	

}
