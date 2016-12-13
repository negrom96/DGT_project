package edu.uclm.esi.iso2.multas.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

import edu.uclm.esi.iso2.multas.dao.DriverDao;
import edu.uclm.esi.iso2.multas.dao.GeneralDao;
import edu.uclm.esi.iso2.multas.domain.Driver;
import edu.uclm.esi.iso2.multas.domain.Inquiry;
import edu.uclm.esi.iso2.multas.domain.Manager;
import edu.uclm.esi.iso2.multas.domain.Sanction;

public class testing {
    
    private Configuration cfg;
    private SessionFactory factory;
    private Session session;

    /**@Before
    public void setUp() throws IOException {
        cfg=new Configuration();
        cfg.configure("hibernate.cfg.xml");
        factory=cfg.buildSessionFactory();
        session=factory.openSession();

        Transaction t=session.beginTransaction();
        Query<Inquiry> query=session.createQuery("delete from Inquiry");
        query.executeUpdate();
        query=session.createQuery("delete from Sanction");
        query.executeUpdate();
        t.commit();
    }**/

    @Test
    public void test() {
        Manager m = Manager.get();
        int idInquiry = m.openInquiry("0000", 200, "Ciudad Real", 120);
        GeneralDao <Inquiry>dao = new GeneralDao<>();
        Inquiry i=dao.findById(Inquiry.class, idInquiry);
        assertNotNull(i);
        Sanction s= m.identifyDriver(idInquiry, "5000000");
        DriverDao dDao = new DriverDao();
        Driver driver = dDao.findByDni("5000000");
        m.pay(s.getId());
        assertTrue(driver.getPoints()==12);
    }

}
