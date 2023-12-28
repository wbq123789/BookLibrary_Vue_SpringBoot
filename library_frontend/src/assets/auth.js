import Cookies from 'js-cookie'
const TokenKey = 'myZTtoken'
// 从缓存中读取token
export function getToken() {
  return Cookies.get(TokenKey)
}
 
export function setToken(token) {
  return Cookies.set(TokenKey, token)
}
 
export function removeToken() {
  return Cookies.remove(TokenKey)
}