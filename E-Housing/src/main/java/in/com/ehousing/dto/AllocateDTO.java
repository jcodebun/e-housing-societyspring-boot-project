package in.com.ehousing.dto;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ALLOCATE")
public class AllocateDTO extends BaseDTO {

	@Column(name = "FIRST_NAME", length = 225)
	private String firstName;
	
	@Column(name = "LAST_NAME", length = 225)
	private String lastName;
	
	@Column(name="EMAIL",length = 225)
	private String email;
	
	@Column(name = "MOBILE_NO", length = 225)
	private String mobileNo;
	
	@Column(name = "DOB")
	@Temporal(TemporalType.DATE)
	private Date dob;
	
	@Column(name = "TOTAL_MEMBER", length = 225)
	private String totalMember;

	private long societyId;
	private long houseId;

	@ManyToOne
	@JoinColumn(name = "SOCIETY", nullable = false)
	private SocietyDTO society;

	@ManyToOne
	@JoinColumn(name = "HOUSE", nullable = false)
	private HouseDTO house;

	@Lob
	@Column(name = "IMAGE", columnDefinition = "LONGBLOB")
	private byte[] image;
	
	
	@Column(name="USER_NAME" ,length = 225)
	private String userName;
	
	@Column(name="PASSWORD" ,length = 225)
	private String password;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "allocate")
	private Set<RentSellDTO> rents;
	
	

	@Override
	public String getKey() {
		return String.valueOf(id);
	}

	@Override
	public String getValue() {
		return firstName + " " + lastName;
	}

}
