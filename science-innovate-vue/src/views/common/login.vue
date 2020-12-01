<template>
  <div class="bg-login">
    <div class="middle-box loginscreen bg-white container">
      <div class="login-title">
        <h2>科创管理信息系统</h2>
      </div>
      <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()">
        <el-form-item prop="username">
          <el-input v-model="dataForm.username" placeholder="请输入账号"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input :type="passwordType" v-model="dataForm.password" placeholder="请输入密码"></el-input>
          <span class="show-pwd" :style="eyeStatus" @click="showPassword()"><icon-svg :name="eyeName"></icon-svg></span>
        </el-form-item>
        <el-form-item prop="captcha">
          <el-row :gutter="20">
            <el-col :span="14">
              <el-input v-model="dataForm.captcha" placeholder="验证码">
              </el-input>
            </el-col>
            <el-col :span="10" class="login-captcha">
              <img :src="captchaPath" @click="getCaptcha()" alt="">
            </el-col>
          </el-row>
        </el-form-item>
        <el-form-item>
          <el-button class="login-btn-submit" type="primary" @click="dataFormSubmit()" :loading="loginLoading">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
  import {getUUID} from '@/utils'

  export default {
    data () {
      return {
        loginLoading: false,
        dataForm: {
          username: '',
          password: '',
          uuid: '',
          captcha: ''
        },
        dataRule: {
          username: [
            {required: true, message: '帐号不能为空', trigger: 'blur'}
          ],
          password: [
            {required: true, message: '密码不能为空', trigger: 'blur'}
          ],
          captcha: [
            {required: true, message: '验证码不能为空', trigger: 'blur'}
          ]
        },
        passwordType: 'password',
        eyeStatus: '',
        eyeName: 'eyeclose',
        captchaPath: ''
      }
    },
    created () {
      this.autoLogin()
      this.getCaptcha()
    },
    methods: {
      // 提交表单
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.loginLoading = true
            this.$http({
              url: this.$http.adornUrl('/sys/login'),
              method: 'post',
              data: this.$http.adornData({
                'username': this.dataForm.username,
                'password': this.dataForm.password,
                'uuid': this.dataForm.uuid,
                'captcha': this.dataForm.captcha
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.loginLoading = false
                this.$cookie.set('token', data.token)
                this.$router.replace({name: 'home'})
              } else {
                this.loginLoading = false
                this.getCaptcha()
                this.$message.error(data.msg)
              }
            })
          }
        })
      },
      // 查看密码
      showPassword () {
        if (this.passwordType === 'password') {
          this.passwordType = ''
          this.eyeStatus = 'color:#1296db'
          this.eyeName = 'eyeopen'
        } else {
          this.passwordType = 'password'
          this.eyeStatus = ''
          this.eyeName = 'eyeclose'
        }
      },
      // 获取验证码
      getCaptcha () {
        this.dataForm.uuid = getUUID()
        this.captchaPath = this.$http.adornUrl(`/captcha.jpg?uuid=${this.dataForm.uuid}`)
      },
      // 尝试自动登录
      autoLogin () {
        this.loginLoading = true
        this.$http({
          url: this.$http.adornUrl('/innovate/getUserToken'),
          method: 'get',
          params: this.$http.adornParams()
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.loginLoading = false
            this.$cookie.set('token', data.token)
            this.$router.replace({name: 'home'})
          } else {
            this.loginLoading = false
          }
        })
      }
    }
  }
</script>

<style lang="scss">
  $dark_gray: #889aa4;
  body {
    background: url("~@/assets/img/login_bg.jpg") no-repeat center fixed;
    background-size: cover;
  }

  .container {
    padding-right: 15px;
    padding-left: 15px;
    margin-right: auto;
    margin-left: auto;
  }

  .bg-white {
    background-color: #fff;
    /*opacity: 0.8;*/
  }

  .middle-box {
    max-width: 400px;
    z-index: 100;
    margin: 14% auto;
    padding: 0 30px 10px 30px;
    border-radius: 6px;
    box-shadow: 0 0 30px #000;
  }

  .loginscreen .middle-box {
    width: 300px;
  }

  .login-title {
    height: 80px;
    padding: 20px;
    font-weight: bold;
    text-align: center;
  }

  .middle-box {
    .el-input {
      display: inline-block;
    }

    .el-input__inner {
      text-align: center;
      border-color: darkgray;
      color: black;
    }
  }

  .show-pwd {
    position: absolute;
    right: 10px;
    top: 2px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }

  .login-captcha {
    overflow: hidden;

    > img {
      width: 100%;
      cursor: pointer;
    }
  }

  .login-btn-submit {
    width: 100%;
  }
</style>
