package com.fiap.hackaton.attendance.services;

import com.fiap.hackaton.attendance.dto.ComplainsDTO;

import java.util.List;

public interface AttendanceService {

    void createList(ComplainsDTO complainsDTO);

    ComplainsDTO getComplain();

    void closeAttendance(ComplainsDTO complainsDTO);

}
