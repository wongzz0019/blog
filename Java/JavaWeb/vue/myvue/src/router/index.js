//这个index。js是路由的配置文件
import Vue from 'vue'
import VueRouter from 'vue-router'

//安装路由 注意路由名和导入的名一致
Vue.use(VueRouter);

//配置导出路由  注意路由名和导入的名一致
export default new VueRouter({
  routes: [
    {
      //路由路径
      path: '/content',
      name: 'content',
      //跳转的组件
      component: Content
    },
    {
      //路由路径
      path: '/main',
      name: 'main',
      //跳转的组件
      component: Main
    }
  ]
})
