import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

/** 
 * Clase PersonalizeFrame.java 
 *
 * @author Revolution Software Developers
 **/

public class PersonalizeFrame extends JFrame implements MouseListener, ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Panel de contenidos
	 */
	private JLayeredPane principal = this.getLayeredPane();
	
	/**
	 * Jugador de seleccion
	 */
	JLabel seleccionJugador;
	
	/**
	 * Jugador1
	 */
	JLabel cuadroJugador1;
	
	/**
	 * Imagen del jugador 2
	 */
	JLabel cuadroJugador2;
	
	/**
	 * Jugador2
	 */
	JLabel nombreJugador1;
	
	/**
	 * Jugador 1
	 */
	JLabel nombreJugador2;
	
	/**
	 * Mundo seleccionado
	 */
	JLabel seleccionMundo;
	
	/**
	 * Imagen del mund
	 */
	JLabel imagenMundo;
	
	/**
	 * Flecha izquierda de seleccion de mapa
	 */
	JLabel flechaIzquierda;
	
	/**
	 * Flecha derecha del mapa
	 */
	JLabel flechaDerecha;
	
	/**
	 * Boton de regresar
	 */
	JButton regresar;
	
	/**
	 * Boton de jugar
	 */
	JButton jugar;
	
	public PersonalizeFrame() {
		Main.setDefaults(this);
		
		
		JLabel seleccionJugador = new JLabel();
		
		JLabel cuadroJugador1 = new JLabel("");
		JLabel cuadroJugador2 = new JLabel("");
		
		JLabel nombreJugador1 = new JLabel();
		JLabel nombreJugador2 = new JLabel();
		
		JLabel seleccionMundo = new JLabel();
		JLabel imagenMundo = new JLabel();
		
		JLabel flechaIzquierda = new JLabel();
		JLabel flechaDerecha = new JLabel();
		
		cuadroJugador1.addMouseListener(this);
		cuadroJugador2.addMouseListener(this);
		
		seleccionJugador.setIcon(Main.getImageIcon("seleccionajugador.png"));
		seleccionJugador.setSize(400,200);
		seleccionJugador.setLocation(220,10);
		
		principal.add(seleccionJugador,JLayeredPane.MODAL_LAYER);
		
		cuadroJugador1.setIcon(Main.getImageIcon("zapataseleccionado.png"));
		cuadroJugador1.setSize(210,390);
		cuadroJugador1.setLocation(180,30);
		
		principal.add(cuadroJugador1,JLayeredPane.MODAL_LAYER);
		
		cuadroJugador2.setIcon(Main.getImageIcon("villa.png"));
		cuadroJugador2.setSize(200,380);
		cuadroJugador2.setLocation(480,30);
		
		principal.add(cuadroJugador2,JLayeredPane.MODAL_LAYER);
		
		nombreJugador1.setIcon(Main.getImageIcon("zapatanombre.png"));
		nombreJugador1.setSize(200,200);
		nombreJugador1.setLocation(180,240);
		
		principal.add(nombreJugador1,JLayeredPane.MODAL_LAYER);
		
		nombreJugador2.setIcon(Main.getImageIcon("villanombre.png"));
		nombreJugador2.setSize(200,200);
		nombreJugador2.setLocation(500,240);
		
		principal.add(nombreJugador2,JLayeredPane.MODAL_LAYER);
		
		seleccionMundo.setIcon(Main.getImageIcon("seleccionmundo.png"));
		seleccionMundo.setSize(400,200);
		seleccionMundo.setLocation(220,280);
		
		principal.add(seleccionMundo,JLayeredPane.MODAL_LAYER);
		
		imagenMundo.setIcon(Main.getImageIcon("cantina.png"));
		imagenMundo.setSize(300,400);
		imagenMundo.setLocation(280,290);
		
		principal.add(imagenMundo,JLayeredPane.MODAL_LAYER);
		
		flechaIzquierda.setIcon(Main.getImageIcon("flechaizquierda.png"));
		flechaIzquierda.setSize(100,100);
		flechaIzquierda.setLocation(210,440);
		
		principal.add(flechaIzquierda,JLayeredPane.MODAL_LAYER);
		
		flechaDerecha.setIcon(Main.getImageIcon("flechaderecha.png"));
		flechaDerecha.setSize(100,100);
		flechaDerecha.setLocation(500,440);
		
		principal.add(flechaDerecha,JLayeredPane.MODAL_LAYER);
		
		regresar = new JButton("Regresar");
		regresar.setSize(100,30);
		regresar.setLocation(60,520);
		principal.add(regresar,JLayeredPane.POPUP_LAYER);
		
		jugar = new JButton("Jugar");
		jugar.setSize(100,30);
		jugar.setLocation(590,520);
		principal.add(jugar,JLayeredPane.POPUP_LAYER);
		
		regresar.addActionListener(this);
		jugar.addActionListener(this);
				
		
	}

	/**
	 * Mouseclicked
	 */
	public void mouseClicked(MouseEvent e) {
		if (e.getSource()==cuadroJugador1){
			cuadroJugador1.setIcon(Main.getImageIcon("zapataseleccionado.png"));
			cuadroJugador2.setIcon(Main.getImageIcon("villa.png"));
		}
		
		if(e.getSource()==cuadroJugador2){
			cuadroJugador2.setIcon(Main.getImageIcon("villaseleccionado.png"));
			cuadroJugador1.setIcon(Main.getImageIcon("zapata.png"));
		}
		
	}
	
	/**
	 * Action Performed
	 */
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Object src = arg0.getSource();
		if(src == jugar){
			GameFrame frame = new GameFrame();
			frame.setVisible(true);
			this.dispose();
		} else if(src == regresar){
			Main.principal.setVisible(true);
			this.dispose();
		}
	}
	
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	

}
