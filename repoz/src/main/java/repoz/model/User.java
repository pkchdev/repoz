package repoz.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
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
import repoz.model.conv.LocalDateTimeConverter;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Setter
	@Column(name = "use_id")
	private Long id;

	@Getter
	@Setter
	@Column(name = "use_username")
	private String username;

	@Getter
	@Setter
	@Column(name = "use_password")
	private String password;

	@Transient
	@Getter
	@Setter
	private String passwordConfirm;
	
	@Getter
	@Setter
	@Column(name = "use_creation_date", insertable=false)
	private LocalDateTime creationDate;
	
	@ManyToOne
	@JoinColumn(name = "use_rol_id")
	@Getter
	@Setter
	private Role role;

}
