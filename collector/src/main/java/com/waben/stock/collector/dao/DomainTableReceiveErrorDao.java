package com.waben.stock.collector.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.waben.stock.collector.entity.DomainTableReceiveError;

/**
 * 同步错误日志 Dao
 * 
 * @author luomengan
 *
 */
public interface DomainTableReceiveErrorDao {

	public DomainTableReceiveError createDomainTableReceiveError(DomainTableReceiveError domainTableReceiveError);

	public void deleteDomainTableReceiveErrorById(Integer id);

	public DomainTableReceiveError updateDomainTableReceiveError(DomainTableReceiveError domainTableReceiveError);

	public DomainTableReceiveError retrieveDomainTableReceiveErrorById(Integer id);

	public Page<DomainTableReceiveError> pageDomainTableReceiveError(int page, int limit);
	
	public List<DomainTableReceiveError> listDomainTableReceiveError();

}
