package com.example.merchant.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.example.merchant.model.MerchantEntity;
import org.springframework.stereotype.Repository;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MerchantRepository {

    private final DynamoDBMapper dynamoDBMapper;

    public MerchantRepository(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public MerchantEntity save(MerchantEntity merchant) {
        dynamoDBMapper.save(merchant);
        return merchant;
    }

    public MerchantEntity findById(String id) {
        MerchantEntity merchantKey = new MerchantEntity();
        merchantKey.setPK("#Merchant" + id);

        DynamoDBQueryExpression<MerchantEntity> query = new DynamoDBQueryExpression<MerchantEntity>()
                .withHashKeyValues(merchantKey)
                .withLimit(1);

        List<MerchantEntity> results = dynamoDBMapper.query(MerchantEntity.class, query);
        return results.isEmpty() ? null : results.get(0);
    }

    public List<MerchantEntity> findMerchantsByClientId(String clientId) {
        Map<String, AttributeValue> expressionValues = new HashMap<>();
        expressionValues.put(":gIndex2Pk", new AttributeValue().withS("#Client" + clientId));

        DynamoDBQueryExpression<MerchantEntity> queryExpression = new DynamoDBQueryExpression<MerchantEntity>()
                .withIndexName("GSIgIndex2Pk")
                .withKeyConditionExpression("gIndex2Pk = :gIndex2Pk")
                .withExpressionAttributeValues(expressionValues)
                .withConsistentRead(false);

        return dynamoDBMapper.query(MerchantEntity.class, queryExpression);
    }
}
