import Home from '@/views/Home.vue';
import NotFound from '@/views/NotFound.vue';
import ConnexionForm from "@/views/ConnexionForm";
import Navigation from "@/components/Navigation";
import AdmiDonnee from "@/views/AdmiDonnee";
import QuestionnaireSatisfaction from "@/views/QuestionnaireSatisfaction";

export const routes = [
    {
        path: '/home', name: 'home', components: {
            default: Home,
            'header-top': Navigation
        }
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
        path: '/:notFound(.*)', component: NotFound
    },
];