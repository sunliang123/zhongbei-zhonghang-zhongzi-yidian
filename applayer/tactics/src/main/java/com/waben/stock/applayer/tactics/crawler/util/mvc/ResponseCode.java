package com.waben.stock.applayer.tactics.crawler.util.mvc;

/**
 * Created by yhj on 17/2/23.
 */
public class ResponseCode {
    public static final int SUCCESS = 200;  //  | 成功 |
    public static final Integer REPEAT_CODE = 201;

    /**
     * 之前遗留 的代码 都禁止使用了
     **/
    @Deprecated
    public static final Integer SUCCESS_CODE = SUCCESS;
    public static final Integer ERROR_CODE = 600;
    public static final Integer ORDER_ERROR_CODE = 701;
    public static final Integer COTERIE_ERROR_CODE = 702;
    /** end **/

    /**
     * 认证相关
     */
    public static final int AUTH_FAIL = 210;  //  | 用户认证失败 |
    public static final int AUTH_NOT_EXIST = 211;  //  | 用户不存在 |
    public static final int AUTH_KEY_ERROR = 212;  //  | 用户秘钥认证失败 |
    public static final int MSG_CODE_TIMEOUT = 213; //  | 消息验证码失效|
    public static final int WECHAT_NOT_BOUND = 214;//|微信尚未绑定
    public static final int AUTH_IMG_CODE = 215;//需要进行图片验证
    public static final int IMG_CODE_TIMEOUT = 216;//图片验证码超时失效

    /**
     * 权限相关
     */
    public static final int PERMISSION_DENY = 251;  //  | 没有访问权限 |
    public static final int PERMISSION_REQ = 252;  //  | 请联系管理员分配 |


    /**
     * 请求参数相关
     */
    public static final int PARAM_ERROR = 300;  //  | 参数错误 |
    public static final int PARAM_METHOD_JSON = 301;  //  | 请求参数格式需要为application/json |
    public static final int PARAM_METHOD_HTTP = 303;  //  | 请求必须是HTTP请求 |
    public static final int PARAM_METHOD_ERROR = 302;  //  | 请求必须是HTTP请求 |
    public static final int PARAM_METHOD_GET = 304;  //  | 请求必须是GET请求 |
    public static final int PARAM_METHOD_POST = 305;  //  | 请求必须是POST请求 |

    public static final int PARAM_TYPE_NUM = 320;  //  | 参数必须为数字 |
    public static final int PARAM_NUM_DOUBLE = 321;  //  | 参数为数字且必须是小数 |
    public static final int PARAM_NUM_INT = 322;  //  | 参数为字符串且必须是整数 |
    public static final int PARAM_NUM_FORMAT = 323;  //  | 参数为字符串且必须符合特定格式 |

    public static final int PARAM_TYPE_STRING = 340;  //  | 参数必须为字符串 |
    public static final int PARAM_STR_NOT_EMPTY = 341;  //  | 字符串不能为空 |
    public static final int PARAM_STR_TOO_LONG = 342;  //  | 参数为字符串且长度太长 |
    public static final int PARAM_STR_CH = 345;  //  | 参数为字符串且需要为中文 |
    public static final int PARAM_STR_EN = 346;  //  | 参数为字符串且需要为英文 |
    public static final int PARAM_STR_NUMBER = 347;  //  | 参数为字符串且需要为数字 |
    public static final int PARAM_STR_UNSPECIAL = 348;  //  | 参数为字符串且需要不能包含特殊字符 |
    public static final int PARAM_STR_EMAIL = 351;  //  | 参数为字符串且需要为邮箱 |
    public static final int PARAM_STR_IDCARD = 352;  //  | 参数为字符串且需要为身份证 |

    public static final int PARAM_TYPE_DATE = 360;  //  | 参数必须为时间类型 |
    public static final int PARAM_DATE_TIME = 362;  //  | 参数必须为时间类型(HH:mm:ss) |
    public static final int PARAM_DATE_DATE = 364;  //  | 参数必须为日期类型(yyyy-MM-dd) |
    public static final int PARAM_DATE_DATETIME = 366;  //  | 参数必须为长时间类型(yyyy-MM-dd HH:mm:ss) |

    /**
     * 资源相关
     */

    public static final int RESOURCE_NOT_FOUND = 404;  //  | 请求没有被找到 |

    /**
     * 服务器相关
     */

    public static final int SERVER_ERROR = 500;  //  | 服务器内部异常 |
    public static final int SERVER_ERROR_CPU = 501;  //  | 服务器CPU异常 |
    public static final int SERVER_ERROR_MEM = 502;  //  | 服务器MEM异常 |
    public static final int SERVER_ERROR_DISK = 503;  //  | 服务器DISK异常 |
    public static final int SERVER_DB_ERROR = 510;  //  | 数据库请求异常 |
    public static final int SERVER_DB_UNCONN = 511;  //  | 数据库连接不可用 |
    public static final int SERVER_DB_TIMEOUT = 512;  //  | 数据库请求超时 |
    public static final int SERVER_MD_ERROR = 520;  //  | 中间件异常 |
    public static final int SERVER_MD_ZOO = 521;  //  | 中间件异常(ZOOKEPER) |
    public static final int SERVER_MD_MQ = 522;  //  | 中间件异常(MQ) |
    public static final int SERVER_MD_LOCK = 523;  //  | 中间件异常(LOCK) |


    /**
     * 内部服务器异常.
     */
    public static final int INNER_ERROR = 600; //内部调用错误.

