// 部门列表
const departmentOptions = [
  { label: '软件开发', value: '软件开发' },
  { label: '人工智能', value: '人工智能' },
  { label: '网络安全', value: '网络安全' },
  { label: '虚拟现实', value: '虚拟现实' },
  { label: '算法竞赛', value: '算法竞赛' }
];

// 届数列表（示例，可以根据实际情况调整）
const periodOptions = [
  { label: '2020', value: '2020' },
  { label: '2021', value: '2021' },
  { label: '2022', value: '2022' },
  { label: '2023', value: '2023' },
  { label: '2023', value: '2023' }
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
    label: '级数',
    field: 'period',
    placeholder: '请选择级数',
    id: 'period',
    options: periodOptions,
    labelKey: 'label',
    valueKey: 'value'
  }
];

// 表格配置
export const tableConfig = [
  {
    prop: 'personnelName',
    label: '姓名'
  },
  {
    prop: 'personnelPeriod',
    label: '级数'
  },
  {
    prop: 'personnelDepartment',
    label: '部门'
  },
  {
    prop: 'personnelPost',
    label: '职位'
  },
  {
    label: '头像',
    slot: true,
    slotName: 'avatar'
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
    slot: true,
    label: '操作',
    slotName: 'handle'
  }
];
