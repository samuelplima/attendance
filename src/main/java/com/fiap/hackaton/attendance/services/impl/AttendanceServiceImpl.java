package com.fiap.hackaton.attendance.services.impl;

import com.fiap.hackaton.attendance.dto.ComplainsDTO;
import com.fiap.hackaton.attendance.enuns.AttendanceEnum;
import com.fiap.hackaton.attendance.exception.ResourceNotFoundException;
import com.fiap.hackaton.attendance.services.AttendanceService;
import com.fiap.hackaton.attendance.sqs.MessageServiceProducer;
import com.fiap.hackaton.attendance.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AttendanceServiceImpl implements AttendanceService {

    private final List<ComplainsDTO> complainsDTOList;

    private final MessageServiceProducer messageServiceProducer;

    @Value("${amazon.sqs.queue.attendance}")
    private String queueName;


    @Override
    public void createList(ComplainsDTO complainsDTO) {
        complainsDTOList.add(complainsDTO);
    }

    @Override
    public ComplainsDTO getComplain() {
        return complainsDTOList.stream()
                .findFirst().orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND, "Complains List was empty"));
    }

    @Override
    public void closeAttendance(ComplainsDTO complainsDTO) {
        complainsDTO.setStatus(AttendanceEnum.FECHADO.name());
        messageServiceProducer.sentToQueue(queueName, JsonUtil.writeValueAsString(complainsDTO));
        log.info("**** COMPLAIN UPDATED AND SENT TO QUEUE : " + queueName + " ****");
    }

}


