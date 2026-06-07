package com.library.model;

import com.library.interfaces.LateFeePolicy;

public class StandardFeePolicy implements LateFeePolicy {
    @Override
    public double applyPolicy(double baseFee) { return baseFee; }
    @Override
    public String getPolicyName() { return "Phi phat tieu chuan (100%)"; }
}

