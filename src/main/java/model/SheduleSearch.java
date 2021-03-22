package model;


public class SheduleSearch {
	private String id;
	private final String group;
	private final String pro;
	private String dateStart;
	private String timeStart;
	private String dateFinish;
	private String timeFinish;
	private String audit;
	private final String type;
	private final String tech;


	public SheduleSearch(String group, String pro,
						 String dateStart, String timeStart,
						 String dateFinish, String timeFinish,
						 String audit, String type, String tech) {
		this.group = group;
		this.pro = pro;
		this.dateStart = dateStart;
		this.timeStart = timeStart;
		this.dateFinish = dateFinish;
		this.timeFinish = timeFinish;
		this.audit = audit;
		this.type = type;
		this.tech = tech;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getGroup() {
		return group;
	}

	public String getPro() {
		return pro;
	}

	/**
	 * Возвращает
	 *
	 * @return
	 */
	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getTimeStart() {
		return timeStart;
	}


	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}


	public String getDateFinish() {
		return dateFinish;
	}


	public void setDateFinish(String dateFinish) {
		this.dateFinish = dateFinish;
	}


	public String getTimeFinish() {
		return timeFinish;
	}

	public void setTimeFinish(String timeFinish) {
		this.timeFinish = timeFinish;
	}


	public String getAudit() {
		return audit;
	}

	public void setAudit(String audit) {
		this.audit = audit;
	}

	public String getType() {
		return type;
	}


	public String getTech() {
		return tech;
	}

	@Override
	public String toString() {
		return "SheduleSearch{" +
			"group='" + group + '\'' +
			", pro='" + pro + '\'' +
			", dateStart='" + dateStart + '\'' +
			", timeStart='" + timeStart + '\'' +
			", dateFinish='" + dateFinish + '\'' +
			", timeFinish='" + timeFinish + '\'' +
			", audit='" + audit + '\'' +
			", type='" + type + '\'' +
			", tech='" + tech + '\'' +
			'}';
	}
}