import Exceptions.CommitException;
import Exceptions.SessionOpenException;
import Zad2.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GenericDAOTests<T> {
    private GenericDAO<T> genericDAO;
    @Mock
    private SessionFactory<T> factory;
    @Mock
    private Session<T> session;
    @Mock
    private Transaction<T> transaction;
    @Mock
    private DbLogger dbLogger;

    @Captor
    private ArgumentCaptor<Exception> exceptionCaptor;

    @BeforeEach
    void init() {
        genericDAO = new GenericDAO<T>();

        genericDAO.setSessionFactory(factory);
        genericDAO.setDbLogger(dbLogger);
        when(factory.newSession()).thenReturn(session);
    }

    @Test
    void all_good() throws SessionOpenException, CommitException {
        when(session.beginTransaction()).thenReturn(transaction);

        genericDAO.save(null);

        //verification

        InOrder inOrder = inOrder(session, transaction);

        inOrder.verify(session).open();
        inOrder.verify(transaction).save(null);
        inOrder.verify(transaction).commitTransaction();
        inOrder.verify(session).close();
    }

    @Test
    void openSessionError() throws SessionOpenException {
        doThrow(SessionOpenException.class).when(session).open();

        Assertions.assertThrows(SessionOpenException.class, () -> genericDAO.save(null));
    }

    @Test
    void commitTransactionError() throws CommitException, SessionOpenException {
        when(session.beginTransaction()).thenReturn(transaction);

        doThrow(CommitException.class).when(transaction).commitTransaction();

        genericDAO.save(null);

        verify(dbLogger).log(exceptionCaptor.capture());
        Exception e = exceptionCaptor.getValue();

        Assertions.assertInstanceOf(CommitException.class, e);


        verify(session).close();
    }
}