package com.hzz.service.role;

import com.hzz.mapper.role.RoleMapper;
import com.hzz.pojo.Role;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Bosco
 * @date 2021/12/16
 */
@Service
public class RoleServiceImpl implements RoleService{

    @Resource
    private RoleMapper roleMapper;

    public void setRoleMapper(RoleMapper roleMapper){
        this.roleMapper = roleMapper;
    }

    public List<Role> getRoleList() {
        return roleMapper.getRoleList();
    }

    public int add(Role role) {
        return roleMapper.add(role);
    }

    public int deleteRoleById(Integer deId) {
        return roleMapper.deleteRoleById(deId);
    }

    public int modify(Role role) {
        return roleMapper.modify(role);
    }

    public Role getRoleById(int id) {
        return roleMapper.getRoleById(id);
    }

    public int roleCodeIsExist(String roleCode) {
        return roleMapper.roleCodeIsExist(roleCode);
    }
}
