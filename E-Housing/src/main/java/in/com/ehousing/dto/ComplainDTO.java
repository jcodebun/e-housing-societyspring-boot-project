package in.com.ehousing.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="COMPLAIN")
public class ComplainDTO extends BaseDTO {
	
	@Column(name="SUBJECT",length = 225)
	private String subject;
	@Column(name="COMPLAIN",length = 225)
	private String complain;
	@Column(name="USER_ID")
	private long userId;
	@Column(name="STATUS",length = 225)
	private String status;

	@Override
	public String getKey() {
		return String.valueOf(id);
	}

	@Override
	public String getValue() {
		return subject;
	}

}
