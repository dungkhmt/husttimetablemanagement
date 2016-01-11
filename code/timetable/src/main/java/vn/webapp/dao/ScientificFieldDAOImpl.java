/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.webapp.model.Department;
import vn.webapp.model.PaperCategory;
import vn.webapp.model.ScientificField;
import vn.webapp.model.Staff;
import vn.webapp.model.Users;

@Repository("scientificFieldDAO")
@SuppressWarnings({"unchecked", "rawtypes"})

public class ScientificFieldDAOImpl extends BaseDao implements ScientificFieldDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Get department list
     * @param null
     * @return List
     */
    @Override
    public List<ScientificField> loadScientificFieldList() {
        try {
            begin();
            Criteria criteria = getSession().createCriteria(ScientificField.class);
            List<ScientificField> scientificFieldList = criteria.list();
            commit();
            return scientificFieldList;
        } catch (HibernateException e) {
            e.printStackTrace();
            rollback();
            close();
            return null;
        } finally {
            flush();
            close();
        }
    }        
}
