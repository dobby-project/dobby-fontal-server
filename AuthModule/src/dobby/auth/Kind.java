package dobby.auth;

/**
 * Created by gautierc on 19/12/15.
 */
public enum Kind {

    ADMIN(0),
    USER(1),
    GUEST(2);

    private final int value;

    Kind(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    /**
     * Compare the current Kind level with the required one
     * @param requiredKind
     * @return
     */
    public boolean isSufficientTo(Kind requiredKind) {
        return this.getValue() <= requiredKind.getValue();
    }
}
