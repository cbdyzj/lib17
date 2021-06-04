<!--suppress HtmlUnknownTarget -->
<template>
  <div id="app">
    <ul class="nav">
      <li><a href="/index.html">App</a></li>
      <li><a href="/counter.html">Counter</a></li>
      <li><a href="/sample.html">Sample List</a></li>
      <li><a class="github-link" href="https://github.com/cbdyzj/design-vue">GitHub</a></li>
    </ul>
    <hr>
    <GoodComponent />

  </div>
</template>

<script>
import GoodComponent from '@/components/GoodComponent.jsx'

export default {
  components: {
    GoodComponent,
  },
}
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>

<style scoped lang="less">
.nav {

  font-size: 0;

  * {
    font-size: initial;
  }

  :not(:first-child) {

    border-left: 1px solid #2c3e50;
  }

  li {

    padding: 0 8px;
    display: inline-block;

    a {

      color: #2c3e50;
      text-decoration: none;
    }
  }
}

.github-link {

  position: relative;

  &::after {
    position: absolute;
    display: block;
    content: '';
    background-image: url(@/images/github.svg);
    background-size: 14px 14px;
    height: 14px;
    width: 14px;
    right: -16px;
    top: 4px;
  }


}
</style>
