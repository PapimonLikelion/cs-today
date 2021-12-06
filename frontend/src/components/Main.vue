<template>
  <div class="main">
    <h2 class="title"><a href="/">💻 오늘의 CS 💻</a></h2>
    <p class="text">데이터 구조, 알고리즘, 운영체제, 네트워크 내용을</p>
    <p class="text">부담없는 하루 분량으로 뉴스레터를 보내드려요</p>
    <div class="btn-options">
      <button type="button" class="btn btn-primary register-btn" @click="$router.push('/register')">오늘의 CS 구독하기 🎉</button>
      <button type="button" class="btn btn-primary register-btn" @click="donation()">커피 한 잔 후원하기 ☕</button>
    </div>
  </div>
</template>

<script>
export default {
  name: "main",
  methods: {
    donation() {
      window.open("https://toss.me/joelonsw/2000", "_blank");
    }
  }
}
</script>

<style>
.main {
  width: 520px;
  height: 100vh;
  background-color: white;
}

.title {
  margin-top: 200px;
  margin-bottom: 80px;
  font-size: 35px;
}

.text {
  font-size: 18px;
}

.btn-options {
  margin-top: 50px;
  display: flex;
  align-items: center;
  flex-direction: column;
}

.btn-options button {
  margin-top: 15px;
  font-size: 18px;
  width: 200px;
  text-align: center;
}

@media(max-width: 480px) {
  .title {
    margin-top: 100px;
    font-size: 25px;
  }

  .text {
    font-size: 15px;
  }
  
  .btn-options button {
    font-size: 15px;
  }
}
</style>