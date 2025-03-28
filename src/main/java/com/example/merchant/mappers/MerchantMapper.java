package com.example.merchant.mappers;

import com.example.merchant.dto.MerchantInputDto;
import com.example.merchant.dto.MerchantOutputDto;
import com.example.merchant.model.MerchantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.Instant;
import java.util.Date;

@Mapper(componentModel = "spring")
public interface MerchantMapper {

    @Mapping(target = "gindex2Pk", expression = "java(dto.getClientId() != null ? \"#Client\" + dto.getClientId() : null)")
    MerchantEntity toEntity(MerchantInputDto dto);

    @Mappings({
            @Mapping(target = "createdDate", expression = "java(dateToString(entity.getCreatedDate()))")
    })
    MerchantOutputDto toDto(MerchantEntity entity);

    default String dateToString(Date date) {
        return date != null ? date.toInstant().toString() : null;
    }

    default Date stringToDate(String dateStr) {
        return dateStr != null ? Date.from(Instant.parse(dateStr)) : null;
    }
}
