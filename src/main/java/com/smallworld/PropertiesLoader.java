package com.smallworld;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.IOException;

public class PropertiesLoader {

    private PropertiesConfiguration propertiesConfiguration;
    public PropertiesLoader() throws ConfigurationException {
        propertiesConfiguration = new PropertiesConfiguration("application.properties");
        propertiesConfiguration.load();
    }

    public String getJsonFilePath() throws IOException {
        String jsonFilePath = propertiesConfiguration.getString("jsonFilePath");
        if(jsonFilePath == null) {
            throw new IOException("json File Path not defined in application.properties");
        }
        return jsonFilePath;
    }

}
