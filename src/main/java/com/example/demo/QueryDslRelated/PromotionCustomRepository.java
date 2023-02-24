package com.example.demo.QueryDslRelated;

import com.example.demo.domain.Promotion;

import java.util.List;

public interface PromotionCustomRepository {

    List<Promotion> findAllInnerFetchJoin();

    List<Promotion> findAllInnerFetchJoinWithDistinct();
}
