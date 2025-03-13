package appweb.util;

public class Message {
	
	private String message;
	private String field;
	private int type = 1;
	
	public final static String NOT_NULL = "O campo '%s' é obrigatório!!";
	public final static String INVALID_FIELD = "O campo '%s' posssui o valor invalido!";
	
	public Message(String message, String field) {
		super();
		this.message = message;
		this.field = field;
	}

	public Message(String message, String field, int type) {
		super();
		this.message = message;
		this.field = field;
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
}
