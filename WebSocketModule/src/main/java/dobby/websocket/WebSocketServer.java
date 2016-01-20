package dobby.websocket;

import javax.websocket.DeploymentException;
import org.glassfish.tyrus.server.Server;

/**
 * @author Pavel Bucek (pavel.bucek at oracle.com)
 */
public class WebSocketServer {

    private Server server;

    public WebSocketServer(String host, int port, String path) {
        server = new Server(host, port, path, MyEndPoint.class);
    }

    public void start() throws DeploymentException {
        server.start();
    }

    public void stop() {
        server.stop();
    }
}