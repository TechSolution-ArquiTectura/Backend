package com.upc.TuCine.security.domain.service.communication;

import com.upc.TuCine.shared.domain.service.communication.BaseResponse;
import com.upc.TuCine.user.resource.UserDto;

public class RegisterResponse extends BaseResponse<UserDto> {
    public RegisterResponse(String message) {
        super(message);
    }

    public RegisterResponse(UserDto resource) {
        super(resource);
    }
}