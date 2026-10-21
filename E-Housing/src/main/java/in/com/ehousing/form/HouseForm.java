package in.com.ehousing.form;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import in.com.ehousing.dto.BaseDTO;
import in.com.ehousing.dto.HouseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HouseForm extends BaseForm {

	@NotEmpty(message = "Name is required")
	private String name;
	
	@NotEmpty(message = "Block No is required")
	private String blockNo;
	
	@NotEmpty(message = "House Type is required")
	private String houseType;
	@NotEmpty(message = "Detail is required")
	private String detail;
	
	
	@Min(value = 1,message = "Society is required")
	private long societyId;


	

	@Override
	public BaseDTO getDTO() {
		HouseDTO bean=new HouseDTO();
		bean.setId(id);
		bean.setName(name);
		bean.setBlockNo(blockNo);
		bean.setHouseType(houseType);
		bean.setDetail(detail);
		bean.setSocietyId(societyId);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}

	@Override
	public void populate(BaseDTO bdto) {
		HouseDTO dto=(HouseDTO) bdto;
		id=getId();
		name=dto.getName();
		blockNo=dto.getBlockNo();
		houseType=dto.getHouseType();
		detail=dto.getDetail();
		societyId=dto.getSocietyId();
		createdBy=dto.getCreatedBy();
		modifiedBy=dto.getModifiedBy();
		createdDateTime=dto.getCreatedDatetime();
		modifiedDateTime=dto.getModifiedDatetime();
	}

}
