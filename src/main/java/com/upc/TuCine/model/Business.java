package com.upc.TuCine.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.upc.TuCine.user.domain.model.entity.User;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Business")
public class Business {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", length = 100, nullable = false)
    private String name;
    @Column(name = "social_reason", length = 200, nullable = false)
    private String socialReason;
    @Column(name = "ruc", length = 11, nullable = false)
    private String ruc;
    @Column(name = "phone", length = 9, nullable = false)
    private String phone;
    @Column(name = "email", length = 100, nullable = false)
    private String email;
    @Column(name = "image_logo", length = 5000, nullable = false)
    private String imageLogo;
    @Column(name = "image_banner", length = 5000, nullable = false)
    private String imageBanner;
    @Column(name = "description", length = 2000, nullable = false)
    private String description;
    @Column(name = "date_attention", length = 800, nullable = false)
    private String dateAttention;
    @Column(name = "address", length = 200, nullable = false)
    private String address;
    @Column(name = "reference_address", length = 200, nullable = false)
    private String referenceAddress;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "FK_OWNER_ID"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

    @ManyToOne
    @JoinColumn(name = "BusinessType_id", nullable = false, foreignKey = @ForeignKey(name = "FK_DISTRICT_ID"))

    private BusinessType businessType;
}
