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
	<div class="common-footer">
  <ul class="common-footer-row1 wrap-1280">
    <li class="li1">
      <h5>关于国图</h5>
      
        <p> <a href="https://www.nlc.cn/web/dsb_footer/gygt/gld/index.shtml" target="_blank">
            馆领导
          </a> </p>
      

        <p> <a href="https://www.nlc.cn/web/dsb_footer/gygt/jgsz/index.shtml" target="_blank">
            机构设置
          </a> </p>
      

        <p> <a href="https://www.nlc.cn/web/dsb_footer/gygt/lsyg/index.shtml" target="_blank">
            国图概况
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
      

        <p> <a href="https://www.nlc.cn/web/dsb_footer/gygt/guanshuqiye/index.shtml" target="_blank">
            馆属企业
          </a> </p>
      

    </li>
    <li class="li2">
      <h5>支持我们</h5>
      
        <p> <a href="https://www.nlc.cn/web/dsb_footer/dsb_zcwm/dsb_cbwjs/index.shtml" target="_blank">
            出版物交存
          </a> </p>
      

        <p> <a href="https://www.nlc.cn/web/dsb_footer/dsb_zcwm/wenxianjuanzeng/index.shtml" target="_blank">
            文献捐赠
          </a> </p>
      

        <p> <a href="https://www.nlc.cn/web/dsb_footer/dsb_zcwm/dsb_wxjz/index.shtml" target="_blank">
            征集启事
          </a> </p>
      

      
    </li>
    <li class="li3">
      <h5>联系我们</h5>
      <div class="about-block">
        <p class="about"> <a href="https://www.nlc.cn/web/dzzn/tushiguotu/dsb_tsgt/index.shtml" target="_blank"> -国家图书馆总馆   国家典籍博物馆- </a></p>
        <p>地址：北京市海淀区中关村南大街33号</p>
        <p>邮编：100081</p>
        <p>服务咨询电话：4006006988、（+86 10）88545426</p>
        <p>E-mail：webmaster@nlc.cn</p>
      </div>
      <div>
        <p class="about"> <a href="https://www.nlc.cn/web/dzzn/tushiguotu/dsb_tsgt/index.shtml" target="_blank"> -国家图书馆古籍馆- </a></p>
        <p>地址：北京市西城区文津街7号 国家图书馆古籍馆</p>
        <p>邮编：100034</p>
        <p>电话：（+86 10）88003091</p>
      </div>
    </li>
    <li class="li4">
      <h5>相关链接</h5>
      
        <p> <a href="https://www.mct.gov.cn/" target="_blank">
            中华人民共和国文化和旅游部
          </a> </p>
      

        <p> <a href="https://www.ccdy.cn/" target="_blank">
            中国文化传媒网
          </a> </p>
      

        <p> <a href="http://www.lsc.org.cn/cns/index.html" target="_blank">
            中国图书馆学会
          </a> </p>
      

        <p> <a href="https://www.culturedc.cn/web2.1/index.html" target="_blank">
            国家公共文化云
          </a> </p>
      

        <p> <a href="http://www.nlcpress.com/" target="_blank">
            国家图书馆出版社
          </a> </p>
      

        <p> <a href="http://www.chinaabp.cn/" target="_blank">
            中国古籍保护协会
          </a> </p>
      

    </li>
    <li class="li5">
      
        <dl>
          <dt class=""> <img src="/template_resource/10008/img/icon-wb.png" class="noCode load" alt="国家图书馆官方微博二维码"> <img src="/template_resource/10008/img/icon-wb-code.png?v=123" class="code load" alt="国家图书馆官方微博二维码"> </dt>
          <dd>
            微博
          </dd>
        </dl>
      
      
        <dl>
          <dt> <img src="/template_resource/10008/img/icon-wx.png" class="noCode load" alt="国家图书馆官方微信二维码"> <img src="/template_resource/10008/img/icon-wx-code.png?v=123" class="code load" alt="国家图书馆官方微信二维码"> </dt>
          <dd>
            微信
          </dd>
        </dl>
      
      
        <dl>
          <dt> <img src="/template_resource/10008/img/icon-dy.png" class="noCode load" alt="国家图书馆官方抖音二维码"> <img src="/template_resource/10008/img/icon-dy-code.png?v=123" class="code load" alt="国家图书馆官方抖音二维码"> </dt>
          <dd>
            抖音
          </dd>
        </dl>
      
      
        <dl>
          <dt> <img src="/template_resource/10008/img/icon-gt.png?v=111" alt="掌上国图二维码" class="load"> <img src="/template_resource/10008/img/icon-gt-code.png?v=123" class="code load" alt="掌上国图二维码"> </dt>
          <dd>
            掌上国图
          </dd>
        </dl>
      
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
    <li> <a target="_blank" href="https://beian.miit.gov.cn/#/Integrated/index">京ICP备05014420号</a> <span>|</span>
      <a>电话：（+86 10）88545426</a> <span>|</span> <b>© 中国国家图书馆版权所有</b> </li> <a target="_blank" href="https://bszs.conac.cn/sitename?method=show&amp;id=37DFB7402E4D3CF3E053022819ACB320"> <img src="/template_resource/10008/img/cion-mark.png" alt="全国党政机关事业单位互联网网站标识管理服务平台" class="load"> </a>
  </ul>
</div>
</template> 


<script>
import { getToken } from '@/assets/auth.js'
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
</style> 
