import pako from "pako.min.js"
//const polyfill = require('base64.min.js')
//const {atob} = polyfill;
//解压
export const unzip = (strData) => {
  let charData = strData.split('').map(function(x){return x.charCodeAt(0);});
  let binData = new Uint8Array(charData);
  let data = pako.inflate(binData);
  return Utf8ArrayToStr(data);
}

//解决数据过大和中文乱码
export const Utf8ArrayToStr = (array) => {
  let out, i, len, c;
  let char2, char3;

  out = "";
  len = array.length;
  i = 0;
  while(i < len) {
    c = array[i++];
    switch(c >> 4)
    {
      case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7:
      // 0xxxxxxx
      out += String.fromCharCode(c);
      break;
      case 12: case 13:
      // 110x xxxx   10xx xxxx
      char2 = array[i++];
      out += String.fromCharCode(((c & 0x1F) << 6) | (char2 & 0x3F));
      break;
      case 14:
        // 1110 xxxx  10xx xxxx  10xx xxxx
        char2 = array[i++];
        char3 = array[i++];
        out += String.fromCharCode(((c & 0x0F) << 12) |
          ((char2 & 0x3F) << 6) |
          ((char3 & 0x3F) << 0));
        break;
    }
  }
  return out;
}
