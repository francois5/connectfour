package ctrl;

/**
 *
 * @author seb
 */
public class SoundFactory {
    private static Sound sound = null;
    
    public static Sound getSound() {
        if(sound == null) {
            sound = new SoundImpl();
        }
        return sound;
    }
}
