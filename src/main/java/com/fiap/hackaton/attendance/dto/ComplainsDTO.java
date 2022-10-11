package com.fiap.hackaton.attendance.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComplainsDTO {
	
	private String id;
	private Date dataCriacao;
	private Date dataAlteracao;
	private String usuario;
	private String reclamacao;

}
