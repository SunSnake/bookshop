export const formatTime = (date: Date) => {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return (
    [year, month, day].map(formatNumber).join('/') +
    ' ' +
    [hour, minute, second].map(formatNumber).join(':')
  )
}

const formatNumber = (n: number) => {
  const s = n.toString()
  return s[1] ? s : '0' + s
}

export const str2List = (str) => {
  if (str.substring(0, 1) !== "[" || str.substring(str.length - 1) !== "]") {
    throw new Error("数组格式非法！")
  }
  return str.substring(1, str.length-1).split(", ");
}
