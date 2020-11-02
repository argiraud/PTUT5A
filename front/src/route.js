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
        path: '/:notFound(.*)', component: NotFound
},
];