<template>
  <nav class="site-navbar" :class="'site-navbar--' + navbarLayoutType">
    <div class="site-navbar__header">
      <h1 class="site-navbar__brand" @click="$router.push({ name: 'home' })">
        <a class="site-navbar__brand-lg" href="javascript:;">高校教师链式业绩管理</a>
        <a class="site-navbar__brand-mini" href="javascript:;">教师</a>
      </h1>
    </div>
    <div class="site-navbar__body clearfix">
      <el-menu
        class="site-navbar__menu"
        mode="horizontal">
        <el-menu-item class="site-navbar__switch" index="0" @click="sidebarFold = !sidebarFold">
          <icon-svg name="zhedie"></icon-svg>
        </el-menu-item>
      </el-menu>
      <el-menu
        class="site-navbar__menu site-navbar__menu--right"
        mode="horizontal">
        <el-menu-item index="1" @click="$router.push({ name: 'theme' })">
          <template slot="title">
            <el-badge value="new">
              <icon-svg name="shezhi" class="el-icon-setting"></icon-svg>
            </el-badge>
          </template>
        </el-menu-item>
        <el-menu-item class="site-navbar__avatar" index="3">
          <el-dropdown :show-timeout="0" placement="bottom">
            <span class="el-dropdown-link">
              <img src="~@/assets/img/avatar.png" :alt="userName">{{ userName }}
            </span>
            <el-dropdown-menu slot="dropdown">
              <template v-if="isTeacher()">
                <el-dropdown-item @click.native="teacherDetailsHandle()">个人业绩</el-dropdown-item>
                <el-dropdown-item @click.native="teacherUpdateHandle()">个人信息</el-dropdown-item>
              </template>
              <el-dropdown-item @click.native="updatePasswordHandle()">修改密码</el-dropdown-item>
              <el-dropdown-item @click.native="logoutHandle()">退出</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </el-menu-item>
      </el-menu>
    </div>
    <!-- 弹窗, 修改密码 -->
    <update-password v-if="updatePassowrdVisible" ref="updatePassowrd"></update-password>
    <teacher-details v-if="teacherDetailsVisible" ref="teacherDetails"></teacher-details>
    <teacher-update-info v-if="teacherUpdateInfoVisible" ref="teacherUpdateInfo"></teacher-update-info>
  </nav>
</template>

<script>
  import UpdatePassword from './main-navbar-update-password'
  import TeacherDetails from './teacher-details'
  import TeacherUpdateInfo from './teacher-update-info'
  import { clearLoginInfo } from '@/utils'
  export default {
    data () {
      return {
        updatePassowrdVisible: false,
        teacherDetailsVisible: false,
        teacherUpdateInfoVisible: false,
        userRole: this.$store.state.user.roleIdList
      }
    },
    components: {
      UpdatePassword,
      TeacherDetails,
      TeacherUpdateInfo
    },
    computed: {
      navbarLayoutType: {
        get () { return this.$store.state.common.navbarLayoutType }
      },
      sidebarFold: {
        get () { return this.$store.state.common.sidebarFold },
        set (val) { this.$store.commit('common/updateSidebarFold', val) }
      },
      mainTabs: {
        get () { return this.$store.state.common.mainTabs },
        set (val) { this.$store.commit('common/updateMainTabs', val) }
      },
      userName: {
        get () { return this.$store.state.user.name }
      }
    },
    methods: {
      // 修改密码
      updatePasswordHandle () {
        this.updatePassowrdVisible = true
        this.$nextTick(() => {
          this.$refs.updatePassowrd.init()
        })
      },
      // 退出
      logoutHandle () {
        this.$confirm(`确定进行[退出]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/sys/logout'),
            method: 'post',
            data: this.$http.adornData()
          }).then(({data}) => {
            if (data && data.code === 0) {
              clearLoginInfo()
              this.$router.push({ name: 'login' })
            }
          })
        }).catch(() => {})
      },
      isTeacher () {
        for (var item in this.userRole) {
          if (this.userRole[item] === 3) {
            return true
          }
        }
        return false
      },
      teacherUpdateHandle () {
        this.teacherUpdateInfoVisible = true
        this.$nextTick(() => {
          this.$refs.teacherUpdateInfo.init(this.$store.state.user.id)
        })
      },
      teacherDetailsHandle () {
        this.teacherDetailsVisible = true
        this.$nextTick(() => {
          this.$refs.teacherDetails.init(this.$store.state.user.id)
        })
      }
    }
  }
</script>
