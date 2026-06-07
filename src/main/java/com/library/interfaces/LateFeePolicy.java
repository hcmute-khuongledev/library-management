package com.library.interfaces;

public interface LateFeePolicy {
    double applyPolicy(double baseFee);
    String getPolicyName();
}
