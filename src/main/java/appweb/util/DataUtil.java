package appweb.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

public class DataUtil {
	public static List<Map<String, String>> getData(ResultSet rs) {
		List<Map<String, String>> list = new ArrayList<>();
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			int numcols = rsmd.getColumnCount();

			while (rs.next()) {
				Map<String, String> map = new HashMap<>();

				for (int i = 1; i <= numcols; i++) {
					map.put(rsmd.getColumnName(i).toLowerCase(), rs.getString(i));
				}
				list.add(map);
			}
			close(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void close(ResultSet o) {
		try {
			if (o != null)
				o.close();
		} catch (Exception e) {
			// e.printStackTrace();
		}
	}

	public static void close(Statement o) {
		try {
			if (o != null)
				o.close();
		} catch (Exception e) {
			// e.printStackTrace();
		}
	}

	public static void close(PreparedStatement o) {
		try {
			if (o != null)
				o.close();
		} catch (Exception e) {
			// e.printStackTrace();
		}
	}

	public static void close(CallableStatement o) {
		try {
			if (o != null)
				o.close();
		} catch (Exception e) {
			// e.printStackTrace();
		}
	}

	public static void close(Connection o) {
		try {
			if (o != null)
				o.close();
		} catch (Exception e) {
			// e.printStackTrace();
		}
	}

	public static void close(Session o) {
		try {
			if (o != null)
				o.close();
		} catch (Exception e) {
			// e.printStackTrace();
		}
	}
}
