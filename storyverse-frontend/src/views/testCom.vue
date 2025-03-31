<template>
    <div>
        <h1>연습 뷰컴포넌트</h1>
        <h3>{{ count }}번 클릭했습니다</h3>
        <button @click="increment">클릭</button>
        <ul>
            <li v-for="(fruit, index) in fruit" :key="index">{{ fruit }}</li>
            <li v-for="(user, index) in user" :key="index">
                이름: {{ user.name }} / 나이: {{ user.age }}
            </li>
        </ul>
        
    </div>
</template>
<script>
export default {
    name : 'testCom',
    data() {
        return {
            count:0,
            메시지: "안녕하세요",
            message: "Hello Vue!",
            fruit: ['사과', '바나나', 123],
            user: [
                {name: '홍길동',age: 25},
                {name: '김요한',age: 33}
            ],
            flagTest: true

        };
    },
    created() {
    // 최초 마운트 시 sessionStorage에서 count 불러오기
    const savedCount = sessionStorage.getItem("ClickCount");
    this.count = savedCount ? parseInt(savedCount) : 0;
  },
    methods: {
        increment() {
            this.count++;
            sessionStorage.setItem("ClickCount", this.count);
        }

    }
}
</script>
<style scoped>
button {
    padding: 10px;
    background-color: #42b983;
    color: white;
    border: none;
    border-radius: 4px;
}
</style>