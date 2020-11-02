import Home from '@/views/Home.vue';
import NotFound from '@/views/NotFound.vue';
import Navigation from "@/components/Navigation";

export const routes = [
    {
        path: '/home', name: 'home', components: {
            default: Home,
            'header-top': Navigation
        }
    },
    {
        path: '/adminDonnees',
        name: 'Administration des données',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "about" */ '@/views/AdmiDonnee.vue')
    },
    {
        path: '/questionSatisf',
        name: 'Questionnaire de Satisfaction',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "about" */ '@/views/QuestionnaireSatisfaction.vue')
    },
    {
        path: '/:notFound(.*)', component: NotFound
    },
];