    public static final int INNER_RESULT_ERROR = 610; // 返回数据异常.
    public static final int INNER_RESULT_JSON_ERROR = 611; // 返回JSON解析异常.
    public static final int INNER_RESULT_CODE_ERROR = 612; // 返回Code 不存在.

    public static final int INNER_RESULT_DATA_ERROR = 630;    //返回data异常.
    public static final int INNER_JSON_ERROR = 631;    //返回data不存在

    public static final int INNER_CHECK_LIST_ERROR = 640;    //commentMongo中要检查的List不存在
    public static final int INNER__CONTENT_PICTURE_GREEN_ERROR = 660;    //没有配置图片检测类型
    public static final int INNER__CONTENT_SCAN_ERROR = 661;    //没有配置图片检测类型

    /**
     * 用户相关
     */
    public static final int USER_NOT_EXSIT = 2000;// 用户不存在
    public static final int USER_BACK_LIST = 2001;// 用户存在于黑名单
    public static final int USER_UNVILIDATE = 2002;// 用户失效
    public static final int USER_STATUS_ERROR = 2003;// 用户状态异常
    public static final int USER_IS_EXSIT = 2004; //用户已存在
    public static final int USER_MSG_ERROR = 2005; //验证码错误
    public static final int USER_PASS_ERROR = 2006; //密码错误
    public static final int USER_PASS_LENGTH_ERROR = 2007; //密码长度错误
    public static final int USER_PASS_ILLEGAL = 2008; //密码中包含非法字符
    public static final int USER_PASS_NOT_SET = 2009;  //未设置密码
    // 用户基础信息
    public static final int USER_INFO_PHONE_FAIL = 2010;// 号码不存在
    public static final int USER_INFO_NAME_FAIL = 2011;// 用户名字不存在
    public static final int USER_PHONE_ERROR = 2012; //手机号码不正确
    public static final int USER_USE_ERROR = 2013; //用户账户异常

    /**
     * 用户认证信息 2100-2199.
     */
    public static final int USER_CERT_ERROR = 2100;// 用户认证异常
    public static final int USER_CERT_NOT = 2101;// 用户没有认证
    public static final int USER_CERT_CARD_ERROR = 2102;// 用户身份证错误
    public static final int USER_CERT_PHONE_ERROR = 2103;// 用户认证号码错误
    public static final int USER_CERT_WEIXIN = 2140;// 微信获取token异常

    /**
     * 用户金额相关.2200-2299.
     */
    public static final int USER_MONEY_ERROR = 2200;// 资金账户错误.
    public static final int USER_MONEY_NOT_ENOUGH = 2201; // 余额不足
    public static final int USER_MONEY_SAFE_NOT_SET = 2202;  //未设置安全密码
    public static final int USER_MONEY_SAFE_ERROR = 2203;  //安全密码错误 
    public static final int USER_MONEY_EXCHANGE_IS_NULL = 2204;  //兑换项目不存在
    public static final int USER_MONEY_EXCHANGE_IS_UPDATE = 2205;  //兑换项目已修改
    public static final int USER_MONEY_EXCESS_MAX_MONEY = 2207;  //金额超过最高限制

    /**
     * 行情相关
     */
    public static final int QUOTA_SETTING_ERROR = 3000;//行情设置问题

    /**
     * 消息
     */
    public static final int MSG_NOT_EXSIT = 4000;


    /**
     * 游戏4500 - 4599
     *
     * @See {@org.songbai.variety.game.util.GameResponseCode}
     */
    public static final int Game_code = 4500;


    /**
     * 训练相关 4600- 4699
     */
    public static final int TRAIN_CODE = 4600;

    /**
     * 姐说 4700-4799
     */
    public static final int CUSTOM_NOT_EXIST = 4700;   //小姐姐不存在
    public static final int QUESTION_NOT_EXIST = 4701; //提问不存在
    public static final int QUESTION_ALREADY_RUSH = 4702; //此问题已被人抢走
    public static final int QUESTION_ALREADY_SOLVE = 4704; //此问题已被解决
    public static final int CUSTOM_NOT_LOGIN = 4703;  //客服未登录
    public static final int CUSTOM_ALREADY_EXIST = 4705; //小姐姐已存在
    public static final int SAVANT_ALREADY_NOT_YET = 4706;//专家不存在或已被删除
    public static final int VOICE_IS_LISTENER = 4707; //语音已听过
    public static final int RADIO_NOT_EXIST =4708;//电台不存在
    public static final int AUDIO_NOT_EXIST = 4709;//音频不存在
    public static final int QUESTION_RUSH_TO_ANSWER_FAIL=4710;//已被抢答；
    public static final int TOPIC_NOT_EXIST = 4711;//话题不存在
    public static final int TOPIC_ALREADY_PRISE = 4712;//已经点赞
    public static final int CUSTOMID_IS_NULL = 4713;//小姐姐id为空
    public static final int COMMENT_NOT_EXIST = 4714;//评论不存在
    public static final int TOPICID_IS_NULL = 4715;//话题id为空
    public static final int RADIO_ALREADY_EXIST = 4716;//电台名已存在
    public static final int COMMENT_BOYOND_LIMIT = 4717;//评论字数超出限制

    /**
     * 排行榜 4800 - 4899
     */
    public static final int ALREADY_WORSHIP = 4800; //已膜拜


    /**
     * 排行榜 5100 - 5199
     */
    public static final int activity = 5100; //活动的代码


}
