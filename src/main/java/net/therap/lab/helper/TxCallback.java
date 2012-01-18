package net.therap.lab.helper;

import org.hibernate.Session;

/**
 * @author jahangir
 * @since 1/13/12 10:12 AM
 */
public interface TxCallback {

    void doInTx(Session session);

}
