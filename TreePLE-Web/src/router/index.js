import Vue from 'vue'
import Router from 'vue-router'
import TreePLE from '@/components/TreePLE'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/app',
      name: 'TreePLE',
      component: TreePLE
    }
  ]
})
