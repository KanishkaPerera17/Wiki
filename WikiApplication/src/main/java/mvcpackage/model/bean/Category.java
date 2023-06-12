package mvcpackage.model.bean;

public class Category {
	
	
	protected int Cid;
    protected String Cname;
    protected String Cdescription;
    
    public Category() {}
    
    public Category(String cname, String cdescription) {
		Cname = cname;
		Cdescription = cdescription;
	}
    
    public Category(int cid, String cname, String cdescription) {
		Cid = cid;
		Cname = cname;
		Cdescription = cdescription;
	}
    
	public int getCid() {
		return Cid;
	}
	public void setCid(int cid) {
		Cid = cid;
	}
	public String getCname() {
		return Cname;
	}
	public void setCname(String cname) {
		Cname = cname;
	}
	public String getCdescription() {
		return Cdescription;
	}
	public void setCdescription(String cdescription) {
		Cdescription = cdescription;
	}

}
