 

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.URL;

/**
 * Clase manejadora de sonidos en una aplicación
 * @author Revolution Software Developers
 */
public class Sound implements Runnable {
	/**
	 * Clip de la cancion en memoria
	 */
	private AudioClip song;
	
	/**
	 * URL Fisico del clip de audio
	 */
	private URL songPath;
	
	/**
	 * Constructor del Audio Clip,
	 * @param filename
	 */
	public Sound(String filename) {
		//String ruta = RUTA+filename;
		
		try {
			songPath = new URL("file:"+filename); //new URL("file:" + ruta); 
			song = Applet.newAudioClip(songPath); 
		} catch(Exception e){
			System.out.println("Sonido: " + filename + " no encontrado. El sonido no se reproducira");
		} 
	}
	
	/**
	 * Constructor con la opcion de reproducir en ese instante
	 * @param filename 
	 */
	public Sound(String filename, boolean reproduce){
		String ruta = RUTA+filename;
		try {
			songPath = new URL("file:" + ruta); 
			song = Applet.newAudioClip(songPath); 
		} catch(Exception e){
			System.out.println("Sonido: " + ruta + " no encontrado. El sonido no se reproducira");
		} 
		
		if(reproduce){
			this.playSoundOnce();
		}
	}
	
	/**
	 * Reproduce el sonido infinitamente
	 */
	public void playSound() {
		song.loop(); 
	}
	
	
	/**
	 * Detiene el audio
	 */
	public void stopSound() {
		song.stop(); 
	}
	
	/**
	 * Reproduce el sonido finitamente 1 vez
	 */
	public void playSoundOnce() {
		song.play(); 
	}
	
	/**
	 * Reproduce el sonido cierto numero de veces
	 */
	public void playSoundBy(int times){
		for(int i = 0; i < times; i++){
			song.play();
		}
	}
	
	public static final String RUTA = (new File ("")).getAbsolutePath()+"\\sounds\\";

	public void run() {
		for(int i = 0; i<3; i++){
			song.play();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}