package com.waben.stock.applayer.tactics.business;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JavaType;
import com.waben.stock.applayer.tactics.dto.liveplayer.LiveplayerDto;
import com.waben.stock.applayer.tactics.service.RedisCache;
import com.waben.stock.interfaces.commonapi.netease.ChannelManageOverHttps;
import com.waben.stock.interfaces.commonapi.netease.ImOverHttps;
import com.waben.stock.interfaces.commonapi.netease.config.NeteaseImConfig;
import com.waben.stock.interfaces.commonapi.netease.config.NeteaseLiveConfig;
import com.waben.stock.interfaces.commonapi.netease.imbean.NeteaseChatroomCreateParam;
import com.waben.stock.interfaces.commonapi.netease.imbean.NeteaseChatroomCreateRet;
import com.waben.stock.interfaces.commonapi.netease.imbean.NeteaseChatroomRequestAddrParam;
import com.waben.stock.interfaces.commonapi.netease.imbean.NeteaseImResponse;
import com.waben.stock.interfaces.commonapi.netease.imbean.NeteaseRefreshTokenParam;
import com.waben.stock.interfaces.commonapi.netease.imbean.NeteaseRefreshTokenRet;
import com.waben.stock.interfaces.commonapi.netease.imbean.NeteaseUserCreateParam;
import com.waben.stock.interfaces.commonapi.netease.imbean.NeteaseUserCreateRet;
import com.waben.stock.interfaces.commonapi.netease.livebean.NeteaseAddressParam;
import com.waben.stock.interfaces.commonapi.netease.livebean.NeteaseAddressRet;
import com.waben.stock.interfaces.commonapi.netease.livebean.NeteaseChannellistParam;
import com.waben.stock.interfaces.commonapi.netease.livebean.NeteaseChannellistRet;
import com.waben.stock.interfaces.commonapi.netease.livebean.NeteaseLivePage;
import com.waben.stock.interfaces.commonapi.netease.livebean.NeteaseLiveResponse;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.util.JacksonUtil;

/**
 * 直播Business
 * 
 * @author luomengan
 *
 */
@Service("tacticsLiveplayerBusiness")
public class LiveplayerBusiness {

	@Value("${execute.domain:youguwang.com.cn}")
	private String domain;

	@Autowired
	private RedisCache redisCache;

	@PostConstruct
	public void createAdminUser() {
		createUser("admin");
	}

	public LiveplayerDto address(Long publisherId, int clientType) {
		LiveplayerDto result = new LiveplayerDto();
		// 获取当前直播
		NeteaseChannellistRet channel = getCurrentLivePlayer();
		// 获取直播地址
		NeteaseAddressRet channelAddress = getLivePlayerAddress(channel.getCid());
		result.setHlsPullUrl(channelAddress.getHlsPullUrl());
		result.setHttpPullUrl(channelAddress.getHttpPullUrl());
		result.setRtmpPullUrl(channelAddress.getRtmpPullUrl());
		// 获取用户id和token
		String accid = generateAccid(String.valueOf(publisherId));
		String token = redisCache.get(generateImUserTokenKey(String.valueOf(publisherId)));
		if (token == null) {
			token = createUser(String.valueOf(publisherId));
			redisCache.set(generateImUserTokenKey(String.valueOf(publisherId)), token);
		}
		result.setAccid(accid);
		result.setToken(token);
		// 获取聊天室
		String roomidStr = redisCache.get(generateChatroomidKey(channel.getCid()));
		if (roomidStr == null) {
			NeteaseChatroomCreateRet chatroom = createChatroom(channel.getName() + "_直播聊天室");
			roomidStr = String.valueOf(chatroom.getRoomid());
			redisCache.set(generateChatroomidKey(channel.getCid()), roomidStr);
		}
		result.setRoomid(Long.parseLong(roomidStr));
		// 获取聊天室地址
		result.setRoomAddress(getChatroomAddress(accid, result.getRoomid(), clientType));
		return result;
	}

	private String generateAccid(String accidSuffix) {
		return domain + "_" + accidSuffix;
	}

	private String generateImUserTokenKey(String accidSuffix) {
		return NeteaseImConfig.TokenKeyPrefix + String.valueOf(accidSuffix);
	}

	private String generateChatroomidKey(String channelCid) {
		return channelCid + "_roomid";
	}

