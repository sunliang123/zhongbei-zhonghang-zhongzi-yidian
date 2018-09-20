package com.waben.stock.applayer.tactics.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.waben.stock.applayer.tactics.business.BindCardBusiness;
import com.waben.stock.applayer.tactics.business.CapitalAccountBusiness;
import com.waben.stock.applayer.tactics.business.OrganizationBusiness;
import com.waben.stock.applayer.tactics.business.OrganizationPublisherBusiness;
import com.waben.stock.applayer.tactics.business.PublisherBusiness;
import com.waben.stock.applayer.tactics.business.RealNameBusiness;
import com.waben.stock.applayer.tactics.business.SmsBusiness;
import com.waben.stock.applayer.tactics.dto.publisher.PublisherCapitalAccountDto;
import com.waben.stock.applayer.tactics.dto.publisher.SettingRemindDto;
import com.waben.stock.applayer.tactics.security.CustomUserDetails;
import com.waben.stock.applayer.tactics.security.SecurityUtil;
import com.waben.stock.applayer.tactics.security.jwt.JWTTokenUtil;
import com.waben.stock.applayer.tactics.service.RedisCache;
import com.waben.stock.applayer.tactics.service.SmsCache;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.dto.organization.OrganizationDto;
import com.waben.stock.interfaces.dto.publisher.BindCardDto;
import com.waben.stock.interfaces.dto.publisher.CapitalAccountDto;
import com.waben.stock.interfaces.dto.publisher.PublisherDto;
import com.waben.stock.interfaces.dto.publisher.RealNameDto;
import com.waben.stock.interfaces.enums.RedisCacheKeyType;
import com.waben.stock.interfaces.enums.ResourceType;
import com.waben.stock.interfaces.enums.SmsType;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Created by yuyidi on 2017/11/3.
 * @desc
 */
