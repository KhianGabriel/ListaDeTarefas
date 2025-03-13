package appweb.data;

public class SelectDataItem {
	private String value;
	private String text;
	private String description;
	private boolean status;

	public SelectDataItem() {
		super();		
	}
	
	public SelectDataItem(String value, String text) {
		super();
		this.value = value;
		this.text = text;
	}
	
	public SelectDataItem(String value, String text, String description) {
		super();
		this.value = value;
		this.text = text;
		this.description = description;
	}
	
	public SelectDataItem(String value, String text, String description, boolean status) {
		super();
		this.value = value;
		this.text = text;
		this.description = description;
		this.status = status;
	}
	
	public SelectDataItem(String value, String text, boolean status) {
		super();
		this.value = value;
		this.text = text;
		this.status = status;
	}

	public String getValue() {
		return value;
	}
	
	public String getId() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}
	
	public String getNome() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}	
}
