<template>
    <div class="create-post">
      <h1>Create a New Post</h1>
      <form @submit.prevent="handleSubmit">
        <div>
          <label for="title">제목:</label>
          <input id="title" v-model="title" required>
        </div>
        <div>
          <label for="content">내용:</label>
          <textarea id="content" v-model="content" required></textarea>
        </div>
        <button type="submit">작성하기</button>
      </form>
    </div>
  </template>
  
  <script>
  export default {
    name: "CreatePost",
    data() {
      return {
        title: '',
        content: ''
      }
    },
    methods: {
      handleSubmit() {
        // 폼 제출 시 API 호출 등 처리 로직을 구현합니다.
        console.log("제목:", this.title, "내용:", this.content);
      }
    }
  }
  </script>
  
  <style scoped>
  .create-post {
    padding: 20px;
  }
  </style>
  