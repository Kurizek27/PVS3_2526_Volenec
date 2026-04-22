package streams;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class PaymentDataGenerator {

    public static void main(String[] args) {
        List<Payment> payments = generateRandomPayments(10);
        payments.forEach(System.out::println);
    }

    public static List<Payment> generateRandomPayments(int count) {
        if (count <= 0) return null;

        Random rng = new Random();
        List<String> users = Arrays.asList("U001", "U002", "U003", "U004", "U005");
        List<String> paymentMethods = Arrays.asList("Credit card", "Debit card", "Paypal", "Bank Transfer");
        List<String> statuses = Arrays.asList("Pending", "Declined", "Completed");
        List<String> categories = Arrays.asList("groceries", "utilities", "bills", "travel", "entertaiment");

        return IntStream.range(0, count)
                .mapToObj(i -> new Payment(
                        "TXN" + (1000 + i),
                        users.get(rng.nextInt(users.size())),
                        10 + (500 * rng.nextDouble()),
                        LocalDateTime.now().minusDays(rng.nextInt(30)),
                        paymentMethods.get(rng.nextInt(paymentMethods.size())),
                        statuses.get(rng.nextInt(statuses.size())),
                        categories.get(rng.nextInt(categories.size()))
                )).toList();
    }
}

class Payment {
    private String transactionId;
    private String userId;
    private double amount;
    private LocalDateTime transactionDate;
    private String paymentMethod;
    private String status;
    private String category;

    public Payment(String transactionId, String userId, double amount, LocalDateTime transactionDate, String paymentMethod, String status, String category) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.category = category;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getUserId() {
        return userId;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "PaymentDataGenerator{" +
                "transactionId='" + transactionId + '\'' +
                ", userId='" + userId + '\'' +
                ", amount=" + amount +
                ", transactionDate=" + transactionDate +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", status='" + status + '\'' +
                ", category='" + category + '\'' +
                '}';
    }

}