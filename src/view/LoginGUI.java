package view;

import java.awt.EventQueue;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import Helper.*;
import Model.Bashekim;
import Model.Doctor;
import Model.Hasta;

public class LoginGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	private JTextField fld_hastaTC;
	private JTextField fld_doctorTc;
	private JPasswordField fld_doctorPass;
	private DBConnection conn = new DBConnection();
	private JPasswordField fld_hastaPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setResizable(false);
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 450);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(255, 255, 255));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lbl_logo = new JLabel(new ImageIcon(getClass().getResource("medicine.png")));
		lbl_logo.setBounds(231, 14, 65, 55);
		w_pane.add(lbl_logo);
		
		JLabel lblNewLabel = new JLabel("Hastane Yönetim Sistemine Hoşgeldiniz");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel.setBounds(105, 83, 317, 30);
		w_pane.add(lblNewLabel);
		
		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(10, 123, 466, 280);
		w_pane.add(w_tabpane);
		
		JPanel w_hastaLogin = new JPanel();
		w_hastaLogin.setBackground(new Color(255, 255, 255));
		w_tabpane.addTab("Hastane Girişi", null, w_hastaLogin, null);
		w_hastaLogin.setLayout(null);
		
		JLabel lblTcNumaranz = new JLabel("T.C. Numaranız: ");
		lblTcNumaranz.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblTcNumaranz.setBounds(32, 36, 126, 30);
		w_hastaLogin.add(lblTcNumaranz);
		
		JLabel lblifre = new JLabel("Şifre:");
		lblifre.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblifre.setBounds(31, 86, 126, 30);
		w_hastaLogin.add(lblifre);
		
		fld_hastaTC = new JTextField();
		fld_hastaTC.setFont(new Font("Yu Gothic Light", Font.PLAIN, 18));
		fld_hastaTC.setBounds(168, 37, 266, 31);
		w_hastaLogin.add(fld_hastaTC);
		fld_hastaTC.setColumns(10);
		
		JButton btn_register = new JButton("Kayıt Ol");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI rGUI = new RegisterGUI();
				rGUI.setVisible(true);
				dispose();
			}
		});
		btn_register.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		btn_register.setBounds(32, 155, 180, 37);
		w_hastaLogin.add(btn_register);
		
		JButton btn_hastaLogin = new JButton("Giriş Yap");
		btn_hastaLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_hastaTC.getText().length() == 0 || fld_hastaPass.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					boolean key = true;
					try {
						Connection con = conn.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM users"); // Veritabanından gelen sonuçları satır satır dolaşmamızı sağlar.
						while(rs.next())
						{
							// kullanıcının girdiği veriler ile veri tabanındakiler uyuşuyor mu?
							if(fld_hastaTC.getText().equals(rs.getString("tcno")) && fld_hastaPass.getText().equals(rs.getString("password")))
							{
								if(rs.getString("type").equals("hasta")) {
									Hasta hasta= new Hasta(); // Eğer bilgiler eşleşirse baş hekim nesnesi oluşur
									hasta.setId(rs.getInt("id")); // Veritabanındaki id sütunundan gelen değer alınır ve bhekim nesnesine atanır.
									hasta.setPassword("password");
									hasta.setTcno(rs.getString("tcno"));
									hasta.setName(rs.getString("name"));
									hasta.setType(rs.getString("type"));
									HastaGUI hGUI = new HastaGUI(hasta);
									hGUI.setVisible(true);
									dispose(); // var olan JFrame kapanır
									key = false;
								}
							}
						}
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					} 
					
					if(key) {
						Helper.showMsg("Böyle bir kullanıcı hasta bulunamadı, lütfen kayıt olunuz!");
					}
				}
			}
		});
		btn_hastaLogin.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		btn_hastaLogin.setBounds(240, 155, 180, 37);
		w_hastaLogin.add(btn_hastaLogin);
		
		fld_hastaPass = new JPasswordField();
		fld_hastaPass.setBounds(167, 91, 267, 28);
		w_hastaLogin.add(fld_hastaPass);
		
		JPanel w_doctorLogin = new JPanel();
		w_doctorLogin.setBackground(new Color(255, 255, 255));
		w_tabpane.addTab("Doktor Girişi", null, w_doctorLogin, null);
		w_doctorLogin.setLayout(null);
		
		JPanel w_hastaLogin_1 = new JPanel();
		w_hastaLogin_1.setLayout(null);
		w_hastaLogin_1.setBackground(Color.WHITE);
		w_hastaLogin_1.setBounds(0, 0, 461, 253);
		w_doctorLogin.add(w_hastaLogin_1);
		
		JLabel lblTcNumaranz_1 = new JLabel("T.C. Numaranız: ");
		lblTcNumaranz_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblTcNumaranz_1.setBounds(32, 36, 126, 30);
		w_hastaLogin_1.add(lblTcNumaranz_1);
		
		JLabel lblifre_1 = new JLabel("Şifre:");
		lblifre_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblifre_1.setBounds(31, 86, 126, 30);
		w_hastaLogin_1.add(lblifre_1);
		
		fld_doctorTc = new JTextField();
		fld_doctorTc.setFont(new Font("Yu Gothic Light", Font.PLAIN, 18));
		fld_doctorTc.setColumns(10);
		fld_doctorTc.setBounds(168, 37, 266, 31);
		w_hastaLogin_1.add(fld_doctorTc);
		
		JButton btn_doctorLogin = new JButton("Giriş Yap");
		btn_doctorLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_doctorTc.getText().length() == 0 || fld_doctorPass.getText().length() == 0) 
				{
					// mesajın hangi pencereye bağlı olduğunu belirtir "null" ise ekranın ortasında çıkar 
					Helper.showMsg("fill");
				}
				else 
				{
					// Connection JDBC kütüphanesindeki bir sınıf(sol taraf)
					// DBConneciton sınıfının nesnesi conn
					// connDb ise bu sınıfın metodu
					try {
						Connection con = conn.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM users"); // Veritabanından gelen sonuçları satır satır dolaşmamızı sağlar.
						while(rs.next())
						{
							// kullanıcının girdiği veriler ile veri tabanındakiler uyuşuyor mu?
							if(fld_doctorTc.getText().equals(rs.getString("tcno")) && fld_doctorPass.getText().equals(rs.getString("password")))
							{
								if(rs.getString("type").equals("bashekim")) {
									Bashekim bhekim = new Bashekim(); // Eğer bilgiler eşleşirse baş hekim nesnesi oluşur
									bhekim.setId(rs.getInt("id")); // Veritabanındaki id sütunundan gelen değer alınır ve bhekim nesnesine atanır.
									bhekim.setPassword("password");
									bhekim.setTcno(rs.getString("tcno"));
									bhekim.setName(rs.getString("name"));
									bhekim.setType(rs.getString("type"));
									BashekimGUI bGUI = new BashekimGUI(bhekim);
									bGUI.setVisible(true);
									dispose(); // var olan JFrame kapanır
								}
								
								if(rs.getString("type").equals("doktor")) {
									Doctor doctor = new Doctor();
									doctor.setId(rs.getInt("id")); // Veritabanındaki id sütunundan gelen değer alınır ve bhekim nesnesine atanır.
									doctor.setPassword("password");
									doctor.setTcno(rs.getString("tcno"));
									doctor.setName(rs.getString("name"));
									doctor.setType(rs.getString("type"));
									DoctorGUI dGUI = new DoctorGUI(doctor);
									dGUI.setVisible(true);
									dispose();
									
								}
							}
						}
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					} 
				}
				
			}
		});
		btn_doctorLogin.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		btn_doctorLogin.setBounds(32, 155, 388, 45);
		w_hastaLogin_1.add(btn_doctorLogin);
		
		fld_doctorPass = new JPasswordField();
		fld_doctorPass.setBounds(167, 86, 267, 28);
		w_hastaLogin_1.add(fld_doctorPass);
	}
}
