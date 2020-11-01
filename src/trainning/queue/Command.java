package trainning.queue;

public class Command implements Comparable<Command> {

	private String type;
	private int priority;
	private String params;

	public Command(String type, int priority, String params) {
		this.type = type;
		this.priority = priority;
		this.params = params;
	}

	@Override
	public int compareTo(Command otherCommand) {
		return otherCommand.priority - priority;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

}
