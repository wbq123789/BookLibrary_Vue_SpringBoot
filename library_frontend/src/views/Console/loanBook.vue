<template>
  <el-table
    ref="singleTableRef"
    :data="tableData"
    highlight-current-row
    style="width: 100%"
  >
    <el-table-column type="index" width="50" />
    <el-table-column property="title" label="书籍标题" width="150" />
    <el-table-column property="desc" label="书籍简介" width="600"/>
    <el-table-column property="label" label="书籍类型" />
    <el-table-column label="借阅书籍"><el-button type="primary" plain>借阅</el-button></el-table-column>
  </el-table>
</template>

<script lang="ts">
import { getBookList,borrowBook,getUserMessage,getUserIdByName} from '@/net';
import { ref, onMounted } from 'vue';
import { ElTable, ElMessage } from 'element-plus';
import { Message } from '@element-plus/icons-vue';

export default {
  setup() {
    interface tableData {
      bid: string,
      title: string
      desc: string
      label: string
    }
    let User = {
    username:'',
    role:''
    }
    User=getUserMessage();
    let userId;
    getUserIdByName(User.username,(data)=>{
        userId=JSON.parse(data)
    })
    let tableData = ref([
      {
        bid: '',
        title: '',
        desc: '',
        label: '',
      },
    ]);
    borrowBook(userId,tableData.value[0].bid,(data)=>{
      ElMessage.success(JSON.parse(data))
    })
    getBookList('book', (book) => {
          tableData.value =JSON.parse(book);
        });
    borrowBook()
    onMounted(() => {
      getBookList;
    });
    return {
      tableData
    };
  },
};
</script>
