package in.com.ehousing.form;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import in.com.ehousing.dto.BaseDTO;
import in.com.ehousing.dto.ComplainDTO;
import in.com.ehousing.dto.HouseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComplainForm extends BaseForm {

	@NotEmpty(message = "Subject is required")
	private String subject;
	
	@NotEmpty(message = "Complain is Required")
	private String complain;
	
	private String status;
	

	@Override
	public BaseDTO getDTO() {
		ComplainDTO bean=new ComplainDTO();
		bean.setId(id);
		bean.setSubject(subject);
		bean.setComplain(complain);
		bean.setStatus(status);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}

	@Override
	public void populate(BaseDTO bdto) {
		ComplainDTO dto=(ComplainDTO) bdto;
		id=getId();
		subject=dto.getSubject();
		complain=dto.getComplain();
		status=dto.getStatus();
		createdBy=dto.getCreatedBy();
		modifiedBy=dto.getModifiedBy();
		createdDateTime=dto.getCreatedDatetime();
		modifiedDateTime=dto.getModifiedDatetime();
	}

}
