package com.example.merchant.service;

import com.example.merchant.dto.MerchantInputDto;
import com.example.merchant.dto.MerchantOutputDto;

import java.util.List;

public interface MerchantService {
    MerchantOutputDto createMerchant(MerchantInputDto merchantDto);
    MerchantOutputDto findMerchantById(String id, boolean simpleOutput);
    List<MerchantOutputDto> findMerchantsByClientId(String clientId);
}
