

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
	 * 
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
		fondo.setIcon(Main.getImageIcon("principal.png"));
		fondo.setSize(new Dimension(490,340));
		principal.add(fondo,JLayeredPane.PALETTE_LAYER);
		fondo.setLocation(130,150);
		JPanel scores = new JPanel();
		scores.setOpaque(false);
		scores.setSize(400,340);
		scores.setLocation(130,150);
		//TODO: para el prototipo 2 hacer un ciclo con los highscores en la tabla
		
		back.setSize(new Dimension(90,20));
		back.setLocation(330,500);
		back.addActionListener(this);
		
		principal.add(scores,JLayeredPane.POPUP_LAYER);
		principal.add(back,JLayeredPane.POPUP_LAYER);
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

}
