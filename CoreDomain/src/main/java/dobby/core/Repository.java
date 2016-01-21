package dobby.core;

import java.util.Optional;

/**
 * Created by gautierc on 19/01/16.
 */
public interface Repository<Key, Kind> {
    Optional<Kind> getItemByKey(Key key);
    Optional<Kind> getItemByName(String name);
    void accept(Kind kind);
    void close(Kind kind);
}
