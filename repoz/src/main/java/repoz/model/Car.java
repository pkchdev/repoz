package repoz.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="car")
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "car_id")
	@Getter
	@Setter
	private Long id;

	@Column(name = "car_maker")
	@Getter
	@Setter
	private String maker;
	
	@Column(name = "car_model")
	@Getter
	@Setter
	private String model;
	
	@Column(name = "car_date")
	@Getter
	@Setter
	private LocalDate date;
	
	@Column(name = "car_picture")
	@Getter
	@Setter
	private Byte[] picture;
	
}
