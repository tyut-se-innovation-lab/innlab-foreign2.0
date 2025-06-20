export const formConfig = [{
    type:'input',
    label: '姓名',
    id: 'name'
},
{
    type:'input',
    label: '地址',
    id: 'adress'
},
{
    type:'input',
    label: '数量',
    id: 'num'
},{
    type:'select',
    label: '推送方式',
    id: 'pushWay',
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