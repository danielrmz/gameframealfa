import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.*;

public class PanelJuego extends JPanel implements Runnable, KeyListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int ALTO = 600;
	private static final int ANCHO = 800;
	
	public volatile boolean running;
	
	public int imagenSiguiente;
	private Image panelSecundario;
	private Graphics2D gImagen;
	
	private Player p;
	

	
	public PanelJuego() {
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(ALTO,ANCHO));
		setFocusable(true);
		requestFocus();
        p = new Player(60,60);
		addKeyListener(this);
		imagenSiguiente = 0;
	}
	
	public void gameUpdate(){
	}
	
	public void gameRender(){
		
		if(panelSecundario==null){
			panelSecundario = createImage(ALTO, ANCHO);
		}
		gImagen = (Graphics2D)panelSecundario.getGraphics();
		gImagen.setColor(Color.WHITE);
		gImagen.fillRect(0, 0, ALTO, ANCHO);
		
		p.draw(gImagen);
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
				System.out.println("Entro al keyPressed");
				p.setMoving(true);
				p.setDirection(Player.LEFT);
			break;
			case KeyEvent.VK_DOWN:  break;
			case KeyEvent.VK_LEFT:  break;
			case KeyEvent.VK_RIGHT:  break;
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
				Thread.sleep(100);
			}catch(Exception e){}
		}
	}

	
	

}
