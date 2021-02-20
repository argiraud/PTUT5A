import Home from '@/views/Home.vue';
import Dashboard from '@/views/Dashboard.vue';
import NotFound from '@/views/NotFound.vue';
import ConnexionForm from "@/views/ConnexionForm";
import AdmiDonnee from "@/views/AdmiDonneeForm";
import QuestionnaireSatisfaction from "@/views/QuestionnaireSatisfactionForm";
import Profile from "@/views/Profile";
import DepotOffreForm from "@/views/DepotOffreForm";
import SaisieVoeuxForm from "@/views/SaisieVoeuxForm";

import DepotCandidatureForm from "@/views/DepotCandidatureForm";
import SaisieVoeuxEtudiantForm from "@/views/SaisieVoeuxEtudiantForm";

export const routes = [
    {
        path: '/home',
        name: 'home',
        component: Home
    },
    {
        path: '/Profil',
        name: 'Profile',
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
        path: '/depotOffre',
        name: 'depotOffre',
        component: DepotOffreForm
    },
    {
        path: '/depotCandidature',
        name: 'depotCandidature',
        component: DepotCandidatureForm
    },
    {
        path: '/saisieVoeuxEtudiant',
        name: 'saisieVoeuxEtudiant',
        component: SaisieVoeuxEtudiantForm
    },
    {
        path: '/saisieVoeux',
        name: 'saisieVoeux',
        component: SaisieVoeuxForm
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