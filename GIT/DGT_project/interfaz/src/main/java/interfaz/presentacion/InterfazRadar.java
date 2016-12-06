package interfaz.presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;

import radar.domain.Radar;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;

import org.hibernate.HibernateException;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class InterfazRadar {

	private JFrame frmDireccionGeneralDe;
	private JButton btnIniciar;
	private JLabel lblKmh;
	private Radar radar;
	private JPanel panel;
	private JLabel lblIntroduzcaElLmite;
	private JComboBox comboBoxvelmax;
	private JButton buttonApagar;
	private JPanel panelChangOwner;
	private JLabel label_1;
	private JTextField textFieldLicence;
	private JLabel label_2;
	private JTextField textFieldDNI;
	private JButton buttonCambiar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazRadar window = new InterfazRadar();
					window.frmDireccionGeneralDe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfazRadar() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDireccionGeneralDe = new JFrame();
		frmDireccionGeneralDe.setTitle(" Direccion General de Trafico");
		frmDireccionGeneralDe.setResizable(false);
		frmDireccionGeneralDe.setBounds(100, 100, 725, 304);
		frmDireccionGeneralDe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDireccionGeneralDe.getContentPane().setLayout(null);
		{
			panel = new JPanel();
			panel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Iniciar", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(0, 0, 371, 260);
			frmDireccionGeneralDe.getContentPane().add(panel);
			panel.setLayout(null);
			{
				btnIniciar = new JButton("Iniciar Radar");
				btnIniciar.setFont(new Font("Tahoma", Font.PLAIN, 18));
				btnIniciar.setBounds(29, 173, 136, 44);
				panel.add(btnIniciar);
				{
					lblKmh = new JLabel("Km/h");
					lblKmh.setFont(new Font("Tahoma", Font.PLAIN, 18));
					lblKmh.setBounds(281, 108, 63, 20);
					panel.add(lblKmh);
				}
				{
					lblIntroduzcaElLmite = new JLabel("Introduzca el límite de velocidad de la vía:");
					lblIntroduzcaElLmite.setFont(new Font("Tahoma", Font.PLAIN, 18));
					lblIntroduzcaElLmite.setBounds(15, 52, 319, 20);
					panel.add(lblIntroduzcaElLmite);
				}
				{
					comboBoxvelmax = new JComboBox();
					comboBoxvelmax.setFont(new Font("Tahoma", Font.PLAIN, 18));
					comboBoxvelmax.setModel(new DefaultComboBoxModel(new String[] {"30", "40", "50", "60", "70", "80", "90", "100", "110", "120"}));
					comboBoxvelmax.setBounds(107, 105, 146, 26);
					panel.add(comboBoxvelmax);
				}
				{
					buttonApagar = new JButton("Apagar Radar");
					buttonApagar.addActionListener(new ButtonApagarActionListener());
					buttonApagar.setEnabled(false);
					buttonApagar.setBounds(180, 175, 164, 41);
					panel.add(buttonApagar);
					buttonApagar.setFont(new Font("Tahoma", Font.PLAIN, 18));
				}
				{
					panelChangOwner = new JPanel();
					panelChangOwner.setToolTipText("");
					panelChangOwner.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cambiar propietario", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
					panelChangOwner.setBounds(386, 0, 322, 260);
					frmDireccionGeneralDe.getContentPane().add(panelChangOwner);
					GridBagLayout gbl_panelChangOwner = new GridBagLayout();
					gbl_panelChangOwner.columnWidths = new int[]{166, 129, 0};
					gbl_panelChangOwner.rowHeights = new int[]{30, 26, 26, 0, 0, 44, 0};
					gbl_panelChangOwner.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
					gbl_panelChangOwner.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
					panelChangOwner.setLayout(gbl_panelChangOwner);
					{
						label_1 = new JLabel("Licencia Vehículo");
						label_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
						GridBagConstraints gbc_label_1 = new GridBagConstraints();
						gbc_label_1.anchor = GridBagConstraints.SOUTHEAST;
						gbc_label_1.insets = new Insets(0, 0, 5, 5);
						gbc_label_1.gridx = 0;
						gbc_label_1.gridy = 1;
						panelChangOwner.add(label_1, gbc_label_1);
					}
					{
						textFieldLicence = new JTextField();
						textFieldLicence.setColumns(10);
						GridBagConstraints gbc_textFieldLicence = new GridBagConstraints();
						gbc_textFieldLicence.fill = GridBagConstraints.HORIZONTAL;
						gbc_textFieldLicence.anchor = GridBagConstraints.NORTH;
						gbc_textFieldLicence.insets = new Insets(0, 0, 5, 0);
						gbc_textFieldLicence.gridx = 1;
						gbc_textFieldLicence.gridy = 1;
						panelChangOwner.add(textFieldLicence, gbc_textFieldLicence);
					}
					{
						label_2 = new JLabel("DNI Propietario");
						label_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
						GridBagConstraints gbc_label_2 = new GridBagConstraints();
						gbc_label_2.anchor = GridBagConstraints.SOUTHEAST;
						gbc_label_2.insets = new Insets(0, 0, 5, 5);
						gbc_label_2.gridx = 0;
						gbc_label_2.gridy = 2;
						panelChangOwner.add(label_2, gbc_label_2);
					}
					{
						textFieldDNI = new JTextField();
						textFieldDNI.setColumns(10);
						GridBagConstraints gbc_textFieldDNI = new GridBagConstraints();
						gbc_textFieldDNI.fill = GridBagConstraints.HORIZONTAL;
						gbc_textFieldDNI.anchor = GridBagConstraints.NORTH;
						gbc_textFieldDNI.insets = new Insets(0, 0, 5, 0);
						gbc_textFieldDNI.gridx = 1;
						gbc_textFieldDNI.gridy = 2;
						panelChangOwner.add(textFieldDNI, gbc_textFieldDNI);
					}
					{
						buttonCambiar = new JButton("Cambiar");
						buttonCambiar.addActionListener(new ButtonCambiarActionListener());
						buttonCambiar.setEnabled(false);
						buttonCambiar.setFont(new Font("Tahoma", Font.PLAIN, 18));
						GridBagConstraints gbc_buttonCambiar = new GridBagConstraints();
						gbc_buttonCambiar.fill = GridBagConstraints.VERTICAL;
						gbc_buttonCambiar.gridwidth = 2;
						gbc_buttonCambiar.gridx = 0;
						gbc_buttonCambiar.gridy = 5;
						panelChangOwner.add(buttonCambiar, gbc_buttonCambiar);
					}
				}
				btnIniciar.addActionListener(new BtnIniciarActionListener());
			}
		}
	}
	private class BtnIniciarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) throws HibernateException {
			Object velmax = comboBoxvelmax.getSelectedItem();
			String velocidadMax = String.valueOf(velmax);
			double velocidad = Double.parseDouble(velocidadMax);
			radar = new Radar (velocidad);
			radar.iniciar_radar();	
			btnIniciar.setEnabled(false);
			buttonApagar.setEnabled(true);
			buttonCambiar.setEnabled(true);
		}
	}
	private class ButtonCambiarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			radar.cambiarpropietario(textFieldLicence.getText(), textFieldDNI.getText());
		}
	}
	private class ButtonApagarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			radar.desconectar();
		}
	}
}
