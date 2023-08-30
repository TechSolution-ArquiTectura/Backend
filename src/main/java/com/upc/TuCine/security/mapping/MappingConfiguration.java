package com.upc.TuCine.security.mapping;

import com.upc.TuCine.mapping.PaymentMethodMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.upc.TuCine.mapping.TicketMapper;
import com.upc.TuCine.user.mapping.UserMapper;

@Configuration("securityMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public TypeUserMapper typeUserMapper() {
        return new TypeUserMapper();
    }
    @Bean
    public GenderMapper genderMapper() {
        return new GenderMapper();
    }
    @Bean
    public UserMapper userMapper(){
        return new UserMapper();
    }
    @Bean
    public TicketMapper ticketMapper(){
        return new TicketMapper();
    }
    @Bean
    public PaymentMethodMapper paymentMethodsMapper(){
        return new PaymentMethodMapper();
    }
}
