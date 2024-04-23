package com.turkcell.identityService.business.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoggedInResponse {
    private String accessToken;
    private String refreshToken;
}
