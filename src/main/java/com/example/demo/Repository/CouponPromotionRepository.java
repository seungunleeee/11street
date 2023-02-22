package com.example.demo.Repository;

import com.example.demo.domain.CouponPromotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponPromotionRepository extends JpaRepository<CouponPromotion,Long> {
}
