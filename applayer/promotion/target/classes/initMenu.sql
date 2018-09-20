INSERT INTO `finance`.`menu` (`name`, `pid`, `sort`, `state`, `url`, `icon`, `variety`) VALUES ('代理商管理', '0', '1', b'1', 'admin', null, '4');
INSERT INTO `finance`.`menu` (`name`, `pid`, `sort`, `state`, `url`, `icon`, `variety`) VALUES ('代理资金管理', '0', '2', b'1', 'fund', null, '4');
INSERT INTO `finance`.`menu` (`name`, `pid`, `sort`, `state`, `url`, `icon`, `variety`) VALUES ('客户管理', '0', '3', b'1', 'customer', null, '4');
INSERT INTO `finance`.`menu` (`name`, `pid`, `sort`, `state`, `url`, `icon`, `variety`) VALUES ('客户交易管理', '0', '4', b'1', 'dealAd', null, '4');
INSERT INTO `finance`.`menu` (`name`, `pid`, `sort`, `state`, `url`, `icon`, `variety`) VALUES ('系统管理', '0', '5', b'1', 'system', null, '4');

INSERT INTO `finance`.`menu` (`name`, `pid`, `sort`, `state`, `url`, `icon`, `variety`) select '代理商列表' as name, id as pid, '1' as sort, b'1' as state, 'agentList' as url, null as icon, '4' as variety from menu where name='代理商管理' and url='admin';
INSERT INTO `finance`.`menu` (`name`, `pid`, `sort`, `state`, `url`, `icon`, `variety`) select '代理商资产' as name, id as pid, '2' as sort, b'1' as state, 'agentMoney' as url, null as icon, '4' as variety from menu where name='代理商管理' and url='admin';

INSERT INTO `finance`.`menu` (`name`, `pid`, `sort`, `state`, `url`, `icon`, `variety`) select '资金流水' as name, id as pid, '1' as sort, b'1' as state, 'bill' as url, null as icon, '4' as variety from menu where name='代理资金管理' and url='fund';
INSERT INTO `finance`.`menu` (`name`, `pid`, `sort`, `state`, `url`, `icon`, `variety`) select '佣金结算' as name, id as pid, '2' as sort, b'1' as state, 'settlement' as url, null as icon, '4' as variety from menu where name='代理资金管理' and url='fund';
INSERT INTO `finance`.`menu` (`name`, `pid`, `sort`, `state`, `url`, `icon`, `variety`) select '提现审核' as name, id as pid, '3' as sort, b'1' as state, 'audit' as url, null as icon, '4' as variety from menu where name='代理资金管理' and url='fund';
INSERT INTO `finance`.`menu` (`name`, `pid`, `sort`, `state`, `url`, `icon`, `variety`) select '提现记录' as name, id as pid, '4' as sort, b'1' as state, 'cash' as url, null as icon, '4' as variety from menu where name='代理资金管理' and url='fund';

INSERT INTO `finance`.`menu` (`name`, `pid`, `sort`, `state`, `url`, `icon`, `variety`) select '客户列表' as name, id as pid, '1' as sort, b'1' as state, 'list' as url, null as icon, '4' as variety from menu where name='客户管理' and url='customer';

INSERT INTO `finance`.`menu` (`name`, `pid`, `sort`, `state`, `url`, `icon`, `variety`) select '期权订单' as name, id as pid, '1' as sort, b'1' as state, 'optionOrder' as url, null as icon, '4' as variety from menu where name='客户交易管理' and url='dealAd';
INSERT INTO `finance`.`menu` (`name`, `pid`, `sort`, `state`, `url`, `icon`, `variety`) select '交易流水' as name, id as pid, '2' as sort, b'1' as state, 'fradingFlow' as url, null as icon, '4' as variety from menu where name='客户交易管理' and url='dealAd';

INSERT INTO `finance`.`menu` (`name`, `pid`, `sort`, `state`, `url`, `icon`, `variety`) select '角色管理' as name, id as pid, '1' as sort, b'1' as state, 'role' as url, null as icon, '4' as variety from menu where name='系统管理' and url='system';
INSERT INTO `finance`.`menu` (`name`, `pid`, `sort`, `state`, `url`, `icon`, `variety`) select '员工管理' as name, id as pid, '2' as sort, b'1' as state, 'staff' as url, null as icon, '4' as variety from menu where name='系统管理' and url='system';


select * from role_menu t where t.role_id=304;

INSERT INTO `finance`.`role_menu` (`role_id`, `menu_id`) VALUES ('304', '149');
INSERT INTO `finance`.`role_menu` (`role_id`, `menu_id`) VALUES ('304', '150');
INSERT INTO `finance`.`role_menu` (`role_id`, `menu_id`) VALUES ('304', '151');
INSERT INTO `finance`.`role_menu` (`role_id`, `menu_id`) VALUES ('304', '152');
INSERT INTO `finance`.`role_menu` (`role_id`, `menu_id`) VALUES ('304', '153');
INSERT INTO `finance`.`role_menu` (`role_id`, `menu_id`) VALUES ('304', '171');
INSERT INTO `finance`.`role_menu` (`role_id`, `menu_id`) VALUES ('304', '172');
INSERT INTO `finance`.`role_menu` (`role_id`, `menu_id`) VALUES ('304', '173');
INSERT INTO `finance`.`role_menu` (`role_id`, `menu_id`) VALUES ('304', '174');
INSERT INTO `finance`.`role_menu` (`role_id`, `menu_id`) VALUES ('304', '175');
INSERT INTO `finance`.`role_menu` (`role_id`, `menu_id`) VALUES ('304', '176');
INSERT INTO `finance`.`role_menu` (`role_id`, `menu_id`) VALUES ('304', '177');
INSERT INTO `finance`.`role_menu` (`role_id`, `menu_id`) VALUES ('304', '178');
INSERT INTO `finance`.`role_menu` (`role_id`, `menu_id`) VALUES ('304', '179');
INSERT INTO `finance`.`role_menu` (`role_id`, `menu_id`) VALUES ('304', '180');
INSERT INTO `finance`.`role_menu` (`role_id`, `menu_id`) VALUES ('304', '181');