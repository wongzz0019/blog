//import com.hzz.mapper.provider.ProviderMapper;
//import com.hzz.pojo.Provider;
//import org.apache.ibatis.session.SqlSession;
//import org.junit.Test;
//
//import java.util.Date;
//import java.util.List;
//
///**
// * @author Bosco
// * @date 2021/12/2
// */
//public class ProviderMapperTest {
//
//    @Test//增加用户信息
//    public void add(){
//        SqlSession sqlSession = MybatisUtils.getSqlSession();
//        ProviderMapper mapper = sqlSession.getMapper(ProviderMapper.class);
//        Provider provider = new Provider();
//        provider.setProCode("YYDS");
//        provider.setProName("广州");
//        provider.setCreationDate(new Date());
//        provider.setProContact("黄钊");
//        mapper.add(provider);
//
//        sqlSession.commit();
//        sqlSession.close();
//    }
//
//    @Test//条件查询list
//    public void getProviderList(){
//        SqlSession sqlSession = MybatisUtils.getSqlSession();
//        ProviderMapper mapper = sqlSession.getMapper(ProviderMapper.class);
//        List<Provider> providers = mapper.getProviderList("北京", "BJ", 0, 4);
//        for (Provider provider : providers) {
//            System.out.println(provider.getId());
//        }
//        sqlSession.close();
//    }
//
//    @Test //获取供应商列表
//    public void getProList(){
//        SqlSession sqlSession = MybatisUtils.getSqlSession();
//        ProviderMapper mapper = sqlSession.getMapper(ProviderMapper.class);
//        List<Provider> proList = mapper.getProList();
//        System.out.println(proList.toString());
//        sqlSession.close();
//    }
//
//    @Test //获取供应商数量
//    public void getProviderCount(){
//        SqlSession sqlSession = MybatisUtils.getSqlSession();
//        ProviderMapper mapper = sqlSession.getMapper(ProviderMapper.class);
//        int count = mapper.getProviderCount("广州", "GZ");
//        System.out.println(count);
//        sqlSession.close();
//    }
//
//    @Test
//    public void deleteProviderById(){
//        SqlSession sqlSession = MybatisUtils.getSqlSession();
//        ProviderMapper mapper = sqlSession.getMapper(ProviderMapper.class);
//        mapper.deleteProviderById(17);
//        sqlSession.commit();
//        sqlSession.close();
//    }
//
//    @Test
//    public void getProviderById(){
//        SqlSession sqlSession = MybatisUtils.getSqlSession();
//        ProviderMapper mapper = sqlSession.getMapper(ProviderMapper.class);
//        Provider providerById = mapper.getProviderById(17);
//        System.out.println(providerById.toString());
//        sqlSession.close();
//    }
//
//    @Test
//    public void modify(){
//        SqlSession sqlSession = MybatisUtils.getSqlSession();
//        ProviderMapper mapper = sqlSession.getMapper(ProviderMapper.class);
//        Provider provider = new Provider();
//        provider.setId(17);
//        provider.setModifyBy(1);
//        provider.setModifyDate(new Date());
//        provider.setProName("云浮");
//        mapper.modify(provider);
//        sqlSession.commit();
//        sqlSession.close();
//    }
//
//}
