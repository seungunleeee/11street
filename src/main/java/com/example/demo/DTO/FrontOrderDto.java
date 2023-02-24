package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FrontOrderDto {
    private long userid;
    private List<Long> promotionids = new ArrayList<>();
    private String address ;

    private String updateOption;
    private long orderId;
}
