import javax.swing.*;

public class FrameJuego extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public PanelJuego pj;
	
	private JLayeredPane principal = null;
	
	public FrameJuego(){
		principal = this.getLayeredPane();
		
		//-- Cambiar por mapa
		int width = GameMaps.desierto[0].length * 50+10;
		int height = GameMaps.desierto.length * 50+35;
		int[][] map = new int[GameMaps.desierto.length][GameMaps.desierto[0].length];
		copyMap(GameMaps.desierto,map);
		this.setSize(width,height);
		pj = new PanelJuego(map,"desierto",this);
		add(pj);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}
	
	public void makeWinnerAnimation(Player winner){
		
		
	}
	
	public static void copyMap(int[][] base, int[][] dest){
		for(int i=0;i<base.length;i++){
			for(int j=0;j<base[0].length;j++){
				dest[i][j] = base[i][j];
			}
		}
	}
	
}
