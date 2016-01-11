/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import vn.webapp.dto.DataPage;
import vn.webapp.model.DefenseSession;
import vn.webapp.model.Faculty;
import vn.webapp.model.MasterClass;
import vn.webapp.model.Student;

public interface DefenseSessionService {

	public List<DefenseSession> listDefenseSession();
	
	public List<DefenseSession> getListAllDefenseSession();
	
    public void editADefenseSession(int iDefenseSessionId, String sDefenseSessionCode, String sDefenseSessionName, int iActive);
    
    public int saveADefenseSession(String sDefenseSessionCode, String sDefenseSessionName, int iActive);
    
    public int removeADefenseSession(int iDefenseSessionID);
    
    public DefenseSession getDefenseSessionById(int iDefenseSessionID);
}
