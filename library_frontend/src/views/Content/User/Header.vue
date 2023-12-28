<template>
  <title>图书管理系统</title>
  <div class="Header">
    <div class="title">图书管理系统
    </div>
      <img
        style="width: 70px"
        src="../../../assets/icon-yellow.png"
      />
      <div class="flex-grow" />
      <div class="UserName">
        欢迎 ： {{ User.username }}用户
      <el-button type="warning" round @click="userLogout" class="button">退出登录</el-button>
    </div>
  </div>
</template>

<script setup>
import { logout,getUserMessage} from '@/net'
import router from "@/router";
import { ref } from 'vue'

const activeIndex = ref('1');

let User = {
  username:'',
  role:''
}

User=getUserMessage();
function userLogout() {
  logout(() => router.push("/"))
}
</script>

<style>
.Header{
  height: 70px;
  background-color: antiquewhite;
  padding: 0;
}
.picture{
  top:0;
  right:100px;
  height: 0;
}
.title{
  margin-left: 90px;
  font-size:xxx-large;
  height: 0;
  font-family: "Kaiti SC";
}
.UserName{
  width:400px;
  height:90px;
  position: fixed;
  right: 1px;
  text-align: center;
  float: right;
  top:5px;
  font-size: x-large;
  margin-top: 16px;
}
.Header .button{
  height:55px;
  position: fixed;
  top:10px;
  right:0;
}
.flex-grow {
  flex-grow: 1;
}
#search{
  position: absolute;
  top: 10px;
  margin-left: 600px;
  background-color: black;
  height: 40px;
  border-radius: 40px;
  padding: 10px;
  width: 300px;
  position: relative;
}
</style>