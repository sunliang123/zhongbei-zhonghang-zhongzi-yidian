package com.waben.stock.interfaces.constants;

/**
 * @author Created by yuyidi on 2017/9/24.
 * @desc
 */
public class ExceptionConstant {

	// 系统内部异常
	public static final String NETFLIX_CIRCUIT_EXCEPTION = "1000";
	public static final String UNKNOW_EXCEPTION = "1001";
	public static final String DATETIME_ERROR = "1002";
	public static final String DATANOTFOUND_EXCEPTION = "1003";
	public static final String ARGUMENT_EXCEPTION = "1004";
	public static final String WARNING_EXCEPTION = "1005";

	// 发送短信失败
	public static final String SENDMESSAGE_FAILED_EXCEPTION = "1005";
	// 短信发送间隔时间太短
	public static final String SENDMESSAGE_INTERVAL_TOOSHORT_EXCEPTION = "1006";
	// 验证码错误或者验证码已过期
	public static final String VERIFICATIONCODE_INVALID_EXCEPTION = "1007";

	// 发布人服务异常
	// 该手机号已被注册
	public static final String PHONE_BEEN_REGISTERED_EXCEPTION = "2001";
	// 错误的手机号码
	public static final String PHONE_WRONG_EXCEPTION = "2002";
	// 用户名或者密码错误
	public static final String USERNAME_OR_PASSWORD_ERROR_EXCEPTION = "2003";
	// 该手机号尚未注册
	public static final String PHONE_ISNOT_REGISTERED_EXCEPTION = "2004";
	// 该银行卡已绑定，不能重复绑定
	public static final String BANKCARD_ALREADY_BIND_EXCEPTION = "2005";
	// 该股票已收藏，不能重复收藏
	public static final String STOCK_ALREADY_FAVORITE_EXCEPTION = "2006";
	// 原始密码不匹配
	public static final String ORIGINAL_PASSWORD_MISMATCH_EXCEPTION = "2007";
	// 手机号不匹配
	public static final String PHONE_MISMATCH_EXCEPTION = "2008";
	// 已经设置过支付密码，修改支付密码需要验证码
	public static final String MODIFY_PAYMENTPASSWORD_NEEDVALIDCODE_EXCEPTION = "2009";
	// 不能绑定信用卡
	public static final String CREDITCARD_NOTSUPPORT_EXCEPTION = "2010";
	// 未找到该卡号对应的银行，请检查输入的信息是否正确
	public static final String BANKCARD_NOTRECOGNITION_EXCEPTION = "2011";
	// 不支持的银行卡号
	public static final String BANKCARD_NOTSUPPORT_EXCEPTION = "2012";
	// 用户不匹配
	public static final String PUBLISHERID_NOTMATCH_EXCEPTION = "2013";
	// 银行卡信息有误，请检查输入的信息是否正确
	public static final String BANKCARDINFO_NOTMATCH_EXCEPTION = "2014";
	// 信息输入有误
	public static final String BANKCARDINFO_WRONG_EXCEPTION = "2015";
	// 已实名认证，不能重复操作
	public static final String REALNAME_EXIST_EXCEPTION = "2016";
	// 实名认证信息错误
	public static final String REALNAME_WRONG_EXCEPTION = "2017";
	// 代理商代码不存在
	public static final String ORGCODE_NOTEXIST_EXCEPTION = "2018";
	// 该银行卡已被使用
	public static final String BANKCARD_ALREADY_USERED_EXCEPTION = "2019";
	// 该实名信息已被使用
	public static final String REALNAME_ALREADY_USERED_EXCEPTION = "2020";
	// 资金账户已冻结，不能执行资金相关的操作
	public static final String CAPITALACCOUNT_FROZEN_EXCEPTION = "2021";
	// 您的账号已被冻结无法登录
	public static final String PUBLISHER_DISABLED_EXCEPITON = "2022";
	// 用户修改不成功，有未完成订单
	public static final String MODIFY_DISABLED_EXCEPITON = "2023";
	// 您的账户尚未实名认证
	public static final String NOTREALNAME_EXEPTION = "2024";
	// 身份证号码格式有误
	public static final String IDCARD_FORMAT_WRONG_EXCEPTION = "2025";
	// 年龄必须介于18~65周岁
	public static final String AGENOTBETTEN18AND65_EXCEPTION = "2026";

	/* 业务异常 */
	// 系统管理业务异常
	// 菜单服务异常
	public static final String MENU_SERVICE_EXCEPTION = "3001";
	// 权限服务异常
	public static final String SECURITY_METHOD_UNSUPPORT_EXCEPTION = "3002";
	// 员工服务异常
	public static final String STAFF_NOT_FOUND_EXCEPTION = "3003";
	// 角色服务异常
	public static final String ROLE_NOT_FOUND_EXCEPTION = "3004";
	public static final String PERMISSION_NOT_FOUND_EXCEPTION = "3005";

