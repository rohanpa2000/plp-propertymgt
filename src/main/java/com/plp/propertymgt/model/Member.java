package com.plp.propertymgt.model;

import com.google.gson.internal.LinkedTreeMap;

public class Member {
	
	private int id;
	private int tenantid;
	private String name;
	private String nickName;
	private String displayName;
	private String phone;
	private String email;
	private String postal;
	
	@Override
    public String toString() {
    	
        return  "id: " + id + "\r" +
        		"tenantid: " + tenantid + "\r" +
                "name: " + name + "\r" +
                "nickName: " + nickName  + "\r" +
                "displayName: " + displayName + "\r" +
                "phone: " + phone + "\r" +
                "email: " + email +"\r" +
        		"postal: " + postal +"\r";
    }
	
	public static Member getFromLinkedTreeMap(LinkedTreeMap<?,?> linkedMap){
		
		Member member = new Member();
		
		try{
			
			System.out.println(linkedMap);
			
			member.setId((int) Double.parseDouble((linkedMap.get("id").toString())));
			member.setTenantId((int) Double.parseDouble((linkedMap.get("tenantid").toString())));
			member.setName((String) linkedMap.get("name"));
			member.setNickName((String) linkedMap.get("nickName"));
			member.setDisplayName((String) linkedMap.get("displayName"));
			member.setPhone((String) linkedMap.get("phone"));
			member.setEmail((String) linkedMap.get("email"));
			member.setPostal((String) linkedMap.get("postal"));
		}
		catch (Exception e){
			e.printStackTrace();
		}
    	
		return member;
	}

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getTenantId() {
		return tenantid;
	}
	public void setTenantId(int tenantid) {
		this.tenantid = tenantid;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPostal() {
		return postal;
	}
	public void setPostal(String postal) {
		this.postal = postal;
	}
}
