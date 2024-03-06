import Vue from 'vue'
import Router from 'vue-router'
import QuestionsList from '@/components/QuestionsList'
import QuestionForm from '@/components/QuestionForm'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'QuestionsList',
      component: QuestionsList
    },
    {
      path: '/question/add',
      name: 'AddQuestion',
      component: QuestionForm
    },
    {
      path: '/question/edit/:id',
      name: 'EditQuestion',
      component: QuestionForm,
      props: true
    }
  ]
})
