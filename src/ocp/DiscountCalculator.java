package ocp;

import java.util.Map;

public class DiscountCalculator {
    private final Map<String, DiscountStrategy> strategies;

    public DiscountCalculator() {
        strategies = Map.of(
                "regular", new RegularDiscount(),
                "vip", new VipDiscount(),
                "super_vip", new SuperVipDiscount()
        );
    }

    public double calculateDiscount(String userType, double price) {
        DiscountStrategy strategy = strategies.getOrDefault(userType, p -> 0.0);
        return strategy.applyDiscount(price);
    }
}
