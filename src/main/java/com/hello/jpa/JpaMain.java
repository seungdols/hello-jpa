package com.hello.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {

            Member member = new Member();
            member.setName("seungho");

            member.setHomeAreaAddress(
                    new Address("city1", "steet", "13333")
            );
            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");

            member.getAddressHistory().add(
                    new Address("old1", "steet1", "133")
            );
            member.getAddressHistory().add(
                    new Address("old2", "steet2", "1311")
            );

            entityManager.persist(member);


            entityManager.flush();
            entityManager.clear();

            Member findMember = entityManager.find(Member.class, member.getId());
            System.out.println("findMember: " + findMember.getName());

            List<Address> addressHistory = findMember.getAddressHistory();
            for (Address address : addressHistory) {
                System.out.println(address);
            }

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("exception: " + e);
        } finally {
            entityManager.close();
        }
        entityManagerFactory.close();
    }
}
