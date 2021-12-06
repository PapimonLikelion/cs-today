<template>
  <div class="main">
    <h2 class="title"><a href="/">💻 오늘의 CS 💻</a></h2>
    <h5>존재하지 않는 페이지입니다</h5>
    <button type="button" class="btn-return btn btn-primary register-btn" @click="$router.push('/')">메인페이지로 돌아가기</button>
  </div>
</template>

<script>
export default {
  name: "notfound",
}
</script>

<style>
.btn-return {
  margin-top: 50px;
  font-size: 18px;
  width: 200px;
  text-align: center;
}

@media(max-width: 480px) {
  .btn-return {
    font-size: 15px;
  }
}
</style>