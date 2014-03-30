package br.com.typekboom.model;

public class TranslationObjective {
	
	private String phrase;
	private String translation;
	
	public TranslationObjective() {	}
	
	public TranslationObjective(String phrase, String translation) {
		super();
		this.phrase = phrase;
		this.translation = translation;
	}

	public String getPhrase() {
		return phrase;
	}
	public void setPhrase(String phrase) {
		this.phrase = phrase;
	}
	public String getTranslation() {
		return translation;
	}
	public void setTranslation(String translation) {
		this.translation = translation;
	}
	
}