	private String createUser(String accidSuffix) {
		NeteaseUserCreateParam createParam = new NeteaseUserCreateParam();
		createParam.setAccid(generateAccid(accidSuffix));
		NeteaseImResponse<NeteaseUserCreateRet> response = ImOverHttps.doAction(NeteaseImConfig.CreateUrl, createParam,
				NeteaseImConfig.CreateRetKey, NeteaseUserCreateRet.class);
		if (response.getCode() == 200) {
			return response.getRet().getToken();
		} else if (response.getCode() == 414) {
			NeteaseRefreshTokenParam refreshParam = new NeteaseRefreshTokenParam();
			refreshParam.setAccid(generateAccid(accidSuffix));
			NeteaseImResponse<NeteaseRefreshTokenRet> refreshResponse = ImOverHttps.doAction(
					NeteaseImConfig.RefreshTokenUrl, refreshParam, NeteaseImConfig.RefreshTokenRetKey,
					NeteaseRefreshTokenRet.class);
			if (refreshResponse.getCode() == 200) {
				return refreshResponse.getRet().getToken();
			} else {
				throw new ServiceException(ExceptionConstant.UNKNOW_EXCEPTION,
						"创建网易云信IM用户失败:" + generateAccid(accidSuffix));
			}
		} else {
			throw new ServiceException(ExceptionConstant.UNKNOW_EXCEPTION,
					"创建网易云信IM用户失败:" + generateAccid(accidSuffix));
		}
	}

	private NeteaseChatroomCreateRet createChatroom(String roomName) {
		NeteaseChatroomCreateParam createParam = new NeteaseChatroomCreateParam();
		createParam.setCreator(generateAccid("admin"));
		createParam.setName(roomName);
		return ImOverHttps.doAction(NeteaseImConfig.ChatroomCreateUrl, createParam,
				NeteaseImConfig.ChatroomCreateRetKey, NeteaseChatroomCreateRet.class).getRet();
	}

	@SuppressWarnings("unchecked")
	private String getChatroomAddress(String accid, Long roomid, int clientType) {
		NeteaseChatroomRequestAddrParam addressParam = new NeteaseChatroomRequestAddrParam();
		addressParam.setAccid(accid);
		addressParam.setRoomid(roomid);
		addressParam.setClienttype(clientType);
		List<String> addressList = (List<String>) ImOverHttps.doAction(NeteaseImConfig.RequestAddrUrl, addressParam,
				NeteaseImConfig.RequestAddrRetKey, JacksonUtil.getGenericType(List.class, String.class)).getRet();
		return addressList.get(0);
	}

	private NeteaseAddressRet getLivePlayerAddress(String cid) {
		NeteaseAddressParam param = new NeteaseAddressParam();
		param.setCid(cid);
		NeteaseLiveResponse<NeteaseAddressRet> response = ChannelManageOverHttps.doAction(NeteaseLiveConfig.AddressUrl,
				param, NeteaseAddressRet.class);
		if (response.getCode() == 200) {
			return response.getRet();
		} else {
			throw new ServiceException(ExceptionConstant.UNKNOW_EXCEPTION, response.getMsg());
		}
	}

	private NeteaseChannellistRet getCurrentLivePlayer() {
		NeteaseChannellistRet result = null;

		NeteaseChannellistParam listParam = new NeteaseChannellistParam();
		listParam.setPnum(1);
		listParam.setRecords(100);
		JavaType listJavaType = JacksonUtil.getGenericType(NeteaseLivePage.class, NeteaseChannellistRet.class);
		NeteaseLiveResponse<NeteaseLivePage<NeteaseChannellistRet>> listResponse = ChannelManageOverHttps
				.doAction(NeteaseLiveConfig.ChannellistUrl, listParam, listJavaType);
		if (listResponse.getCode() == 200) {
			String currentCid = redisCache.get(NeteaseLiveConfig.CurrentLivePlayerKey);
			List<NeteaseChannellistRet> content = listResponse.getRet().getList();
			if (content != null && content.size() > 0) {
				boolean isMatch = false;
				if (currentCid != null) {
					for (NeteaseChannellistRet channel : content) {
						if (channel.getCid().equals(currentCid)) {
							channel.setCurrent(true);
							isMatch = true;
							result = channel;
						}
					}
				}
				if (!isMatch) {
					result = content.get(content.size() - 1);
				}
			} else {
				throw new ServiceException(ExceptionConstant.NO_LIVEPLAYER_EXCEPTION);
			}
			return result;
		} else {
			throw new ServiceException(ExceptionConstant.NO_LIVEPLAYER_EXCEPTION);
		}
	}

}
