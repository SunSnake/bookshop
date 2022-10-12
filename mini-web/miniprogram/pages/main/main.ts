// index.ts
import {MsgToast} from "../../utils/util";

const app = getApp();

Page({
  data: {
    books: [] as any[],
    current: 1,//开始页面
    pageSize: 6, //初始页默认值
    total: 0
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
    let oldList = this.data.books;
    app.getRequestWithPage('/book/loadBooks', this.data.current, this.data.pageSize, (resp) => {
      wx.hideLoading();

      if (resp.status == -1) {
        MsgToast(resp.msg);
        return;
      }

      let data = resp.data;
      //将旧数据与新数据合并
      let newList = oldList.concat(data.records);
      this.setData({
        books: newList,
        total: data.total
      })
    }, (err) => {
      wx.hideLoading();
      MsgToast(err.errMsg);
    })
  },

  loadPage() {
    if (this.data.books.length >= this.data.total) {
      return false;
    }

    wx.showLoading({
      title: '加载中..',
    });
    this.setData({
      current: ++this.data.current,
      pageSize: this.data.pageSize, //更新当前页数
    });

    this.loadItems();
  },

  showItemDetail(e) {
    let book = e.currentTarget.dataset.book;
    let dataStr = JSON.stringify(book)
    wx.navigateTo({
      url: '../bookDetail/bookDetail?dataStr=' + encodeURIComponent(dataStr)
    });
  }

})
