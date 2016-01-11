package vn.webapp.dao;

import vn.webapp.model.JuryRoom;
import vn.webapp.model.JurySlot;

import java.util.List;

public interface JurySlotDAO {
	public List<JurySlot> listJurySlots(String defenseSessionCode, String staffCode);
	public JurySlot getJurySlotByCode(String sJurySlotCode, String defenseSessionCode, String userCode);
	public JurySlot getJurySlotByUserCode(String sJurySlotCode, String userCode);
	public List<JurySlot> listJurySlots(String staffCode);
	public int saveJurySlot(JurySlot jurySlot);
	public int removeAJurySlot(JurySlot jurySlot);
}
