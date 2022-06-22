package Zad2;

import Exceptions.CommitException;
import Exceptions.SessionOpenException;

public class GenericDAO<T> {
    SessionFactory<T> factory;
    DbLogger dbLogger;

    public void setSessionFactory(SessionFactory<T> f) {
        factory = f;
    }

    public void setDbLogger(DbLogger d) {
        dbLogger = d;
    }

    public void save(T o) throws SessionOpenException {
        Session<T> session = factory.newSession();
        session.open();
        Transaction<T> trans = session.beginTransaction();
        trans.save(o);
        try {
            trans.commitTransaction();
        } catch (CommitException e) {
            dbLogger.log(e);
        }
        session.close();
    }
}