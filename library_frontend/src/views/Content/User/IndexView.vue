<template>
  <div class="common-layout">
    <el-container>
      <el-header>
        <Header />
      </el-header>
      <el-container>
        <el-aside width="200px">
          <NavCommand />
        </el-aside>
        <el-main>
          <RouterView />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import NavCommand from "../../../components/Command.vue"
import Header from "./Header.vue"
export default {
  components: {
    NavCommand,
    Header
  }
}
</script>

<style>
header.el-header{
  border: none;
  padding: 0;
}
</style>
