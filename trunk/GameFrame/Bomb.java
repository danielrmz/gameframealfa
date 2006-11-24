import java.awt.*;
import java.io.*;

/**
 * Clase Bomba. 
 * Esta clase mantiene un contador de la explosion a traves de los frames
 * al explotar checa las celdas donde esta el fuego y mata a los que se ubican ahi
 * 
 * @author Revolution Software Developers
 */
public class Bomb implements Serializable {
	
	/**
	 * Constante de Eclipse
	 */
	private static final long serialVersionUID = 5531330444559978082L;

	/**
	 * Dice si la bomba esta activa o no
	 */
	private boolean isActive;
	
	/**
	 * Posicion horizontal de la bomba
	 */
	private int Xpos;
	
	/**
	 * Posicion vertical de la bomba
	 */
	private int Ypos;
	
	/**
	 * Alcance de la bomba.
	 */
	private int bombpow;
	
	/**
	 * Estatus de la bomba
	 */
	public int status;
	
	/**
	 * Owner de la bomba
	 */
	public Player owner;

	/**
	 * Constructor, manda las coordenadas iniciales de la bomba 
	 * y el jugador que la creo
	 * @param x Coordenada X
	 * @param y Coordenada Y
	 * @param p Jugador
	 */
	public Bomb(int x, int y, Player p){
		status = 0;
		isActive = true;
		Xpos = x;
		Ypos = y;
		owner = p;
		//-- Trae el maximo alcance de la bomba del jugador, para que si 
		//-- el jugador obtiene un powerup no se cambie tambien aqui, sino que sean independientes
		bombpow = p.getBombsPow(); 
	}
	
	/**
	 * Transforma el punto a coordenadas de i,j de la matriz
	 * @param p
	 * @return Point
	 */
	public static Point transform(Point p){
		Point aux = new Point();
		aux.setLocation((int) ((p.x)/50),(int) ((p.y)/50));		
		return aux;
	}
	
	/**
	 * Dibuja la animacion de las bombas en el canvas
	 * @param gImagen
	 */
	public static void drawBombs(Graphics gImagen,PanelJuego panel){
		int[][] grid = PanelJuego.grid;
		
		for(int i = 0; i<PanelJuego.bombs.size(); i++){
			Bomb aux = PanelJuego.bombs.get(i);
			if(aux.status<80){
				grid[aux.getXpos()][aux.getYpos()] = GameMaps.BOMBA;
				gImagen.drawImage(PanelJuego.getImage("bomba/molotov"+((aux.status)%4)+".png"),aux.getXpos()*50, aux.getYpos()*50, null);
				aux.status++;
			}else if (aux.status >=80 && aux.status<100){
				aux.detonate(gImagen,true);
				if (aux.status==80){
					if(ConfigFrame.isSonido){
						new SoundClip("sound/boom.wav");
					}
					for(int j = 0; j<4; j++){
						if(panel.players[j]!=null){
							//System.out.println("player "+j+":");
							panel.players[j].checkMovement("X", -1);
							panel.players[j].checkMovement("X", 1);
							panel.players[j].checkMovement("Y", -1);
							panel.players[j].checkMovement("Y", 1);
						}
					}
				}
				aux.status++;
			}else{
				//PanelJuego.despliegaTablero();
				aux.detonate(gImagen, false);
				aux.owner.setActiveBombs(aux.owner.getActiveBombs()-1);
				PanelJuego.bombs.remove(i);
			}
			
		}
	}
	
	/**
	 * Trae aleatoriamente que tipo de contenido tendra el crate al ser explotado
	 * @return
	 */
	private int getPowerUp(){
		boolean powerup = Math.random()>0.5;
		if(powerup && ConfigFrame.isItems){
			return Math.random()>0.5?GameMaps.MOREBOMBS:GameMaps.MOREPOWER;
		}
		return GameMaps.BLANK;
	}
	
