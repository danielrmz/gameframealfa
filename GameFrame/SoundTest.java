import java.io.File;
import java.io.IOException;
import javax.sound.midi.*;


public class SoundTest{
	
	public SoundTest(File f){
		
		Sequencer sequencer = null;

		Sequence sequence = null;

		
		try {
			sequencer = MidiSystem.getSequencer();

		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			sequencer.open();
;
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			sequence = MidiSystem.getSequence(f);

		} catch (InvalidMidiDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			sequencer.setSequence(sequence);

		} catch (InvalidMidiDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sequencer.start();
	}

}
