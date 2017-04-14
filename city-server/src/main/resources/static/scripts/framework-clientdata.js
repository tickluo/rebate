﻿var clients = [];

var userMenu = [
    {
        "City_Id": "d614c595-d2c6-4a6e-9f4e-62373a796500",
        "City_ParentId": "0",
        "City_Layers": null,
        "City_EnCode": null,
        "City_FullName": "业绩报表",
        "City_Icon": "fa fa-bar-chart-o",
        "City_UrlAddress": "fa fa-bar-chart-o",
        "City_Target": "expand",
        "City_IsMenu": true,
        "City_IsExpand": true,
        "City_IsPublic": false,
        "City_AllowEdit": true,
        "City_AllowDelete": false,
        "City_SortCode": 31,
        "City_DeleteMark": null,
        "City_EnabledMark": true,
        "City_Description": "业绩报表menu",
        "City_CreatorTime": "2016-12-16 11:34:01",
        "City_CreatorUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
        "City_LastModifyTime": "2017-01-17 09:52:42",
        "City_LastModifyUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
        "City_DeleteTime": null,
        "City_DeleteUserId": null,
        "ChildNodes": [
            {
                "City_Id": "f33cb530-c853-41a2-a3d5-577624d608f2",
                "City_ParentId": "d614c595-d2c6-4a6e-9f4e-62373a796500",
                "City_Layers": null,
                "City_EnCode": null,
                "City_FullName": "日期报表",
                "City_Icon": null,
                "City_UrlAddress": "/report/date",
                "City_Target": "iframe",
                "City_IsMenu": true,
                "City_IsExpand": false,
                "City_IsPublic": false,
                "City_AllowEdit": false,
                "City_AllowDelete": false,
                "City_SortCode": 1,
                "City_DeleteMark": null,
                "City_EnabledMark": true,
                "City_Description": "按日期报表",
                "City_CreatorTime": "2016-12-16 11:35:00",
                "City_CreatorUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
                "City_LastModifyTime": "2016-12-23 14:25:05",
                "City_LastModifyUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
                "City_DeleteTime": null,
                "City_DeleteUserId": null,
                "ChildNodes": []
            },
            {
                "City_Id": "cc6b4fba-d380-4830-a164-5af7dd93a317",
                "City_ParentId": "d614c595-d2c6-4a6e-9f4e-62373a796500",
                "City_Layers": null,
                "City_EnCode": null,
                "City_FullName": "CPS报表",
                "City_Icon": null,
                "City_UrlAddress": "/report/cps",
                "City_Target": "iframe",
                "City_IsMenu": true,
                "City_IsExpand": false,
                "City_IsPublic": false,
                "City_AllowEdit": false,
                "City_AllowDelete": false,
                "City_SortCode": 2,
                "City_DeleteMark": null,
                "City_EnabledMark": true,
                "City_Description": "按cps报表",
                "City_CreatorTime": "2016-12-16 11:35:34",
                "City_CreatorUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
                "City_LastModifyTime": "2016-12-23 14:32:05",
                "City_LastModifyUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
                "City_DeleteTime": null,
                "City_DeleteUserId": null,
                "ChildNodes": []
            }
        ]
    },
    {
        "City_Id": "d29620a2-fd39-484a-8a69-49c73ed654e9",
        "City_ParentId": "0",
        "City_Layers": null,
        "City_EnCode": null,
        "City_FullName": "账号管理",
        "City_Icon": "fa fa-user",
        "City_UrlAddress": "fa fa-user",
        "City_Target": "expand",
        "City_IsMenu": false,
        "City_IsExpand": false,
        "City_IsPublic": false,
        "City_AllowEdit": false,
        "City_AllowDelete": false,
        "City_SortCode": 39,
        "City_DeleteMark": null,
        "City_EnabledMark": true,
        "City_Description": "账号管理",
        "City_CreatorTime": "2016-12-16 11:36:25",
        "City_CreatorUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
        "City_LastModifyTime": "2016-12-16 13:22:30",
        "City_LastModifyUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
        "City_DeleteTime": null,
        "City_DeleteUserId": null,
        "ChildNodes": [
            {
                "City_Id": "b2d1bbc7-c5df-409f-9a9d-bce528a0ec62",
                "City_ParentId": "d29620a2-fd39-484a-8a69-49c73ed654e9",
                "City_Layers": null,
                "City_EnCode": null,
                "City_FullName": "账号信息",
                "City_Icon": null,
                "City_UrlAddress": "/user/userInfo",
                "City_Target": "iframe",
                "City_IsMenu": true,
                "City_IsExpand": false,
                "City_IsPublic": false,
                "City_AllowEdit": false,
                "City_AllowDelete": false,
                "City_SortCode": 1,
                "City_DeleteMark": null,
                "City_EnabledMark": true,
                "City_Description": "账号信息",
                "City_CreatorTime": "2016-12-16 11:36:53",
                "City_CreatorUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
                "City_LastModifyTime": "2016-12-19 18:59:49",
                "City_LastModifyUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
                "City_DeleteTime": null,
                "City_DeleteUserId": null,
                "ChildNodes": []
            },
            {
                "City_Id": "035d9207-73b6-4746-abdb-afd0f6ef72c5",
                "City_ParentId": "d29620a2-fd39-484a-8a69-49c73ed654e9",
                "City_Layers": null,
                "City_EnCode": null,
                "City_FullName": "修改密码",
                "City_Icon": null,
                "City_UrlAddress": "/user/resetPassword",
                "City_Target": "iframe",
                "City_IsMenu": true,
                "City_IsExpand": false,
                "City_IsPublic": false,
                "City_AllowEdit": false,
                "City_AllowDelete": false,
                "City_SortCode": 2,
                "City_DeleteMark": null,
                "City_EnabledMark": true,
                "City_Description": "修改密码",
                "City_CreatorTime": "2016-12-16 11:37:19",
                "City_CreatorUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
                "City_LastModifyTime": "2016-12-20 14:36:06",
                "City_LastModifyUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
                "City_DeleteTime": null,
                "City_DeleteUserId": null,
                "ChildNodes": []
            },
            {
                "City_Id": "c8509525-19ee-4069-aa66-4f96808f1693",
                "City_ParentId": "d29620a2-fd39-484a-8a69-49c73ed654e9",
                "City_Layers": null,
                "City_EnCode": null,
                "City_FullName": "银行信息",
                "City_Icon": null,
                "City_UrlAddress": "/bank/bankInfo",
                "City_Target": "iframe",
                "City_IsMenu": true,
                "City_IsExpand": false,
                "City_IsPublic": false,
                "City_AllowEdit": false,
                "City_AllowDelete": false,
                "City_SortCode": 3,
                "City_DeleteMark": null,
                "City_EnabledMark": true,
                "City_Description": "银行信息",
                "City_CreatorTime": "2016-12-16 11:38:05",
                "City_CreatorUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
                "City_LastModifyTime": "2016-12-20 20:13:30",
                "City_LastModifyUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
                "City_DeleteTime": null,
                "City_DeleteUserId": null,
                "ChildNodes": []
            }
        ]
    },
    {
        "City_Id": "aaa48315-d88b-48e7-b11b-b1d1eb4c3b19",
        "City_ParentId": "0",
        "City_Layers": null,
        "City_EnCode": null,
        "City_FullName": "返利提现",
        "City_Icon": "fa fa-paypal",
        "City_UrlAddress": "fa fa-paypal",
        "City_Target": "expand",
        "City_IsMenu": false,
        "City_IsExpand": false,
        "City_IsPublic": false,
        "City_AllowEdit": false,
        "City_AllowDelete": false,
        "City_SortCode": 43,
        "City_DeleteMark": null,
        "City_EnabledMark": true,
        "City_Description": "返利提现",
        "City_CreatorTime": "2016-12-16 11:38:54",
        "City_CreatorUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
        "City_LastModifyTime": "2016-12-16 13:22:41",
        "City_LastModifyUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
        "City_DeleteTime": null,
        "City_DeleteUserId": null,
        "ChildNodes": [
            {
                "City_Id": "eca4673a-61b5-4195-b1f1-eb0271ac6572",
                "City_ParentId": "aaa48315-d88b-48e7-b11b-b1d1eb4c3b19",
                "City_Layers": null,
                "City_EnCode": null,
                "City_FullName": "返利提现",
                "City_Icon": null,
                "City_UrlAddress": "/rebate/rebateCash",
                "City_Target": "iframe",
                "City_IsMenu": true,
                "City_IsExpand": false,
                "City_IsPublic": false,
                "City_AllowEdit": false,
                "City_AllowDelete": false,
                "City_SortCode": 1,
                "City_DeleteMark": null,
                "City_EnabledMark": true,
                "City_Description": "返利提现",
                "City_CreatorTime": "2016-12-16 11:39:18",
                "City_CreatorUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
                "City_LastModifyTime": "2016-12-23 14:33:09",
                "City_LastModifyUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
                "City_DeleteTime": null,
                "City_DeleteUserId": null,
                "ChildNodes": []
            },
            {
                "City_Id": "d9acaa34-2988-4f27-81b2-84f6830a42be",
                "City_ParentId": "aaa48315-d88b-48e7-b11b-b1d1eb4c3b19",
                "City_Layers": null,
                "City_EnCode": null,
                "City_FullName": "提现记录",
                "City_Icon": null,
                "City_UrlAddress": "/rebate/cashRecord",
                "City_Target": "iframe",
                "City_IsMenu": true,
                "City_IsExpand": false,
                "City_IsPublic": false,
                "City_AllowEdit": false,
                "City_AllowDelete": false,
                "City_SortCode": 2,
                "City_DeleteMark": null,
                "City_EnabledMark": true,
                "City_Description": "提现记录",
                "City_CreatorTime": "2016-12-16 11:39:46",
                "City_CreatorUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
                "City_LastModifyTime": "2016-12-23 14:33:37",
                "City_LastModifyUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
                "City_DeleteTime": null,
                "City_DeleteUserId": null,
                "ChildNodes": []
            },
            {
                "City_Id": "2a8b56d5-19a9-41d2-a3b9-1c3adf258991",
                "City_ParentId": "aaa48315-d88b-48e7-b11b-b1d1eb4c3b19",
                "City_Layers": null,
                "City_EnCode": null,
                "City_FullName": "各网站返利点",
                "City_Icon": null,
                "City_UrlAddress": "/rebate/rebateSite",
                "City_Target": "iframe",
                "City_IsMenu": true,
                "City_IsExpand": false,
                "City_IsPublic": false,
                "City_AllowEdit": false,
                "City_AllowDelete": false,
                "City_SortCode": 3,
                "City_DeleteMark": null,
                "City_EnabledMark": true,
                "City_Description": "各网站的返利点",
                "City_CreatorTime": "2016-12-16 11:40:28",
                "City_CreatorUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
                "City_LastModifyTime": "2016-12-22 14:57:04",
                "City_LastModifyUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
                "City_DeleteTime": null,
                "City_DeleteUserId": null,
                "ChildNodes": []
            }
        ]
    },
    {
        "City_Id": "1a5df304-79ff-4ab3-9f68-caf7c889626b",
        "City_ParentId": "0",
        "City_Layers": null,
        "City_EnCode": null,
        "City_FullName": "开发者文档",
        "City_Icon": "fa fa-gear",
        "City_UrlAddress": "fa-gear",
        "City_Target": "expand",
        "City_IsMenu": false,
        "City_IsExpand": false,
        "City_IsPublic": false,
        "City_AllowEdit": false,
        "City_AllowDelete": false,
        "City_SortCode": 47,
        "City_DeleteMark": null,
        "City_EnabledMark": true,
        "City_Description": "开发者文档",
        "City_CreatorTime": "2016-12-16 11:41:08",
        "City_CreatorUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
        "City_LastModifyTime": "2016-12-16 13:22:48",
        "City_LastModifyUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
        "City_DeleteTime": null,
        "City_DeleteUserId": null,
        "ChildNodes": [
            {
                "City_Id": "fa8e86a0-ec79-4bc6-99ce-316f898857c4",
                "City_ParentId": "1a5df304-79ff-4ab3-9f68-caf7c889626b",
                "City_Layers": null,
                "City_EnCode": null,
                "City_FullName": "APPKEY",
                "City_Icon": null,
                "City_UrlAddress": "/developer/index",
                "City_Target": "iframe",
                "City_IsMenu": true,
                "City_IsExpand": false,
                "City_IsPublic": false,
                "City_AllowEdit": false,
                "City_AllowDelete": false,
                "City_SortCode": 1,
                "City_DeleteMark": null,
                "City_EnabledMark": true,
                "City_Description": "APPKEY",
                "City_CreatorTime": "2016-12-16 11:41:29",
                "City_CreatorUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
                "City_LastModifyTime": "2016-12-22 11:01:50",
                "City_LastModifyUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
                "City_DeleteTime": null,
                "City_DeleteUserId": null,
                "ChildNodes": []
            }
        ]
    }
];
var superAdminMenu = [
    {
        "City_Id": "b2d1bbc7-c5df-409f-9a9d-bce52sa0ec42",
        "City_Layers": null,
        "City_EnCode": null,
        "City_FullName": "商户选取",
        "City_Icon": null,
        "City_UrlAddress": "/user/userInfo",
        "City_Target": "iframe",
        "City_IsMenu": true,
        "City_IsExpand": false,
        "City_IsPublic": false,
        "City_AllowEdit": false,
        "City_AllowDelete": false,
        "City_SortCode": 1,
        "City_DeleteMark": null,
        "City_EnabledMark": true,
        "City_Description": "商户选取",
        "City_CreatorTime": "2016-12-16 11:36:53",
        "City_CreatorUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
        "City_LastModifyTime": "2016-12-19 18:59:49",
        "City_LastModifyUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
        "City_DeleteTime": null,
        "City_DeleteUserId": null,
        "ChildNodes": []
    },
    {
        "City_Id": "d614c595-d2c6-4a6e-9f4e-62373a796500",
        "City_ParentId": "0",
        "City_Layers": null,
        "City_EnCode": null,
        "City_FullName": "业绩报表",
        "City_Icon": "fa fa-bar-chart-o",
        "City_UrlAddress": "fa fa-bar-chart-o",
        "City_Target": "expand",
        "City_IsMenu": true,
        "City_IsExpand": true,
        "City_IsPublic": false,
        "City_AllowEdit": true,
        "City_AllowDelete": false,
        "City_SortCode": 31,
        "City_DeleteMark": null,
        "City_EnabledMark": true,
        "City_Description": "业绩报表menu",
        "City_CreatorTime": "2016-12-16 11:34:01",
        "City_CreatorUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
        "City_LastModifyTime": "2017-01-17 09:52:42",
        "City_LastModifyUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
        "City_DeleteTime": null,
        "City_DeleteUserId": null,
        "ChildNodes": [
            {
                "City_Id": "f33cb530-c853-41a2-a3d5-577624d608f2",
                "City_ParentId": "d614c595-d2c6-4a6e-9f4e-62373a796500",
                "City_Layers": null,
                "City_EnCode": null,
                "City_FullName": "日期报表",
                "City_Icon": null,
                "City_UrlAddress": "/report/date",
                "City_Target": "iframe",
                "City_IsMenu": true,
                "City_IsExpand": false,
                "City_IsPublic": false,
                "City_AllowEdit": false,
                "City_AllowDelete": false,
                "City_SortCode": 1,
                "City_DeleteMark": null,
                "City_EnabledMark": true,
                "City_Description": "按日期报表",
                "City_CreatorTime": "2016-12-16 11:35:00",
                "City_CreatorUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
                "City_LastModifyTime": "2016-12-23 14:25:05",
                "City_LastModifyUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
                "City_DeleteTime": null,
                "City_DeleteUserId": null,
                "ChildNodes": []
            },
            {
                "City_Id": "cc6b4fba-d380-4830-a164-5af7dd93a317",
                "City_ParentId": "d614c595-d2c6-4a6e-9f4e-62373a796500",
                "City_Layers": null,
                "City_EnCode": null,
                "City_FullName": "CPS报表",
                "City_Icon": null,
                "City_UrlAddress": "/report/cps",
                "City_Target": "iframe",
                "City_IsMenu": true,
                "City_IsExpand": false,
                "City_IsPublic": false,
                "City_AllowEdit": false,
                "City_AllowDelete": false,
                "City_SortCode": 2,
                "City_DeleteMark": null,
                "City_EnabledMark": true,
                "City_Description": "按cps报表",
                "City_CreatorTime": "2016-12-16 11:35:34",
                "City_CreatorUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
                "City_LastModifyTime": "2016-12-23 14:32:05",
                "City_LastModifyUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
                "City_DeleteTime": null,
                "City_DeleteUserId": null,
                "ChildNodes": []
            }
        ]
    },
    {
        "City_Id": "d29620a2-fd39-484a-8a69-49c73ed654e9",
        "City_ParentId": "0",
        "City_Layers": null,
        "City_EnCode": null,
        "City_FullName": "账号管理",
        "City_Icon": "fa fa-user",
        "City_UrlAddress": "fa fa-user",
        "City_Target": "expand",
        "City_IsMenu": false,
        "City_IsExpand": false,
        "City_IsPublic": false,
        "City_AllowEdit": false,
        "City_AllowDelete": false,
        "City_SortCode": 39,
        "City_DeleteMark": null,
        "City_EnabledMark": true,
        "City_Description": "账号管理",
        "City_CreatorTime": "2016-12-16 11:36:25",
        "City_CreatorUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
        "City_LastModifyTime": "2016-12-16 13:22:30",
        "City_LastModifyUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
        "City_DeleteTime": null,
        "City_DeleteUserId": null,
        "ChildNodes": [
            {
                "City_Id": "b2d1bbc7-c5df-409f-9a9d-bce528a0ec62",
                "City_ParentId": "d29620a2-fd39-484a-8a69-49c73ed654e9",
                "City_Layers": null,
                "City_EnCode": null,
                "City_FullName": "账号信息",
                "City_Icon": null,
                "City_UrlAddress": "/user/userInfo",
                "City_Target": "iframe",
                "City_IsMenu": true,
                "City_IsExpand": false,
                "City_IsPublic": false,
                "City_AllowEdit": false,
                "City_AllowDelete": false,
                "City_SortCode": 1,
                "City_DeleteMark": null,
                "City_EnabledMark": true,
                "City_Description": "账号信息",
                "City_CreatorTime": "2016-12-16 11:36:53",
                "City_CreatorUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
                "City_LastModifyTime": "2016-12-19 18:59:49",
                "City_LastModifyUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
                "City_DeleteTime": null,
                "City_DeleteUserId": null,
                "ChildNodes": []
            },
            {
                "City_Id": "035d9207-73b6-4746-abdb-afd0f6ef72c5",
                "City_ParentId": "d29620a2-fd39-484a-8a69-49c73ed654e9",
                "City_Layers": null,
                "City_EnCode": null,
                "City_FullName": "修改密码",
                "City_Icon": null,
                "City_UrlAddress": "/user/resetPassword",
                "City_Target": "iframe",
                "City_IsMenu": true,
                "City_IsExpand": false,
                "City_IsPublic": false,
                "City_AllowEdit": false,
                "City_AllowDelete": false,
                "City_SortCode": 2,
                "City_DeleteMark": null,
                "City_EnabledMark": true,
                "City_Description": "修改密码",
                "City_CreatorTime": "2016-12-16 11:37:19",
                "City_CreatorUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
                "City_LastModifyTime": "2016-12-20 14:36:06",
                "City_LastModifyUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
                "City_DeleteTime": null,
                "City_DeleteUserId": null,
                "ChildNodes": []
            },
            {
                "City_Id": "c8509525-19ee-4069-aa66-4f96808f1693",
                "City_ParentId": "d29620a2-fd39-484a-8a69-49c73ed654e9",
                "City_Layers": null,
                "City_EnCode": null,
                "City_FullName": "银行信息",
                "City_Icon": null,
                "City_UrlAddress": "/bank/bankInfo",
                "City_Target": "iframe",
                "City_IsMenu": true,
                "City_IsExpand": false,
                "City_IsPublic": false,
                "City_AllowEdit": false,
                "City_AllowDelete": false,
                "City_SortCode": 3,
                "City_DeleteMark": null,
                "City_EnabledMark": true,
                "City_Description": "银行信息",
                "City_CreatorTime": "2016-12-16 11:38:05",
                "City_CreatorUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
                "City_LastModifyTime": "2016-12-20 20:13:30",
                "City_LastModifyUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
                "City_DeleteTime": null,
                "City_DeleteUserId": null,
                "ChildNodes": []
            }
        ]
    },
    {
        "City_Id": "aaa48315-d88b-48e7-b11b-b1d1eb4c3b19",
        "City_ParentId": "0",
        "City_Layers": null,
        "City_EnCode": null,
        "City_FullName": "返利提现",
        "City_Icon": "fa fa-paypal",
        "City_UrlAddress": "fa fa-paypal",
        "City_Target": "expand",
        "City_IsMenu": false,
        "City_IsExpand": false,
        "City_IsPublic": false,
        "City_AllowEdit": false,
        "City_AllowDelete": false,
        "City_SortCode": 43,
        "City_DeleteMark": null,
        "City_EnabledMark": true,
        "City_Description": "返利提现",
        "City_CreatorTime": "2016-12-16 11:38:54",
        "City_CreatorUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
        "City_LastModifyTime": "2016-12-16 13:22:41",
        "City_LastModifyUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
        "City_DeleteTime": null,
        "City_DeleteUserId": null,
        "ChildNodes": [
            {
                "City_Id": "eca4673a-61b5-4195-b1f1-eb0271ac6572",
                "City_ParentId": "aaa48315-d88b-48e7-b11b-b1d1eb4c3b19",
                "City_Layers": null,
                "City_EnCode": null,
                "City_FullName": "返利提现",
                "City_Icon": null,
                "City_UrlAddress": "/rebate/rebateCash",
                "City_Target": "iframe",
                "City_IsMenu": true,
                "City_IsExpand": false,
                "City_IsPublic": false,
                "City_AllowEdit": false,
                "City_AllowDelete": false,
                "City_SortCode": 1,
                "City_DeleteMark": null,
                "City_EnabledMark": true,
                "City_Description": "返利提现",
                "City_CreatorTime": "2016-12-16 11:39:18",
                "City_CreatorUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
                "City_LastModifyTime": "2016-12-23 14:33:09",
                "City_LastModifyUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
                "City_DeleteTime": null,
                "City_DeleteUserId": null,
                "ChildNodes": []
            },
            {
                "City_Id": "d9acaa34-2988-4f27-81b2-84f6830a42be",
                "City_ParentId": "aaa48315-d88b-48e7-b11b-b1d1eb4c3b19",
                "City_Layers": null,
                "City_EnCode": null,
                "City_FullName": "提现记录",
                "City_Icon": null,
                "City_UrlAddress": "/rebate/cashRecord",
                "City_Target": "iframe",
                "City_IsMenu": true,
                "City_IsExpand": false,
                "City_IsPublic": false,
                "City_AllowEdit": false,
                "City_AllowDelete": false,
                "City_SortCode": 2,
                "City_DeleteMark": null,
                "City_EnabledMark": true,
                "City_Description": "提现记录",
                "City_CreatorTime": "2016-12-16 11:39:46",
                "City_CreatorUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
                "City_LastModifyTime": "2016-12-23 14:33:37",
                "City_LastModifyUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
                "City_DeleteTime": null,
                "City_DeleteUserId": null,
                "ChildNodes": []
            },
            {
                "City_Id": "2a8b56d5-19a9-41d2-a3b9-1c3adf258991",
                "City_ParentId": "aaa48315-d88b-48e7-b11b-b1d1eb4c3b19",
                "City_Layers": null,
                "City_EnCode": null,
                "City_FullName": "各网站返利点",
                "City_Icon": null,
                "City_UrlAddress": "/rebate/rebateSite",
                "City_Target": "iframe",
                "City_IsMenu": true,
                "City_IsExpand": false,
                "City_IsPublic": false,
                "City_AllowEdit": false,
                "City_AllowDelete": false,
                "City_SortCode": 3,
                "City_DeleteMark": null,
                "City_EnabledMark": true,
                "City_Description": "各网站的返利点",
                "City_CreatorTime": "2016-12-16 11:40:28",
                "City_CreatorUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
                "City_LastModifyTime": "2016-12-22 14:57:04",
                "City_LastModifyUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
                "City_DeleteTime": null,
                "City_DeleteUserId": null,
                "ChildNodes": []
            }
        ]
    },
    {
        "City_Id": "1a5df304-79ff-4ab3-9f68-caf7c889626b",
        "City_ParentId": "0",
        "City_Layers": null,
        "City_EnCode": null,
        "City_FullName": "开发者文档",
        "City_Icon": "fa fa-gear",
        "City_UrlAddress": "fa-gear",
        "City_Target": "expand",
        "City_IsMenu": false,
        "City_IsExpand": false,
        "City_IsPublic": false,
        "City_AllowEdit": false,
        "City_AllowDelete": false,
        "City_SortCode": 47,
        "City_DeleteMark": null,
        "City_EnabledMark": true,
        "City_Description": "开发者文档",
        "City_CreatorTime": "2016-12-16 11:41:08",
        "City_CreatorUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
        "City_LastModifyTime": "2016-12-16 13:22:48",
        "City_LastModifyUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
        "City_DeleteTime": null,
        "City_DeleteUserId": null,
        "ChildNodes": [
            {
                "City_Id": "fa8e86a0-ec79-4bc6-99ce-316f898857c4",
                "City_ParentId": "1a5df304-79ff-4ab3-9f68-caf7c889626b",
                "City_Layers": null,
                "City_EnCode": null,
                "City_FullName": "APPKEY",
                "City_Icon": null,
                "City_UrlAddress": "/developer/index",
                "City_Target": "iframe",
                "City_IsMenu": true,
                "City_IsExpand": false,
                "City_IsPublic": false,
                "City_AllowEdit": false,
                "City_AllowDelete": false,
                "City_SortCode": 1,
                "City_DeleteMark": null,
                "City_EnabledMark": true,
                "City_Description": "APPKEY",
                "City_CreatorTime": "2016-12-16 11:41:29",
                "City_CreatorUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
                "City_LastModifyTime": "2016-12-22 11:01:50",
                "City_LastModifyUserId": "9f2ec079-7d0f-4fe2-90ab-8b09a8302aba",
                "City_DeleteTime": null,
                "City_DeleteUserId": null,
                "ChildNodes": []
            }
        ]
    }
];

$(function () {
    clients = $.clientsInit();
});
$.clientsInit = function () {
    var dataJson = {
        dataItems: [],
        organize: [],
        role: [],
        duty: [],
        user: [],
        authorizeMenu: [],
        authorizeButton: []
    };
    dataJson.authorizeMenu = userMenu;
    /*if (globalUser.roles === 'ROLE_USER') {
        dataJson.authorizeMenu = userMenu;
    }
    if (globalUser.roles === 'ROLE_SUPER_ADMIN') {
        dataJson.authorizeMenu = superAdminMenu;
    }*/
    return dataJson;
};
