	import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.LinkedList;

import javax.swing.JPanel;

public class PanelJuego extends JPanel implements Runnable, KeyListener{
	
	private static final long serialVersionUID = 1L;
	
	public static int ALTO;
	public static int ANCHO;
	
	public static LinkedList<Bomb> bombs;
	public static Player[] players; 
	
	public volatile static boolean running;
	
	public int imagenSiguiente;
	private Image panelSecundario;
	public static Graphics2D gImagen;
	private String mundo = "";
	private Player p;
	
	public static int[][] grid = new int[14][11];
	
	public static void despliegaTablero(){
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
	
	public PanelJuego(int[][] mapa) {
		ANCHO = mapa[0].length * 50;
		ALTO  = mapa.length * 50;
		setSize(new Dimension(ANCHO,ALTO));
		setFocusable(true);
		requestFocus();
      
		PanelJuego.grid = mapa;
		imagenSiguiente = 0;
		players = new Player[4];
		if(mapa.equals(GameMaps.desierto)){
			this.mundo = "desierto";
		} else if(mapa.equals(GameMaps.cantina)){
			this.mundo = "cantina";
		} else if(mapa.equals(GameMaps.normal)){
			this.mundo = "normal";
		}
		bombs = new LinkedList<Bomb>();
		p = new Player(0,-10);
		players[0] = p; 
	    this.addKeyListener(p);
	    this.addKeyListener(this);
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
		Image block = getImage("mundos/"+this.mundo+"/block.png");
		Image block2 = getImage("mundos/"+this.mundo+"/block2.png");
		
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[i].length; j++){
				if(grid[i][j] == GameMaps.BLOQUE || grid[i][j] == GameMaps.BLOQUE2){
					Image b = (GameMaps.BLOQUE == grid[i][j] || block2.getWidth(this) < 0 )?block:block2;
					gImagen.drawImage(b,i*50,j*50,null,null);
				}
			}
		}
	}
	
	public void drawGrid(){
		gImagen.setColor(Color.BLACK);
		for(int i=50; i<=ANCHO;i+=50){
			gImagen.drawLine(i,0,i,ALTO);
		}
		
		for(int i=50; i<=ALTO;i+=50){
			gImagen.drawLine(0,i,ANCHO,i);
		}
	}
	
	public void gameRender(){
		if(panelSecundario==null){
			panelSecundario = createImage(ANCHO+1, ALTO+1);
		}
		gImagen = (Graphics2D)panelSecundario.getGraphics();
		gImagen.drawImage(getImage("mundos/"+this.mundo+"/bg.png"),0,0,Color.BLACK,null);
		
		this.drawBlocks();
		//this.drawGrid();
		
		Bomb.drawBombs(gImagen,p);
		p.draw(gImagen);
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if (panelSecundario!=null){
			g.drawImage(panelSecundario,0,0,null);
		}
	}

	public void keyPressed(KeyEvent ke) {
	}

	public void keyReleased(KeyEvent ke) {
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
