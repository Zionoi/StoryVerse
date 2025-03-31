<template>
  <div class="post-detail">
    <h1>📌 게시글 상세</h1>

    <div v-if="post">
      <h2>{{ post.title }}</h2>
      <p>{{ post.content }}</p>
      <small>{{ post.date }}</small>
    </div>
    <p v-else>게시글을 찾을 수 없습니다.</p>
  </div>
</template>

<script>
import api from '@/api'

export default {
  name: 'PostDetail',
  props: ['id'],
  data() {
    return {
      post: null
    }
  },
  async mounted() {
    try {
      const res = await api.get(`/posts/${this.id}`)
      this.post = res.data
    } catch (err) {
      console.error('게시글 상세 불러오기 실패:', err)
    }
  }
}
</script>
