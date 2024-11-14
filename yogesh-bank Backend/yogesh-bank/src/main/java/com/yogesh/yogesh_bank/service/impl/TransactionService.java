package com.yogesh.yogesh_bank.service.impl;

import com.yogesh.yogesh_bank.dto.TransactionDto;
import com.yogesh.yogesh_bank.dto.UpiRequestDto;
import com.yogesh.yogesh_bank.dto.UpiResponse;
import com.yogesh.yogesh_bank.entity.Transaction;

public interface TransactionService {
    void saveTransaction(TransactionDto transactionDto);
    UpiResponse initiateUpiPayment(UpiRequestDto upiRequestDto);
}
