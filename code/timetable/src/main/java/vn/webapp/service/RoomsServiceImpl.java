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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.webapp.dao.MasterClassDAO;
import vn.webapp.dao.DepartmentDAO;
import vn.webapp.dao.RoomsDAO;
import vn.webapp.dto.DataPage;
import vn.webapp.model.MasterClass;
import vn.webapp.model.Department;
import vn.webapp.model.Rooms;

@Service("roomsService")
public class RoomsServiceImpl implements RoomsService {

    @Autowired
    private RoomsDAO roomsDAO;

    
    @Override
    public List<Rooms> listRooms() {
        try {
            return roomsDAO.listRooms();
            
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
}
