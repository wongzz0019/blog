<template>
  <div>
    <h1>个人信息</h1>
<!--打印后台输出的数据-->
<!--第一种方法：直接取 注意是$route.params    -->
<!--      {{$route.params.id}}-->
    <!--第二种方法：解耦，1.在路由组件设置中开启props:true 2.在组件暴露中写props:['xx'] 3.输出数据{{xx}}-->
    {{id}}

  </div>

</template>

<script>
    export default {
      //解耦
      props: ['id'],
      name: "Profile",
      //钩子函数 过滤器
      beforeRouteEnter:(to, from, next) => {
          console.log("进入路由前");
        //加载数据
          next(vm => {
            vm.getData();
          }); //指向下一个，不写就会卡在这。
      },
      beforeRouteLeave:(to, from, next) => {
        console.log("离开路由后");
        next(); //指向下一个，不写就会卡在这。
      },
      methods: {
        //获取数据
        getData: function () {
          //是一个方法
          this.axios({
            method: 'get',
            url: 'http://localhost:8081/static/mock/data.json',
          }).then(function (response) {//链式编程，response响应的数据，
            console.log(response); //打印响应的数据
          })
        }
      }
    }
</script>

<style scoped>

</style>
