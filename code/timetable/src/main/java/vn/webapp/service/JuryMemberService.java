package vn.webapp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import vn.webapp.dto.DataPage;
import vn.webapp.model.JuryMember;

public interface JuryMemberService {
	List<JuryMember> listJuryMembers(String defenseSessionCode, String staffCode);
	List<JuryMember> listJuryMembers(String staffCode);
	int saveAJuryMember(String juryMemberCode, String defenseSessionCode, String userCode);
	public int removeAJuryMember(String userCode, String sJuryMemberCode);
}
