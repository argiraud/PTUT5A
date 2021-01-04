import Home from '@/views/Home.vue';
import Dashboard from '@/views/Dashboard.vue';
import NotFound from '@/views/NotFound.vue';
import ConnexionForm from "@/views/ConnexionForm";
import AdmiDonnee from "@/views/AdmiDonneeForm";
import QuestionnaireSatisfaction from "@/views/QuestionnaireSatisfactionForm";
import Profile from "@/views/Profile";

export const routes = [
    {
        path: '/home',
        name: 'home',
        component: Home
    },
    {
        path: '/profile',
        name: 'profile',
        component: Profile
    },
    {
        path: '/Connexion',
        name: 'Connexion',
        component: ConnexionForm
    },
    {
        path: '/adminDonnees',
        name: 'Administration des données',
        component: AdmiDonnee
    },
    {
        path: '/questionSatisf',
        name: 'Questionnaire de Satisfaction',
        component: QuestionnaireSatisfaction
    },
    {
        path: '/creationCompte',
        name: 'Creation',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "about" */ '@/components/Stepper.vue')
    },
    {
        path: '/dashboard', name: 'dashboard', components: {
            default: Dashboard,
        }
    },
    {
        path: '/:notFound(.*)', component: NotFound
    },
];