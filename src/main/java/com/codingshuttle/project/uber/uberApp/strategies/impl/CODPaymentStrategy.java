package com.codingshuttle.project.uber.uberApp.strategies.impl;

import com.codingshuttle.project.uber.uberApp.entities.Driver;
import com.codingshuttle.project.uber.uberApp.entities.Payment;
import com.codingshuttle.project.uber.uberApp.entities.Wallet;
import com.codingshuttle.project.uber.uberApp.services.WalletService;
import com.codingshuttle.project.uber.uberApp.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//Rider -> 100
//Driver -> 70 - deduct 30 from driver's wallet(Uber commission)

@Service
@RequiredArgsConstructor
public class CODPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;

    @Override
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();//to get the driver of the assigned ride

        Wallet driverWallet = walletService.findByUser(driver.getUser());
        double platformCommission = payment.getAmount() * PLATFORM_COMMISSION;//commission calculation
        walletService.deductMoneyFromWallet(driver.getUser(), platformCommission);//method to deduct the money from driver's wallet


    }
}
