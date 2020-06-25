package com.icia.cheatingday.util.typehandler;

import java.sql.*;
import java.time.*;

import org.apache.ibatis.type.*;

@MappedTypes(LocalDateTime.class)
public class LocalDateTimeTypeHandler extends BaseTypeHandler<LocalDateTime> {
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, LocalDateTime parameter, JdbcType jdbcType) throws SQLException {
		ps.setTimestamp(i, Timestamp.valueOf(parameter));
	}

	@Override
	public LocalDateTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
		Timestamp timestamp = rs.getTimestamp(columnName);
		return getLocalDateTime(timestamp);
	}

	@Override
	public LocalDateTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		Timestamp timestamp = rs.getTimestamp(columnIndex);
		return getLocalDateTime(timestamp);
	}

	@Override
	public LocalDateTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		Timestamp timestamp = cs.getTimestamp(columnIndex);
		return getLocalDateTime(timestamp);
	}

	private static LocalDateTime getLocalDateTime(Timestamp timestamp) {
		if (timestamp != null) {
			return timestamp.toLocalDateTime();
		}
		return null;
	}
}
