import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class PreGameFrame extends JFrame implements MouseListener{
	
	private static final long serialVersionUID = 1L;
	
	JLayeredPane principal = this.getLayeredPane();
	
	JLabel backGround;
	JLabel villa;
	JLabel player1;
	JLabel zapata;
	JLabel player2;
	JLabel mundoCantina;
	JLabel mundoNormal;
	JLabel mundoDesierto;
	JLabel frameMundo;
	JLabel nombreCantina;
	JLabel nombreNormal;
	JLabel nombreDesierto;
	
	JButton jugar; 
	JButton regresar;
	
	public static ImageIcon getImageIcon(String filename){	
		ImageIcon image = new ImageIcon(filename);
		if(image.getImageLoadStatus()==4) return null;
		return image;
	}
	
	public PreGameFrame(){
		setSize(752,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		backGround = new JLabel();
		backGround.setIcon(getImageIcon("cantina_main.png"));
		backGround.setSize(752,600);
		backGround.setLocation(0,0);
		principal.add(backGround,JLayeredPane.DEFAULT_LAYER);
		
		villa = new JLabel();
		villa.setIcon(getImageIcon("img/player 1.png"));
		villa.setSize(120,180);
		villa.setLocation(480,100);
		principal.add(villa,JLayeredPane.PALETTE_LAYER);
		
		player1 = new JLabel();
		player1.setIcon(getImageIcon("player1.png"));
		player1.setSize(120,180);
		player1.setLocation(480,100);
		principal.add(player1,JLayeredPane.MODAL_LAYER);
		
		zapata = new JLabel();
		zapata.setIcon(getImageIcon("img/player 2.png"));
		zapata.setSize(120,180);
		zapata.setLocation(200,100);
		principal.add(zapata,JLayeredPane.PALETTE_LAYER);
		
		player2 = new JLabel();
		player2.setIcon(getImageIcon("player2.png"));
		player2.setSize(120,180);
		player2.setLocation(200,100);
		principal.add(player2,JLayeredPane.MODAL_LAYER);
		
		mundoCantina = new JLabel();
		mundoCantina.setIcon(getImageIcon("cantina.png"));
		mundoCantina.setSize(150,150);
		mundoCantina.setLocation(100,350);
		principal.add(mundoCantina,JLayeredPane.MODAL_LAYER);
		
		mundoNormal = new JLabel();
		mundoNormal.setIcon(getImageIcon("normal.png"));
		mundoNormal.setSize(150,150);
		mundoNormal.setLocation(300,350);
		principal.add(mundoNormal,JLayeredPane.MODAL_LAYER);
		
		mundoDesierto = new JLabel();
		mundoDesierto.setIcon(getImageIcon("desierto.png"));
		mundoDesierto.setSize(150,150);
		mundoDesierto.setLocation(500,350);
		principal.add(mundoDesierto,JLayeredPane.MODAL_LAYER);
		
		frameMundo = new JLabel();
		frameMundo.setIcon(getImageIcon("frame.png"));
		frameMundo.setSize(200,200);
		frameMundo.setLocation(482,325);
		principal.add(frameMundo,JLayeredPane.PALETTE_LAYER);
		
		nombreCantina = new JLabel();
		nombreCantina.setIcon(getImageIcon("nombreCantina.png"));
		nombreCantina.setSize(200,200);
		nombreCantina.setLocation(530,230);
		principal.add(nombreCantina,JLayeredPane.MODAL_LAYER);
		
		nombreNormal = new JLabel();
		nombreNormal.setIcon(getImageIcon("nombreNormal.png"));
		nombreNormal.setSize(200,200);
		nombreNormal.setLocation(330,230);
		principal.add(nombreNormal,JLayeredPane.MODAL_LAYER);
		
		nombreDesierto = new JLabel();
		nombreDesierto.setIcon(getImageIcon("nombreDesierto.png"));
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
	
	public void seleccion(String s){
		if(s.equals("villa")){
			player1.setLocation(480,100);
			player2.setLocation(200,100);
		}else if(s.equals("zapata")){
			player1.setLocation(200,100);
			player2.setLocation(480,100);
		}
	}

	public void mouseClicked(MouseEvent mc) {
		if((mc.getX()>480 && mc.getX()<600) && (mc.getY()>125 && mc.getY()<305)){
			seleccion("villa");
		}else if((mc.getX()>200 && mc.getX()<320) && (mc.getY()>125 && mc.getY()<305)){
			seleccion("zapata");
		}else if((mc.getX()>100 && mc.getX()<250) && (mc.getY()>370 && mc.getY()<530)){
			frameMundo.setLocation(82,325);
		}else if((mc.getX()>300 && mc.getX()<450) && (mc.getY()>370 && mc.getY()<530)){
			frameMundo.setLocation(282,325);
		}else if((mc.getX()>500 && mc.getX()<650) && (mc.getY()>370 && mc.getY()<530)){
			frameMundo.setLocation(482,325);
		}else if(mc.getSource() == jugar){
			System.out.println("click");
			GameFrame frame = new GameFrame();
			this.setVisible(false);
			frame.setVisible(true);
			frame.mapa.t.start();
		}else if(mc.getSource() == regresar){
			InitFrame inicio = new InitFrame();
			this.setVisible(false);
			inicio.setVisible(true);
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

}
