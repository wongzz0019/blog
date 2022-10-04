//import com.hzz.mapper.bill.BillMapper;
//import com.hzz.pojo.Bill;
//import org.apache.ibatis.session.SqlSession;
//import org.junit.Test;
//
//import java.util.List;
//
///**
// * @author Bosco
// * @date 2021/12/2
// */
//public class BillMapperTest {
//
//    @Test
//    public void getBillCountByProviderId(){
//        SqlSession sqlSession = MybatisUtils.getSqlSession();
//        BillMapper mapper = sqlSession.getMapper(BillMapper.class);
//        int billCountByProviderId = mapper.getBillCountByProviderId(14);
//        System.out.println(billCountByProviderId);
//        sqlSession.close();
//    }
//
//    @Test
//    public void add(){
//        SqlSession sqlSession = MybatisUtils.getSqlSession();
//        BillMapper mapper = sqlSession.getMapper(BillMapper.class);
//        Bill bill = new Bill();
//        bill.setId(1);
//        bill.setProductName("袜子");
//        bill.setBillCode("WZ");
//        bill.setProductUnit("双");
//
//        mapper.add(bill);
//        sqlSession.commit();
//        sqlSession.close();
//    }
//
//    @Test
//    public void getBillList(){
//        SqlSession sqlSession = MybatisUtils.getSqlSession();
//        BillMapper mapper = sqlSession.getMapper(BillMapper.class);
//        List<Bill> bills = mapper.getBillList("北京", 7, "2", 0, 4);
//
//        for (Bill bill : bills) {
//            System.out.println(bill.toString());
//        }
//        sqlSession.close();
//    }
//
//    @Test
//    public void getBillCount(){
//        SqlSession sqlSession = MybatisUtils.getSqlSession();
//        BillMapper mapper = sqlSession.getMapper(BillMapper.class);
//        int count = mapper.getBillCount("洗洁精", 9);
//        System.out.println(count);
//
//        sqlSession.close();
//    }
//
//    @Test
//    public void deleteBillById(){
//        SqlSession sqlSession = MybatisUtils.getSqlSession();
//        BillMapper mapper = sqlSession.getMapper(BillMapper.class);
//        mapper.deleteBillById(19);
//        sqlSession.commit();
//        sqlSession.close();
//    }
//
//    @Test
//    public void getBillById(){
//        SqlSession sqlSession = MybatisUtils.getSqlSession();
//        BillMapper mapper = sqlSession.getMapper(BillMapper.class);
//        Bill billById = mapper.getBillById(2);
//        System.out.println(billById.toString());
//
//        sqlSession.close();
//    }
//
//    @Test
//    public void modify(){
//        SqlSession sqlSession = MybatisUtils.getSqlSession();
//        BillMapper mapper = sqlSession.getMapper(BillMapper.class);
//        Bill bill = new Bill();
//        bill.setId(20);
//        bill.setProviderId(10);
//        mapper.modify(bill);
//
//        sqlSession.commit();
//        sqlSession.close();
//    }
//
//    @Test
//    public void deleteBillByProviderId(){
//        SqlSession sqlSession = MybatisUtils.getSqlSession();
//        BillMapper mapper = sqlSession.getMapper(BillMapper.class);
//        sqlSession.rollback();
//
//        sqlSession.commit();
//        sqlSession.close();
//    }
//
//
//
//
//}
