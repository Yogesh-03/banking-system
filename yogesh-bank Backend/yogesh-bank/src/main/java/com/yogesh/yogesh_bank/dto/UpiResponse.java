package com.yogesh.yogesh_bank.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UpiResponse {
    String status;
    String responseCode;
    String transactionId;
    LocalDateTime timestamp;
}
