import java.io.*;
import java.util.*;

public class SaveStructure implements Serializable {

	private static final long serialVersionUID = -2664409749529086998L;
	
	/**
	 * Bombas actuales
	 */
	//public LinkedList<Bomb> bombs = new LinkedList<Bomb>();
	
	/**
	 * Mundo actual, indica tambien el directorio
	 */
	public String mundo = "";
	
	/**
	 * Grid del mundo
	 */
	public int[][] grid;
	

	/**
	 * Tiempo actual de juego
	 */
	public long gameTime = 0;
	
	public SaveStructure(LinkedList<Bomb> bombs,long gameTime, int[][] grid, String mundo) {
		//this.bombs = bombs;
		this.gameTime = gameTime;
		this.grid = grid;
		this.mundo = mundo;
		
	}

}
