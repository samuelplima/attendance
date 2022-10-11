package com.fiap.hackaton.attendance.controller;

import com.fiap.hackaton.attendance.dto.ComplainsDTO;
import com.fiap.hackaton.attendance.services.AttendanceService;
import com.fiap.hackaton.attendance.sqs.MessageServiceProducer;
import com.fiap.hackaton.attendance.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }


    @GetMapping(value = "/getComplain")
    ResponseEntity<ComplainsDTO> getComplain() {
        ComplainsDTO complainsDTO = attendanceService.getComplain();
        return new ResponseEntity<>(complainsDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/closeComplain")
    ResponseEntity<Void> closeComplain(@RequestBody ComplainsDTO complainsDTO) {
        attendanceService.closeAttendance(complainsDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
