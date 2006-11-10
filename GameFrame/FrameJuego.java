import javax.swing.*;

import java.awt.*;

public class FrameJuego extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	PanelJuego pj;
	
	public FrameJuego(){
		this.setSize(707,583);
		pj = new PanelJuego(GameMaps.desierto);
		add(pj);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
	}
	
}
