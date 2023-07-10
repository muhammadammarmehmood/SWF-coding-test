import com.smallworld.JsonFileParser;
import com.smallworld.PropertiesLoader;
import com.smallworld.TransactionDataFetcher;
import com.smallworld.data.Transaction;
import org.apache.commons.configuration.ConfigurationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

public class TransactionDataFetcherTest {

    /**
     * transaction data for testing purpose.
     */
    public static List<Transaction> transactionList;

    /**
     * Sets up the test environment before executing any test cases.
     * This method is annotated with {@code @BeforeAll} to indicate that it should be run once before all test cases.
     * It performs the following steps:
     * - Loads the JSON file path from the properties file using a {@link PropertiesLoader} instance.
     * - Reads the transaction data from the JSON file using the {@link JsonFileParser} class and stores it in the {@code transactionList} variable.
     * - Prints an empty line to provide separation in the console.
     *
     * @throws ConfigurationException If there is an error in loading the properties file.
     * @throws IOException            If there is an error in reading the JSON file.
     */
    @BeforeAll
    public static void setUp () throws ConfigurationException, IOException {
        PropertiesLoader propertiesLoader = new PropertiesLoader();
        String jsonFilePath = propertiesLoader.getJsonFilePath();
        transactionList = JsonFileParser.readTransactionJsonFile(jsonFilePath);
    }

    /**
     * Tests the {@link TransactionDataFetcher#getTotalTransactionAmount()} method.
     */
    @Test
    public void testGetTotalTransactionAmount() {
        TransactionDataFetcher dataFetcher = new TransactionDataFetcher(transactionList);

        double totalAmount = dataFetcher.getTotalTransactionAmount();
        Assertions.assertEquals(4371.37, totalAmount);
    }

    /**
     * Tests the {@link TransactionDataFetcher#getTotalTransactionAmountSentBy} method.
     */
    @Test
    public void testGetTotalTransactionAmountSentBy() {
        TransactionDataFetcher dataFetcher = new TransactionDataFetcher(transactionList);

        double totalAmountSentByTomShelby = dataFetcher.getTotalTransactionAmountSentBy("Tom Shelby");
        Assertions.assertEquals(828.26, totalAmountSentByTomShelby);

        double totalAmountSentByAuntPolly = dataFetcher.getTotalTransactionAmountSentBy("Aunt Polly");
        Assertions.assertEquals(101.02, totalAmountSentByAuntPolly);
    }

    /**
     * Tests the {@link TransactionDataFetcher#getMaxTransactionAmount()} method.
     */
    @Test
    public void testGetMaxTransactionAmount() {
        TransactionDataFetcher dataFetcher = new TransactionDataFetcher(transactionList);

        double maxAmount = dataFetcher.getMaxTransactionAmount();
        Assertions.assertEquals(985.0, maxAmount);
    }

    /**
     * Tests the {@link TransactionDataFetcher#countUniqueClients()} method.
     */
    @Test
    public void testCountUniqueClients() {
        TransactionDataFetcher dataFetcher = new TransactionDataFetcher(transactionList);

        long uniqueClientCount = dataFetcher.countUniqueClients();
        Assertions.assertEquals(14, uniqueClientCount);
    }

    /**
     * Tests the {@link TransactionDataFetcher#hasOpenComplianceIssues} method.
     */
    @Test
    public void testHasOpenComplianceIssues() {
        TransactionDataFetcher dataFetcher = new TransactionDataFetcher(transactionList);

        boolean hasOpenIssues = dataFetcher.hasOpenComplianceIssues("Tom Shelby");
        Assertions.assertTrue(hasOpenIssues);

        hasOpenIssues = dataFetcher.hasOpenComplianceIssues("Aunt Polly");
        Assertions.assertFalse(hasOpenIssues);
    }

    /**
     * Tests the {@link TransactionDataFetcher#getTransactionsByBeneficiaryName()} method.
     */
    @Test
    public void testGetTransactionsByBeneficiaryName() {
        TransactionDataFetcher dataFetcher = new TransactionDataFetcher(transactionList);

        Map<String, List<Transaction>> transactionsByBeneficiaryName = dataFetcher.getTransactionsByBeneficiaryName();
        Assertions.assertEquals(10, transactionsByBeneficiaryName.size());

        List<Transaction> alfieSolomonsTransactions = transactionsByBeneficiaryName.get("Alfie Solomons");
        Assertions.assertNotNull(alfieSolomonsTransactions);
        Assertions.assertEquals(1, alfieSolomonsTransactions.size());

        List<Transaction> arthurShelbyTransactions = transactionsByBeneficiaryName.get("Arthur Shelby");
        Assertions.assertNotNull(arthurShelbyTransactions);
        Assertions.assertEquals(2, arthurShelbyTransactions.size());
    }

    /**
     * Tests the {@link TransactionDataFetcher#getUnsolvedIssueIds()} method.
     */
    @Test
    public void testGetUnsolvedIssueIds() {
        TransactionDataFetcher dataFetcher = new TransactionDataFetcher(transactionList);

        Set<Integer> unsolvedIssueIds = dataFetcher.getUnsolvedIssueIds();
        Set<Integer> expectedIssueIds = Set.of(1, 3, 15, 54, 99);
        Assertions.assertEquals(expectedIssueIds, unsolvedIssueIds);
    }

    /**
     * Tests the {@link TransactionDataFetcher#getAllSolvedIssueMessages()} method.
     */
    @Test
    public void testGetAllSolvedIssueMessages() {
        TransactionDataFetcher dataFetcher = new TransactionDataFetcher(transactionList);

        List<String> solvedIssueMessages = dataFetcher.getAllSolvedIssueMessages();
        List<String> expectedIssueMessages = List.of("Never gonna give you up", "Never gonna let you down", "Never gonna run around and desert you");
        Assertions.assertEquals(expectedIssueMessages, solvedIssueMessages);
    }

    /**
     * Tests the {@link TransactionDataFetcher#getTop3TransactionsByAmount()} method.
     */
    @Test
    public void testGetTop3TransactionsByAmount() {
        TransactionDataFetcher dataFetcher = new TransactionDataFetcher(transactionList);

        List<Transaction> top3Transactions = dataFetcher.getTop3TransactionsByAmount();
        Assertions.assertEquals(3, top3Transactions.size());

        // Assert the transactions in the correct order (descending order of amount)
        Assertions.assertEquals(985.0, top3Transactions.get(0).getAmount());
        Assertions.assertEquals(666.0, top3Transactions.get(1).getAmount());
        Assertions.assertEquals(666.0, top3Transactions.get(2).getAmount());
    }

    /**
     * Tests the {@link TransactionDataFetcher#getTopSender()} method.
     */
    @Test
    public void testGetTopSender() {
        TransactionDataFetcher dataFetcher = new TransactionDataFetcher(transactionList);

        Optional<String> topSender = dataFetcher.getTopSender();
        Assertions.assertTrue(topSender.isPresent());
        Assertions.assertEquals("Grace Burgess", topSender.get());
    }


}
