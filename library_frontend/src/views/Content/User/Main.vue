<template>
  <div class="banner">         
    <div class="item">         
      <img :src="data[currentIndex]">                                                                                                                                                                     
    </div>
    <div class="page" v-if="this.data.length > 1">
      <ul>
        <li @click="gotoPage(prevIndex)">&lt;</li>
        <li v-for="(item,index) in data" @click="gotoPage(index)" :class="{'current':currentIndex == index}">  {{index+1}}</li>
        <li @click="gotoPage(nextIndex)">&gt;</li>
      </ul>                    
    </div>
  </div>
  <div class="zcrs">注册人数：{{personSum.valueOf()}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;书籍总数：{{bookSum.valueOf()}}</div>
	<div class="common-footer">
  <ul class="common-footer-row1 wrap-1280">
    <li class="li1">
      <h5>关于图书馆</h5>
        <p> <a href="https://www.nlc.cn/web/dsb_footer/gygt/jgsz/index.shtml" target="_blank">
            机构设置
          </a> </p>
      

        <p> <a href="https://www.nlc.cn/web/dsb_footer/gygt/lsyg/index.shtml" target="_blank">
            图书馆概况
          </a> </p>
      

        <p> <a href="https://www.nlc.cn/web/dsb_footer/gygt/guoneiwaijiaoliu/index.shtml" target="_blank">
            国内外交流
          </a> </p>
      

        <p> <a href="https://www.nlc.cn/web/dsb_footer/gygt/xxgk/index.shtml" target="_blank">
            信息公开
          </a> </p>
      

        <p> <a href="https://www.nlc.cn/web/dsb_footer/gygt/fwgf/index.shtml" target="_blank">
            服务规范
          </a> </p>

    </li>
    <li class="li3">
      <h5>联系我们</h5>
      <div class="about-block">
        <p class="about"> <a href="https://www.nlc.cn/web/dzzn/tushiguotu/dsb_tsgt/index.shtml" target="_blank"> -湖北文理学院   软工2111-508图书馆- </a></p>
        <p>地址：湖北省襄阳市湖北文理学院</p>
        <p>邮编：441100</p>
        <p>（+86 10）15392822811/177712522161</p>
        <p>E-mail：2902471892@qq.com</p>
      </div>
    </li>
  </ul>
  <ul class="common-footer-row2 wrap-1280">
    <li> <a href="https://www.nlc.cn/web/dsb_footer/bqsm/index.shtml" target="_blank">
        版权声明
      </a> <span>|</span> <a href="https://www.nlc.cn/web/dsb_footer/zhandianditu/index.shtml" target="_blank">
        站点地图
      </a> <span>|</span> <a href="https://www.nlc.cn/web/dsb_footer/rczp/index.shtml" target="_blank">
        人才招聘
      </a> </li>
    <li>
      <a>电话：（+86 10）13886511528</a> <span>|</span> <b>© 中国湖北文理学院508版权所有</b> </li> <a target="_blank" href="https://bszs.conac.cn/sitename?method=show&amp;id=37DFB7402E4D3CF3E053022819ACB320"></a>
  </ul>
</div>
</template> 


<script>
import { getToken } from '@/assets/auth.js'
import { getBookCount , getUserCount} from '@/net';
import { ref } from 'vue'
export default {
	components: {},
	props: {},
	data() {
    return {
      data: [
        '../../../src/assets/c2fe5d3e.jpg',
        '../../../src/assets/d06ed100.jpg',
        '../../../src/assets/f080014a.jpg'
      ],
      currentIndex: 0,
      timer: null,
      islogin: getToken()
    }
  },
	methods: {
		gotoPage(index) {
      this.currentIndex = index                                                                                                                                                                           
    },
		runInv() {                 
      this.timer = setInterval(() => {
        this.gotoPage(this.nextIndex)
      }, 2000)
    },
	},
	computed: {                  
    prevIndex() {              
      if (this.currentIndex === 0) {
        return this.data.length - 1
      } else {                 
        return this.currentIndex - 1
      }
    },
    nextIndex() {              
      if (this.currentIndex === this.data.length - 1) {
        return 0               
      } else {                 
        return this.currentIndex + 1
      }
    }
  },
	mounted:function(){
		this.runInv();
	},setup(){
    let personSum = ref('')
    let bookSum = ref('')
    getUserCount((data)=>{
      personSum.value = JSON.parse(data)
    })
    getBookCount((data)=>{
      bookSum.value = JSON.parse(data)
    })
    return {personSum,bookSum}
  }
};
</script>

<style lang="scss" scoped>
.banner {
  position: relative;          
  margin-bottom: 0.7rem;       
    .current {                 
      color: #ff6700;          
    }
    .page {                    
      background: rgba(0,0,0,.5);     
      position: absolute;
      right: 0;                
      bottom: 0;               
      width: 100%;
        ul{                    
          float: right;        
        }
    }
    ul li {                    
      list-style: none;        
      float: left;             
      width: 30px;             
      height: 40px;            
      line-height: 40px;
      text-align: center;
      cursor: pointer;
      color: rgba(255,255,255,.8);    
      font-size: 14px;
    }
}
.banner img {
  width: 100%;
  max-height: 680px;
}
.common-footer{
  font-size: 14px;
  color: rgb(255,255,255,0.6);
}
.common-footer.common-footer-row1{
  display: flex;
  overflow: hidden;
}
.common-footer .common-footer-row1 li {
  float: left;
  height: 320px;
  padding: 0 45px;
  border-right: 1px solid #3AAEBD;
}

.common-footer .common-footer-row1 li.li5 {
  flex: 0.5;
  display: flex;
  flex-flow: row wrap;
  justify-content: space-between;
  border-right: 0 none;
}

.common-footer .common-footer-row1 li.li5 dl {
  float: left;
  margin: 20px 0 0 0;
  width: 57px;
  cursor: pointer;
}

.common-footer .common-footer-row1 li.li5 dl dt {
  width: 57px;
  height: 57px;
  margin: 2px auto;
  position: relative;
}

.common-footer .common-footer-row1 li.li5 dl dt img {
  display: block;
  width: 100%;
}

.common-footer .common-footer-row2 {
  border-top: 1px solid #3AAEBD;
  padding: 20px 0 0 0;
  text-align: center;
  position: relative;
}

.common-footer .common-footer-row2 li {
  padding-bottom: 15px;
}

.common-footer .common-footer-row2 span {
  color: #fff;
  opacity: 0.3;
  padding: 0 20px;
}

.common-footer .common-footer-row2 b {
  color: rgba(255, 255, 255, 1);
}

.common-footer .common-footer-row2 img {
  width: 54px;
  height: 65px;
  display: block;
  position: absolute;
  right: 192px;
  top: 21px;
}
.common-footer {
  overflow: hidden;
  background: url('../../../assets/footer-bg.png') right bottom no-repeat #1395a7;
  background-size: 628px auto;
  position: relative;
  z-index: 2;
  min-width: 100%;
}
.common-footer {
  font-size: 14px;
  color: rgba(255, 255, 255, 0.6);
  line-height: 24px;
}
ul{
  display: block;
  list-style-type: disc;
  margin-block-start: 1em;
  margin-block-end: 1em;
  margin-inline-start: 0;
  margin-inline-end: 0;
  padding-inline-start: 40px;
}
.zcrs{
  width: 100%;
  text-align: center;
  font-size: xx-large;
  background-color: #1395a7;
  font-family: "Wawati SC";
}
</style> 
