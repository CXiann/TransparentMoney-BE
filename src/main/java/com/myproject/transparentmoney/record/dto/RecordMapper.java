package com.myproject.transparentmoney.record.dto;

import org.mapstruct.*;

import com.myproject.transparentmoney.record.dto.request.RecordUpdateRequest;
import com.myproject.transparentmoney.record.model.Record;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RecordMapper {

    void updateRecordFromDto(RecordUpdateRequest dto, @MappingTarget Record record);
}
