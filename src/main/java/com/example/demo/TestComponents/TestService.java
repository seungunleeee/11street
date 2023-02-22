package com.example.demo.TestComponents;

import com.example.demo.Repository.CouponPromotionRepository;
import com.example.demo.Repository.CouponRepository;
import com.example.demo.domain.Users;
import com.example.demo.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import com.example.demo.Repository.*;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class TestService {
    private final CouponRepository couponRepository;
    private final CouponPromotionRepository couponPromotionRepository;
    private final itemRepository itemRepository;
    private final OrdersRepository ordersRepository;
    private final PromotionRepository promotionRepository;
    private final UsersRepository usersRepository;


    @Transactional
    public Users Signin(Users users){
    Users result =usersRepository.save(users);
    result.setAddress("수원시 탑동 세광양대창");
    return result;
    }
    @Transactional
    public items item(items item){

        item.setUser(usersRepository.findById(new Long(1)).get());

        items result =itemRepository.save(item);
        return result;
    }
    @Transactional
    public Coupon coupons(Coupon coupon){
        return couponRepository.save(coupon);
    }

    @Transactional
    public Promotion promotion(Promotion promotion){

        promotion.setItem(itemRepository.findById(new Long(1)).get());
        return promotionRepository.save(promotion);
    }
    @Transactional
    public Promotion promotioncoupon(long id){

        Promotion promotion= promotionRepository.findById(id).get();
      CouponPromotion couponPromotion = new CouponPromotion();
      couponPromotion.setPromotion(promotion);
      Coupon tempcoupon = new Coupon();
      tempcoupon.setId(1);
      couponPromotion.setCoupon(tempcoupon);
        promotion.getCouponPromotionList().add(couponPromotion);
//        couponPromotionRepository.save(couponPromotion);




         couponPromotion = new CouponPromotion();
        couponPromotion.setPromotion(promotion);
         tempcoupon = new Coupon();
        tempcoupon.setId(2);
        couponPromotion.setCoupon(tempcoupon);
        promotion.getCouponPromotionList().add(couponPromotion);
//        couponPromotionRepository.save(couponPromotion);



        return promotionRepository.save(promotion);
    }
    @Transactional
    public Promotion getPromotion(long id){


        return promotionRepository.findById(id).get();
    }

}
