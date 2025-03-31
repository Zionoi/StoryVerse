<template>
  <div class="home">
    <h1>여러 이야기가 모여 펼쳐지는 당신만의 우주</h1>

    <router-link to="/create" class="write-link">✍ 글쓰기</router-link>

    <div class="view-mode-toggle">
      <button @click="viewMode = 'list'">📋 목록형</button>
      <button @click="viewMode = 'card'">🟦 카드형</button>
    </div>

    <p v-if="posts.length === 0">게시글이 없습니다.</p>

    <!-- 목록형 -->
    <div v-if="viewMode === 'list'" class="post-list">
      <div
        v-for="post in posts"
        :key="post.id"
        class="post-list-item"
        @click="goToDetail(post.id)"
        style="cursor: pointer"
      >
        <h3>{{ post.title }}</h3>
        <p>{{ post.content }}</p>
        <small>{{ post.date }}</small>
      </div>
    </div>

    <!-- 카드형 -->
    <div v-else class="post-card-list">
      <div
        v-for="post in posts"
        :key="post.id"
        class="post-card"
        @click="goToDetail(post.id)"
        style="cursor: pointer"
      >
        <h3>{{ post.title }}</h3>
        <p>{{ post.content }}</p>
        <small>{{ post.date }}</small>
      </div>
    </div>
  </div>
</template>

<script>
import api from '@/api'

export default {
  name: 'HomePage',
  data() {
    return {
      viewMode: 'list',
      posts: []
    }
  },
  methods: {
    async fetchPosts() {
      try {
        const res = await api.get('/posts')
        this.posts = res.data
      } catch (err) {
        console.error('게시글 불러오기 실패:', err)
      }
    },
    goToDetail(id) {
      this.$router.push(`/post/${id}`)
    }
  },
  mounted() {
    this.fetchPosts()
  }
}
</script>
