package tcpserver;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ReservationDTO implements Serializable {
	private String id;
	private String title;
	private String date;

	public ReservationDTO(String id, String title, String date) {
		super();
		this.id = id;
		this.title = title;
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
