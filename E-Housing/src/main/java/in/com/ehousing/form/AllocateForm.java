package in.com.ehousing.form;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.util.unit.DataUnit;
import org.springframework.web.multipart.MultipartFile;

import in.com.ehousing.dto.AllocateDTO;
import in.com.ehousing.dto.BaseDTO;
import in.com.ehousing.dto.HouseDTO;
import in.com.ehousing.dto.SocietyDTO;
import in.com.ehousing.util.DataUtility;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AllocateForm extends BaseForm {

	@NotEmpty(message = "First Name is required")
	private String firstName;
	
	@NotEmpty(message = "Last Name is required")
	private String lastName;
	
	@NotEmpty(message = "Email is required")
	private String email;
	
	@NotEmpty(message = "Mobile No is required")
	private String mobileNo;
	
	@NotEmpty(message = "Date of Birth is required")
	private String dob;
	
	@NotEmpty(message = "Total Member is required")
	private String totalMember;
	
	@Min(value = 1,message = "House is required")
	private long houseId;

	@Min(value = 1,message = "Society is required")
	private long societyId;

	private MultipartFile image;
	
	@NotEmpty(message ="User Name is required")
	private String userName;
	@NotEmpty(message ="Password is required")
	private String password;

	@Override
	public BaseDTO getDTO() {
		AllocateDTO bean=new AllocateDTO();
		bean.setId(id);
		bean.setFirstName(firstName);
		bean.setLastName(lastName);
		bean.setEmail(email);
		bean.setMobileNo(mobileNo);
		bean.setDob(DataUtility.getDate(dob));
		bean.setTotalMember(totalMember);
		bean.setSocietyId(societyId);
		bean.setHouseId(houseId);
		bean.setUserName(userName);
		bean.setPassword(password);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}

	@Override
	public void populate(BaseDTO bdto) {
		AllocateDTO dto=(AllocateDTO) bdto;
		id=getId();
		firstName=dto.getFirstName();
		lastName=dto.getLastName();
		email=dto.getEmail();
		mobileNo=dto.getMobileNo();
		dob=DataUtility.getDateString(dto.getDob());
		totalMember=dto.getTotalMember();
		houseId=dto.getHouseId();
		societyId=dto.getSocietyId();
		userName=dto.getUserName();
		password=dto.getPassword();
		createdBy=dto.getCreatedBy();
		modifiedBy=dto.getModifiedBy();
		createdDateTime=dto.getCreatedDatetime();
		modifiedDateTime=dto.getModifiedDatetime();
	}

}
