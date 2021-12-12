<template>
  <div class="main">
    <h2 class="title"><a href="/">💻 오늘의 CS 💻</a></h2>

    <div class="form" v-if="register == 'before'">
      <div class="email">
        <p class="text">1. 뉴스레터를 받아볼 이메일을 입력해주세요</p>
        <input type="email" v-model="email" class="form-control" placeholder="이메일을 입력해주세요">
      </div>

      <div class="days">
        <p class="text">2. 뉴스레터를 얼마마다 한 번씩 보내드릴까요?</p>
        <select v-model="days" class="form-select" aria-label="Default select example">
          <option value="1">1일마다 받기</option>
          <option value="2">2일마다 받기</option>
          <option value="3">3일마다 받기</option>
          <option value="4">4일마다 받기</option>
          <option value="5">5일마다 받기</option>
          <option value="6">6일마다 받기</option>
          <option selected value="7">7일마다 받기</option>
        </select>
      </div>

      <button type="button" class="btn btn-primary register-btn" @click="send()">오늘의 CS 구독하기 🎉</button>
    </div>

    <div v-if="register == 'loading'" class="loading-module">
      <PulseLoader :color="'#0d6efd'"></PulseLoader>
    </div>

    <div class="registerd" v-if="register == 'after'">
      <h4 class="mb-4">🎉 감사합니다 🎉</h4>
      <p class="text">{{this.email}}로 {{this.days}}일마다 보내드릴게요!</p>
      <button type="button" class="btn-return btn btn-primary" @click="$router.push('/')">메인페이지로 돌아가기</button>
    </div>

  </div>
</template>

<script>
import axios from 'axios'
import PulseLoader from 'vue-spinner/src/PulseLoader.vue'

export default {
  name: "register",
  components : {
    PulseLoader
  },
  data() {
    return {
      email: "",
      days: "7",
      register: "before",
    }
  },
  methods: {
    send() {
      if (!this.validEmail()) {
        alert("올바르지 않은 이메일 주소입니다");
        return;
      }
      this.register = "loading",
      axios.post('https://cs-today.kro.kr/register', {
        email : this.email,
        mailInterval : this.days
      }).then((result) => {
        console.log(result);
        this.register = "after";
      }).catch((error) => {
        alert(error);
        this.register = "before";
      });
    },
    validEmail(){
      const regex = /^((?!\.)[\w\-_.]*[^.])(@\w+)(\.\w+(\.\w+)?[^.\W])$/;
      return regex.test(this.email);
    }
  }
}
</script>

<style>
.form {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.email {
  width: 350px;
  margin-bottom: 50px;
}

.days {
  width: 350px;
  margin-bottom: 70px;
}

.register-btn {
  font-size: 18px;
}

.loading-module {
  margin-top: 150px;
}

@media(max-width: 480px) {
  .email {
    width: 300px;
  }

  .days {
    width: 300px;
  }

  .register-btn {
    font-size: 15px;
  }
  
  .loading-module {
    margin-top: 170px;
  }
}
</style>