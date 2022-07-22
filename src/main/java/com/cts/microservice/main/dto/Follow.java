package com.cts.microservice.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Follow {

	private long id;

	private String username;

	private String following;

}
