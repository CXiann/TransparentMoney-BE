package com.myproject.transparentmoney.record;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RecordService {

    @Autowired
    private RecordRepository recordRepository;

    public List<Record> findAll() {
        return recordRepository.findAll();
    }

    public Optional<Record> findById(String uuid) {
        try {
            UUID uuidObj = UUID.fromString(uuid);
            Optional<Record> optionalrecord = recordRepository.findById(uuidObj);
            if (optionalrecord.isPresent()) {
                return optionalrecord;
            }
            log.info("Record with id: {} doesn't exist", uuid);
            return null;
        } catch (HttpMessageNotReadableException e) {
            log.error("Record with id: {} doesn't exist", uuid, e);
            return null;
        }

    }

    public Record saveRecord(Record record) {
        record.setCreatedAt(OffsetDateTime.now());
        record.setUpdatedAt(OffsetDateTime.now());
        Record savedRecord = recordRepository.save(record);

        log.info("Record with id: {} saved successfully", record.getId());
        return savedRecord;
    }

    public Record updateRecord(Record record) {
        boolean recordExisted = findById(record.getId().toString()).isPresent();
        if (recordExisted) {
            record.setUpdatedAt(OffsetDateTime.now());
            Record updatedRecord = recordRepository.save(record);
            log.info("Record with id: {} updated successfully", record.getId());
            return updatedRecord;
        }
        log.info("Record with id: {} doesn't exist", record.getId());
        return null;

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
