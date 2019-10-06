import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

/**
 * Created by User on 15 Сент., 2019
 */
public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();

        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();

        try {
            String sql = "From " + PurchaseListEntity.class.getSimpleName();

            Transaction transaction = session.beginTransaction();

            sql = "from " + PurchaseListEntity.class.getSimpleName() + " p " +
                    "join " + Course.class.getSimpleName() + " c on c.name=p.courseName " +
                    "join " + Student.class.getSimpleName() + " s on s.name=p.studentName";
            try {
                List<Object[]> resultPurchaseList = session.createQuery(sql).list();
                for (Object[] omas : resultPurchaseList) {
                    Purchase purchase = new Purchase();
                    Course course = null;
                    Student student = null;
                    PurchaseListEntity purchaseListEntity = null;

                    for (Object o : omas) {
                        if (o instanceof Course) {
                            course = (Course) o;
                        }
                        if (o instanceof Student) {
                            student = (Student) o;
                        }
                        if (o instanceof PurchaseListEntity) {
                            purchaseListEntity = (PurchaseListEntity) o;
                        }
                    }

                    purchase.setCourse(course);
                    purchase.setStudent(student);
                    purchase.setPrice(course.getPrice());
                    purchase.setSubscriptionDate(purchaseListEntity.getSubscriptionDate());
                    session.save(purchase);
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            transaction.commit();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
