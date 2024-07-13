import { ref } from 'vue';

export const elementIdMap: Record<number, { path: string; name: string }> = {
    1: { path: '/', name: '首页' },
    2: { path: '/description', name: '组织介绍' },
    4: { path: '/part', name: '软件开发' },
    5: { path: '/part', name: '网络安全' },
    6: { path: '/part', name: '人工智能' },
    7: { path: '/part', name: '虚拟现实' },
    8: { path: '/activity', name: '日常活动' },
    9: { path: '/about', name: '联系我们' }
};

export const menuList = ref<Array<{ id: number; name: string; children: Array<{ id: number; name: string; }> }>>([]);
menuList.value = [
    {
        id: 1,
        name: '首页',
        children: []
    },
    {
        id: 2,
        name: '组织介绍',
        children: []
    },
    {
        id: 3,
        name: '组织架构',
        children: [
            {
                id: 4,
                name: '软件开发'
            },
            {
                id: 5,
                name: '网络安全'
            },
            {
                id: 6,
                name: '人工智能'
            },
            {
                id: 7,
                name: '虚拟现实'
            },
        ]
    },
    {
        id: 8,
        name: '日常活动',
        children: []
    },
    {
        id: 9,
        name: '联系我们',
        children: []
    },
];