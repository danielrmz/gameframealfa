import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;


public class ConfigFrame extends JFrame implements MouseListener,KeyListener {
	
	JLabel itemslbl;
	JLabel siItems;
	JLabel noItems;
	JRadioButton siItemsRb;
	JRadioButton noItemsRb;
	
	JLabel diflbl;
	JLabel difFacil;
	JLabel difNormal;
	JLabel difDif;
	JRadioButton facilRb;
	JRadioButton normalRb;
	JRadioButton difRb;
	
	JLabel sonidolbl;
	JLabel sonidoSi;
	JLabel sonidoNo;
	JRadioButton sonidoSiRb;
	JRadioButton sonidoNoRb;
	
	JLabel controlslbl;
	
	JLabel player1;
	JLabel arriba1;
	JTextField arriba1tf;
	JLabel abajo1;
	JTextField abajo1tf;
	JLabel derecha1;
	JTextField derecha1tf;
	JLabel izquierda1;
	JTextField izquierda1tf;
	JLabel bomba1;
	JTextField bomba1tf;
	
	JLabel player2;
	JLabel arriba2;
	JTextField arriba2tf;
	JLabel abajo2;
	JTextField abajo2tf;
	JLabel derecha2;
	JTextField derecha2tf;
	JLabel izquierda2;
	JTextField izquierda2tf;
	JLabel bomba2;
	JTextField bomba2tf;
	
	JButton regresar;
	JButton guardar;
	
	
	public ConfigFrame(){
		setLocation(250,100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,430);
		setLayout(new BorderLayout());
		
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(9,1));
		JPanel nullPanel1 = new JPanel();
		JPanel nullPanel2 = new JPanel();
		
		
		JPanel items = new JPanel();
		items.setLayout(new GridLayout(1,3));
		itemslbl = new JLabel("    Items");
		items.add(itemslbl);
		
		JPanel siItemsPanel = new JPanel();
		siItemsPanel.setLayout(new GridLayout(1,2));
	    siItems = new JLabel("Si");
	    siItemsRb = new JRadioButton();
	    siItemsPanel.add(siItems);
	    siItemsPanel.add(siItemsRb);
	    items.add(siItemsPanel);
	    
		JPanel noItemsPanel = new JPanel();
		noItemsPanel.setLayout(new GridLayout(1,2));
		noItems = new JLabel("No");
		noItemsRb = new JRadioButton();
		noItemsPanel.add(noItems);
		noItemsPanel.add(noItemsRb);
		items.add(noItemsPanel);
		
		
		JPanel difPanel = new JPanel();
		difPanel.setLayout(new GridLayout(1,4));
		diflbl = new JLabel("   Dificultad");
		difPanel.add(diflbl);
		
		JPanel difFacilPanel = new JPanel();
		difFacilPanel.setLayout(new GridLayout(1,2));
		difFacil = new JLabel("Facil");
		facilRb = new JRadioButton();
		difFacilPanel.add(difFacil);
		difFacilPanel.add(facilRb);
		difPanel.add(difFacilPanel);
		
		JPanel difNormalPanel = new JPanel();
		difNormalPanel.setLayout(new GridLayout(1,2));
		difNormal = new JLabel("Normal");
		normalRb = new JRadioButton();
		difNormalPanel.add(difNormal);
		difNormalPanel.add(normalRb);
		difPanel.add(difNormalPanel);
		
		JPanel difDificilPanel = new JPanel();
		difDificilPanel.setLayout(new GridLayout(1,2));
		difDif= new JLabel("Dificil");
		difRb = new JRadioButton();
		difDificilPanel.add(difDif);
		difDificilPanel.add(difRb);
		difPanel.add(difDificilPanel);
		
	
		JPanel sonidoPanel = new JPanel();
		sonidoPanel.setLayout(new GridLayout(1,3));
		sonidolbl = new JLabel("   Sonido");
		sonidoPanel.add(sonidolbl);
		
		JPanel sonidoSiPanel = new JPanel();
		sonidoSiPanel.setLayout(new GridLayout(1,2));
		sonidoSi = new JLabel("Si");
		sonidoSiRb = new JRadioButton();
		sonidoSiPanel.add(sonidoSi);
		sonidoSiPanel.add(sonidoSiRb);
		sonidoPanel.add(sonidoSiPanel);
		
