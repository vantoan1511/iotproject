package com.iotproject.service;

import com.iotproject.entity.Temperature;
import com.iotproject.exception.NoLogFoundException;
import com.iotproject.repository.TempRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TemperatureServiceImpl implements ITemperatureService {

    private final TempRepository tempRepository;

    public TemperatureServiceImpl(TempRepository tempRepository) {
        this.tempRepository = tempRepository;
    }

    @Override
    public Temperature getSingleLog(Long id) {
        return tempRepository.findById(id).orElseThrow(() -> new NoLogFoundException("Không tìm thấy dữ liệu với id %d".formatted(id)));
    }

    @Override
    public List<Temperature> getAllLogs() {
        return tempRepository.findAll();
    }

    @Override
    public Page<Temperature> getAllLogs(Pageable pageable) {
        return tempRepository.findAll(pageable);
    }

    @Override
    public Temperature save(Temperature temperature) {
        temperature.setCreatedDate(Date.from(Instant.now()));
        return tempRepository.save(temperature);
    }

    @Override
    public void delete(long id) {
        tempRepository.deleteById(id);
    }

    @Override
    public void delete(List<Long> ids) {
        for (long id : ids) {
            delete(id);
        }
    }
}
