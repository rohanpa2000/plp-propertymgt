package com.plp.propertymgt.business;

import java.util.List;

import com.plp.propertymgt.data.MemberData;
import com.plp.propertymgt.data.mysql.MemberDataMySql;
import com.plp.propertymgt.model.Member;
import com.plp.propertymgt.utility.Helper;

public class MemberBusiness {
	
	MemberData memberData = new MemberDataMySql();
	
	 public List<Member> getMembers(int tenantId){
		 return memberData.getMembers(tenantId);
	 }
	 
	 public void updateMember(Member member){
		 
		 if (!member.getNickName().trim().equals("") && member.getDisplayName().trim().equals("")){
			 
			 String sourceText = member.getNickName().trim();
			 
			 try {
				String sinText = Helper.getTranslatedText(sourceText, "en", "si");
				String taText = Helper.getTranslatedText(sourceText, "en", "ta");
				
				member.setDisplayName(sinText + " - " + taText);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 
		 memberData.updateMember(member);		 
	 }
	 
	 public void deleteMembers(List<Member> entities, int tenantid){
		 memberData.deleteMembers(entities, tenantid);
	 }
	 
	 public void addMember(Member entity){
		 memberData.addMember(entity);
	 }
}
