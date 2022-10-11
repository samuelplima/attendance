package com.fiap.hackaton.attendance.controller;

import com.amazonaws.services.sqs.model.Message;
import com.fiap.hackaton.attendance.sqs.MessageServiceProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    private MessageServiceProducer messageServiceProducer;

    @Autowired
    public AttendanceController(MessageServiceProducer messageServiceProducer) {
        this.messageServiceProducer = messageServiceProducer;
    }

    @PostMapping(value = "/postMessage" )
    ResponseEntity<Void> postMessage(@RequestBody String msg){
        messageServiceProducer.sentToQueue("attedence", msg);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
