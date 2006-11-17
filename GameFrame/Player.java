import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.JComponent;

/**
 * Clase que maneja al jugador, sus imagenes y las colisiones
 * @author Revolution Software Developers
 */
public class Player extends JComponent implements KeyListener,Runnable{

	/**
	 * Constante de eclipse
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Cantidad actual de jugadores
	 */
	public static int numPlayers = 0;
	
	/**
	 * Limite de bombas que pueden poner los jugadores
	 */
	private static final int bombsNumLimit = 4;
	
	/**
	 * Limite de alcance que pueden tener las bombas
	 */
	private static final int bombsPowLimit = 3;
	
	/**
	 * Caracter indicador de la direccion actual del jugador ARRIBA
	 */
	private static final char UP = 'U';
	
	/**
	 * Caracter indicador de la direccion actual del jugador ABAJO
	 */
	private static final char DOWN = 'D';
	
	/**
	 * Caracter indicador de la direccion actual del jugador IZQUIERDA
	 */
	private static final char LEFT = 'L';
	
	/**
	 * Caracter indicador de la direccion actual del jugador DERECHA
	 */
	private static final char RIGTH = 'R';

	/**
	 * Imagen principal base
	 */
	private static String baseImage = "Base.png";
	
	/**
	 * Ultimo directorio de la direccion a utilizar
	 */
	private String lastDir = "";
	
	/**
	 * Posicion en la coordenada X del jugador
	 */
	private int Xpos;
	
	/**
	 * Posicion Vertical
	 */
	private int Ypos;
	
	/**
	 * Numero de jugador actual
	 */
	private int player;
	
	/**
	 * Indica si el player esta vivo o muerto
	 */
	private boolean alive;
	
	/**
	 * Numero de bombas activas del jugador
	 */
	private int activeBombs;
	
	/**
	 * Numero de bombas permitidas por el jugador
	 */
	private int bombsNum;
	
	/**
	 * Limite de alcance de las bombas del jugador
	 */
	private int bombsPow;
	
	/**
	 * Indica si el jugador se esta moviendo o no
	 */
	public boolean isMoving;
	
	/**
	 * Direccion actual del jugador
	 */
	private char direction;
	
	/**
	 * Imagen actual del jugador
	 */
	private BufferedImage playerImage; 
	
	/**
	 * Numero de imagen actual del jugador
	 */
	private int counter;

	/**
	 * Constructor del jugador
	 * @param x coordenada inicial de x
	 * @param y coordenada inicial de y
	 */

	public Player(int x, int y, int p) {
		//-- Numero de jugador
		player = p;
		numPlayers++;
		//-- Coordenadas iniciales
		Xpos = x;
		Ypos = y;
		
		//-- Numero de imagen inicial
		counter = 0;
		
		//-- Direccion inicial
		direction = 'D';
		
		//-- Limites posibles del jugador
		bombsNum = 2;
		bombsPow = 2;
		
		//-- Bombas activas
		activeBombs = 0;
		
		//-- Activo
		alive = true;
		
		Thread t = new Thread(this);
		t.start();
		//-- Borra el area alrededor del jugador para que no quede encerrado en un principio
		this.cleanArea();

		addKeyListener(this);

	}
	
	/**
	 * Regresa las coordenadas centradas de acuerdo aun 
	 * @param x
	 * @param y
	 * @return
	 */
	public static Point center(double x, double y){
		Point aux = new Point();
		aux.setLocation((x+23.5),(y+30.5));
	
		return aux;
	}
	
