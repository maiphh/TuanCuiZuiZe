public class PlatinumMember extends Member {
    private static final long SPENDING_THRESHOLD = 25_000_000;
    private static final double DISCOUNT = 0.15;

    PlatinumMember(Member member) {
        this.setUserName(member.getUserName());
        this.setFullName(member.getFullName());
        this.setPassword(member.getPassword());
        this.setCustomerId(member.getCustomerId());
        this.setPhoneNumber(member.getPhoneNumber());
        this.setSpending(member.getSpending());

    }

    public static long getSpendingThreshold() {
        return SPENDING_THRESHOLD;
    }

    @Override
    public double getDiscount() {
        return DISCOUNT;
    }
}