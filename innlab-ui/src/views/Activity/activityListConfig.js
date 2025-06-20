// 部门列表
const departmentOptions = [
  { label: '软件开发', value: '软件开发' },
  { label: '人工智能', value: '人工智能' },
  { label: '网络安全', value: '网络安全' },
  { label: '虚拟现实', value: '虚拟现实' },
  { label: '算法竞赛', value: '算法竞赛' }
];

// 活动类型列表
const activityTypeOptions = [
  { label: '热门', value: '热门' },
  { label: '站内公告', value: '站内公告' },
  { label: '日常活动', value: '日常活动' },
  { label: '技术分享', value: '技术分享' },
  { label: '资料宝库', value: '资料宝库' },
  { label: '软件分享', value: '软件分享' }
];

// 搜索配置
export const searchConfig = [
  {
    type: 'select',
    label: '部门',
    field: 'department',
    placeholder: '请选择部门',
    id: 'department',
    options: departmentOptions,
    labelKey: 'label',
    valueKey: 'value'
  },
  {
    type: 'select',
    label: '活动类型',
    field: 'activityType',
    placeholder: '请选择活动类型',
    id: 'activityType',
    options: activityTypeOptions,
    labelKey: 'label',
    valueKey: 'value'
  }
];

// 表格配置
export const tableConfig = [
  {
    prop: 'activityTitle',
    label: '活动标题'
  },
  {
    prop: 'activityType',
    label: '活动类型'
  },
  {
    prop: 'activityDepartment',
    label: '所属部门'
  },
  {
    prop: 'createTime',
    label: '创建时间'
  },
  {
    label: '封面图',
    slot: true,
    slotName: 'headerImage'
  },
  {
    prop: 'state',
    label: '状态',
    formatter: (row) => {
      return row.state ? '启用' : '停用';
    },
    slot: true,
    slotName: 'status'
  },
  {
    prop: 'isTop',
    label: '置顶',
    formatter: (row) => {
      return row.isTop ? '是' : '否';
    },
    slot: true,
    slotName: 'isTop'
  },
  {
    slot: true,
    label: '操作',
    slotName: 'handle',
    width: '280'
  }
];
