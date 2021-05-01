import java.awt.Color;
import java.awt.Rectangle; 

public class Sprite {

	
	// declare the private variables for the Sprite class
	private int xPosition, yPosition;
	private int xVelocity, yVelocity; 
	private int width, height;
	private Color spriteColour;
	private int initialXPosition, initialYPosition;
	
	
	// get and set methods for the x and Y positions
	
	public int getxPosition()
	{
		return xPosition; 
	}

	public void setxPosition(int xPosition)
	{
		this.xPosition = xPosition;
	}
	
	public int getyPosition()
	{
		return yPosition; 
	}
	
	public void setyPosition(int yPosition)
	{
		this.yPosition = yPosition;
	}
	
	
	// check if the sprite is within the bounds of the screen.
	 public void setXPosition(int newX, int panelWidth) 
	 {
	     xPosition = newX;
	     	     
	     if (xPosition < 0)
	     {
	    	 xPosition = 0;
	     }
	     else if (xPosition + width > panelWidth) 
	     {
	    	 xPosition = panelWidth - width;
	     }
	 }

	 public void setYPosition(int newY, int panelHeight) 
	 {
		 yPosition = newY;
		 
		 if (yPosition < 0)
	     {
	    	 yPosition = 0;
	     }
	     else if (yPosition + height > panelHeight) 
	     {
	    	 yPosition = panelHeight - height;
	     }
		 
	 }
	 
	 
	 // method to set the initialXPosition and initialYPosition
	 
	 public void setInitialPosition (int initialX, int initialY)
	 {
		 initialXPosition = initialX;
		 initialYPosition = initialY;
	 }
	 
	 public void resetToInitialPosition()
	 {
		 setxPosition(initialXPosition);
		 setyPosition(initialYPosition);
	 }
	 
	
	// get and set methods for the velocity
	
	public int getxVelocity()
	{
		return xVelocity;
	}
	
	public void setxVelocity(int xVelocity)
	{
		this.xVelocity = xVelocity;
	}
	
	public int getyVelocity()
	{
		return yVelocity;
	}
	
	public void setyVelocity(int yVelocity)
	{
		this.yVelocity = yVelocity;
	}
	
	
	// get and set methods for the width and height
	
	public int getWidth()
	{
		return width;
	}
	
	public void setWidth(int width)
	{
		this.width = width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public void setHeight(int height)
	{
		this.height = height;
	}
	
	
	// get and set methods for the Sprite colour
	
	public Color getspriteColor() 
	{
		return spriteColour;
	}
	
	public void setspriteColor(Color spriteColour)
	{
		this.spriteColour = spriteColour;
	}
	
	
	// create a rectangle object that can be used for the ball and paddles
		public Rectangle getRectangle()
	{
		return new Rectangle(getxPosition(), getyPosition(), getWidth(), getHeight());
	}
}
