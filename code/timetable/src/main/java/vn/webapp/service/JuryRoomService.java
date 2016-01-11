package vn.webapp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import vn.webapp.dto.DataPage;
import vn.webapp.model.JuryRoom;

public interface JuryRoomService {
	List<JuryRoom> listJuryRooms(String defenseSessionCode, String staffCode);
	List<JuryRoom> listJuryRooms(String staffCode);
	int saveAJuryRoom(int juryRoom_Index, String juryRoomCode, String defenseSessionCode, String userCode);
	public int removeAJuryRoom(String userCode, String sJuryRoomCode);
}
