package asgn2Simulators;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultCaret;

import asgn2CarParks.CarPark;
import asgn2Exceptions.SimulationException;
import asgn2Exceptions.VehicleException;

public class GUISimulator implements MouseListener, Runnable {

	private static CarPark carPark;
	private static Simulator sim;
	private static Log log;

	private static double meanStay = Constants.DEFAULT_INTENDED_STAY_MEAN;
	private static double sdStay = Constants.DEFAULT_INTENDED_STAY_SD;
	private static int seed = Constants.DEFAULT_SEED;
	private static double carProb = Constants.DEFAULT_CAR_PROB;
	private static double smallCarProb = Constants.DEFAULT_SMALL_CAR_PROB;
	private static double mcProb = Constants.DEFAULT_MOTORCYCLE_PROB;

	private static int maxCarSpaces = Constants.DEFAULT_MAX_CAR_SPACES;
	private static int maxSmallCarSpaces = Constants.DEFAULT_MAX_SMALL_CAR_SPACES;
	private static int maxMotorCycleSpaces = Constants.DEFAULT_MAX_MOTORCYCLE_SPACES;
	private static int maxQueueSize = Constants.DEFAULT_MAX_QUEUE_SIZE;

	private JFrame frame;
	private JTextField textField_MaxCarSpaces;
	private JTextField textField_MaxSmallCarSpaces;
	private JTextField textField_MaxMotorcycleSpaces;
	private JTextField textField_MaxQueueSize;
	private JTextField textField_MeanStay;
	private JTextField textField_CarProbability;
	private JTextField textField_SmallCarProbability;
	private JTextField textField_MotorcycleProbability;

	private static Color redColor = new Color(204, 0, 51);
	private static Color darkBlue = new Color(0, 0, 128);

	public static final int WIDTH = 1024;
	public static final int HEIGHT = 576;
	public static final int INPUT_WIDTH = (int) (WIDTH * 0.15);
	private JTextField textField_SDStay;
	private JTextField textField_Seed;
	private JPanel runSimulationButton;
	private JPanel cardLayout_1;
	private JTextArea logTextArea;
	private JLabel StatusLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		// CarPark cp = new CarPark();
		// Simulator s = null;
		// Log l = null;

		if (args.length == 10) {
			// Setup with cmd line args

			maxCarSpaces = Integer.parseInt(args[0]);
			maxSmallCarSpaces = Integer.parseInt(args[1]);
			maxMotorCycleSpaces = Integer.parseInt(args[2]);
			maxQueueSize = Integer.parseInt(args[3]);

			meanStay = Double.parseDouble(args[8]);
			sdStay = Double.parseDouble(args[9]);
			seed = Integer.parseInt(args[4]);
			carProb = Double.parseDouble(args[5]);
			smallCarProb = Double.parseDouble(args[6]);
			mcProb = Double.parseDouble(args[7]);

			carPark = new CarPark(maxCarSpaces, maxSmallCarSpaces,
					maxMotorCycleSpaces, maxQueueSize);
			try {
				sim = new Simulator(seed, meanStay, sdStay, carProb,
						smallCarProb, mcProb);
				log = new Log();
			} catch (IOException | SimulationException e1) {
				e1.printStackTrace();
				System.exit(-1);
			}

		} else {
			if (args.length == 0) {
				// Use Defaults
				carPark = new CarPark();
				try {
					sim = new Simulator();
					log = new Log();
				} catch (IOException | SimulationException e1) {
					e1.printStackTrace();
					System.exit(-1);
				}
			} else {
				// Insufficient arguments in args
				System.out.println("ERROR: Not enough parameters");
				System.exit(-1);
			}
		}

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

	@Override
	public void run() {
		// TODO Auto-generated method stub
	}

	/**
	 * Create the application.
	 */
	public GUISimulator() {
		initialize();
		initializeInputFields();
	}

