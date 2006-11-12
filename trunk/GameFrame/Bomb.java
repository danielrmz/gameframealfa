import java.awt.Graphics;
import java.awt.Point;

public class Bomb {
	
	private boolean isActive;
	private int Xpos;
	private int Ypos;
	private int bombpow;
	
	public int status;
	public Player owner;

	
	public static Point transform(Point p){
		Point aux = new Point();
		aux.setLocation((int) ((p.x)/50),(int) ((p.y)/50));		
		return aux;
	}
	public static void drawBombs(Graphics gImagen){
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
					for(int j = 0; j<4; j++){
						if(PanelJuego.players[j]!=null){
							PanelJuego.players[j].checkMovement("X", -1);
							PanelJuego.players[j].checkMovement("X", 1);
							PanelJuego.players[j].checkMovement("Y", -1);
							PanelJuego.players[j].checkMovement("Y", 1);
						}
					}
				}
				aux.status++;
			}else{
				aux.detonate(gImagen, false);
				aux.owner.setActiveBombs(aux.owner.getActiveBombs()-1);
				PanelJuego.bombs.remove(i);
			}
			
		}
	}
	
	public Bomb(int x, int y, Player p){
		status = 0;
		isActive = true;
		Xpos = x;
		Ypos = y;
		owner = p;
		bombpow = p.getBombsPow();
	}
	
	private int getPowerUp(){
		boolean powerup = Math.random()>0.5;
		if(powerup){
			return Math.random()>0.5?GameMaps.MOREBOMBS:GameMaps.MOREPOWER;
		}
		return GameMaps.BLANK;
	}
	
	private void detonate(Graphics gImage, boolean active){
		int[][] grid = PanelJuego.grid;

		//add center fire indication
		gImage.drawImage(PanelJuego.getImage("fuego/cruz.png"),Xpos*50,Ypos*50,null);
		grid[Xpos][Ypos] = (active)?GameMaps.FUEGO:GameMaps.BLANK;
		
		//check up
		for(int i=Ypos+1; i<(Ypos+this.bombpow); i++){
			try{
				if((grid[Xpos][i] == GameMaps.BLOQUE))
					break;
				else{
					
					if(active){
						if(i+1==(Ypos+this.bombpow)){
							gImage.drawImage(PanelJuego.getImage("fuego/abajo.png"),Xpos*50,i*50,null);
						} else {
							gImage.drawImage(PanelJuego.getImage("fuego/vertical.png"),Xpos*50,i*50,null);
						}
						grid[Xpos][i] = ((grid[Xpos][i] == GameMaps.CRATE)  || (grid[Xpos][i] == GameMaps.FUEGOB) )?GameMaps.FUEGOB:GameMaps.FUEGO;
					} 
					
					
					
					else {
						if(grid[Xpos][i] == GameMaps.FUEGOB){
							
							grid[Xpos][i] = this.getPowerUp();
							
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
						if(i-1 == (Ypos-this.bombpow)){
							gImage.drawImage(PanelJuego.getImage("fuego/arriba.png"),Xpos*50,i*50,null);
						} else {
							gImage.drawImage(PanelJuego.getImage("fuego/vertical.png"),Xpos*50,i*50,null);
						}
						grid[Xpos][i] = ((grid[Xpos][i] == GameMaps.CRATE)  || (grid[Xpos][i] == GameMaps.FUEGOB) )?GameMaps.FUEGOB:GameMaps.FUEGO;
					} else {
						if(grid[Xpos][i] == GameMaps.FUEGOB){
							
							grid[Xpos][i] = this.getPowerUp();
							
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
						if(i-1 == (Xpos-this.bombpow)){ 
							gImage.drawImage(PanelJuego.getImage("fuego/izquierda.png"),i*50,Ypos*50,null);
						} else {
							gImage.drawImage(PanelJuego.getImage("fuego/horizontal.png"),i*50,Ypos*50,null);
						}
						grid[i][Ypos] = ((grid[i][Ypos] == GameMaps.CRATE)  || (grid[i][Ypos] == GameMaps.FUEGOB) )?GameMaps.FUEGOB:GameMaps.FUEGO;
					} else {
						if(grid[i][Ypos] == GameMaps.FUEGOB){
							
							grid[i][Ypos] = this.getPowerUp();
							
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
						if(i+1 == Xpos+this.bombpow){
							gImage.drawImage(PanelJuego.getImage("fuego/derecha.png"),i*50,Ypos*50,null);
						} else {
							gImage.drawImage(PanelJuego.getImage("fuego/horizontal.png"),i*50,Ypos*50,null);
						} 
						grid[i][Ypos] = ((grid[i][Ypos] == GameMaps.CRATE) || (grid[i][Ypos] == GameMaps.FUEGOB) )?GameMaps.FUEGOB:GameMaps.FUEGO;
					} else {
						if(grid[i][Ypos] == GameMaps.FUEGOB){
							
							grid[i][Ypos] = this.getPowerUp();
							
						} else {
							grid[i][Ypos] = GameMaps.BLANK;
						}
					}
					
				}
			}catch(ArrayIndexOutOfBoundsException aiobe){}
		}
		
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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
