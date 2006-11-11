import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class Player extends JComponent implements KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	
	private boolean alive;
	
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
		alive = true;

		this.cleanArea();

		addKeyListener(this);

	}
	
	public void cleanArea(){
		int[][] grid = PanelJuego.grid;
		int pos[] = this.getCurrentPosition();
		int i = pos[0];
		int j = pos[1];
		grid[pos[0]][pos[1]] = GameMaps.BLANK;
		try { grid[i+1][j] = GameMaps.BLANK; } catch (ArrayIndexOutOfBoundsException e){}
		try { grid[i][j+1] = GameMaps.BLANK; } catch (ArrayIndexOutOfBoundsException e){}
		try { grid[i-1][j] = GameMaps.BLANK; } catch (ArrayIndexOutOfBoundsException e){}
		try { grid[i][j-1] = GameMaps.BLANK; } catch (ArrayIndexOutOfBoundsException e){}
		
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

		if(alive){
			g.drawImage(getPlayerImage(),Xpos,Ypos,null);
		}else{
			g.drawImage(PanelJuego.getImage("Quemado.png"),Xpos,Ypos,null);
		}

		
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
	
	
	
	public Point getMatrixPoint(int x,int y){
		int ny = y/50;
		int nx = x/50;
		return new Point(nx,ny);
	}
	
	public void changePosition(int i, int j){
		if(PanelJuego.grid[i][j] != GameMaps.BLOQUE){
			//int olds[] = this.getGridPosition();
			//int auxi,auxj;
			//try {
			//	auxi = olds[0]; 
			//	auxj = olds[1];
			//	PanelJuego.grid[auxi][auxj] = GameMaps.BLANK;
			///	PanelJuego.grid[i][j] = this.playerid;
			//} catch (NullPointerException e){}
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
				int cell = grid[p.x-1][p.y];
				if(cell == GameMaps.BLOQUE || cell == GameMaps.BOMBA || cell == GameMaps.CRATE){
					return Math.abs((p.x)*50-posx1);
				} else {
					if(cell == GameMaps.FUEGO && Math.abs((p.x-1)*50-posx1)<5){
						this.setAlive(false);
					} else if(cell == GameMaps.MOREBOMBS){
						this.bombsNum++;
					}
					this.changePosition(p.x-1,p.y);
					return 5;
				}
			
			} else if(direction>0) { //-- Se mueve a la derecha
				Point p1 = this.getMatrixPoint(this.getXpos(),posy);
				if(p1.x == grid.length-2) return 5; //-- Bugfix de imprecision de la primera columna
				int cell = grid[p.x+1][p.y];
				if(cell == GameMaps.BLOQUE || cell == GameMaps.BOMBA || cell == GameMaps.CRATE){
					return Math.abs((p.x+1)*50-posx2);
				} else {
					if(cell == GameMaps.FUEGO /*&& Math.abs((p.x+1)*50-posx1)<5*/){
						this.setAlive(false);
					} else if(cell == GameMaps.MOREBOMBS){
						this.bombsNum++;
					}
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
		Point p = this.getMatrixPoint(posx1,posy);  //-- Izquierda
		Point p1 = this.getMatrixPoint(posx2,posy); //-- Derecha
		
		//-- Rectangulos inferiores
		PanelJuego.gImagen.setColor(Color.GREEN);
		PanelJuego.gImagen.fill(new Rectangle(new Point(posx1,posy),new Dimension(5,5)));
		PanelJuego.gImagen.fill(new Rectangle(new Point(posx2,posy),new Dimension(5,5)));
		try {
			
			if(direction<0){

				int cell = grid[p.x][p.y-1];
				int cell2 = grid[p1.x][p.y-1];

				//-- Checa la parte izquierda
				if(cell == GameMaps.BLOQUE || cell == GameMaps.BOMBA){
					return Math.abs((p.y-1)*50-this.getYpos());
				//-- Checa la parte derecha
				} else if(cell2 == GameMaps.BLOQUE || cell2 == GameMaps.BOMBA){ 
					return Math.abs((p1.y-1)*50-this.getYpos());
				} else {
					//-- No es bomba ni bloque//&& (Math.abs((p.y)*50-posy)<5)

					if((cell == GameMaps.FUEGO || cell2 == GameMaps.FUEGO ) ){
						this.setAlive(false);
					} else if(cell == GameMaps.MOREBOMBS || cell2 == GameMaps.MOREBOMBS){
						this.bombsNum++;
					}
					this.changePosition(p.x,p.y-1);
					return 5;
				}
			} else if(direction>0) { //-- Se mueve para abajo
				if(p.y == grid[0].length-1) return 5;
				int cell = grid[p.x][p.y+1];
				int cell2 = grid[p1.x][p.y+1];
				
				//-- Checa la parte de la izquierda
				if(cell == GameMaps.BLOQUE || cell == GameMaps.BOMBA || cell == GameMaps.CRATE){
					int movement = Math.abs((p.y+1)*50-posy);
					return (movement>5)?movement:0;
				//-- Checa la parte de la derecha
				} else if(cell2 == GameMaps.BLOQUE || cell2 == GameMaps.BOMBA || cell == GameMaps.CRATE){
					int movement = Math.abs((p1.y+1)*50-posy);
					return (movement>5)?movement:0;	
				} else {
					//-- No es bomba ni bloque

					if((cell == GameMaps.FUEGO || cell2 == GameMaps.FUEGO) /*&& (Math.abs((p.y)*50-posy)<5)*/){	
						this.setAlive(false);
					} else if(cell == GameMaps.MOREBOMBS || cell2 == GameMaps.MOREBOMBS){
						this.bombsNum++;
					}
					this.changePosition(p.x,p.y+1);
					return 5;
				}
				
			}
		} catch (ArrayIndexOutOfBoundsException e){ return 0;}
	
		return 0;
	}
	
	public int checkMovement(String axis, int direction){ //-- direction: - para arriba o izq + para derecha o abajo
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


	public boolean isAlive() {
		return alive;
	}


	public void setAlive(boolean alive) {
		this.alive = alive;
	}


	public void keyPressed(KeyEvent ke) {
		switch(ke.getKeyCode()){
		case KeyEvent.VK_UP:
			 isMoving = true;
			//p.setMoving(true);
			setDirection(Player.UP);
		break;
		case KeyEvent.VK_DOWN: 
			isMoving = true;
			//p.setMoving(true);
			setDirection(Player.DOWN);
		break;
		case KeyEvent.VK_LEFT:
			isMoving = true;
			//p.setMoving(true);
			setDirection(Player.LEFT);
		break;
		case KeyEvent.VK_RIGHT:
			isMoving = true;
			//p.setMoving(true);
			setDirection(Player.RIGTH);
		break;
		case KeyEvent.VK_ESCAPE:  
			PanelJuego.running = false; 
		break;
		case KeyEvent.VK_A:
			if((getActiveBombs() < getBombsNum()) && alive){
				System.out.println(alive);
				Point aux = Player.center(getXpos(),getYpos());
				Point aux2 = Bomb.transform(aux);
				PanelJuego.bombs.add(new Bomb(aux2.x,aux2.y,this));
				setActiveBombs(getActiveBombs()+1);
			}
		break;
	}
		
	}


	public void keyReleased(KeyEvent ke) {
		if ((!(ke.getKeyCode() == KeyEvent.VK_A))){
			isMoving = false;
		}
		
	}
	public void keyTyped(KeyEvent arg0) {
	}
}
