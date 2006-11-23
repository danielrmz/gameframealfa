import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

/** 
 * Clase GameFrame.java 
 * Implementa el juego principal, mapa y jugadores
 * 
 * @author Revolution Software Developers
 * @package drunkenman
 **/

public class GameFrame extends JFrame implements KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Layer Principal de contenidos
	 */
	private JLayeredPane principal = this.getLayeredPane();
	
	/**
	 * Label del screen de pausa
	 */
	private JLabel pausa = new JLabel();
	
	/**
	 * Variable para saber si el juego esta pausado
	 */
	private boolean paused = false;
	
	public PanelJuego mapa;
	/**
	 * Constructor
	 */
	public GameFrame() {
		Main.setDefaults(this);
		int[][] map;
		String name = "";
		double random = Math.random();
		
		if(random<0.4){
			map = new int[GameMaps.cantina.length][GameMaps.cantina[0].length];
			this.copyMap(GameMaps.cantina,map);
			name = "cantina";
		} else if(random>=0.4 && random <0.7){
			map = new int[GameMaps.desierto.length][GameMaps.desierto[0].length];
			this.copyMap(GameMaps.desierto,map);
			name = "desierto";
		} else {
			map = new int[GameMaps.normal.length][GameMaps.normal[0].length];
			this.copyMap(GameMaps.normal,map);
			name = "normal";
		}
		
		//PanelJuego.despliegaTablero(map);
		
		mapa = new PanelJuego(map,name,this);
		mapa.setSize(new Dimension(700,500));
		mapa.setOpaque(false);
		mapa.setLocation(160,80);
		principal.add(mapa,JLayeredPane.MODAL_LAYER);
		
		JLabel nuevo = new JLabel("F2 Juego Nuevo");
		JLabel pausa = new JLabel("F3 Pausa");
		JLabel guardar = new JLabel("F4 Guardar");
		JLabel cerrar = new JLabel("F10 Salir del Juego");
		nuevo.setSize(new Dimension(100,20));
		pausa.setSize(new Dimension(100,20));
		guardar.setSize(new Dimension(100,20));
		cerrar.setSize(new Dimension(150,20));
		
		nuevo.setForeground(Color.WHITE);
		pausa.setForeground(Color.WHITE);
		guardar.setForeground(Color.WHITE);
		cerrar.setForeground(Color.WHITE);
		
		nuevo.setLocation(20,20);
		pausa.setLocation(20,40);
		guardar.setLocation(20,60);
		cerrar.setLocation(20,80);
		
		principal.add(nuevo,JLayeredPane.PALETTE_LAYER);
		principal.add(pausa,JLayeredPane.PALETTE_LAYER);
		principal.add(guardar,JLayeredPane.PALETTE_LAYER);
		principal.add(cerrar,JLayeredPane.PALETTE_LAYER);
		mapa.addKeyListener(this);
		//this.addKeyListener(this);
	}

	public void copyMap(int[][] base, int[][] dest){
		for(int i=0;i<base.length;i++){
			for(int j=0;j<base[0].length;j++){
				dest[i][j] = base[i][j];
			}
		}
	}
	/**
	 * Pausa/Despausa el juego dependiendo de la variable global
	 * @param pause
	 */
	public void pause(boolean pause){
		if(pause){
			ImageIcon img = Main.getImageIcon("pausa.png");
			pausa.setIcon(img);
			pausa.setSize(new Dimension(800,600));
			principal.add(pausa,JLayeredPane.DRAG_LAYER);
			
		} else { 
			pausa.setIcon(null);
			pausa.setSize(new Dimension(0,0));
			pausa = new JLabel();
		}
		
		this.paused = pause;
		PanelJuego.paused = paused;
	}
	
	public void makeWinnerAnimation(Player winner){
		JOptionPane.showMessageDialog(this, "El jugador "+winner.getid()+" es el ganador!");
		if(HighscoresFrame.isHighscore(mapa.gameTime)!= -1){
			ImageIcon img = Main.getImageIcon("winner.png");
			pausa.setIcon(img);
			pausa.setSize(new Dimension(800,600));
			principal.add(pausa,JLayeredPane.DRAG_LAYER);
			
			String name = JOptionPane.showInputDialog(this,"Has obtenido un lugar en el bar de la fama, introduce tu nombre ");
			HighscoresFrame.setHighscore(name,mapa.gameTime);
			HighscoresFrame.printHighscores();
			
		}
	}
	
	/**
	 * Muestra la pantalla de guardar
	 */
	public void save(){
		JFileChooser fc = new JFileChooser(Main.RUTA);
		//fc.addChoosableFileFilter();
		fc.showSaveDialog(this);
		File file = fc.getSelectedFile();
		if(file!=null){
			//TODO: Guardar en archivo, serializar mapa
		}
	}
	
	/**
	 * Detecta las acciones
	 */
	public void keyPressed(KeyEvent e) {
		int pressed = e.getKeyCode();
		
		if(!this.paused){
			switch (pressed){
			case 113:
				PanelJuego.endGame();
				PersonalizeFrame frame = new PersonalizeFrame();
				frame.setVisible(true);
				this.dispose();
				break;
			case 114:
				this.pause(true);
				break;
			case 115:
				this.save();
				break;
			case 121: 
				PanelJuego.endGame();
				this.dispose();
				Main.principal.setVisible(true);
				break;
			}
		} else { 
			if(pressed==114){
				this.pause(false);
			}
		}
		
	}
	
	/**
	 * Accion de tecla presionada
	 */
	public void keyTyped(KeyEvent e) {}
	
	/**
	 * Al soltar a una tecla
	 */
	public void keyReleased(KeyEvent e) {}

}