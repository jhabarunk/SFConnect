import com.sforce.soap.partner.Connector;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.soap.partner.fault.LoginFault;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;

import java.io.IOException;
import java.util.*;


public class SFConnect {
    private static final String AUTH_ENDPOINT = "%s/services/Soap/u/49.0";
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.load(SFConnect.class.getResourceAsStream("/application.properties"));
        try {
            String login_url = properties.getProperty("SF_BASE_URL");
            String username = properties.getProperty("USERNAME");
            String pass = properties.getProperty("PASSWORD");
            ConnectorConfig config = new ConnectorConfig();
            config.setUsername(username);
            config.setPassword(pass);
            config.setAuthEndpoint(String.format(AUTH_ENDPOINT, login_url));
            PartnerConnection connection = Connector.newConnection(config);
            System.out.println(connection.getConfig().getSessionId());
        }catch (ConnectionException c) {
            System.out.println("Message: " + c.getMessage());
            System.out.println("LocalizedMessage: " + c.getLocalizedMessage());
            System.out.println("LoginFault: " + ((LoginFault) c).getExceptionMessage());
        }

    }
}
 
