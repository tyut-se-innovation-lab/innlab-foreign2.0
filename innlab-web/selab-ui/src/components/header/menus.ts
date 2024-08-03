import { ref } from 'vue';

export const elementIdMap: Record<number, { path: string; name: string; query: number; }> = {
    1: { path: '/', name: '首页' },
    2: { path: '/blog', name: '组织介绍', actId: 18 },
    4: { path: '/part', name: '软件开发' },
    5: { path: '/part', name: '网络安全' },
    6: { path: '/part', name: '人工智能' },
    7: { path: '/part', name: '虚拟现实' },
    8: { path: '/part', name: '算法竞赛' },
    9: { path: '/activity', name: '机构动态' },
    10: { path: '/about', name: '联系我们' }
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
            {
                id: 8,
                name: '算法竞赛'
            },
        ]
    },
    {
        id: 9,
        name: '机构动态',
        children: []
    },
    {
        id: 10,
        name: '联系我们',
        children: []
    },
];