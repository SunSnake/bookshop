// app.ts
App<IAppOption>({
  //设置全局请求URL
  globalData:{
    URL: 'http://127.0.0.1:8080',
    sessionKey: ''
  },

  onLaunch() {
    // 展示本地存储能力
    const logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    // 登录
    wx.login({
      success: res => {
        getApp().postRequest('/member/login', {code: res.code}, resp => {
          if (resp.status == -1) {
            wx.showToast({
              title: resp.msg,
              duration: 0,
              mask: true
            });
            return;
          }

          wx.setStorageSync('sessionKey', resp.data)
          console.log(resp)
        }, err => {
          console.log(err)
        });
      },
    })
  },

  getRequest(url, page, pageSize, success, failCallback) {
    if (page !== '' && pageSize != '') {
      url += '?page=' + page + '&pageSize=' + pageSize;
    }
    getApp().wxRequest('GET', url, {}, success, failCallback)
  },

  postRequest(url, data, success, failCallback) {
    getApp().wxRequest('POST', url, data, success, failCallback)
  },

  /**
   * 封装wx.request请求
   * method： 请求方式
   * url: 请求地址
   * data： 要传递的参数
   * callback： 请求成功回调函数
   * errFun： 请求失败回调函数
   **/
  wxRequest(method, url, data, success, failCallback) {
    wx.request({
      url: getApp().globalData.URL + url,
      method: method,
      data: data,
      header: {
        // application/x-www-form-urlencoded
        'content-type': 'application/json;charset=UTF-8',
        'Accept': 'application/json',
        'token': wx.getStorageSync('sessionKey')
      },
      dataType: 'json',
      success: function (res) {
        let resp:{} = res.data
        if (resp) {
          success(resp);
        } else {
          failCallback(resp.msg || '系统出小差了');
        }
      },
      fail: function (err) {
        failCallback(err);
      }
    })
  }

})
