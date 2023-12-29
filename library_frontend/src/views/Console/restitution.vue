<template>
  <el-table :data="tableData" style="width: 100%" >
    <el-table-column property="bid" label="书籍编号" width="150" />
    <el-table-column property="title" label="书籍标题" width="150" />
    <el-table-column property="desc" label="书籍简介" width="600" />
    <el-table-column property="label" label="书籍类型" width="200"/>
    <el-table-column fixed="right" label="归还书籍" width="120">
      <template #default="scope">
        <el-button type="danger" round @click.prevent="restitutionBook(scope.$index)">
          归还
        </el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script >
import { getUserBorrowMessage, returnBook,getUserMessage} from '@/net';
import { ref,onMounted } from 'vue';
import { ElTable } from 'element-plus';

export default {
  setup() {
    let user = getUserMessage();
    console.log(user)
    let tableData =ref([{
      bid: '',
      title: '',
      desc: '',
      label: ''
    }])
    onMounted(() => {
      getUserBorrowMessage;
    });
    getUserBorrowMessage(user.userid,(book) => {
      tableData.value = JSON.parse(book);
      console.log(tableData.value);
    });
    function restitutionBook(index) {
      returnBook(user.userid, tableData.value[index].bid, () => {
        window.location.reload();
      });
    }

    return {
      tableData,
      restitutionBook,
    };
  },
};
</script>
