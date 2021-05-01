import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Font;



public class PongPanel extends JPanel implements ActionListener, KeyListener {

	private final static Color PANEL_COLOUR = Color.BLACK;
	private final static  int TIMER_DELAY = 5;
	private final static int BALL_MOVEMENT_SPEED = 2;
	private final static int POINTS_TO_WIN = 3;
	private final static int SCORE_TEXT_X_PADDING = 100;
	private final static int SCORE_TEXT_Y_PADDING = 100;
	private final static int SCORE_FONT_SIZE = 50;
	private final static String SCORE_FONT_FAMILY = "Serif";
	private final static int WINNER_TEXT_X = 200;
    private final static int WINNER_TEXT_Y = 200;
    private final static int WINNER_FONT_SIZE = 40;
    private final static String WINNER_FONT_FAMILY = "Serif";

private final static String WINNER_TEXT = "WIN!";
	
	
	GameState gameState = GameState.Initialising;
	Ball ball;
	Paddle paddle1, paddle2;
	int player1Score = 0, player2Score = 0;
	Player gameWinner;
	

	public PongPanel()
	{
		setBackground(PANEL_COLOUR);
		Timer timer = new Timer(TIMER_DELAY, this);
		timer.start();
		addKeyListener(this);
		setFocusable(true);
	}
	
	
	// method to create objects
	public void createObjects()
	{
		ball =  new Ball(getWidth(), getHeight());
        paddle1 = new Paddle(Player.One, getWidth(), getHeight());
        paddle2 = new Paddle(Player.Two, getWidth(), getHeight());
	}
	
	
	
	private void update()
	{
		switch(gameState)
		{
			case Initialising:
			{
				createObjects();					// create the ball and paddles
				gameState = GameState.Playing;		// change the game state
				ball.setxVelocity(BALL_MOVEMENT_SPEED);
				ball.setyVelocity(BALL_MOVEMENT_SPEED);
				break;
			}
			case Playing: 
			{
				moveObject(paddle1);				// move paddle one
				moveObject(paddle2);				// move paddle one
				moveObject(ball);					// move the ball
				checkWallBounce();					// check for a collision with a wall
				checkPaddleBounce();				// check for a collision with a paddle
				checkWin();							// check if a player has enough points to win
				break;
			}
			case GameOver: 
			{
				break;
			}
		}
	}
	
	
	// method to paint the dotted line
	private void paintDottedLine(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g.create();
		Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] {9}, 0);
		g2d.setStroke(dashed);
		g2d.setPaint(Color.WHITE);
		g2d.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
		g2d.dispose();
	}
	
	
	
	//method to paint the Sprites
	public void paintSprite(Graphics g, Sprite sprite)
	{
		g.setColor(sprite.getspriteColor());
		g.fillRect(sprite.getxPosition(), sprite.getyPosition(), sprite.getWidth(), sprite.getHeight());
	}
	
	// method to paint the scores to the screen
	private void paintScores(Graphics g)
	{
		Font scoreFont = new Font(SCORE_FONT_FAMILY, Font.BOLD, SCORE_FONT_SIZE);
		String leftScore = Integer.toString(player1Score);
		String rightScore = Integer.toString(player2Score);
		g.setFont(scoreFont);
		g.drawString(leftScore, SCORE_TEXT_X_PADDING, SCORE_TEXT_Y_PADDING);
		g.drawString(rightScore, getWidth()-SCORE_TEXT_X_PADDING, SCORE_TEXT_Y_PADDING);
	}
	
	// method to paint the win message to the screen
	private void paintWinner(Graphics g)
	{
        if(gameWinner != null)
        {
           Font winnerFont = new Font(WINNER_FONT_FAMILY, Font.BOLD, WINNER_FONT_SIZE);
           g.setFont(winnerFont);
           int xPosition = getWidth() / 2;
           
           if(gameWinner == Player.One) 
           {
               xPosition -= WINNER_TEXT_X;
           } 
           else if(gameWinner == Player.Two) 
           {
               xPosition += WINNER_TEXT_X;
           }
           g.drawString(WINNER_TEXT, xPosition, WINNER_TEXT_Y);
       }
	}
	
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		paintDottedLine(g);
		if (gameState != GameState.Initialising)
		{
			paintSprite(g, ball);
			paintSprite(g, paddle1);
			paintSprite(g, paddle2);
			paintScores(g);
			paintWinner(g);
		}
		
		}
	
	private void moveObject(Sprite obj)
	{
		obj.setXPosition(obj.getxPosition() + obj.getxVelocity(), getWidth());
		obj.setYPosition(obj.getyPosition() + obj.getyVelocity(), getHeight());
	}

	private void checkWallBounce()
	{
		
		if (ball.getxPosition() <= 0)				// if hits the left side of the screen
		{
			ball.setxVelocity(-ball.getxVelocity());
			addScore(Player.Two);
			resetBall();
		}
		else if (ball.getxPosition() >= getWidth() - ball.getWidth())			// if hits the right side of the screen
		{
			ball.setxVelocity(-ball.getxVelocity());
			addScore(Player.One);
			resetBall();
		}
		
	      if(ball.getyPosition() <= 0 || ball.getyPosition() >= getHeight() - ball.getHeight())   // if it hits the top or bottom of the screen
	      {
	          ball.setyVelocity(-ball.getyVelocity());
	      }
	}
	
	private void resetBall()
	{
		ball.resetToInitialPosition();
	}
	
	private void checkPaddleBounce()
	{
		if(ball.getxVelocity() < 0 && ball.getRectangle().intersects(paddle1.getRectangle()))
		{
			ball.setxVelocity(BALL_MOVEMENT_SPEED);
		}
		
		if(ball.getxVelocity() > 0 && ball.getRectangle().intersects(paddle2.getRectangle()))
		{
			ball.setxVelocity(-BALL_MOVEMENT_SPEED);
		}
	}
	
	
	private void addScore(Player player)
	{
		if (player == Player.One)				// if player 1 scores 
		{
			player1Score ++;
		}
		else if(player == Player.Two)		// if player 2 scores
		{
			player2Score ++;
		}
	}
	
	private void checkWin()
	{
		if(player1Score == POINTS_TO_WIN)
		{
			gameWinner = Player.One;			
			gameState = GameState.GameOver;					// change the game state
		}
		
		if (player2Score == POINTS_TO_WIN)
		{
			gameWinner = Player.Two;
			gameState = GameState.GameOver;					// change the game state 
		}
	}
	
	
	@Override
	public void keyTyped(KeyEvent event) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent event) 
	{
		if(event.getKeyCode() == KeyEvent.VK_UP)
		{
			paddle2.setyVelocity(-3);
		}
		else if (event.getKeyCode() == KeyEvent.VK_DOWN)
		{
			paddle2.setyVelocity(3);
		}
		
		if (event.getKeyCode() == KeyEvent.VK_W)
		{
			paddle1.setyVelocity(-3);
		}
		else if (event.getKeyCode() == KeyEvent.VK_S)
		{
			paddle1.setyVelocity(3);
		}
	}

	@Override
	public void keyReleased(KeyEvent event) 
	{
		if(event.getKeyCode() == KeyEvent.VK_UP || event.getKeyCode() == KeyEvent.VK_DOWN)
		{
			paddle2.setyVelocity(0);
		}
		
		if (event.getKeyCode() == KeyEvent.VK_W || event.getKeyCode() == KeyEvent.VK_S)
		{
			paddle1.setyVelocity(0);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent event) 
	{
		// TODO Auto-generated method stub
		update();
		repaint();

	}
	
	

}
