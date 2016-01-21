package dobby.websocket;

import javax.websocket.DeploymentException;

import dobby.core.communication.StandardRouter;
import org.glassfish.tyrus.server.Server;

import java.util.logging.Logger;

/**
 * @author Pavel Bucek (pavel.bucek at oracle.com)
 */
public class WebSocketServer {

    private final static Logger LOGGER = Logger.getLogger(StandardRouter.class.getName());

    private Server server;
    private String host;
    private int port;
    private String path;

    public WebSocketServer(String host, int port, String path) {
        server = new Server(host, port, path, null, MyEndPoint.class);
        this.host = host;
        this.port = port;
        this.path = path;
    }

    public void start() throws DeploymentException {
        server.start();
        LOGGER.info("WebSocketServer started, listening at: ws://"+host+":"+port+path);
    }

    public void stop() {
        server.stop();
        LOGGER.info("WebSocketServer stopped.");
    }
}