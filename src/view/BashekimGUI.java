package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Bashekim;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class BashekimGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	static Bashekim bashekim = new Bashekim();
	private JPanel w_pane;
	private JTextField fld_dName;
	private JTextField fld_dTcno;
	private JTextField fld_dPass;
	private JTextField fld_doctorID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BashekimGUI frame = new BashekimGUI(bashekim);
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
	public BashekimGUI(Bashekim bashekim) {
		setTitle("Hastane Yönetim Sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(255, 255, 255));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hoşgeldiniz Sayın " + bashekim.getName());
		lblNewLabel.setBounds(10, 24, 314, 29);
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		w_pane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Çıkış Yap");
		btnNewButton.setBounds(641, 30, 85, 21);
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 11));
		w_pane.add(btnNewButton);
		
		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(10, 85, 716, 368);
		w_pane.add(w_tab);
		
		JPanel w_doctor = new JPanel();
		w_doctor.setBackground(new Color(255, 255, 255));
		w_tab.addTab("Doktor Yönetimi", null, w_doctor, null);
		w_doctor.setLayout(null);
		
		JLabel label = new JLabel("Ad Soyad");
		label.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		label.setBounds(485, 13, 76, 26);
		w_doctor.add(label);
		
		fld_dName = new JTextField();
		fld_dName.setBounds(485, 38, 216, 26);
		w_doctor.add(fld_dName);
		fld_dName.setColumns(10);
		
		JLabel label_1 = new JLabel("T.C. No");
		label_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		label_1.setBounds(485, 74, 64, 26);
		w_doctor.add(label_1);
		
		fld_dTcno = new JTextField();
		fld_dTcno.setBounds(485, 99, 216, 26);
		w_doctor.add(fld_dTcno);
		fld_dTcno.setColumns(10);
		
		JLabel label_2 = new JLabel("Şifre");
		label_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		label_2.setBounds(485, 135, 88, 26);
		w_doctor.add(label_2);
		
		fld_dPass = new JTextField();
		fld_dPass.setBounds(485, 160, 216, 26);
		w_doctor.add(fld_dPass);
		fld_dPass.setColumns(10);
		
		JButton btn_addDoctor = new JButton("Ekle");
		btn_addDoctor.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		btn_addDoctor.setBounds(485, 196, 216, 36);
		w_doctor.add(btn_addDoctor);
		
		JLabel label_3 = new JLabel("Kullanıcı ID");
		label_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		label_3.setBounds(485, 242, 101, 26);
		w_doctor.add(label_3);
		
		fld_doctorID = new JTextField();
		fld_doctorID.setBounds(485, 264, 216, 26);
		w_doctor.add(fld_doctorID);
		fld_doctorID.setColumns(10);
		
		JButton btn_delDoctor = new JButton("Sil");
		btn_delDoctor.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		btn_delDoctor.setBounds(485, 298, 216, 33);
		w_doctor.add(btn_delDoctor);
	}
}
