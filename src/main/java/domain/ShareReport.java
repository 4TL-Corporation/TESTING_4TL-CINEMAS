package domain;

import java.util.Date;

public class ShareReport {
	private String username;
	private String videoID;
	private String receiverMail;
	private Date sentDate;
	
	public ShareReport() {
		
	}

	public ShareReport(String username, String videoID, String receiverMail, Date sentDate) {
		this.username = username;
		this.videoID = videoID;
		this.receiverMail = receiverMail;
		this.sentDate = sentDate;
	}

	public String getusername() {
		return username;
	}

	public void setusername(String username) {
		this.username = username;
	}

	public String getvideoID() {
		return videoID;
	}

	public void setvideoID(String videoID) {
		this.videoID = videoID;
	}

	public String getReceiverMail() {
		return receiverMail;
	}

	public void setReceiverMail(String receiverMail) {
		this.receiverMail = receiverMail;
	}

	public Date getSentDate() {
		return sentDate;
	}

	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}
	
	
	
}
