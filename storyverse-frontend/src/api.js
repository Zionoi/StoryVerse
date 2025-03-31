import axios from 'axios'

const api = axios.create({
  baseURL: '/api', // vue.config.js에서 프록시 설정 했기 때문에 가능
  headers: {
    'Content-Type': 'application/json'
  }
})

export default api
