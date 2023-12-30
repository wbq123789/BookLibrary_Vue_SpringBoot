<template>
  <el-table :data="tableData" style="width: 100%">
    <el-table-column property="id" label="用户编号" width="150" />
    <el-table-column property="username" label="用户姓名" width="300" />
    <el-table-column property="email" label="用户邮箱" width="450" />
    <el-table-column property="register_time" label="注册时间" />
  </el-table>
</template>

<script setup>
import { getUserList } from '@/net';
import { ref, onMounted } from 'vue';

const currentDate = ref(new Date());
const tableData = ref([]);

onMounted(() => {
  getUserList((user) => {
    tableData.value = JSON.parse(user);
    console.log(tableData.value);
  });
});
</script>



<style>

</style>