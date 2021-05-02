import com.sforce.soap.partner.Connector;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SFConnect {
    public static void main(String[] args) throws ConnectionException, IOException {

        Properties properties = getProperties();

        String login_url = properties.getProperty("SF_BASE_URL");
        String username = properties.getProperty("USERNAME");
        String pass = properties.getProperty("PASSWORD");
        ConnectorConfig config = new ConnectorConfig();
        config.setUsername(username);
        config.setPassword(pass);
        config.setAuthEndpoint(login_url+"/services/Soap/u/49.0");
        PartnerConnection connection = Connector.newConnection(config);
        System.out.println(connection.getUserInfo().toString());
    }

    public static Properties getProperties() throws IOException {
        Properties properties = new Properties();
        try (InputStream is = SFConnect.class.getResourceAsStream("application.properties")) {
            properties.load(is);
        }
        return properties;
    }
}
