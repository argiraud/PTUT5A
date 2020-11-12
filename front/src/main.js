import App from './App.vue'
import Vue from 'vue'
import VueRouter from 'vue-router';
import { routes } from './route';
import vuetify from './plugins/vuetify';
import store from './store'


Vue.use(VueRouter);

const router = new VueRouter({
    routes,
    mode: 'history',
    scrollBehavior(to, from, savedPosition) {
        if (savedPosition) {
            return savedPosition;
        }
        if (to.hash) {
            return { selector: to.hash };
        }
        return {x: 0, y: 0};
    }
});

router.beforeEach((to, from, next) => {
    console.log('global beforeEach');
    next();
});

new Vue({
    el: '#app',
    router,
    vuetify,
    store,
    render: h => h(App)
})
