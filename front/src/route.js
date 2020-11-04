import Home from '@/views/Home.vue';
import NotFound from '@/views/NotFound.vue';
import RegisterForm from "@/views/RegisterForm";
import ConnexionForm from "@/views/ConnexionForm";
import Navigation from "@/components/Navigation";

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
        path: '/:notFound(.*)', component: NotFound
    },
];