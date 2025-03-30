import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Profile from '../views/Profile.vue'
import PostDetail from '../views/PostDetail.vue'
import CreatePost from '../views/CreatePost.vue'
import UserBlog from '../views/UserBlog.vue'
import TestCom from '@/views/testCom.vue'

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home
    },
    {
        path: '/profile',
        name: 'Profile',
        component: Profile,
        meta: { requiresAuth: true } // 로그인한 사용자만 접근하도록 설정할 수 있습니다.
    },
    {
        path: '/post/:id',
        name: 'PostDetail',
        component: PostDetail,
        props: true // URL 파라미터를 컴포넌트의 props로 전달
    },
    {
        path: '/create',
        name: 'CreatePost',
        component: CreatePost
    },
    {
        path: '/user/:userId',
        name: 'UserBlog',
        component: UserBlog,
        props: true  // URL의 userId를 컴포넌트 props로 전달
    },
    {
        path: '/test',
        name: 'testCom',
        component: TestCom,
    }

]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router