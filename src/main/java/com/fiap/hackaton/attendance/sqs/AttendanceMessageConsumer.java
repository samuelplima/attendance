package com.fiap.hackaton.attendance.sqs;

import com.fiap.hackaton.attendance.dto.ComplainsDTO;
import com.fiap.hackaton.attendance.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class AttendanceMessageConsumer {

    @Value("${amazon.sqs.queue.complains}")
    private String queueName;

    @JmsListener(destination = "${amazon.sqs.queue.complains}")
    public void messageConsumer(@Payload String message) {
        ComplainsDTO complainsDTO = JsonUtil.readValue(message, ComplainsDTO.class);

        log.info("***** COMPLAIN UPDATED RECEIVED AFTER ATTENDANCE:  " + queueName + ", COMPLAIN USER: " + complainsDTO.getUsuario()
                + ", COMPLAIN ID: " + complainsDTO.getId());

        //chama service
    }
}
