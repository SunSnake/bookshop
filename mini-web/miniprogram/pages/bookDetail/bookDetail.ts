// bookcase.ts
Page({
  data: {
    item: ''
  },

  onLoad (options) {
    let item = JSON.parse(decodeURIComponent(<string>options.dataStr));
    console.log(item)
    this.setData({
      item: item
    })
  }
})
