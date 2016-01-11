package vn.webapp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import vn.webapp.dto.DataPage;
import vn.webapp.model.JurySlot;

public interface JurySlotService {
	List<JurySlot> listJurySlots(String defenseSessionCode, String staffCode);
	List<JurySlot> listJurySlots(String staffCode);
	int saveAJurySlot(int jurySlot_Index, String jurySlotCode, String defenseSessionCode, String userCode);
	public int removeAJurySlot(String userCode, String sJurySlotCode);
}
