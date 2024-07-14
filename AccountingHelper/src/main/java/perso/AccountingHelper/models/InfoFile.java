package perso.AccountingHelper.models;

public class InfoFile {
	String path;
	String name;
	String content;
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void addContent(String line) {
		this.content += line;
	}	
}
