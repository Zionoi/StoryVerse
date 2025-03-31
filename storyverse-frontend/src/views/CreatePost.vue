<template>
  <div class="create-post">
    <h1>새 게시글 작성</h1>
    <form @submit.prevent="submitPost">
      <label>제목:</label>
      <input v-model="title" required />

      <label>내용:</label>
      <textarea v-model="content" required></textarea>

      <label>공개 여부:</label>
      <input type="checkbox" v-model="isOpen" />

      <button type="submit">작성하기</button>
    </form>
  </div>
</template>

<script>
import api from '@/api'

export default {
  name: 'CreatePost',
  data() {
    return {
      title: '',
      content: '',
      userId: 'kimyohan', // 로그인 기능 붙으면 교체 예정
      isOpen: true,
      date: new Date().toISOString().slice(0, 10) // 오늘 날짜 (YYYY-MM-DD)
    }
  },
  methods: {
    async submitPost() {
      try {
        const post = {
          title: this.title,
          content: this.content,
          userId: this.userId,
          isOpen: this.isOpen,
          date: this.date
        }
        await api.post('/posts', post)
        alert('작성 완료!')
        this.$router.push('/')
      } catch (err) {
        console.error('작성 실패:', err)
        alert('작성 중 오류가 발생했습니다.')
      }
    }
  }
}
</script>
