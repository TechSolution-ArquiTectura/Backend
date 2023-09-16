package com.upc.TuCine.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.upc.TuCine.user.domain.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Review")
public class Review {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "comment", nullable = false)
    private String comment;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Column(name = "created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate updatedAt;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false, foreignKey = @ForeignKey(name = "FK_REVIEW_USER"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

    @ManyToOne
    @JoinColumn(name = "businessId", foreignKey = @ForeignKey(name = "FK_REVIEW_BUSINESS"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Business business;
}
