// index.ts

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
      this.setData({
        items: resp
      })
    }, (err) => {
      console.log(err.errMsg)
    })
  },

  showItemDetail(e) {
    let item = e.currentTarget.dataset.item;
    let dataStr = JSON.stringify(item)
    wx.navigateTo({
      url: '../bookDetail/bookDetail?dataStr=' + encodeURIComponent(dataStr)
    });
  }

})
