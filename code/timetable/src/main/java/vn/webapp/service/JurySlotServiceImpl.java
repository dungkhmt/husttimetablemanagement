package vn.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.webapp.model.JuryRoom;
import vn.webapp.model.JurySlot;

import java.util.ArrayList;
import java.util.List;

import vn.webapp.dao.JurySlotDAO;

@Service("JurySlotService")
public class JurySlotServiceImpl implements JurySlotService{
	@Autowired
	private JurySlotDAO jurySlotDAO;
	
	@Override
	public List<JurySlot> listJurySlots(String defenseSessionCode, String staffCode){
		try{
			return jurySlotDAO.listJurySlots(defenseSessionCode, staffCode);
		}catch(Exception e){
			System.out.println("JurySlotService::listJurySlots(" + defenseSessionCode + "," + staffCode + "), error = " + e.toString());
			return null;
		}
	}
	@Override
	
	public List<JurySlot> listJurySlots(String staffCode){
		try{
			return jurySlotDAO.listJurySlots(staffCode);
		}catch(Exception e){
			System.out.println("JurySlotService::listJurySlots(" + staffCode +"), error = " + e.toString());
			return null;
		}
	}
	
	@Override
	public int saveAJurySlot(int jurySlot_Index, String jurySlotCode, String defenseSessionCode, String userCode){
		JurySlot aJurySlot = jurySlotDAO.getJurySlotByCode(jurySlotCode, defenseSessionCode, userCode);
		if(aJurySlot == null)
		{
			JurySlot jurySlot = new JurySlot();
			jurySlot.setJurySlot_Code(jurySlotCode);
			jurySlot.setJurySlot_DefenseSessionCode(defenseSessionCode);
			jurySlot.setJurySlot_StaffCode(userCode);
			jurySlot.setJurySlot_Index(jurySlot_Index); 
			return jurySlotDAO.saveJurySlot(jurySlot);
		}
		return 0;
	}
	
	@Override
	public int removeAJurySlot(String userCode, String sJurySlotCode)
	{
		try{
			JurySlot oCheckExistingRecord = jurySlotDAO.getJurySlotByUserCode(sJurySlotCode, userCode);
    		if(oCheckExistingRecord != null)
    		{
	    		return jurySlotDAO.removeAJurySlot(oCheckExistingRecord);
    		}
    		return 0;
		}catch(Exception exception)
		{
			System.out.println("Exception: " + exception.getMessage());
			return 0;
		}
	}
	
}
