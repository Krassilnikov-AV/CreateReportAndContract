package readDoc;

import java.sql.Date;
import java.util.*;

/**
 * Класс ReadData
 */
public interface ReadData {
	public LinkedList<Date> getDate(int columnIndex);
	public List<String> getString(int columnIndex);
}