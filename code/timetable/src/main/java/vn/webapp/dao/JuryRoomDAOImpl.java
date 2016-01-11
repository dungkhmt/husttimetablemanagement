package vn.webapp.dao;

import java.util.List;

import vn.webapp.model.JuryRoom;

import org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("JuryRoomDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class JuryRoomDAOImpl extends BaseDao implements JuryRoomDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	@Override
	public List<JuryRoom> listJuryRooms(String defenseSessionCode,
			String staffCode) {
		// TODO Auto-generated method stub
		try{
			begin();
			Criteria criteria = getSession().createCriteria(JuryRoom.class);
			criteria.setFirstResult(0);
			criteria.add(Restrictions.eq("JuryRoom_DefenseSessionCode", defenseSessionCode));
			criteria.add(Restrictions.eq("JuryRoom_StaffCode", staffCode));
			List<JuryRoom> listJuryRooms = criteria.list();
			commit();
			return listJuryRooms;
		}catch(HibernateException e){
			e.printStackTrace();
			rollback();
			close();
			return null;
		}finally{
			flush();
			close();
		}
	}

	@Override
	public JuryRoom getJuryRoomByCode(String sJuryRoomCode, String defenseSessionCode, String userCode){
		try{
			begin();
			Criteria criteria = getSession().createCriteria(JuryRoom.class);
			criteria.setFirstResult(0);
			criteria.add(Restrictions.eq("JuryRoom_Code", sJuryRoomCode));
			criteria.add(Restrictions.eq("JuryRoom_DefenseSessionCode", defenseSessionCode));
			criteria.add(Restrictions.eq("JuryRoom_StaffCode", userCode));
			JuryRoom juryRoom = (JuryRoom)criteria.uniqueResult();
			commit();
			return juryRoom;
		}catch(HibernateException e){
			e.printStackTrace();
			rollback();
			close();
			return null;
		}finally{
			flush();
			close();
		}
	}
	
	@Override
	public JuryRoom getJuryRoomByUserCode(String sJuryRoomCode, String userCode){
		try{
			begin();
			Criteria criteria = getSession().createCriteria(JuryRoom.class);
			criteria.setFirstResult(0);
			criteria.add(Restrictions.eq("JuryRoom_Code", sJuryRoomCode));
			criteria.add(Restrictions.eq("JuryRoom_StaffCode", userCode));
			JuryRoom juryRoom = (JuryRoom)criteria.uniqueResult();
			commit();
			return juryRoom;
		}catch(HibernateException e){
			e.printStackTrace();
			rollback();
			close();
			return null;
		}finally{
			flush();
			close();
		}
	}
	
	@Override
	public List<JuryRoom> listJuryRooms(String staffCode) {
		// TODO Auto-generated method stub
		try{
			begin();
			Criteria criteria = getSession().createCriteria(JuryRoom.class);
			criteria.setFirstResult(0);
			criteria.add(Restrictions.eq("JuryRoom_StaffCode", staffCode));
			List<JuryRoom> listJuryRooms = criteria.list();
			commit();
			return listJuryRooms;
		}catch(HibernateException e){
			e.printStackTrace();
			rollback();
			close();
			return null;
		}finally{
			flush();
			close();
		}
	}

	@Override
	public int saveJuryRoom(JuryRoom juryRoom){
		try {
            begin();
            int id = 0; 
            id = (int)getSession().save(juryRoom);
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
	
	@Override
	public int removeAJuryRoomByCode(JuryRoom aJuryRoom){
		try {
            begin();
            getSession().delete(aJuryRoom);
            commit();
            return 1;
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
