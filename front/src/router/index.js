import { createRouter, createWebHashHistory } from 'vue-router'
import RegisterForm from '../components/RegisterForm.vue'
import ConnexionForm from '../components/ConnexionForm.vue'

const routes = [
  {
    path: '/',
    name: 'Connexion',
    component: ConnexionForm
  },
  {
    path: '/Register',
    name: 'Register',
    component: RegisterForm
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
