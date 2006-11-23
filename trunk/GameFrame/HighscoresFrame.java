import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;

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
		this.generateHighscores();
	}
	
	public void clearHighscores(){
		for(int i=0; i<highscores.length; i++){
			highscores[i] = null;
		}
		new Serial("highscores.drk",null);
		HighscoresFrame f = new HighscoresFrame();
		f.setVisible(true);
		this.dispose();
	}
	
	public void generateHighscores(){
		Font font = new Font("Arial", Font.BOLD, 16);
		
		for(int i = 0; i < 10; i++){
			if(highscores[i] == null) return;
			JLabel num = new JLabel((i+1)+".");
			num.setFont(font);
			JLabel name = new JLabel(highscores[i].getName());
			name.setFont(font);
			long t = highscores[i].getTime();
			String subt = ((t/60.0)+"");
			String p1 = subt.substring(0,subt.indexOf('.'));
			String p2 = subt.substring(subt.indexOf('.'),subt.indexOf('.')+3);
			JLabel time = new JLabel(p1+p2+" minutos");
			time.setFont(font);
			num.setSize(new Dimension(20,20));
			name.setSize(new Dimension(100,20));
			time.setSize(new Dimension(150,20));
			num.setLocation(140,190+(i*20));
			name.setLocation(155,190+(i*20));
			time.setLocation(500,195+(i*20));
			principal.add(num,JLayeredPane.DRAG_LAYER);
			principal.add(name,JLayeredPane.DRAG_LAYER);
			principal.add(time,JLayeredPane.DRAG_LAYER);
		}
	}
	
	public static void printHighscores(){
		System.out.println("====== Highscores =======");
		for(int i=0; i<highscores.length; i++){
			if(highscores[i]!=null){
				System.out.println((i+1)+". "+highscores[i].getName()+", tiempo: "+highscores[i].getTime());
			}
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
	
	public static void setHighscore(String name, long time){
		Highscore nuevo = new Highscore(name,time);
		int pos = isHighscore(time);
		if(pos>=0){
			//-- Recorrer highscores
			for(int i = highscores.length-2; i>=pos; i--){
				highscores[i+1] = highscores[i];
			}
		}
		highscores[pos] = nuevo;
	}
	
	public static int isHighscore(long time){ 
		for(int i=0; i<highscores.length; i++){
			if(highscores[i]!=null){
				if(highscores[i].getTime() > time && highscores[i].getTime() > 0){
					return i;
				} 
			} else if(highscores[i] == null){
				return i;
			}
		}
		return -1;
	}
	
	public static class Highscore implements Serializable { 
		private static final long serialVersionUID = 1L;
		protected String name = "";
		protected long time = 0;
		
		public Highscore(String name, long time){
			this.name = name;
			this.time = time;
		}
		
		public String getName(){return name;}
		public long getTime(){return time;}
		
	}
	
	/**
	 * Estructura para guardar los highscores
	 * @author Revolution Software Developers
	 */
	public static class HighscoreTable implements Serializable { 
		private static final long serialVersionUID = 1L;
		public Highscore[] table;
		public HighscoreTable(Highscore[] table){
			this.table = table;
		}
	}
}
