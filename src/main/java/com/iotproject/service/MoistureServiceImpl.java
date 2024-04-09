package com.iotproject.service;

import com.iotproject.entity.Moisture;
import com.iotproject.repository.MoistureRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class MoistureServiceImpl implements IMoistureService {

    private final MoistureRepository moistureRepository;

    public MoistureServiceImpl(MoistureRepository moistureRepository) {
        this.moistureRepository = moistureRepository;
    }

    @Override
    public Moisture getSingleLog(Long id) {
        return moistureRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tim thấy dữ liệu với id %d".formatted(id)));
    }

    @Override
    public Page<Moisture> getAllLogs(Pageable pageable) {
        return moistureRepository.findAll(pageable);
    }

    @Override
    public Moisture save(Moisture moisture) {
        moisture.setCreatedDate(Date.from(Instant.now()));
        return moistureRepository.save(moisture);
    }
}
