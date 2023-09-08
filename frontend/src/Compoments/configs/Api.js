import axios from "axios";
import cookie from "react-cookies";

const SERVER_CONTEXT = '/WebBookingServer'
const SERVER = 'http://localhost:8080'

export const endpoints = {
    'CSCS': `${SERVER_CONTEXT}/api/admin/cscs/`,
    'loginStaff': `${SERVER_CONTEXT}/api/login/staff`,
    'loginCus': `${SERVER_CONTEXT}/api/login/customer`,
    'current-user': `${SERVER_CONTEXT}/api/current-user/`,
    'user': (userId) => `${SERVER_CONTEXT}/api/user/${userId}/`,
}
export const authAPI = () => {
    return axios.create({
        baseURL: SERVER,
        headers: {
            'Authorization': cookie.load('token'),
            'Content-Type': 'multipart/form-data'
        }
    })
}

export default axios.create({
    baseURL: SERVER
})