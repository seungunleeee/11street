package com.example.demo.TestComponents;

import com.example.demo.DTO.FrontOrderDto;
import com.example.demo.QueryDslRelated.QueryDslPromotionRepository;
import com.example.demo.Repository.CouponPromotionRepository;
import com.example.demo.Repository.CouponRepository;
import com.example.demo.customClass.OrderStatus;
import com.example.demo.domain.Users;
import com.example.demo.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import com.example.demo.Repository.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {
    private final CouponRepository couponRepository;
    private final CouponPromotionRepository couponPromotionRepository;
    private final itemRepository itemRepository;
    private final OrdersRepository ordersRepository;
    private final PromotionRepository promotionRepository;
    private final UsersRepository usersRepository;

    private  final QueryDslPromotionRepository queryDslPromotionRepository;

    private final CartRepository cartRepository ;


    @Transactional
    public Users Signin(Users users){
        Users result =usersRepository.save(users);
        result.setAddress("수원시 탑동 세광양대창");
        return result;
    }
    @Transactional
    public items item(items item){

        item.setUser(usersRepository.findById( Long.valueOf(1)).get());

        items result =itemRepository.save(item);
        return result;
    }
    @Transactional
    public Coupon coupons(Coupon coupon){
        return couponRepository.save(coupon);
    }

    @Transactional
    public Promotion promotion(Promotion promotion){

        promotion.setItem(itemRepository.findById( Long.valueOf(1)).get());
        return promotionRepository.save(promotion);
    }
    @Transactional
    public Promotion promotioncoupon(long promotionID, List<Long> CouponIdList){

        Promotion promotionEntity = promotionRepository.findById(promotionID).get();
        System.out.println("promotion 타입 -->>> "+ promotionEntity.getPromotionType());
        List<CouponPromotion> couponPromotionList = new ArrayList<>();
        for ( Long couponId : CouponIdList){
            CouponPromotion couponPromotion = new CouponPromotion();
            couponPromotion.setPromotion(promotionEntity);
            Coupon tempcoupon = new Coupon();
            tempcoupon.setId(couponId);
            couponPromotion.setCoupon(tempcoupon);
            couponPromotionList.add(couponPromotion);
        }

        couponPromotionRepository.saveAll(couponPromotionList);


        return promotionRepository.save(promotionEntity);
    }
    @Transactional
    public Promotion getPromotion(long id){


        return promotionRepository.findById(id).get();
    }
    @Transactional
    public List<Promotion> Qpromotion(){

        List<Promotion> promotionList  = queryDslPromotionRepository.findAllInnerFetchJoin();
//   List<Promotion> promotionList = promotionRepository.findAll();
        return  promotionList;
    }

    //주문 관련 서비스 method
    @Transactional
    public List<Orders> StoreOrder(FrontOrderDto frontOrderDto){
        Users orderOwner = usersRepository.findById(frontOrderDto.getUserid()).get();
        List <Promotion > orderdPromotions =  promotionRepository.findAllById(frontOrderDto.getPromotionids());
        List<Orders> orderList = new ArrayList();
        Orders orderTemp;
        for(Promotion promotionTemp : orderdPromotions){
            orderTemp = new Orders();
            //DTO 변환으로 바꾸기
            orderTemp.setOrderStatus(OrderStatus.PREPARING);
            orderTemp.setUsers(orderOwner);
            orderTemp.setPromotion(promotionTemp);
            orderTemp.setAddress(frontOrderDto.getAddress());
            orderList.add(orderTemp);
        }
        return ordersRepository.saveAll(orderList);
    }

    @Transactional
    public HttpStatus UpdateOrder(FrontOrderDto frontOrderDto){
        HttpStatus status ;
        Orders currentorder = ordersRepository.findById(frontOrderDto.getOrderId()).get();
        if(currentorder.getOrderStatus()== OrderStatus.PREPARING){
            System.out.println("일단 들어옴");
            if(frontOrderDto.getUpdateOption().equals("Cancel")){
                ordersRepository.delete(currentorder);
            } else if ( frontOrderDto.getUpdateOption().equals("AddressUpdate")) {
                System.out.println( "배송지 변경 완료");
                currentorder.setAddress("경기도 과천시 본수원갈비");
            }
            status= HttpStatus.OK;
        }
        else {
            status = HttpStatus.NOT_ACCEPTABLE;
        }
        return status;
    }

    //Cart 관련 메서드
    @Transactional
    public  void addPromotionInCart(FrontOrderDto frontOrderDto){
        Users CartUser = usersRepository.findById(frontOrderDto.getUserid()).get();
        List< Cart> PromotionToBeAdded = new ArrayList<>();

        for(Long tempPromotionId : frontOrderDto.getPromotionids()) {
            Promotion tempPromotion = new Promotion();
            Cart tempCart = new Cart();
            //DTO로 변환 후 cart 받기
            tempCart.setCartOwner(CartUser);


            tempPromotion.setId(tempPromotionId);
            tempCart.setCartPromotion(tempPromotion);
            PromotionToBeAdded.add(tempCart);
        }
        //sout
        System.out.println( " save all  실행 전");
        cartRepository.saveAll(PromotionToBeAdded);


    }

    @Transactional
    public Page<Cart> showCartList(Pageable pageable , long userId){
        return cartRepository.getCartList(userId,pageable);
    }


}
