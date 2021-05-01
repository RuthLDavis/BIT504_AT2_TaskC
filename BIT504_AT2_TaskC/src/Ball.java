import java.awt.Color;

public class Ball extends Sprite {
	
	// create the constant variables for the Ball class
	private static final Color BALL_COLOUR = Color.YELLOW;
	private static final int BALL_WIDTH = 25;
	private static final int BALL_HEIGHT = 25;
	
	// constructor to set the colour, width and height of the ball. 
	
	public Ball(int panelWidth, int panelHeight)
	{
		setWidth(BALL_WIDTH);
		setHeight(BALL_HEIGHT);
		setspriteColor(BALL_COLOUR);
		setInitialPosition(panelWidth / 2 - (getWidth() / 2), panelHeight / 2 - (getHeight() / 2) );
		resetToInitialPosition();

	}
} 
