// index.ts

const app = getApp();

Page({
  data: {
    items: [] as any[],
    page: 0,//开始页面
    pageSize: 6, //初始页默认值
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
    //获取上次加载的旧数据，第一次为空
    let oldList = this.data.items;
    app.getRequest('/unit/loadItems', this.data.page, this.data.pageSize, (resp) => {
      wx.hideLoading({
        success: (res) => {},
      })
      //将旧数据与新数据合并
      let newList = oldList.concat(resp);
      this.setData({
        items: newList
      })
    }, (err) => {
      console.log(err.errMsg)
    })
  },

  loadPage() {
    if (this.data.items.length >= 8) {
      return false;
    }

    wx.showLoading({
      title: '加载中..',
    });
    let page = this.data.page + this.data.pageSize;
    this.setData({
      page: page,
      pageSize: this.data.pageSize, //更新当前页数
    });

    this.loadItems();
  },

  showItemDetail(e) {
    let item = e.currentTarget.dataset.item;
    let dataStr = JSON.stringify(item)
    wx.navigateTo({
      url: '../bookDetail/bookDetail?dataStr=' + encodeURIComponent(dataStr)
    });
  }

})
