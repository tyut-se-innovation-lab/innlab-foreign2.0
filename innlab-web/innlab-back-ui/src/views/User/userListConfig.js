// 部门列表（示例）
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
    type: 'input',
    label: '账号',
    field: 'userAccount', // 对应查询参数
    placeholder: '请输入账号',
    id: 'userAccount' // 用于changeValue事件中标识字段
  },
  {
    type: 'select',
    label: '部门',
    field: 'userDepartment', // 对应查询参数
    placeholder: '请选择部门',
    id: 'userDepartment',
    options: departmentOptions, // 选项数据
    labelKey: 'label', // MySelect组件需要的属性，指定选项标签的键
    valueKey: 'value' // MySelect组件需要的属性，指定选项值的键
  }
];

// 表格配置
export const tableConfig = [
  {
    prop: 'userAccount',
    label: '账号'
  },
  {
    prop: 'userNickname',
    label: '昵称'
  },
  {
    prop: 'userDepartmentName',
    label: '部门'
  },
  {
    prop: 'userEmail',
    label: '邮箱'
  },
  {
    prop: 'userState',
    label: '状态',
    // 使用formatter将布尔值转换为文字
    formatter: (row) => {
      return row.userState ? '启用' : '停用';
    },
    // 使用插槽，以便在表格列中添加开关
    slot: true,
    slotName: 'status' // 插槽名称
  },
  {
    slot: true,
    label: '操作',
    slotName: 'handle' // 操作列的插槽名称
  }
];
