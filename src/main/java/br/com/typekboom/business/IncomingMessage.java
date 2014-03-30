package br.com.typekboom.business;

public class IncomingMessage {
	
	private String type;
	private String word;
	
	public IncomingMessage() {	}
	
	@Override
	public String toString() {
		return "IncomingMessage [type=" + type + ", word=" + word + "]";
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	
}