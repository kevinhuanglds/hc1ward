package org.lds.hcstake.hc1ward.dal;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class MemberInfo {
	
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

	@Persistent
	private String recNo;
	
	@Persistent
	private String name;
	
	@Persistent
	private String birthday;
	
	@Persistent
	private String gender;
	
	@Persistent
	private int age;
	
	@Persistent
	private String home_phone;
	
	@Persistent
	private String cell_phone;
	
	@Persistent
	private String address;
	
	@Persistent
	private String pristhood;

	@Persistent
	private boolean active;
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getPristhood() {
		return pristhood;
	}

	public void setPristhood(String pristhood) {
		this.pristhood = pristhood;
	}

	public MemberInfo(String recNo, String name) {
		this.recNo = recNo;
		this.name = name;
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getRecNo() {
		return recNo;
	}

	public void setRecNo(String recNo) {
		this.recNo = recNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getHome_phone() {
		return home_phone;
	}

	public void setHome_phone(String home_phone) {
		this.home_phone = home_phone;
	}

	public String getCell_phone() {
		return cell_phone;
	}

	public void setCell_phone(String cell_phone) {
		this.cell_phone = cell_phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String toJSON() {
		String result = String.format("{'key' : '%s', 'recNo' : '%s', 'name' : '%s', 'gender' : '%s', 'age' : %i, 'home_phone' : '%s', 'cell_phone' : '%s', 'address' : '%s', 'pristhood' : '%s'}", 
				this.key, this.recNo, this.name, this.gender,this.age, this.home_phone, this.cell_phone, this.address, this.pristhood);
		return result ;
	}
	
}
