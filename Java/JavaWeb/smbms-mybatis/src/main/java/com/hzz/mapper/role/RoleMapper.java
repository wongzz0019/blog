package com.hzz.mapper.role;


import com.hzz.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {

    //获取角色列表
    public List<Role> getRoleList();

    //增加角色信息
    public int add(Role role);

    //通过Id删除role
    public int deleteRoleById(@Param("id") Integer deId);

    //修改角色信息
    public int modify(Role role);

    //通过id获取role
    Role getRoleById(@Param("id") int id);

    //根据roleCode，进行角色编码的唯一性验证
    public int roleCodeIsExist(@Param("roleCode") String roleCode);


}
