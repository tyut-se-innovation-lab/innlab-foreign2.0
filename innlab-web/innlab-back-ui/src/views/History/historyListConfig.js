
export const tableConfig = [
  {
    prop: 'historyTime',
    label: '历史时间'
  },
  {
    prop: 'historyTitle',
    label: '历史标题'
  },
  {
    label: '封面图',
    slot: true,
    slotName: 'headerImage'
  },
  {
    prop: 'createTime',
    label: '创建时间'
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
