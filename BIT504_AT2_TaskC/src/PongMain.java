import javax.swing.JFrame;

 

public class PongMain extends JFrame {

	// set the constant variables to private so they cannot be changed by other parts of the application. 
	// we make them constant as they are unlikely to change but also we create variables for these so the value is only defined in one place. 
	// any other part of the application that needs to use these values will use the variable name rather than the value directly. This will
	// make changes to these variables simple as the value only needs to be changed in the one place and it will flow through to wherever the
	// variable has been used.
	
	private final static String WINDOW_TITLE = "Pong";
	private final static int WINDOW_WIDTH = 800;
	private final static int WINDOW_HEIGHT = 600; 

	public PongMain() {

		setTitle(WINDOW_TITLE);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);				// sets the window size (width, height) used the constant variables here 
		setResizable(false);
		add(new PongPanel());
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

 

	public static void main(String[] args) 
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable() 	// method to delay the GUI creation task until the initial threads task are complete
		{
			public void run ()
			{
				new PongMain();
			}
		});	

	}

 

}