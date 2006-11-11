import java.awt.Graphics;
import java.awt.Point;

public class Bomb {
	
	private boolean isActive;
	private int Xpos;
	private int Ypos;
	public int status;
	public Player owner;

	
	public static Point transform(Point p){
		Point aux = new Point();
		aux.setLocation((int) ((p.x)/50),(int) ((p.y)/50));		
		return aux;
	}
	public static void drawBombs(Graphics gImagen, Player p){
		int[][] grid = PanelJuego.grid;
		for(int i = 0; i<PanelJuego.bombs.size(); i++){
			Bomb aux = PanelJuego.bombs.get(i);
			if(aux.status<80){
				grid[aux.getXpos()][aux.getXpos()] = GameMaps.BOMBA;
				gImagen.drawImage(PanelJuego.getImage("bomba/molotov"+((aux.status)%4)+".png"),aux.getXpos()*50, aux.getYpos()*50, null);
				aux.status++;
			}else if (aux.status >=80 && aux.status<100){
				grid[aux.getXpos()][aux.getXpos()] = GameMaps.BLANK;
				aux.detonate(gImagen, p,true);
				aux.status++;
			}else{
				aux.detonate(gImagen, p, false);
				aux.owner.setActiveBombs(aux.owner.getActiveBombs()-1);
				PanelJuego.bombs.remove(i);
			}
			
		}
	}
	
	public Bomb(int x, int y, Player p){
		System.out.println("Se Construyo la bomba");
		status = 0;
		isActive = true;
		Xpos = x;
		Ypos = y;
		owner = p;
	}
	
	
	private void detonate(Graphics gImage, Player p, boolean active){
		int[][] grid = PanelJuego.grid;

		//add center fire indication
		gImage.drawImage(PanelJuego.getImage("fuego/fuego.png"),Xpos*50,Ypos*50,null);
		
		//check up
		for(int i=Ypos; i<(Ypos+3); i++){
			try{
				if((grid[Xpos][i] == GameMaps.BLOQUE))
					break;
				else{
					gImage.drawImage(PanelJuego.getImage("fuego/fuego.png"),Xpos*50,i*50,null);
					grid[Xpos][i] = (active)?GameMaps.FUEGO:GameMaps.BLANK;
				}
			}catch(ArrayIndexOutOfBoundsException aiobe){}
		}
		//check down
		for(int i=Ypos; i>(Ypos-3); i--){
			try{
				if((grid[Xpos][i] == GameMaps.BLOQUE))
					break;
				else{
					gImage.drawImage(PanelJuego.getImage("fuego/fuego.png"),Xpos*50,i*50,null);
					grid[Xpos][i] = (active)?GameMaps.FUEGO:GameMaps.BLANK;
				}
			}catch(ArrayIndexOutOfBoundsException aiobe){}
		}
		//check left
		for(int i=Xpos; i>(Xpos-3); i--){
			try{
				if((grid[i][Ypos] == GameMaps.BLOQUE))
					break;
				else{
					gImage.drawImage(PanelJuego.getImage("fuego/fuego.png"),i*50,Ypos*50,null);
					grid[i][Ypos] = (active)?GameMaps.FUEGO:GameMaps.BLANK;
				}
			}catch(ArrayIndexOutOfBoundsException aiobe){}
		}
		//check right
		for(int i=Xpos; i<(Xpos+3); i++){
			try{
				if((grid[i][Ypos] == GameMaps.BLOQUE))
					break;
				else{
					gImage.drawImage(PanelJuego.getImage("fuego/fuego.png"),i*50,Ypos*50,null);
					grid[i][Ypos] = (active)?GameMaps.FUEGO:GameMaps.BLANK;
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
