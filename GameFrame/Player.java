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
		aux.setLocation(((x+47)/2),((y+61)/2));
		System.out.println(aux.getX()+" "+aux.getY());
		return aux;
	}
	
	
	public Player(int x, int y) {
		playerid = ++Player.numPlayers;
		Xpos = x;
		Ypos = y;
		counter = 0;
		direction = 'D';
		bombsNum = 2;
		activeBombs = 0;
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
	
	public int[] getActualPosition(){
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

	public boolean checkMovement(String direction, int change){
		int i = (int)(((this.Xpos+50.0)/(double)PanelJuego.ANCHO)*10);
		int j = (int)(((this.Ypos+50.0)/(double)PanelJuego.ALTO)*10);
		int col = j;
		int row = i;
		//-- Busca la posicion anterior y la borra
		int[] olds = this.getActualPosition();
		if(olds != null){
			PanelJuego.grid[olds[0]][olds[1]] = -1;
		}
		// Establece en la posicion anterior al jugador
		PanelJuego.grid[i][j] = this.playerid;
		try {
			if(direction.equals("Y")){
				col = j + change;
			} else if(direction.equals("X")){
				row = i + change;
			}
					switch(PanelJuego.grid[row][col]){
					case GameMaps.BLOQUE:  
						//this.setMoving(false);
						return false;
					case GameMaps.CRATE:
						this.setMoving(false);
						return false;
					case GameMaps.BOMBA: //-- Si es bomba se detiene, falta ver caso en que tenga powerup de kickear
						this.setMoving(false);
						return false;
					case GameMaps.POWERUP: 
						break;
					default:
						if(PanelJuego.grid[row][col] > 100){ //-- Colision con oto jugador
							this.setMoving(false);
							return false;
						}
						break;
					}
			
					
		} catch(ArrayIndexOutOfBoundsException e){
			//this.setMoving(false);
			return false;
		}
		return true;
	}

	public int getActiveBombs() {
		return activeBombs;
	}

	public void setActiveBombs(int activeBombs) {
		this.activeBombs = activeBombs;
	}
}
