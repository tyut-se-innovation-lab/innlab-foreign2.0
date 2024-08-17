import { createRouter, createWebHashHistory } from 'vue-router'
import Home from '../views/home/index.vue'
import bus from '@/eventBus';
// import Home from '../views/home/index.vue'
const router = createRouter({
  history: createWebHashHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: { sidebar: true },
      beforeEnter: (to, from, next) => {
        // 判断是否是从非首页跳转到首页，如果是则执行页面刷新逻辑
        bus.emit('loading', true);
        if (from.fullPath !== '/') {
          // 使用 setTimeout 延迟刷新页面，确保页面跳转前刷新完成
          setTimeout(() => {
            window.location.reload();
          }, 100);
        }
        next();
      },
    },
    { path: '/error', name: 'error', component: () => import('../views/error/index.vue'), meta: { title: '404' } },
    {
      path: '/part',
      name: 'part',
      component: () => import('../views/part/index.vue'),
      // meta: { header_unhome: true, footer: true },
      meta: { header: true, sidebar: true },

    },
    {
      path: '/blog',
      name: 'blog',
      component: () => import('../views/blog/index.vue'),
      // meta: { header_unhome: true, footer: true }
      meta: { header: true, sidebar: true }
    },
    {
      path: '/proMd',
      name: 'proMd',
      component: () => import('../views/proMd/index.vue'),
      // meta: { header_unhome: true, footer: true }
      meta: { header: true, sidebar: true }
    },
    {
      path: '/people',
      name: 'people',
      component: () => import('../views/people/index.vue'),
      // meta: { header_unhome: true, footer: true }
      meta: { sidebar: true }
    },
    {
      path: '/activity',
      name: 'activity',
      component: () => import('../views/activity/index.vue'),
      meta: { sidebar: true }
    },
    {
      path: '/about',
      name: 'about',
      component: () => import('../views/about/index.vue'),
      meta: { sidebar: true }
    }
  ]
})

// 全局前置守卫
// 在特定路由组件中使用 beforeRouteEnter 导航守卫
// router.beforeEach((to, from, next) => {
//   if (to.name === 'part' || to.name === 'blog' || to.name === 'people' || to.name === 'activity') {
//     // 如果路由是指定的路由，则刷新页面
//     window.location.reload();
//     next();
//   }

// });

export default router