	// 点买服务、股票异常
	// 账户可用余额不足
	public static final String AVAILABLE_BALANCE_NOTENOUGH_EXCEPTION = "6001";
	// 买入或者卖出前需进行锁定操作
	public static final String BUYRECORD_ISNOTLOCK_EXCEPTION = "6002";
	// 投资人必须和买入锁定时的投资人一致
	public static final String BUYRECORD_INVESTORID_NOTMATCH_EXCEPTION = "6003";
	// 点买记录状态不匹配，不支持该操作
	public static final String BUYRECORD_STATE_NOTMATCH_OPERATION_NOTSUPPORT_EXCEPTION = "6004";
	// 申请卖出发布人不匹配，不支持该操作
	public static final String BUYRECORD_PUBLISHERID_NOTMATCH_EXCEPTION = "6005";
	// 点买记录不存在
	public static final String BUYRECORD_NOT_FOUND_EXCEPTION = "6006";
	// 点买发布扣款发生异常，如账户已扣款，请联系客服人员
	public static final String BUYRECORD_POST_DEBITFAILED_EXCEPTION = "6007";
	// 支付密码未设置
	public static final String PAYMENTPASSWORD_NOTSET_EXCEPTION = "6008";
	// 支付密码错误
	public static final String PAYMENTPASSWORD_WRONG_EXCEPTION = "6009";
	// 点买异常
	public static final String BUYRECORD_SAVE_EXCEPTION = "6010";
	// 申请卖出只能由发布人本人操作
	public static final String BUYRECORD_SELLAPPLY_NOTMATCH_EXCEPTION = "6011";
	// 退回保证金发生异常
	public static final String BUYRECORD_RETURNRESERVEFUND_EXCEPTION = "6012";
	// 非交易时间段
	public static final String BUYRECORD_NONTRADINGPERIOD_EXCEPTION = "6013";
	// 该股票已停牌
	public static final String STOCK_SUSPENSION_EXCEPTION = "6014";
	// 提现失败
	public static final String WITHDRAWALS_EXCEPTION = "6015";
	// 充值失败
	public static final String RECHARGE_EXCEPTION = "6016";
	// 用户选择不递延，不能进行递延操作
	public static final String BUYRECORD_USERNOTDEFERRED_EXCEPTION = "6017";
	// 该点买记录已经递延过了，不能重复递延
	public static final String BUYRECORD_ALREADY_DEFERRED_EXCEPTION = "6018";
	// 当前用户为测试用户，不能进行提现操作
	public static final String TESTUSER_NOWITHDRAWALS_EXCEPTION = "6019";
	// 当前用户不能参与该策略，或该策略为一次性参与活动且当前用户已经参与
	public static final String STRATEGYQUALIFY_NOTENOUGH_EXCEPTION = "6020";
	// 申请市值不足购买一手
	public static final String APPLYAMOUNT_NOTENOUGH_BUYSTOCK_EXCEPTION = "6021";
	// 持仓第二天之后才能申请卖出
	public static final String USERSELLAPPLY_NOTMATCH_EXCEPTION = "6022";
	// 买入中和买入锁定状态下才能撤单
	public static final String BUYRECORD_REVOKE_NOTSUPPORT_EXCEPTION = "6023";
	// 不支持购买创业板的股票
	public static final String DEVELOPSTOCK_NOTSUPPORT_EXCEPTION = "6024";
	// 银联支付单笔最大额度为5000
	public static final String UNIONPAY_SINGLELIMIT_EXCEPTION = "6025";
	// 该股票已涨停，不能购买
	public static final String STOCK_ARRIVEUPLIMIT_EXCEPTION = "6026";
	// 该股票已跌停，不能购买
	public static final String STOCK_ARRIVEDOWNLIMIT_EXCEPTION = "6027";
	// ST股无法申购请购买其它股票
	public static final String ST_STOCK_CANNOTBUY_EXCEPTION = "6028";
	// 不支持的股票，请更换股票
	public static final String BLACKLIST_STOCK_EXCEPTION = "6029";
	// 连续两个涨停的股票不能申购
	public static final String STOCKOPTION_2UPLIMIT_CANNOTBY_EXCEPTION = "6030";
	// 请求充值失败
	public static final String REQUEST_RECHARGE_EXCEPTION = "6031";
	// 单笔提现不能超过10万
	public static final String SINGLE_WITHDRAWAL_LIMIT_EXCEPTION = "6032";

	// 投资人服务异常
	public static final String INVESTOR_NOT_FOUND_EXCEPTION = "7001";
	public static final String INVESTOR_SECURITIES_LOGIN_EXCEPTION = "7002";
	public static final String INVESTOR_STOCKACCOUNT_MONEY_NOT_ENOUGH = "7003";
	public static final String INVESTOR_EXCHANGE_TYPE_NOT_SUPPORT_EXCEPTION = "7004";
	public static final String INVESTOR_STOCKACCOUNT_NOT_EXIST = "7005";
	public static final String INVESTOR_STOCKENTRUST_BUY_ERROR = "7006";
	public static final String INVESTOR_STOCKENTRUST_FETCH_ERROR = "7007";

