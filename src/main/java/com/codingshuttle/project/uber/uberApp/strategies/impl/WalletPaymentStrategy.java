package com.codingshuttle.project.uber.uberApp.strategies.impl;

import com.codingshuttle.project.uber.uberApp.entities.Driver;
import com.codingshuttle.project.uber.uberApp.entities.Payment;
import com.codingshuttle.project.uber.uberApp.entities.Rider;
import com.codingshuttle.project.uber.uberApp.entities.enums.PaymentStatus;
import com.codingshuttle.project.uber.uberApp.entities.enums.TransactionMethod;
import com.codingshuttle.project.uber.uberApp.repositories.PaymentRepository;
import com.codingshuttle.project.uber.uberApp.services.PaymentService;
import com.codingshuttle.project.uber.uberApp.services.WalletService;
import com.codingshuttle.project.uber.uberApp.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//Rider had 232, Driver had 500
//Rider cost is 100, Commission - 30
//Rider - 232 - 100
//Driver - 500 + (100 - 30) = 570

@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    @Transactional
    public void processPayment(Payment payment) {

        Driver driver = payment.getRide().getDriver();//to get the driver of the particular ride
        Rider rider = payment.getRide().getRider();//to get the rider of the particular ride

        walletService.deductMoneyFromWallet(rider.getUser(),
                payment.getAmount(), null, payment.getRide(), TransactionMethod.RIDE);

        double driversCut = payment.getAmount() * (1 - PLATFORM_COMMISSION); //total amount * (1 - 0.3)

        walletService.addMoneyToWallet(rider.getUser(), payment.getAmount(), null, payment.getRide(),
                TransactionMethod.RIDE);

        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }
}
