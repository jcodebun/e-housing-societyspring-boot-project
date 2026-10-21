package in.com.ehousing.form;

import in.com.ehousing.dto.AllocateDTO;
import in.com.ehousing.dto.BaseDTO;
import in.com.ehousing.dto.HouseDTO;
import in.com.ehousing.dto.RentSellDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentSellForm extends BaseForm {
	
	private String price;
	private AllocateDTO allocate;
	private HouseDTO house;
	private long societyId;
	private String type;
	
	

	@Override
	public BaseDTO getDTO() {
		RentSellDTO bean=new RentSellDTO();
		bean.setId(id);
		bean.setPrice(price);
		bean.setAllocate(allocate);
		bean.setHouse(house);
		bean.setSocietyId(societyId);
		bean.setType(type);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		RentSellDTO dto=(RentSellDTO)bDto;
		id=dto.getId();
		societyId=dto.getSociety().getId();
		type=dto.getType();
		price=dto.getPrice();
		allocate=dto.getAllocate();
		house=dto.getHouse();
		createdBy=dto.getCreatedBy();
		modifiedBy=dto.getModifiedBy();
		createdDateTime=dto.getCreatedDatetime();
		modifiedDateTime=dto.getModifiedDatetime();
	}

	@Override
	public String toString() {
		return "RentForm [price=" + price + ", allocate=" + allocate + ", house=" + house + "]";
	}

}
