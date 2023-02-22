package com.example.demo.Repository;

import com.example.demo.domain.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<Promotion,Long> {
}
