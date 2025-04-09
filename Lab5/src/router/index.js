import { createRouter, createWebHistory } from 'vue-router';
import CategoryForm from '../components/CategoryForm.vue';

const routes = [
  { path: '/', component: { template: '<h1>Trang Chủ</h1>' } },
  { path: '/categories', component: CategoryForm },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
