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

public class InterfazRadar {

	private JFrame frame;
	private JButton btnNewButton;
	private JTextField textField;
	private JLabel lblLmiteDeVelocidad;
	private JLabel lblKmh;
	private Radar radar;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazRadar window = new InterfazRadar();
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
	public InterfazRadar() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		{
			panel = new JPanel();
			panel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Direcci\u00F3n General de Tr\u00E1fico", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(0, 0, 442, 258);
			frame.getContentPane().add(panel);
			panel.setLayout(null);
			{
				btnNewButton = new JButton("Iniciar Radar");
				btnNewButton.setBounds(148, 160, 123, 29);
				panel.add(btnNewButton);
				btnNewButton.setEnabled(false);
				{
					lblKmh = new JLabel("Km/h");
					lblKmh.setBounds(330, 108, 38, 20);
					panel.add(lblKmh);
				}
				{
					lblLmiteDeVelocidad = new JLabel("Límite de velocidad");
					lblLmiteDeVelocidad.setBounds(27, 108, 136, 20);
					panel.add(lblLmiteDeVelocidad);
				}
				{
					textField = new JTextField();
					textField.setBounds(174, 105, 146, 26);
					panel.add(textField);
					textField.addActionListener(new TextFieldActionListener());
					textField.setColumns(10);
				}
				btnNewButton.addActionListener(new BtnNewButtonActionListener());
			}
		}
	}
	private class BtnNewButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			double velocidad = Double.parseDouble(textField.getText());
			radar = new Radar (velocidad);
			radar.iniciar_radar();
		}
	}
	private class TextFieldActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			btnNewButton.setEnabled(true);
		}
	}
}
