import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Profile from '../views/Profile.vue'
import PostDetail from '../views/PostDetail.vue'
import CreatePost from '../views/CreatePost.vue'
import UserBlog from '../views/UserBlog.vue'
import TestCom from '@/views/testCom.vue'
import LoginPage from '@/views/LoginPage.vue'
import SignupPage from '@/views/SignupPage.vue'

const routes = [
  { path: '/', name: 'Home', component: Home,meta: { requiresAuth: true } },
  { path: '/profile', name: 'Profile', component: Profile, meta: { requiresAuth: true } },
  { path: '/post/:id', name: 'PostDetail', component: PostDetail, props: true },
  { path: '/create', name: 'CreatePost', component: CreatePost },
  { path: '/user/:userId', name: 'UserBlog', component: UserBlog, props: true },
  { path: '/test', name: 'testCom', component: TestCom },
  { path: '/login', name: 'LoginPage', component: LoginPage },
  { path: '/signup', name: 'SignupPage', component: SignupPage } // ✅ 추가
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach(async (to, from, next) => {
    // ✅ 인증이 필요 없는 페이지 정의
    const publicPages = ['/login', '/signup']
    const isPublic = publicPages.includes(to.path)
  
    const isAuthenticated = await checkLogin()
  
    if (!isPublic && to.meta.requiresAuth && !isAuthenticated) {
      next('/login')
    } else {
      next()
    }
  })

async function checkLogin() {
  try {
    const res = await fetch('http://localhost:8080/api/auth/check', {
      method: 'GET',
      credentials: 'include'
    })
    return res.ok
  } catch (e) {
    return false
  }
}

export default router
