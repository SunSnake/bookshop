// index.ts
import {unzip} from '../../utils/pako'
import {str2List} from '../../utils/util'

Page({
  data: {
    items: [] as any[]
  },

  onLoad() {
    this.loadItems();
  },

  onShow() {
    if (typeof this.getTabBar === 'function' && this.getTabBar()) {
      this.getTabBar().setData({
        active: getApp().globalData.active
      })
    }
  },

  loadItems() {
    wx.request({
      url: 'http://127.0.0.1:8080/unit/loadItems',
      success: (res) => {
        let dataStr = unzip(res.data);
        let list: any[] = str2List(dataStr);

        this.setData({
          items: list.map((item: Object) => {
            return JSON.parse(<string>item);
          }),
        })
      }
    })
  },

  showItemDetail(e) {
    let item = e.currentTarget.dataset.item;
    let dataStr = JSON.stringify(item)
    //console.log(dataStr)
    wx.navigateTo({
      url: '../bookDetail/bookDetail?dataStr=' + encodeURIComponent(dataStr)
    });
  }

})
