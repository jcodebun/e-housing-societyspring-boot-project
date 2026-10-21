package in.com.ehousing.form;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import in.com.ehousing.dto.BaseDTO;
import in.com.ehousing.dto.SocietyDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SocietyForm extends BaseForm {

	@NotEmpty(message = "Name is required")
	private String name;
	@NotEmpty(message = "No Of House is required")
	private String noOfHouse;
	@NotEmpty(message = "Address is required")
	private String address;
	@NotEmpty(message = "City is required")
	private String city;
	@NotEmpty(message = "Pin Code is required")
	private String pincode;

	private MultipartFile image;

	

	@Override
	public BaseDTO getDTO() {
		SocietyDTO bean=new SocietyDTO();
		bean.setId(id);
		bean.setName(name);
		bean.setNoOfHouse(noOfHouse);
		bean.setAddress(address);
		bean.setCity(city);
		bean.setPincode(pincode);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}

	@Override
	public void populate(BaseDTO bdto) {
		SocietyDTO dto=(SocietyDTO) bdto;
		id=getId();
		name=dto.getName();
		noOfHouse=dto.getNoOfHouse();
		address=dto.getAddress();
		city=dto.getCity();
		pincode=dto.getPincode();
		createdBy=dto.getCreatedBy();
		modifiedBy=dto.getModifiedBy();
		createdDateTime=dto.getCreatedDatetime();
		modifiedDateTime=dto.getModifiedDatetime();
	}

}
