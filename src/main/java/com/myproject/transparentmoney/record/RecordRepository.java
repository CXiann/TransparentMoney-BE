package com.myproject.transparentmoney.record;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.transparentmoney.record.model.Record;

public interface RecordRepository extends JpaRepository<Record, UUID> {

}
