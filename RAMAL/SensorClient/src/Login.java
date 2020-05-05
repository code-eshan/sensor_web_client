import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.awt.Color;
import java.awt.Font;


import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
public class Login {
	
//	private static Connection connection;

	private JFrame frame;
	private JTextField username;
	private JPasswordField password;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				
				
				showData();
			}
		});
		frame.setBounds(100, 100, 959, 571);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUserName.setBackground(Color.GRAY);
		lblUserName.setForeground(Color.BLACK);
		lblUserName.setBounds(0, 144, 148, 27);
		frame.getContentPane().add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassword.setBounds(12, 209, 109, 27);
		frame.getContentPane().add(lblPassword);
		
		username = new JTextField();
		username.setBounds(143, 144, 175, 38);
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(143, 204, 175, 38);
		frame.getContentPane().add(password);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLogin.setBackground(Color.CYAN);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			

//				
				String uname = username.getText();
				String pass = password.getText();
//				
				if(uname.equalsIgnoreCase("admin") && pass.equalsIgnoreCase("admin")){	
				
				frame.dispose();
				AddAlarm h = new AddAlarm();
				h.setVisible(true);
				try {
					Server s = new Server();
					s.checkSensor();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				JOptionPane.showConfirmDialog(frame, "Your successfully Login");
			
			}else {
					
					JOptionPane.showConfirmDialog(frame, "Invalid username or password");
					
		}				
				}
		});
		btnLogin.setBounds(143, 309, 135, 39);
		frame.getContentPane().add(btnLogin);
		
		JLabel lblNewLabel = new JLabel("Admin Login");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 43, 281, 50);
		frame.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(330, 43, 599, 434);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setForeground(new Color(255, 0, 0));
		lblNewLabel_1.setBounds(12, 383, 286, 100);
		frame.getContentPane().add(lblNewLabel_1);
		
		


	}
	
	
	private void showData() {
		
		
		try {
		
			
			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("Sensor ID");
			model.addColumn("Status");
			model.addColumn("Flow Num");
			model.addColumn("Room Num");
			model.addColumn("Smoke Level");
			model.addColumn("CO2 Level");
			
			Server s = new Server();
			ResultSet rs = (ResultSet) s.getItems();
			while(rs.next()) {
				
				
				model.addRow(new Object[] {
						rs.getString("id"),
						rs.getString("status"),
						rs.getString("floorNum"),
						rs.getString("roomNum"),
						rs.getString("sLevel"),
						rs.getString("cLevel")
				});
			}
			
			rs.close();
			
			table.setModel(model);
			table.setAutoResizeMode(0);
			table.getColumnModel().getColumn(0).setPreferredWidth(80);
			table.getColumnModel().getColumn(1).setPreferredWidth(120);
			table.getColumnModel().getColumn(2).setPreferredWidth(80);
			table.getColumnModel().getColumn(3).setPreferredWidth(80);
			table.getColumnModel().getColumn(4).setPreferredWidth(80);
			table.getColumnModel().getColumn(5).setPreferredWidth(80);
			
		}catch(Exception e) {
			
			System.out.println(e);
		}
		
		
	}
}
