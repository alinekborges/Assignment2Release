/**
 * 
 * This file is part of the CarParkSimulator Project, written as 
 * part of the assessment for INB370, semester 1, 2014. 
 *
 * CarParkSimulator
 * asgn2Simulators 
 * 20/04/2014
 * 
 */
package asgn2Simulators;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import java.awt.*;

/**
 * @author hogan
 * 
 */
@SuppressWarnings("serial")
public class GUISimulator extends JFrame implements Runnable {

	private static final long serialVersionUID = -7031008862559936404L;
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 576;
	
	public static final int TITLE_HEIGHT = (int) (HEIGHT*0.1);
	public static final int TITLE_WIDTH = WIDTH;
	public static final int INPUT_HEIGHT = (int) (HEIGHT * 0.7);
	public static final int INPUT_WIDTH = (int) (WIDTH*0.15);
	public static final int RUN_HEIGHT = (int) (HEIGHT*0.2);
	public static final int RUN_WIDHT = INPUT_WIDTH;	
	public static final int OUTPUT_HEIGHT = HEIGHT - TITLE_HEIGHT;
	public static final int OUTPUT_WIDTH = WIDTH - INPUT_WIDTH;

	private JPanel titlePanel;
	private JPanel inputPanel;
	private JPanel runPanel;
	private JPanel outputPanel;
	
	private Button startButton;
	//private //

	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public GUISimulator(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub

	}

	private void createGUI() {
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		//widht = full panel
		//height = 10% panel
		titlePanel = createPanel(Color.BLUE , TITLE_WIDTH, TITLE_HEIGHT);		
		inputPanel = createPanel(Color.CYAN, INPUT_WIDTH, INPUT_HEIGHT);
		runPanel = createPanel(Color.GRAY, RUN_WIDHT, RUN_HEIGHT);
		outputPanel = createPanel(Color.magenta, OUTPUT_WIDTH, OUTPUT_WIDTH);
		
		this.getContentPane().add(titlePanel, BorderLayout.NORTH);
		this.getContentPane().add(inputPanel, BorderLayout.WEST);
		this.getContentPane().add(runPanel, BorderLayout.SOUTH);
		this.getContentPane().add(outputPanel, BorderLayout.EAST);
		
		repaint();
		this.setVisible(true);

	}
	
	private JTextArea createTextArea() {
		JTextArea jta = new JTextArea(); 
		jta.setEditable(false);
		jta.setLineWrap(true);
		jta.setFont(new Font("Arial",Font.BOLD,24));
		jta.setBorder(BorderFactory.createEtchedBorder());
		return jta;
	}

	private JPanel createPanel(Color c, int width, int height) {
		// Create a JPanel object and store it in a local var
		// set the background colour to that passed in c
		// Return the JPanel object
		Dimension d = new Dimension(width, height);
		JPanel jp = new JPanel();
		jp.setBackground(c);
		jp.setPreferredSize(d);		
		return jp;

	}
	
	private void layoutButtonPanel() {
		GridBagLayout layout = new GridBagLayout();
	    //pnlBtn.setLayout(layout);
	    
	    //add components to grid
	    GridBagConstraints constraints = new GridBagConstraints(); 
	    
	    //Defaults
	    constraints.fill = GridBagConstraints.NONE;
	    constraints.anchor = GridBagConstraints.CENTER;
	    constraints.weightx = 100;
	    constraints.weighty = 100;
	    
	    //addToPanel(pnlBtn, btnLoad,constraints,0,0,2,1); 
	    //addToPanel(pnlBtn, btnUnload,constraints,3,0,2,1); 
	    //addToPanel(pnlBtn, btnFind,constraints,0,2,2,1); 
	    //addToPanel(pnlBtn, btnSwitch,constraints,3,2,2,1); 	
	}
	
	/**
     * 
     * A convenience method to add a component to given grid bag
     * layout locations. Code due to Cay Horstmann 
     *
     * @param c the component to add
     * @param constraints the grid bag constraints to use
     * @param x the x grid position
     * @param y the y grid position
     * @param w the grid width
     * @param h the grid height
     */
   private void addToPanel(JPanel jp,Component c, GridBagConstraints constraints, int x, int y, int w, int h) {  
      constraints.gridx = x;
      constraints.gridy = y;
      constraints.gridwidth = w;
      constraints.gridheight = h;
      jp.add(c, constraints);
   }
	
	private JButton createButton(String str) {
		JButton jb = new JButton(str); 
		//jb.addActionListener(this);
		return jb; 
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		createGUI();

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
        SwingUtilities.invokeLater(new GUISimulator("BorderLayout"));

	}

}
