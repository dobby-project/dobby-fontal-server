package dobby.auth;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import dobby.auth.token.TokenFactory;
import javax.ws.rs.core.UriBuilder;

import dobby.auth.token.Token;

import java.io.IOException;
import java.util.*;

/**
 * Created by gautierc on 19/12/15.
 */
public class TokenSolver {

    public static Optional<Token> retrieveData(String tokenString) {

        UriBuilder builder = UriBuilder
                .fromPath("//localhost:3689")
                .scheme("http")
                .path("/token/{token}/");
        String url = builder.build(tokenString).toString();

        Client client = Client.create();
        WebResource webResource = client.resource(url);
        ClientResponse response = webResource.get(ClientResponse.class);

        if (response.getStatus() == 200)
        {

            String json = response.getEntity(String.class);

            JsonFactory factory = new JsonFactory();
            //factory.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
            ObjectMapper om = new ObjectMapper(factory);
            MapType type = om.getTypeFactory().constructMapType(HashMap.class, String.class, String.class);

            JsonParser jp = null;
            try {
                jp = factory.createParser(json);
                return TokenFactory.fromMap(om.readValue(jp, type));
            } catch (IOException e) {
                e.printStackTrace();
                return Optional.empty();
            }
        }
        else
        {
            System.out.println("Reponse at "+url.toString()+" give HTTP status "+response.getStatus()+" (expected 200)");
            return Optional.empty();
        }
    }
}
