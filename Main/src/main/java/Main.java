import dobby.auth.TokenSolver;
import dobby.auth.exception.UnreachableApiException;
import dobby.core.communication.Router;
import dobby.core.communication.StandardRouter;
import dobby.websocket.MyMessageHandler;
import dobby.websocket.WebSocketServer;
import javax.websocket.DeploymentException;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Created by gautierc on 12/12/15.
 */
public class Main {
    private final static Logger LOGGER = Logger.getLogger(StandardRouter.class.getName());

    public static void main(String[] args) throws DeploymentException, IOException, InterruptedException {

        // TODO: Try replacing with aspect4log AspectJ
        Handler fh = new FileHandler("log");
        fh.setFormatter(new SimpleFormatter());
        //LOGGER.setUseParentHandlers(false);
        LOGGER.addHandler(fh);

        // Check Auth API connectivity
        try {
            LOGGER.info("Auth API tickling response: "+TokenSolver.tickle());
        } catch (UnreachableApiException e) {
            LOGGER.warning("Auth API is unreachable: "+e.getMessage());
        }

        Router router = new StandardRouter();
        RepositoryFactory repos = new RepositoryFactory(router);

        // We "inject" the repo to the message handler
        MyMessageHandler.userRepo = repos.getUserRepo();
        MyMessageHandler.router = router;

        WebSocketServer server = new WebSocketServer("localhost", 8085, "/dobby");
        server.start();

        System.out.println("Press any key to quit.");
        System.in.read();

        server.stop();
    }
}
