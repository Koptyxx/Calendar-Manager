import { createRouter, createWebHistory } from 'vue-router'
import Login from '../components/FridayLogin'
import Register from '../components/FridayRegistration'
import Home from '../views/Home'

const routes = [

    {
        path: '/',
        name: 'Home',
        component: Home
    },
    {
        path: '/login',
        name: 'Login',
        component: Login
    },
    {
        path: '/register',
        name: 'Register',
        component: Register
    }
]

const router = createRouter({

    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router
