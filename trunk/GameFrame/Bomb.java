import java.awt.image.BufferedImage;

public class Bomb {
	
	private int Xpos;
	private int Ypos;
	private BufferedImage BombImage;
	
	public Bomb(int x, int y){
		Xpos = x;
		Ypos = y;
	}
	
	private void calculatePath(int power){
		int[][] idMatrix = PanelJuego.grid;
		
		//add center fire indication
		idMatrix[Xpos][Ypos] = PanelJuego.FUEGO;
		
		
		//check up
		for(int i=Ypos; i<(Ypos+power); i++)
			if(!(idMatrix[Xpos][i] == GameMaps.BLOQUE))
				idMatrix[Xpos][i] = PanelJuego.FUEGO;
	}

}
