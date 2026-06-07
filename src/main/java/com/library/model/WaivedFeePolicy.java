package com.library.model;

import com.library.interfaces.LateFeePolicy;

public class WaivedFeePolicy implements LateFeePolicy {
    @Override
    public double applyPolicy(double baseFee) { return 0; }
    @Override
    public String getPolicyName() { return "Mien phi phat (thang khai truong)"; }
}
