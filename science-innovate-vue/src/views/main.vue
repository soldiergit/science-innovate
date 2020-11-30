<template>
  <div
    class="site-wrapper"
    :class="{ 'site-sidebar--fold': sidebarFold }"
    v-loading.fullscreen.lock="loading"
    element-loading-text="拼命加载中">
    <template v-if="!loading">
      <main-navbar />
      <main-sidebar />
      <div class="site-content__wrapper" :style="{ 'min-height': documentClientHeight + 'px' }">
        <main-content v-if="!$store.state.common.contentIsNeedRefresh" v-bind:class="{site:this.$route.name === 'home'}"/>
      </div>
    </template>
  </div>
</template>

<script>
  import MainNavbar from './main-navbar'
  import MainSidebar from './main-sidebar'
  import MainContent from './main-content'
  export default {
    provide () {
      return {
        // 刷新
        refresh () {
          this.$store.commit('common/updateContentIsNeedRefresh', true)
          this.$nextTick(() => {
            this.$store.commit('common/updateContentIsNeedRefresh', false)
          })
        }
      }
    },
    data () {
      return {
        loading: true
      }
    },
    components: {
      MainNavbar,
      MainSidebar,
      MainContent
    },
    computed: {
      documentClientHeight: {
        get () { return this.$store.state.common.documentClientHeight },
        set (val) { this.$store.commit('common/updateDocumentClientHeight', val) }
      },
      sidebarFold: {
        get () { return this.$store.state.common.sidebarFold }
      },
      userId: {
        get () { return this.$store.state.user.id },
        set (val) { this.$store.commit('user/updateId', val) }
      },
      userName: {
        get () { return this.$store.state.user.name },
        set (val) { this.$store.commit('user/updateName', val) }
      }
    },
    created () {
      this.getUserInfo()
      this.getAllTeacherSelect()
      this.getBasicsData()
    },
    mounted () {
      this.resetDocumentClientHeight()
    },
    methods: {
      // 重置窗口可视高度
      resetDocumentClientHeight () {
        this.documentClientHeight = document.documentElement['clientHeight']
        window.onresize = () => {
          this.documentClientHeight = document.documentElement['clientHeight']
        }
      },
      // 获取当前管理员信息
      getUserInfo () {
        this.$http({
          url: this.$http.adornUrl('/sys/user/info'),
          method: 'get',
          params: this.$http.adornParams()
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.userId = data.user.userId
            this.userName = data.user.username
            // 2020-08-22 设置用户角色
            this.$store.state.user.roleIdList = data.user.roleIdList
          }
        })
      },
      // 获取全体教师：教师业绩支撑选择负责人或者成员
      getAllTeacherSelect () {
        this.$http({
          url: this.$http.adornUrl('/business/teacherinfo/select'),
          method: 'get',
          params: this.$http.adornParams()
        }).then(({data}) => {
          if (data && data.code === 0) {
            // 2020-09-14 设置全部教师
            this.$store.state.user.allTeacherSelectList = data.teacherList
          }
        })
      },
      // 获取基础数据信息
      getBasicsData () {
        this.$http({
          url: this.$http.adornUrl('/business/basics/datas'),
          method: 'get',
          params: this.$http.adornParams()
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.loading = false
            // 2020-09-15 基础数据
            // 获奖级别集合
            this.$store.state.user.competitionGradeEntities = data.dataVo.competitionGradeEntities
            // 获奖等次集合
            this.$store.state.user.competitionRankEntities = data.dataVo.competitionRankEntities
            // 成果类型
            this.$store.state.user.gainTypeEntities = data.dataVo.gainTypeEntities
            // 论文等级集合
            this.$store.state.user.papersGradeEntities = data.dataVo.papersGradeEntities
            // 项目类别集合
            this.$store.state.user.projectCategoryEntities = data.dataVo.projectCategoryEntities
            // 项目级别集合
            this.$store.state.user.projectLevelEntities = data.dataVo.projectLevelEntities
          }
        })
      }
    }
  }
</script>

<style>
  .site {
    padding: 0px;
  }
  .site .el-card .el-card__body {
    padding: 0px;
  }
  /*设置全局 :show-overflow-tooltip="true" 时的样式*/
  .el-tooltip__popper{max-width:20%;}
  .el-tooltip__popper,.el-tooltip__popper.is-dark{background:#f5f5f5 !important;
    color: #303133 !important;}
</style>