@RestController("tacticsPublisherController")
@RequestMapping("/publisher")
@Api(description = "策略发布人")
public class PublisherController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private PublisherBusiness publisherBusiness;

	@Autowired
	private CapitalAccountBusiness accountBusiness;

	@Autowired
	private BindCardBusiness bindCardBusiness;

	@Autowired
	private SmsBusiness smsBusiness;

	@Autowired
	private OrganizationPublisherBusiness orgPublisherBusiness;

	@Autowired
	private RealNameBusiness realNameBusiness;

	@Autowired
	private RedisCache redisCache;

	@Autowired
	private SmsCache smsCache;

	@Autowired
	private OrganizationBusiness orgBusiness;

	@GetMapping("/{id}")
	public Response<PublisherDto> echo(@PathVariable Long id) {
		return new Response<>(publisherBusiness.findById(id));
	}

	@PostMapping("/sendSms")
	@ApiOperation(value = "发送短信", notes = "type(1:注册,2:修改密码,3:绑定银行卡,5:修改支付密码)")
	public Response<String> sendAuthCode(String phone, int type) {
		SmsType smsType = SmsType.getByIndex(String.valueOf(type));
		smsBusiness.sendAuthCode(phone, smsType);
		return new Response<>();
	}

	@GetMapping("/paymentPwdCode/validation/{verificationCode}")
	@ApiOperation(value = "验证修改支付密码的验证码是否正确")
	public Response<Boolean> validatePaymentPwdCode(@PathVariable("verificationCode") String verificationCode) {
		// 检查验证码
		smsCache.matchVerificationCode(SmsType.ModifyPaymentPwdCode, SecurityUtil.getUsername(), "code",
				verificationCode);
		return new Response<>(true);
	}

	@PostMapping("/register")
	@ApiOperation(value = "注册发布策略人")
	public Response<PublisherCapitalAccountDto> register(@RequestParam(required = true) String phone,
			@RequestParam(required = true) String password, @RequestParam(required = true) String verificationCode,
			String promoter, String orgCode, HttpServletRequest request) {
		// 检查机构代码是否正确
		if (orgCode != null && !"".equals(orgCode.trim())) {
			OrganizationDto org = orgBusiness.fetchByCode(orgCode);
			if (org == null) {
				throw new ServiceException(ExceptionConstant.ORGCODE_NOTEXIST_EXCEPTION);
			}
		}
		// 检查验证码
		smsCache.matchVerificationCode(SmsType.RegistVerificationCode, phone, "code", verificationCode);
		// 注册
		PublisherDto publisher = publisherBusiness.register(phone, password, promoter, request.getHeader("endType"));
		CapitalAccountDto account = accountBusiness.findByPublisherId(publisher.getId());
		PublisherCapitalAccountDto data = new PublisherCapitalAccountDto(publisher, account);
		String token = JWTTokenUtil.generateToken(new CustomUserDetails(publisher.getId(), publisher.getSerialCode(),
				publisher.getPhone(), null, JWTTokenUtil.getAppGrantedAuthList()));
		data.setToken(token);
		redisCache.set(String.format(RedisCacheKeyType.AppToken.getKey(), phone), token);
		// 关联结构代码
		orgPublisherBusiness.addOrgPublisher(data.getId(), orgCode);
		return new Response<>(data);
	}

	@PostMapping("/registrationId")
	@ApiOperation(value = "设置极光registrationId", notes = "deviceType设备类型(1IOS 2安卓),shellIndex空壳包序号")
	public Response<String> bindRegistrationId(String registrationId,
			@RequestParam(defaultValue = "0") Integer deviceType,
			@RequestParam(defaultValue = "0") Integer shellIndex) {
		logger.info("用户{}设置极光registrationId:{}", SecurityUtil.getUserId(), registrationId);
		if (registrationId != null && !"".equals(registrationId.trim())) {
			if ("0".equals(shellIndex) || shellIndex == null) {
				redisCache.set(String.format(RedisCacheKeyType.AppRegistrationId.getKey(), SecurityUtil.getUserId()),
						registrationId.trim());
			} else {
				redisCache.set(String.format(RedisCacheKeyType.AppRegistrationId.getKey(), SecurityUtil.getUserId()),
						deviceType + "_" + shellIndex + "_" + registrationId.trim());
			}
		}
		Response<String> response = new Response<String>();
		response.setResult(registrationId);
		return response;
	}

	@GetMapping("/getCurrent")
	@ApiOperation(value = "获取当前发布策略人信息")
	public Response<PublisherCapitalAccountDto> getCurrent() {
		PublisherDto publisher = publisherBusiness.findById(SecurityUtil.getUserId());
		CapitalAccountDto account = accountBusiness.findByPublisherId(SecurityUtil.getUserId());
		return new Response<>(new PublisherCapitalAccountDto(publisher, account));
	}

	@GetMapping("/getSettingRemind")
	@ApiOperation(value = "获取当前发布策略人设置提醒信息")
	public Response<SettingRemindDto> getSettingRemind() {
		Response<SettingRemindDto> result = new Response<>(new SettingRemindDto());
		result.getResult().setPhone(SecurityUtil.getUsername());
		// 获取是否绑卡
		List<BindCardDto> bindCardList = bindCardBusiness.listsByPublisherId(SecurityUtil.getUserId());
		if (bindCardList != null && bindCardList.size() > 0) {
			result.getResult().setSettingBindCard(true);
			result.getResult().setBindCardCount(bindCardList.size());
		}
		// 获取是否设置过支付密码
		CapitalAccountDto account = accountBusiness.findByPublisherId(SecurityUtil.getUserId());
		if (account != null && account.getPaymentPassword() != null && !"".equals(account.getPaymentPassword())) {
			result.getResult().setSettingPaymentPassword(true);
		}
		// 获取是否实名认证
		RealNameDto realName = realNameBusiness.fetch(ResourceType.PUBLISHER, SecurityUtil.getUserId());
		if (realName != null) {
			result.getResult().setSettingRealName(true);
		}
		return result;
	}

	@PostMapping("/modifyPassword")
	@ApiOperation(value = "修改密码")
	public Response<PublisherCapitalAccountDto> modifyPassword(String phone, String password, String verificationCode) {
		// 检查验证码
		smsCache.matchVerificationCode(SmsType.ModifyPasswordCode, phone, "code", verificationCode);
		// 修改密码
		PublisherDto publisher = publisherBusiness.modifyPassword(phone, password);
		CapitalAccountDto account = accountBusiness.findByPublisherId(publisher.getId());
		PublisherCapitalAccountDto data = new PublisherCapitalAccountDto(publisher, account);
		String cacheToken = redisCache.get(String.format(RedisCacheKeyType.AppToken.getKey(), phone));
		String token = null;
		if (cacheToken != null && !"".equals(cacheToken.trim())) {
			token = cacheToken;
		} else {
			token = JWTTokenUtil.generateToken(new CustomUserDetails(publisher.getId(), publisher.getSerialCode(),
					publisher.getPhone(), null, JWTTokenUtil.getAppGrantedAuthList()));
		}
		data.setToken(token);
		redisCache.set(String.format(RedisCacheKeyType.AppToken.getKey(), phone), token);
		return new Response<>(data);
	}

	@PostMapping("/resetPassword")
	@ApiOperation(value = "根据原密码重设密码")
	public Response<PublisherCapitalAccountDto> resetPassword(String oldPassword, String newPassword) {
		// 验证原始密码是否正确
		PublisherDto publisher = publisherBusiness.findById(SecurityUtil.getUserId());
		if (!oldPassword.equals(publisher.getPassword())) {
			throw new ServiceException(ExceptionConstant.ORIGINAL_PASSWORD_MISMATCH_EXCEPTION);
		}
		// 修改密码
		publisherBusiness.modifyPassword(publisher.getPhone(), newPassword);
		CapitalAccountDto account = accountBusiness.findByPublisherId(publisher.getId());
		PublisherCapitalAccountDto data = new PublisherCapitalAccountDto(publisher, account);
		String token = JWTTokenUtil.generateToken(new CustomUserDetails(publisher.getId(), publisher.getSerialCode(),
				publisher.getPhone(), null, JWTTokenUtil.getAppGrantedAuthList()));
		data.setToken(token);
		return new Response<>(data);
	}

	@PostMapping("/modifyPaymentPassword")
	@ApiOperation(value = "设置支付密码")
	public Response<String> modifyPaymentPassword(String phone, String paymentPassword, String verificationCode) {
		if (!SecurityUtil.getUsername().equals(phone)) {
			throw new ServiceException(ExceptionConstant.PHONE_MISMATCH_EXCEPTION);
		}
		// 检查验证码
		smsCache.matchVerificationCode(SmsType.ModifyPaymentPwdCode, phone, "code", verificationCode);
		accountBusiness.modifyPaymentPassword(SecurityUtil.getUserId(), paymentPassword);
		return new Response<>();
	}

	@PostMapping("/initPaymentPassword")
	@ApiOperation(value = "初始化支付密码")
	public Response<String> initPaymentPassword(String paymentPassword) {
		// 是否是第一次设置支付密码
		CapitalAccountDto account = accountBusiness.findByPublisherId(SecurityUtil.getUserId());
		if (account != null && account.getPaymentPassword() != null && !"".equals(account.getPaymentPassword())) {
			throw new ServiceException(ExceptionConstant.MODIFY_PAYMENTPASSWORD_NEEDVALIDCODE_EXCEPTION);
		}
		accountBusiness.modifyPaymentPassword(SecurityUtil.getUserId(), paymentPassword);
		return new Response<>();
	}

	@ApiOperation(value = "上传用户头像")
	@PostMapping("/headPortrait")
	public Response<String> uploadHeadPortrait(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		Response<String> response = new Response<String>();
		response.setResult(publisherBusiness.uploadHeadPortrait(SecurityUtil.getUserId(), file));
		return response;
	}

}
