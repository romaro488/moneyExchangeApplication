package com.application.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Error extends Throwable {

	private String description;
}
