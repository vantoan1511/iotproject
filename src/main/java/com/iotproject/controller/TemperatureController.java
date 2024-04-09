package com.iotproject.controller;

import com.iotproject.entity.Temperature;
import com.iotproject.http.ErrorResponse;
import com.iotproject.service.ITemperatureService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;

@RestController
@RequestMapping("/v1/api/templogs")
public class TemperatureController {

    private final ITemperatureService temperatureService;

    public TemperatureController(ITemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    @GetMapping({"/", ""})
    public Page<Temperature> getAllLogs(@RequestParam(value = "page", defaultValue = "1") int page,
                                        @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageRequest = PageRequest.of(page - 1, size);
        return temperatureService.getAllLogs(pageRequest);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getSingleLog(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(temperatureService.getSingleLog(id));
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(ErrorResponse.builder()
                    .code(HttpStatus.NOT_FOUND.value())
                    .timestamp(Timestamp.from(Instant.now()))
                    .message(ex.getLocalizedMessage())
                    .path("/v1/api/templogs/" + id)
                    .build(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> sendLog(@RequestBody Temperature temperature) {
        try {
            return new ResponseEntity<>(temperatureService.save(temperature), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ErrorResponse.builder()
                    .path("/v1/api/templogs")
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message(ex.getLocalizedMessage())
                    .timestamp(Timestamp.from(Instant.now()))
                    .build(), HttpStatus.BAD_REQUEST);
        }
    }
}
