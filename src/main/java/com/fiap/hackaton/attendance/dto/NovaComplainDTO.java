package com.fiap.hackaton.attendance.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class NovaComplainDTO {
	
	private Long id;
	private Date dataCriacao;
	private Date dataAlteracao;
	private String usuario;

	
}
