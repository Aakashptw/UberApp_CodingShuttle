package com.codingshuttle.project.uber.uberApp.services;

import com.codingshuttle.project.uber.uberApp.entities.User;
import com.codingshuttle.project.uber.uberApp.entities.Wallet;

public interface WalletService {

    Wallet addMoneyToWallet(Long userId, Double amount);

    void withdrawAllMyMoneyFromWallet();

    Wallet findWalletById(Long WalletId);

    Wallet createNewWallet(User user);
}
