// index.ts
import {unzip} from '../../utils/pako'
import {str2List} from '../../utils/util'

const app = getApp();

Page({
  data: {
    items: [] as any[]
  },

  onLoad() {
    this.loadItems();
  },

  onShow() {
    let tabBar = this.getTabBar();
    if (typeof this.getTabBar === 'function' && tabBar) {
      tabBar.setData({
        active: getApp().globalData.active
      })
    }
  },

  loadItems() {
    app.getRequest('/unit/loadItems', {}, (resp) => {
      let dataStr = unzip(resp);
      let list: any[] = str2List(dataStr);

      this.setData({
        items: list.map((item: Object) => {
          return JSON.parse(<string>item);
        }),
      })
    }, (err) => {
      console.log(err.errMsg)
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
