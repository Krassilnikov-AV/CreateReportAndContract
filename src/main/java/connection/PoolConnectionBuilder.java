/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package connection;


import javax.naming.*;
import javax.sql.DataSource;
import java.sql.*;

/**
 * Класс ConnectionPool
 */
public class PoolConnectionBuilder implements ConnectionBuilder  {
//	private static final Logger logger = LoggerFactory.getLogger(PoolConnectionBuilder.class);
	private DataSource dataSource;
	public PoolConnectionBuilder() {
		try {
			Context ctx =new InitialContext();
			dataSource=(DataSource) ctx.lookup("java:comp/env/jdbc/raspisanie");
		} catch (NamingException e) {
			System.out.println(e);
//			logger.error("", e);
		}
	}
	@Override
	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
}