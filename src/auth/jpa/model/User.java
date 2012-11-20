package auth.jpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mysql.jdbc.Statement;

@Entity
@Table(name="USER")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	int id;
	String name;
	String username;
	String lastname;
	String password;
	public User() {
		
	}
	public User(String username,String password,String name,String lastname) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.lastname = lastname;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
}
