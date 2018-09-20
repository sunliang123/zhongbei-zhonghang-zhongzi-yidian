package com.waben.stock.collector.service;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.waben.stock.collector.dao.BindCardDao;
import com.waben.stock.collector.dao.BuyRecordDao;
import com.waben.stock.collector.dao.CapitalAccountDao;
import com.waben.stock.collector.dao.CapitalFlowDao;
import com.waben.stock.collector.dao.DeferredRecordDao;
import com.waben.stock.collector.dao.DomainTableDao;
import com.waben.stock.collector.dao.DomainTableReceiveErrorDao;
import com.waben.stock.collector.dao.FrozenCapitalDao;
import com.waben.stock.collector.dao.OfflineStockOptionTradeDao;
import com.waben.stock.collector.dao.OrganizationAccountDao;
import com.waben.stock.collector.dao.OrganizationAccountFlowDao;
import com.waben.stock.collector.dao.OrganizationDao;
import com.waben.stock.collector.dao.OrganizationPublisherDao;
import com.waben.stock.collector.dao.PaymentOrderDao;
import com.waben.stock.collector.dao.PublisherDao;
import com.waben.stock.collector.dao.SettlementDao;
import com.waben.stock.collector.dao.StockOptionCycleDao;
import com.waben.stock.collector.dao.StockOptionTradeDao;
import com.waben.stock.collector.dao.StrategyTypeDao;
import com.waben.stock.collector.dao.WithdrawalsOrderDao;
import com.waben.stock.collector.entity.BindCard;
import com.waben.stock.collector.entity.BuyRecord;
import com.waben.stock.collector.entity.CapitalAccount;
import com.waben.stock.collector.entity.CapitalFlow;
import com.waben.stock.collector.entity.DeferredRecord;
import com.waben.stock.collector.entity.DomainTable;
import com.waben.stock.collector.entity.DomainTableReceiveError;
import com.waben.stock.collector.entity.FrozenCapital;
import com.waben.stock.collector.entity.OfflineStockOptionTrade;
import com.waben.stock.collector.entity.Organization;
import com.waben.stock.collector.entity.OrganizationAccount;
import com.waben.stock.collector.entity.OrganizationAccountFlow;
import com.waben.stock.collector.entity.OrganizationPublisher;
import com.waben.stock.collector.entity.PaymentOrder;
import com.waben.stock.collector.entity.Publisher;
import com.waben.stock.collector.entity.Settlement;
import com.waben.stock.collector.entity.StockOptionCycle;
import com.waben.stock.collector.entity.StockOptionTrade;
import com.waben.stock.collector.entity.StrategyType;
import com.waben.stock.collector.entity.WithdrawalsOrder;

/**
 * 应用表 Service
 * 
 * @author luomengan
 *
 */
@Service
public class DomainTableService {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private DomainTableDao domainTableDao;
	@Autowired
	private BindCardDao bindCardDao;
	@Autowired
	private BuyRecordDao buyRecordDao;
	@Autowired
	private CapitalAccountDao capitalAccountDao;
	@Autowired
	private CapitalFlowDao capitalFlowDao;
	@Autowired
	private DeferredRecordDao deferredRecordDao;
	@Autowired
	private FrozenCapitalDao frozenCapitalDao;
	@Autowired
	private OfflineStockOptionTradeDao offlineStockOptionTradeDao;
	@Autowired
	private OrganizationAccountDao organizationAccountDao;
	@Autowired
	private OrganizationAccountFlowDao organizationAccountFlowDao;
	@Autowired
	private OrganizationDao organizationDao;
	@Autowired
	private OrganizationPublisherDao organizationPublisherDao;
	@Autowired
	private PaymentOrderDao paymentOrderDao;
	@Autowired
	private PublisherDao publisherDao;
	@Autowired
	private SettlementDao settlementDao;
	@Autowired
	private StockOptionCycleDao stockOptionCycleDao;
	@Autowired
	private StockOptionTradeDao stockOptionTradeDao;
	@Autowired
	private StrategyTypeDao strategyTypeDao;
	@Autowired
	private WithdrawalsOrderDao withdrawalsOrderDao;
	@Autowired
	private DomainTableReceiveErrorDao errorDao;

