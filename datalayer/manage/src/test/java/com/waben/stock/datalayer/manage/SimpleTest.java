package com.waben.stock.datalayer.manage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.waben.stock.datalayer.manage.entity.Role;
import com.waben.stock.datalayer.manage.entity.Staff;
import com.waben.stock.interfaces.dto.manage.MenuDto;
import com.waben.stock.interfaces.util.JacksonUtil;
import com.waben.stock.interfaces.util.ShareCodeUtil;
import org.junit.Test;

import java.util.*;

/**
 * @author Created by yuyidi on 2017/11/16.
 * @desc
 */
public class SimpleTest {

    @Test
    public void testBeanCopy() {
        Staff staff = new Staff();
        staff.setUserName("yuyidi");
        staff.setPassword("1234566");
        staff.setCreateTime(new Date());
        Role role = new Role();
        role.setCode("user");
        role.setName("管理员");
        staff.setRole(role);
//        StaffDto staffDto = staff.copy();
//        System.out.println(JacksonUtil.encode(staffDto));
    }

    @Test
    public void menus() throws Exception{
        String json = "[{\"id\":101,\"name\":\"策略人管理\",\"pid\":0,\"state\":true,\"sort\":1,\"childs\":[]}," +
                "{\"id\":102,\"name\":\"投资人管理\",\"pid\":0,\"state\":true,\"sort\":2,\"childs\":[]},{\"id\":103," +
                "\"name\":\"系统管理\",\"pid\":0,\"state\":true,\"sort\":3,\"childs\":[]},{\"id\":104,\"name\":\"交易管理\"," +
                "\"pid\":0,\"state\":true,\"sort\":4,\"childs\":[]},{\"id\":105,\"name\":\"风控管理\",\"pid\":0," +
                "\"state\":true,\"sort\":5,\"childs\":[]},{\"id\":106,\"name\":\"财务管理\",\"pid\":0,\"state\":true," +
                "\"sort\":6,\"childs\":[]},{\"id\":107,\"name\":\"策略人列表\",\"pid\":101,\"state\":true,\"sort\":1," +
                "\"childs\":[]},{\"id\":108,\"name\":\"资金流水\",\"pid\":101,\"state\":true,\"sort\":2,\"childs\":[]}," +
                "{\"id\":109,\"name\":\"资金账户\",\"pid\":101,\"state\":true,\"sort\":3,\"childs\":[]}]";
        List<MenuDto> menuDtos =JacksonUtil.decode(json, new TypeReference<List<MenuDto>>(){});
        System.out.println(JacksonUtil.encode(menuDtos));
        List<MenuDto> menus = menus(menuDtos, 0l);
        System.out.println(JacksonUtil.encode(menus));
    }

    private List<MenuDto> menus(List<MenuDto> menuDtos, Long pid) {
        List<MenuDto> menus = new ArrayList<>();
        for (MenuDto menuDto : menuDtos) {
            if (menuDto.getPid().equals(pid)) {
//              若传过来的pid 为当前的id 则找到对应的父级并添加到子列表中
                menuDto.setChilds(menus(menuDtos, menuDto.getId()));
                menus.add(menuDto);
            }
        }
        return menus;
    }

    @Test
    public void testCode() {
        Set<String> sets = new HashSet<>();
        for (int i= 1;i<1000000;i++) {
            String result = ShareCodeUtil.encode(i);
            System.out.println(result);
            sets.add(result);
        }
        System.out.println(sets.size());
    }


}
