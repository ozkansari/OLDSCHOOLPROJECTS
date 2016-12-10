package com.mycolumnist.model.persistence.entity.base;

import java.io.Serializable;

public class EntityBase<T> implements Serializable {
    /**
     * default logger
     */
    private static final long serialVersionUID = 1L;

    /**
     * Entity identifier(Table primary key)
     */
    protected T id;

    /** ************************************************************************ */
    /** ************************** CONSTRUCTORS ******************************** */
    /** ************************************************************************ */
    public EntityBase() {

    }

    public EntityBase(T id) {
        this.id = id;
    }

    /** ************************************************************************ */
    /** ************************* GETTERS & SETTERS **************************** */
    /** ************************************************************************ */
    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    /** ************************************************************************ */
    /** ************************** COMMON METHODS ****************************** */
    /** ************************************************************************ */

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
   
    @SuppressWarnings("unchecked")
	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof EntityBase))
            return false;
        final EntityBase other = (EntityBase) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.getId()))
            return false;
        return true;
    }

    public String toString() {
        return this.getClass().getSimpleName().toString() + " id=" + id;
    }

}
