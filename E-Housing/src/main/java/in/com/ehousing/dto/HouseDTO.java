package in.com.ehousing.dto;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="HOUSE")
@Getter
@Setter
public class HouseDTO extends BaseDTO {
	
	@Column(name="NAME",length = 225)
	private String name;
	
	@ManyToOne
	@JoinColumn(name="SOCIETY_ID",nullable = false)
	private SocietyDTO society;
	
	@Column(name="BLOCK_NO",length = 225)
	private String blockNo;
	
	@Column(name="HOUSE_TYPE",length = 225)
	private String houseType;
	
	@Column(name="DETAIL",length = 225)
	private String detail;
	
	@Transient
	private long societyId;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "house")
	private Set<AllocateDTO> allocates;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "house")
	private Set<RentSellDTO> rents;
	
	
	
	@Override
	public String getKey() {
		return String.valueOf(id);
	}
	@Override
	public String getValue() {
		return name;
	}
	
}
