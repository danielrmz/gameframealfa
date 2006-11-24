import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.event.MouseInputListener;

/** 
 * Clase InitFrame.java 
 * Frame inicial del Juego
 * @author Revolution Software Developers
 **/

public class InitFrame extends JFrame implements MouseInputListener, KeyListener {

	/**
	 * Constante de Eclipse
	 */
	private static final long serialVersionUID = 8392766137162067216L;

	/**
	 * Layer principal de contenido
	 */
	private JLayeredPane principal = this.getLayeredPane();
	
	/**
	 * Label de juego rapido del menu principal
	 */
	private JLabel rapido = new JLabel();
	
	/**
	 * Label de Opciones del juego
	 */
	private JLabel opciones = new JLabel();
	
	/**
	 * Label de Salir del juego
	 */
	private JLabel salir = new JLabel();
	
	/**
	 * Label de Nuevo juego
	 */
	private JLabel nuevo = new JLabel();
	
	/**
	 * Label de Bar de la fama
	 */
	private JLabel highs = new JLabel();
	
	/**
	 * Selected label
	 */
	private JLabel selected = new JLabel();
	
	private int opcionactual = 0;
	
	public static SoundTest st;
	
	/**
	 * Constructor inicial de la aplicacion GUI
	 */
	public InitFrame() {
		Main.setDefaults(this);
		//-- Fondo
		JLabel fondo = new JLabel();
		
		//-- Imagenes
		ImageIcon imgfondo    = Main.getImageIcon("principal/menu_bg.png");
		ImageIcon imgselected = Main.getImageIcon("principal/selected.png");
		ImageIcon imgnuevo    = Main.getImageIcon("principal/nuevo.png");
		ImageIcon imgrapido   = Main.getImageIcon("principal/rapido.png");
		ImageIcon imgbar 	  = Main.getImageIcon("principal/bar.png");
		ImageIcon imgopciones = Main.getImageIcon("principal/opciones.png");
		ImageIcon imgsalir 	  = Main.getImageIcon("principal/salir.png");
		
		//-- Relacionar
		fondo.setIcon(imgfondo);
		nuevo.setIcon(imgnuevo);
		rapido.setIcon(imgrapido);
		opciones.setIcon(imgopciones);
		highs.setIcon(imgbar);
		salir.setIcon(imgsalir);
		selected.setIcon(imgselected);
		
		//-- Tamaños
		fondo.setSize(new Dimension(208,208));
		selected.setSize(new Dimension(170,42));
		nuevo.setSize(new Dimension(150,12));
		rapido.setSize(new Dimension(150,15));
		opciones.setSize(new Dimension(150,12));
		highs.setSize(new Dimension(150,11));
		salir.setSize(new Dimension(150,12));
		
		//-- Posiciones
		fondo.setLocation(new Point(270,300));
		selected.setLocation(new Point(285,310));
		nuevo.setLocation(new Point(320,325));
		rapido.setLocation(new Point(320,355));
		highs.setLocation(new Point(320,385));
		opciones.setLocation(new Point(320,415));
		salir.setLocation(new Point(320,445));
		
		
		//-- Agregar
		principal.add(fondo,JLayeredPane.PALETTE_LAYER);
		principal.add(selected,JLayeredPane.MODAL_LAYER);
		principal.add(nuevo,JLayeredPane.POPUP_LAYER);
		principal.add(rapido,JLayeredPane.POPUP_LAYER);
		principal.add(highs,JLayeredPane.POPUP_LAYER);
		principal.add(opciones,JLayeredPane.POPUP_LAYER);
		principal.add(salir,JLayeredPane.POPUP_LAYER);
		
		//-- Action Listeners
		nuevo.addMouseListener(this);
		rapido.addMouseListener(this);
		opciones.addMouseListener(this);
		highs.addMouseListener(this);
		salir.addMouseListener(this);
		this.addKeyListener(this);
		
		if(st == null){
			st = new SoundTest(new File("sound/superman.mid"));
		} else { 
			st.sequencer.start();
		}
	}
	
