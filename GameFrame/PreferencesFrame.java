import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.MouseInputListener;


/** 
 * Clase PreferencesFrame.java 
 *
 * @author Revolution Software Developers
 * @package drunkenman
 **/

public class PreferencesFrame extends JFrame implements MouseInputListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Layer de contenido
	 */
	private JLayeredPane principal = this.getLayeredPane();
	
	/**
	 * Boton de regresar
	 */
	JButton regresar;

	public PreferencesFrame() {
		Main.setDefaults(this);
		
		JPanel preferencias = new JPanel(new GridLayout(4,1));
		
		JPanel ItemsPanel = new JPanel(new GridLayout(2,1));
		JPanel ItemsOpcionPanel = new JPanel(new GridLayout(1,2));
		
		JPanel DifPanel = new JPanel(new GridLayout(2,1));
		JPanel DifOpcionPanel = new JPanel(new GridLayout(1,3));
		
		JPanel ContPanel = new JPanel(new GridLayout(2,1));
		JPanel ContOpcionPanel = new JPanel(new GridLayout(4,3));
		
		JPanel SonidoPanel = new JPanel(new GridLayout(2,1));
		JPanel SonidoOpcionPanel = new JPanel(new GridLayout(1,2));
		
		
		JLabel UsarItems = new JLabel("Usar Items");
		JPanel SiItemsPanel = new JPanel();
		SiItemsPanel.setOpaque(false);
	    JPanel NoItemsPanel = new JPanel();
	    NoItemsPanel.setOpaque(false);
		JLabel SiItems = new JLabel("Si");
		JRadioButton SiItemsR = new JRadioButton();
		SiItemsR.setOpaque(false);
		JLabel NoItems = new JLabel("No");
		JRadioButton NoItemsR = new JRadioButton();
		NoItemsR.setOpaque(false);
		SiItemsPanel.add(SiItemsR);
		SiItemsPanel.add(SiItems);
		NoItemsPanel.add(NoItemsR);
		NoItemsPanel.add(NoItems);
		
		JLabel Dificultad = new JLabel("Dificultad");
		JPanel DifAltaPanel = new JPanel();
		JRadioButton DifAltaR = new JRadioButton();
		DifAltaR.setOpaque(false);
		JLabel DifAlta = new JLabel("Alta");
		JPanel DifMediaPanel = new JPanel();
		JRadioButton DifMediaR = new JRadioButton();
		DifMediaR.setOpaque(false);
		JLabel DifMedia = new JLabel("Media");
		JPanel DifBajaPanel = new JPanel();
		JRadioButton DifBajaR = new JRadioButton();
		DifBajaR.setOpaque(false);
		JLabel DifBaja = new JLabel("Baja");
		DifAltaPanel.add(DifAltaR);
		DifAltaPanel.add(DifAlta);
		DifAltaPanel.setOpaque(false);
		DifMediaPanel.add(DifMediaR);
		DifMediaPanel.add(DifMedia);
		DifMediaPanel.setOpaque(false);
		DifBajaPanel.add(DifBajaR);
		DifBajaPanel.add(DifBaja);
		DifBajaPanel.setOpaque(false);
		
		JLabel ConfControl = new JLabel("Configurar Control");
		JLabel Arriba = new JLabel("");
		JLabel Abajo = new JLabel("");
		JLabel Izquierda = new JLabel("");
		JLabel Derecha = new JLabel("");
		JTextField TArriba = new JTextField("I");
		JTextField TAbajo = new JTextField("K");
		JTextField TIzquierda = new JTextField("J");
		JTextField TDerecha = new JTextField("L");
		
		JLabel Sonido = new JLabel("Sonido");
		JPanel SonidoSiPanel = new JPanel();
		JLabel SonidoSi = new JLabel("Si");
		JRadioButton SonidoSiR = new JRadioButton();
		SonidoSiR.setOpaque(false);
		JPanel SonidoNoPanel = new JPanel();
		JLabel SonidoNo = new JLabel("No");
		JRadioButton SonidoNoR = new JRadioButton();
		SonidoNoR.setOpaque(false);
		SonidoSiPanel.add(SonidoSiR);
		SonidoSiPanel.add(SonidoSi);
		SonidoSiPanel.setOpaque(false);
		SonidoNoPanel.add(SonidoNoR);
		SonidoNoPanel.add(SonidoNo);
		SonidoNoPanel.setOpaque(false);
		
		ItemsOpcionPanel.add(SiItemsPanel);
		ItemsOpcionPanel.add(NoItemsPanel);
		ItemsOpcionPanel.setOpaque(false);
		ItemsPanel.add(UsarItems); 
		ItemsPanel.add(ItemsOpcionPanel);
		
		DifOpcionPanel.add(DifAltaPanel);
		DifOpcionPanel.add(DifMediaPanel);
		DifOpcionPanel.add(DifBajaPanel);
		DifOpcionPanel.setOpaque(false);
		DifPanel.add(Dificultad);
		DifPanel.add(DifOpcionPanel);
		
		ContOpcionPanel.add(new JLabel());
		ContOpcionPanel.add(TArriba);
		ContOpcionPanel.add(new JLabel());
		ContOpcionPanel.add(TIzquierda);
		ContOpcionPanel.add(Arriba);
		ContOpcionPanel.add(TDerecha);
		ContOpcionPanel.add(Izquierda);
		ContOpcionPanel.add(Abajo);
		ContOpcionPanel.add(Derecha);
		ContOpcionPanel.add(new JLabel());
		ContOpcionPanel.add(TAbajo);
		ContOpcionPanel.add(new JLabel());
		ContOpcionPanel.setOpaque(false);
		ContPanel.add(ConfControl);
		ContPanel.add(ContOpcionPanel);
		
		SonidoOpcionPanel.add(SonidoSiPanel);
		SonidoOpcionPanel.add(SonidoNoPanel);
		SonidoOpcionPanel.setOpaque(false);
		SonidoPanel.add(Sonido);
		SonidoPanel.add(SonidoOpcionPanel);
		
		ItemsPanel.setOpaque(false);
		DifPanel.setOpaque(false);
		ContPanel.setOpaque(false);
		SonidoPanel.setOpaque(false);
		
		preferencias.add(ItemsPanel);
		preferencias.add(DifPanel);
		preferencias.add(ContPanel);
		preferencias.add(SonidoPanel);
		preferencias.setSize(400,400);
		preferencias.setLocation(150,100);
		preferencias.setOpaque(false);
		
		JLabel fondo = new JLabel();
		fondo.setIcon(Main.getImageIcon("opciones.png"));
		fondo.setSize(485,420);
		fondo.setLocation(130,100);
		
		regresar = new JButton("Regresar");
		regresar.setSize(100,30);
		regresar.setLocation(320,530);
		
		principal.add(regresar,JLayeredPane.MODAL_LAYER);
		principal.add(fondo, JLayeredPane.PALETTE_LAYER);
		principal.add(preferencias,JLayeredPane.MODAL_LAYER);
		
		regresar.addMouseListener(this);
		
		
			
		
	}
	
	/**
	 * Para regresar
	 */
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==regresar){
			this.dispose();
			Main.principal.setVisible(true);
		}
		
		
	}
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
