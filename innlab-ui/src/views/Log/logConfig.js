export const accessLogConfig = [
// 表格配置
  {
    prop: 'logId',
    label: '日志ID'
  },
  {
    prop: 'accessIp',
    label: '访问IP'
  },
  {
    prop: 'accessUrl',
    label: '访问路径'
  },
  {
    prop: 'accessTime',
    label: '访问时间'
  },
  {
    slot: true,
    label: '操作',
    slotName: 'handle' // 操作列的插槽名称
  }
];

// 表格配置
export const loginLogConfig = [
  {
    prop: 'logId',
    label: '日志ID'
  },
  {
    prop: 'userAccount',
    label: '用户账号'
  },
  {
    prop: 'loginIp',
    label: '登录IP'
  },
  {
    prop: 'loginLocation',
    label: '登录地点'
  },
  {
    prop: 'resultCode',
    label: '状态',
    // 使用插槽，以便在表格列中添加开关
    slot: true,
    slotName: 'status' // 插槽名称
  },
  {
    prop: 'loginTime',
    label: '登录时间'
  },
  {
    slot: true,
    label: '操作',
    slotName: 'handle' // 操作列的插槽名称
  }
];
// 表格配置
export const sysLogConfig = [
  {
    prop: 'logId',
    label: '日志ID',
  },
  {
    prop: 'userAccount',
    label: '用户账号',
  },
  {
    prop: 'type',
    label: '操作类型',
    // 使用插槽，以便在表格列中添加开关
    slot: true,
    slotName: 'type' // 插槽名称
  },
  {
    prop: 'model',
    label: '模块',
  },
  {
    prop: 'description',
    label: '操作描述',
  },
  {
    prop: 'resultCode',
    label: '状态',
    // 使用插槽，以便在表格列中添加开关
    slot: true,
    slotName: 'status' // 插槽名称
  },
  {
    prop: 'createTime',
    label: '操作时间',
  },
  {
    slot: true,
    label: '操作',
    slotName: 'handle'
  }
];
