/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javax.persistence;

/**
 *
 * @author tobias
 */
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;

public interface EntityManager {

    public void persist(Object entity);

    public <T extends Object> T merge(T entity);

    public void remove(Object entity);

    public <T extends Object> T find(Class<T> entityClass, Object primaryKey);

    public <T extends Object> T find(Class<T> entityClass, Object primaryKey, Map<String, Object> properties);

    public <T extends Object> T find(Class<T> entityClass, Object primaryKey, LockModeType lockMode);

    public <T extends Object> T find(Class<T> entityClass, Object primaryKey, LockModeType lockMode, Map<String, Object> properties);

    public <T extends Object> T getReference(Class<T> entityClass, Object primaryKey);

    public void flush();

    public void setFlushMode(FlushModeType flushMode);

    public FlushModeType getFlushMode();

    public void lock(Object entity, LockModeType lockMode);

    public void lock(Object entity, LockModeType lockMode, Map<String, Object> properties);

    public void refresh(Object entity);

    public void refresh(Object entity, Map<String, Object> properties);

    public void refresh(Object entity, LockModeType lockMode);

    public void refresh(Object entity, LockModeType lockMode, Map<String, Object> properties);

    public void clear();

    public void detach(Object entity);

    public boolean contains(Object entity);

    public LockModeType getLockMode(Object entity);

    public void setProperty(String propertyName, Object value);

    public Map<String, Object> getProperties();

    public Query createQuery(String qlString);

    public <T extends Object> TypedQuery<T> createQuery(CriteriaQuery<T> criteriaQuery);

    public Query createQuery(CriteriaUpdate updateQuery);

    public Query createQuery(CriteriaDelete deleteQuery);

    public <T extends Object> TypedQuery<T> createQuery(String qlString, Class<T> resultClass);

    public Query createNamedQuery(String name);

    public <T extends Object> TypedQuery<T> createNamedQuery(String name, Class<T> resultClass);

    public Query createNativeQuery(String sqlString);

    public Query createNativeQuery(String sqlString, Class resultClass);

    public Query createNativeQuery(String sqlString, String resultSetMapping);

    public StoredProcedureQuery createNamedStoredProcedureQuery(String name);

    public StoredProcedureQuery createStoredProcedureQuery(String procedureName);

    public StoredProcedureQuery createStoredProcedureQuery(String procedureName, Class[] resultClasses);

    public StoredProcedureQuery createStoredProcedureQuery(String procedureName, String[] resultSetMappings);

    public void joinTransaction();

    public boolean isJoinedToTransaction();

    public <T extends Object> T unwrap(Class<T> cls);

    public Object getDelegate();

    public void close();

    public boolean isOpen();

    public EntityTransaction getTransaction();

    public EntityManagerFactory getEntityManagerFactory();

    public CriteriaBuilder getCriteriaBuilder();

    public Metamodel getMetamodel();

    public <T extends Object> EntityGraph<T> createEntityGraph(Class<T> rootType);

    public EntityGraph<?> createEntityGraph(String graphName);

    public EntityGraph<?> getEntityGraph(String graphName);

    public <T extends Object> List<EntityGraph<? super T>> getEntityGraphs(Class<T> entityClass);
}

