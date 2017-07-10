package repoz.model.conv;

import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, java.sql.Timestamp> {

	@Override
	public java.sql.Timestamp convertToDatabaseColumn(java.time.LocalDateTime entityValue) {
		return entityValue == null ? null : java.sql.Timestamp.valueOf(entityValue);
	}

	@Override
	public java.time.LocalDateTime convertToEntityAttribute(java.sql.Timestamp dbValue) {
		return dbValue == null ? null : dbValue.toLocalDateTime();
	}
}
