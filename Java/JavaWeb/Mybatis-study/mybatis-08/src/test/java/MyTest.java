import com.hzz.dao.BlogMapper;
import com.hzz.pojo.Blog;
import com.hzz.utils.IdUtil;
import com.hzz.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.*;

/**
 * @author Bosco
 * @date 2021/11/24
 */
public class MyTest {

    static Logger logger = Logger.getLogger(MyTest.class);

    @Test
    public void addtest(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        mapper.addBlog(new Blog("2","好好学","小黄",new Date(),1997));
        sqlSession.close();
    }

    @Test
    public void addInitBlogTest(){

            SqlSession sqlSession = MybatisUtils.getSqlSession();
            BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
            Blog blog = new Blog();
            blog.setId(IdUtil.getId());
            blog.setTitle("Mybatis");
            blog.setAuthor("狂神说");
            blog.setCreateTime(new Date());
            blog.setViews(9999);

            mapper.addBlog(blog);

            blog.setId(IdUtil.getId());
            blog.setTitle("Java");
            mapper.addBlog(blog);

            blog.setId(IdUtil.getId());
            blog.setTitle("Spring");
            mapper.addBlog(blog);

            blog.setId(IdUtil.getId());
            blog.setTitle("微服务");
            mapper.addBlog(blog);

            sqlSession.close();

    }

    @Test
    public void queryBlogIFTest(){

            SqlSession sqlSession = MybatisUtils.getSqlSession();
            BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
            logger.info("进入BlogMapper测试");
            HashMap map = new HashMap();
            map.put("title","Java");
            map.put("author","狂神说");
            List<Blog> blogs = mapper.queryBlogIF(map);
            for (Blog blog : blogs) {
                    System.out.println(blog);
            }

            sqlSession.close();
    }

    @Test
    public void queryBlogChooseTest(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        HashMap map = new HashMap();
        map.put("views",9999);
        List<Blog> blogs = mapper.queryBlogChoose(map);
        for (Blog blog : blogs) {
            System.out.println(blog);
        }
        sqlSession.close();
    }

    @Test
    public void updateBlogTest(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        HashMap map = new HashMap();
        map.put("title","Spring2");
        map.put("author","狂神说2");
        map.put("id","cfc0acce7bc043c6b183893039e3ad56");
        int i = mapper.updateBlog(map);
        System.out.println(i);
        sqlSession.close();
    }

    @Test
    public void queryBlogForeachTest(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

        HashMap map = new HashMap();
        ArrayList<Integer> ids = new ArrayList<Integer>();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        map.put("ids",ids);

        List<Blog> blogs = mapper.queryBlogForeach(map);
        for (Blog blog : blogs) {
            System.out.println(blog);
        }
        sqlSession.close();
    }
}
