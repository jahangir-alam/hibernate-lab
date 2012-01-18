package net.therap.lab.onetoone;

import net.therap.lab.onetoone.domain.Stock;
import net.therap.lab.onetoone.domain.StockDetail;
import net.therap.lab.onetoone.domain.AppUser;
import net.therap.lab.helper.TxCallback;
import net.therap.lab.util.HibernateUtil;
import org.hibernate.Session;

import java.util.Date;

/**
 * @author jahangir
 * @since 1/12/12 9:35 PM
 */
public class Main {

    public static void main(String[] args) {

        createUser();
        createStock();
        readStock();

        System.out.println("Done");
    }

    private static void createUser() {
        process(new TxCallback() {
            @Override
            public void doInTx(Session session) {
                AppUser user = new AppUser();
                user.setName("Jahangir Alam");
                session.save(user);
            }
        });
    }

    private static void readStock() {
        process(new TxCallback() {
            @Override
            public void doInTx(Session session) {
                Stock stock = (Stock) session.get(Stock.class, 1L);
                System.out.println(stock);
                System.out.println(stock.getStockDetail());
            }
        });
    }

    private static void createStock() {

        process(new TxCallback() {
            @Override
            public void doInTx(Session session) {
                Stock stock = new Stock();

                stock.setStockCode("7052");
                stock.setStockName("PADINI");

                AppUser appUser = (AppUser) session.load(AppUser.class, 1L);
                stock.setCreatedBy(appUser);

                StockDetail stockDetail = new StockDetail();
                stockDetail.setCompanyName("PADINI Holding Malaysia");
                stockDetail.setCompanyDesc("one stop shopping");
                stockDetail.setRemarks("vinci vinci");
                stockDetail.setListedDate(new Date());

                stock.setStockDetail(stockDetail);
                stockDetail.setStock(stock);
                session.save(stock);
            }
        });
    }

    private static void process(TxCallback callback) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        callback.doInTx(session);
        session.getTransaction().commit();
        session.close();
    }
}
