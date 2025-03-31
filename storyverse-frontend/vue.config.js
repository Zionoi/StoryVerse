const { defineConfig } = require('@vue/cli-service')

module.exports = defineConfig({
  transpileDependencies: true,

 
  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:8080', // 스프링부트 백엔드 주소
        changeOrigin: true
      }
    }
  }
})
