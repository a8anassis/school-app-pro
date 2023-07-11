package gr.aueb.cf.schoolpro;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gr.aueb.cf.schoolpro.util.DBUtil;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AdminInsertTeachersForm extends JFrame {
	private static final long serialVersionUID = 123456;
	private JPanel contentPane;
	private JTextField firstnameTxt;
	private JLabel lastnameLbl;
	private JTextField lastnameTxt;
	private JLabel genderLbl;
	private JLabel ssnLbl;
	private JTextField ssnTxt;
	private JLabel specialityLbl;
	private Connection connection;
	private JComboBox<String> comboBox;
	private Map<String, Integer> cities;
	private DefaultComboBoxModel<String> model;

	public AdminInsertTeachersForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 538, 468);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel firstnameLbl = new JLabel("Όνομα");
		firstnameLbl.setForeground(new Color(128, 0, 0));
		firstnameLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		firstnameLbl.setBounds(68, 75, 56, 17);
		contentPane.add(firstnameLbl);
		
		firstnameTxt = new JTextField();
		firstnameTxt.setBounds(129, 73, 207, 20);
		contentPane.add(firstnameTxt);
		firstnameTxt.setColumns(10);
		
		lastnameLbl = new JLabel("Επώνυμο");
		lastnameLbl.setForeground(new Color(128, 0, 0));
		lastnameLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lastnameLbl.setBounds(52, 135, 72, 17);
		contentPane.add(lastnameLbl);
		
		lastnameTxt = new JTextField();
		lastnameTxt.setColumns(10);
		lastnameTxt.setBounds(129, 133, 207, 20);
		contentPane.add(lastnameTxt);
		
		genderLbl = new JLabel("Φύλο");
		genderLbl.setForeground(new Color(128, 0, 0));
		genderLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		genderLbl.setBounds(80, 165, 44, 17);
		contentPane.add(genderLbl);
		
		ssnLbl = new JLabel("Αρ. Μητρώου");
		ssnLbl.setForeground(new Color(128, 0, 0));
		ssnLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		ssnLbl.setBounds(25, 105, 99, 17);
		contentPane.add(ssnLbl);
		
		ssnTxt = new JTextField();
		ssnTxt.setColumns(10);
		ssnTxt.setBounds(129, 103, 207, 20);
		contentPane.add(ssnTxt);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		JRadioButton maleRdbtn = new JRadioButton("Άνδρας");
		maleRdbtn.setBounds(129, 164, 81, 23);
		contentPane.add(maleRdbtn);
		
		JRadioButton femaleRdbtn = new JRadioButton("Γυναίκα");
		femaleRdbtn.setBounds(206, 164, 95, 23);
		contentPane.add(femaleRdbtn);
		
		buttonGroup.add(maleRdbtn);
		buttonGroup.add(femaleRdbtn);
		
		specialityLbl = new JLabel("Πόλη");
		specialityLbl.setForeground(new Color(128, 0, 0));
		specialityLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		specialityLbl.setBounds(80, 195, 44, 17);
		contentPane.add(specialityLbl);
		
		comboBox = new JComboBox<>();
		
		comboBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				//comboBox.removeAll();
				String sql = "SELECT * FROM CITIES";
				//connection = Login.getConnection();
				
			    try (Connection connection = DBUtil.getConnection();
			    		PreparedStatement ps = connection.prepareStatement(sql);
			    		ResultSet rs = ps.executeQuery(sql)) {
			    	cities = new HashMap<>();
			    	model = new DefaultComboBoxModel<>();
			    	
			    	while (rs.next()) {
			    		String city = rs.getString("CITY");
			    		int id = rs.getInt("ID");
			    		cities.put(city, id);
			    		//comboBox.addItem(city);
			    		model.addElement(city);
			    	}
			    	comboBox.setModel(model);
			    	comboBox.setMaximumRowCount(5);
			    	
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		comboBox.setBounds(129, 194, 207, 20);
		contentPane.add(comboBox);
		
	}
}
