package CustomerDisplayBoard;

public class UserBean {

	String name,mobile,address,paper,hawker,dof;
	public UserBean() {}
	public UserBean(String name, String mobile, String address, String paper, String hawker, String dof) {
		super();
		this.name = name;
		this.mobile = mobile;
		this.address = address;
		this.paper = paper;
		this.hawker = hawker;
		this.dof = dof;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPaper() {
		return paper;
	}
	public void setPaper(String paper) {
		this.paper = paper;
	}
	public String getHawker() {
		return hawker;
	}
	public void setHawker(String hawker) {
		this.hawker = hawker;
	}
	public String getDof() {
		return dof;
	}
	public void setDof(String dof) {
		this.dof = dof;
	}
	
	
}
