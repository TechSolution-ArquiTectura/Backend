package com.upc.TuCine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;

import com.upc.TuCine.user.domain.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

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
    @Column(name = "phone", length = 9)
    private String phone;
    @Column(name = "logo_src")
    private String logoSrc;
    @Column(name = "banner_src")
    private String bannerSrc;
    @Column(name = "description", length = 2000, nullable = false)
    private String description;
    @Column(name = "address", length = 200, nullable = false)
    private String address;
    @Column(name = "rating")
    private Float rating;
    @Column(name = "comments_count")
    private Integer commentsCount;

    @Column(name = "start_time")
    private LocalDate startTime;
    @Column(name = "end_time")
    private LocalDate endTime;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false, foreignKey = @ForeignKey(name = "FK_BUSINESS_USER"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

    @JsonIgnore
    @ManyToMany
    private List<BusinessType> businessTypes;

}
