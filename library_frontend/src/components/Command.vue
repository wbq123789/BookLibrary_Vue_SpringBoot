import { getUserMessage } from '@/net';
<template>
  <el-menu 
      active-text-color="#ffd04b"
      background-color="#545c64"
      class="el-menu-vertical-demo"
      default-active="2"
      text-color="#fff">
    <h2>功能导航</h2>
    <el-menu-item @click="ClickTo(item)" v-for="item in Admin" :key="item.name" :index="item.name" v-if="UserName=='admin'">
      <el-icon><component :is="item.icon"/></el-icon>
      <template #title>{{ item.lable }}</template>
    </el-menu-item>
    <el-menu-item @click="ClickTo(item)" v-for="item in User" :key="item.name" :index="item.name" v-if="UserName=='user'">
      <el-icon><component :is="item.icon"/></el-icon>
      <template #title>{{ item.lable }}</template>
    </el-menu-item>
  </el-menu>
</template>

<script lang="ts">
import {getUserMessage} from '@/net';
export default {
  data() {
    return {
      UserName:getUserMessage().role,
      menuData: [
        {
          path: '/index',
          name: 'index',
          lable: '首页',
          icon:"House",
          User: 0,
          url: "Home/Home",
        },
        {
          path: '/main',
          name: 'AdminMain',
          lable: '用户信息',
          icon:"UserFilled",
          User: 1,
          url: "U/U",
        },
        {
          path: '/addbook',
          name: 'addBook',
          lable: '添加书籍',
          icon:"Collection",
          User:1,
          url: "Add/Add",
        },
        {
          path: '/deletebook',
          name: 'deleteBook',
          lable: '删除书籍',
          icon:"Delete",
          User:1,
          url: "Delete/Delete",
        },
        {
          path: '/loanbook',
          name: 'loanBook',
          lable: '借阅书籍',
          icon:"Reading",
          User:0,
          url: "Loan/Loan",
        },
        {
          path: '/restitution',
          name: 'restitution',
          lable: '归还书籍',
          icon:"document",
          User:0,
          url: "Ret/Ret",
        },
      ]
    }
  },methods:{
    ClickTo(item){
      console.log(item)
      this.$router.push(item.path)
    }
  },
  computed:{
    Admin(){
      return this.menuData.filter((item)=> item.User)
    },
    User(){
      return this.menuData.filter((item) => !item.User)
    }
  }
}
</script>

<style>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
.el-menu{
  height: 100vh;
}
</style>
