import java.io.File;
import java.io.IOException;
import javax.sound.midi.*;

/**
 * Clase manejadora de los sonidos
 * @author Revolution Software Developers
 */

public class SoundTest {

	/**
	 * Secuenciador de la cancion actual
	 */
	public Sequencer sequencer = null;

	
	/**
	 * Activo
	 */
	private boolean active = true;
	
	/**
	 * Trae un archivo y lo toca una vez
	 * @param f
	 */
	public SoundTest(File f){
		this.getSequencer(f);
		sequencer.start();
	}
	
	/**
	 * Trae el sonido y la repite indefinidamente dependiendo del segundo parametro
	 * @param f
	 * @param loop
	 */
	public SoundTest(File f, boolean loop){
		this.getSequencer(f);
		sequencer.start();
		if(loop){
			sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
		}
	}
	
	/**
	 * Activa la secuencia de nuevo
	 * @param active
	 */
	public void setActive(boolean active){
		this.active = active;
		if(this.active && sequencer.isRunning()){
			sequencer.stop();
		} else if(!sequencer.isRunning()){
			sequencer.start();
		}
	}
	
	/**
	 * Trae el secuenciador
	 */
	private void getSequencer(File f){
		Sequence sequence = null;
		try {
			sequencer = MidiSystem.getSequencer();
			sequencer.open();
			sequence = MidiSystem.getSequence(f);
			sequencer.setSequence(sequence);

		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void stop(){
		this.sequencer.stop();
	}
}
