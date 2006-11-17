/**
 * Clase que contiene el mapa y sus elementos
 * @author Revolution Software Developers
 */
public class GameMaps {
	
	/**
	 * Celda vacia del mapa
	 */
	public static final int BLANK = 0;
	
	/**
	 * Bloque no destructible
	 */
	public static final int BLOQUE = 1;
	
	/**
	 * Bomba
	 */
	public static final int BOMBA = 2;
	
	/**
	 * Powerup que indica que subira de posibles bombas a colocar
	 */
	public static final int MOREBOMBS = 3;
	
	/**
	 * Crate que contiene un powerup
	 */
	public static final int CRATE = 4;
	
	/**
	 * Fuego de la bomba al explotar
	 */
	public static final int FUEGO = 5;
	
	/**
	 * Bloque
	 */
	public static final int BLOQUE2 = 6;
	
	/**
	 * Mas alcance
	 */
	public static final int MOREPOWER = 7;
	
	/**
	 * Fuego de un crate... para saber que era un powerup ahi despues de que desaparezca el fuego
	 */
	public static final int FUEGOB = 8;
	
	/**
	 * Mapas del desierto
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
    	  {0,0,0,0,0,0,0,0,0,0,0}
    	  
    };
    
	/**
	 * Mapa de la cantina
	 */
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
	
	/**
	 * Mapa normal
	 */
    public static final int normal[][] = { 
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
       	  {0,0,0,0,0,0,0,0,0,0,0}};
}
