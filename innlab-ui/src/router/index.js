import { createRouter, createWebHistory, createWebHashHistory } from 'vue-router'
import Layout from '../layout/index.vue'
import { getAuthRouters } from "@/router/authRouter";
import {getDynamicRoutes} from '@/utils/getData.js'
import { useAuthRouterStore } from '@/stores/authRouter.js'




const router = createRouter({
  history: createWebHashHistory(),
  routes: [
    {
      path: '/',
      name: '首页',
      component: Layout,
      redirect: '/dashboard',
      meta: {
        staticRouter: true // 静态路由
      },
      children: [{
        path: 'dashboard',
        name: 'dashboard',
        component: () => import('@/views/Dashboard/index.vue'),
        meta: {
          breadcrumbName: '首页',
          icon: 'dashboard',
          staticRouter: true // 静态路由
        }
      }]
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../views/Login/index.vue'),
    },
    {
      path: '/register',
      name: 'register',
      component: () => import('../views/Register/index.vue'),
    }
  ],
})

// 验证token
router.beforeEach(async(to, from, next) => {
  const token = sessionStorage.token
  const authRouterStore = useAuthRouterStore();
  const { routerList } = authRouterStore;

   // 定义无需token的白名单路径
  const whiteList = ['/login', '/register'];

  if (whiteList.includes(to.path)) {// 访问登录/注册页面时直接放行
       next();
    } else if (!token) {
    // 没有token且访问非白名单页面，重定向到登录页
    next('/login');
    } else {
    const authRouterStore = useAuthRouterStore()
    let {routerList} = authRouterStore
    if(!routerList.length) {
      let dynamicRoutes = await getDynamicRoutes()
      // 动态添加路由
      let newRoutes = getAuthRouters(dynamicRoutes)
      authRouterStore.addRouterList(newRoutes)
      newRoutes.forEach(val => {
        router.addRoute(val)
      })
      next({ path: to.path })

    }else {
      next()
    }

  }
})
export default router
