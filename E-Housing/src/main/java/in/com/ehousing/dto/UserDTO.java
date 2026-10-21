package in.com.ehousing.dto;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USER")
@Getter
@Setter
public class UserDTO extends BaseDTO {

	@Column(name = "FIRST_NAME", length = 225)
	private String firstName;

	@Column(name = "LAST_NAME", length = 225)
	private String lastName;

	@Column(name = "USER_NAME", length = 225)
	private String login;

	@Column(name = "PASSWORD", length = 225)
	private String password;

	private String confirmPassword;


	@Column(name = "ROLE_ID", length = 225)
	private long roleId;




	public String getKey() {
		return id + "";
	}

	public String getValue() {

		return firstName + " " + lastName;
	}

	@Override
	public String toString() {
		return "UserDTO [firstName=" + firstName + ", lastName=" + lastName + ", login=" + login + ", password="
				+ password + ", confirmPassword=" + confirmPassword + ", roleId=" + roleId + "]";
	}
}
