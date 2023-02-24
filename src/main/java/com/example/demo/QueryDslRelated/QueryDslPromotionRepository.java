package com.example.demo.QueryDslRelated;

import com.example.demo.domain.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueryDslPromotionRepository extends JpaRepository<Promotion , Long > , PromotionCustomRepository {
}
