import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Register from '@/components/Register'
import TreePLE from '@/components/TreePLE'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/trep',
      name: 'TreePLE',
      component: TreePLE
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
})