	/**
	 * Borra el area alrededor del jugador para que no haya bloques que interfieran con su movimiento inical
	 */
	private void cleanArea(){
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
	
	/**
	 * Regres auna imagen de tipo BufferedImage para el jugador
	 * @param url
	 * @return
	 */
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
	
	/**
	 * Dibuja en el canvas de la imagen al jugador, de estar muerto lo pinta quemado
	 * @param g
	 */
	public void draw(Graphics2D g){

		if(alive){
			g.drawImage(getPlayerImage(),Xpos,Ypos,null);
		}else{
			g.drawImage(PanelJuego.getImage("Quemado.png"),Xpos,Ypos,null);
		}

		
	}
	
	/**
	 * Cambia la direccion hacia la izquierda y checa colisiones
	 */
	private void moveLeft(){
		int r = this.checkMovement("X",-1);
		Xpos-= (r>5)?5:r;
		counter++;
		
	}
	
	/**
	 * Cambia la direccion hacia abajo y checa colisiones
	 */
	private void moveDown(){
		int r = this.checkMovement("Y",1);	
		Ypos+=(r>5)?5:r;
		counter++;
	}
	
	/**
	 * Cambia la direccion hacia arriba y checa colisiones
	 */
	private void moveUp(){
		int r = this.checkMovement("Y",-1);
		Ypos-=(r>5)?5:r;
		counter++;		
	}
	
	/**
	 * Cambia la direccion hacia la derecha y checa colisiones
	 */
	private void moveRigth(){
		int r = this.checkMovement("X",1);
		Xpos+=(r>5)?5:r;
		counter++;
	}

	/**
	 * Trae el numero de bombas que tiene permitido poner el usuario actualmente
	 * @return bombsNum
	 */
	public int getBombsNum() {
		return bombsNum;
	}

	/**
	 * Establece el numero de bombas maximas que puede colocar
	 * @param bombsNum
	 */
	public void setBombsNum(int bombsNum) {
		this.bombsNum = bombsNum;
	}

	/**
	 * Trae el alcance de las bombas
	 * @return bombsPow
	 */
	public int getBombsPow() {
		return bombsPow;
	}

	/**
	 * Establece el poder de las bombas
	 * @param bombsPow
	 */
	public void setBombsPow(int bombsPow) {
		this.bombsPow = bombsPow;
	}

	/**
	 * Trae la imagen con la posicion actual del jugador
	 * @return playerImage
	 */
	private BufferedImage getPlayerImage() {
		String auxDir = "";//+direction;
		//-- Verifica la posicion y cambia de directorio
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

	/**
	 * Cambia la imagen del jugador
	 * @param playerImage
	 */
	public void setPlayerImage(BufferedImage playerImage) {
		this.playerImage = playerImage;
	}

	/**
	 * Trae la coordenada X del jugador
	 * @return Xpos
	 */
	public int getXpos() {
		return Xpos;
	}

	/**
	 * Establece la coordenada X del jugador
	 * @param xpos
	 */
	public void setXpos(int xpos) {
		Xpos = xpos;
	}

	/**
	 * Trae la coordenada Y del jugador
	 * @return Ypos
	 */
	public int getYpos() {
		return Ypos;
	}

	/**
	 * Establece la coordenada Y del jugador
	 * @param ypos
	 */
	public void setYpos(int ypos) {
		Ypos = ypos;
	}

	/**
	 * Trae el numero de imagen actual del jugador
	 * @return counter
	 */
	public int getCounter() {
		return counter;
	}
	
	/**
	 * Establece el numero de imagen actual
	 * @param counter
	 */
	public void setCounter(int counter) {
		this.counter = counter;
	}
	
	/**
	 * Indica si el jugador esta en movimiento
	 * @return isMoving
	 */
	public boolean isMoving() {
		return isMoving;
	}
	
	/**
	 * Establece si el jugador puede moverse o no
	 * @param isMoving
	 */
	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}

	/**
	 * Regresa la direccion actual del jugador
	 * @return direction
	 */
	public int getDirection() {
		return direction;
	}
	
	/**
	 * Establece la direccion actual del jugador
	 * @param direction
	 */
	public void setDirection(char direction) {
		this.direction = direction;
	}
	
	/**
	 * Trae un arreglo con la posicion actual en i,j de una matriz del jugador
	 * @return position
	 */
	public int[] getCurrentPosition(){
		int position[] = new int[2];
		int width = PanelJuego.grid.length * 50;
		int height = PanelJuego.grid[0].length * 50;
		//-- Calcula su posicion en pantalla
		position[0] = (int)(((this.Xpos+50.0)/(double)width)*10);
		position[1] = (int)(((this.Ypos+50.0)/(double)height)*10);
		
		return position;
	}
	
	/**
	 * Trae un punto (Point) de la pantalla en coordenadas i,j
	 * @param x punto x en pixeles
	 * @param y punto y en pixeles
	 * @return point
	 */
	public Point getMatrixPoint(int x,int y){
		int ny = y/50;
		int nx = x/50;
		return new Point(nx,ny);
	}
	
	/**
	 * Procesa los items encontrados
	 * @param x
	 * @param y
	 */
	public void processItems(int x, int y){
		int[][] grid = PanelJuego.grid;
		int cell = grid[x][y];
		if(cell == GameMaps.MOREBOMBS){	
			if(this.bombsNum <= Player.bombsNumLimit){
				this.bombsNum++;
			}
			grid[x][y] = GameMaps.BLANK;
		} else if(cell == GameMaps.MOREPOWER){
			if(this.bombsPow <= Player.bombsPowLimit){
				this.bombsPow++;
			}
			grid[x][y] = GameMaps.BLANK;
		}
	}
	
	/**
	 * Regresa el numero de pasos que puede dar, si hay una colision regresa 0 y si puede moverse
	 * regresa los numero de pasos posibles antes de colisionar
	 * @param direction +1 para derecha y abajo -1 para izquierda y arriba
	 * @return pasos en pixeles
	 */
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
					if(cell == GameMaps.FUEGO && Math.abs((x)*50-posx1)<5){
						this.setAlive(false);
					}
					this.processItems(p.x-1,p.y);
					return 5;
				}
			
			} else if(direction>0) { //-- Se mueve a la derecha
				Point p1 = this.getMatrixPoint(this.getXpos(),posy);
				if(p1.x == grid.length-2 && grid[grid.length-2][p.y] == GameMaps.FUEGO){
					this.setAlive(false);
				} else if(p1.x == grid.length-2 && (grid[grid.length-1][p.y] == GameMaps.CRATE || grid[grid.length-1][p.y] == GameMaps.BLOQUE || grid[grid.length-1][p.y] == GameMaps.BOMBA)){
					return 0;
				}
				if(p1.x == grid.length-2) return 5; //-- Bugfix de imprecision de la primera columna
				int cell = grid[p1.x+1][p.y];
				if(cell == GameMaps.BLOQUE || cell == GameMaps.BOMBA || cell == GameMaps.CRATE){
					return Math.abs((p1.x+1)*50-posx2);
				} else {
					if(grid[p1.x][p.y] == GameMaps.FUEGO){
						this.setAlive(false);
					}
					this.processItems(p.x+1,p.y);
					return 5;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e){ return 0;}
		return 0;
	}
	
	/**
	 * Regresa la cantidad de posibles pasos que puede dar si existe una colision enfrente o no
	 * @param direction
	 * @return pasos
	 */
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
			
			if(direction<0){ //-- Si se va a mover para arriba

				int cell = grid[p.x][p.y-1];
				int cell2 = grid[p1.x][p.y-1];
				
				//-- Checa la parte izquierda
				if(cell == GameMaps.BLOQUE || cell == GameMaps.BOMBA || cell == GameMaps.CRATE){
					return Math.abs((p.y-1)*50-this.getYpos());
				//-- Checa la parte derecha
				} else if(cell2 == GameMaps.BLOQUE || cell2 == GameMaps.BOMBA || cell2 == GameMaps.CRATE){ 
					return Math.abs((p1.y-1)*50-this.getYpos());
				} else {
					//-- No es bomba ni bloque//
					if((cell == GameMaps.FUEGO || cell2 == GameMaps.FUEGO )&& (Math.abs((p.y)*50-posy)<5) ){
						this.setAlive(false);
					} 
					//-- Procesa los items de cada lado
					this.processItems(p.x,p.y-1);
					this.processItems(p1.x,p.y-1);
			
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
					//-- No es bomba ni bloque checa el fuego de la casilla actual no el de la posterior
					if((grid[p.x][p.y] == GameMaps.FUEGO || grid[p1.x][p.y] == GameMaps.FUEGO) /*&& (Math.abs((p.y)*50-posy)<5)*/){	
						this.setAlive(false);
					} 
					//-- Procesa los items
					this.processItems(p.x,p.y+1);
					this.processItems(p1.x,p.y+1);
					
					return 5;
				}
				
			}
		} catch (ArrayIndexOutOfBoundsException e){ return 0;}
	
		return 0;
	}
	
	/**
	 * Verifica el movimiento filtrando la coordenada a usar
	 * @param axis
	 * @param direction
	 * @return entero indicando los pasos
	 */
	public int checkMovement(String axis, int direction){ //-- direction: - para arriba o izq + para derecha o abajo
		if(axis.equals("X")){
			return this.returnX(direction);
		} else if(axis.equals("Y")){
			return this.returnY(direction);
		}
		
		return 0;
	}
	
	/**
	 * Trae el numero de bombas activas del jugador
	 * @return
	 */
	public int getActiveBombs() {
		return activeBombs;
	}

	/**
	 * Establece el numero de bombas activas del jugador
	 * @param activeBombs
	 */
	public void setActiveBombs(int activeBombs) {
		this.activeBombs = activeBombs;
	}

	/**
	 * Regresa si el jugaor esta vivo o no
	 * @return
	 */
	public boolean isAlive() {
		return alive;
	}

	/**
	 * Establece si el jugador esta activo o no
	 * @param alive
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	/**
	 * Metodo para controlar elmovimiento del jugador desde el teclado
	 */
	public void keyPressed(KeyEvent ke) {
		if(player == 1){
		switch(ke.getKeyCode()){
		case KeyEvent.VK_UP:
			 isMoving = true;
			setDirection(Player.UP);
		break;
		case KeyEvent.VK_DOWN: 
			isMoving = true;
			setDirection(Player.DOWN);
		break;
		case KeyEvent.VK_LEFT:
			isMoving = true;
			setDirection(Player.LEFT);
		break;
		case KeyEvent.VK_RIGHT:
			isMoving = true;
			setDirection(Player.RIGTH);
		break;
		case KeyEvent.VK_ESCAPE:  
			PanelJuego.running = false; 
		break;
		case KeyEvent.VK_M:
			if((getActiveBombs() < getBombsNum()) && alive){
				//System.out.println(alive);
				Point aux = Player.center(getXpos(),getYpos());
				Point aux2 = Bomb.transform(aux);
				if(PanelJuego.grid[aux2.x][aux2.y] != GameMaps.BLANK) return;
				
				PanelJuego.bombs.add(new Bomb(aux2.x,aux2.y,this));
				setActiveBombs(getActiveBombs()+1);
			}
		break;
		case KeyEvent.VK_F4:
			this.setAlive(true);
			break;
	}
	}else
		if(player == 2){
			switch(ke.getKeyCode()){
			case KeyEvent.VK_W:
				 isMoving = true;
				setDirection(Player.UP);
			break;
			case KeyEvent.VK_S: 
				isMoving = true;
				setDirection(Player.DOWN);
			break;
			case KeyEvent.VK_A:
				isMoving = true;
				setDirection(Player.LEFT);
			break;
			case KeyEvent.VK_D:
				isMoving = true;
				setDirection(Player.RIGTH);
			break;
			case KeyEvent.VK_ESCAPE:  
				PanelJuego.running = false; 
			break;
			case KeyEvent.VK_G:
				if((getActiveBombs() < getBombsNum()) && alive){
				//	System.out.println(alive);
					Point aux = Player.center(getXpos(),getYpos());
					Point aux2 = Bomb.transform(aux);
					PanelJuego.bombs.add(new Bomb(aux2.x,aux2.y,this));
					setActiveBombs(getActiveBombs()+1);
				}
			break;
		}
		}

	}

	/**
	 * Control del jugador
	 */
	public void keyReleased(KeyEvent ke) {
		if(player == 1){
			if ((ke.getKeyCode() == KeyEvent.VK_UP) ||
				(ke.getKeyCode() == KeyEvent.VK_DOWN) ||
				(ke.getKeyCode() == KeyEvent.VK_LEFT) ||
				(ke.getKeyCode() == KeyEvent.VK_RIGHT)
				){
				isMoving = false;
			}
		}else{
			if ((ke.getKeyCode() == KeyEvent.VK_W) ||
					(ke.getKeyCode() == KeyEvent.VK_S) ||
					(ke.getKeyCode() == KeyEvent.VK_A) ||
					(ke.getKeyCode() == KeyEvent.VK_D)
					){
					isMoving = false;
				}
			
		}
	}
	/**
	 * Metodo no utilizado
	 */
	public void keyTyped(KeyEvent arg0) {}

	/**
	 * Verifica las teclas del jugador
	 */
	public void run() {
		while(alive){
			addKeyListener(this);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {}
		}
	}
}
