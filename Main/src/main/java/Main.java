import dobby.websocket.WebSocketServer;
import javax.websocket.DeploymentException;
import java.io.IOException;

/**
 * Created by gautierc on 12/12/15.
 */
public class Main {
    public static void main(String[] args) throws DeploymentException, IOException, InterruptedException {

        WebSocketServer server = new WebSocketServer("localhost", 8085, "/dobby");
        server.start();

        System.out.println("Press any key to quit.");
        System.in.read();

        server.stop();
    }
}
