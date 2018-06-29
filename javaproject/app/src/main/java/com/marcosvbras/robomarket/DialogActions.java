package com.marcosvbras.robomarket;

import com.marcosvbras.robomarket.business.domain.Robot;

public interface DialogActions {
    void onSave(RobotSale robotSale);
    void onRemove(RobotSale robotSale);
}
