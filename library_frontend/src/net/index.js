import axios from "axios";
import {ElMessage} from "element-plus";

const authItemName = "authorize"
const UserMessage="User";
const defaultUserName='Test';
let Data={
    code: null,
    data: null,
    message:null
}
const accessHeader = () => {
    return {
        'Authorization': `Bearer ${takeAccessToken()}`
    }
}

const defaultError = (error) => {
    console.error(error)
    ElMessage.error('发生了一些错误，请联系管理员')
}

const defaultFailure = (message, status, url) => {
    console.warn(`请求地址: ${url}, 状态码: ${status}, 错误信息: ${message}`)
    ElMessage.warning(message)
}

function takeAccessToken() {
    const str = localStorage.getItem(authItemName) || sessionStorage.getItem(authItemName);
    if(!str) return null
    const authObj = JSON.parse(str)
    if(new Date(authObj.expire) <= new Date()) {
        deleteAccessToken()
        ElMessage.warning("登录状态已过期，请重新登录！")
        return null
    }
    return authObj.token
}

function getUserMessage(){
    let str=null;
    if(str = localStorage.getItem(UserMessage)) {
        console.log(str)
        return JSON.parse(str);
    }
    else if(str = sessionStorage.getItem(UserMessage)){
        console.log(str)
        return JSON.parse(str);
    }
    else
        ElMessage.failure("出现错误，请联系管理员");
}

function getUserIdByName(success){
    let User = {
        userid:null,
        username:null,
        role:null
    }
    User=getUserMessage()
    get(`/api/account/findUserId?UserName=${User.username}`,(data)=>{
        success(data)
    })
}

function storeUserToken(remember,userid,username,role){
    const User={
        userid:userid,
        username:username,
        role:role
    }
    const str = JSON.stringify(User)
    if(remember)
    {
        localStorage.setItem(UserMessage, str)
    }
    else
        sessionStorage.setItem(UserMessage, str)
}

function storeAccessToken(remember, token, expire){
    const authObj = {
        token: token,
        expire: expire
    }
    const str = JSON.stringify(authObj)
    if(remember)
    {
        localStorage.setItem(authItemName, str)

    }
    else
        sessionStorage.setItem(authItemName, str)
}

function deleteAccessToken() {
    localStorage.removeItem(UserMessage)
    sessionStorage.removeItem(UserMessage)
    localStorage.removeItem(authItemName)
    sessionStorage.removeItem(authItemName)
}

function internalPost(url, data, headers, success, failure, error = defaultError){
    axios.post(url, data, { headers: headers }).then(({data}) => {
        if(data.code === 200)
            success(data.data)
        else
            failure(data.message, data.code, url)
    }).catch(err => error(err))
}

function internalGet(url, headers, success, failure, error = defaultError){
    axios.get(url, { headers: headers }).then(({data}) => {
        if(data.code === 200)
            success(data.data)
        else
            failure(data.message, data.code, url)
    }).catch(err => error(err))
}

function login(username, password, remember, success, failure = defaultFailure){
    internalPost('/api/auth/login', {
        username: username,
        password: password
    }, {
        'Content-Type': 'application/x-www-form-urlencoded'
    }, (data) => {
        storeAccessToken(remember, data.token, data.expire)
        storeUserToken(remember,data.id,username,data.role)
        ElMessage.success(`登录成功，欢迎 ${data.username} 来到我们的系统`)
        success(data)
    }, failure)
}

function post(url, data, success, failure = defaultFailure) {
    internalPost(url, data, accessHeader() , success, failure)
}

function borrowBook(uid,bid,success){
    post('/api/book/borrowBook',{
        uid:uid,
        bid:bid
    },(data)=>{
        ElMessage.success(`书籍借阅成功`)
        success(data)
    })
} //借书

function returnBook(uid,bid,success){
    post('/api/book/returnBook',{
        uid:uid,
        bid:bid
    },(data)=>{
        ElMessage.success(`书籍归还成功`)
        success(data)
    })
}//还书
function addBook(title,author,desc,label,success){
    post('/api/book/addBook',{
        title: title,
        author:author,
        desc: desc,
        label: label,
    },(data)=>{
        ElMessage.success(`书籍添加成功`)
        success(data)
    })
}//添加书籍

function deleteBook(bid,success){
    post('/api/book/deleteBook',{
        bid: bid,
    },(data)=>{
        ElMessage.success(`书籍添加成功`)
        success(data)
    })
}//删除书籍

function logout(success, failure = defaultFailure){
    get('/api/auth/logout', () => {
        deleteAccessToken()
        ElMessage.success(`退出登录成功，欢迎您再次使用`)
        success()
    }, failure)
}

function getBookList(type,success, failure = defaultFailure){
    get(`/api/book/bookList?type=${type}`,(data)=>{
        success(data)
    })
}//获取书籍列表

function getBookCount(success, failure = defaultFailure){
    get(`/api/book/bookCount`,(data)=>{
        success(data)
    })
}//获取书籍总数

function getUserCount(success){
    get("/api/account/userCount",(data)=>{
        success(data);
    })
}//获取用户总数

function getUserList(success){
    get("/api/account/userList",(data)=>{
        success(data);
    })
}//获取用户列表

function getUserBorrowMessage(uid,success){

    post(`/api/account/getBorrowMessage`,{
        uid:uid
    },(data)=>{
        success(data)
    })

}//获取用户借阅书籍信息

function get(url, success, failure = defaultFailure) {
    internalGet(url, accessHeader(), success, failure)
}
function unauthorized() {
    return !takeAccessToken()
}


export { post, get, login, logout, unauthorized,
    getUserMessage,getUserIdByName,getBookList,getBookCount,borrowBook,returnBook,addBook,deleteBook,getUserList
    ,getUserBorrowMessage,getUserCount};
