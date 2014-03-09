package br.com.typekboom.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.typekboom.util.GenericUtil;

/**
 * @author efraimgentil (efraim.gentil@gmail.com)
 */
public abstract class DefaultDao<Entity , PrimaryKeyType>{
	
	private Class<Entity> entityClass;
	
	@PersistenceContext
	protected EntityManager entityManager;
	
	public void create(Entity entity){
		entityManager.persist(entity);
	}
	
	public void update(Entity entity){
		entityManager.merge(entity);
	}
	
	public void delete(PrimaryKeyType pk){
		entityManager.remove( find(pk) );
	}
	
	public Entity find(PrimaryKeyType pk){
		return entityManager.find( getEntityClass() , pk);
	}
	
	public Class<Entity> getEntityClass(){
		if(entityClass == null){
			entityClass = new GenericUtil().getGenericClassType( this.getClass() ,  0 );
		}
		return entityClass;
	}
	
}