import java.awt.*;
import java.io.File;
import javax.swing.*;

/** 
 * Clase Main.java 
 *
 * @author Revolution Software Developers
 * 
 **/
public class Main {
	/**
	 * Constante de la ruta del juego
	 */
	public static final String RUTA = (new File ("")).getAbsolutePath()+"\\";
	
	/**
	 * Version actual
	 */
	public static final String VERSION = "0.1";
	
	/**
	 * Frame principal
	 */
	public static InitFrame principal = new InitFrame();
	
	/**
	 * Metodo principal de la aplicacion
	 * @param args
	 */
	public static void main(String[] args) {
		loadHighscores();
		principal.setVisible(true);
	}
	
	public static void loadHighscores(){
		HighscoresFrame.HighscoreTable table = (HighscoresFrame.HighscoreTable)((new Serial("highscores.drk").getObject()));
		if(table!=null){
			HighscoresFrame.highscores = table.table;
		}
	}

	/**
	 * Regresa una imagen del directorio img/ dando nadamas su nombre
	 * @param filename
	 * @return image
	 */
	public static Image getImage(String filename){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage(Main.RUTA+"img/"+filename);
		return image;
	}
	
	/**
	 * Regresa el ImageIcon de una imagen especificada
	 * @param filename
	 * @return image
	 */
	public static ImageIcon getImageIcon(String filename){	
		ImageIcon image = new ImageIcon(Main.RUTA+"img\\"+filename);
		
		if(image.getImageLoadStatus()==4) return null;
		return image;
	}
	
	/**
	 * Establece los valores default para los frames del juego
	 * se usa para evitar discrepancias entre tamanos, fondos etc..
	 * @param frame
	 */
	public static void setDefaults(JFrame frame){
		JLayeredPane principal = frame.getLayeredPane();
		ImageIcon bg = Main.getImageIcon("cantina_main.png");
		JLabel bgLabel = new JLabel("");
		bgLabel.setIcon(bg);
		bgLabel.setSize(new Dimension(752,600));
		principal.add(bgLabel,JLayeredPane.DEFAULT_LAYER);
		
		frame.setSize(new Dimension(752,625));
		frame.setTitle("Macrosoft Appol Drunkenman ™");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocation(250,100);
		
	}

}
