/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.webapp.dao.JournalDAO;
import vn.webapp.model.Journal;

@Service("journalService")
public class JournalServiceImpl implements JournalService{

    @Autowired
    private JournalDAO journalDAO;

    public void setJournalDAO(JournalDAO journalDAO) {
        this.journalDAO = journalDAO;
    }
    
    /**
     * Get all list Paper Category
     * @param null
     * @return List
     */
    @Override
    public List<Journal> list(){
    	return journalDAO.getList();
    }
}
