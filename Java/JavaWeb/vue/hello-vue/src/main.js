import Vue from 'vue'
import App from './App'
import router from "./router"
//导入vue相关
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
//导入axios相关
import axios from "axios";
import VueAxios from 'vue-axios';

Vue.use(router)
Vue.use(ElementUI)
Vue.use(VueAxios,axios)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  render:h=>h(App)
})
