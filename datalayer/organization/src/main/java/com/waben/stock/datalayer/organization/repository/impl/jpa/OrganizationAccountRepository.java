package com.waben.stock.datalayer.organization.repository.impl.jpa;

import com.waben.stock.datalayer.organization.entity.Organization;
import com.waben.stock.datalayer.organization.entity.OrganizationAccount;

/**
 * 机构账户 Jpa
 * 
 * @author luomengan
 *
 */
public interface OrganizationAccountRepository extends CustomJpaRepository<OrganizationAccount, Long> {

	OrganizationAccount findByOrg(Organization org);

}
