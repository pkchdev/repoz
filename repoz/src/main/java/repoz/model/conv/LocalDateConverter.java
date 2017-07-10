package repoz.model.conv;

import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, java.sql.Date> {

	@Override
	public java.sql.Date convertToDatabaseColumn(java.time.LocalDate entityValue) {
		return entityValue == null ? null : java.sql.Date.valueOf(entityValue);
	}

	@Override
	public java.time.LocalDate convertToEntityAttribute(java.sql.Date dbValue) {
		return dbValue == null ? null : dbValue.toLocalDate();
	}

}
