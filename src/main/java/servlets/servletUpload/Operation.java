package servlets.servletUpload;

/**
 * Класс Operation
 */
public enum Operation {
	DELETE("delete"), INSERT("insert"), VIEW("view"),
	CREATE_SHEDULE("create"), CREATE_CONTRACT("create_contract");

	private String title;

	Operation(String title) {
		this.title = title;
	}

	/**
	 * Возвращает
	 *
	 * @return
	 */
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
