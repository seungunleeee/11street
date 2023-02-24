package com.example.demo.TestComponents;

import com.example.demo.domain.*;
import com.example.demo.domain.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;

    @PostMapping("/sign")
    public  Users  signin(@RequestBody Users users){
        System.out.println(users);
       Users user1 =testService.Signin(users);

return  user1;
    }
    @PostMapping("/item")
    public  items  items(@RequestBody items items){
        System.out.println(items);
        return testService.item(items);

    }
    @PostMapping("/coupon")
    public Coupon coupons(@RequestBody Coupon coupon){
        System.out.println(coupon);
        return testService.coupons(coupon);
    }
    @PostMapping("/promotion")
    public Promotion coupons(@RequestBody Promotion promotion){
        System.out.println(promotion);
        return testService.promotion(promotion);
    }

    @PostMapping("/promotion/coupon")
    public Promotion coupons(@RequestBody long id){
        System.out.println(id);
        return testService.promotioncoupon(id);
    }
    @PostMapping("/promotions")
    public Promotion promotions(@RequestBody long id){
        System.out.println(id);
        return testService.getPromotion(id);
    }
    @GetMapping("/test")
    public  Promotion testpromotion(){
        System.out.println( "it's for test ");
        return null;
    }
    @PostMapping("testlogin")
    public  Promotion testlogin(){
        System.out.println(" it's test login");
        return null;
    }




    @GetMapping("testGet")
    public Promotion testmethod (){
        System.out.println("It's for tests");
        return null;
    }

}
