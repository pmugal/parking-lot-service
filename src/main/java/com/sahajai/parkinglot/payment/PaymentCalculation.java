package com.sahajai.parkinglot.payment;

import com.sahajai.parkinglot.entity.ParkingTicket;

public interface PaymentCalculation {
    double calculatePaymentSum(ParkingTicket parkingTicket);
}
