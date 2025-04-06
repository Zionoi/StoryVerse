<template>
  <div class="home">
    <!-- 상단 로그인/회원가입/로그아웃 -->
    <div class="auth-buttons" style="text-align: right">
      <template v-if="!isLoggedIn">
        <router-link to="/login" class="btn">로그인</router-link>
        <router-link to="/signup" class="btn">회원가입</router-link>
      </template>
      <template v-else>
        <button @click="logout" class="btn">로그아웃</button>
      </template>
    </div>

    <h1>여러 이야기가 모여 펼쳐지는 당신만의 우주</h1>

    <!-- 로그인 시에만 글쓰기 버튼 보이게 -->
    <router-link v-if="isLoggedIn" to="/create" class="write-link">✍ 글쓰기</router-link>

    <div class="view-mode-toggle">
      <button @click="viewMode = 'list'">📋 목록형</button>
      <button @click="viewMode = 'card'">🟦 카드형</button>
    </div>

    <p v-if="posts.length === 0">게시글이 없습니다.</p>

    <!-- 목록형 -->
    <div v-if="viewMode === 'list'" class="post-list">
      <div
        v-for="post in posts"
        :key="post.postId"
        class="post-list-item"
        @click="goToDetail(post.postId)"
        style="cursor: pointer"
      >
        <h3>{{ post.title }}</h3>
        <p>{{ post.postContent }}</p>
        <small>{{ post.postDate }}</small>
      </div>
    </div>

    <!-- 카드형 -->
    <div v-else class="post-card-list">
      <div
        v-for="post in posts"
        :key="post.postId"
        class="post-card"
        @click="goToDetail(post.postId)"
        style="cursor: pointer"
      >
        <h3>{{ post.title }}</h3>
        <p>{{ post.postContent }}</p>
        <small>{{ post.postDate }}</small>
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
      posts: [],
      isLoggedIn: false,
      userId: ''  // 사용자 ID 저장
    }
  },
  methods: {
    async fetchPosts() {
      try {
        const res = await api.get(`/posts/user/${this.userId}`, { withCredentials: true })
        this.posts = res.data
      } catch (err) {
        console.error('내 게시글 불러오기 실패:', err)
        this.posts = []
      }
    },
    async checkLogin() {
      try {
        const res = await api.get('/auth/check', { withCredentials: true })
        this.userId = res.data.userId     // ✅ 여기서 userId 저장
        this.isLoggedIn = true
      } catch {
        this.isLoggedIn = false
      }
    },
    goToDetail(postId) {
      this.$router.push(`/post/${postId}`)
    },
    async logout() {
      try {
        await api.post('/auth/logout', {}, { withCredentials: true })
        this.isLoggedIn = false
        this.posts = []
        this.$router.push('/login')
      } catch (e) {
        console.error('로그아웃 실패:', e)
      }
    },
    getTokenFromCookie() {
      const match = document.cookie.match(new RegExp('(^| )token=([^;]+)'))
      return match ? decodeURIComponent(match[2]) : null
    },
    parseJwt(token) {
      try {
        const base64Payload = token.split('.')[1]
        const payload = atob(base64Payload)
        return JSON.parse(payload)
      } catch {
        return {}
      }
    }
  },
  mounted() {
    this.checkLogin().then(() => {
      if (this.isLoggedIn) this.fetchPosts()
    })
  }
}
</script>



<style scoped>
.write-link {
  display: inline-block;
  margin: 1rem 0;
  font-weight: bold;
}
.auth-buttons .btn {
  margin-left: 1rem;
}
</style>
