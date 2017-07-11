package repoz.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="car")
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "car_id", nullable=false)
	@Getter
	@Setter
	private Long id;

	@Column(name = "car_maker", nullable=false)
	@Getter
	@Setter
	private String maker;
	
	@Column(name = "car_model", nullable=false)
	@Getter
	@Setter
	private String model;
	
	@Column(name = "car_date", nullable=false)
	@Getter
	@Setter
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	
	@Column(name = "car_picture")
	@Getter
	@Setter
	private Byte[] picture;
	
}
