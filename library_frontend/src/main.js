import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import axios from "axios";
import 'element-plus/theme-chalk/dark/css-vars.css'
import { install } from '@icon-park/vue-next/es/all'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

axios.defaults.baseURL = 'http://192.168.0.100:8080'

const app = createApp(App)

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}

install(app) // 使用默认前缀“ icon”，例如：icon是People，名称是icon-people

app.use(router)

app.mount('#app')
