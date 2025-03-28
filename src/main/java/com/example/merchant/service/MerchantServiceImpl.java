package com.example.merchant.service;

import com.example.merchant.dto.MerchantInputDto;
import com.example.merchant.dto.MerchantOutputDto;
import com.example.merchant.mappers.MerchantMapper;
import com.example.merchant.model.MerchantEntity;
import com.example.merchant.repository.MerchantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MerchantServiceImpl implements MerchantService {

    private final MerchantRepository merchantRepository;
    private final MerchantMapper merchantMapper;

    public MerchantServiceImpl(MerchantRepository merchantRepository, MerchantMapper merchantMapper) {
        this.merchantRepository = merchantRepository;
        this.merchantMapper = merchantMapper;
    }

    @Override
    public MerchantOutputDto createMerchant(MerchantInputDto merchantDto) {
        MerchantEntity entity = merchantMapper.toEntity(merchantDto);
        entity.inicializarClavesSiFaltan();
        return merchantMapper.toDto(merchantRepository.save(entity));
    }

    @Override
    public MerchantOutputDto findMerchantById(String id, boolean simpleOutput) {
        MerchantEntity merchant = merchantRepository.findById(id);
        if (merchant == null) return null;
        if (simpleOutput) {
            MerchantOutputDto simple = new MerchantOutputDto();
            simple.setId(merchant.getId());
            return simple;
        }
        return merchantMapper.toDto(merchant);
    }

    @Override
    public List<MerchantOutputDto> findMerchantsByClientId(String clientId) {
        return merchantRepository.findMerchantsByClientId(clientId).stream()
                .map(merchantMapper::toDto)
                .collect(Collectors.toList());
    }
}