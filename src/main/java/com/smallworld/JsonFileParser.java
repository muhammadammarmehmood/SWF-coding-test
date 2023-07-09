package com.smallworld;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smallworld.data.Transaction;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonFileParser {
    public static List<Transaction> readTransactionJsonFile (String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Transaction> transactions = objectMapper.readValue(
                new File(filePath),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Transaction.class));
        return transactions;
    }
}
