<template>
  <img alt="Vue logo" src="./assets/storyverseLogo.svg">
  <div id="app">
    <router-view name="header" />
    <router-view /> <!-- default view -->
    <router-view name="footer" />
  </div>
</template>

<script>

export default {
  name: 'App',
  
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
