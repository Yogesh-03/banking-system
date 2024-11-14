package com.yogesh.yogesh_bank.service.impl;

import com.yogesh.yogesh_bank.dto.TransactionDto;
import com.yogesh.yogesh_bank.dto.UpiRequestDto;
import com.yogesh.yogesh_bank.dto.UpiResponse;
import com.yogesh.yogesh_bank.entity.Transaction;
import com.yogesh.yogesh_bank.entity.User;
import com.yogesh.yogesh_bank.repository.TransactionRepository;
import com.yogesh.yogesh_bank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void saveTransaction(TransactionDto transactionDto) {
        Transaction transaction = Transaction.builder()
                .transactionType(transactionDto.getTransactionType())
                .accountNumber(transactionDto.getAccountNumber())
                .amount(transactionDto.getAmount())
                .status("SUCCESS")
                .build();

        transactionRepository.save(transaction);
    }

    @Override
    public UpiResponse initiateUpiPayment(UpiRequestDto upiRequestDto) {
        boolean isAccountExists = userRepository.existsByAccountNumber(upiRequestDto.getAccountNumber());
        String payeeMobileNumber = upiRequestDto.getVirtualPaymentAddress().substring(0,10);
        boolean isPayeeExists = userRepository.existsByPhoneNumber(payeeMobileNumber);

        System.out.println(payeeMobileNumber);
        System.out.println(isAccountExists + " " + isPayeeExists);

        if (!isAccountExists || !isPayeeExists) {
            return UpiResponse.builder()
                    .status("FAILED")
                    .transactionId(null)
                    .responseCode("-1")
                    .timestamp(LocalDateTime.now())
                    .build();
        }

        User userToDebit = userRepository.findByAccountNumber(upiRequestDto.getAccountNumber());
        User userToCredit = userRepository.findByPhoneNumber(payeeMobileNumber);

        if (userToDebit.getAccountBalance().compareTo(upiRequestDto.getAmount()) < 0){
            return UpiResponse.builder()
                    .status("FAILED")
                    .transactionId(null)
                    .responseCode("-1")
                    .timestamp(LocalDateTime.now())
                    .build();
        } else {

            userToDebit.setAccountBalance(userToDebit.getAccountBalance().subtract(upiRequestDto.getAmount()));
            userRepository.save(userToDebit);

            userToCredit.setAccountBalance(userToCredit.getAccountBalance().add(upiRequestDto.getAmount()));
            userRepository.save(userToCredit);

            // save transaction

            TransactionDto transactionDto = TransactionDto.builder()
                    .accountNumber(userToDebit.getAccountNumber())
                    .transactionType("UPI-PAYMENT")
                    .amount(upiRequestDto.getAmount())
                    .build();

            Transaction transaction = Transaction.builder()
                    .transactionType(transactionDto.getTransactionType())
                    .accountNumber(transactionDto.getAccountNumber())
                    .amount(transactionDto.getAmount())
                    .status("SUCCESS")
                    .build();

           Transaction savedTransaction = transactionRepository.save(transaction);

            return UpiResponse.builder()
                    .status("SUCCESS")
                    .transactionId(savedTransaction.getTransactionId())
                    .responseCode("200")
                    .timestamp(LocalDateTime.now())
                    .build();
        }
    }
}
