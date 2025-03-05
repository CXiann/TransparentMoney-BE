package com.myproject.transparentmoney.record;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, UUID> {

}
