package com.smallworld;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smallworld.data.Transaction;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonFileParser {

    /**
     * Reads the json file and parses it into list.
     * @param filePath - of the json file
     * @return list of transactions parsed from json File.
     * @throws IOException - when there is error reading json file.
     */
    public static List<Transaction> readTransactionJsonFile (String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Transaction> transactions = objectMapper.readValue(
                new File(filePath),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Transaction.class));
        return transactions;
    }
}
