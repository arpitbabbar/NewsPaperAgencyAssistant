package BillHistory;

public class UserBean {
	String billid,mob,nod,dob,amount;
	public UserBean() {}
	public UserBean(String billid, String mob, String nod, String dob, String amount) {
		super();
		this.billid = billid;
		this.mob = mob;
		this.nod = nod;
		this.dob = dob;
		this.amount = amount;
	}
	public String getBillid() {
		return billid;
	}
	public void setBillid(String billid) {
		this.billid = billid;
	}
	public String getMob() {
		return mob;
	}
	public void setMob(String mob) {
		this.mob = mob;
	}
	public String getNod() {
		return nod;
	}
	public void setNod(String nod) {
		this.nod = nod;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
}
