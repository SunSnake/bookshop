"use strict";
// index.ts
Page({
    data: {
        current: 'homepage'
    },
    handleChange({ detail }) {
        getApp().globalData.current = detail.key;
        switch (detail.key) {
            case 'homepage':
                wx.switchTab({
                    url: '../index/index'
                });
                break;
            case 'group':
                wx.switchTab({
                    url: '../logs/logs'
                });
                break;
            case 'remind':
                wx.switchTab({
                    url: '../former/former'
                });
                break;
            case 'mine':
                wx.switchTab({
                    url: '../user/user'
                });
                break;
        }
    }
});
