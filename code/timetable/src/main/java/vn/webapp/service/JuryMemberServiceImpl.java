package vn.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.webapp.model.JuryMember;
import vn.webapp.model.RawMasterDefenseJuryThesis;

import java.util.ArrayList;
import java.util.List;

import vn.webapp.dao.JuryMemberDAO;

@Service("JuryMemberService")
public class JuryMemberServiceImpl implements JuryMemberService{
	@Autowired
	private JuryMemberDAO juryMemberDAO;
	
	@Override
	public List<JuryMember> listJuryMembers(String defenseSessionCode, String staffCode){
		try{
			return juryMemberDAO.listJuryMembers(defenseSessionCode, staffCode);
		}catch(Exception e){
			System.out.println("JuryMemberService::listJuryMembers(" + defenseSessionCode + "," + staffCode + "), error = " + e.toString());
			return null;
		}
	}
	@Override
	
	public List<JuryMember> listJuryMembers(String staffCode){
		try{
			return juryMemberDAO.listJuryMembers(staffCode);
		}catch(Exception e){
			System.out.println("JuryMemberService::listJuryMembers(" + staffCode +"), error = " + e.toString());
			return null;
		}
	}
	
	@Override
	public int saveAJuryMember(String juryMemberCode, String defenseSessionCode, String userCode){
		JuryMember juryMember = new JuryMember();
		juryMember.setJuryMem_MemberCode(juryMemberCode);
		juryMember.setJuryMem_DefenseSessionCode(defenseSessionCode);
		juryMember.setJuryMem_StaffCode(userCode);
		juryMember.setJuryMem_Code(defenseSessionCode + "-" + userCode + "-"+juryMemberCode); 
		return juryMemberDAO.saveJuryMember(juryMember);
	}
	
	/**
     * 
     */
    @Override
    public int removeAJuryMember(String userCode, String sJuryMemberCode){
    	try{
    		JuryMember oCheckExistingRecord = juryMemberDAO.getAJuryMemberByCode(sJuryMemberCode, userCode);
    		if(oCheckExistingRecord != null)
    		{
	    		return juryMemberDAO.removeAJuryMemberByCode(oCheckExistingRecord);
    		}
    		return 0;
	}catch(Exception exception)
	{
		System.out.println("Exception: " + exception.getMessage());
		return 0;
	}
    }
	
}