	/**
	 * Detona la bomba
	 * @param gImage canvas de imagen
	 * @param active 
	 */
	private void detonate(Graphics gImage, boolean active){
		int[][] grid = PanelJuego.grid;

		//add center fire indication
		gImage.drawImage(PanelJuego.getImage("fuego/cruz.png"),Xpos*50,Ypos*50,null);
		grid[Xpos][Ypos] = (active)?GameMaps.FUEGO:GameMaps.BLANK;
		
		//check up
		for(int i=Ypos+1; i<(Ypos+this.bombpow); i++){
			try{
				if((grid[Xpos][i] == GameMaps.BLOQUE)){
					break;
				} else {
					
					if(active){
						grid[Xpos][i] = ((grid[Xpos][i] == GameMaps.CRATE)  || (grid[Xpos][i] == GameMaps.FUEGOB) )?GameMaps.FUEGOB:GameMaps.FUEGO;
						if(grid[Xpos][i] == GameMaps.FUEGOB) {
							gImage.drawImage(PanelJuego.getImage("fuego/abajo.png"),Xpos*50,i*50,null);
							break;
						}
						if(i+1==(Ypos+this.bombpow)){
							gImage.drawImage(PanelJuego.getImage("fuego/abajo.png"),Xpos*50,i*50,null);
						} else {
							gImage.drawImage(PanelJuego.getImage("fuego/vertical.png"),Xpos*50,i*50,null);
						}
					} 
					
					
					
					else {
						if(grid[Xpos][i] == GameMaps.FUEGOB){
							
							grid[Xpos][i] = this.getPowerUp();
							break;
						} else {
							grid[Xpos][i] = GameMaps.BLANK;
						}
					}
					
				}
			}catch(ArrayIndexOutOfBoundsException aiobe){}
		}
		
		//check down
		for(int i=Ypos-1; i>(Ypos-this.bombpow); i--){
			try{
				if((grid[Xpos][i] == GameMaps.BLOQUE) )
					break;
				else{
					if(active){
						grid[Xpos][i] = ((grid[Xpos][i] == GameMaps.CRATE)  || (grid[Xpos][i] == GameMaps.FUEGOB) )?GameMaps.FUEGOB:GameMaps.FUEGO;
						//-- Si ya ha sido cambiado entonces parar hasta ahi y poner la imagen solamente
						if(grid[Xpos][i] == GameMaps.FUEGOB) {
							gImage.drawImage(PanelJuego.getImage("fuego/arriba.png"),Xpos*50,i*50,null);
							break;
						}
						if(i-1 == (Ypos-this.bombpow)){
							gImage.drawImage(PanelJuego.getImage("fuego/arriba.png"),Xpos*50,i*50,null);
						} else {
							gImage.drawImage(PanelJuego.getImage("fuego/vertical.png"),Xpos*50,i*50,null);
						}
					} else {
						if(grid[Xpos][i] == GameMaps.FUEGOB){
							grid[Xpos][i] = this.getPowerUp();
							//-- Si es powerup romper el ciclo de limpieza de imagenes para no borrar otros crates o algo
							break;
						} else {
							grid[Xpos][i] = GameMaps.BLANK;
						}
					}
					
				}
			}catch(ArrayIndexOutOfBoundsException aiobe){}
		}
		
		//check left
		for(int i=Xpos-1; i>(Xpos-this.bombpow); i--){
			try{
				if((grid[i][Ypos] == GameMaps.BLOQUE))
					break;
				else{
					if(active){
						grid[i][Ypos] = ((grid[i][Ypos] == GameMaps.CRATE)  || (grid[i][Ypos] == GameMaps.FUEGOB) )?GameMaps.FUEGOB:GameMaps.FUEGO;
						//-- Si ya ha sido cambiado entonces parar hasta ahi y poner la imagen solamente
						if(grid[i][Ypos] == GameMaps.FUEGOB) {
							gImage.drawImage(PanelJuego.getImage("fuego/izquierda.png"),i*50,Ypos*50,null);
							break;
						}
						if(i-1 == (Xpos-this.bombpow)){ 
							gImage.drawImage(PanelJuego.getImage("fuego/izquierda.png"),i*50,Ypos*50,null);
						} else {
							gImage.drawImage(PanelJuego.getImage("fuego/horizontal.png"),i*50,Ypos*50,null);
						}
						} else {
						if(grid[i][Ypos] == GameMaps.FUEGOB){
							
							grid[i][Ypos] = this.getPowerUp();
							break;
						} else {
							grid[i][Ypos] = GameMaps.BLANK;
						}
					}
					
					
				}
			}catch(ArrayIndexOutOfBoundsException aiobe){}
		}
		//check right
		for(int i=Xpos+1; i<(Xpos+this.bombpow); i++){
			try{
				if((grid[i][Ypos] == GameMaps.BLOQUE))
					break;
				else{
					if(active){
						grid[i][Ypos] = ((grid[i][Ypos] == GameMaps.CRATE) || (grid[i][Ypos] == GameMaps.FUEGOB) )?GameMaps.FUEGOB:GameMaps.FUEGO;
						
						//-- Si ya ha sido cambiado entonces parar hasta ahi y poner la imagen solamente
						if(grid[i][Ypos] == GameMaps.FUEGOB) {
							gImage.drawImage(PanelJuego.getImage("fuego/derecha.png"),i*50,Ypos*50,null);
							break;
						}
						if(i+1 == Xpos+this.bombpow){
							gImage.drawImage(PanelJuego.getImage("fuego/derecha.png"),i*50,Ypos*50,null);
						} else {
							gImage.drawImage(PanelJuego.getImage("fuego/horizontal.png"),i*50,Ypos*50,null);
						} 
						
					} else {
						if(grid[i][Ypos] == GameMaps.FUEGOB){
							grid[i][Ypos] = this.getPowerUp();
							break;
						} else {
							grid[i][Ypos] = GameMaps.BLANK;
						}
					}
					
				}
			}catch(ArrayIndexOutOfBoundsException aiobe){}
		}
		
	}
	
	/**
	 * Trae si la bomba esta activa o no
	 * @return
	 */
	public boolean isActive() {
		return isActive;
	}
	
	/**
	 * Establece si la bomba esta activa o no
	 * @param isActive
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	/**
	 * Traer estatus
	 * @return
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * Establece el estatus
	 * @param status
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * Trae la posicion x actual de la bomba
	 * @return
	 */
	public int getXpos() {
		return Xpos;
	}

	/**
	 * Establece la posicion x de la bomba
	 * @param xpos
	 */
	public void setXpos(int xpos) {
		Xpos = xpos;
	}

	/**
	 * Trae la posicion y de la bomba
	 * @return
	 */
	public int getYpos() {
		return Ypos;
	}

	/**
	 * Establece la posicion y de la bomba
	 * @param ypos
	 */
	public void setYpos(int ypos) {
		Ypos = ypos;
	}
	

}
