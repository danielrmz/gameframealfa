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
		villa.setIcon(getImageIcon("villa.png"));
		villa.setSize(120,180);
		villa.setLocation(480,100);
		principal.add(villa,JLayeredPane.PALETTE_LAYER);
		
		player1 = new JLabel();
		player1.setIcon(getImageIcon("player1.png"));
		player1.setSize(120,180);
		player1.setLocation(480,100);
		principal.add(player1,JLayeredPane.MODAL_LAYER);
		
		zapata = new JLabel();
		zapata.setIcon(getImageIcon("zapata.png"));
		zapata.setSize(120,180);
		zapata.setLocation(200,100);
		principal.add(zapata,JLayeredPane.PALETTE_LAYER);
		
		player2 = new JLabel();
		player2.setIcon(getImageIcon("player2.png"));
		player2.setSize(120,180);
		player2.setLocation(200,100);
		principal.add(player2,JLayeredPane.MODAL_LAYER);
		
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
