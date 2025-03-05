package com.myproject.transparentmoney.record;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/records")
@Validated
public class RecordController {

    @Autowired
    private RecordService recordService;

    @GetMapping("")
    public ResponseEntity<List<Record>> findAll() {
        return ResponseEntity.ok(recordService.findAll());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Record> findById(@PathVariable String uuid) {
        // can modify based on what to return to API ui
        return ResponseEntity.ok(recordService.findById(uuid).orElse(null));
    }

    @PostMapping("")
    public ResponseEntity<Record> saveRecord(@RequestBody Record record) {
        return ResponseEntity.ok(recordService.saveRecord(record));
    }

    @PutMapping("")
    public ResponseEntity<Record> updateRecord(@RequestBody Record record) {
        return ResponseEntity.ok(recordService.updateRecord(record));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteRecordById(@PathVariable String uuid) {
        recordService.deleteRecordById(uuid);
        return ResponseEntity.ok().build();
    }
}
