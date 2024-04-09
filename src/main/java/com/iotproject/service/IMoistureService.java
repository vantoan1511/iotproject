package com.iotproject.service;

import com.iotproject.entity.Moisture;
import com.iotproject.entity.Temperature;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMoistureService {

    Moisture getSingleLog(Long id);

    Page<Moisture> getAllLogs(Pageable pageable);

    Moisture save(Moisture moisture);
}
