
export const checkISBN10 = code => {
  code = (code + '').replace(/[-\s]/g,'');
  if (!/^\d{9}[\dxX]?$/.test(code)) {
    return;
  }
  let i = 0,c = 0; // c:checksum
  while (i < 9) {
    c += code.charAt(i++)*i;
  }
  c %= 11;
  if (c === 10) {
    c = 'X';
  }
  if (code.length === 9) {
    return code+c;
  }

  return c === (i = code.charAt(9)) || c==='X' && i === 'x';
}

export const checkISBN13 = code => {
  code = (code + '').replace(/[-\s]/g,'');
  if (!/^\d{12,13}$/.test(code)) {
    return;
  }
  let i = 1,c = 0; // c:checksum
  for (;i < 12; i += 2) {
    c += Math.floor(code.charAt(i));
  }
  for (c *= 3, i = 0; i < 12; i += 2) {
    c += Math.floor(code.charAt(i));
  }

  c = (220 - c) % 10; // 220:大於(1*6+3*6)，%10==0即可。
  if (code.length === 12) {
    return code+c;
  }
  return c === code.charAt(12);
}
