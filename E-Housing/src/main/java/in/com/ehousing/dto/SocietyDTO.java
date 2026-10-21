package in.com.ehousing.dto;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="SOCIETY")
@Getter
@Setter
public class SocietyDTO extends BaseDTO {
	
	@Column(name="NAME",length = 225)
	private String name;
	@Column(name="NO_OF_HOUSE",length = 225)
	private String noOfHouse;
	@Column(name="ADDRESS",length = 225)
	private String address;
	@Column(name="CITY",length = 225)
	private String city;
	@Column(name="PIN_CODE",length = 225)
	private String pincode;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "society")
	private Set<HouseDTO> houses;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "society")
	private Set<AllocateDTO> allocates;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "society")
	private Set<RentSellDTO> rentSell;
	
	
	@Lob
	@Column(name="IMAGE",columnDefinition = "LONGBLOB")
	private byte[] image;

	@Override
	public String getKey() {
		return String.valueOf(id);
	}

	@Override
	public String getValue() {
		return name;
	}

}
