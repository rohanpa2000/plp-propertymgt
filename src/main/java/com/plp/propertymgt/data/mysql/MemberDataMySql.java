package com.plp.propertymgt.data.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.plp.propertymgt.config.Configuration;
import com.plp.propertymgt.data.MemberData;
import com.plp.propertymgt.model.Member;

public class MemberDataMySql implements MemberData{
	
	static String url = Configuration.DB_URL;
	static String user = Configuration.DB_USER;
	static String pass = Configuration.DB_PWD;
	
	@Override
	public List<Member> getMembers(int tenantId){
		
		List<Member> entities = new ArrayList<Member>();
		
		try(Connection conn = DriverManager.getConnection (url, user, pass);){
		
			PreparedStatement statement = conn.prepareStatement("CALL sp_select_members_by_tenantid(?)");
			
			statement.setInt(1, tenantId);
			
			ResultSet results =  statement.executeQuery();
			
			while (results.next()) {
				Member entity = new Member();
				
				entity.setId(results.getInt("member_id"));
				entity.setTenantId(results.getInt("tenant_id"));
				entity.setName(results.getString("name"));
				entity.setNickName(results.getString("nickname"));
				entity.setDisplayName(results.getString("display_name"));
				entity.setPhone(results.getString("phone"));
				entity.setEmail(results.getString("email"));
				entity.setPostal(results.getString("postal"));
				
				entities.add(entity);
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return entities;
	}
	
	@Override
	public void updateMember(Member entity){
		try(Connection conn = DriverManager.getConnection (url, user, pass);){
		
			PreparedStatement statement = conn.prepareStatement("CALL sp_update_member_by_id_tenantid(?,?,?,?,?,?,?,?)");
			
			statement.setInt(1,entity.getId());
			statement.setInt(2,entity.getTenantId());
			statement.setString(3,entity.getName());
			statement.setString(4,entity.getNickName());
			statement.setString(5,entity.getDisplayName());
			statement.setString(6,entity.getPhone());
			statement.setString(7,entity.getEmail());
			statement.setString(8,entity.getPostal());			
			
			statement.executeUpdate();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void deleteMembers(List<Member> entities, int tenantid){
		try(Connection conn = DriverManager.getConnection (url, user, pass);){
			
			String memberids = "";
			
			for (Member entity : entities)
				memberids += Integer.toString(entity.getId()) + ",";
			
			memberids = memberids.substring(0, memberids.length() - 1);
			
			PreparedStatement statement = conn.prepareStatement("CALL sp_delete_members_by_ids_tenantid(?,?)");
			
			
			statement.setString(1, memberids);
			statement.setInt(1, tenantid);
			
			statement.executeUpdate();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void addMember(Member entity){
		try(Connection conn = DriverManager.getConnection (url, user, pass);){
		
			PreparedStatement statement = conn.prepareStatement("CALL sp_insert_member(?,?,?,?,?,?,?)");
			
			statement.setInt(1,entity.getTenantId());
			statement.setString(2,entity.getName());
			statement.setString(3,entity.getNickName());
			statement.setString(4,entity.getDisplayName());
			statement.setString(5,entity.getPhone());
			statement.setString(6,entity.getEmail());
			statement.setString(7,entity.getPostal());			
			
			statement.executeUpdate();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}
