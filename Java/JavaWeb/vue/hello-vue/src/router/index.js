//导入vue
import Vue from 'vue';
import VueRouter from 'vue-router';
//导入组件
import Main from "../views/Main";
import Login from "../views/Login";
import UserProfile from "../views/user/Profile";
import UserList from "../views/user/List";
import NotFound from "../views/NotFound";

//使用
Vue.use(VueRouter);


//路由暴露导出
export default new VueRouter({
  //路由模式mode：hash(路径带#),history(不带)
  mode: 'history',
  routes: [
    {
      //登录页
      path: '/main',
      component: Main,
      props: true,
      children: [
        {path: '/user/list',name: 'UserList', component: UserList},
        // :id是传的参数，和前端页面的地址对应
        {path: '/user/profile/:id', name: 'UserProfile', component: UserProfile, props:true}//开启解耦props:true
      ]
    },
    //首页
    {
      path: '/login',
      component: Login
    },
    {
      path: "/goHome",
      //重定向
      redirect: '/main'
    },{
      path: '*',
      component: NotFound
    }
  ]

})

