<template>
    <div class="user-blog">
      <h1>User Blog</h1>
      <p>사용자 ID: {{ userId }}</p>
      <!-- 여기서 해당 사용자의 게시글 목록을 표시합니다. -->
    </div>
  </template>
  
  <script>
  export default {
    name: "UserBlog",
    props: {
      userId: {
        type: String,
        required: true
      }
    }
  }
  </script>
  
  <style scoped>
  .user-blog {
    padding: 20px;
  }
  </style>
  