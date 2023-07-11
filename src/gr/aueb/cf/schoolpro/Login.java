package gr.aueb.cf.schoolpro;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Login extends JFrame {
	private static final long serialVersionUID = 123456;
	private JPanel contentPane;
	private JTextField usernameTxt;
	private JTextField usernameRegTxt;
	private JPasswordField passwordTxt;
	private static Connection connection;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	public Login() {
//		addWindowListener(new WindowAdapter() {
//			@Override
//			public void windowOpened(WindowEvent e) {
//				String username = "schoolpro";
//				String password = System.getenv("SCHOOL_DB_USER_PASSWD");
//				String url = "jdbc:mysql://localhost:3306/schooldbpro?serverTimezone = UTC";
//				
//				try {
//					Class.forName("com.mysql.cj.jdbc.Driver");
//					connection = DriverManager.getConnection(url, username, password);
//					System.out.println("Successfully connect to DB" + connection);
//				} catch (SQLException | ClassNotFoundException e1) {
//					e1.printStackTrace();
//				}
//			}
//		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/resources/eduv2.png")));
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(241, 241, 241));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/resources/school.jpg")));
		lblNewLabel.setBounds(0, 215, 437, 48);
		contentPane.add(lblNewLabel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabbedPane.setBounds(0, 11, 437, 199);
		contentPane.add(tabbedPane);
		
		JPanel login = new JPanel();
		tabbedPane.addTab("Login", null, login, null);
		login.setLayout(null);
		
		JLabel passwordLbl = new JLabel("Password");
		passwordLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		passwordLbl.setBounds(93, 68, 71, 23);
		login.add(passwordLbl);
		
		JLabel usernameLbl = new JLabel("Username");
		usernameLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		usernameLbl.setBounds(93, 35, 71, 23);
		login.add(usernameLbl);
		
		usernameTxt = new JTextField();
		usernameTxt.setBounds(174, 35, 153, 23);
		login.add(usernameTxt);
		usernameTxt.setColumns(10);
		
		JButton loginBtn = new JButton("Login");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inputPasswd = String.valueOf(passwordTxt.getPassword()).trim();
				
				if (usernameTxt.getText().matches("[aA]dmin")) {
					if (inputPasswd.equals(System.getenv("SCHOOL_APP_ADMIN_PASSWD"))) {
						Main.getAdminMenu().setVisible(true);
						Main.getLoginForm().setEnabled(false);
					} else {
						JOptionPane.showMessageDialog(null, "Password Error" , "Error" , JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else {
					//checkUsersDB();
				}	
			}
		});
		loginBtn.setForeground(new Color(0, 0, 255));
		loginBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		loginBtn.setBounds(238, 102, 90, 40);
		login.add(loginBtn);
		
		passwordTxt = new JPasswordField();
		passwordTxt.setBounds(174, 68, 153, 23);
		login.add(passwordTxt);
		
		JPanel register = new JPanel();
		tabbedPane.addTab("Register", null, register, null);
		register.setLayout(null);
		
		JLabel passwordRegLbl = new JLabel("Password");
		passwordRegLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		passwordRegLbl.setBounds(101, 44, 71, 23);
		register.add(passwordRegLbl);
		
		JLabel usernameRegLbl = new JLabel("Username");
		usernameRegLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		usernameRegLbl.setBounds(101, 11, 71, 23);
		register.add(usernameRegLbl);
		
		usernameRegTxt = new JTextField();
		usernameRegTxt.setColumns(10);
		usernameRegTxt.setBounds(182, 11, 153, 23);
		register.add(usernameRegTxt);
		
		JButton registerBtn = new JButton("Register");
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		registerBtn.setForeground(Color.BLUE);
		registerBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		registerBtn.setBounds(246, 117, 90, 40);
		register.add(registerBtn);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblConfirmPassword.setBounds(44, 74, 128, 23);
		register.add(lblConfirmPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(182, 44, 153, 23);
		register.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(182, 74, 153, 23);
		register.add(passwordField_1);
	}

	public static Connection getConnection() {
		return connection;
	}
}
