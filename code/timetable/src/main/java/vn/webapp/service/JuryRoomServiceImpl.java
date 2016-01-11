package vn.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.webapp.model.JuryMember;
import vn.webapp.model.JuryRoom;

import java.util.ArrayList;
import java.util.List;

import vn.webapp.dao.JuryRoomDAO;

@Service("JuryRoomService")
public class JuryRoomServiceImpl implements JuryRoomService{
	@Autowired
	private JuryRoomDAO juryRoomDAO;
	
	@Override
	public List<JuryRoom> listJuryRooms(String defenseSessionCode, String staffCode){
		try{
			return juryRoomDAO.listJuryRooms(defenseSessionCode, staffCode);
		}catch(Exception e){
			System.out.println("JuryRoomService::listJuryRooms(" + defenseSessionCode + "," + staffCode + "), error = " + e.toString());
			return null;
		}
	}
	@Override
	
	public List<JuryRoom> listJuryRooms(String staffCode){
		try{
			return juryRoomDAO.listJuryRooms(staffCode);
		}catch(Exception e){
			System.out.println("JuryRoomService::listJuryRooms(" + staffCode +"), error = " + e.toString());
			return null;
		}
	}
	
	@Override
	public int saveAJuryRoom(int juryRoom_Index, String juryRoomCode, String defenseSessionCode, String userCode){
		
		JuryRoom aJuryRoom = juryRoomDAO.getJuryRoomByCode(juryRoomCode, defenseSessionCode, userCode);
		if(aJuryRoom == null)
		{
			JuryRoom juryRoom = new JuryRoom();
			juryRoom.setJuryRoom_Code(juryRoomCode);
			juryRoom.setJuryRoom_DefenseSessionCode(defenseSessionCode);
			juryRoom.setJuryRoom_StaffCode(userCode);
			juryRoom.setJuryRoom_Index(juryRoom_Index); 
			return juryRoomDAO.saveJuryRoom(juryRoom);
		}
		return 0;
	}
	
	/**
     * 
     */
    @Override
    public int removeAJuryRoom(String userCode, String sJuryRoomCode){
    	try{
    		JuryRoom oCheckExistingRecord = juryRoomDAO.getJuryRoomByUserCode(sJuryRoomCode, userCode);
    		if(oCheckExistingRecord != null)
    		{
	    		return juryRoomDAO.removeAJuryRoomByCode(oCheckExistingRecord);
    		}
    		return 0;
		}catch(Exception exception)
		{
			System.out.println("Exception: " + exception.getMessage());
			return 0;
		}
    }
	
}
