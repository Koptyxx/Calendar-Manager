<template>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login</title>

    </head>

  <body class="container">


    <ul class="nav nav-pills nav-justified mb-3">
        <li class="nav-item" role="presentation">
            <router-link to="/login" aria-colcount="blue">
                <a
                    class="nav-link active"
                    id="tab-login"
                    href="#"
                    role="tab"
                    aria-selected="true"
                >
                    Login
                </a>


            </router-link>

        </li>
        <li class="nav-item" role="presentation">
            <router-link to="/register">

                <a
                    class="nav-link"
                    id="tab-register"
                    role="tab"
                    aria-selected="false"
                >
                    Register
                </a>
            </router-link>
        </li>
    </ul>
        <div id='login'>
            <form
                action="#"
                @submit="addUser"
            >

                <div class="form-outline mb-4 text-center">
                    <label class="form-label" for="username">Username</label>
                    <input type="text" id="username" class="form-control" placeholder="Username" v-model="formData.username"/>
                </div>
                <!-- Password input -->
                <div class="form-outline mb-4 text-center">
                    <label class="form-label">Password</label>
                    <input :type="passwordFieldType" class="form-control" placeholder="Password" v-model="formData.password"/>
                    <input type="checkbox" @click="showPassword">
                    <label>Show Password</label>
                </div>

                <div>
                    <button type="submit"
                            class="btn btn-primary btn-block mb-4"
                    >
                                Sign in
                    </button>
                </div>

                <div id="msg">
                    <pre>

                    </pre>
                </div>
            </form>
        </div>
    </body>
</template>

<script>

  export default {
        data(){
            return{
                formData:{
                    username: null,
                    password: null,
                    
            },
            users: [],
            passwordFieldType : "password"
          
            }
        },
    
        methods:{

            showPassword(){
                this.passwordFieldType = this.passwordFieldType === "password" ? "text" : "password"
            },

            getUsername(){
                console.log("get called", this.formData)
            },

            addUser(e){
                e.preventDefault();
                let user = {
                    username: this.formData.username,
                    password: this.formData.password
                }
                this.users.push(user)
                document.forms[0].reset()

                console.log("added")
                let pre = document.querySelector('#msg pre');
                pre.textContent = '\n' + JSON.stringify(this.users, '\t', 2);

                localStorage.setItem('UserList', JSON.stringify(this.users) );

            }
        }


    }
</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    h3 {
        margin: 40px 0 0;
    }
    ul {
        list-style-type: none;
        padding: 0;
    }
    li {
        display: inline-block;
        margin: 0 10px;
    }
        a {
        color: #42b983;
    }
</style>
