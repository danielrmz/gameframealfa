import java.io.File;
import java.io.IOException;
import javax.sound.midi.*;

/**
 * Clase manejadora de los sonidos
 * @author Revolution Software Developers
 */

public class SoundTest {
	private Sequencer sequencer = null;
	private boolean active = true;
	
	public SoundTest(File f){
		this.getSequencer(f);
		sequencer.start();
	}
	
	public SoundTest(File f, boolean loop){
		this.getSequencer(f);
		sequencer.start();
		if(loop){
			sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
		}
	}
	
	public void setActive(boolean active){
		this.active = active;
		if(this.active && sequencer.isRunning()){
			sequencer.stop();
		} else if(!sequencer.isRunning()){
			sequencer.start();
		}
	}
	
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
}
