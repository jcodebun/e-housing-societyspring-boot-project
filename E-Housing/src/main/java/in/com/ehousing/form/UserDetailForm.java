package in.com.ehousing.form;

import org.springframework.web.multipart.MultipartFile;

import in.com.ehousing.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailForm extends BaseForm {

	private MultipartFile image;
	
	@Override
	public BaseDTO getDTO() {
		return null;
	}

	@Override
	public void populate(BaseDTO bDto) {
		
	}

}
