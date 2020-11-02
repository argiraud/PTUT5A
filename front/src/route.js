import Home from '@/views/Home.vue';
import NotFound from '@/views/NotFound.vue';
import RegisterForm from "@/views/RegisterForm";
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
        path: '/Register',
        name: 'Register',
        component: RegisterForm
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
        path: '/:notFound(.*)', component: NotFound
    },
];