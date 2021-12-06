import { createWebHistory, createRouter } from "vue-router";
import Main from "./components/Main.vue";
import Register from "./components/Register.vue";
import NotFound from "./components/NotFound.vue";

const routes = [
    {
        path: "/",
        component: Main,
    },
    {
        path: "/register",
        component: Register,
    },
    {
        path: "/:anything(.*)",
        component: NotFound,
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;