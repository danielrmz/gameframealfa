import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.LinkedList;
import javax.swing.JPanel;

/**
 * Panel que maneja el juego
 * @author Revolution Software Developers
 */
public class PanelJuego extends JPanel implements Runnable, KeyListener {
	/**
	 * Constante de eclipse
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Alto de la pantalla
	 */
	public static int ALTO;
	
	/**
	 * Ancho de la pantalla
	 */
	public static int ANCHO;
	
	/**
	 * Bombas actuales
	 */
	public static LinkedList<Bomb> bombs = new LinkedList<Bomb>();
	
	/**
	 * Jugadores actuales
	 */
	public Player[] players; 
	
	/**
	 * Indica si el juego esta corriendo
	 */
	public volatile static boolean running;
	
	/**
	 * Thread del tiempo de refresh
	 */
	public Thread t;
	
	/**
	 * # de Imagen siguiente a pintar
	 */
	public int imagenSiguiente;
	
	/**
	 * Imagen a pintar
	 */
	private Image panelSecundario;
	
	/**
	 * Grafico actual
	 */
	public static Graphics2D gImagen;
	
	/**
	 * Mundo actual, indica tambien el directorio
	 */
	private String mundo = "";
	
	/**
	 * Grid del mundo
	 */
	public static int[][] grid;
	
	/**
	 * Ruta de la aplicacion
	 */
	public static final String RUTA = (new File ("")).getAbsolutePath()+"\\";
	
	/**
	 * Variable que pausa el panel
	 */
	public static boolean paused = false;
	
	/**
	 * Constructor
	 * @param mapa, mapa escogido
	 */
	public PanelJuego(int[][] mapa,String strmapa) {
		//-- Establece el alto y ancho, posteriormente se le asignara este tama�o a las imagenes creadas
		ANCHO = mapa[0].length * 50;
		ALTO  = mapa.length * 50;
		imagenSiguiente = 0;
		players = new Player[4];
		
		//-- Establece un tama�o al panel
		setSize(new Dimension(ANCHO,ALTO));
		setFocusable(true);
		requestFocus();
      
		//-- Le asigna el mapa escogido al panel. 
		PanelJuego.grid = mapa;
		this.mundo = strmapa;
		//-- Establece las crates rompibles
		this.defineCrates();
		this.addKeyListener(this);
		  
		//-- Establece a los dos jugadores
		this.setPlayer(new Player(0,-10,1));
		this.setPlayer(new Player(100,100,2));
		t = new Thread(this);
	    
	}
	
	/**
	 * Establece un jugador en el canvas
	 * @param p
	 */
	public void setPlayer(Player p){
		if(Player.numPlayers<players.length){
			players[Player.numPlayers-1] = p;
			this.addKeyListener(p);
		}
	}
	
	/**
	 * Despliega el tablero en consola
	 */
	public static void despliegaTablero(){
		int[][] grid = PanelJuego.grid;
		System.out.println("==============================");
		for(int i=0; i<grid[0].length; i++){
			System.out.print("|");
			for(int j=0; j<grid.length; j++){
				System.out.print(grid[j][i]+"|");
			}
			System.out.println();
		}
	}
	
	/**
	 * Despliega el tablero en consola
	 */
	public static void despliegaTablero(int[][] grid){
		System.out.println("==============================");
		for(int i=0; i<grid.length; i++){
			System.out.print("|");
			for(int j=0; j<grid[i].length; j++){
				System.out.print(grid[i][j]+"|");
			}
			System.out.println();
		}
	}
	/**
	 * Trae la imagen 
	 * @param filename
	 * @return image
	 */
	public static Image getImage(String filename){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage(RUTA+"img/"+filename);
		return image;
	}
	
	
	/**
	 * Define los crates en la pantalla aleatoriamente
	 */
	public void defineCrates(){
		int[][] grid = PanelJuego.grid;
		int max = grid.length/2 * grid[0].length/2;
		while(max>0){
			int i =(int)(Math.random()*grid.length);
			int j =(int)(Math.random()*grid[0].length);
			
			if(grid[i][j] != GameMaps.BLOQUE && grid[i][j] != GameMaps.CRATE){
					grid[i][j] = GameMaps.CRATE;
					max--;
			}
		}
	}
	
