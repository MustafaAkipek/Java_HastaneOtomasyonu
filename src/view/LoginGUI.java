package view;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class LoginGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	private JTextField fld_hastaTC;
	private JTextField fld_hastaPass;
	private JTextField textField;
	private JTextField textField_1;

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
		setTitle("Hastane Otomasyoonu");
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
		fld_hastaTC.setText("dfdfd");
		fld_hastaTC.setBounds(168, 37, 266, 31);
		w_hastaLogin.add(fld_hastaTC);
		fld_hastaTC.setColumns(10);
		
		fld_hastaPass = new JTextField();
		fld_hastaPass.setText("dfdfd");
		fld_hastaPass.setFont(new Font("Yu Gothic Light", Font.PLAIN, 18));
		fld_hastaPass.setColumns(10);
		fld_hastaPass.setBounds(169, 86, 266, 31);
		w_hastaLogin.add(fld_hastaPass);
		
		JButton btn_register = new JButton("Kayıt Ol");
		btn_register.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		btn_register.setBounds(32, 155, 180, 37);
		w_hastaLogin.add(btn_register);
		
		JButton btn_hastaLogin = new JButton("Giriş Yap");
		btn_hastaLogin.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		btn_hastaLogin.setBounds(240, 155, 180, 37);
		w_hastaLogin.add(btn_hastaLogin);
		
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
		
		textField = new JTextField();
		textField.setText("dfdfd");
		textField.setFont(new Font("Yu Gothic Light", Font.PLAIN, 18));
		textField.setColumns(10);
		textField.setBounds(168, 37, 266, 31);
		w_hastaLogin_1.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setText("dfdfd");
		textField_1.setFont(new Font("Yu Gothic Light", Font.PLAIN, 18));
		textField_1.setColumns(10);
		textField_1.setBounds(169, 86, 266, 31);
		w_hastaLogin_1.add(textField_1);
		
		JButton btn_doctorLogin = new JButton("Giriş Yap");
		btn_doctorLogin.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		btn_doctorLogin.setBounds(32, 155, 388, 45);
		w_hastaLogin_1.add(btn_doctorLogin);
	}
}
