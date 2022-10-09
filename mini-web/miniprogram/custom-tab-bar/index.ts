// index.ts
Page({
  data: {
    active: 0,
  },

  onChange(event) {
    getApp().globalData.active = event.detail;

    switch (event.detail) {
      case 0:
        wx.switchTab({
          url: '../main/main'
        });
        break;

      case 1:
        wx.switchTab({
          url: '../friend/friend'
        });
        break;

      case 2:
        wx.switchTab({
          url: '../shopping/shopping'
        });
        break;

      case 3:
        wx.switchTab({
          url: '../bookcase/bookcase'
        });
        break;

      case 4:
        wx.switchTab({
          url: '../user/user'
        });
        break;
    }
  }
})
