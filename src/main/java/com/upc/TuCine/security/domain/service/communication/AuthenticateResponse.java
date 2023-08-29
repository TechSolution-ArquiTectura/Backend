package com.upc.TuCine.security.domain.service.communication;

import com.upc.TuCine.security.resource.AuthenticateDto;
import com.upc.TuCine.shared.domain.service.communication.BaseResponse;

public class AuthenticateResponse extends BaseResponse<AuthenticateDto> {
    public AuthenticateResponse(String message) {
        super(message);
    }

    public AuthenticateResponse(AuthenticateDto resource) {
        super(resource);
    }
}
