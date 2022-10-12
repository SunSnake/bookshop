import {isEmpty, MsgToast} from "../../utils/util";

const app = getApp();

Page({

  data: {
    isbn: '',
    book: {
      id: '',
      name: '',
      subname: '',
      photoUrl: '',
      published: '',
      publishing: '',
      designed: '',
      description: '',
      authorIntro: '',
      author: ''
    },
    hideContent: true
  },

  findBookByISBN() {
    if (isEmpty(this.data.isbn)) {
      MsgToast('请输入isbn号码查询！');
      return;
    }

    if (!/^\d+$/.test(this.data.isbn) || !(this.data.isbn.length == 13 || this.data.isbn.length == 10)) {
      MsgToast('请输入正确13位isbn号码！');
      return;
    }

    /*if (!checkISBN13(this.data.isbn) || !checkISBN10(this.data.isbn)) {
      MsgToast('isbn号码校验不通过，请手动输入书籍信息！');
      return;
    }*/

    wx.showLoading({
      title: '查询中..',
    });

    app.getRequest('/book/findBookByISBN?isbn=' + this.data.isbn, (resp) => {
      wx.hideLoading();
      if (resp.status == -1) {
        MsgToast(resp.msg);
        return;
      }

      this.setData({
        book: resp.data,
        hideContent: false
      });
    }, (err) => {
      wx.hideLoading();
      MsgToast(err.errMsg);
    })
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
