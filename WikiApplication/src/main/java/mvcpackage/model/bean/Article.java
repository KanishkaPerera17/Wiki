package mvcpackage.model.bean;

public class Article {

	private int Aid;
	private String Atitle;
	private String Acategory;
	private String Acontent;
	private Boolean Apublic;
	private String Acreated_at;
	
	public Article() {}
	
	public Article(int aid, Boolean apublic) {
		Aid = aid;
		Apublic = apublic;
	}
	
	public Article(int aid, String atitle, String acategory, String acontent, String acreated_at) {
		Aid = aid;
		Atitle = atitle;
		Acategory = acategory;
		Acontent = acontent;
		Acreated_at = acreated_at;
	}
	
	public Article(String atitle, String acategory, String acontent, Boolean apublic) {
		Atitle = atitle;
		Acategory = acategory;
		Acontent = acontent;
		Apublic = apublic;
	}
	
	public Article(int aid, String atitle, String acategory, String acontent, Boolean apublic) {
		Aid = aid;
		Atitle = atitle;
		Acategory = acategory;
		Acontent = acontent;
		Apublic = apublic;
	}
	
	public Article(int aid, String atitle, String acategory, String acontent, Boolean apublic, String acreated_at) {
		Aid = aid;
		Atitle = atitle;
		Acategory = acategory;
		Acontent = acontent;
		Apublic = apublic;
		Acreated_at = acreated_at;
	}
	
	public int getAid() {
		return Aid;
	}

	public void setAid(int aid) {
		Aid = aid;
	}

	public String getAtitle() {
		return Atitle;
	}

	public void setAtitle(String atitle) {
		Atitle = atitle;
	}

	public String getAcategory() {
		return Acategory;
	}

	public void setAcategory(String acategory) {
		Acategory = acategory;
	}

	public String getAcontent() {
		return Acontent;
	}

	public void setAcontent(String acontent) {
		Acontent = acontent;
	}
	
	public Boolean getApublic() {
		return Apublic;
	}

	public void setApublic(Boolean apublic) {
		Apublic = apublic;
	}

	public String getAcreated_at() {
		return Acreated_at;
	}

	public void setAcreated_at(String acreated_at) {
		Acreated_at = acreated_at;
	}
	
}
