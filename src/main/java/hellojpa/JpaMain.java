package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Member member = new Member();
            member.setId(100L);
            member.setName("HelloJPA");
//            사실 여기까지는 아무상태도 아니다. -> 비영속
//            이렇게 해야 영속 상태
            System.out.println("=====BEFORE====");
            em.persist(member);
            System.out.println("=====AFTER=====");

            tx.commit(); // 이때 데이터베이스에 쿼리가 날라간다.
//            왜이렇게하는거지 ???
        }catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
