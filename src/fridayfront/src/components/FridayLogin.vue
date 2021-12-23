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

                <a
                    class="nav-link active"
                    id="tab-login"
                    href="#"
                    role="tab"
                    aria-selected="true"
                >
                    Login
                </a>


        </li>
        <li class="nav-item" role="presentation">


                <a
                    class="nav-link text-primary"
                    id="tab-register"
                    href="/register"
                    role="tab"
                    aria-selected="false"
                >
                    Register
                </a>

        </li>
    </ul>
        <div id='login'>
            <form
                action=""
                @submit="findUser"
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
               passwordFieldType : "password",
            }
        },
    
        methods:{

            showPassword(){
                this.passwordFieldType = this.passwordFieldType === "password" ? "text" : "password"
            },

            findUser(){
                let url = "http://localhost:8080/user/find/" + this.formData.username
                fetch(url)
                .then(res => res.json())
                .then(data => { console.log(data) });
                window.open(url, '_black');
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
