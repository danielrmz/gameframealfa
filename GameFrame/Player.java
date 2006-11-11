import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player {

	public static int numPlayers = 100;
	public static final int bombsNumLimit = 5;
	public static final int bombsPowLimit = 3;
	public static final char UP = 'U';
	public static final char DOWN = 'D';
	public static final char LEFT = 'L';
	public static final char RIGTH = 'R';
	public static final int stripLength = 6;

	public static String baseImage = "Base.png";
	public String lastDir = "";
	
	private int Xpos;
	private int Ypos;
	
	private int activeBombs;
	private int bombsNum;
	private int bombsPow;
	
	public boolean isMoving;
	private char direction;
	
	private BufferedImage playerImage; 
	
	private int counter;
	private final int playerid;
	
	public static Point center(double x, double y){
		Point aux = new Point();
		aux.setLocation((x+23.5),(y+30.5));
		System.out.println(aux.getX()+" "+aux.getY());
		return aux;
	}
	
	
	public Player(int x, int y) {
		playerid = ++Player.numPlayers;
		Xpos = x;
		Ypos = y;
		counter = 0;
		direction = 'D';

		int[] pos = this.getCurrentPosition();
		PanelJuego.grid[pos[0]][pos[1]] = this.playerid;
		bombsNum = 2;
		activeBombs = 0;
		this.despliegaTablero();
		
	}
	
	public BufferedImage loadImage(String url){
		url = "img/"+url;
		BufferedImage auxImage = null;
		try{
			auxImage = ImageIO.read(getClass( ).getResource(url));
		}catch(IOException ioe){
			System.out.println("Error loading image");
		}catch(IllegalArgumentException e){}
		return auxImage;
	}
	
	public void draw(Graphics2D g){
		g.drawImage(getPlayerImage(),Xpos,Ypos,null);
		g.setColor(Color.YELLOW);
		try {
			g.drawLine(Xpos,Ypos,Xpos+this.playerImage.getWidth(),Ypos);
			g.drawLine(Xpos,Ypos,Xpos,Ypos+this.playerImage.getHeight());
			g.drawLine(Xpos+this.playerImage.getWidth(),Ypos,Xpos+this.playerImage.getWidth(),Ypos+this.playerImage.getHeight());
			g.setColor(Color.RED);
			g.drawLine(Xpos,Ypos+this.playerImage.getHeight(),Xpos+this.playerImage.getWidth(),Ypos+this.playerImage.getHeight());
		} catch(NullPointerException e){}
	}
	
	public void moveLeft(){
		int r = this.checkMovement("X",-1);
		Xpos-= (r>5)?5:r;
		counter++;
		
	}
	public void moveDown(){
		int r = this.checkMovement("Y",1);	
		Ypos+=(r>5)?5:r;
		counter++;
	}
	public void moveUp(){
		int r = this.checkMovement("Y",-1);
		Ypos-=(r>5)?5:r;
		counter++;		
	}
	public void moveRigth(){
		int r = this.checkMovement("X",1);
		Xpos+=(r>5)?5:r;
		counter++;
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
		String auxDir = "";//+direction;
		if (isMoving && counter<6){
			switch(direction){
			case UP: 
				lastDir="arriba/";
				moveUp(); 	
			break;
			case DOWN: 	
				lastDir="abajo/";	
				moveDown();	
				break;
			case LEFT: 	
				lastDir="izquierda/";
				moveLeft(); 
				break;
			case RIGTH: 
				lastDir="derecha/";	
				moveRigth();	
			break;
			}
			counter = (counter == 0)?1:counter; //-- La imagen inicial es 1 por lo que si el counter es 0 pone 1.
			String file = auxDir+lastDir+(""+counter)+baseImage;
			playerImage = loadImage(file);
		}
		else{
			counter = 0;
			//isMoving = false;
			String file = auxDir+(!lastDir.equals("")?lastDir+"1":"")+baseImage;
			playerImage = loadImage(file);
		}
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
	
	public int[] getCurrentPosition(){
		int position[] = new int[2];
		int width = PanelJuego.grid.length * 50;
		int height = PanelJuego.grid[0].length * 50;
		
		position[0] = (int)(((this.Xpos+50.0)/(double)width)*10);
		position[1] = (int)(((this.Ypos+50.0)/(double)height)*10);
		
		return position;
	}
	
	public int[] getGridPosition(){
		int[][] grid = PanelJuego.grid;
		int[] position = new int[2];
		for(int i=0; i<grid.length; i++){
			for(int j=0; j<grid[i].length;j++){
				if(grid[i][j] == this.playerid){
					position[0] = i;
					position[1] = j;
					return position;
				}
			}
		}
		return null;
	}
	
	public void despliegaTablero(){
		int[][] grid = PanelJuego.grid;
		System.out.println("==============================");
		for(int i=0; i<grid.length; i++){
			System.out.print("|");
			for(int j=0; j<grid[i].length; j++){
				System.out.print(grid[i][j]+"|");
			}
			System.out.println();
		}
	}
	
	public Point getMatrixPoint(int x,int y){
		int ny = y/50;
		int nx = x/50;
		return new Point(nx,ny);
	}
	
	public void changePosition(int i, int j){
		if(PanelJuego.grid[i][j] != GameMaps.BLOQUE){
			int olds[] = this.getGridPosition();
			
			PanelJuego.grid[olds[0]][olds[1]] = GameMaps.BLANK;
			PanelJuego.grid[i][j] = this.playerid;
		}
	}
	
	public int returnX(int direction){
		int[][] grid = PanelJuego.grid;
		
		//-- Base
		int y = (this.playerImage!=null)?this.playerImage.getHeight()-4:58;
		int x = (this.playerImage!=null)?this.playerImage.getWidth():47;
		int posy  = this.getYpos()+y;
		
		//-- Punto izquierdo y derecho
		int posx1 = this.getXpos()+8;
		int posx2 = this.getXpos()+x-12;
		
		//-- Crear puntos de la base
		Point p = this.getMatrixPoint(posx1,posy);
		
		//-- Rectangulos inferiores
		PanelJuego.gImagen.setColor(Color.GREEN);
		PanelJuego.gImagen.fill(new Rectangle(new Point(posx1,posy),new Dimension(5,5)));
		PanelJuego.gImagen.fill(new Rectangle(new Point(posx2,posy),new Dimension(5,5)));
		
		try {
			if(direction<0){ //-- Se mueve a la izquierda
				Point p1 = this.getMatrixPoint(this.getXpos()+x,posy);
				if(p1.x == 1) return 5; //-- Bugfix de imprecision de la primera columna
					
				if(grid[p.x-1][p.y] == GameMaps.BLOQUE){
					return Math.abs((p.x)*50-posx1);
				} else {
					this.changePosition(p.x-1,p.y);
					return 5;
				}
			
			} else if(direction>0) { //-- Se mueve a la derecha
				Point p1 = this.getMatrixPoint(this.getXpos(),posy);
				if(p1.x == grid.length-2) return 5; //-- Bugfix de imprecision de la primera columna
				
				if(grid[p.x+1][p.y] == GameMaps.BLOQUE){
					return Math.abs((p.x+1)*50-posx2);
				} else {
					this.changePosition(p.x+1,p.y);
					return 5;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e){ return 0;}
		return 0;
	}
	
	public int returnY(int direction){
		int[][] grid = PanelJuego.grid;
		
		//-- cambios base
		int y = (this.playerImage!=null)?this.playerImage.getHeight()-4:58;
		int x = (this.playerImage!=null)?this.playerImage.getWidth():47;
		int posy  = this.getYpos()+y;
		
		//-- Checar en los 2 puntos Izquierda y derecha
		int posx1 = this.getXpos()+8;
		int posx2 = this.getXpos()+x-12;
		
		//-- Crear puntos de la base
		Point p = this.getMatrixPoint(posx1,posy);
		Point p1 = this.getMatrixPoint(posx2,posy);
		
		//-- Rectangulos inferiores
		PanelJuego.gImagen.setColor(Color.GREEN);
		PanelJuego.gImagen.fill(new Rectangle(new Point(posx1,posy),new Dimension(5,5)));
		PanelJuego.gImagen.fill(new Rectangle(new Point(posx2,posy),new Dimension(5,5)));
		try {
			
			if(direction<0){ //-- Se mueve para arriba
				if(grid[p.x][p.y-1] == GameMaps.BLOQUE){
					return Math.abs((p.y-1)*50-this.getYpos());
				} else if(grid[p1.x][p.y-1] == GameMaps.BLOQUE){ 
					return Math.abs((p1.y-1)*50-this.getYpos());
				}else {
					this.changePosition(p.x,p.y-1);
					return 5;
				}
			} else if(direction>0) { //-- Se mueve para abajo
				if(p.y == grid[0].length-1) return 5;
				if(grid[p.x][p.y+1] == GameMaps.BLOQUE){
					int movement = Math.abs((p.y+1)*50-posy);
					return (movement>5)?movement:0;
				} else if(grid[p1.x][p1.y+1] == GameMaps.BLOQUE){
					int movement = Math.abs((p1.y+1)*50-posy);
					return (movement>5)?movement:0;	
				} else {
					this.changePosition(p.x,p.y+1);
					return 5;
				}
				
			}
		} catch (ArrayIndexOutOfBoundsException e){ return 0;}
	
		return 0;
	}
	
	public int checkMovement(String axis, int direction){ //-- direction: - para arriba o izq + para derecha o abajo
	//	this.despliegaTablero();
		if(axis.equals("X")){
			return this.returnX(direction);
		} else if(axis.equals("Y")){
			return this.returnY(direction);
		}
		
		return 0;
	}

	public int getActiveBombs() {
		return activeBombs;
	}

	public void setActiveBombs(int activeBombs) {
		this.activeBombs = activeBombs;
	}
}
