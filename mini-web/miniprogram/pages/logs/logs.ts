// logs.ts
import {formatTime} from '../../utils/util'

Page({
  data: {
    logs: []
  },

  onShow() {
    let tabBar = this.getTabBar();
    if (typeof this.getTabBar === 'function' && tabBar) {
      tabBar.setData({
        active: getApp().globalData.active
      })
    }
  },

  onLoad() {
    this.setData({
      logs: (wx.getStorageSync('logs') || []).map((log: string) => {
        //console.log('log');
        return {
          date: formatTime(new Date(log)),
          timeStamp: log
        }
      })
    })
  },

})
