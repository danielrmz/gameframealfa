import javax.swing.JFrame;

public class FrameJuego extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	PanelJuego pj;
	
	public FrameJuego(){
		//-- Cambiar por mapa
		int width = GameMaps.desierto[0].length * 50+10;
		int height = GameMaps.desierto.length * 50+35;
		
		this.setSize(width,height);
		pj = new PanelJuego(GameMaps.desierto);
		add(pj);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
	}
	
}
