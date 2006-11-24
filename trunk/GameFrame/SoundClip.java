import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Clase que reproduce el sonido
 * @author Revolution Software Developers
 */
public class SoundClip {
	
	/**
	 * Constructor del soundclip de sonido
	 * @param file
	 */
	public SoundClip(String file){
		
		AudioInputStream ais = null;
		Clip clip = null;
		
		try {
			ais = AudioSystem.getAudioInputStream(getClass( ).getResource(file) );
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		AudioFormat format = ais.getFormat( );
		
		DataLine.Info info = new DataLine.Info(Clip.class, format);
		
		try {
			clip = (Clip) AudioSystem.getLine(info);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		
		try {
			clip.open(ais);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        try {
			ais.close( );
		} catch (IOException e) {
			e.printStackTrace();
		} 
		clip.start();
	}

}
