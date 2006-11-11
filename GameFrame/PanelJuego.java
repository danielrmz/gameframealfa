import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.LinkedList;

import javax.swing.*;

public class PanelJuego extends JPanel implements Runnable, KeyListener{
	
	private static final long serialVersionUID = 1L;
	
	public static final int ALTO = 550;
	public static final int ANCHO = 700;
	
	public static LinkedList<Bomb> bombs;
	
	public volatile boolean running;
	
	public int imagenSiguiente;
	private Image panelSecundario;
	private Graphics2D gImagen;
	
	private Player p;
	
	public static int[][] grid = new int[14][11];
	
	public PanelJuego(int[][] mapa) {
		setBackground(Color.WHITE);
		setSize(new Dimension(ANCHO,ALTO));
		setFocusable(true);
		requestFocus();
        p = new Player(0,0);
		addKeyListener(this);
		imagenSiguiente = 0;
		PanelJuego.grid = mapa;
		bombs = new LinkedList<Bomb>();
	}
	
	public void gameUpdate(){
	}
	
	
	public static Image getImage(String filename){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage(RUTA+"img/"+filename);
		return image;
	}
	
	public static final String RUTA = (new File ("")).getAbsolutePath()+"\\";
	
	public void drawBlocks(){
		int[][] grid = PanelJuego.grid;
		Image block = getImage("mis/Caja Metal.png");
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[i].length; j++){
				if(grid[i][j] == GameMaps.BLOQUE){
					gImagen.drawImage(block,i*50,j*50,null,null);
				}
			}
		}
	}
	
	
	public void gameRender(){
		if(panelSecundario==null){
			panelSecundario = createImage(ANCHO+1, ALTO+1);
		}
		gImagen = (Graphics2D)panelSecundario.getGraphics();
		gImagen.drawImage(getImage("bgs/sand.gif"),0,0,Color.BLACK,null);
		
		this.drawBlocks();
		gImagen.setColor(Color.BLACK);
		for(int i=50; i<=ANCHO;i+=50){
			gImagen.drawLine(i,0,i,ALTO);
		}
		
		for(int i=50; i<=ALTO;i+=50){
			gImagen.drawLine(0,i,ANCHO,i);
		}
		Bomb.drawBombs(gImagen);
		p.draw(gImagen);
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if (panelSecundario!=null){
			g.drawImage(panelSecundario,0,0,null);
		}
	}

	public void keyPressed(KeyEvent ke) {
				switch(ke.getKeyCode()){
					case KeyEvent.VK_UP:
						p.isMoving = true;
						//p.setMoving(true);
						p.setDirection(Player.UP);
					break;
					case KeyEvent.VK_DOWN: 
						p.isMoving = true;
						//p.setMoving(true);
						p.setDirection(Player.DOWN);
					break;
					case KeyEvent.VK_LEFT:
						p.isMoving = true;
						//p.setMoving(true);
						p.setDirection(Player.LEFT);
					break;
					case KeyEvent.VK_RIGHT:
						p.isMoving = true;
						//p.setMoving(true);
						p.setDirection(Player.RIGTH);
					break;
					case KeyEvent.VK_ESCAPE:  
						running = false; 
					break;
					case KeyEvent.VK_A:
						if(p.getActiveBombs() < p.getBombsNum()){
							Point aux = Player.center(p.getXpos(),p.getYpos());
							Point aux2 = Bomb.transform(aux);
							bombs.add(new Bomb(aux2.x,aux2.y,p));
							p.setActiveBombs(p.getActiveBombs()+1);
						}
					break;
				}
	}

	public void keyReleased(KeyEvent ke) {
		if ((!(ke.getKeyCode() == KeyEvent.VK_A))){
			p.isMoving = false;
		}
	}

	public void keyTyped(KeyEvent arg0) {
	}

	public void run() {
		running = true;
		while(running){
		    gameUpdate();
			gameRender();
			repaint();
			try{
				Thread.sleep(30);
			}catch(Exception e){}
		}
	}

}
