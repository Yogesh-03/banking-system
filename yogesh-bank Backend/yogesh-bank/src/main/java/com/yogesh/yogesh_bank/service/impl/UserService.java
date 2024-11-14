package com.yogesh.yogesh_bank.service.impl;

import com.yogesh.yogesh_bank.dto.*;
import org.springframework.stereotype.Service;

public interface UserService {
    BankResponse createAccount(UserRequest userRequest);
    BankResponse balanceEnquiry(EnquiryRequest enquiryRequest);
    String nameEnquiry(EnquiryRequest enquiryRequest);
    BankResponse creditAccount(CreditDebitRequest creditDebitRequest);
    BankResponse debitAccount(CreditDebitRequest creditDebitRequest);
    BankResponse transfer(TransferRequest transferRequest);
    BankResponse login(LoginDto loginDto);
    BankResponse userAccountEnquiry(UserAccountEnquiryDto accountNumber);
}
