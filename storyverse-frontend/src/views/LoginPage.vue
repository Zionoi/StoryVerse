<template>
    <div class="login-container">
      <h2>로그인</h2>
      <form @submit.prevent="login">
        <input v-model="userId" placeholder="아이디" />
        <input v-model="password" type="password" placeholder="비밀번호" />
        <button type="submit">로그인</button>
      </form>
      <p v-if="errorMessage">{{ errorMessage }}</p>
  
      <!-- 회원가입 버튼 -->
      <p class="signup-link">
        아직 계정이 없으신가요?
        <router-link to="/signup">회원가입</router-link>
      </p>
    </div>
  </template>
  
  <script>
  import axios from 'axios'
  
  export default {
    data() {
      return {
        userId: '',
        password: '',
        errorMessage: ''
      }
    },
    methods: {
      async login() {
        try {
          await axios.post(
            'http://localhost:8080/api/auth/login',
            { userId: this.userId, password: this.password },
            { withCredentials: true }
          )
          this.$router.push('/')
        } catch (err) {
          this.errorMessage = '로그인 실패. 다시 확인해 주세요.'
        }
      }
    }
  }
  </script>
  