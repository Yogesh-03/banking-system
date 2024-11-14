package com.yogesh.yogesh_bank.controller;

import com.itextpdf.text.DocumentException;
import com.yogesh.yogesh_bank.dto.UpiRequestDto;
import com.yogesh.yogesh_bank.dto.UpiResponse;
import com.yogesh.yogesh_bank.entity.Transaction;
import com.yogesh.yogesh_bank.service.impl.BankStatement;
import com.yogesh.yogesh_bank.service.impl.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping()
public class TransactionController {

    @Autowired
    private BankStatement bankStatement;

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/bankStatement")
    public List<Transaction>  generateBankStatement(@RequestParam String accountNumber,
                                                    @RequestParam String startDate,
                                                    @RequestParam String endDate) throws DocumentException, FileNotFoundException {
        return bankStatement.generateStatement(accountNumber, startDate, endDate);
    }

    @GetMapping("/getTransactions")
    public List<Transaction> generateTransactions(@RequestParam String accountNumber,
                                                  @RequestParam String startDate,
                                                  @RequestParam String endDate) throws DocumentException, FileNotFoundException {
        return bankStatement.generateTransaction(accountNumber, startDate, endDate);
    }

    @PostMapping("/upiPayment")
    public UpiResponse initiateUpiPayment(@RequestBody UpiRequestDto upiRequestDto) {
        return transactionService.initiateUpiPayment(upiRequestDto);
    }
}
