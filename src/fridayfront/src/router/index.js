import { createRouter, createWebHashHistory } from 'vue-router'
import Register from '../components/FridayRegistration'
import Calendar from '../components/CalendarManager'
import Login from '../components/FridayLogin'
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
    },
    {
        path: "/calendar",
        name: 'Calendar',
        component: Calendar
    }
]

const router = createRouter({

    history: createWebHashHistory(),
    routes
})

export default router
