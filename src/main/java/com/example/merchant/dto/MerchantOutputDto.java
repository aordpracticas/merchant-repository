package com.example.merchant.dto;

import com.example.merchant.model.MerchantEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MerchantOutputDto {
    private String id;
    private String name;
    private String address;
    private MerchantEntity.MerchantType merchantType;
    private String status;
    private String createdDate;
    private String gIndex2Pk;
}
