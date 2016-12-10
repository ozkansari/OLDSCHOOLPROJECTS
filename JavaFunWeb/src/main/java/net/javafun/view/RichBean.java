package net.javafun.view;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import net.javafun.modal.jpa.service.UserInfoDao;
import net.javafun.modal.jpa.util.DaoFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ViewScoped
@ManagedBean(name="richBean")
public class RichBean implements Serializable {

	private final static Logger logger = LoggerFactory.getLogger(RichBean.class);
	
    private static final long serialVersionUID = -2403138958014741653L;
    private String name;

    public RichBean() {
    	logger.debug("post construct: initialize");

        name = "John";
        UserInfoDao dao = DaoFactory.getUserInfoDao();
        dao.findOrCreateUser(name, name, name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
