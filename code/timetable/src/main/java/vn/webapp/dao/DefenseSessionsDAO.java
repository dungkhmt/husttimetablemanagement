/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.dao;

import java.util.List;

import vn.webapp.model.DefenseSession;

public interface DefenseSessionsDAO {

	public List<DefenseSession> getListDefenseSession();
	
	public List<DefenseSession> getListAllDefenseSession();
	
	public void editADefenseSession(DefenseSession defenseSession);
    
    public int saveADefenseSession(DefenseSession defenseSession);
    
    public int removeADefenseSession(int iDefenseSessionId);
    
    public DefenseSession getDefenseSessionById(int iDefenseSessionId);
}
