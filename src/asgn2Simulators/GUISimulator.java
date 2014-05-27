package asgn2Simulators;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class GUISimulator {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 576;
	public static final int INPUT_WIDTH = (int) (WIDTH*0.15);
	private JTextField textField_8;
	private JTextField textField_9;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUISimulator window = new GUISimulator();
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
	public GUISimulator() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		
		JPanel titlePanel = new JPanel();
		titlePanel.setForeground(Color.WHITE);
		titlePanel.setBackground(SystemColor.textHighlight);
		frame.getContentPane().add(titlePanel, BorderLayout.NORTH);
		
		JLabel lblCarParkSimulator = new JLabel("Car Park Simulator");
		lblCarParkSimulator.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblCarParkSimulator.setForeground(Color.WHITE);
		titlePanel.add(lblCarParkSimulator);
		
		JPanel inputPanel = new JPanel();
		inputPanel.setForeground(SystemColor.textHighlight);
		inputPanel.setBackground(Color.WHITE);
		inputPanel.setBorder(new EmptyBorder(10, 35, 10, 15));
		frame.getContentPane().add(inputPanel, BorderLayout.WEST);
		inputPanel.setLayout(new GridLayout(21, 1, 0, 1));
		inputPanel.setSize(INPUT_WIDTH, HEIGHT);
		
		JLabel lblMaxCarSpaces = new JLabel("Max Car Spaces");
		lblMaxCarSpaces.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMaxCarSpaces.setForeground(SystemColor.textHighlight);
		inputPanel.add(lblMaxCarSpaces);
		
		textField = new JTextField();
		textField.setForeground(SystemColor.textHighlight);
		inputPanel.add(textField);
		textField.setColumns(10);
		textField.setBorder(new LineBorder(SystemColor.textHighlight, 1));
		
		JLabel lblMaxSmallCar = new JLabel("Max Small Car Spaces");
		lblMaxSmallCar.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMaxSmallCar.setForeground(SystemColor.textHighlight);
		lblMaxSmallCar.setBackground(Color.WHITE);
		inputPanel.add(lblMaxSmallCar);
		
		textField_1 = new JTextField();
		inputPanel.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setBorder(new LineBorder(SystemColor.textHighlight, 1));
		
		JLabel lblNewLabel = new JLabel("Max Motorcycle Spaces");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setForeground(SystemColor.textHighlight);
		inputPanel.add(lblNewLabel);
		
		textField_2 = new JTextField();
		inputPanel.add(textField_2);
		textField_2.setColumns(10);
		textField_2.setBorder(new LineBorder(SystemColor.textHighlight, 1));
		
		JLabel lblNewLabel_1 = new JLabel("Max Queue Size");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setForeground(SystemColor.textHighlight);
		inputPanel.add(lblNewLabel_1);
		
		textField_3 = new JTextField();
		inputPanel.add(textField_3);
		textField_3.setColumns(10);
		textField_3.setBorder(new LineBorder(SystemColor.textHighlight, 1));
		
		JLabel lblNewLabel_2 = new JLabel("Mean Stay");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setForeground(SystemColor.textHighlight);
		inputPanel.add(lblNewLabel_2);
		
		textField_4 = new JTextField();
		inputPanel.add(textField_4);
		textField_4.setColumns(10);
		textField_4.setBorder(new LineBorder(SystemColor.textHighlight, 1));
		
		JLabel lblCarProbability = new JLabel("Car Probability");
		lblCarProbability.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCarProbability.setForeground(SystemColor.textHighlight);
		inputPanel.add(lblCarProbability);
		
		textField_5 = new JTextField();
		inputPanel.add(textField_5);
		textField_5.setColumns(10);
		textField_5.setBorder(new LineBorder(SystemColor.textHighlight, 1));
		
		JLabel lblSmallCarProbability = new JLabel("Small Car Probability");
		lblSmallCarProbability.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSmallCarProbability.setForeground(SystemColor.textHighlight);
		inputPanel.add(lblSmallCarProbability);
		
		textField_6 = new JTextField();
		inputPanel.add(textField_6);
		textField_6.setColumns(10);
		textField_6.setBorder(new LineBorder(SystemColor.textHighlight, 1));
		
		JLabel lblMotorcycleProbability = new JLabel("MotorCycle Probability");
		lblMotorcycleProbability.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMotorcycleProbability.setForeground(SystemColor.textHighlight);
		inputPanel.add(lblMotorcycleProbability);
		
		textField_7 = new JTextField();
		inputPanel.add(textField_7);
		textField_7.setColumns(10);
		textField_7.setBorder(new LineBorder(SystemColor.textHighlight, 1));
		
		JLabel lblSdStay = new JLabel("SD Stay");
		lblSdStay.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSdStay.setForeground(SystemColor.textHighlight);
		inputPanel.add(lblSdStay);
		
		textField_8 = new JTextField();
		inputPanel.add(textField_8);
		textField_8.setColumns(10);
		textField_8.setBorder(new LineBorder(SystemColor.textHighlight, 1));
		
		JLabel lblSeed = new JLabel("Seed");
		lblSeed.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSeed.setForeground(SystemColor.textHighlight);
		inputPanel.add(lblSeed);
		
		textField_9 = new JTextField();
		inputPanel.add(textField_9);
		textField_9.setColumns(10);
		textField_9.setBorder(new LineBorder(SystemColor.textHighlight, 1));
		
		JButton btnNewButton = new JButton("Start");
		inputPanel.add(btnNewButton);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
	}

}
