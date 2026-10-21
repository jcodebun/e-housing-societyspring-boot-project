package in.com.ehousing.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="RENT")
public class RentSellDTO extends BaseDTO {
	
	@ManyToOne
	@JoinColumn(name = "HOUSE", nullable = false)
	private HouseDTO house;
	
	
	@Column(name = "PRICE",length = 225)
	private String price;
	
	
	@ManyToOne
	@JoinColumn(name = "ALLOCATE", nullable = false)
	private AllocateDTO allocate;
	
	@Column(name = "TYPE",length = 225)
	private String type;
	
	
	@ManyToOne
	@JoinColumn(name = "SOCIETY", nullable = false)
	private SocietyDTO society;
	
	
	private long societyId;
	
	

	@Override
	public String getKey() {
		return null;
	}

	@Override
	public String getValue() {
		return null;
	}

}
