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
  export default {
    name: "PostDetail",
    props: {
      id: {
        type: String,
        required: true
      }
    },
    data() {
      return {
        post: null,
        posts: [
          {
            id: 1,
            title: 'Vue.js로 시작하는 감성 블로그',
            content: '이 프로젝트는 Vue와 Spring Boot로 만드는 블로그 시스템입니다.',
            date: '2025-03-27'
          },
          {
            id: 2,
            title: '오늘의 이야기 - 별을 보다',
            content: '밤하늘의 별을 보며 떠올린 감정을 글로 남겨봤어요.',
            date: '2025-03-26'
          }
        ]
      }
    },
    mounted() {
      // props.id는 문자열이므로 숫자로 변환해서 비교
      const postId = parseInt(this.id)
      this.post = this.posts.find(p => p.id === postId)
    //  추후 백앤드와 연결시 아래 코드로 대체
    //  axios.get(`/api/posts/${this.id}`)
    // .then(res => {
    //   this.post = res.data
    // })
    // .catch(err => {
    //   console.error(err)
    // })
    }
  }
  </script>
  
  <style scoped>
  .post-detail {
    padding: 20px;
  }
  </style>
  