<template>
    <div class="home">
      <h1>여러 이야기가 모여 펼쳐지는 당신만의 우주</h1>
      <!-- router-link 방식 -->
      <router-link to="/create" class="write-link">✍ router-link 방식 글 쓰기 버튼튼 router-link는 내부 링크 용도로 주로 쓰임, 새로고침없이 빠름름</router-link>
        <br>
      <!-- 또는 버튼 방식 -->
      <button @click="goToCreate"> this.$router.push() 사용 방식, js코드로 직접 경로 이동을 제어, 로그인 등 조건/이벤트 기반으로 동작할 때 유용 ➕ 글 작성 페이지로 이동</button>
      <!-- 뷰 모드 전환 버튼 -->
      <div class="view-mode-toggle">
        <button @click="viewMode = 'list'">📋 목록형 보기</button>
        <button @click="viewMode = 'card'">🟦 카드형 보기</button>
      </div>
  
      <!-- 게시글이 없을 때 -->
      <p v-if="posts.length === 0">게시글이 없습니다.</p>
  
      <!-- 목록형 UI -->
      <!-- 목록형 UI -->
<div v-if="viewMode === 'list'" class="post-list">
  <div
    v-for="(post, index) in posts"
    :key="index"
    class="post-list-item"
    @click="goToDetail(post.id)"
    style="cursor: pointer;"
  >
    <h3>{{ post.title }}</h3>
    <p>{{ post.summary }}</p>
    <small>{{ post.date }}</small>
  </div>
</div>

<!-- 카드형 UI -->
<div v-else class="post-card-list">
  <div
    v-for="(post, index) in posts"
    :key="index"
    class="post-card"
    @click="goToDetail(post.id)"
    style="cursor: pointer;"
  >
    <h3>{{ post.title }}</h3>
    <p>{{ post.summary }}</p>
    <small>{{ post.date }}</small>
  </div>
</div>
    </div>
  </template>
  
  <script>
  export default {
    name: 'HomePage',
    data() {
      return {
        viewMode: 'list', // 'list' 또는 'card'
        posts: [
        {
            id: 1,
            title: 'Vue.js로 시작하는 감성 블로그',
            summary: '이 프로젝트는 Vue와 Spring Boot로 만드는 블로그 시스템입니다.',
            date: '2025-03-27'
        },
        {
            id: 2,
            title: '오늘의 이야기 - 별을 보다',
            summary: '밤하늘의 별을 보며 떠올린 감정을 글로 남겨봤어요.',
            date: '2025-03-26'
        }
        ]
      }
      
    },
    methods: {
        goToCreate() {
    this.$router.push('/create')
    },
    goToDetail(postId) {
        this.$router.push(`/post/${postId}`)
    }
  }
  }
  </script>
  
  <style scoped>
  .home {
    padding: 20px;
  }
  
  .view-mode-toggle {
    margin-bottom: 16px;
  }
  
  button {
    margin-right: 10px;
    padding: 8px 12px;
    border: none;
    background-color: #42b983;
    color: white;
    border-radius: 4px;
    cursor: pointer;
  }
  
  button:hover {
    background-color: #36976b;
  }
  
  .post-list-item {
    border-bottom: 1px solid #ddd;
    padding: 10px 0;
  }
  
  .post-card-list {
    display: flex;
    flex-wrap: wrap;
    gap: 16px;
  }
  
  .post-card {
    border: 1px solid #ddd;
    padding: 16px;
    width: 45%;
    box-shadow: 2px 2px 6px rgba(0,0,0,0.1);
    border-radius: 8px;
  }
  </style>
  