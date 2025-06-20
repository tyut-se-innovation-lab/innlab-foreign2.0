
export const tableConfig = [
    {
        prop: 'date',
        label: '日期'

    },
    {
        prop: 'name',
        label: '姓名'

    },
    {
        prop: 'address',
        label: '地址'

    },
    {
        prop: 'num',
        label: '数量',
        formatter: (row) => {
            return row + '个'
        }

    },
    {
        prop: 'pushWayValue',
        label: '推送方式'

    },
    {
        slot: true,
        label: '操作',
        slotName: 'handle'
    }
]

export const searchConfig = [
    {
        type: 'tree',
        title:'分组',
        id: 'groupsId',
        placeholder: '请选择分组',
        data:[]
    },
    {
        type: 'input',
        title:'姓名',
        id: 'name',
        placeholder: '请输入姓名查询'
    },
    {
        type: 'select',
        id: 'pushWayValue',
        title:'推送方式',
        placeholder: '请选择推送方式',
        options: [
            {
                value: 1,
                label: '短信'
            },
            {
                value: 2,
                label: '钉钉'
            },
            {
                value: 3,
                label: '微信'
            }
        ]
    }
]

export const tableConfig2 = [
    {
        prop: 'date',
        label: '日期'

    },
    {
        prop: 'name',
        label: '姓名'

    },
    {
        prop: 'address',
        label: '地址'

    },
    {
        prop: 'num',
        label: '数量',
        formatter: (row) => {
            return row + '个'
        }

    },
    {
        prop: 'pushWayValue',
        label: '推送方式'

    },
    {
        slot: true,
        label: '考评',
        slotName: 'handle'
    }
]
export const searchConfig2 = [
    {
        type: 'input',
        title:'用户名',
        id: 'name',
        placeholder: '请输入用户名查询'
    },
  
    {
        type: 'datetime',
        title:'开始时间',
        id: 'startTime',
        placeholder: '请选择开始时间',
        defaultValue: new Date(new Date().getTime() - (7 * 24 * 60 * 60 * 1000))
    },
    {
        type: 'datetime',
        title:'结束时间',
        id: 'endTime',
        defaultValue:new Date(),
        placeholder: '请选择结束时间'
    },
    {
        type: 'select',
        id: 'school',
        title:'学校',
        placeholder: '请选择所在学校',
        options: [
            {
                value: 1,
                label: '重庆大学'
            },
            {
                value: 2,
                label: '重庆理工大学'
            },
            {
                value: 3,
                label: '重庆科技大学'
            }
        ]
    },
]

export const editFormConfig = [{
    type:'input',
    label: '姓名',
    id: 'name'
},
{
    type:'input',
    label: '地址',
    id: 'address'
},
{
    type:'input',
    label: '数量',
    id: 'num'
},{
    type:'select',
    label: '推送方式',
    id: 'pushWayValue',
    placeholder: '请选择推送方式',
        options: [
            {
                value: 1,
                label: '短信'
            },
            {
                value: 2,
                label: '钉钉'
            },
            {
                value: 3,
                label: '微信'
            }
        ]

}
]