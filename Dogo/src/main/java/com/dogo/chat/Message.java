package com.dogo.chat;


import javax.persistence.*; //use everything in the persistence libarary
//a plain o java object
//annotations above the id property

@Entity
@Table(name="message") //over the entire class

public class Message {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer Id;
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
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
	private String name;
	private String content;

}
