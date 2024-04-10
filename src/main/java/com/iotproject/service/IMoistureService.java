package com.iotproject.service;

import com.iotproject.entity.Moisture;
import com.iotproject.entity.Temperature;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IMoistureService {

    Moisture getSingleLog(Long id);

    List<Moisture> getAllLogs();

    Page<Moisture> getAllLogs(Pageable pageable);

    Moisture save(Moisture moisture);

    void delete(Long id);

    void delete(List<Long> ids);
}
