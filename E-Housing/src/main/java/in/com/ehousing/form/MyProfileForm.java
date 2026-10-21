package in.com.ehousing.form;
import javax.validation.constraints.NotEmpty;

import in.com.ehousing.dto.AllocateDTO;
import in.com.ehousing.dto.BaseDTO;
import in.com.ehousing.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyProfileForm extends BaseForm {

	@NotEmpty(message = "First Name is required")
	private String firstName;
	
	@NotEmpty(message = "LastName is required")
	private String lastName;
	
	@NotEmpty(message = "Email is required")
	private String email;
	
	@NotEmpty(message = "MobileNo is required")
	private String mobileNo;
	
	
	public BaseDTO getDTO() {
		AllocateDTO entity = new AllocateDTO();
		entity.setEmail(email);
		entity.setFirstName(firstName);
		entity.setLastName(lastName);
		entity.setMobileNo(mobileNo);
		return entity;
	}

	
	public void populate(BaseDTO bDto) {
		AllocateDTO entity = (AllocateDTO) bDto;
		email = entity.getEmail();
		firstName = entity.getFirstName();
		lastName = entity.getLastName();
		mobileNo=entity.getMobileNo();

	}

	

}