	private void initializeInputFields() {

		this.textField_MaxCarSpaces.setText(String.valueOf(maxCarSpaces));
		this.textField_MaxSmallCarSpaces.setText(String
				.valueOf(maxSmallCarSpaces));
		this.textField_MaxMotorcycleSpaces.setText(String
				.valueOf(maxMotorCycleSpaces));
		this.textField_MaxQueueSize.setText(String.valueOf(maxQueueSize));

		this.textField_CarProbability.setText(String.valueOf(carProb));
		this.textField_MotorcycleProbability.setText(String.valueOf(mcProb));
		this.textField_SmallCarProbability
				.setText(String.valueOf(smallCarProb));

		this.textField_SDStay.setText(String.valueOf(sdStay));
		this.textField_Seed.setText(String.valueOf(seed));
		this.textField_MeanStay.setText(String.valueOf(meanStay));

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setForeground(Color.WHITE);
		frame.setBackground(Color.WHITE);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);

		JPanel titlePanel = new JPanel();
		titlePanel.setBorder(new EmptyBorder(5, 0, 8, 0));
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

		textField_MaxCarSpaces = new JTextField();
		textField_MaxCarSpaces.setHorizontalAlignment(SwingConstants.CENTER);
		textField_MaxCarSpaces.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_MaxCarSpaces.setForeground(new Color(0, 0, 128));
		inputPanel.add(textField_MaxCarSpaces);
		textField_MaxCarSpaces.setColumns(10);
		textField_MaxCarSpaces
				.setBorder(new LineBorder(new Color(51, 153, 255)));
		textField_MaxCarSpaces.getDocument().addDocumentListener(
				new notNegativeChecker(textField_MaxCarSpaces));

		JLabel lblMaxSmallCar = new JLabel("Max Small Car Spaces");
		lblMaxSmallCar.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMaxSmallCar.setForeground(SystemColor.textHighlight);
		lblMaxSmallCar.setBackground(Color.WHITE);
		inputPanel.add(lblMaxSmallCar);

		textField_MaxSmallCarSpaces = new JTextField();
		textField_MaxSmallCarSpaces
				.setHorizontalAlignment(SwingConstants.CENTER);
		textField_MaxSmallCarSpaces.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_MaxSmallCarSpaces.setForeground(new Color(0, 0, 128));
		inputPanel.add(textField_MaxSmallCarSpaces);
		textField_MaxSmallCarSpaces.setColumns(10);
		textField_MaxSmallCarSpaces.setBorder(new LineBorder(
				SystemColor.textHighlight, 1));
		textField_MaxSmallCarSpaces.getDocument().addDocumentListener(
				new notNegativeChecker(textField_MaxSmallCarSpaces));

		JLabel lblNewLabel = new JLabel("Max Motorcycle Spaces");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setForeground(SystemColor.textHighlight);
		inputPanel.add(lblNewLabel);

		textField_MaxMotorcycleSpaces = new JTextField();
		textField_MaxMotorcycleSpaces
				.setHorizontalAlignment(SwingConstants.CENTER);
		textField_MaxMotorcycleSpaces
				.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_MaxMotorcycleSpaces.setForeground(new Color(0, 0, 128));
		inputPanel.add(textField_MaxMotorcycleSpaces);
		textField_MaxMotorcycleSpaces.setColumns(10);
		textField_MaxMotorcycleSpaces.setBorder(new LineBorder(
				SystemColor.textHighlight, 1));
		textField_MaxMotorcycleSpaces.getDocument().addDocumentListener(
				new notNegativeChecker(textField_MaxMotorcycleSpaces));

		JLabel lblNewLabel_1 = new JLabel("Max Queue Size");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setForeground(SystemColor.textHighlight);
		inputPanel.add(lblNewLabel_1);

		textField_MaxQueueSize = new JTextField();
		textField_MaxQueueSize.setHorizontalAlignment(SwingConstants.CENTER);
		textField_MaxQueueSize.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_MaxQueueSize.setForeground(new Color(0, 0, 128));
		inputPanel.add(textField_MaxQueueSize);
		textField_MaxQueueSize.setColumns(10);
		textField_MaxQueueSize.setBorder(new LineBorder(
				SystemColor.textHighlight, 1));
		textField_MaxQueueSize.getDocument().addDocumentListener(
				new notNegativeChecker(textField_MaxQueueSize));

