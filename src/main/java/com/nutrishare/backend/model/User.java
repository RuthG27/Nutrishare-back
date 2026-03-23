package com.nutrishare.backend.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "usuarios")
public class User {
	@Id
	private String id;
	private String name;
	private String email;
	private String passwordHash;
	private List<String> favoritasIds = new ArrayList<>();
}