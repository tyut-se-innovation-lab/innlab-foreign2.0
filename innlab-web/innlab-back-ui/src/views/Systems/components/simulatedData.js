// 模拟数据，演示效果使用，实际应用请从后端获取真实数据

export const roleData = [
    {
        name: '系统管理员',
        marks: '最高权限',
        time: '2025-02-21',
        id:1
    },
    {
        name: '研发人员',
        marks: '研发部门',
        time: '2025-02-20',
        id:2
    }
]

export const funtionsData1 = [
    {
        "menuId": 10000,
        "menuName": "系统管理",
        "listPermission": null,
        "sonMenuButtonCustom": [
            {
                "menuId": 10100,
                "menuName": "组织架构",
                "listPermission": null,
                "sonMenuButtonCustom": [
                    {
                        "menuId": 10101,
                        "menuName": "公司管理",
                        "listPermission": [
                            {
                                "id": 8,
                                "name": "增加公司",
                                "uri": "POST/organizes",
                                "menuId": 10101
                            },
                            {
                                "id": 9,
                                "name": "删除公司",
                                "uri": "DELETE/organizes/batch",
                                "menuId": 10101
                            },
                            {
                                "id": 10,
                                "name": "修改公司",
                                "uri": "PUT/organizes/{id}",
                                "menuId": 10101
                            }
                        ],
                        "sonMenuButtonCustom": null
                    },
                    {
                        "menuId": 10102,
                        "menuName": "部门管理",
                        "listPermission": [
                            {
                                "id": 11,
                                "name": "增加部门",
                                "uri": "POST/departments",
                                "menuId": 10102
                            },
                            {
                                "id": 12,
                                "name": "删除部门",
                                "uri": "DELETE/departments/batch",
                                "menuId": 10102
                            },
                            {
                                "id": 13,
                                "name": "修改部门",
                                "uri": "PUT/departments/{id}",
                                "menuId": 10102
                            }
                        ],
                        "sonMenuButtonCustom": null
                    },
                    {
                        "menuId": 10103,
                        "menuName": "用户管理",
                        "listPermission": [
                            {
                                "id": 1,
                                "name": "增加用户",
                                "uri": "POST/users",
                                "menuId": 10103
                            },
                            {
                                "id": 2,
                                "name": "删除用户",
                                "uri": "DELETE/users/batch",
                                "menuId": 10103
                            },
                            {
                                "id": 3,
                                "name": "修改用户",
                                "uri": "PUT/users/{id}",
                                "menuId": 10103
                            },
                            {
                                "id": 31,
                                "name": "用户角色分配",
                                "uri": "POST/users/{id}/role",
                                "menuId": 10103
                            }
                        ],
                        "sonMenuButtonCustom": null
                    },
                    {
                        "menuId": 10104,
                        "menuName": "角色管理",
                        "listPermission": [
                            {
                                "id": 14,
                                "name": "增加角色",
                                "uri": "POST/roles",
                                "menuId": 10104
                            },
                            {
                                "id": 15,
                                "name": "删除角色",
                                "uri": "DELETE/roles/batch",
                                "menuId": 10104
                            },
                            {
                                "id": 16,
                                "name": "修改角色",
                                "uri": "PUT/roles/{id}",
                                "menuId": 10104
                            },
                            {
                                "id": 17,
                                "name": "角色菜单分配",
                                "uri": "POST/roles/{id}/menu",
                                "menuId": 10104
                            },
                            {
                                "id": 18,
                                "name": "角色功能按钮分配",
                                "uri": "POST/roles/{id}/permission",
                                "menuId": 10104
                            }
                        ],
                        "sonMenuButtonCustom": null
                    }
                ]
            },
            {
                "menuId": 10200,
                "menuName": "入库管理",
                "listPermission": [
                    {
                        "id": 4,
                        "name": "增加入库单",
                        "uri": "POST/regulations",
                        "menuId": 10200
                    },
                    {
                        "id": 5,
                        "name": "删除入库单",
                        "uri": "DELETE/regulations/batch",
                        "menuId": 10200
                    },
                    {
                        "id": 6,
                        "name": "上传入库附件",
                        "uri": "POST/regulations/uploadFile",
                        "menuId": 10200
                    },
                    {
                        "id": 7,
                        "name": "修改入库单",
                        "uri": "PUT/regulations/{id}",
                        "menuId": 10200
                    }
                ],
                "sonMenuButtonCustom": []
            },
            {
                "menuId": 10300,
                "menuName": "出库管理",
                "listPermission": [
                    {
                        "id": 19,
                        "name": "增加出库单",
                        "uri": "POST/meters",
                        "menuId": 10300
                    },
                    {
                        "id": 20,
                        "name": "删除出库单",
                        "uri": "DELETE/meters/batch",
                        "menuId": 10300
                    },
                    {
                        "id": 21,
                        "name": "修改出库单",
                        "uri": "PUT/meters/{id}",
                        "menuId": 10300
                    },
                    {
                        "id": 22,
                        "name": "上传出库单附件",
                        "uri": "POST/meters/uploadFile",
                        "menuId": 10300
                    },
                    {
                        "id": 23,
                        "name": "记录溯源信息",
                        "uri": "POST/meters/source",
                        "menuId": 10300
                    },
                    {
                        "id": 24,
                        "name": "删除溯源信息",
                        "uri": "DELETE/meters/source/{id}",
                        "menuId": 10300
                    }
                ],
                "sonMenuButtonCustom": []
            },
            {
                "menuId": 10400,
                "menuName": "固定资产管理",
                "listPermission": [
                    {
                        "id": 25,
                        "name": "增加固定资产",
                        "uri": "POST/substances",
                        "menuId": 10400
                    },
                    {
                        "id": 26,
                        "name": "删除固定资产",
                        "uri": "DELETE/substances/batch",
                        "menuId": 10400
                    },
                    {
                        "id": 27,
                        "name": "修改固定资产",
                        "uri": "PUT/substances/{id}",
                        "menuId": 10400
                    },
                    {
                        "id": 28,
                        "name": "上传固定资产附件",
                        "uri": "POST/substances/uploadFile",
                        "menuId": 10400
                    },
                    {
                        "id": 29,
                        "name": "记录溯源信息",
                        "uri": "POST/substances/source",
                        "menuId": 10400
                    },
                    {
                        "id": 30,
                        "name": "删除溯源信息",
                        "uri": "DELETE/substances/source/{id}",
                        "menuId": 10400
                    }
                ],
                "sonMenuButtonCustom": []
            },
            {
                "menuId": 10500,
                "menuName": "日志管理",
                "listPermission": null,
                "sonMenuButtonCustom": []
            }
        ]
    },
    {
        "menuId": 20000,
        "menuName": "财务管理",
        "listPermission": null,
        "sonMenuButtonCustom": [
            {
                "menuId": 20300,
                "menuName": "对账单",
                "listPermission": [
                    {
                        "id": 32,
                        "name": "增加对账单",
                        "uri": "POST/calibrationLists",
                        "menuId": 20300
                    },
                    {
                        "id": 33,
                        "name": "删除对账单",
                        "uri": "DELETE/calibrationLists/batch",
                        "menuId": 20300
                    }
                ],
                "sonMenuButtonCustom": []
            }
        ]
    },
    {
        "menuId": 30000,
        "menuName": "报价管理",
        "listPermission": null,
        "sonMenuButtonCustom": [
            {
                "menuId": 30300,
                "menuName": "报价单",
                "listPermission": [
                    {
                        "id": 323,
                        "name": "增加报价单",
                        "uri": "POST/calibrationLists",
                        "menuId": 20300
                    },
                    {
                        "id": 333,
                        "name": "删除报价单",
                        "uri": "DELETE/calibrationLists/batch",
                        "menuId": 20300
                    }
                ],
                "sonMenuButtonCustom": []
            },
            {
                "menuId": 30400,
                "menuName": "委托服务管理",
                "sonMenuButtonCustom": [
                    {
                        "menuId": 304000,
                        "menuName": "委托单",
                        "sonMenuButtonCustom": [
                        ],
                        "listPermission": [
                            {
                                "id": 3233,
                                "name": "增加委托单",
                                "uri": "POST/calibrationLists",
                                "menuId": 203000
                            },
                            {
                                "id": 3333,
                                "name": "删除委托单",
                                "uri": "DELETE/calibrationLists/batch",
                                "menuId": 203000
                            }
                        ],


                    },
                    {
                        "menuId": 304001,
                        "menuName": "下厂单",
                        "sonMenuButtonCustom": [
                        ],
                        "listPermission": [
                            {
                                "id": 32333,
                                "name": "增加下厂单",
                                "uri": "POST/calibrationLists",
                                "menuId": 2030003
                            },
                            {
                                "id": 33333,
                                "name": "删除下厂单",
                                "uri": "DELETE/calibrationLists/batch",
                                "menuId": 2030003
                            }
                        ],


                    }
                ]
            }
        ]
    }
]

