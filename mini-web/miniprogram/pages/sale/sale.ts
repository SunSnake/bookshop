
const app = getApp()

Page({

  data: {
    bookList: [],
    totalPrice: 0
  },

  navigateToAddBook() {
    wx.navigateTo({
      url: '../shopping/shopping'
    });
  },

  onShow() {
    let tabBar = this.getTabBar();
    if (typeof this.getTabBar === 'function' && tabBar) {
      tabBar.setData({
        active: getApp().globalData.active
      })
    }
  },

})
