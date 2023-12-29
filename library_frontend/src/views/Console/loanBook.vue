<template>
  <el-table :data="tableData" style="width: 100%">
    <el-table-column property="bid" label="书籍编号" width="150" />
    <el-table-column property="title" label="书籍标题" width="150" />
    <el-table-column property="desc" label="书籍简介" width="600" />
    <el-table-column property="label" label="书籍类型" width="200"/>
    <el-table-column fixed="right" label="借阅书籍" width="120">
      <template #default="scope">
        <el-button type="primary" plain @click.prevent="test(scope.$index)">
          借阅
        </el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script lang="ts">
import { getBookList, borrowBook, getUserMessage, getUserIdByName } from '@/net';
import { ref, onMounted } from 'vue';
import { ElTable } from 'element-plus';
export default {
  setup() {
    let userId;
    getUserIdByName((data) => {
      userId = JSON.parse(data)
    })
    let tableData = ref([
      {
        bid: '',
        title: '',
        desc: '',
        label: '',
      },
    ]);

    getBookList('c_borrow', (book) => {
      tableData.value = JSON.parse(book);
      console.log(tableData.value)
    });
    onMounted(() => {
      getBookList;
    });
    function test(index) {
      console.log(userId)
      borrowBook(userId, tableData.value[index].bid, () => {
        window.location.reload();
      })
    }
    return {
      tableData, test
    };
  }
};
</script>
