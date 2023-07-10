package com.smallworld;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.IOException;

public class PropertiesLoader {

    private final PropertiesConfiguration propertiesConfiguration;

    /**
     * Constructs a new {@code PropertiesLoader} instance.
     * It loads the "application.properties" file and initializes a {@link PropertiesConfiguration} object.
     *
     * @throws ConfigurationException If there is an error in loading the properties file.
     */
    public PropertiesLoader() throws ConfigurationException {
        propertiesConfiguration = new PropertiesConfiguration("application.properties");
        propertiesConfiguration.load();
    }

    /**
     * Retrieves the JSON file path from the properties file.
     *
     * The method reads the "jsonFilePath" property from the properties file and returns the corresponding value.
     * If the "jsonFilePath" property is not defined in the properties file, an {@link IOException} is thrown.
     *
     * @return The JSON file path specified in the properties file.
     * @throws IOException If the "jsonFilePath" property is not defined in the properties file.
     */
    public String getJsonFilePath() throws IOException {
        String jsonFilePath = propertiesConfiguration.getString("jsonFilePath");
        if(jsonFilePath == null) {
            throw new IOException("json File Path not defined in application.properties");
        }
        return jsonFilePath;
    }

}
