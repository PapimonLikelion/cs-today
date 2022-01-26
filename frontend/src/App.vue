<template>
  <router-view></router-view>
</template>

<script>

export default {
  name: 'App',
  components: {
  }
}
</script>

<style>
@font-face {
  font-family: BMHANNAAir;
  font-display: auto;
  src: url('assets/fonts/BMHANNAAir_ttf.ttf') format('truetype');
  font-weight: 500;
}

@font-face {
  font-family: BMHANNAPro;
  font-display: auto;
  src: url('assets/fonts/BMHANNAPro.ttf') format('truetype');
  font-weight: 500;
}

#app {
  font-family: BMHANNAPro;
  text-align: center;
  display: flex;
  justify-content: center;
  background-color: #E2EEFF;
}

a {
  text-decoration: none !important;
  color: black !important;
}
</style>
