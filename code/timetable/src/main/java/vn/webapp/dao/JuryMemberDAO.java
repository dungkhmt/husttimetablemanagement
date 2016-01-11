package vn.webapp.dao;

import vn.webapp.model.JuryMember;
import vn.webapp.model.RawMasterDefenseJuryThesis;

import java.util.List;

public interface JuryMemberDAO {
	List<JuryMember> listJuryMembers(String defenseSessionCode, String staffCode);
	
	JuryMember getAJuryMemberByCode(String sJuryMemberCode, String sStaffCode);
	
	List<JuryMember> listJuryMembers(String staffCode);
	
	int saveJuryMember(JuryMember juryMember);
	
	public int removeAJuryMemberByCode(JuryMember aJuryMember);
}
