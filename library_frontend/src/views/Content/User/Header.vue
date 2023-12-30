<template>
  <title>图书管理系统</title>
  <div class="Head">
    <div class="biaoti"><router-link to="/index">星愿图书管理系统</router-link>
    </div>
      <img
        style="width: 70px;height:70px;margin-top: -25px;"
        src="../../../assets/logo.png"
      />
      <div class="flex-grow" />
      <div class="UserName">
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;欢迎 ： {{ User.username }}用户
      <el-button type="warning" round @click="userLogout" class="button">退出登录</el-button>
    </div>
  </div>
</template>

<script setup>
import { logout,getUserMessage} from '@/net'
import router from "@/router";
import { ref } from 'vue'

const activeIndex = ref('1');
let User=getUserMessage();
function userLogout() {
  logout(() => router.push("/"))
}
</script>

<style>
.Head{
  padding:0;
  margin: 0;
  background:linear-gradient(#a8edea,#fed6e3);
  width: 100%;
}
.biaoti{
  width: 600px;
  height: 30px;
  translate: 80px;
  font-size: xxx-large;
  font-family: "Kaiti SC";
  color:#330867;
  opacity: 0.6;
}
div a {
  font-weight: bold;
  color: #2c3e50;
  text-decoration: none;
  padding: 10px;
  border-radius: 4px;
}
div a.router-link-exact-active {
  color: rgb(230, 161, 233);
}
.UserName{
  text-align: center;
  width: 40%;
  float: right;
  height:30px;
  top: -65px;
  position: relative;
  font-size: xx-large;
  translate: 40px;
  color: #330867;
  opacity: 0.6;
  font-family: "Wawati SC";
}
.Head .button{
  height:55px;
  position: relative;
  top: -5px;
  opacity: 0.5;
  float: right;
  translate: -60px;
  background:linear-gradient(#a8edea,#fed6e3);
  border: none;
  color: #330867;
  font-size: x-large;
}
.flex-grow {
  flex-grow: 1;
}
</style>