package dobby.core.communication;

import dobby.core.Repository;
import dobby.core.Stakeholder;

/**
 * Created by gautierc on 19/01/16.
 */
public class StandardRouter implements Router {
    @Override
    public void transfer(Message msg) {

        Stakeholder origin = msg.getOrigin();

        // Check destination connectivity
        // How to "ask" the right repo (app or internal / user)
        Repository repo = origin.getOpposedRepository();
        Stakeholder destination = repo.getItemByName(msg.getDestinationName());

        // What if the user is in pending ??

        // Check authorisation from origin to speak to dest
        // TODO: RightChecker.isAllowedToCommunicate(origin, );

        // Ask "Dest" Stakeholder
    }

    @Override
    public void notify(Stakeholder stakeholder) {

    }
}
