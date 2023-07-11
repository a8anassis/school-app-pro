package gr.aueb.cf.schoolpro;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gr.aueb.cf.schoolpro.util.DBUtil;
import gr.aueb.cf.schoolpro.util.DateUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminUpdateDeleteStudentsForm extends JFrame {
	private static final long serialVersionUID = 123456;
	private JPanel contentPane;
	private JTextField idTxt;
	private JTextField firstnameTxt;
	private JTextField lastnameTxt;
	private JTextField birthDateTxt;
	
	private JComboBox<String> cityComboBox = new JComboBox<>();
	private JComboBox<String> userComboBox = new JComboBox<>();
	private Map<Integer, String> cities;
	private Map<Integer, String> usernames;
	private DefaultComboBoxModel<String> citiesModel;
	private DefaultComboBoxModel<String> usernamesModel;
	private ButtonGroup buttonGroup = new ButtonGroup(); 
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	private JRadioButton maleRdBtn;
	private JRadioButton femaleRdBtn;
	private JButton firstBtn;
	private JButton prevBtn;
	private JButton nextBtn;
	private JButton lastBtn;
	Connection conn = null;

	public AdminUpdateDeleteStudentsForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				String sql = "SELECT * FROM STUDENTS WHERE LASTNAME LIKE ?";
				// Connection conn = Login.getConnection();
				//Connection conn = null;
				try {
					conn = DBUtil.getConnection();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					//DBUtil.getConnection();
					//System.out.println("Search name" + Main.getStudentsMenu().getLastname().trim())
					ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
					ps.setString(1, Main.getStudentsMenu().getLastname() + "%");
					rs = ps.executeQuery();
					
					if (!rs.next()) {
						JOptionPane.showMessageDialog(null, "Empty", "Result", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
					
					
				
				PreparedStatement psCities;
				ResultSet rsCities;
			    try {
			    	String sqlCities = "SELECT * FROM CITIES";
			    	psCities = conn.prepareStatement(sqlCities);
		    		rsCities = psCities.executeQuery(sqlCities);
			    	cities = new HashMap<>();
			    	citiesModel = new DefaultComboBoxModel<>();
			    	
			    	while (rsCities.next()) {
			    		String city = rsCities.getString("CITY");
			    		int id = rsCities.getInt("ID");
			    		cities.put(id, city);
			    		citiesModel.addElement(city);
			    	}
			    	cityComboBox.setModel(citiesModel);
			    	cityComboBox.setMaximumRowCount(5);
			    	
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			    
			  
			    PreparedStatement psUsers;
			    ResultSet rsUsers;
			    try {
			    	    String sqlUsers = "SELECT ID, USERNAME FROM USERS";
					    psUsers = conn.prepareStatement(sqlUsers);
			    		rsUsers = psUsers.executeQuery(sqlUsers);
			    	usernames = new HashMap<>();
			    	usernamesModel = new DefaultComboBoxModel<>();
			    	
			    	while (rsUsers.next()) {
			    		String username = rsUsers.getString("USERNAME");
			    		int id = rsUsers.getInt("ID");
			    		usernames.put(id, username);
			    		usernamesModel.addElement(username);
			    	}
			    	userComboBox.setModel(usernamesModel);
			    	userComboBox.setMaximumRowCount(5);
			    	
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			    
			    try {
					idTxt.setText(Integer.toString(rs.getInt("ID")));
					firstnameTxt.setText(rs.getString("FIRSTNAME"));
					lastnameTxt.setText(rs.getString("LASTNAME"));
					if (rs.getString("GENDER").equals("M")) {
						maleRdBtn.setSelected(true);
					} else {
						femaleRdBtn.setSelected(true);
					}
					birthDateTxt.setText(DateUtil.toSQLDateString(rs.getDate("BIRTH_DATE")));
					cityComboBox.setSelectedItem(cities.get(rs.getInt("CITY_ID")));
					userComboBox.setSelectedItem(usernames.get(rs.getInt("USER_ID")));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		setTitle("Ενημέρωση / Διαγραφή");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 469, 545);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel idLbl = new JLabel("ID");
		idLbl.setForeground(new Color(128, 0, 0));
		idLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		idLbl.setBounds(92, 27, 17, 17);
		contentPane.add(idLbl);
		
		idTxt = new JTextField();
		idTxt.setBackground(new Color(252, 252, 205));
		idTxt.setEditable(false);
		idTxt.setBounds(124, 25, 59, 20);
		contentPane.add(idTxt);
		idTxt.setColumns(10);
		
		JLabel firstnameLbl = new JLabel("Όνομα");
		firstnameLbl.setForeground(new Color(128, 0, 0));
		firstnameLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		firstnameLbl.setBounds(60, 71, 49, 17);
		contentPane.add(firstnameLbl);
		
		firstnameTxt = new JTextField();
		firstnameTxt.setBounds(124, 69, 203, 19);
		contentPane.add(firstnameTxt);
		firstnameTxt.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Επώνυμο");
		lblNewLabel.setForeground(new Color(128, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(45, 115, 64, 14);
		contentPane.add(lblNewLabel);
		
		lastnameTxt = new JTextField();
		lastnameTxt.setBounds(124, 112, 203, 20);
		contentPane.add(lastnameTxt);
		lastnameTxt.setColumns(10);
		
		JLabel genderLbl = new JLabel("Φύλο");
		genderLbl.setForeground(new Color(128, 0, 0));
		genderLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		genderLbl.setBounds(72, 156, 37, 14);
		contentPane.add(genderLbl);
		
		maleRdBtn = new JRadioButton("Άνδρας");
		maleRdBtn.setBounds(124, 152, 71, 23);
		contentPane.add(maleRdBtn);
		
		femaleRdBtn = new JRadioButton("Γυναίκα");
		femaleRdBtn.setBounds(208, 154, 71, 23);
		contentPane.add(femaleRdBtn);
		
		buttonGroup.add(maleRdBtn);
		buttonGroup.add(femaleRdBtn);
		
		JLabel birthDateLbl = new JLabel("Ημ. Γέννησης");
		birthDateLbl.setForeground(new Color(128, 0, 0));
		birthDateLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		birthDateLbl.setBounds(10, 197, 99, 14);
		contentPane.add(birthDateLbl);
		
		birthDateTxt = new JTextField();
		birthDateTxt.setBounds(124, 194, 133, 20);
		contentPane.add(birthDateTxt);
		birthDateTxt.setColumns(10);
		
		JLabel cityLbl = new JLabel("Πόλη");
		cityLbl.setForeground(new Color(128, 0, 0));
		cityLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		cityLbl.setBounds(67, 238, 42, 14);
		contentPane.add(cityLbl);
		

		cityComboBox.setBounds(124, 235, 173, 22);
		contentPane.add(cityComboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setForeground(new Color(128, 0, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(38, 279, 71, 14);
		contentPane.add(lblNewLabel_1);
		
	
		userComboBox.setBounds(124, 275, 173, 22);
		contentPane.add(userComboBox);
		
		firstBtn = new JButton("");
		firstBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.first()) {
						idTxt.setText(Integer.toString(rs.getInt("ID")));
						firstnameTxt.setText(rs.getString("FIRSTNAME"));
						lastnameTxt.setText(rs.getString("LASTNAME"));
						if (rs.getString("GENDER").equals("M")) {
							maleRdBtn.setSelected(true);
						} else {
							femaleRdBtn.setSelected(true);
						}
						birthDateTxt.setText(DateUtil.toSQLDateString(rs.getDate("BIRTH_DATE")));
						cityComboBox.setSelectedItem(cities.get(rs.getInt("CITY_ID")));
						userComboBox.setSelectedItem(usernames.get(rs.getInt("USER_ID")));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		firstBtn.setIcon(new ImageIcon(AdminUpdateDeleteStudentsForm.class.getResource("/resources/First record.png")));
		firstBtn.setBounds(87, 350, 49, 23);
		contentPane.add(firstBtn);
		
		prevBtn = new JButton("");
		prevBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.previous()) {
						idTxt.setText(Integer.toString(rs.getInt("ID")));
						firstnameTxt.setText(rs.getString("FIRSTNAME"));
						lastnameTxt.setText(rs.getString("LASTNAME"));
						if (rs.getString("GENDER").equals("M")) {
							maleRdBtn.setSelected(true);
						} else {
							femaleRdBtn.setSelected(true);
						}
						birthDateTxt.setText(DateUtil.toSQLDateString(rs.getDate("BIRTH_DATE")));
						cityComboBox.setSelectedItem(cities.get(rs.getInt("CITY_ID")));
						userComboBox.setSelectedItem(usernames.get(rs.getInt("USER_ID")));
					} else {
						rs.first();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		prevBtn.setIcon(new ImageIcon(AdminUpdateDeleteStudentsForm.class.getResource("/resources/Previous_record.png")));
		prevBtn.setBounds(134, 350, 49, 23);
		contentPane.add(prevBtn);
		
		nextBtn = new JButton("");
		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.next()) {
						idTxt.setText(Integer.toString(rs.getInt("ID")));
						firstnameTxt.setText(rs.getString("FIRSTNAME"));
						lastnameTxt.setText(rs.getString("LASTNAME"));
						if (rs.getString("GENDER").equals("M")) {
							maleRdBtn.setSelected(true);
						} else {
							femaleRdBtn.setSelected(true);
						}
						birthDateTxt.setText(DateUtil.toSQLDateString(rs.getDate("BIRTH_DATE")));
						cityComboBox.setSelectedItem(cities.get(rs.getInt("CITY_ID")));
						userComboBox.setSelectedItem(usernames.get(rs.getInt("USER_ID")));
					} else {
						rs.last();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		nextBtn.setIcon(new ImageIcon(AdminUpdateDeleteStudentsForm.class.getResource("/resources/Next_track.png")));
		nextBtn.setBounds(179, 350, 49, 23);
		contentPane.add(nextBtn);
		
		lastBtn = new JButton("");
		lastBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.last()) {
						idTxt.setText(Integer.toString(rs.getInt("ID")));
						firstnameTxt.setText(rs.getString("FIRSTNAME"));
						lastnameTxt.setText(rs.getString("LASTNAME"));
						if (rs.getString("GENDER").equals("M")) {
							maleRdBtn.setSelected(true);
						} else {
							femaleRdBtn.setSelected(true);
						}
						birthDateTxt.setText(DateUtil.toSQLDateString(rs.getDate("BIRTH_DATE")));
						cityComboBox.setSelectedItem(cities.get(rs.getInt("CITY_ID")));
						userComboBox.setSelectedItem(usernames.get(rs.getInt("USER_ID")));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		lastBtn.setIcon(new ImageIcon(AdminUpdateDeleteStudentsForm.class.getResource("/resources/Last_Record.png")));
		lastBtn.setBounds(227, 350, 49, 23);
		contentPane.add(lastBtn);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(24, 324, 303, 2);
		contentPane.add(separator);
	}
}
