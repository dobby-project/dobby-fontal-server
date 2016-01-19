package dobby.core.game;

/**
 * Created by gautierc on 10/12/15.
 */
public class BasicConfiguration implements Configuration {

    public static final Configuration NONE = new BasicConfiguration("none");

    private String name;

    public BasicConfiguration(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
