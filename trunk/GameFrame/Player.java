import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Player {
	
	public static int bombsNumLimit = 5;
	public static int bombsPowLimit = 3;
	public static String baseImage = "1 Camina.png";
	
	private int Xpos;
	private int Ypos;
	
	private int bombsNum;
	private int bombsPow;
	
	private BufferedImage playerImage; 
	
	
	public Player(int x, int y) {
		Xpos = x;
		Ypos = y;
		try{
		playerImage = ImageIO.read(getClass( ).getResource(baseImage));
		}catch(IOException ioe){
			System.out.println("Error loading image");
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
		return playerImage;
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

}