		JLabel lblNewLabel_2 = new JLabel("Mean Stay");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setForeground(SystemColor.textHighlight);
		inputPanel.add(lblNewLabel_2);

		textField_MeanStay = new JTextField();
		textField_MeanStay.setHorizontalAlignment(SwingConstants.CENTER);
		textField_MeanStay.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_MeanStay.setForeground(new Color(0, 0, 128));
		inputPanel.add(textField_MeanStay);
		textField_MeanStay.setColumns(10);
		textField_MeanStay.setBorder(new LineBorder(SystemColor.textHighlight,
				1));
		textField_MeanStay.getDocument().addDocumentListener(
				new notNegativeChecker(textField_MeanStay));

		JLabel lblCarProbability = new JLabel("Car Probability");
		lblCarProbability.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCarProbability.setForeground(SystemColor.textHighlight);
		inputPanel.add(lblCarProbability);

		textField_CarProbability = new JTextField();
		textField_CarProbability.setHorizontalAlignment(SwingConstants.CENTER);
		textField_CarProbability.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_CarProbability.setForeground(new Color(0, 0, 128));
		inputPanel.add(textField_CarProbability);
		textField_CarProbability.setColumns(10);
		textField_CarProbability.setBorder(new LineBorder(
				SystemColor.textHighlight, 1));
		textField_CarProbability.getDocument().addDocumentListener(
				new ProbabilityChecker(textField_CarProbability));

		JLabel lblSmallCarProbability = new JLabel("Small Car Probability");
		lblSmallCarProbability.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSmallCarProbability.setForeground(SystemColor.textHighlight);
		inputPanel.add(lblSmallCarProbability);

		textField_SmallCarProbability = new JTextField();
		textField_SmallCarProbability
				.setHorizontalAlignment(SwingConstants.CENTER);
		textField_SmallCarProbability
				.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_SmallCarProbability.setForeground(new Color(0, 0, 128));
		inputPanel.add(textField_SmallCarProbability);
		textField_SmallCarProbability.setColumns(10);
		textField_SmallCarProbability.setBorder(new LineBorder(
				SystemColor.textHighlight, 1));
		textField_SmallCarProbability.getDocument().addDocumentListener(
				new ProbabilityChecker(textField_SmallCarProbability));

		JLabel lblMotorcycleProbability = new JLabel("MotorCycle Probability");
		lblMotorcycleProbability.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMotorcycleProbability.setForeground(SystemColor.textHighlight);
		inputPanel.add(lblMotorcycleProbability);

		textField_MotorcycleProbability = new JTextField();
		textField_MotorcycleProbability
				.setHorizontalAlignment(SwingConstants.CENTER);
		textField_MotorcycleProbability.setFont(new Font("Tahoma", Font.BOLD,
				12));
		textField_MotorcycleProbability.setForeground(new Color(0, 0, 128));
		inputPanel.add(textField_MotorcycleProbability);
		textField_MotorcycleProbability.setColumns(10);
		textField_MotorcycleProbability.setBorder(new LineBorder(
				SystemColor.textHighlight, 1));
		textField_MotorcycleProbability.getDocument().addDocumentListener(
				new ProbabilityChecker(textField_MotorcycleProbability));

		JLabel lblSdStay = new JLabel("SD Stay");
		lblSdStay.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSdStay.setForeground(SystemColor.textHighlight);
		inputPanel.add(lblSdStay);

		textField_SDStay = new JTextField();
		textField_SDStay.setHorizontalAlignment(SwingConstants.CENTER);
		textField_SDStay.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_SDStay.setForeground(new Color(0, 0, 128));
		inputPanel.add(textField_SDStay);
		textField_SDStay.setColumns(10);
		textField_SDStay
				.setBorder(new LineBorder(SystemColor.textHighlight, 1));
		textField_SDStay.getDocument().addDocumentListener(
				new notNegativeChecker(textField_SDStay));

		JLabel lblSeed = new JLabel("Seed");
		lblSeed.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSeed.setForeground(SystemColor.textHighlight);
		inputPanel.add(lblSeed);

