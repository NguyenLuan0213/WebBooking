import axios from "axios";
import cookie from "react-cookies";

const SERVER_CONTEXT = '/WebBookingServer'
const SERVER = 'http://localhost:8080'

export const endpoints = {
    'login': `${SERVER_CONTEXT}/api/login/`,
    'CSCS': `${SERVER_CONTEXT}/api/cscs/`,
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