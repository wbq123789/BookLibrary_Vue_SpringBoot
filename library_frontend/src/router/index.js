import { createRouter, createWebHistory } from 'vue-router'
import { unauthorized, getUserMessage } from "@/net";

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'welcome',
            component: () => import('@/views/WelcomeView.vue'),
            children: [
                {
                    path: '',
                    name: 'welcome-login',
                    component: () => import('@/views/welcome/LoginPage.vue')
                }, {
                    path: 'register',
                    name: 'welcome-register',
                    component: () => import('@/views/welcome/RegisterPage.vue')
                }, {
                    path: 'forget',
                    name: 'welcome-forget',
                    component: () => import('@/views/welcome/ForgetPage.vue')
                }
            ]
        }, 
        {
            path: '/index',
            name: 'index',
            component: () => import('@/views/Content/User/IndexView.vue'),
            children:[
                {
                    path:'/head',
                    name:'UserHead',
                    component:() => import('@/views/Content/User/Header.vue')
                },
                {
                    path:'',
                    name:'UserMain',
                    component:() => import('@/views/Content/User/Main.vue')
                },
                {
                    path:'/main',
                    name:'AdminMain',
                    component:() => import('@/views/Console/overview.vue')
                },
                {
                    path: '/loanbook',
                    name: 'loanBook',
                    component:() => import('@/views/Console/loanBook.vue')
                },
                {
                    path: '/restitution',
                    name: 'restitution',
                    component:() => import('@/views/Console/restitution.vue')
                },
                {
                    path: '/addbook',
                    name: 'addBook',
                    component:() => import('@/views/Console/addBook.vue')
                },
                {
                    path: '/deletebook',
                    name: 'deleteBook',
                    component:() => import('@/views/Console/deleteBook.vue')
                },
            ]
        },
]})


router.beforeEach((to, from, next) => {
    const isUnauthorized = unauthorized()
    if(to.name.startsWith('welcome') && !isUnauthorized) {
        next('/index')
    }else if(to.name.startsWith('index') && isUnauthorized) {
        next('/')
    }else {
        next()
    }
})

export default router
