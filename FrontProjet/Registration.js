const registration = Vue.createApp({
    data(){
        return {
            username: null,
            password: null,
            passwordFieldType: "password",
            confirmed_password: null,
            confirmed_passwordFieldType: "password",
            passwords_error: null
        }
    },
    methods:{
        showPassword(){
            this.passwordFieldType = this.passwordFieldType === "password" ? "text" : "password";
        },

        showConfirmedPassword(){
            this.confirmed_passwordFieldType = this.confirmed_passwordFieldType === "password" ? "text" : "password";
        },

        testPasswords(){
            this.passwords_error = this.confirmed_password !== this.password;
        }
    }
}).mount("#Registration");