import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

/**
 * Clase que personaliza el juego antes de empezar
 * @author Revolution Software Developers
 */
public class PreGameFrame extends JFrame implements MouseListener{
	/**
	 * Constante de Eclipse
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Contenedor de layers
	 */
	private JLayeredPane principal = this.getLayeredPane();
	
	/**
	 * Jugador 1
	 */
	public JLabel villa;
	
	/**
	 * Label Player 1
	 */
	public JLabel player1;
	
	/**
	 * Imagen de zapata
	 */
	public JLabel zapata;
	
	/**
	 * jugador 2
	 */
	public JLabel player2;
	
	/**
	 * Imagen del mundo cantina
	 */
	public JLabel mundoCantina;
	
	/**
	 * Imagen del mundo normal
	 */
	public JLabel mundoNormal;
	
	/**
	 * Imagen del mundo desierto
	 */
	public JLabel mundoDesierto;
	
	/**
	 * frame que indica el mundo seleccinoado
	 */
	public JLabel frameMundo;
	
	/**
	 * Nombre de l mundo cantina
	 */
	public JLabel nombreCantina;
	
	/**
	 * Nombredel mundo normal
	 */
	public JLabel nombreNormal;
	
	/**
	 * Nombre del mundo desierto
	 */
	public JLabel nombreDesierto;
	
	/**
	 * Boton d jugar
	 */
	public JButton jugar; 
	
	/**
	 * Boton de regresar
	 */
	public JButton regresar;
	
	/**
	 * Varible que indica el mapa actual seleccionado
	 */
	public int mapaactual = 1;
	
	/**
	 * Varible que indica el jugador seleccionado por el primero
	 */
	public int playeractual = 2;
	
	/**
	 * Trae la imagen
	 * @param filename
	 * @return
	 */
	public static ImageIcon getImageIcon(String filename){	
		ImageIcon image = new ImageIcon(filename);
		if(image.getImageLoadStatus()==4) return null;
		return image;
	}
	
	/**
	 * Constructor Vacio
	 */
	public PreGameFrame(){
		Main.setDefaults(this);
		
		villa = new JLabel();
		villa.setIcon(getImageIcon("img/player 1.png"));
		villa.setSize(120,180);
		villa.setLocation(480,100);
		principal.add(villa,JLayeredPane.PALETTE_LAYER);
		
		player1 = new JLabel();
		player1.setIcon(getImageIcon("img/pregameframe/player1.png"));
		player1.setSize(120,180);
		player1.setLocation(480,100);
		principal.add(player1,JLayeredPane.MODAL_LAYER);
		
		zapata = new JLabel();
		zapata.setIcon(getImageIcon("img/player 2.png"));
		zapata.setSize(120,180);
		zapata.setLocation(200,100);
		principal.add(zapata,JLayeredPane.PALETTE_LAYER);
		
		player2 = new JLabel();
		player2.setIcon(getImageIcon("img/pregameframe/player2.png"));
		player2.setSize(120,180);
		player2.setLocation(200,100);
		principal.add(player2,JLayeredPane.MODAL_LAYER);
		
		mundoCantina = new JLabel();
		mundoCantina.setIcon(getImageIcon("img/pregameframe/cantina.png"));
		mundoCantina.setSize(150,150);
		mundoCantina.setLocation(500,350);
		principal.add(mundoCantina,JLayeredPane.MODAL_LAYER);
		
		mundoNormal = new JLabel();
		mundoNormal.setIcon(getImageIcon("img/pregameframe/normal.png"));
		mundoNormal.setSize(150,150);
		mundoNormal.setLocation(300,350);
		principal.add(mundoNormal,JLayeredPane.MODAL_LAYER);
		
		mundoDesierto = new JLabel();
		mundoDesierto.setIcon(getImageIcon("img/pregameframe/desierto.png"));
		mundoDesierto.setSize(150,150);
		mundoDesierto.setLocation(100,350);
		principal.add(mundoDesierto,JLayeredPane.MODAL_LAYER);
		
		frameMundo = new JLabel();
		frameMundo.setIcon(getImageIcon("img/pregameframe/frame.png"));
		frameMundo.setSize(200,200);
		frameMundo.setLocation(482,325);
		principal.add(frameMundo,JLayeredPane.PALETTE_LAYER);
		
		nombreCantina = new JLabel();
		nombreCantina.setIcon(getImageIcon("img/pregameframe/nombreCantina.png"));
		nombreCantina.setSize(200,200);
		nombreCantina.setLocation(530,230);
		principal.add(nombreCantina,JLayeredPane.MODAL_LAYER);
		
		nombreNormal = new JLabel();
		nombreNormal.setIcon(getImageIcon("img/pregameframe/nombreNormal.png"));
		nombreNormal.setSize(200,200);
		nombreNormal.setLocation(330,230);
		principal.add(nombreNormal,JLayeredPane.MODAL_LAYER);
		
		nombreDesierto = new JLabel();
		nombreDesierto.setIcon(getImageIcon("img/pregameframe/nombreDesierto.png"));
		nombreDesierto.setSize(200,200);
		nombreDesierto.setLocation(130,230);
		principal.add(nombreDesierto,JLayeredPane.MODAL_LAYER);
		
		jugar = new JButton("Jugar");
		jugar.setSize(100,30);
		jugar.setLocation(525,520);
		principal.add(jugar,JLayeredPane.MODAL_LAYER);
		jugar.addMouseListener(this);
		
		regresar = new JButton("Regresar");
		regresar.setSize(100,30);
		regresar.setLocation(125,520);
		principal.add(regresar,JLayeredPane.MODAL_LAYER);
		regresar.addMouseListener(this);
		
		addMouseListener(this);
	}
	
	/**
	 * Cambia la imagen del jugador seleccinoada
	 * @param s imagn
	 */
	public void seleccion(String s){
		if(s.equals("villa")){
			player1.setLocation(480,100);
			player2.setLocation(200,100);
			this.playeractual = 2;
		}else if(s.equals("zapata")){
			player1.setLocation(200,100);
			player2.setLocation(480,100);
			this.playeractual = 1;
		}
	}

	/**
	 * Cambia la imagen seleccionada
	 */
	public void mouseClicked(MouseEvent mc) {
		if((mc.getX()>480 && mc.getX()<600) && (mc.getY()>125 && mc.getY()<305)){
			seleccion("villa");
		}else if((mc.getX()>200 && mc.getX()<320) && (mc.getY()>125 && mc.getY()<305)){
			seleccion("zapata");
		}else if((mc.getX()>100 && mc.getX()<250) && (mc.getY()>370 && mc.getY()<530)){
			frameMundo.setLocation(82,325);
			this.mapaactual = 2;
		}else if((mc.getX()>300 && mc.getX()<450) && (mc.getY()>370 && mc.getY()<530)){
			frameMundo.setLocation(282,325);
			this.mapaactual = 3;
		}else if((mc.getX()>500 && mc.getX()<650) && (mc.getY()>370 && mc.getY()<530)){
			frameMundo.setLocation(482,325);
			this.mapaactual = 1;
			
		}else if(mc.getSource() == jugar){
			GameFrame frame = new GameFrame(mapaactual);
			this.setVisible(false);
			frame.setVisible(true);
			boolean p1 = (playeractual == 2);
			frame.mapa.setPlayers(p1);
			InitFrame.st.sequencer.stop();
			frame.mapa.t.start();
		}else if(mc.getSource() == regresar){
			InitFrame inicio = new InitFrame();
			this.dispose();
			inicio.setVisible(true);
		}
		
	}

	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}

}
