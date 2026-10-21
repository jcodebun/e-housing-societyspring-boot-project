package in.com.ehousing.form;


import javax.validation.constraints.NotEmpty;

import in.com.ehousing.dto.BaseDTO;
import in.com.ehousing.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ForgetPasswordForm extends BaseForm {

	@NotEmpty(message = "Login is required")
	private String login;
	
	
	
	
	
	
	
	

	@Override
	public BaseDTO getDTO() {
		UserDTO dto = new UserDTO();
		dto.setLogin(login);
		return dto;
	}

	@Override
	public void populate(BaseDTO bDto) {
		
	}

}
