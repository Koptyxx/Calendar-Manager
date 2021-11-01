const login = Vue.createApp({
    data(){
        return {
            username: "",
            password: "",
            passwordFieldType : "password"
        }
    },
    methods:{

        showPassword(){
            this.passwordFieldType = this.passwordFieldType === "password" ? "text" : "password";
        }
    }
}).mount("#Login");