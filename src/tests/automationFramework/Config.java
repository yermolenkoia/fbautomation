package tests.automationFramework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private String email;
    private String password;
    private String base_url;
    private String username;

    private static final String EMAIL_FIELD = "email";
    private static final String PASSWORD_FIELD = "password";
    private static final String DOMAIN_FIELD = "domain";
    private static final String USERNAME_FIELD = "username";
    private static final String PROPERTIES_FILENAME = "datafile.properties";


    public Config() {
        try {
            FileInputStream file = new FileInputStream(PROPERTIES_FILENAME);
            Properties properties = new Properties();
            properties.load(file);
            base_url = "https://" + properties.getProperty(DOMAIN_FIELD);
            email = properties.getProperty(EMAIL_FIELD);
            password = properties.getProperty(PASSWORD_FIELD);
            username = properties.getProperty(USERNAME_FIELD);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getBase_url() {
        return base_url;
    }

    public String getUsername() {
        return username;
    }

}