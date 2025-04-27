package com.myproject.transparentmoney.record;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.myproject.transparentmoney.record.dto.RecordMapper;
import com.myproject.transparentmoney.record.dto.request.RecordUpdateRequest;
import com.myproject.transparentmoney.record.model.Record;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RecordService {

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private RecordMapper recordMapper;

    public List<Record> findAll() {
        return recordRepository.findAll();
    }

    public Record findById(String uuid) {
        UUID uuidObj = UUID.fromString(uuid);
        return recordRepository.findById(uuidObj)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Record ID " + uuid + " not found"));
    }

    public Record saveRecord(Record record) {
        record.setCreatedAt(OffsetDateTime.now());
        record.setUpdatedAt(OffsetDateTime.now());
        Record savedRecord = recordRepository.save(record);

        log.info("Record with id: {} saved successfully", record.getId());
        return savedRecord;
    }

    public Record updateRecord(RecordUpdateRequest recordRequest) {
        Record record = findById(recordRequest.id());

        recordMapper.updateRecordFromDto(recordRequest, record);
        record.setUpdatedAt(OffsetDateTime.now());

        Record updatedRecord = recordRepository.save(record);

        log.info("Record with id: {} updated successfully", record.getId());
        return updatedRecord;

    }

    public void deleteRecordById(String uuid) {
        try {
            UUID uuidObj = UUID.fromString(uuid);
            recordRepository.deleteById(uuidObj);
            log.info("Record with id: {} removed successfully", uuid);
        } catch (HttpMessageNotReadableException e) {
            log.error("Record with id: {} doesn't exist", uuid, e);
        }
    }
}
