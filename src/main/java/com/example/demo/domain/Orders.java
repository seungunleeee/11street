package com.example.demo.domain;

import com.example.demo.customClass.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    //관련된 상품 , 쿠폰이 딸려옴
    @JsonIgnoreProperties({})
    private Promotion promotion;

    private String address ;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @JsonIgnoreProperties({"enrolleditems"})
    @ManyToOne
    private Users users;
}
