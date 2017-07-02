package repoz.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Setter
	private int id;

	@Getter
	@Setter
	private String username;

	@Getter
	@Setter
	private String password;

	@Transient
	@Getter
	@Setter
	private String passwordConfirm;

	@ManyToOne
	@JoinColumn(name="id_role")
	@Getter
	@Setter
	private Role role;

}
