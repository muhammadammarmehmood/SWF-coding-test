package com.smallworld;

import com.smallworld.data.Transaction;
import org.apache.commons.configuration.ConfigurationException;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String args[]) throws IOException, ConfigurationException {
        PropertiesLoader propertiesLoader = new PropertiesLoader();
        String jsonFilePath = propertiesLoader.getJsonFilePath();
        List<Transaction> transactions = JsonFileParser.readTransactionJsonFile(jsonFilePath);
        System.out.println("");
    }
}
