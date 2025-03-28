package com.example.merchant.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@DynamoDBTable(tableName = "MainTable")
public class MainTable {

    private String PK;
    private String SK;

    @DynamoDBAttribute
    private String id;

    @DynamoDBAttribute
    private String status;

    @DynamoDBAttribute(attributeName = "gIndex2Pk")
    private String gindex2Pk;

    @DynamoDBAttribute
    private Date createdDate;

    @DynamoDBHashKey(attributeName = "PK")
    public String getPK() {
        return PK;
    }

    public void setPK(String PK) {
        this.PK = PK;
    }

    @DynamoDBRangeKey(attributeName = "SK")
    public String getSK() {
        return SK;
    }

    public void setSK(String SK) {
        this.SK = SK;
    }

    public String getGindex2Pk() {
        return gindex2Pk;
    }

    public void setGindex2Pk(String gindex2Pk) {
        this.gindex2Pk = gindex2Pk;
    }

    public void inicializarBase(String tipoEntidad) {
        if (this.id == null || this.id.isEmpty()) {
            String generatedId = UUID.randomUUID().toString();
            this.id = generatedId;
            this.PK =  tipoEntidad+"#" + generatedId;
            this.SK = generatedId;
        }

        if (this.status == null) {
            this.status = "ACTIVE";
        }

        if (this.createdDate == null) {
            this.createdDate = new Date();
        }
    }
}
