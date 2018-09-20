package com.waben.stock.interfaces.service.stockoption;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.waben.stock.interfaces.dto.admin.stockoption.StockOptionAdminDto;
import com.waben.stock.interfaces.dto.admin.stockoption.StockOptionBlacklistAdminDto;
import com.waben.stock.interfaces.dto.admin.stockoption.StockOptionRiskAdminDto;
import com.waben.stock.interfaces.dto.promotion.stockoption.StockOptionPromotionDto;
import com.waben.stock.interfaces.dto.promotion.stockoption.StockOptionStaDto;
import com.waben.stock.interfaces.dto.stockoption.StockOptionAmountLimitDto;
import com.waben.stock.interfaces.dto.stockoption.StockOptionQuoteDto;
import com.waben.stock.interfaces.dto.stockoption.StockOptionTradeDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.StockOptionTradeQuery;
import com.waben.stock.interfaces.pojo.query.StockOptionTradeUserQuery;
import com.waben.stock.interfaces.pojo.query.admin.stockoption.StockOptionAdminQuery;
import com.waben.stock.interfaces.pojo.query.admin.stockoption.StockOptionRiskAdminQuery;
import com.waben.stock.interfaces.pojo.query.promotion.stockoption.StockOptionPromotionQuery;

public interface StockOptionTradeInterface {

