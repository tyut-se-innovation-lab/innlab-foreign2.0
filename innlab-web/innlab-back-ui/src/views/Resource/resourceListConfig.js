// 资源类型选项

 export const resourceTypeOptions = [
  { label: '图片', value: 1 },
  { label: '视频', value: 2 },
  { label: '其他文件', value: 3 }
];

// 搜索配置
export const searchConfig = [
  {
    type: 'select',
    label: '资源类型',
    field: 'resourceType',
    placeholder: '请选择资源类型',
    id: 'resourceType',
    options: resourceTypeOptions,
    labelKey: 'label',
    valueKey: 'value'
  }
];

// 表格配置
export const tableConfig = [
  {
    prop: 'resourceId',
    label: '资源ID'
  },
  {
    prop: 'resourceName',
    label: '资源名称'
  },
  {
    prop: 'resourceType',
    label: '资源类型',
    // 使用插槽，以便在表格列中添加开关
    slot: true,
    slotName: 'resourceType' // 插槽名称
  },
  {
    label: '预览',
    slot: true,
    slotName: 'preview'
  },
  {
    prop: 'createTime',
    label: '创建时间'
  },
  {
    slot: true,
    label: '操作',
    slotName: 'handle'
  }
];