export const funtionsData2 = [
    {
        "menuId": 10000,
        "menuName": "系统管理",
        "listPermission": null,
        "sonMenuButtonCustom": [
            {
                "menuId": 10200,
                "menuName": "入库管理",
                "listPermission": [
                    {
                        "id": 4,
                        "name": "增加入库单",
                        "uri": "POST/regulations",
                        "menuId": 10200
                    },
                    {
                        "id": 5,
                        "name": "删除入库单",
                        "uri": "DELETE/regulations/batch",
                        "menuId": 10200
                    },
                    {
                        "id": 6,
                        "name": "上传入库附件",
                        "uri": "POST/regulations/uploadFile",
                        "menuId": 10200
                    },
                    {
                        "id": 7,
                        "name": "修改入库单",
                        "uri": "PUT/regulations/{id}",
                        "menuId": 10200
                    }
                ],
                "sonMenuButtonCustom": []
            },
            {
                "menuId": 10300,
                "menuName": "出库管理",
                "listPermission": [
                    {
                        "id": 19,
                        "name": "增加出库单",
                        "uri": "POST/meters",
                        "menuId": 10300
                    },
                    {
                        "id": 20,
                        "name": "删除出库单",
                        "uri": "DELETE/meters/batch",
                        "menuId": 10300
                    },
                    {
                        "id": 21,
                        "name": "修改出库单",
                        "uri": "PUT/meters/{id}",
                        "menuId": 10300
                    },
                    {
                        "id": 22,
                        "name": "上传出库单附件",
                        "uri": "POST/meters/uploadFile",
                        "menuId": 10300
                    },
                    {
                        "id": 23,
                        "name": "记录溯源信息",
                        "uri": "POST/meters/source",
                        "menuId": 10300
                    },
                    {
                        "id": 24,
                        "name": "删除溯源信息",
                        "uri": "DELETE/meters/source/{id}",
                        "menuId": 10300
                    }
                ],
                "sonMenuButtonCustom": []
            },
            {
                "menuId": 10400,
                "menuName": "固定资产管理",
                "listPermission": [
                    {
                        "id": 25,
                        "name": "增加固定资产",
                        "uri": "POST/substances",
                        "menuId": 10400
                    },
                    {
                        "id": 26,
                        "name": "删除固定资产",
                        "uri": "DELETE/substances/batch",
                        "menuId": 10400
                    },
                    {
                        "id": 27,
                        "name": "修改固定资产",
                        "uri": "PUT/substances/{id}",
                        "menuId": 10400
                    },
                    {
                        "id": 28,
                        "name": "上传固定资产附件",
                        "uri": "POST/substances/uploadFile",
                        "menuId": 10400
                    },
                    {
                        "id": 29,
                        "name": "记录溯源信息",
                        "uri": "POST/substances/source",
                        "menuId": 10400
                    },
                    {
                        "id": 30,
                        "name": "删除溯源信息",
                        "uri": "DELETE/substances/source/{id}",
                        "menuId": 10400
                    }
                ],
                "sonMenuButtonCustom": []
            },
            {
                "menuId": 10500,
                "menuName": "日志管理",
                "listPermission": null,
                "sonMenuButtonCustom": []
            }
        ]
    },
    {
        "menuId": 20000,
        "menuName": "财务管理",
        "listPermission": null,
        "sonMenuButtonCustom": [
            {
                "menuId": 20300,
                "menuName": "对账单",
                "listPermission": [
                    {
                        "id": 32,
                        "name": "增加对账单",
                        "uri": "POST/calibrationLists",
                        "menuId": 20300
                    },
                    {
                        "id": 33,
                        "name": "删除对账单",
                        "uri": "DELETE/calibrationLists/batch",
                        "menuId": 20300
                    }
                ],
                "sonMenuButtonCustom": []
            }
        ]
    }
]

export const rolePermissionData = [
    32,
    8,
    9,
    10,
    11,
    12,
    13,
    1,
    2,
    3,
    31,
    14,
    15,
    16,
    17,
    18,
    6,
    19,
    20,
    21,
    25,
    26,
    27,
    28
]

export const roleMenusPermissionData = [
    1000,1001,2000,2001,1002,4000,4001
]
