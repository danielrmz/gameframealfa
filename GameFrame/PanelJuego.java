import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class PanelJuego extends JPanel implements Runnable, KeyListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int ALTO = 550;
	public static final int ANCHO = 700;
	public static final int BLOQUE = 1;
	public static final int PERSONAJE = 0;
	public static final int BOMBA = 2;
	public static final int POWERUP = 3;
	
	public volatile boolean running;
	
	public int imagenSiguiente;
	private Image panelSecundario;
	private Graphics2D gImagen;
	
	private Player p;
	
	public static int[][] grid = new int[14][11];
	
	public PanelJuego() {
		setBackground(Color.WHITE);
		setSize(new Dimension(ANCHO,ALTO));
		setFocusable(true);
		requestFocus();
        p = new Player(0,0);
		addKeyListener(this);
		imagenSiguiente = 0;
		grid[1][0] = PanelJuego.BLOQUE;
	}
	
	public void gameUpdate(){
	}
	
	
	
	public void gameRender(){
		
		if(panelSecundario==null){
			panelSecundario = createImage(ANCHO+1, ALTO+1);
		}
		gImagen = (Graphics2D)panelSecundario.getGraphics();
		gImagen.setColor(Color.WHITE);
		gImagen.fillRect(0, 0, ANCHO, ALTO);
		
		p.draw(gImagen);
		
		gImagen.setColor(Color.BLACK);
		for(int i=50; i<=ANCHO;i+=50){
			gImagen.drawLine(i,0,i,ALTO);
		}
		
		for(int i=50; i<=ALTO;i+=50){
			gImagen.drawLine(0,i,ANCHO,i);
		}
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if (panelSecundario!=null){
			g.drawImage(panelSecundario,0,0,null);
		}
	}

	public void keyPressed(KeyEvent ke) {
		if(!p.isMoving()){
				switch(ke.getKeyCode()){
					case KeyEvent.VK_UP: 
						p.setMoving(true);
						p.setDirection(Player.UP);
					break;
					case KeyEvent.VK_DOWN: 
						p.setMoving(true);
						p.setDirection(Player.DOWN);
					break;
					case KeyEvent.VK_LEFT:
						p.setMoving(true);
						p.setDirection(Player.LEFT);
					break;
					case KeyEvent.VK_RIGHT: 
						p.setMoving(true);
						p.setDirection(Player.RIGTH);
					break;
					case KeyEvent.VK_ESCAPE:  running = false; break;
				}
		}
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void run() {
		running = true;
		while(running){
		    gameUpdate();
			gameRender();
			repaint();
			try{
				Thread.sleep(20);
			}catch(Exception e){}
		}
	}

	
	

}
