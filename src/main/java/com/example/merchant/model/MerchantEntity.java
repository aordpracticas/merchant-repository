package com.example.merchant.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@DynamoDBTable(tableName = "MainTable")
public class MerchantEntity extends MainTable {

    @DynamoDBAttribute
    private String name;

    @DynamoDBAttribute
    private String address;

    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute
    private MerchantType merchantType;

    public enum MerchantType {
        MERCHANT_TYPE_PERSONAL_SERVICES,
        MERCHANT_TYPE_FINANCIAL_SERVICES
    }

    public MerchantEntity(String name, String address, MerchantType merchantType, String clientId) {
        super();
        String generatedId = UUID.randomUUID().toString();
        this.setId(generatedId);
        this.setPK("#Merchant" + this.getId());
        this.setSK(this.getId());
        this.setGindex2Pk("#Client" + clientId);
        this.name = name;
        this.address = address;
        this.merchantType = merchantType;
    }

    public MerchantEntity(String name, String address, MerchantType merchantType) {
        super();
        String generatedId = UUID.randomUUID().toString();
        this.setId(generatedId);
        this.setPK("#Merchant" + this.getId());
        this.setSK(this.getId());
        this.setGindex2Pk(null);
        this.name = name;
        this.address = address;
        this.merchantType = merchantType;
    }

    public void inicializarClavesSiFaltan() {
        this.inicializarBase("Merchant");
    }
}