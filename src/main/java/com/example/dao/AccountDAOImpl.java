package com.example.dao;

import com.example.model.Account;
import com.example.util.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class AccountDAOImpl extends AbstractDAO<Account, Object> {

    private static AccountDAOImpl accountDAO = null;

    private AccountDAOImpl() {}

    public static AccountDAOImpl getInstance() {
        if (accountDAO == null)
            accountDAO = new AccountDAOImpl();
        return accountDAO;
    }

    public Account loginByUserAndPassword(String userName, String pass) throws Exception {

        Session session = HibernateUtil.getSesstionFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Account> query = builder.createQuery(Account.class);
        Root<Account> root = query.from(Account.class);

        Predicate user = builder.like(root.get("user"), userName);
        Predicate password = builder.like(root.get("password"), pass);

        query.select(root).where(builder.and(user, password));

        return session.createQuery(query).getSingleResult();

    }

    public boolean checkUserExits(String userName) throws Exception {

        Session session = HibernateUtil.getSesstionFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Account> query = builder.createQuery(Account.class);
        Root<Account> root = query.from(Account.class);

        Predicate user = builder.like(root.get("user"), userName);

        query.select(root).where(user);

        return session.createQuery(query) != null;

    }

}