	/**
	 * Metodo del mouse clicked
	 */
	public void mouseClicked(MouseEvent e) {
		Object clicked = e.getSource();
		if(clicked == salir){
			System.exit(1);
			this.dispose();
		} else if(clicked == nuevo){
			PreGameFrame f = new PreGameFrame();
			this.setVisible(false);
			f.setVisible(true);
		} else if(clicked == rapido){
			GameFrame frame = new GameFrame();
			this.setVisible(false);
			frame.setVisible(true);
			st.sequencer.stop();
			frame.mapa.t.start();
		} else if(clicked == opciones){
			ConfigFrame frame = new ConfigFrame();
			this.setVisible(false);
			frame.setVisible(true);
		} else if(clicked == highs){
			HighscoresFrame frame = new HighscoresFrame();
			this.setVisible(false);
			frame.setVisible(true);
		} 
		
	}
	
	/**
	 * Cambia el cursor del mouse si esta dentro del menu y el color de la letra
	 */
	public void mouseEntered(MouseEvent e) {
		JLabel aux = (JLabel)e.getSource();
		Point p = selected.getLocation();
		Point plable = aux.getLocation();
		selected.setLocation(new Point(p.x,plable.y-15));
		Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
		setCursor(cursor);
		this.updatecounter(aux);
	}
	
	/**
	 * Devuelve el cursor a su estado natural
	 */
	public void mouseExited(MouseEvent e) {
		Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
		setCursor(cursor);
	}
	
	public void updatecounter(JLabel src){
		if(src.equals(nuevo)) 			this.opcionactual = 0;
		else if(src.equals(rapido)) 	this.opcionactual = 1;
		else if(src.equals(highs)) 		this.opcionactual = 2;
		else if(src.equals(opciones)) 	this.opcionactual = 3;
		else if(src.equals(salir))		this.opcionactual = 4;
	}
	
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	public void mouseDragged(MouseEvent arg0) {}
	public void mouseMoved(MouseEvent arg0) {}
	public void keyTyped(KeyEvent arg0) {}
	public void keyReleased(KeyEvent arg0) {}
	
	/**
	 * Metodo que detecta que elemento del menu ha sido seleccionado
	 */
	public void keyPressed(KeyEvent e) {
		Point p = selected.getLocation();
		
		switch(e.getKeyCode()){
		case KeyEvent.VK_DOWN:
			if(this.opcionactual == 4) return;
			selected.setLocation(new Point(p.x,p.y+30));
			this.opcionactual++;
			break;
		case KeyEvent.VK_UP:
			if(this.opcionactual == 0) return;
			selected.setLocation(new Point(p.x,p.y-30));
			this.opcionactual--;
			break;
		case KeyEvent.VK_F4:
			st.sequencer.stop();
		break;
		case KeyEvent.VK_ENTER:
			MouseEvent e2;
			switch(this.opcionactual){
			case 0: 
				e2 = new MouseEvent(nuevo, 0, 0, 0, 0, 0, 1,false,1);
				this.mouseClicked(e2);
				break;
			case 1: 
				e2 = new MouseEvent(rapido, 0, 0, 0, 0, 0, 1,false,1);
				this.mouseClicked(e2);
				break;
			
			case 2: 
				e2 = new MouseEvent(highs, 0, 0, 0, 0, 0, 1,false,1);
				this.mouseClicked(e2);
				break;
			case 3: 
				e2 = new MouseEvent(opciones, 0, 0, 0, 0, 0, 1,false,1);
				this.mouseClicked(e2);
				break;
			case 4: 
				e2 = new MouseEvent(salir, 0, 0, 0, 0, 0, 1,false,1);
				this.mouseClicked(e2);
				break;
			
			}
			break;
		}
	}

	
}
