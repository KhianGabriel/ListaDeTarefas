package appweb.util;

public class MessageAlert {

	public final static int MSG_CAMPO = 0;
	public final static int MSG_DADOS = 1;
	public final static int MSG_OUTROS = 2;
	public final static int INFO = 1;
	public final static int WARNING = 2;
	public final static int ERROR = 3;

	private int typeMessage = 0; // Codigo da Mensagem.
	// 0 - Mensagem de campo.
	// 1 - Mensagem de manipulação de dados.
	// 2 - Outros Mensagem.
	private String fieldName = "";
	private String labelText = "";
	private String message = "";

	public MessageAlert(int typeMessage, String fieldName, String labelText, String mensage) {
		setTypeMessage(typeMessage);
		setFieldName(fieldName);
		setLabelText(labelText);
		setMessage(mensage);
	}

	public MessageAlert(int typeMessage, String labelText, String mensage) {
		setTypeMessage(typeMessage);
		setLabelText(labelText);
		setMessage(mensage);
	}

	public MessageAlert(int typeMessage, String mensage) {
		setTypeMessage(typeMessage);
		setMessage(mensage);
	}

	public MessageAlert(String mensage) {
		setTypeMessage(MSG_DADOS);
		setMessage(mensage);
	}

	public MessageAlert(String fieldName, String mensage) {
		setFieldName(fieldName);
		setMessage(mensage);
	}

	public int getTypeMessage() {
		return typeMessage;
	}

	public void setTypeMessage(int typeMessage) {
		this.typeMessage = typeMessage;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getLabelText() {
		return labelText;
	}

	public void setLabelText(String labelText) {
		this.labelText = labelText;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String toString() {
		return typeMessage + " - fieldName: " + fieldName + ", - labelText: " + labelText + ", - message: " + message;
	}

}
