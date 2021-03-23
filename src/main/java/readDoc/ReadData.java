package readDoc;

import model.*;

import java.io.IOException;
import java.sql.Date;
import java.util.*;

public interface ReadData {
	public LinkedList<Date> getDate(int columnIndex);

	public List<String> getString(int columnIndex) throws IOException;

	public List<Integer> getDataInteger(int columnIndex);

	HashSet<GroupInsert> getGroup();

	public HashSet<TeacherInsert> getTeacher();
	List<SheduleInsert> getShedulesSearch();
	public List<GroupInsert> getGroupInsert();
}