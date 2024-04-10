package com.iotproject.service;

import com.iotproject.entity.Temperature;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITemperatureService {

    Temperature getSingleLog(Long id);

    List<Temperature> getAllLogs();

    Page<Temperature> getAllLogs(Pageable pageable);

    Temperature save(Temperature temperature);

    void delete(long id);

    void delete(List<Long> ids);
}
