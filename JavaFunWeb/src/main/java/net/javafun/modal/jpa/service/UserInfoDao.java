/**
 * 
 */
package net.javafun.modal.jpa.service;

import java.util.List;

import net.javafun.modal.jpa.entity.UserInfo;

/**
 * @author ozkansari
 *
 */
public interface UserInfoDao {

	public UserInfo findOrCreateUser(String username, String pwd, String email);

	public List<UserInfo> findUserInfoEntities(int pageSize, int i);
}