		textField_Seed = new JTextField();
		textField_Seed.setHorizontalAlignment(SwingConstants.CENTER);
		textField_Seed.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_Seed.setForeground(new Color(0, 0, 128));
		inputPanel.add(textField_Seed);
		textField_Seed.setColumns(10);
		textField_Seed.setBorder(new LineBorder(SystemColor.textHighlight, 1));
		textField_Seed.getDocument().addDocumentListener(
				new notNegativeChecker(textField_Seed));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(204, 0, 51));
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		cardLayout_1 = new JPanel();
		cardLayout_1.setBorder(new LineBorder(SystemColor.textHighlight, 4));
		cardLayout_1.setBackground(Color.WHITE);
		tabbedPane.addTab("Text LOG", null, cardLayout_1,
				"show the text log output");
		cardLayout_1.setLayout(new CardLayout(0, 0));

		JPanel startPanel = new JPanel();
		startPanel.setBackground(Color.WHITE);
		cardLayout_1.add(startPanel, "RUN");
		startPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 180));

		runSimulationButton = new JPanel();
		runSimulationButton.setBorder(new EmptyBorder(25, 25, 25, 25));
		runSimulationButton.setBackground(SystemColor.textHighlight);
		runSimulationButton.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		runSimulationButton.addMouseListener(this);

		JLabel runSimulationLabel = new JLabel("Run Simulation");
		runSimulationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		runSimulationLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		runSimulationLabel.setForeground(Color.WHITE);
		runSimulationLabel.setBackground(Color.WHITE);
		runSimulationButton.add(runSimulationLabel);
		startPanel.add(runSimulationButton);
		
		StatusLabel = new JLabel("");
		StatusLabel.setForeground(new Color(204, 0, 0));
		startPanel.add(StatusLabel);
		
		JPanel panel = new JPanel();
		startPanel.add(panel);

		JPanel textLogPanel_1 = new JPanel();
		textLogPanel_1.setBackground(Color.WHITE);
		cardLayout_1.add(textLogPanel_1, "LOG");

		logTextArea = new JTextArea();
		logTextArea.setText("Simulation log");
		logTextArea.setForeground(new Color(0, 0, 102));
		logTextArea.setColumns(50);
		logTextArea.setLineWrap(true);
		logTextArea.setSize(400, 400);
		// textLogPanel_1.add(logTextArea);

		DefaultCaret caret = (DefaultCaret) logTextArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		textLogPanel_1.setLayout(null);

		JScrollPane scroll = new JScrollPane(logTextArea);
		scroll.setLocation(5, 5);
		scroll.setSize(new Dimension(700, 400));
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		textLogPanel_1.add(scroll);

		// JScrollPane scroll = new JScrollPane ( logTextArea );
		// scroll.setVerticalScrollBarPolicy (
		// ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
	}

	/**
	 * Method to run the simulation from start to finish. Exceptions are
	 * propagated upwards from Vehicle, Simulation and Log objects as necessary
	 * 
	 * @throws VehicleException
	 *             if Vehicle creation or operation constraints violated
	 * @throws SimulationException
	 *             if Simulation constraints are violated
	 * @throws IOException
	 *             on logging failures
	 */
	private void runSimulation() throws VehicleException, SimulationException,
			IOException {

		String buffer = "";
		logTextArea.setText("Initializing Simulation\n");
		log.initialEntry(carPark, sim);
		for (int time = 0; time <= Constants.CLOSING_TIME; time++) {
			// queue elements exceed max waiting time
			if (!carPark.queueEmpty()) {
				carPark.archiveQueueFailures(time);
			}
			// vehicles whose time has expired
			if (!carPark.carParkEmpty()) {
				// force exit at closing time, otherwise normal
				boolean force = (time == Constants.CLOSING_TIME);
				carPark.archiveDepartingVehicles(time, force);
			}
			// attempt to clear the queue
			if (!carPark.carParkFull()) {
				carPark.processQueue(time, sim);
			}
			// new vehicles from minute 1 until the last hour
			if (newVehiclesAllowed(time)) {
				carPark.tryProcessNewVehicles(time, sim);
			}
			// Log progress
			String status = carPark.getStatus(time);
			log.writer.write(status);
			// this.logTextArea.append(status + "\n");
			buffer += (status);
		}
		logTextArea.setText(buffer);
		log.finalise(carPark);
	}

	/**
	 * Helper method to determine if new vehicles are permitted
	 * 
	 * @param time
	 *            int holding current simulation time
	 * @return true if new vehicles permitted, false if not allowed due to
	 *         simulation constraints.
	 */
	private boolean newVehiclesAllowed(int time) {
		boolean allowed = (time >= 1);
		return allowed && (time <= (Constants.CLOSING_TIME - 60));
	}

	/*
	 * 
	 */
	private class ProbabilityChecker implements DocumentListener {

		private JTextField textField;

		public ProbabilityChecker(JTextField tf) {
			this.textField = tf;
		}

		@Override
		public void changedUpdate(DocumentEvent arg0) {
			check();
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			check();

		}

		@Override
		public void removeUpdate(DocumentEvent arg0) {
			check();
		}

		public void check() {
			if (invalidProbability(textField.getText())) {
				setInvalidTextField(textField);
			} else {
				setValidTextField(textField);
			}
		}

	};

	private class notNegativeChecker implements DocumentListener {

		JTextField textField;

		public notNegativeChecker(JTextField tf) {
			this.textField = tf;
		}

		@Override
		public void changedUpdate(DocumentEvent arg0) {
			check();
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			check();

		}

		@Override
		public void removeUpdate(DocumentEvent arg0) {
			check();
		}

		public void check() {
			if (isNotNegative(textField.getText())) {
				setValidTextField(textField);
			} else {
				setInvalidTextField(textField);
			}
		}

		public boolean isNotNegative(String value) {
			double v = parseDouble(value);
			if (v >= 0) {
				return true;
			} else {
				return false;
			}
		}

	};

	private static boolean isNotNegative(String value) {
		double v = parseDouble(value);
		if (v >= 0) {
			return true;
		} else {
			return false;
		}
	}

	private static void setInvalidTextField(JTextField tf) {
		tf.setBorder(new LineBorder(redColor, 1));
	}

	private static void setValidTextField(JTextField tf) {
		tf.setBorder(new LineBorder(SystemColor.textHighlight, 1));
	}

	private static double parseDouble(String d) {
		try {
			return Double.parseDouble(d);
		} catch (Exception ex) {
			try {
				return Integer.parseInt(d);
			} catch (Exception ex2) {
				return -1;
			}
		}
	}

	private boolean invalidProbability(String prob) {
		double d = parseDouble(prob);
		return (d < 0.0) || (d > 1.0);
	}
	
	private boolean invalidProbability(Double d) {
		return (d < 0.0) || (d > 1.0);
	}

	private void checkInputsAndRun() throws Exception {

		int maxCarSpaces;
		int maxSmallCarSpaces;
		int maxMotorcycleSpaces;
		int maxQueueSize;
		double meanStay;
		double carProb;
		double smallCarProb;
		double mcProb;
		double sdStay;
		int seed;

		// try parse all values
		try {
			maxCarSpaces = Integer.parseInt(this.textField_MaxCarSpaces
					.getText());
			if (maxCarSpaces < 0) {
				throw new Exception();
			}
		} catch (Exception ex) {
			throw new Exception(" \"" + this.textField_MaxCarSpaces.getText()
					+ "\" is not a valid entry to Max Car Spaces");
		}
		
		try {
			maxSmallCarSpaces = Integer.parseInt(this.textField_MaxSmallCarSpaces
					.getText());
			if (maxSmallCarSpaces < 0) {
				throw new Exception();
			}
		} catch (Exception ex) {
			throw new Exception(" \"" + this.textField_MaxSmallCarSpaces.getText()
					+ "\" is not a valid entry to Max Small Car Spaces");
		}
		
		try {
			maxMotorcycleSpaces = Integer.parseInt(this.textField_MaxMotorcycleSpaces
					.getText());
			if (maxMotorcycleSpaces < 0) {
				throw new Exception();
			}
		} catch (Exception ex) {
			throw new Exception(" \"" + this.textField_MaxMotorcycleSpaces.getText()
					+ "\" is not a valid entry to Max Motorcycle Spaces");
		}
		
		try {
			maxQueueSize = Integer.parseInt(this.textField_MaxQueueSize
					.getText());
			if (maxQueueSize < 0) {
				throw new Exception();
			}
		} catch (Exception ex) {
			throw new Exception(" \"" + this.textField_MaxQueueSize.getText()
					+ "\" is not a valid entry to Max Queue Size");
		}
		
		try {
			meanStay = Double.parseDouble(this.textField_MeanStay
					.getText());
			if (meanStay < 0) {
				throw new Exception();
			}
		} catch (Exception ex) {
			throw new Exception(" \"" + this.textField_MeanStay.getText()
					+ "\" is not a valid entry to Max Car Spaces");
		}
		
		try {
			sdStay = Double.parseDouble(this.textField_SDStay
					.getText());
			if (sdStay < 0) {
				throw new Exception();
			}
		} catch (Exception ex) {
			throw new Exception(" \"" + this.textField_SDStay.getText()
					+ "\" is not a valid entry to Max Car Spaces");
		}
		
		try {
			seed = Integer.parseInt(this.textField_Seed
					.getText());
			if (seed < 0) {
				throw new Exception();
			}
		} catch (Exception ex) {
			throw new Exception(" \"" + this.textField_Seed.getText()
					+ "\" is not a valid entry to Max Car Spaces");
		}
		
		try {
			carProb = Double.parseDouble(this.textField_CarProbability
					.getText());
			if (invalidProbability(carProb)) {
				throw new Exception();
			}
		} catch (Exception ex) {
			throw new Exception(" \"" + this.textField_CarProbability.getText()
					+ "\" is not a valid entry to Max Car Spaces");
		}
		
		try {
			smallCarProb = Double.parseDouble(this.textField_SmallCarProbability
					.getText());
			if (invalidProbability(smallCarProb)) {
				throw new Exception();
			}
		} catch (Exception ex) {
			throw new Exception(" \"" + this.textField_SmallCarProbability.getText()
					+ "\" is not a valid entry to Max Car Spaces");
		}
		
		try {
			mcProb = Double.parseDouble(this.textField_MotorcycleProbability
					.getText());
			if (invalidProbability(mcProb)) {
				throw new Exception();
			}
		} catch (Exception ex) {
			throw new Exception(" \"" + this.textField_MotorcycleProbability.getText()
					+ "\" is not a valid entry to Max Car Spaces");
		}
		
		if (maxCarSpaces < maxSmallCarSpaces) {
			throw new Exception("Max Car Spaces should be more or equal than small car");
		}

		carPark = new CarPark(maxCarSpaces, maxSmallCarSpaces,
				maxMotorCycleSpaces, maxQueueSize);
		sim = new Simulator(seed, meanStay, sdStay,
				carProb, smallCarProb, mcProb);
		runSimulation();
		

	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// Run the simulation
		// SimulationRunner sr = new SimulationRunner(carPark, sim, log);
		try {
			CardLayout cl = (CardLayout) cardLayout_1.getLayout();
			cl.show(cardLayout_1, "LOG");
			checkInputsAndRun();			

		} catch (VehicleException | SimulationException | IOException exception) {
			System.exit(-1);
		} catch (Exception ex) {
			StatusLabel.setText(ex.getMessage());
			CardLayout cl = (CardLayout) cardLayout_1.getLayout();
			cl.show(cardLayout_1, "RUN");
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.runSimulationButton.setBackground(this.darkBlue);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.runSimulationButton.setBackground(SystemColor.textHighlight);

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public JPanel getCardLayout() {
		return cardLayout_1;
	}

	public JTextArea getLogTextArea() {
		return logTextArea;
	}
	public JLabel getStatusLabel() {
		return StatusLabel;
	}
}
