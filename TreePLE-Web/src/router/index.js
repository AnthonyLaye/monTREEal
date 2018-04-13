import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Register from '@/components/Register'
import TreePLE from '@/components/TreePLE'
import Forecast from '@/components/Forecast'
import BioIndex from '@/components/BioIndex'
import CarbonSequestration from '@/components/CarbonSequestration'
import WaterIndex from '@/components/WaterIndex'

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
      component: CarbonSequestration
    },
    {
      path: '/waterindex',
      name: 'WaterIndex',
      component: WaterIndex
    }
  ]
})
