package com.blog.service.dto;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserDTO {

	private Long id;
	@NotEmpty(message = "first name shoud not be null")
	private String firstName;
	//@NotNull(message = "last name shoud not be null")
	private String lastName;
	@NotEmpty(message = "middle name shoud not be null")
	private String middleName;
	@NotEmpty(message = "password name shoud not be null")
	private String password;
	@NotEmpty(message = "description should not be null")
	private String description;
}
