package com.example.demo.QueryDslRelated;


import com.example.demo.domain.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import static  com.example.demo.domain.QPromotion.promotion;
import static  com.example.demo.domain.Qitems.items;

@Repository
public class PromotionCustomRepositoryImpl implements PromotionCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public PromotionCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory){
        this.jpaQueryFactory = jpaQueryFactory;

    }

    @Override
    public List<Promotion> findAllInnerFetchJoin() {
        QCouponPromotion couponPromotion = QCouponPromotion.couponPromotion;
        QUsers users = QUsers.users;
        QCoupon coupon = QCoupon.coupon;
        return jpaQueryFactory.selectFrom(promotion)
//            .innerJoin(promotion.couponPromotionList)
                .leftJoin(promotion.item, items)
                .fetchJoin()
                .leftJoin(items.user,users)
                .fetchJoin()
                .leftJoin(promotion.couponPromotionList,couponPromotion)
                .fetchJoin()
                .leftJoin(couponPromotion.coupon,coupon)
                .fetchJoin()
                .distinct()
                .fetch();
    }

    @Override
    public List<Promotion> findAllInnerFetchJoinWithDistinct() {


        return jpaQueryFactory.selectFrom(promotion)

                .innerJoin(promotion.couponPromotionList)
                .fetchJoin()
                .distinct()
                .fetch();
    }
}