	private JsonParser jsonParser = new JsonParser();

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public DomainTable getDomainTableInfo(Long id) {
		return domainTableDao.retrieveDomainTableById(id);
	}

	@Transactional
	public DomainTable addDomainTable(DomainTable domainTable) {
		return domainTableDao.createDomainTable(domainTable);
	}

	@Transactional
	public DomainTable modifyDomainTable(DomainTable domainTable) {
		return domainTableDao.updateDomainTable(domainTable);
	}

	@Transactional
	public void deleteDomainTable(Long id) {
		domainTableDao.deleteDomainTableById(id);
	}

	@Transactional
	public void deleteDomainTables(String ids) {
		if (ids != null) {
			String[] idArr = ids.split(",");
			for (String id : idArr) {
				if (!"".equals(id.trim())) {
					domainTableDao.deleteDomainTableById(Long.parseLong(id.trim()));
				}
			}
		}
	}

	public Page<DomainTable> domainTables(int page, int limit) {
		return domainTableDao.pageDomainTable(page, limit);
	}

	public List<DomainTable> list() {
		return domainTableDao.listDomainTable();
	}

	public List<DomainTable> listByDomain(String domain) {
		return domainTableDao.listByDomain(domain);
	}

	public void receiveData(Long domainTableId, String data) {
		try {
			DomainTable domainTable = domainTableDao.retrieveDomainTableById(domainTableId);
			String[] setMethods = domainTable.getSetMethods().split(",");
			String className = domainTable.getClassName();
			Class<?> clazz = Class.forName(className);
			JsonArray jsonArr = jsonParser.parse(data).getAsJsonArray();
			if (jsonArr != null && jsonArr.size() > 0) {
				logger.info("{} receiveData {}", domainTable.getDomain(), domainTable.getClassName());
				for (int i = 0; i < jsonArr.size(); i++) {
					// 解析数据
					Long dataId = null;
					Object dataObj = clazz.newInstance();
					JsonArray dataArr = jsonArr.get(i).getAsJsonArray();
					for (int j = 0; j < dataArr.size(); j++) {
						String[] setMethodArr = setMethods[j].split("_");
						String setMethod = setMethodArr[0].trim();
						String fieldType = setMethodArr[1].trim();
						Class<?> fieldClass = Class.forName(fieldType);
						Method method = clazz.getMethod(setMethod, new Class[] { fieldClass });
						Object fieldValue = null;
						if (!dataArr.get(j).isJsonNull()) {
							if (fieldClass == String.class) {
								fieldValue = dataArr.get(j).getAsString();
							} else if (fieldClass == Long.class) {
								fieldValue = dataArr.get(j).getAsLong();
							} else if (fieldClass == Integer.class) {
								fieldValue = dataArr.get(j).getAsInt();
							} else if (fieldClass == Date.class) {
								String dateStr = dataArr.get(j).getAsString();
								fieldValue = sdf.parse(dateStr);
							} else if (fieldClass == Boolean.class) {
								fieldValue = dataArr.get(j).getAsBoolean();
							} else if (fieldClass == BigDecimal.class) {
								fieldValue = dataArr.get(j).getAsBigDecimal();
							}
							if ("setDataId".equals(setMethod)) {
								dataId = (Long) fieldValue;
							}
						}
						method.invoke(dataObj, fieldValue);
					}
					// 更新数据
					updateData(domainTable.getDomain(), dataId, dataObj, clazz);
					// 更新最新索引
					if (i == jsonArr.size() - 1) {
						domainTable.setLastId(dataId);
						domainTableDao.updateDomainTable(domainTable);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void updateData(String domain, Long dataId, Object dataObj, Class<?> clazz) {
		if (clazz == BindCard.class) {
			BindCard data = (BindCard) dataObj;
			data.setDomain(domain);
			BindCard origin = bindCardDao.getByDomainAndDataId(domain, dataId);
			if (origin != null) {
				data.setId(origin.getId());
				bindCardDao.updateBindCard(data);
			} else {
				bindCardDao.createBindCard(data);
			}
		} else if (clazz == BuyRecord.class) {
			BuyRecord data = (BuyRecord) dataObj;
			data.setDomain(domain);
			BuyRecord origin = buyRecordDao.getByDomainAndDataId(domain, dataId);
			if (origin != null) {
				data.setId(origin.getId());
				buyRecordDao.updateBuyRecord(data);
			} else {
				buyRecordDao.createBuyRecord(data);
			}
		} else if (clazz == CapitalAccount.class) {
			CapitalAccount data = (CapitalAccount) dataObj;
			data.setDomain(domain);
			CapitalAccount origin = capitalAccountDao.getByDomainAndDataId(domain, dataId);
			if (origin != null) {
				data.setId(origin.getId());
				capitalAccountDao.updateCapitalAccount(data);
			} else {
				capitalAccountDao.createCapitalAccount(data);
			}
		} else if (clazz == CapitalFlow.class) {
			CapitalFlow data = (CapitalFlow) dataObj;
			data.setDomain(domain);
			CapitalFlow origin = capitalFlowDao.getByDomainAndDataId(domain, dataId);
			if (origin != null) {
				data.setId(origin.getId());
				capitalFlowDao.updateCapitalFlow(data);
			} else {
				capitalFlowDao.createCapitalFlow(data);
			}
		} else if (clazz == DeferredRecord.class) {
			DeferredRecord data = (DeferredRecord) dataObj;
			data.setDomain(domain);
			DeferredRecord origin = deferredRecordDao.getByDomainAndDataId(domain, dataId);
			if (origin != null) {
				data.setId(origin.getId());
				deferredRecordDao.updateDeferredRecord(data);
			} else {
				deferredRecordDao.createDeferredRecord(data);
			}
		} else if (clazz == FrozenCapital.class) {
			FrozenCapital data = (FrozenCapital) dataObj;
			data.setDomain(domain);
			FrozenCapital origin = frozenCapitalDao.getByDomainAndDataId(domain, dataId);
			if (origin != null) {
				data.setId(origin.getId());
				frozenCapitalDao.updateFrozenCapital(data);
			} else {
				frozenCapitalDao.createFrozenCapital(data);
			}
		} else if (clazz == OfflineStockOptionTrade.class) {
			OfflineStockOptionTrade data = (OfflineStockOptionTrade) dataObj;
			data.setDomain(domain);
			OfflineStockOptionTrade origin = offlineStockOptionTradeDao.getByDomainAndDataId(domain, dataId);
			if (origin != null) {
				data.setId(origin.getId());
				offlineStockOptionTradeDao.updateOfflineStockOptionTrade(data);
			} else {
				offlineStockOptionTradeDao.createOfflineStockOptionTrade(data);
			}
		} else if (clazz == Organization.class) {
			Organization data = (Organization) dataObj;
			data.setDomain(domain);
			Organization origin = organizationDao.getByDomainAndDataId(domain, dataId);
			if (origin != null) {
				data.setId(origin.getId());
				organizationDao.updateOrganization(data);
			} else {
				organizationDao.createOrganization(data);
			}
		} else if (clazz == OrganizationAccount.class) {
			OrganizationAccount data = (OrganizationAccount) dataObj;
			data.setDomain(domain);
			OrganizationAccount origin = organizationAccountDao.getByDomainAndDataId(domain, dataId);
			if (origin != null) {
				data.setId(origin.getId());
				organizationAccountDao.updateOrganizationAccount(data);
			} else {
				organizationAccountDao.createOrganizationAccount(data);
			}
		} else if (clazz == OrganizationAccountFlow.class) {
			OrganizationAccountFlow data = (OrganizationAccountFlow) dataObj;
			data.setDomain(domain);
			OrganizationAccountFlow origin = organizationAccountFlowDao.getByDomainAndDataId(domain, dataId);
			if (origin != null) {
				data.setId(origin.getId());
				organizationAccountFlowDao.updateOrganizationAccountFlow(data);
			} else {
				organizationAccountFlowDao.createOrganizationAccountFlow(data);
			}
		} else if (clazz == OrganizationPublisher.class) {
			OrganizationPublisher data = (OrganizationPublisher) dataObj;
			data.setDomain(domain);
			OrganizationPublisher origin = organizationPublisherDao.getByDomainAndDataId(domain, dataId);
			if (origin != null) {
				data.setId(origin.getId());
				organizationPublisherDao.updateOrganizationPublisher(data);
			} else {
				organizationPublisherDao.createOrganizationPublisher(data);
			}
		} else if (clazz == PaymentOrder.class) {
			PaymentOrder data = (PaymentOrder) dataObj;
			data.setDomain(domain);
			PaymentOrder origin = paymentOrderDao.getByDomainAndDataId(domain, dataId);
			if (origin != null) {
				data.setId(origin.getId());
				paymentOrderDao.updatePaymentOrder(data);
			} else {
				paymentOrderDao.createPaymentOrder(data);
			}
		} else if (clazz == Publisher.class) {
			Publisher data = (Publisher) dataObj;
			data.setDomain(domain);
			Publisher origin = publisherDao.getByDomainAndDataId(domain, dataId);
			if (origin != null) {
				data.setId(origin.getId());
				publisherDao.updatePublisher(data);
			} else {
				publisherDao.createPublisher(data);
			}
		} else if (clazz == Settlement.class) {
			Settlement data = (Settlement) dataObj;
			data.setDomain(domain);
			Settlement origin = settlementDao.getByDomainAndDataId(domain, dataId);
			if (origin != null) {
				data.setId(origin.getId());
				settlementDao.updateSettlement(data);
			} else {
				settlementDao.createSettlement(data);
			}
		} else if (clazz == StockOptionCycle.class) {
			StockOptionCycle data = (StockOptionCycle) dataObj;
			data.setDomain(domain);
			StockOptionCycle origin = stockOptionCycleDao.getByDomainAndDataId(domain, dataId);
			if (origin != null) {
				data.setId(origin.getId());
				stockOptionCycleDao.updateStockOptionCycle(data);
			} else {
				stockOptionCycleDao.createStockOptionCycle(data);
			}
		} else if (clazz == StockOptionTrade.class) {
			StockOptionTrade data = (StockOptionTrade) dataObj;
			data.setDomain(domain);
			StockOptionTrade origin = stockOptionTradeDao.getByDomainAndDataId(domain, dataId);
			if (origin != null) {
				data.setId(origin.getId());
				stockOptionTradeDao.updateStockOptionTrade(data);
			} else {
				stockOptionTradeDao.createStockOptionTrade(data);
			}
		} else if (clazz == StrategyType.class) {
			StrategyType data = (StrategyType) dataObj;
			data.setDomain(domain);
			StrategyType origin = strategyTypeDao.getByDomainAndDataId(domain, dataId);
			if (origin != null) {
				data.setId(origin.getId());
				strategyTypeDao.updateStrategyType(data);
			} else {
				strategyTypeDao.createStrategyType(data);
			}
		} else if (clazz == WithdrawalsOrder.class) {
			WithdrawalsOrder data = (WithdrawalsOrder) dataObj;
			data.setDomain(domain);
			WithdrawalsOrder origin = withdrawalsOrderDao.getByDomainAndDataId(domain, dataId);
			if (origin != null) {
				data.setId(origin.getId());
				withdrawalsOrderDao.updateWithdrawalsOrder(data);
			} else {
				withdrawalsOrderDao.createWithdrawalsOrder(data);
			}
		}
	}

	public void receiveError(Long domainTableId, String error) {
		try {
			DomainTable domainTable = domainTableDao.retrieveDomainTableById(domainTableId);
			DomainTableReceiveError errorEntity = new DomainTableReceiveError();
			errorEntity.setError(error);
			errorEntity.setDomainId(domainTableId);
			errorEntity.setDomain(domainTable != null ? domainTable.getDomain() : null);
			errorEntity.setCreateTime(new Date());
			errorDao.createDomainTableReceiveError(errorEntity);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
