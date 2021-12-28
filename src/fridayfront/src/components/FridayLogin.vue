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
                            v-on:click="findUser"
                            class="btn btn-primary btn-block mb-4">
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

            resetData: function () {
                 this.formData.username = '';
                 this.formData.password = '';
            },

            showPassword(){
                this.passwordFieldType = this.passwordFieldType === "password" ? "text" : "password"
            },

            findUser(){
                const obj = JSON.stringify(this.formData.username);
                localStorage.setItem('user', obj);
                let url = "http://localhost:8080/task/find/username/" + this.formData.username
                let toCal = '/login';
                fetch(url)
                .then(res => res.json())
                this.resetData();
                this.$router.push("/calendar")
                    /*.then(x => {
                        const status = x.status;
                        console.log(status);
                        if(status === 200){
                            toCal.replace(toCal, '/calendar');
                            //console.log("là");
                            //this.$router.push("/calendar")
                        }
                    });

                     */

                /*console.log(toCal);
                this.$router.push(toCal);

                 */
                //.then(res => res.json())
               // this.resetData();

                /*console.log(response);
                if(response === 200){
                    this.$router.push("/calendar")
                }

                 */
                //this.$router.push("/calendar")
                    /*.then(resp => {
                        if (resp.status === 200){
                            this.resetData();
                            this.$router.push("/calendar")
                        }
                        else{
                            this.$router.push("/login")
                        }
                    })/;

                     */

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
