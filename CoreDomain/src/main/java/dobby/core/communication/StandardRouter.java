package dobby.core.communication;

import dobby.core.stakeholder.Stakeholder;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by gautierc on 19/01/16.
 */
public class StandardRouter implements Router {
    private final static Logger LOGGER = Logger.getLogger(StandardRouter.class.getName());

    @Override
    public void accept(Message msg) {

        // Log
        LOGGER.log(Level.FINE,
                "Received msg from {0}, to {1}, raw content: {2}",
                new Object[]{msg.getOrigin().getName(), msg.getDestination().getName(), msg.getRawContent()}
        );

        System.out.println(msg.getFields().toString());

        // What if the user is in pending ??

        // Check authorisation from origin to speak to dest
        // TODO: RightChecker.isAllowedToCommunicate(origin, dest);

        // Ask "Dest" Stakeholder
    }

    @Override
    public void notify(Stakeholder stakeholder) {

    }

    @Override
    public void logError(Exception e) {
        LOGGER.warning(e.getMessage());
    }
}
