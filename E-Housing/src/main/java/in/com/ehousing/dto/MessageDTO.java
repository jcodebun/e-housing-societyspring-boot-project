package in.com.ehousing.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="MESSAGE")
@Getter
@Setter
public class MessageDTO extends BaseDTO {
	
	@Column(name="ALLOCATE_ID")
	private long allocateId;
	@Column(name="NAME",length = 225)
	private String name;
	@Column(name="SUBJECT",length = 225)
	private String subject;
	@Column(name="MESSAGE",length = 225)
	private String message;
	@Column(name="CONTACT_NO",length = 225)
	private String contactNo;
	
	@Column(name="DATE")
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Transient
	private RentSellDTO rent;
	
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
