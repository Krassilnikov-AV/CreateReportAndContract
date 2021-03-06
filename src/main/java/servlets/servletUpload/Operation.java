package servlets.servletUpload;

public enum Operation {
	DELETE("delete"), INSERT("insert"), VIEW("view"),
	CREATE_SHEDULE("create"), CREATE_CONTRACT("create_contract"),
	SELECT_SHEDULE("data_shedule");

	private String title;

	Operation(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public static Operation fromString(String value) {
		if (value != null) {
			for (Operation operation : Operation.values()) {
				if (value.equalsIgnoreCase(operation.getTitle())) {
					return operation;
				}
			}
		}
		throw new IllegalArgumentException("No such value");
	}
}
