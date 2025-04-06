<template>
    <div class="signup-container">
      <h2>회원가입</h2>
      <form @submit.prevent="signup">
        <input v-model="userId" placeholder="아이디" required />
        <input v-model="password" type="password" placeholder="비밀번호" required />
        <input v-model="fullName" placeholder="이름" required />
        <input v-model="nickname" placeholder="닉네임" />
        <input v-model="birthDate" type="date" placeholder="생일" />
        <button type="submit">회원가입</button>
      </form>
      <p v-if="message">{{ message }}</p>
    </div>
  </template>
  
  <script>
  import axios from 'axios'
  
  export default {
    name: 'SignupPage',
    data() {
      return {
        userId: '',
        password: '',
        fullName: '',
        nickname: '',
        birthDate: '',
        message: ''
      }
    },
    methods: {
      async signup() {
        try {
          await axios.post('http://localhost:8080/api/auth/signup', {
            userId: this.userId,
            password: this.password,
            fullName: this.fullName,
            nickname: this.nickname,
            birthDate: this.birthDate
          })
          this.message = '회원가입 성공! 로그인 페이지로 이동합니다.'
          this.$router.push('/login')
        } catch (err) {
          this.message = '회원가입 실패: ' + (err.response?.data || '서버 오류')
        }
      }
    }
  }
  </script>
  
  <style scoped>
  .signup-container {
    max-width: 400px;
    margin: 0 auto;
    padding: 1rem;
  }
  input {
    display: block;
    margin-bottom: 10px;
    width: 100%;
  }
  button {
    width: 100%;
  }
  </style>
  