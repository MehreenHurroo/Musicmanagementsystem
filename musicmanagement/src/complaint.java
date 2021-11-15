
public class complaint {
	private String date,username,complain;

	public complaint(String date, String username, String complain) {
		super();
		this.date = date;
		this.username = username;
		this.complain = complain;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getComplain() {
		return complain;
	}

	public void setComplain(String complain) {
		this.complain = complain;
	}

}