	/**
	 * Dibuja todos los bloques, crates, e items en pantalla
	 */
	public void drawBlocks(){
		int[][] grid = PanelJuego.grid;
		Image block = getImage("mundos/"+this.mundo+"/block.png");
		Image block2 = getImage("mundos/"+this.mundo+"/block2.png");
		
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[i].length; j++){
				if(grid[i][j] == GameMaps.BLOQUE || grid[i][j] == GameMaps.BLOQUE2){
					Image b = (GameMaps.BLOQUE == grid[i][j] || block2.getWidth(this) < 0 )?block:block2;
					gImagen.drawImage(b,i*50,j*50,null,null);
				} else if(grid[i][j] == GameMaps.MOREBOMBS) {
					Image img = getImage("mis/pbomba.png");
					gImagen.drawImage(img,i*50,j*50,null,null);
				} else if(grid[i][j] == GameMaps.MOREPOWER) {
					Image img = getImage("mis/ppoder.png");
					gImagen.drawImage(img,i*50,j*50,null,null);
				} else if(grid[i][j] == GameMaps.CRATE ){
					Image crate = getImage("mundos/"+this.mundo+"/crate.png");
					gImagen.drawImage(crate,i*50,j*50,null,null);
				}
			}
		}
	}
	
	/**
	 * Dibuja un grid en caso de necesitarlo
	 */
	public void drawGrid(){
		gImagen.setColor(Color.BLACK);
		for(int i=50; i<=ANCHO;i+=50){
			gImagen.drawLine(i,0,i,ALTO);
		}
		
		for(int i=50; i<=ALTO;i+=50){
			gImagen.drawLine(0,i,ANCHO,i);
		}
	}
	
	/**
	 * Game Renderer, se encarga de crear la imagen, el fondo y dibujar todo lo principal
	 */
	public void gameRender(){
		if(panelSecundario==null){
			panelSecundario = createImage(ANCHO+1,ALTO+1);
		}
		gImagen = (Graphics2D)panelSecundario.getGraphics();
		gImagen.drawImage(getImage("mundos/"+this.mundo+"/bg.png"),0,0,Color.BLACK,null);
		
		this.drawBlocks();
		this.drawGrid();
		
		Bomb.drawBombs(gImagen,this);
		for(int i=0; i<players.length; i++){
			if(players[i]!=null){
				players[i].draw(gImagen);
			}
		}
	}
	
	/**
	 * Trae el componente grafico del panel
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if (panelSecundario!=null){
			g.drawImage(panelSecundario,0,0,null);
		}
	}

	/**
	 * Key pressed del panel
	 */
	public void keyPressed(KeyEvent ke) {
	}

	/**
	 * Key released del panel
	 */
	public void keyReleased(KeyEvent ke) {
	}

	/**
	 * Key typed del panel
	 */
	public void keyTyped(KeyEvent arg0) {
	}

	/**
	 * Run que verifica el estado del juego
	 */
	public void run() {
		//new SoundTest(new File("sound/llevamecontigo_cumbia.mid"));
		
		running = true;
		while(running){
			if(!paused){	
			   gameRender();
			   repaint();
			   try{
				   Thread.sleep(20);
			   }catch(Exception e){}
			}
		}

		System.out.println("Termino");

	}

	/**
	 * Termina el juego
	 */
	public static void endGame(){
		ALTO = 0;
		ANCHO = 0;
		bombs = new LinkedList<Bomb>();
		running = false;
		gImagen = null;
		grid = null;
		paused = false;
		Player.numPlayers = 0;
	}
}
