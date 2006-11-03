import javax.swing.*;
import java.awt.*;

public class FrameJuego extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	PanelJuego pj;
	
	public FrameJuego(){
		this.setSize(800,600);
		this.setPreferredSize(new Dimension(800,600));
		pj = new PanelJuego();
		add(pj);
	}
	
}
