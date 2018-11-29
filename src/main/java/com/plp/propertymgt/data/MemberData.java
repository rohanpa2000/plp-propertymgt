package com.plp.propertymgt.data;

import java.util.List;

import com.plp.propertymgt.model.Member;

public interface MemberData {
	
	 List<Member> getMembers(int tenantId);
	 void updateMember(Member entity);
	 void deleteMembers(List<Member> entities, int tenantid);
	 void addMember(Member entity);

}
