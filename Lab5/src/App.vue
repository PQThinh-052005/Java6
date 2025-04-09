<script setup>
import { RouterView, RouterLink } from 'vue-router';
</script>

<template>
  <div>
    <nav>
      <RouterLink to="/">Trang chủ</RouterLink> |
      <RouterLink to="/categories">Quản lý danh mục</RouterLink>
    </nav>
    <router-view />
  </div>
</template>

<style scoped>
nav {
  text-align: center;
  margin-bottom: 20px;
}

nav a {
  margin: 0 10px;
  text-decoration: none;
  color: blue;
}

nav a:hover {
  text-decoration: underline;
}
</style>
