package dobby.core;

import java.util.Optional;

/**
 * Created by gautierc on 19/01/16.
 */
public interface Repository {
    Optional getItemByName(String name);
}
