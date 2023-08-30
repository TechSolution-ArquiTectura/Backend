package com.upc.TuCine.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.upc.TuCine.user.domain.model.entity.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.*;

@With
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PaymentMethods")
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotBlank
    @Size(max=60)
    @Column(name = "payment_token", length = 60, nullable = false)
    private String paymentToken;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false, foreignKey = @ForeignKey(name = "FK_PAYMENTMETHOD_USER"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

}