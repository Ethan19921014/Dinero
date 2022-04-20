package tw.dinero.model;

import java.sql.Timestamp;

public class MessageBean {

	private int messageId;
	private String messagetitle;
	private String message;
	private Timestamp messagetime;
	private String pic;
	private String response;
	private Timestamp responsetime;
	
	
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public int getMessageId() {
		return messageId;
	}
	public String getMessagetitle() {
		return messagetitle;
	}
	
	public void setMessagetitle(String messagetitle) {
		this.messagetitle = messagetitle;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Timestamp getMessagetime() {
		return messagetime;
	}
	public void setMessagetime(Timestamp messagetime) {
		this.messagetime = messagetime;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public Timestamp getResponsetime() {
		return responsetime;
	}
	public void setResponsetime(Timestamp responsetime) {
		this.responsetime = responsetime;
	}

}	
