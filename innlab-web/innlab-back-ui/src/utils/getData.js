
const adminRoutes = [
  {
    path: '/cardTemplate',
    id: 1000,
    name: 'cardTemplate',
    meta: {
      title: '卡片合集'
    },
    children: [{
      path: 'cardTemplate1',
      id: 1001,
      name: 'cardTemplate1',
      meta: {
        title: '普通卡片',
      }
    },
      {
        path: 'cardTemplate3',
        id: 1002,
        name: 'cardTemplate3',
        meta: {
          title: '可拖拽卡片',
        }
      },
      {
        path: 'cardTemplate4',
        id: 1003,
        name: 'cardTemplate4',
        meta: {
          title: '可缩放卡片',
        }
      },
      {
        path: 'cardTemplate2',
        id: 1004,
        name: 'cardTemplate2',
        meta: {
          title: 'echarts卡片'
        }
      }
    ]
  },
  {
    path: '/formTemplate',
    id: 2000,
    name: 'formTemplate',
    meta: {
      title: '表单示例'
    },
    children: [{
      path: 'formTemplate1',
      id: 2001,
      name: 'formTemplate1',
      meta: {
        title: '示例1',
      }
    },
      {
        path: 'formTemplate2',
        id: 2002,
        name: 'formTemplate2',
        meta: {
          title: '示例2'
        }
      }
    ]
  },
  {
    path: '/tableTemplate',
    id: 3000,
    name: 'tableTemplate',
    meta: {
      title: '表格示例'
    },
    children: [{
      path: 'tableTemplate1',
      id: 3001,
      name: 'tableTemplate1',
      meta: {
        title: '示例1',
      }
    },
      {
        path: 'tableTemplate2',
        id: 3002,
        name: 'tableTemplate2',
        meta: {
          title: '示例2',
        }
      }
    ]
  },
  {
    path: '/pdfTemplate',
    id: 5000,
    name: 'pdfTemplate',
    meta: {
      title: 'pdf示例'
    },
    children: [{
      path: 'pdfTemplate1',
      id: 5001,
      name: 'pdfTemplate1',
      meta: {
        title: '预览PDF1',
      }
    },
      {
        path: 'pdfTemplate2',
        id: 5002,
        name: 'pdfTemplate2',
        meta: {
          title: '预览PDF2',
        }
      }
    ]
  },
  {
    path: '/systems',
    id: 4000,
    name: 'systems',
    meta: {
      title: '系统设置'
    },
    children: [{
      path: 'structures',
      id: 4001,
      name: 'structures',
      meta: {
        title: '组织架构',
      }
    }
    ]
  }
]


const routes = [
  {
    path: '/user',
    id: 100,
    name: 'user',
    meta: {
      title: '用户管理'
    },
    children: [{
      path: 'userSelfMsg',
      id: 101,
      name: 'userSelfMsg',
      meta: {
        title: '个人信息',
        hidden: true
      }
    }, {
        path: 'userList',
        id: 102,
        name: 'userList',
        meta: {
          title: '用户列表',
        }
      }
    ]
  },
  {
    path: '/log',
    id: 200,
    name: 'log',
    meta: {
      title: '日志管理'
    },
    children: [{
      path: 'accessLogList',
      id: 201,
      name: 'accessLogList',
      meta: {
        title: '访问日志'
      }
    },{
      path: 'logiLogList',
      id: 202,
      name: 'loginLogList',
      meta: {
        title: '登录日志',
      }
    },{
      path: 'sysLogList',
      id: 203,
      name: 'sysLogList',
      meta: {
        title: '系统日志',
      }
    }
    ]
  },
  {
    path: '/resource',
    id: 300,
    name: 'resource',
    meta: {
      title: '资源管理'
    },
    children: [{
      path: 'resourceList',
      id: 301,
      name: 'resourceList',
      meta: {
        title: '资源列表'
      }
    },{
      path: 'imgCache',
      id: 302,
      name: 'imgCache',
      meta: {
        title: '图片缓存管理'
      }
    }
    ]
  },
  {
    path: '/personal',
    id: 400,
    name: 'personal',
    meta: {
      title: '人员管理'
    },
    children: [{
      path: 'personalList',
      id: 401,
      name: 'personalList',
      meta: {
        title: '人员列表'
      }
    }
    ]
  },
  {
    path: '/history',
    id: 500,
    name: 'history',
    meta: {
      title: '历史管理'
    },
    children: [{
      path: 'historyList',
      id: 501,
      name: 'historyList',
      meta: {
        title: '历史列表'
      }
    }
    ]
  },
  {
    path: '/activity',
    id: 600,
    name: 'activity',
    meta: {
      title: '动态管理'
    },
    children: [{
      path: 'activityList',
      id: 601,
      name: 'activityList',
      meta: {
        title: '动态列表'
      }
    },{
      path: 'activityEditor/:activityId',
      id: 602,
      name: 'activityEditor',
      meta: {
        title: '动态编辑',
        requiresAuth: true,
        hidden: true
      }
    }
    ]
  }
]

// 模拟获取动态路由数据
export function getDynamicRoutes(){
    return new Promise((resolve) => {
        resolve(routes)
    })
}

export function getAdminDynamicRoutes(){
  return new Promise((resolve) => {
    resolve(adminRoutes)
  })
}
