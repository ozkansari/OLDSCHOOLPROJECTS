package net.javafun.view.admin;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import net.javafun.modal.jpa.entity.UserInfo;
import net.javafun.modal.jpa.service.UserInfoDao;
import net.javafun.modal.jpa.util.DaoFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ViewScoped
@ManagedBean(name="userInfoBean")
public class UserInfoBean implements Serializable {

	private static final long serialVersionUID = 8562654795072989325L;
	
	private static Logger logger = LoggerFactory.getLogger(UserInfoBean.class);
	
	private int itemCount = 100;
	
	private int pageSize = 10;
	
	private int pageNo = 0;

	public UserInfoBean() {
		logger.debug("UserInfoBean is being instantiated.");
	}
	
	public List<UserInfo> getUserInfoItems() {
		UserInfoDao dao = DaoFactory.getUserInfoDao();
		List<UserInfo> userInfoItems = dao.findUserInfoEntities(pageSize,pageNo*pageSize);
		itemCount = userInfoItems.size();
		return userInfoItems;
	}
	
	public int getItemCount() {
		return itemCount;
	}

}
