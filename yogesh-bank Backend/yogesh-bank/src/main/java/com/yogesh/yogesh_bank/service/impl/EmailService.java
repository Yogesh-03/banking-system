package com.yogesh.yogesh_bank.service.impl;

import com.yogesh.yogesh_bank.dto.EmailDetails;

public interface EmailService {
    void sendEmailAlert(EmailDetails emailDetails);
    void sendEmailWithAttachment(EmailDetails emailDetails);
}
