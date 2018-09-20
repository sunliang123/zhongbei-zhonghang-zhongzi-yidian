package com.waben.stock.datalayer.organization.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.waben.stock.datalayer.organization.entity.Organization;
import com.waben.stock.datalayer.organization.entity.User;
import com.waben.stock.datalayer.organization.repository.OrganizationDao;
import com.waben.stock.datalayer.organization.repository.UserDao;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.exception.DataNotFoundException;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.query.organization.UserQuery;
import com.waben.stock.interfaces.util.PasswordCrypt;

/**
 * 机构管理用户 Service
 * 
 * @author luomengan
 *
 */
@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private OrganizationDao organizationDao;

	public User getUserInfo(Long id) {
		return userDao.retrieve(id);
	}

	@Transactional
	public User addUser(User user, Long orgId) {
		Organization org = organizationDao.retrieve(orgId);
		if(org == null) {
			throw new ServiceException(ExceptionConstant.DATANOTFOUND_EXCEPTION);
		}
		user.setOrg(org);
		return userDao.create(user);
	}

	@Transactional
	public User modifyUser(User user) {
		return userDao.update(user);
	}

	@Transactional
	public void deleteUser(Long id) {
		userDao.delete(id);
	}
	
	@Transactional
	public void deleteUsers(String ids) {
		if(ids != null) {
			String[] idArr= ids.split(",");
			for(String id : idArr) {
				if(!"".equals(id.trim())) {
					userDao.delete(Long.parseLong(id.trim()));
				}
			}
		}
	}

	public Page<User> users(int page, int limit) {
		return userDao.page(page, limit);
	}
	
	public List<User> list() {
		return userDao.list();
	}


	@Transactional
	public User findByUserName(String userName) {
		User result = userDao.retrieveByUserName(userName);
		if (result == null) {
			throw new DataNotFoundException(ExceptionConstant.ORGANIZATION_USER_NOT_FOUND);
		}
		Organization org = result.getOrg();
		if(org != null && org.getParent() != null) {
			org.getParent().getCode();
		}
		return result;
	}
	
	public User getByUserName(String userName) {
		User result = userDao.retrieveByUserName(userName);
		return result;
	}

    public Page<User> pagesByQuery(final UserQuery query) {

		Pageable pageable = new PageRequest(query.getPage(), query.getSize());
		Page<User> pages = userDao.page(new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery,
										 CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicateList = new ArrayList<>();
				if(!StringUtils.isEmpty(query.getId())) {
					Predicate idQuery = criteriaBuilder.equal(root.get("id").as(Long.class), query
							.getId());
					predicateList.add(idQuery);
				}
				if(!StringUtils.isEmpty(query.getOrganization())) {
					Organization organization = new Organization();
					organization.setId(query.getOrganization());
					organization.setParent(organization);
					List<Organization> organizations = organizationDao.listByParent(organization);
					organizations.add(organization);
					predicateList.add(root.get("org").in(organizations));
				}
				if(!StringUtils.isEmpty(query.getUserName())) {
					Predicate userNameQuery = criteriaBuilder.equal(root.get("username").as(String.class), query
							.getUserName());
					predicateList.add(userNameQuery);
				}
				if(!StringUtils.isEmpty(query.getNickName())) {
					Predicate nickNameQuery = criteriaBuilder.equal(root.get("nickname").as(String.class), query
							.getNickName());
					predicateList.add(nickNameQuery);
				}
				criteriaQuery.orderBy(criteriaBuilder.asc(root.get("createTime").as(Date.class)));
				criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
				return criteriaQuery.getRestriction();
			}
		}, pageable);
		return pages;
    }

	public User bindRole(Long id, Long role) {
		User user = userDao.retrieve(id);
		user.setRole(role);
		return userDao.update(user);
	}

	public void modifyPassword(Long userId, String oldPassword, String password) {
		User user = userDao.retrieve(userId);
		if (user == null) {
			throw new DataNotFoundException();
		}
		if (!PasswordCrypt.match(oldPassword, user.getPassword())) {
			throw new ServiceException(ExceptionConstant.ORGUSER_OLDPASSWORD_NOTMATCH_EXCEPTION);
		}
		user.setPassword(PasswordCrypt.crypt(password));
		userDao.update(user);
	}

	@Transactional
	public User revisionState(Long id) {
		User user = userDao.retrieve(id);
		if(user.getState()==null) {
			user.setState(true);
		}else {
			user.setState(!user.getState());
		}
		return user;
	}
}
