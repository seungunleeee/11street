package com.example.demo.TestComponents;

import com.example.demo.DTO.FrontOrderDto;
import com.example.demo.domain.*;
import com.example.demo.domain.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;
    //로그인 controller
    @PostMapping("/sign")
    public  Users  signin(@RequestBody Users users){
        System.out.println(users);
        Users user1 =testService.Signin(users);

        return  user1;
    }


    // item 관련 controller
    @PostMapping("/item")
    public  items  items(@RequestBody items items){
        System.out.println(items);
        return testService.item(items);

    }

    //coupon 관련 controller
    @PostMapping("/coupon")
    public Coupon coupons(@RequestBody Coupon coupon){
        System.out.println(coupon);
        return testService.coupons(coupon);
    }

    // promotion 관련 controller
    @PostMapping("/promotion")
    public Promotion coupons(@RequestBody Promotion promotion){
        System.out.println(promotion);
        return testService.promotion(promotion);
    }

    @PostMapping("/Qpromotion")
    public ResponseEntity<?> Qpromotion(){
        System.out.println();
        List<Promotion> promotionList =    testService.Qpromotion();

        return new ResponseEntity<>(promotionList, HttpStatus.OK);
    }

    @PostMapping("/promotion/coupon")
    public Promotion coupons(@RequestBody long id ,@RequestBody List<Long> couponIdList){
        System.out.println(id);
        return testService.promotioncoupon(id,couponIdList);
    }
    @PostMapping("/promotions")
    public Promotion promotions(@RequestBody long id){
        System.out.println(id);
        return testService.getPromotion(id);
    }

    // 주문
    @PostMapping("/order")
    public  ResponseEntity<?> orderExecute(@RequestBody FrontOrderDto frontOrderDto){
        System.out.println("주문 받은 promotion ids -> "+  frontOrderDto);
        List<Orders> result=testService.StoreOrder(frontOrderDto);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
    @PutMapping("/order")
    public ResponseEntity<?>  orderChange(@RequestBody FrontOrderDto frontOrderDto) {
        System.out.println( "주문 변경 또는 취소할 fronOrderDto"+frontOrderDto);
        HttpStatus status = testService.UpdateOrder(frontOrderDto);
        return new ResponseEntity<>(status);
    }
    @GetMapping("/order")
    public  ResponseEntity<?> ShowOrderList(@RequestPart Pageable pageable){
        return null;
    }

    //장바구니 cart관련 controller
    @PostMapping("/cart")
    public  ResponseEntity<?> addItemToCart(@RequestBody FrontOrderDto frontOrderDto){
        System.out.println( "cart 에 추가 할 promotion id-->>"+frontOrderDto);
        testService.addPromotionInCart(frontOrderDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/cart")
    public  ResponseEntity<?> ShowCartList(@RequestBody  FrontOrderDto frontOrderDto, @PageableDefault(size = 7, sort= "id", direction = Sort.Direction.DESC) Pageable pageable){
        // 장바구니 paging 해서 보내기
        System.out.println("ShowCartList 함수 -->>데이터 매핑 잘됬음 ---");
        Page<Cart> CartList = testService.showCartList(pageable,frontOrderDto.getUserid());
        return new ResponseEntity<>(CartList,HttpStatus.OK);
    }

}
