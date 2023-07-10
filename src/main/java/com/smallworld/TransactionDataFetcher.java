package com.smallworld;

import com.smallworld.data.Transaction;

import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TransactionDataFetcher {

    /**
     * List of transaction data.
     */
    List<Transaction> transactionList;

    public TransactionDataFetcher(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    /**
     * Returns the sum of the amounts of all transactions
     */
    public double getTotalTransactionAmount() {
        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        return transactionList
                .stream()
                .filter(distinctByKey(Transaction::getMtn))
                .mapToDouble(transaction -> Double.parseDouble(decimalFormat.format(transaction.getAmount())))
                .sum();
    }

    /**
     * Returns the sum of the amounts of all transactions sent by the specified client
     *
     * @param senderFullName - Full name of the sender whose data needs to be retrieved.
     */
    public double getTotalTransactionAmountSentBy(String senderFullName) {
        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        return transactionList
                .stream()
                .filter(distinctByKey(Transaction::getMtn))
                .filter(transaction -> transaction.getSenderFullName().equalsIgnoreCase(senderFullName))
                .mapToDouble(transaction -> Double.parseDouble(decimalFormat.format(transaction.getAmount())))
                .sum();
    }

    /**
     * Returns the highest transaction amount
     * {@link double}
     */
    public double getMaxTransactionAmount() {
        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        return transactionList
                .stream()
                .filter(distinctByKey(Transaction::getMtn))
                .mapToDouble(transaction -> Double.parseDouble(decimalFormat.format(transaction.getAmount())))
                .max()
                .orElse(0.0);
    }

    /**
     * Counts the number of unique clients that sent or received a transaction
     *  @return The count of unique clients (senders or beneficiaries) involved in the transactions.
     */
    public long countUniqueClients() {
        return transactionList
                .stream()
                .flatMap(transaction -> Stream.of(transaction.getSenderFullName(), transaction.getBeneficiaryFullName()))
                .distinct()
                .count();
    }

    /**
     * Returns whether a client (sender or beneficiary) has at least one transaction with a compliance
     * issue that has not been solved
     * @param clientFullName - beneficiary name whose data needs to be retrieved.
     * @return {@code true} if the client has at least one transaction with an unsolved compliance issue, {@code false} otherwise.
     */
    public boolean hasOpenComplianceIssues(String clientFullName) {
        return transactionList
                .stream()
                .filter(transaction ->
                        transaction.getSenderFullName().equalsIgnoreCase(clientFullName) ||
                                transaction.getBeneficiaryFullName().equalsIgnoreCase(clientFullName))
                .anyMatch(transaction -> !transaction.isIssueSolved());

    }

    /**
     * Returns all transactions indexed by beneficiary name
     * @return A {@link Map} containing transactions indexed by beneficiary name.
     */

    public Map<String, Transaction> getTransactionsByBeneficiaryName() {
        return transactionList
                .stream()
                .filter(distinctByKey(Transaction::getMtn))
                .collect(Collectors.toMap(Transaction::getBeneficiaryFullName, transaction -> transaction));
    }

    /**
     * Returns the identifiers of all open compliance issues.
     * {@link Set} of unsolved issue ids.
     */
    public Set<Integer> getUnsolvedIssueIds() {
        return transactionList
                .stream()
                .filter(transaction -> transaction.getIssueId() != null && !transaction.isIssueSolved())
                .map(Transaction::getIssueId)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a list of all solved issue messages
     * {@link List} of all the messages of the issues that are resolved.
     */
    public List<String> getAllSolvedIssueMessages() {
        return transactionList
                .stream()
                .filter(transaction -> transaction.isIssueSolved())
                .filter(transaction -> transaction.getIssueMessage() != null)
                .map(Transaction::getIssueMessage)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves top 3 transactions with the highest amount.
     * @return {@link List} contaning top 3 transactions with the highest amount in descending order.
     */
    public List<Transaction> getTop3TransactionsByAmount() {
        return transactionList
                .stream()
                .filter(distinctByKey(Transaction::getMtn))
                .sorted(Comparator.comparingDouble(Transaction::getAmount).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    /**
     * Returns the senderFullName of the sender with the most total sent amount
     * @return full name of the sender
     */
    public Optional<String> getTopSender() {
        Map<String, Double> senderTotalAmounts = transactionList.stream()
                .filter(distinctByKey(Transaction::getMtn))
                .collect(Collectors.groupingBy(Transaction::getSenderFullName,
                        Collectors.summingDouble(Transaction::getAmount)));

        return senderTotalAmounts.entrySet().stream()
                .max(Comparator.comparingDouble(Map.Entry::getValue))
                .map(Map.Entry::getKey);
    }

    /**
     * Returns a predicate that filters distinct elements based on a key extracted from each element.* Applies
     * @param <T>           The type of the elements being filtered.
     * @param keyExtractor  The function to extract the key from each element.
     * @return A predicate that filters distinct elements based on the extracted key.
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

}
