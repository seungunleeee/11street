package com.example.demo.domain;

import com.example.demo.customClass.PromotionType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private PromotionType promotionType;
    private LocalDateTime EnrolledTime;
    private LocalDateTime PromotionEndTime;

    @OneToOne

    private items item;

    @OneToMany(mappedBy = "promotion",fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnoreProperties({"promotion"})
    List<CouponPromotion> couponPromotionList = new ArrayList<>();
}
