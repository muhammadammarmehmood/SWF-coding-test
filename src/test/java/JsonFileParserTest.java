import com.smallworld.JsonFileParser;
import com.smallworld.data.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class JsonFileParserTest {
    @Test
    public void testReadTransactionJsonFile_ValidFilePath() throws IOException {
        String filePath = "transactions.json";
        List<Transaction> transactions = JsonFileParser.readTransactionJsonFile(filePath);
        Assertions.assertNotNull(transactions);
        Assertions.assertFalse(transactions.isEmpty());
    }

    @Test
    public void testReadTransactionJsonFile_InvalidFilePath() {
        String filePath = "file_does_not_exist.json";
        Assertions.assertThrows(IOException.class, () -> {
            JsonFileParser.readTransactionJsonFile(filePath);
        });
    }

    @Test
    public void testReadTransactionJsonFile_EmptyFile() throws IOException {
        String filePath = "src/test/resources/empty_transactions.json";
        List<Transaction> transactions = JsonFileParser.readTransactionJsonFile(filePath);
        Assertions.assertNotNull(transactions);
        Assertions.assertTrue(transactions.isEmpty());
    }

    @Test
    public void testReadTransactionJsonFile_InvalidJsonFormat() {
        String filePath = "src/test/resources/invalid_transactions.json";
        Assertions.assertThrows(IOException.class, () -> {
            JsonFileParser.readTransactionJsonFile(filePath);
        });
    }
}
