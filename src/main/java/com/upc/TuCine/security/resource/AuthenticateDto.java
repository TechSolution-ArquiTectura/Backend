package com.upc.TuCine.security.resource;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class AuthenticateDto {
    private Long id;
    private String email;
    private List<String> typeUsers;
    private String token;
}
