package com.plp.propertymgt.business;

import java.util.List;

import com.plp.propertymgt.data.MemberData;
import com.plp.propertymgt.data.mysql.MemberDataMySql;
import com.plp.propertymgt.model.Member;

public class MemberBusiness {
	
	MemberData memberData = new MemberDataMySql();
	
	 public List<Member> getMembers(int tenantId){
		 return memberData.getMembers(tenantId);
	 }
	 
	 public void updateMember(Member entity){
		 memberData.updateMember(entity);		 
	 }
	 
	 public void deleteMembers(List<Member> entities, int tenantid){
		 memberData.deleteMembers(entities, tenantid);
	 }
	 
	 public void addMember(Member entity){
		 memberData.addMember(entity);
	 }
}
