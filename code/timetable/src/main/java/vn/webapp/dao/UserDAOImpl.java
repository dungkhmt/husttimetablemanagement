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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.webapp.model.UserRoles;
import vn.webapp.model.Users;
import vn.webapp.model.User;

@Repository("userDAO")
@SuppressWarnings({"unchecked", "rawtypes"})

public class UserDAOImpl extends BaseDao implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Get an user by username
     * @param username
     * @return object
     */
    @Override
    public Users getByUsername(String userName) {
        try {
            begin();
            Criteria criteria = getSession().createCriteria(Users.class);
            criteria.add(Restrictions.eq("Username", userName));
            Users user = (Users) criteria.uniqueResult();
            commit();
            return user;
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
    
    /**
     * Get an user by username and id
     * @param String
     * @param int
     * @return object
     */
    @Override
    public Users getByUsernameAndId(String userName, int id){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(Users.class);
            criteria.add(Restrictions.eq("Username", userName));
            criteria.add(Restrictions.ne("User_ID", id));
            Users user = (Users) criteria.uniqueResult();
            commit();
            return user;
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

    /**
     * Get a list all users
     * @param null
     * @return List
     */
    @Override
    public List<Users> list() {
        try {
            begin();
            Criteria criteria = getSession().createCriteria(Users.class);
            criteria.setFirstResult(0);
            criteria.addOrder(Order.desc("User_ID"));
            List<Users> users = criteria.list();
            commit();
            return users;
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

    /**
     * Get an user by id
     * @param int
     * @return object
     */
    @Override
    public Users viewDetail(int id) {
    	try {
	        begin();
	        Criteria criteria = getSession().createCriteria(Users.class, "user");
	        criteria.add(Restrictions.eq("user.User_ID", id));
	        Users user = (Users) criteria.uniqueResult();
	        commit();
	        return user;
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
    
    /**
     * Get an user role by username
     * @param String
     * @return object
     */
    @Override
    public UserRoles viewDetailUserRole(String userName) {
    	try {
	        begin();
	        Criteria criteria = getSession().createCriteria(UserRoles.class, "userRole");
	        criteria.add(Restrictions.eq("userRole.Username", userName));
	        UserRoles userRole = (UserRoles) criteria.uniqueResult();
	        commit();
	        return userRole;
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

    /**
     * Remove an user by id
     * @param String
     * @return int
     */
    @Override
    public int removeUser(String id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "delete from Users u where u.id ='" + id + "'");
        int result = query.executeUpdate();
        return result;
    }

    /**
     * Save an user
     * @param object
     * @return int
     */
    @Override
    public int saveAUser(Users user) 
    {
        try {
           begin();
           int id = 0; 
           id = (int)getSession().save(user);
           commit();
           return id;           
        } catch (HibernateException e) {
            e.printStackTrace();
            rollback();
            close();
            return 0;
        } finally {
            flush();
            close();
        }
    }
    
    /**
     * Save an userrole
     * @param object
     * @return int
     */
    @Override
    public int saveAUserRole(UserRoles userRole) 
    {
        try {
           begin();
           int id = 0; 
           id = (int)getSession().save(userRole);
           commit();
           return id;           
        } catch (HibernateException e) {
            e.printStackTrace();
            rollback();
            close();
            return 0;
        } finally {
            flush();
            close();
        }
    }
    
    /**
     * Edit an user
     * @param object
     * @return int
     */
    @Override
    public void editAnUser(Users user){
        try {
           begin();
           getSession().update(user);
           commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollback();
            close();
        } finally {
            flush();
            close();
        }
    }
    
    /**
     * Edit an user role
     * @param object
     * @return int
     */
    @Override
    public void editAnUserRole(UserRoles userRole){
    	try {
            begin();
            getSession().update(userRole);
            commit();
         } catch (HibernateException e) {
             e.printStackTrace();
             rollback();
             close();
         } finally {
             flush();
             close();
         }
    }
    
    /**
     * Get an user by id
     * @param username
     * @return object
     */
    @Override
    public Users loadUserById(int userId) {
        try {
            begin();
            Criteria criteria = getSession().createCriteria(Users.class);
            criteria.add(Restrictions.eq("User_ID", userId));
            Users user = (Users) criteria.uniqueResult();
            commit();
            return user;
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
    
    /**
     * Count number of users
     * @param null
     * @return int
     */
    @Override
    public int count(){
         try {
            begin();
            Criteria criteria = getSession().createCriteria(Users.class);            
            List<Users> users = criteria.list();
            commit();
            return users.size();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollback();
            close();
            return 0;
        } finally {
            flush();
            close();
        }
    }
}
