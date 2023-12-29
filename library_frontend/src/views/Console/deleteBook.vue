<template>
  <el-table :data="tableData" style="width: 100%">
    <el-table-column property="bid" label="书籍编号" width="150" />
    <el-table-column property="title" label="书籍标题" width="150" />
    <el-table-column property="desc" label="书籍简介" width="600" />
    <el-table-column property="label" label="书籍类型" />
    <el-table-column fixed="right" label="删除书籍" width="120">
      <template #default="scope">
        <el-button type="danger" round @click.prevent="DeleteBook(scope.$index)">
          删除
        </el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script >
import { getBookList, deleteBook } from '@/net';
import { ref,onMounted } from 'vue';
import { ElTable } from 'element-plus';

export default {
  setup() {

    let tableData =ref([{
      bid: '',
      title: '',
      desc: '',
      label: ''
    }])

    getBookList('book', (book) => {
      tableData.value = JSON.parse(book);
      console.log(tableData.value);
    });

    onMounted(() => {
      getBookList;
    });

    function DeleteBook(index) {
      deleteBook(tableData.value[index].bid, () => {
        window.location.reload();
      });
    }

    return {
      tableData,
      DeleteBook,
    };
  },
};
</script>
