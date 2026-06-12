public class RazorparPaymentStrategy implements PaymentStrategy {
    @Override
    public PaymentStatus pay(int cost) {

        try {
            Thread.sleep(2000);
            return PaymentStatus.SUCCEED;
        } catch (Exception e) {
            return PaymentStatus.FAILED;
        }
    }
}
