import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/** 
 * Clase HighscoresFrame.java 
 * Highscores de los jugadores
 * @author Revolution Software Developers
 * @package drunkenman
 **/
public class HighscoresFrame extends JFrame implements ActionListener {
	/**
	 * Constante de Eclipse
	 */
	private static final long serialVersionUID = 6159177034768072506L;

	/**
	 * Layer principal de contenidos
	 */
	private JLayeredPane principal = this.getLayeredPane();
	
	/**
	 * Boton para regresar a las pantallas anteriores
	 */
	private JButton back = new JButton("Regresar");
	
	/**
	 * Highscores
	 */
	public static Highscore[] highscores = new Highscore[10];
	
	/**
	 * Constructor de los highscores
	 */
	public HighscoresFrame() {
		Main.setDefaults(this);
		
		JPanel top = new JPanel(new GridLayout(2,1));
		ImageIcon title = Main.getImageIcon("bar.png");
		JLabel tlabel = new JLabel("");
		tlabel.setIcon(title);
		top.add(tlabel);
		tlabel.setSize(182,42);
		top.setOpaque(false);
		top.setSize(300,200);
		top.setLocation(300,55);
		principal.add(top,JLayeredPane.MODAL_LAYER);
		JLabel fondo = new JLabel("");
		fondo.setIcon(Main.getImageIcon("hall.png"));
		fondo.setSize(new Dimension(495,350));
		principal.add(fondo,JLayeredPane.PALETTE_LAYER);
		fondo.setLocation(130,150);
		JPanel scores = new JPanel();
		scores.setOpaque(false);
		scores.setSize(400,340);
		scores.setLocation(130,150);
		
		back.setSize(new Dimension(90,20));
		back.setLocation(260,520);
		back.addActionListener(this);
		
		JButton clear = new JButton("Borrar Highscores");
		clear.setSize(new Dimension(140,20));
		clear.setLocation(360,520);
		clear.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				clearHighscores();
			}});
		principal.add(clear,JLayeredPane.POPUP_LAYER);
		principal.add(scores,JLayeredPane.POPUP_LAYER);
		principal.add(back,JLayeredPane.POPUP_LAYER);
	}
	
	public void clearHighscores(){
		for(int i=0; i<highscores.length; i++){
			highscores[i] = null;
		}
	}
	/**
	 * Action Performed del boton regresar
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == back){
			this.dispose();
			Main.principal.setVisible(true);
		}
	}
	
	public static void setHighscore(String name, long time, int kills){
		Highscore nuevo = new Highscore(name,time,kills);
		int pos = isHighscore(time,kills);
		if(pos>=0){
			//-- Recorrer highscores
			for(int i = highscores.length-2; i>=pos; i--){
				highscores[i] = highscores[i+1];
			}
		}
		highscores[pos] = nuevo;
	}
	
	public static int isHighscore(long time, int kills){ 
		for(int i=0; i<highscores.length; i++){
			if(highscores!=null){
				if(highscores[i].getKills() < kills){
					return i;
				} else if(highscores[i].getKills() == kills && highscores[i].getTime() > time){
					return i;
				}
			}
		}
		return -1;
	}
	
	public static class Highscore { 
		protected String name = "";
		protected long time = 0;
		protected int kills = 0;
		
		public Highscore(String name, long time, int kills){
			this.name = name;
			this.time = time;
			this.kills = kills;
		}
		
		public String getName(){return name;}
		public long getTime(){return time;}
		public int getKills(){return kills;}
		
	}
}
