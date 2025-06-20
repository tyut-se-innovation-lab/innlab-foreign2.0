// 部门列表
const departmentOptions = [
  { label: '软件开发', value: '软件开发' },
  { label: '人工智能', value: '人工智能' },
  { label: '网络安全', value: '网络安全' },
  { label: '虚拟现实', value: '虚拟现实' },
  { label: '算法竞赛', value: '算法竞赛' }
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
  }
];

// 表格配置
export const tableConfig = [
  {
    prop: 'itemTitle',
    label: '项目标题'
  },
  {
    prop: 'department',
    label: '部门'
  },
  {
    prop: 'createTime',
    label: '创建时间'
  },
  {
    prop: 'author',
    label: '作者'
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
