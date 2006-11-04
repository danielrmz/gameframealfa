import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player {
	
	public static final int bombsNumLimit = 5;
	public static final int bombsPowLimit = 3;
	public static final char UP = 'U';
	public static final char DOWN = 'D';
	public static final char LEFT = 'L';
	public static final char RIGTH = 'R';
	public static final int stripLength = 6;

	public static String baseImage = "Base.png";

	
	private int Xpos;
	private int Ypos;
	
	private int bombsNum;
	private int bombsPow;
	
	private boolean isMoving;
	private char direction;
	
	private BufferedImage playerImage; 
	
	private int counter;
	
	
	public Player(int x, int y) {
		Xpos = x;
		Ypos = y;
		isMoving = false;
		counter = 0;
		direction = 'D';
	}
	
	public BufferedImage loadImage(String url){
		BufferedImage auxImage = null;
		try{
			auxImage = ImageIO.read(getClass( ).getResource(url));
		}catch(IOException ioe){
			System.out.println("Error loading image");
		}
		return auxImage;
	}
	
	public void draw(Graphics2D g){
		g.drawImage(getPlayerImage(),Xpos,Ypos,null);
	}
	
	public void moveLeft(){
		if(this.checkMovement("X",-1)){
			Xpos-=5;
			counter++;
		}
	}
	public void moveDown(){
		if(this.checkMovement("Y",1)){	
			Ypos+=5;
			counter++;
		}
	}
	public void moveUp(){
		if(this.checkMovement("Y",-1)){
			Ypos-=5;
			counter++;
		}
	}
	public void moveRigth(){
		if(this.checkMovement("X",1)){
			Xpos+=5;
			counter++;
		}
	}


	public int getBombsNum() {
		return bombsNum;
	}


	public void setBombsNum(int bombsNum) {
		this.bombsNum = bombsNum;
	}


	public int getBombsPow() {
		return bombsPow;
	}


	public void setBombsPow(int bombsPow) {
		this.bombsPow = bombsPow;
	}


	public BufferedImage getPlayerImage() {
		String auxDir = ""+direction;
		if (isMoving && counter<6){
			switch(direction){
			case UP: 	
				moveUp(); 	
			break;
			case DOWN: 	
				moveDown();	
				break;
			case LEFT: 	
				moveLeft(); 
				break;
			case RIGTH: 
				moveRigth();	
			break;
			}
			playerImage = loadImage((""+counter)+auxDir+baseImage);
			return playerImage;
		}
		else{
			counter = 0;
			isMoving = false;
			playerImage = loadImage((""+counter)+auxDir+baseImage);
			return playerImage;
		}
	}


	public void setPlayerImage(BufferedImage playerImage) {
		this.playerImage = playerImage;
	}


	public int getXpos() {
		return Xpos;
	}


	public void setXpos(int xpos) {
		Xpos = xpos;
	}


	public int getYpos() {
		return Ypos;
	}


	public void setYpos(int ypos) {
		Ypos = ypos;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public boolean isMoving() {
		return isMoving;
	}

	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(char direction) {
		this.direction = direction;
	}

	public boolean checkMovement(String direction, int change){
		int i = (int)(((this.Xpos+50.0)/(double)PanelJuego.ANCHO)*10);
		int j = (int)(((this.Ypos+50.0)/(double)PanelJuego.ALTO)*10);
		int col = j;
		int row = i;
		System.out.println(i+":"+j);
		try {
			if(direction.equals("Y")){
				col = j + change;
			} else if(direction.equals("X")){
				row = i + change;
			}
					switch(PanelJuego.grid[row][col]){
					case PanelJuego.BLOQUE: 
						this.setMoving(false);
						return false;
					case PanelJuego.BOMBA: 
						break;
					case PanelJuego.POWERUP: 
						break;
					case PanelJuego.PERSONAJE: 
						break;
					}
			
					
		} catch(ArrayIndexOutOfBoundsException e){
			this.setMoving(false);
			return false;
		}
		return true;
	}
}
