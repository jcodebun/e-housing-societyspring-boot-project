package in.com.ehousing.form;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import in.com.ehousing.dto.BaseDTO;
import in.com.ehousing.dto.MessageDTO;
import in.com.ehousing.dto.RentSellDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageForm extends BaseForm {

	@NotEmpty(message = "Subject is required")
	private String subject;
	
	@NotEmpty(message = "Message is Required")
	private String message;
	@NotEmpty(message = "Name is Required")
	private String name;
	@NotEmpty(message = "ContactNo is required")
	private String contactNo;
	
	private RentSellDTO rent;
	

	@Override
	public BaseDTO getDTO() {
		MessageDTO bean=new MessageDTO();
		bean.setId(id);
		bean.setSubject(subject);
		bean.setMessage(message);
		bean.setName(name);
		bean.setRent(rent);
		bean.setContactNo(contactNo);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}

	@Override
	public void populate(BaseDTO bdto) {
		MessageDTO dto=(MessageDTO) bdto;
		id=getId();
		rent=dto.getRent();
		subject=dto.getSubject();
		message=dto.getMessage();
		name=dto.getName();
		contactNo=dto.getName();
		createdBy=dto.getCreatedBy();
		modifiedBy=dto.getModifiedBy();
		createdDateTime=dto.getCreatedDatetime();
		modifiedDateTime=dto.getModifiedDatetime();
	}

}