		JPanel sonidoNoPanel = new JPanel();
		sonidoNoPanel.setLayout(new GridLayout(1,2));
		sonidoNo = new JLabel("No");
		sonidoNoRb = new JRadioButton();
		sonidoNoPanel.add(sonidoNo);
		sonidoNoPanel.add(sonidoNoRb);
		sonidoPanel.add(sonidoNoPanel);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(1,1));
		
		JPanel controlesPanel = new JPanel();
		controlesPanel.setLayout(new GridLayout(1,2));
		
		JPanel player1Panel = new JPanel();
		player1Panel.setLayout(new GridLayout(6,2));
		player1 = new JLabel("           Jugador 1");
		arriba1 = new JLabel("   Arriba");
		arriba1tf = new JTextField();
		abajo1 = new JLabel("   Abajo");
		abajo1tf = new JTextField();
		derecha1 = new JLabel("   Derecha");
		derecha1tf = new JTextField();
		izquierda1 = new JLabel("   Izquierda");
		izquierda1tf = new JTextField();
		bomba1 = new JLabel("   Bomba");
		bomba1tf = new JTextField();
		
		player1Panel.add(player1);
		player1Panel.add(new JLabel());
		player1Panel.add(arriba1);
		player1Panel.add(arriba1tf);
		player1Panel.add(abajo1);
		player1Panel.add(abajo1tf);
		player1Panel.add(derecha1);
		player1Panel.add(derecha1tf);
		player1Panel.add(izquierda1);
		player1Panel.add(izquierda1tf);
		player1Panel.add(izquierda1tf);
		player1Panel.add(bomba1);
		player1Panel.add(bomba1tf);
		
		controlesPanel.add(player1Panel);
		
		JPanel player2Panel = new JPanel();
		player2Panel.setLayout(new GridLayout(6,2));
		player2 = new JLabel("           Jugador 2");
		arriba2 = new JLabel("   Arriba");
		arriba2tf = new JTextField();
		abajo2 = new JLabel("   Abajo");
		abajo2tf = new JTextField();
		derecha2 = new JLabel("   Derecha");
		derecha2tf = new JTextField();
		izquierda2 = new JLabel("   Izquierda");
		izquierda2tf = new JTextField();
		bomba2 = new JLabel("   Bomba");
		bomba2tf = new JTextField();
		
		player2Panel.add(player2);
		player2Panel.add(new JLabel());
		player2Panel.add(arriba2);
		player2Panel.add(arriba2tf);
		player2Panel.add(abajo2);
		player2Panel.add(abajo2tf);
		player2Panel.add(derecha2);
		player2Panel.add(derecha2tf);
		player2Panel.add(izquierda2);
		player2Panel.add(izquierda2tf);
		player2Panel.add(izquierda2tf);
		player2Panel.add(bomba2);
		player2Panel.add(bomba2tf);
		
		controlesPanel.add(player2Panel);
		
		controlslbl = new JLabel("   Controles");
		
		centerPanel.add(controlesPanel);
		northPanel.add(new JLabel("                                                                                    CONFIGURACION"));
		northPanel.add(new JLabel());
		northPanel.add(items);
		northPanel.add(nullPanel1);
		northPanel.add(sonidoPanel);
		northPanel.add(nullPanel2);
		northPanel.add(difPanel);
		northPanel.add(new JPanel());
		northPanel.add(controlslbl);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(3,5));
		bottomPanel.add(new JLabel());
		bottomPanel.add(new JLabel());
		bottomPanel.add(new JLabel());
		bottomPanel.add(new JLabel());
		bottomPanel.add(new JLabel());
		bottomPanel.add(new JLabel());
		regresar = new JButton("Regresar");
		bottomPanel.add(regresar);
		bottomPanel.add(new JLabel());
		guardar = new JButton("Aplicar");
		bottomPanel.add(guardar);
		bottomPanel.add(new JLabel());
		bottomPanel.add(new JLabel());
		bottomPanel.add(new JLabel());
		bottomPanel.add(new JLabel());
		bottomPanel.add(new JLabel());
		bottomPanel.add(new JLabel());
		
		this.add(northPanel,BorderLayout.NORTH);
		this.add(centerPanel,BorderLayout.CENTER);
		this.add(bottomPanel,BorderLayout.SOUTH);
		
		this.setVisible(true);
		
		arriba1tf.addKeyListener(this);
		arriba2tf.addKeyListener(this);
		abajo1tf.addKeyListener(this);
		abajo2tf.addKeyListener(this);
		derecha1tf.addKeyListener(this);
		derecha2tf.addKeyListener(this);
		izquierda1tf.addKeyListener(this);
		izquierda2tf.addKeyListener(this);
		bomba1tf.addKeyListener(this);
		bomba2tf.addKeyListener(this);
		
		regresar.addMouseListener(this);
	}

	public void mouseClicked(MouseEvent mc) {
		if(mc.getSource() == regresar){
			System.out.println("clic");
			InitFrame inicio = new InitFrame();
			inicio.setVisible(true);
			this.dispose();
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

	public void keyPressed(KeyEvent ke) {
		Object o = ke.getSource();
		JTextField aux = (JTextField) o;
		aux.setText(KeyEvent.getKeyText(ke.getKeyCode()));
		
	
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