	// 代理商服务异常
	// 代理商不存在
	public static final String ORGANIZATION_NOTEXIST_EXCEPTION = "8001";
	// 代理商类别不存在
	public static final String ORGANIZATIONCATEGORY_NOTEXIST_EXCEPTION = "8002";
	// 代理商用户不存在
	public static final String ORGANIZATION_USER_NOT_FOUND = "8003";
	// 原始支付密码不匹配
	public static final String ORGANIZATIONACCOUNT_OLDPAYMENTPASSWORD_NOTMATCH_EXCEPTION = "8004";
	// 代理商用户已存在
	public static final String ORGANIZATION_USER_EXIST = "8005";
	// 原始登陆密码不匹配
	public static final String ORGUSER_OLDPASSWORD_NOTMATCH_EXCEPTION = "8006";
	// 该发布人已绑定过机构码
	public static final String ORGPUBLISHER_EXIST_EXCEPTION = "8007";
	// 尚未绑卡，不能申请提现
	public static final String WITHDRAWALSAPPLY_NOTSUPPORTED_EXCEPTION = "8008";
	// 代理商名称已存在
	public static final String ORGNAME_EXIST_EXCEPTION = "8009";
	// 一级代理商不能申请提现
	public static final String LEVELONE_CANNOT_WITHDRAWAL_EXCEPTION = "8010";
	// 账户余额不足以冻结
	public static final String BALANCE_NOTENOUGHFROZEN_EXCEPTION = "8011";
	// 当前用户已被冻结
	public static final String AGENT_DISABLED_EXCEPITON = "8012";
	// 该角色已被使用
	public static final String USER_ROLE_EXCEPITON = "8013";
	// 该结算下代理商尚有未完成订单，请完成订单后再切换结算方式
	public static final String SETTLEMENT_METHOD_EXCEPITON = "8014";
	// 上级的比例不能为空,请设置上级表单比例
	public static final String FORM_RATIO_EXCEPITON = "8015";
	// 当前的表单的比例不能大于上级的比例
	public static final String FORM_RATIO_COMPARE_EXCEPITON = "8016";
	// 该角色已存在，请重新输入
	public static final String ROLE_EXISTENCE_EXCEPITON = "8017";
	// 返佣比例设置错误，当前比例不能大于上级比例
	public static final String RAKEBACK_RATIO_WRONG_EXCEPTION = "8018";
	// 提现金额不足以支付提现手续费
	public static final String PROCESSFEE_NOT_ENOUGH_EXCEPTION = "8019";

	// 期权服务异常
	// 策略金额5万起，且必须为5万的整数倍
	public static final String STOCKOPTION_AMOUNTMUSTGT20WAN_EXCEPTION = "9001";
	// 交易状态不匹配，不支持该操作
	public static final String STOCKOPTION_STATE_NOTMATCH_OPERATION_NOTSUPPORT_EXCEPTION = "9002";
	// 自主行权发布人不匹配，不支持该操作
	public static final String STOCKOPTION_PUBLISHERID_NOTMATCH_EXCEPTION = "9003";
	// T+3才能申请行权
	public static final String USERRIGHT_NOTMATCH_EXCEPTION = "9004";
	// 该股票暂时无法申购，请购买其它股票
	public static final String STOCKOPTION_QUOTENOTFOUND_EXCEPTION = "9005";
	// 询价结果不存在
	public static final String INQUIRY_RESULT_NOT_FOUND = "9006";
	// 非交易日不能申请卖出
	public static final String NONTRADINGDAY_EXCEPTION = "9007";
	// 该股票暂时无法申购，请购买其它股票
	public static final String STOCK_ABNORMAL_EXCEPTION = "9008";
	// 今日额度已用完，明日09：00开售
	public static final String STOCK_AMOUNTLIMIT_EXCEPTION = "9009";
	// 该股票最大额度为%s万，请重新输入
	public static final String STOCK_AMOUNTGTLEFT_EXCEPTION = "9010";

	public static final String NO_TRADING_TIMES = "9011";

	// 直播
	// 无直播频道
	public static final String NO_LIVEPLAYER_EXCEPTION = "10001";

	// 抽奖活动
	// 抽奖次数不足
	public static final String INSUFFICIENT_NUMBER_OF_DRAW = "11001";
	// 抽奖次数上限
	public static final String OVERSTEP_NUMBER_OF_DRAW = "11002";
	// 奖品已空
	public static final String PRIZE_IS_EMPTY = "11003";

	// 上传文件
	// 上传图片失败
	public static final String FAILED_TO_UPLOAD_PICTURES = "12001";
}
