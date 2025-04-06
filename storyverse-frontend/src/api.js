import axios from 'axios'

const api = axios.create({
  baseURL: '/api', // vue.config.js에서 프록시 설정된 경로
  headers: {
    'Content-Type': 'application/json'
  },
  withCredentials: true // 쿠키(인증 토큰 등) 자동 포함
})

export default api
