package com.iotproject.api;

import com.iotproject.entity.Temperature;
import com.iotproject.http.ErrorResponse;
import com.iotproject.service.ITemperatureService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/v1/api/templogs")
public class TemperatureAPI {

    private final ITemperatureService temperatureService;

    public TemperatureAPI(ITemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    @GetMapping({"/", ""})
    public Object getAllLogs(
            @RequestParam(value = "unpaged", defaultValue = "false") boolean unPaged,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        if (unPaged) {
            return temperatureService.getAllLogs();
        }
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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        try {
            temperatureService.delete(id);
            return ResponseEntity.ok("Deleted successfully!");
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(ErrorResponse.builder()
                    .path("/v1/api/templogs")
                    .code(HttpStatus.NOT_FOUND.value())
                    .message(ex.getLocalizedMessage())
                    .timestamp(Timestamp.from(Instant.now()))
                    .build(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody List<Long> ids) {
        try {
            temperatureService.delete(ids);
            return ResponseEntity.ok("Deleted successfully!");
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(ErrorResponse.builder()
                    .path("/v1/api/templogs")
                    .code(HttpStatus.NOT_FOUND.value())
                    .message(ex.getLocalizedMessage())
                    .timestamp(Timestamp.from(Instant.now()))
                    .build(), HttpStatus.NOT_FOUND);
        }
    }
}
