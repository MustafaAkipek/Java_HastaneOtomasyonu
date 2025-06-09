package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Helper.Helper;
import Helper.Item;
import Model.Appointment;
import Model.Clinic;
import Model.Hasta;
import Model.Whour;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class HastaGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	private static Hasta hasta = new Hasta();
	private Clinic clinic = new Clinic();
	private JTable table_doctor;
	private DefaultTableModel doctorModel;
	private Object[] doctorData = null;
	private JTable table_whour;
	private Whour whour = new Whour();
	private DefaultTableModel whourModel;
	private Object[] whourData = null;
	private int selectDoctorID = 0;
	private String selectDoctorName = null;
	private JTable table_appoint;
	private Appointment appoint = new Appointment();
	private DefaultTableModel appointModel;
	private Object[] appointData = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HastaGUI frame = new HastaGUI(hasta);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public HastaGUI(Hasta hasta) throws SQLException {

		// Doktor Model
		doctorModel = new DefaultTableModel();
		Object[] colDoctor = new Object[2]; // 2 tane column u çağıracağız
		colDoctor[0] = "ID";
		colDoctor[1] = "Ad Soyad";
		doctorModel.setColumnIdentifiers(colDoctor);
		doctorData = new Object[2]; // row u oluşturduk

		// Doktor Model
		whourModel = new DefaultTableModel();
		Object[] colWhour = new Object[2]; // 2 tane column u çağıracağız
		colWhour[0] = "ID";
		colWhour[1] = "Tarih";
		whourModel.setColumnIdentifiers(colWhour);
		whourData = new Object[2]; // row u oluşturduk
		
		// Doktor Model
				whourModel = new DefaultTableModel();
				Object[] colWhour = new Object[2]; // 2 tane column u çağıracağız
				colWhour[0] = "ID";
				colWhour[1] = "Tarih";
				whourModel.setColumnIdentifiers(colWhour);
				whourData = new Object[2]; // row u oluşturduk

		setResizable(false);
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(255, 255, 255));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hoşgeldiniz Sayın " + hasta.getName());
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 10, 314, 29);
		w_pane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Çıkış Yap");
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 11));
		btnNewButton.setBounds(616, 10, 110, 29);
		w_pane.add(btnNewButton);

		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(10, 66, 716, 368);
		w_pane.add(w_tab);

		JPanel w_appointment = new JPanel();
		w_appointment.setBackground(new Color(255, 255, 255));
		w_tab.addTab("Randevu Sistemi", null, w_appointment, null);
		w_appointment.setLayout(null);

		JScrollPane w_scrollDoctor = new JScrollPane();
		w_scrollDoctor.setBounds(10, 42, 280, 289);
		w_appointment.add(w_scrollDoctor);

		table_doctor = new JTable(doctorModel);
		w_scrollDoctor.setViewportView(table_doctor);

		JLabel lblNewLabel_1 = new JLabel("Doktor Listesi");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 10, 111, 23);
		w_appointment.add(lblNewLabel_1);

		JLabel lblPoliklinikAd = new JLabel("Poliklinik Adı");
		lblPoliklinikAd.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblPoliklinikAd.setBounds(298, 8, 96, 26);
		w_appointment.add(lblPoliklinikAd);

		JComboBox select_clinic = new JComboBox();
		select_clinic.setBounds(300, 41, 150, 34);
		select_clinic.addItem("--Poliklinik Seç--");
		for (int i = 0; i < clinic.getList().size(); i++) {
			select_clinic.addItem(new Item(clinic.getList().get(i).getId(), clinic.getList().get(i).getName()));
		}
		select_clinic.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (select_clinic.getSelectedIndex() != 0) {
					JComboBox c = (JComboBox) e.getSource();
					Item item = (Item) c.getSelectedItem();
					DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
					clearModel.setRowCount(0);
					for (int i = 0; i < clinic.getClinicDoctorList(item.getKey()).size(); i++) {
						doctorData[0] = clinic.getClinicDoctorList(item.getKey()).get(i).getId();
						doctorData[1] = clinic.getClinicDoctorList(item.getKey()).get(i).getName();
						doctorModel.addRow(doctorData);
					}
				} else {

				}
			}
		});

		w_appointment.add(select_clinic);

		JLabel lblPoliklinikAd_1 = new JLabel("Doktor Seç");
		lblPoliklinikAd_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblPoliklinikAd_1.setBounds(300, 100, 96, 26);
		w_appointment.add(lblPoliklinikAd_1);

		JButton btn_selDoctor = new JButton("Seç");
		btn_selDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table_doctor.getSelectedRow();
				if (row >= 0) {
					String value = table_doctor.getModel().getValueAt(row, 0).toString();
					int id = Integer.parseInt(value);
					DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
					clearModel.setRowCount(0);

					for (int i = 0; i < whour.getWhourList(id).size(); i++) {
						whourData[0] = whour.getWhourList(id).get(i).getId();
						whourData[1] = whour.getWhourList(id).get(i).getWdate();
						whourModel.addRow(whourData);
					}
					table_whour.setModel(whourModel);

					selectDoctorID = id;
					selectDoctorName = table_doctor.getModel().getValueAt(row, 1).toString();

				} else {
					Helper.showMsg("Lütfen bir doktor seçiniz!");
				}
			}
		});
		btn_selDoctor.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		btn_selDoctor.setBounds(300, 136, 151, 36);
		w_appointment.add(btn_selDoctor);

		JScrollPane w_scrollWhour = new JScrollPane();
		w_scrollWhour.setBounds(457, 42, 244, 289);
		w_appointment.add(w_scrollWhour);

		table_whour = new JTable(whourModel);
		w_scrollWhour.setViewportView(table_whour);
		table_whour.getColumnModel().getColumn(0).setPreferredWidth(5);

		JLabel lblNewLabel_1_1 = new JLabel("Doktor Listesi");
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(457, 10, 254, 23);
		w_appointment.add(lblNewLabel_1_1);

		JButton btn_addAppoint = new JButton("Randevu Al");
		btn_addAppoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_whour.getSelectedRow();
				if (selRow >= 0) {
					String date = table_whour.getModel().getValueAt(selRow, 1).toString();
					try {
						boolean control = hasta.addAppointment(selectDoctorID, hasta.getId(), selectDoctorName,
								hasta.getName(), date);
						if (control) {
							Helper.showMsg("success");
							hasta.updateWhourStatus(selectDoctorID, date);
							updateWhourModel(selectDoctorID);
						} else {
							Helper.showMsg("error");
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else {
					Helper.showMsg("Lütfen geçerli bir tarih seçiniz!");
				}
			}
		});
		btn_addAppoint.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		btn_addAppoint.setBounds(300, 242, 151, 36);
		w_appointment.add(btn_addAppoint);

		JLabel lblPoliklinikAd_1_1 = new JLabel("Randevu");
		lblPoliklinikAd_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblPoliklinikAd_1_1.setBounds(300, 206, 96, 26);
		w_appointment.add(lblPoliklinikAd_1_1);
		
		JPanel w_appoint = new JPanel();
		w_tab.addTab("Randevularım", null, w_appoint, null);
		w_appoint.setLayout(null);
		
		JScrollPane w_scrollAppoint = new JScrollPane();
		w_scrollAppoint.setBounds(10, 10, 691, 321);
		w_appoint.add(w_scrollAppoint);
		
		table_appoint = new JTable();
		w_scrollAppoint.setViewportView(table_appoint);
	}

	public void updateWhourModel(int doctor_id) {
		DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < whour.getWhourList(doctor_id).size(); i++) {
			whourData[0] = whour.getWhourList(doctor_id).get(i).getId();
			whourData[1] = whour.getWhourList(doctor_id).get(i).getWdate();
			whourModel.addRow(whourData);
		}
	}
}
