//import com.hzz.mapper.role.RoleMapper;
//import com.hzz.pojo.Role;
//import org.apache.ibatis.session.SqlSession;
//import org.junit.Test;
//
//import java.util.Date;
//import java.util.List;
//
//
//public class RoleMapperTest {
//
//    @Test //获取角色列表
//    public void Test(){
//        SqlSession sqlSession = MybatisUtils.getSqlSession();
//        RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
//
//        List<Role> roleList = mapper.getRoleList();
//
//        for (Role role : roleList) {
//            System.out.println(role.toString());
//        }
//        sqlSession.close();
//    }
//
//    @Test //增加角色信息
//    public void add(){
//        SqlSession sqlSession = MybatisUtils.getSqlSession();
//        RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
//        Date date = new Date();
//        Role role = new Role();
//        role.setRoleCode("4");
//        role.setRoleName("清洁");
//        role.setCreatedBy(1);
//        role.setCreationDate(date);
//        mapper.add(role);
//
//        sqlSession.commit();
//        sqlSession.close();
//    }
//
//    @Test //通过Id删除角色信息
//    public void deleteRoleByIdTest(){
//        SqlSession sqlSession = MybatisUtils.getSqlSession();
//        RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
//        mapper.deleteRoleById(5);
//        sqlSession.commit();
//        sqlSession.close();
//    }
//
//    @Test //修改信息
//    public void modifyTest(){
//        SqlSession sqlSession = MybatisUtils.getSqlSession();
//        RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
//
//        Role role = new Role();
//        role.setId(4);
//        role.setRoleCode("SMBMS_CLEANER");
//        role.setRoleName("清洁员");
//        role.setModifyBy(1);
//        role.setModifyDate(new Date());
//
//        mapper.modify(role);
//        sqlSession.commit();
//        sqlSession.close();
//    }
//
//    @Test //通过ID查询信息
//    public void getRoleByIdTest(){
//        SqlSession sqlSession = MybatisUtils.getSqlSession();
//        RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
//        Role role = mapper.getRoleById(2);
//        System.out.println(role.getId());
//        System.out.println(role.getRoleCode());
//        System.out.println(role.getRoleName());
//        sqlSession.close();
//    }
//
//    @Test //根据roleCode，进行角色编码的唯一性验证
//    public void roleCodeIsExistTest(){
//        SqlSession sqlSession = MybatisUtils.getSqlSession();
//        RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
//        int i = mapper.roleCodeIsExist("SMBMS_MANAGER");
//        System.out.println(i);
//        sqlSession.close();
//    }
//
//
//}
