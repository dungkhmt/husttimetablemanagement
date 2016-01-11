/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.webapp.dao.DefenseSessionsDAO;
import vn.webapp.dao.StudentDAO;
import vn.webapp.model.DefenseSession;

@Service("DefenseSessionsService")
public class DefenseSessionServiceImpl implements DefenseSessionService {

	@Autowired
	private StudentDAO StudentDAO;

	@Autowired
	private DefenseSessionsDAO defenseSessionsDAO;

	/**
	 * Get active list
	 * 
	 * @param String
	 * @return object
	 */
	@Override
	public List<DefenseSession> listDefenseSession() {
		try {
			return defenseSessionsDAO.getListDefenseSession();
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}

	/**
	 * Get all list
	 * 
	 * @param String
	 * @return object
	 */
	@Override
	public List<DefenseSession> getListAllDefenseSession() {
		try {
			return defenseSessionsDAO.getListAllDefenseSession();
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}

	/**
	 * 
	 */
	@Override
	public void editADefenseSession(int iDefenseSessionId, String sDefenseSessionCode, String sDefenseSessionName, int iActive){
		DefenseSession defenseSession = defenseSessionsDAO.getDefenseSessionById(iDefenseSessionId);
		if (defenseSession != null) {
			defenseSession.setDEFSESS_Code(sDefenseSessionCode);
			defenseSession.setDEFSESS_Name(sDefenseSessionName);
			defenseSession.setDEFSESS_Enabled(iActive);
			defenseSessionsDAO.editADefenseSession(defenseSession);
			return;
		} else
			return;
	}

	/**
	 * 
	 */
	@Override
	public int saveADefenseSession(String sDefenseSessionCode, String sDefenseSessionName, int iActive){
		DefenseSession defenseSession = new DefenseSession();
		defenseSession.setDEFSESS_Code(sDefenseSessionCode);
		defenseSession.setDEFSESS_Name(sDefenseSessionName);
		defenseSession.setDEFSESS_Enabled(iActive);

    	return defenseSessionsDAO.saveADefenseSession(defenseSession);
	}

	/**
	 * 
	 */
	@Override
	public int removeADefenseSession(int iDefenseSessionID) {
		return defenseSessionsDAO.removeADefenseSession(iDefenseSessionID);
	}

	/**
	 * 
	 * @return
	 */
	@Override
	public DefenseSession getDefenseSessionById(int iDefenseSessionID)
	{
		return defenseSessionsDAO.getDefenseSessionById(iDefenseSessionID);
	}
}
