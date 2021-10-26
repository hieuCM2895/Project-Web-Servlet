package com.example.dao;

import com.example.model.Product;
import com.example.util.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ProductDAOImpl extends AbstractDAO<Product, Object> {

    private static ProductDAOImpl productDAO = null;

    private ProductDAOImpl() {
    }

    public static ProductDAOImpl getInstance() {
        if (productDAO == null)
            productDAO = new ProductDAOImpl();
        return productDAO;
    }

    public Product findNewProduct() throws Exception {

        try {

            Session session = HibernateUtil.getSesstionFactory().openSession();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Product> query = builder.createQuery(Product.class);
            Root<Product> root = query.from(Product.class);

            query.select(root).orderBy(builder.desc(root.get("id")));

            return session.createQuery(query).setMaxResults(1).getSingleResult();

        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    public Product findNewProductByCategoryId(int categoryId) throws Exception {

        try {

            Session session = HibernateUtil.getSesstionFactory().openSession();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Product> query = builder.createQuery(Product.class);
            Root<Product> root = query.from(Product.class);

            query.select(root).orderBy(builder.desc(root.get("id")));
            query.where(builder.equal(root.get("category").get("id"), categoryId));

            return session.createQuery(query).setMaxResults(1).setCacheable(true).getSingleResult();

        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    public List<Product> findListProductByCategoryID(int categoryId) throws Exception {

        try {

            Session session = HibernateUtil.getSesstionFactory().openSession();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Product> query = builder.createQuery(Product.class);
            Root<Product> root = query.from(Product.class);

            query.select(root).where(builder.equal(root.get("category").get("id"), categoryId));

            return session.createQuery(query).setCacheable(true).getResultList();

        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    public List<Product> searchProductByName(String txt) throws Exception {

        try {

            Session session = HibernateUtil.getSesstionFactory().openSession();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Product> query = builder.createQuery(Product.class);
            Root<Product> root = query.from(Product.class);

            query.select(root).where(builder.like(root.get("name"), "%" + txt + "%"));

            return session.createQuery(query).setCacheable(true).getResultList();

        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    public List<Product> searchProductByAccountID(int accountId, int currentNumberPage, int pageSize) throws Exception {

        try {

            Session session = HibernateUtil.getSesstionFactory().openSession();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Product> query = builder.createQuery(Product.class);
            Root<Product> root = query.from(Product.class);

            query.select(root).where(builder.equal(root.get("account").get("id"), accountId))
                    .orderBy(builder.asc(root.get("id")));

            Query query1 = session.createQuery(query).setCacheable(true);
            query1.setFirstResult((currentNumberPage - 1) * pageSize);
            query1.setMaxResults(pageSize);

            return query1.getResultList();

        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    public List<Product> searchProductByAccountID(int currentNumberPage, int pageSize) throws Exception {

        try {

            Session session = HibernateUtil.getSesstionFactory().openSession();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Product> query = builder.createQuery(Product.class);
            Root<Product> root = query.from(Product.class);

            query.select(root).orderBy(builder.asc(root.get("id")));

            Query query1 = session.createQuery(query).setCacheable(true);
            query1.setFirstResult((currentNumberPage - 1) * pageSize);
            query1.setMaxResults(pageSize);

            return query1.getResultList();

        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    // category

    public List<Product> searchProductByCategoryId(int categoryId, int currentNumberPage, int pageSize) throws Exception {

        try {
            Session session = HibernateUtil.getSesstionFactory().openSession();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Product> query = builder.createQuery(Product.class);
            Root<Product> root = query.from(Product.class);

            query.select(root).where(builder.equal(root.get("category").get("id"), categoryId))
                    .orderBy(builder.asc(root.get("id")));

            Query query1 = session.createQuery(query).setCacheable(true);
            query1.setFirstResult((currentNumberPage - 1) * pageSize);
            query1.setMaxResults(pageSize);

            return query1.getResultList();

        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    public Object getNumberSellProductByAccountID(int accountId) throws Exception {

        try {
            Session session = HibernateUtil.getSesstionFactory().openSession();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Object> query = builder.createQuery(Object.class);
            Root<Product> root = query.from(Product.class);

            query.select(builder.count(root)).where(builder.equal(root.get("account").get("id"), accountId));

            return session.createQuery(query).uniqueResult();

        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    public Object getNumberSellProduct() throws Exception {

        try {
            Session session = HibernateUtil.getSesstionFactory().openSession();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Object> query = builder.createQuery(Object.class);
            Root<Product> root = query.from(Product.class);

            query.select(builder.count(root));

            return session.createQuery(query).uniqueResult();

        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    public Object getNumberSellProductByCategory(int categoryId) throws Exception {

        try {
            Session session = HibernateUtil.getSesstionFactory().openSession();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Object> query = builder.createQuery(Object.class);
            Root<Product> root = query.from(Product.class);

            query.select(builder.count(root)).where(builder.equal(root.get("category").get("id"), categoryId));

            return session.createQuery(query).setCacheable(true).uniqueResult();

        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    public List<Product> searchProductByAmount(int amount) throws Exception {

        try {
            Session session = HibernateUtil.getSesstionFactory().openSession();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Product> query = builder.createQuery(Product.class);
            Root<Product> root = query.from(Product.class);

            query.select(root).orderBy(builder.asc(root.get("id")));

            Query query1 = session.createQuery(query).setCacheable(true);
            query1.setMaxResults(amount);

            return query1.getResultList();

        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    public List<Product> searchLastProductByAmount(int amount) throws Exception {

        try {
            Session session = HibernateUtil.getSesstionFactory().openSession();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Product> query = builder.createQuery(Product.class);
            Root<Product> root = query.from(Product.class);

            query.select(root).orderBy(builder.desc(root.get("id")));

            Query query1 = session.createQuery(query).setCacheable(true);
            query1.setMaxResults(amount);

            return query1.getResultList();

        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    public Object findCategoryIdByProductId(int productId) throws Exception {

        try {
            Session session = HibernateUtil.getSesstionFactory().openSession();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Object> query = builder.createQuery(Object.class);
            Root<Product> root = query.from(Product.class);

            query.select(root.get("category").get("id")).where(builder.equal(root.get("id"), productId));

            return session.createQuery(query).uniqueResult();

        } finally {
            if (session != null) {
                session.close();
            }
        }

    }


}