	/**
	 * 根据ID从查询期权交易
	 * 
	 * @param id
	 *            期权交易ID
	 * @return 期权交易
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	Response<StockOptionTradeDto> fetchById(@PathVariable("id") Long id);

	/**
	 * 分页查询期权交易（管理后台）
	 * 
	 * @param query
	 *            查询条件
	 * @return 期权交易分页数据
	 */
	@RequestMapping(value = "/adminpages", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	Response<PageInfo<StockOptionAdminDto>> adminPagesByQuery(@RequestBody StockOptionAdminQuery query);
	
	/**
	 * 分页查询期权交易（代理商后台）
	 * 
	 * @param query
	 *            查询条件
	 * @return 期权交易分页数据
	 */
	@RequestMapping(value = "/promotionpages", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	Response<PageInfo<StockOptionPromotionDto>> promotionPagesByQuery(@RequestBody StockOptionPromotionQuery query);
	
	/**
	 * 统计名义本金、权利金总和
	 * 
	 * @param query
	 *            查询条件
	 * @return 名义本金、权利金总和
	 */
	@RequestMapping(value = "/promotionSta", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	Response<StockOptionStaDto> promotionSta(@RequestBody StockOptionPromotionQuery query);

	/**
	 * 分页查询期权交易（终端用户）
	 * 
	 * @param query
	 *            查询条件
	 * @return 期权交易分页数据
	 */
	@RequestMapping(value = "/userpages", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	Response<PageInfo<StockOptionTradeDto>> pagesByUserQuery(@RequestBody StockOptionTradeUserQuery query);
	
	/**
	 * 分页查询期权交易（终端用户）
	 * 
	 * @param query
	 *            查询条件
	 * @return 期权交易分页数据
	 */
	@RequestMapping(value = "/tradeDynamicQuery", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	Response<PageInfo<StockOptionTradeDto>> tradeDynamicQuery(@RequestBody StockOptionTradeUserQuery query);

	/**
	 * 分页查询期权申购信息
	 *
	 * @param query
	 *            查询条件
	 * @return 结算记录
	 */
	@RequestMapping(value = "/pages", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	Response<PageInfo<StockOptionTradeDto>> pagesByQuery(@RequestBody StockOptionTradeQuery query);

	/**
	 * 添加期权申购，此时状态为“待确认”
	 *
	 * @param stockOptionTradeDto
	 *            期权交易信息
	 * @return 期权交易信息
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	Response<StockOptionTradeDto> add(@RequestBody StockOptionTradeDto stockOptionTradeDto);

	/**
	 * 用户申请提前行权，此时状态为“申请行权”
	 *
	 * @param publisherId
	 *            发布人ID
	 * @param id
	 *            期权交易id
	 * @return 期权交易
	 */
	@RequestMapping(value = "/{publisherId}/userright/{id}", method = RequestMethod.PUT)
	Response<StockOptionTradeDto> userRight(@PathVariable("publisherId") Long publisherId, @PathVariable("id") Long id);

	/**
	 * 撤单
	 * 
	 * @param id
	 *            期权交易ID
	 * @return 期权交易
	 */
	@RequestMapping(value = "/fail/{id}", method = RequestMethod.PUT)
	Response<StockOptionTradeDto> fail(@PathVariable("id") Long id);

	/**
	 * 正式成交
	 * <p>
	 * 向机构申购成功，设置成交价格、机构权利金比例
	 * </p>
	 * 
	 * @param id
	 *            交易ID
	 * @param orgId
	 *            机构ID
	 * @param orgRightMoneyRatio
	 *            机构权利金比例
	 * @param buyingPrice
	 *            买入价格
	 * @return 期权交易
	 */
	@RequestMapping(value = "/turnover/{id}/org/{orgid}", method = RequestMethod.PUT)
	Response<StockOptionTradeDto> turnover(@PathVariable("id") Long id, @PathVariable("orgid") Long orgid,
			@RequestParam("orgRightMoneyRatio") BigDecimal orgRightMoneyRatio,
			@RequestParam("buyingPrice") BigDecimal buyingPrice);

	/**
	 * 标记某条期权交易
	 * 
	 * @param id
	 *            期权交易ID
	 * @return 期权交易
	 */
	@RequestMapping(value = "/mark/{id}", method = RequestMethod.PUT)
	Response<StockOptionTradeDto> mark(@PathVariable("id") Long id, @RequestParam("isMark") Boolean isMark);

	/**
	 * 正式行权结算，状态变更为结算中
	 * 
	 * @param id
	 *            期权交易ID
	 * @return 期权交易
	 */
	@RequestMapping(value = "/insettlement/{id}", method = RequestMethod.PUT)
	Response<StockOptionTradeDto> insettlement(@PathVariable("id") Long id,
			@RequestParam("sellingPrice") BigDecimal sellingPrice);
	
	/**
	 * 给用户结算，状态变更为结算
	 * 
	 * @param id
	 *            期权交易ID
	 * @return 期权交易
	 */
	@RequestMapping(value = "/dosettlement/{id}", method = RequestMethod.PUT)
	Response<StockOptionTradeDto> dosettlement(@PathVariable("id") Long id);

	/**
	 * 分页查询可正常期权交易的股票（风控）
	 * 
	 * @param query
	 *            查询条件
	 * @return 可正常期权交易的股票分页数据
	 */
	@RequestMapping(value = "/risk/normal/adminpages", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	Response<PageInfo<StockOptionRiskAdminDto>> adminNormalRiskPagesByQuery(
			@RequestBody StockOptionRiskAdminQuery query);

	/**
	 * 分页查询可异常期权交易的股票（风控）
	 * 
	 * @param query
	 *            查询条件
	 * @return 期权交易异常的股票分页数据
	 */
	@RequestMapping(value = "/risk/abnormal/adminpages", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	Response<PageInfo<StockOptionRiskAdminDto>> adminAbnormalRiskPagesByQuery(
			@RequestBody StockOptionRiskAdminQuery query);

	/**
	 * 分页查询期权交易黑名单股票（风控）
	 * 
	 * @param query
	 *            查询条件
	 * @return 期权交易黑名单股票分页数据
	 */
	@RequestMapping(value = "/risk/black/adminpages", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	Response<PageInfo<StockOptionBlacklistAdminDto>> adminBlackRiskPagesByQuery(
			@RequestBody StockOptionRiskAdminQuery query);

	/**
	 * 设置期权交易限额
	 * 
	 * @param stockCode
	 *            股票代码
	 * @param stockName
	 *            股票名称
	 * @param isGlobal
	 *            是否为全局设置
	 * @param amountLimit
	 *            限额
	 * @return 期权交易限额
	 */
	@RequestMapping(value = "/amountlimit", method = RequestMethod.PUT)
	Response<StockOptionAmountLimitDto> modifyStockOptionLimit(@RequestParam("stockCode") String stockCode,
			@RequestParam("stockName") String stockName, @RequestParam("isGlobal") Boolean isGlobal,
			@RequestParam("amountLimit") BigDecimal amountLimit);

	/**
	 * 删除期权交易限额
	 * 
	 * @param stockCode
	 *            股票代码
	 * @return 股票代码
	 */
	@RequestMapping(value = "/amountlimit", method = RequestMethod.DELETE)
	Response<String> deleteStockOptionLimit(@RequestParam("stockCode") String stockCode);

	/**
	 * 获取全局期权交易限额
	 * 
	 * @param stockCode
	 *            股票代码
	 * @return 全局期权交易限额
	 */
	@RequestMapping(value = "/amountlimit/global", method = RequestMethod.GET)
	Response<StockOptionAmountLimitDto> fetchGlobalStockOptionLimit();

	/**
	 * 设置平台期权报价
	 * 
	 * @param stockCode
	 *            股票代码
	 * @param stockName
	 *            股票名称
	 * @param cycle
	 *            期权周期
	 * @param rightMoneyRatio
	 *            权利金比例
	 * @return 期权报价
	 */
	@RequestMapping(value = "/modify/quote", method = RequestMethod.PUT)
	Response<StockOptionQuoteDto> modifyStockOptionQuote(@RequestParam("stockCode") String stockCode,
			@RequestParam("stockName") String stockName, @RequestParam("cycle") Integer cycle,
			@RequestParam("rightMoneyRatio") BigDecimal rightMoneyRatio);

	@Deprecated
	@RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
	Response<StockOptionTradeDto> modify(@PathVariable("id") Long id);

	@Deprecated
	@RequestMapping(value = "/settlement/{id}", method = RequestMethod.PUT)
	Response<StockOptionTradeDto> settlement(@PathVariable("id") Long id);

	@Deprecated
	@RequestMapping(value = "/success/{id}", method = RequestMethod.PUT)
	Response<StockOptionTradeDto> success(@PathVariable("id") Long id);

	@RequestMapping(value = "/exercise/{id}", method = RequestMethod.PUT)
	Response<StockOptionTradeDto> exercise(@PathVariable("id") Long id);

	@RequestMapping(value = "/state/{state}", method = RequestMethod.GET)
	Response<List<StockOptionTradeDto>> stockOptionsWithState(@PathVariable("state") Integer state);

	@RequestMapping(value = "/duetreatment/{id}", method = RequestMethod.GET)
	Response<StockOptionTradeDto> dueTreatmentExercise(@PathVariable("id") Long id);
	
	@RequestMapping(value = "/count/stockoptiontrade", method = RequestMethod.GET)
	Response<Integer> countStockOptionTradeState(@RequestParam("publisherId") Long publisherId);

	@RequestMapping(value = "/update", method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
	Response<StockOptionTradeDto> update(@RequestBody StockOptionTradeDto dto);
}
