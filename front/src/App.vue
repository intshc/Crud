<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <header>
    <div>
      <h2>게시판 만들기</h2>
    </div>
    <nav>
      <RouterLink to="/">홈으로 가기</RouterLink>
      &nbsp;
      <RouterLink to="/write">글 작성하기</RouterLink>
    </nav>
  </header>

  <RouterView />
</template>

<style scoped>

</style>
