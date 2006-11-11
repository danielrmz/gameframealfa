
public class GameMaps {
	/**
	 * Constantes de objetos en el mapa
	 */
	public static final int BLANK = 0;
	public static final int CRATE = 4;
	public static final int BLOQUE = 1;
	public static final int BLOQUE2 = 6;
	public static final int BOMBA = 2;
	public static final int POWERUP = 3;
	public static final int FUEGO = 5;
	
	/**
	 * Mapas defaults definidos
	 */
	public static final int desierto[][] = { 
    	  {0,0,0,0,0,0,0,0,0,0,0},
    	  {0,BLOQUE,0,BLOQUE,0,BLOQUE,0,BLOQUE,0,BLOQUE,0},
    	  {0,0,0,0,0,0,0,0,0,0,0},
    	  {0,BLOQUE,0,BLOQUE,0,BLOQUE,0,BLOQUE,0,BLOQUE,0},
    	  {0,0,0,0,0,0,0,0,0,0,0},
    	  {0,BLOQUE,0,BLOQUE,0,BLOQUE,0,BLOQUE,0,BLOQUE,0},
    	  {0,0,0,0,0,0,0,0,0,0,0},
    	  {0,BLOQUE,0,BLOQUE,0,BLOQUE,0,BLOQUE,0,BLOQUE,0},
    	  {0,0,0,0,0,0,0,0,0,0,0},
    	  {0,BLOQUE,0,BLOQUE,0,BLOQUE,0,BLOQUE,0,BLOQUE,0},
    	  {0,0,0,0,0,0,0,0,0,0,0},
    	  
    };
    
	public static final int cantina[][] = {
		 {0,0,0,0,0,0,0,0,0,0,0},
   	  {0,BLOQUE,0,BLOQUE,0,BLOQUE,0,BLOQUE,0,BLOQUE,0},
   	  {0,0,0,0,0,0,0,0,0,0,0},
   	  {0,BLOQUE,0,BLOQUE,0,BLOQUE,0,BLOQUE,0,BLOQUE,0},
   	  {0,0,0,0,0,0,0,0,0,0,0},
   	  {0,BLOQUE,0,BLOQUE,0,BLOQUE,0,BLOQUE,0,BLOQUE,0},
   	  {0,0,0,0,0,0,0,0,0,0,0},
   	  {0,BLOQUE,0,BLOQUE,0,BLOQUE,0,BLOQUE,0,BLOQUE,0},
   	  {0,0,0,0,0,0,0,0,0,0,0},
   	  {0,BLOQUE,0,BLOQUE,0,BLOQUE,0,BLOQUE,0,BLOQUE,0},
   	  {0,0,0,0,0,0,0,0,0,0,0}
   	 };
    public static final int normal[][] = {{1,1}};
}