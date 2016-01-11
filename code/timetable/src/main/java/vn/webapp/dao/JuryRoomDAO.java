package vn.webapp.dao;

import vn.webapp.model.JuryMember;
import vn.webapp.model.JuryRoom;

import java.util.List;

public interface JuryRoomDAO {
	List<JuryRoom> listJuryRooms(String defenseSessionCode, String staffCode);
	JuryRoom getJuryRoomByCode(String sJuryRoomCode, String defenseSessionCode, String userCode);
	JuryRoom getJuryRoomByUserCode(String sJuryRoomCode, String userCode);
	List<JuryRoom> listJuryRooms(String staffCode);
	int saveJuryRoom(JuryRoom juryRoom);
	public int removeAJuryRoomByCode(JuryRoom aJuryRoom);
}
