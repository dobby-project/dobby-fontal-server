package dobby.core.communication;

/**
 * Created by gautierc on 10/12/15.
 */
public final class Channel {

    public static final Channel NONE = new Channel("none");
    public static final Channel TRACKER = new Channel("tracker");

    private String name;

    public Channel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
