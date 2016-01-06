package model.sounds;


import java.util.logging.Logger;
import java.util.logging.Level;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

/**
 *
 * @author seb
 */
public class Instru {
    // 2 étapes Pour synthétiser des sons MIDI:
    // Etape 1 Créer un objet de type Synthesizer et l'initialiser
    // Etape 2 Récupérer un objet de type MidiChannel à partir de l'objet Synthesizer
    // MidiChannel est un canal pour jouer des notes MIDI
    public int volume = 100;
    
    private Synthesizer synthetiseur;
    private MidiChannel canal;
    
    public Instru() {
        try {
            //synthetiseur = MidiSystem.getSoundbank("soundbank.gm").getSynthesizer();
            synthetiseur = MidiSystem.getSynthesizer();
            synthetiseur.open();
        } catch(MidiUnavailableException ex) {
            Logger.getLogger(Instru.class.getName()).log(Level.SEVERE, null, ex);
        }
        canal = synthetiseur.getChannels()[0];
        
        // On initialise l'instrument 0 (le piano) pour le canal
        canal.programChange(0);
    }
    
    // Joue la note dont le numéro est en paramètre
    public void note_on(int note) {
        canal.noteOn(note, volume);
    }
    // Arrête de jouer la note dont le numéro est en paramètre
    public void note_off(int note) {
        canal.noteOff(note);
    }
    // Change le type d'instrument
    public void set_instrument(int instru) {
        canal.programChange(instru);
    }
}
