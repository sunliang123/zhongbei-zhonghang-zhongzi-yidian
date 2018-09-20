package com.waben.stock.interfaces.commonapi.netease.imbean;

public class NeteaseUserCreateParam {

	/**
	 * 网易云通信ID，必填
	 * <p>
	 * 网易云通信ID，最大长度32字符，必须保证一个 APP内唯一（只允许字母、数字、半角下划线_、 @、半角点以及半角-组成，不区分大小写，
	 * 会统一小写处理，请注意以此接口返回结果中的accid为准）。
	 * </p>
	 */
	private String accid;
	/**
	 * 网易云通信ID昵称，选填
	 * <p>
	 * 网易云通信ID昵称，最大长度64字符，用来PUSH推送时显示的昵称
	 * </p>
	 */
	private String name;
	/**
	 * json属性，选填
	 * <p>
	 * json属性，第三方可选填，最大长度1024字符
	 * </p>
	 */
	private String props;
	/**
	 * 网易云通信ID头像URL，选填
	 * <p>
	 * 网易云通信ID头像URL，第三方可选填，最大长度1024
	 * </p>
	 */
	private String icon;
	/**
	 * 网易云通信ID可以指定登录token值，选填
	 * <p>
	 * 网易云通信ID可以指定登录token值，最大长度128字符， 并更新，如果未指定，会自动生成token，并在 创建成功后返回
	 * </p>
	 */
	private String token;
	/**
	 * 用户签名，选填
	 * <p>
	 * 用户签名，最大长度256字符
	 * </p>
	 */
	private String sign;
	/**
	 * 用户email，选填
	 * <p>
	 * 用户email，最大长度64字符
	 * </p>
	 */
	private String email;
	/**
	 * 用户生日，选填
	 * <p>
	 * 用户生日，最大长度16字符
	 * </p>
	 */
	private String birth;
	/**
	 * 用户mobile，选填
	 * <p>
	 * 用户mobile，最大长度32字符
	 * </p>
	 */
	private String mobile;
	/**
	 * 用户性别，选填
	 * <p>
	 * 用户性别，0表示未知，1表示男，2女表示女，其它会报参数错误
	 * </p>
	 */
	private int gender;
	/**
	 * 用户名片扩展字段，选填
	 * <p>
	 * 用户名片扩展字段，最大长度1024字符，用户可自行扩展，建议封装成JSON字符串
	 * </p>
	 */
	private String ex;

	public String getAccid() {
		return accid;
	}

	public void setAccid(String accid) {
		this.accid = accid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProps() {
		return props;
	}

	public void setProps(String props) {
		this.props = props;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getEx() {
		return ex;
	}

	public void setEx(String ex) {
		this.ex = ex;
	}

}
