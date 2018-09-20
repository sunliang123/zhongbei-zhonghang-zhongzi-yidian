package com.waben.stock.applayer.promotion.business;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.applayer.promotion.business.cache.RedisCache;
import com.waben.stock.applayer.promotion.security.SecurityUtil;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.dto.organization.OrganizationDto;
import com.waben.stock.interfaces.dto.organization.UserDto;
import com.waben.stock.interfaces.exception.NetflixCircuitException;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.organization.UserQuery;
import com.waben.stock.interfaces.service.organization.UserInterface;
import com.waben.stock.interfaces.util.PasswordCrypt;

@Service("promotionUserBusiness")
public class UserBusiness {

	private static final List<UserDto> Response = null;
	@Autowired
	private UserInterface userReference;
	@Autowired
	private RoleBusiness roleBusiness;
	@Autowired
	private RedisCache redisCache;
	
	private static final String PROMOTION_BLACKUSER_REDISKEY = "PROMOTION_BLACKUSER";

	public UserDto fetchByUserName(String userName) {
		Response<UserDto> response = userReference.fetchByUserName(userName);
		String code = response.getCode();
		if ("200".equals(code)) {
			return response.getResult();
		} else if (ExceptionConstant.NETFLIX_CIRCUIT_EXCEPTION.equals(code)) {
			throw new NetflixCircuitException(code);
		}
		throw new ServiceException(response.getCode());
	}

	public UserDto save(UserDto userDto) {
		// 获取用户所属机构的管理员角色并绑定给当前用户
		Response<UserDto> userDtoResponse = userReference.getByUsername(userDto.getUsername());
		if (userDtoResponse.getResult() == null) {
			OrganizationDto organizationDto = new OrganizationDto();
			organizationDto.setId(userDto.getOrgId());
			userDto.setState(false);
			userDto.setCreateTime(new Date());
			userDto.setOrg(organizationDto);
			userDto.setPassword(PasswordCrypt.crypt(userDto.getPassword()));
			Response<UserDto> response = userReference.addition(userDto);
			String code = response.getCode();
			if ("200".equals(code)) {
				return response.getResult();
			} else if (ExceptionConstant.NETFLIX_CIRCUIT_EXCEPTION.equals(code)) {
				throw new NetflixCircuitException(code);
			}
			throw new ServiceException(response.getCode());
		} else {
			throw new ServiceException(ExceptionConstant.ORGANIZATION_USER_EXIST);
		}
	}

	public UserDto revision(UserDto userDto) {
		UserDto result = userReference.fetchByUserName(userDto.getUsername()).getResult();
		userDto.setPassword(PasswordCrypt.crypt(userDto.getPassword()));
		userDto.setCreateTime(result.getCreateTime());
		userDto.setState(result.getState());
		userDto.setOnlyStockoption(result.isOnlyStockoption());
		userDto.setOrgId(userDto.getOrgId());
		Response<UserDto> response = userReference.modification(userDto);
		String code = response.getCode();
		if ("200".equals(code)) {
			return response.getResult();
		} else if (ExceptionConstant.NETFLIX_CIRCUIT_EXCEPTION.equals(code)) {
			throw new NetflixCircuitException(code);
		}
		throw new ServiceException(response.getCode());
	}

	public UserDto saveUserRole(Long id, Long roleId) {
		Response<UserDto> response = userReference.bindRole(id, roleId);
		String code = response.getCode();
		if ("200".equals(code)) {
			return response.getResult();
		} else if (ExceptionConstant.NETFLIX_CIRCUIT_EXCEPTION.equals(code)) {
			throw new NetflixCircuitException(code);
		}
		throw new ServiceException(response.getCode());
	}

	public PageInfo<UserDto> pages(UserQuery userQuery) {
		userQuery.setOrganization(SecurityUtil.getUserDetails().getOrgId());
		Response<PageInfo<UserDto>> response = userReference.pages(userQuery);
		String code = response.getCode();
		if ("200".equals(code)) {
			return response.getResult();
		} else if (ExceptionConstant.NETFLIX_CIRCUIT_EXCEPTION.equals(code)) {
			throw new NetflixCircuitException(code);
		}
		throw new ServiceException(response.getCode());
	}

	public void modifyPassword(Long userId, String oldPassword, String password) {
		Response<Void> response = userReference.modifyPassword(userId, oldPassword, password);
		String code = response.getCode();
		if ("200".equals(code)) {
			return;
		} else if (ExceptionConstant.NETFLIX_CIRCUIT_EXCEPTION.equals(code)) {
			throw new NetflixCircuitException(code);
		}
		throw new ServiceException(response.getCode());
	}

	public UserDto revisionState(Long id) {
		Response<UserDto> response = userReference.modifyState(id);
		String code = response.getCode();
		if ("200".equals(code)) {
			if (response.getResult() != null) {
				if (response.getResult().getState()) {
					redisCache.set(PROMOTION_BLACKUSER_REDISKEY + "_" + String.valueOf(id), "true");
				}else{
					redisCache.remove(PROMOTION_BLACKUSER_REDISKEY + "_" + String.valueOf(id));
				}
			}
			return response.getResult();
		} else if (ExceptionConstant.NETFLIX_CIRCUIT_EXCEPTION.equals(code)) {
			throw new NetflixCircuitException(code);
		}
		throw new ServiceException(response.getCode());
	}

	public Response<List<UserDto>> findUserAll() {
		Response<List<UserDto>> response = userReference.list();
		if ("200".equals(response.getCode())) {
			return new Response<>(response.getResult());
		} else if (ExceptionConstant.NETFLIX_CIRCUIT_EXCEPTION.equals(response.getCode())) {
			throw new NetflixCircuitException(response.getCode());
		}
		throw new ServiceException(response.getCode());
	}
}
