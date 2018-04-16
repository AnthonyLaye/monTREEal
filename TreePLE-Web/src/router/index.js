import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'

import TreePLE from '@/components/TreePLE'
import Forecast from '@/components/Forecast'
import BioIndex from '@/components/BioIndex'
import Home from '@/components/Home'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/home',
      name: 'Home',
      component: Home
    },
    {
      path: '/dashboard',
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
      component: require('../components/Register.vue').default
    },
    {
      path: '/forecast',
      name: 'Forecast',
      component: Forecast
    },
    {
      path: '/bioindex',
      name: 'BioIndex',
      component: BioIndex
    },
    {
      path: '/carbonsequestration',
      name: 'CarbonSequestration',
      component: require('../components/CarbonSequestration.vue').default
    },
    {
      path: '/waterindex',
      name: 'WaterIndex',
      component: require('../components/WaterIndex.vue').default
    }
  ]
})
