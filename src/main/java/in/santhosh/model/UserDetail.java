package in.santhosh.model;

public class UserDetail {
	public UserDetail()
	{
		
	}
	private int id;
	private String name;
	private int age;
	private String gender;
	private long mobileNumber;
	private String password;
	private String reTypePassword;
	
	public int getId() {
		return id;
	}
	public String getReTypePassword() {
		return reTypePassword;
	}
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	public String getGender() {
		return gender;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public String getPassword() {
		return password;
	}
	public UserDetail(String name, int age, String gender, long mobileNumber, String password,
			String reTypePassword) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.mobileNumber = mobileNumber;
		this.password = password;
		this.reTypePassword = reTypePassword;
	}
	public UserDetail(int id, String name, int age, String gender, long mobileNumber, String password,
			String reTypePassword) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.mobileNumber = mobileNumber;
		this.password = password;
		this.reTypePassword = reTypePassword;
	}
	

	

}
 
