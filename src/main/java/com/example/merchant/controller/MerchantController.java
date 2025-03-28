package com.example.merchant.controller;

import com.example.merchant.dto.MerchantInputDto;
import com.example.merchant.dto.MerchantOutputDto;
import com.example.merchant.service.MerchantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/merchant")
public class MerchantController {

    private final MerchantService merchantService;

    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @PostMapping("/create")
    public ResponseEntity<MerchantOutputDto> createMerchant(@RequestBody MerchantInputDto merchantDto) {
        return ResponseEntity.ok(merchantService.createMerchant(merchantDto));
    }

    @GetMapping("/findById")
    public ResponseEntity<MerchantOutputDto> findById(@RequestParam String id, @RequestParam(required = false) Boolean simpleOutput) {
        return ResponseEntity.ok(merchantService.findMerchantById(id, simpleOutput != null && simpleOutput));
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<MerchantOutputDto>> findByClient(@PathVariable String clientId) {
        return ResponseEntity.ok(merchantService.findMerchantsByClientId(clientId));
    }
}