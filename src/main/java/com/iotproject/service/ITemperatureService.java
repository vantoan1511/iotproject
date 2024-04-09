package com.iotproject.service;

import com.iotproject.entity.Temperature;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITemperatureService {

    Temperature getSingleLog(Long id);

    Page<Temperature> getAllLogs(Pageable pageable);

    Temperature save(Temperature temperature);
}
