package com.hzz.service.role;

import com.hzz.pojo.Role;

import java.util.List;

/**
 * @author Bosco
 * @date 2021/12/16
 */
public interface RoleService {
    //获取角色列表
    public List<Role> getRoleList();

    //增加角色信息
    public int add(Role role);

    //通过Id删除role
    public int deleteRoleById(Integer deId);

    //修改角色信息
    public int modify(Role role);

    //通过id获取role
    Role getRoleById(int id);

    //根据roleCode，进行角色编码的唯一性验证
    public int roleCodeIsExist(String roleCode);

}
