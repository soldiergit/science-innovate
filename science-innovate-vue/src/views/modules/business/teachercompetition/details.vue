<template>
  <el-dialog
    :title="'详情'"
    width="70%"
    v-loading="dataFormLoading"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-row>
      <table border="1" cellspacing="0" width="100%" class="table">
        <tr align='center'>
          <td colspan="8" style="height: 1.2rem"></td>
        </tr>
        <tr class="contents" align="center">
          <th colspan="8">
            教师竞赛基本信息
          </th>
        </tr>
        <tr align='center'>
          <td colspan="8" style="height: 1.2rem"></td>
        </tr>
        <tr align='center'>
          <th>竞赛名称</th>
          <td><el-tag style="font-size: 1.1rem; font-weight: bolder" v-text="dataForm.name" size="small"></el-tag></td>
          <th>获奖级别</th>
          <td v-for="item in competitionGradeList" v-if="item.value === dataForm.grade">
            <span v-text="item.label"></span>
          </td>
          <th>获奖等次</th>
          <td v-for="item in competitionRankList" v-if="item.value === dataForm.rank">
            <span v-text="item.label"></span>
          </td>
          <th>获奖时间</th>
          <td><span v-if="dataForm.prizeTime" v-text="dataForm.prizeTime.substring(0,4) + '年'"></span></td>
        </tr>

        <!--赛事信息开始-->
        <tr align='center'>
          <td colspan="8" style="height: 1.2rem"></td>
        </tr>
        <tr class="contents"><th colspan="8">赛事信息</th></tr>
        <tr align='center'>
          <td colspan="8" style="height: 1.2rem"></td>
        </tr>
        <tr align='center'>
          <th>主办单位</th>
          <th>承办单位</th>
          <th colspan="4">赛事名称</th>
          <th>赛事等级</th>
          <th>赛事类型</th>
        </tr>
        <tr align="center">
          <td><span v-text="matchInfo.organizer"></span></td>
          <td><span v-text="matchInfo.contractor"></span></td>
          <td colspan="4"><span v-text="matchInfo.name"></span></td>
          <td v-for="item in competitionGradeList" v-if="item.value === matchInfo.grade">
            <span v-text="item.label"></span>
          </td>
          <td><span v-text="'教师竞赛'"></span></td>
        </tr>
        <!--赛事信息结束-->

        <!--参赛队长信息开始-->
        <tr align='center'>
          <td colspan="8" style="height: 1.2rem"></td>
        </tr>
        <tr class="contents" align="center">
          <th colspan="8">参赛队长信息</th>
        </tr>
        <tr align='center'>
          <td colspan="8" style="height: 1.2rem"></td>
        </tr>
        <tr align='center'>
          <th>工号</th>
          <th>姓名</th>
          <th>性别</th>
          <th>出生日期</th>
          <th>入职时间</th>
          <th colspan="3">行政职务</th>
        </tr>
        <tr align="center">
          <td v-text="personInfo.teacherCode"></td>
          <td v-text="personInfo.name"></td>
          <td v-text="personInfo.sex"></td>
          <td v-if="personInfo.birthDay" v-text="personInfo.birthDay.substring(0,10)"></td>
          <td v-if="personInfo.entryTime" v-text="personInfo.entryTime.substring(0,10)"></td>
          <td colspan="3" v-text="personInfo.administerPost"></td>
        </tr>
        <tr align='center'>
          <th colspan="2">最高学历</th>
          <th colspan="2">第一学历</th>
          <th colspan="4">专业技术职称</th>
        </tr>
        <tr align="center">
          <td colspan="2" v-text="personInfo.highEdu"></td>
          <td colspan="2" v-text="personInfo.firstEdu"></td>
          <td colspan="4" v-text="personInfo.technicalPost"></td>
        </tr>
        <tr align="center">
          <th colspan="2">简历</th>
          <td colspan="6" v-text="personInfo.resume"></td>
        </tr>
        <tr align="center">
          <th colspan="2">主要从事工作及研究方向</th>
          <td colspan="6" v-text="personInfo.other"></td>
        </tr>
        <!--参赛队长信息结束-->

        <!--参赛成员开始-->
        <tr align='center'>
          <td colspan="8" style="height: 1.2rem"></td>
        </tr>
        <tr class="contents"><th colspan="8">参赛成员信息</th></tr>
        <tr align='center'>
          <td colspan="8" style="height: 1.2rem"></td>
        </tr>
        <tr align='center'>
          <th>工号</th>
          <th>姓名</th>
          <th>性别</th>
          <th>入职时间</th>
          <th>行政职务</th>
          <th>最高学历</th>
          <th>第一学历</th>
          <th>专业技术职称</th>
        </tr>
        <template v-for="memberInfo in memberInfoList">
          <tr align="center">
            <td v-text="memberInfo.teacherCode"></td>
            <td v-text="memberInfo.name"></td>
            <td v-text="memberInfo.sex"></td>
            <td v-if="memberInfo.entryTime" v-text="memberInfo.entryTime.substring(0,4) + '年'"></td>
            <td v-text="memberInfo.administerPost"></td>
            <td v-text="memberInfo.highEdu"></td>
            <td v-text="memberInfo.firstEdu"></td>
            <td v-text="memberInfo.technicalPost"></td>
          </tr>
        </template>
        <!--参赛成员开始-->

        <!--附件开始-->
        <tr align='center'>
          <td colspan="8" style="height: 1.2rem"></td>
        </tr>
        <tr align="center" class="contents">
          <th colspan="8">附件</th>
        </tr>
        <tr align='center'>
          <td colspan="8" style="height: 1.2rem"></td>
        </tr>
        <tr align='center'>
          <th colspan="6">附件名</th>
          <th colspan="2">操作</th>
        </tr>
        <template>
          <tr v-for="item in fileList"
              align="center">
            <td colspan="6" v-text="item.attachName"></td>
            <td colspan="2"><el-button @click="attachDown(item)">下载</el-button></td>
          </tr>
        </template>
        <tr align='center'>
          <td colspan="8" style="height: 1.2rem"></td>
        </tr>
        <!--附件结束-->

      </table>
    </el-row>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">关闭</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataFormLoading: false,
        // 附件对象集合
        fileList: [],
        // 赛事信息
        matchInfo: {},
        // 参赛队长
        personInfo: {},
        // 参赛成员集合
        memberInfoList: [],
        competitionGradeList: this.$store.state.user.competitionGradeEntities,
        competitionRankList: this.$store.state.user.competitionRankEntities,
        dataForm: {
          tcId: 0,
          name: '',
          grade: '',
          rank: '',
          prizeTime: ''
        }
      }
    },
    methods: {
      init (id) {
        this.visible = true
        this.dataFormLoading = true
        this.dataForm.tcId = id || 0
        this.fileList = []
        this.personInfo = {}
        this.memberInfoList = []
        if (this.dataForm.tcId) {
          this.$http({
            url: this.$http.adornUrl(`/business/teachercompetition/details/${this.dataForm.tcId}`),
            method: 'get',
            params: this.$http.adornParams()
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.dataForm.name = data.teacherCompetition.name
              this.dataForm.grade = data.teacherCompetition.grade
              this.dataForm.rank = data.teacherCompetition.rank
              this.dataForm.prizeTime = data.teacherCompetition.prizeTime
              // 附件信息
              this.fileList = data.teacherCompetition.sysAttachEntityList
              // 赛事信息
              this.matchInfo = data.teacherCompetition.matchInfoEntity
              // 教师信息
              this.personInfo = data.teacherCompetition.personInfo
              this.memberInfoList = data.teacherCompetition.memberInfoList
              this.dataFormLoading = false
            }
          })
        } else {
          this.dataFormLoading = false
        }
      },
      attachDown (attach) {
        // 出现loading
        const downloadFileLoading = this.$loading({
          lock: true,
          text: '文件下载中...',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        })
        this.$http({
          url: this.$http.adornUrl(`/business/sysattach/download`),
          method: 'post',
          responseType: 'blob',
          params: this.$http.adornParams({
            'filePath': attach.attachPath
          })
        }).then(response => {
          if (!response) {
            return
          }
          let url = window.URL.createObjectURL(new Blob([response.data]))
          let link = document.createElement('a')
          link.style.display = 'none'
          link.href = url
          link.setAttribute('download', attach.attachName)
          document.body.appendChild(link)
          link.click()
          // 关闭loading
          downloadFileLoading.close()
        }).catch(err => {
          console.log(err)
          // 关闭loading
          downloadFileLoading.close()
        })
      }
    }
  }
</script>
<style>
  .contents{
    height: 60px;
    font-size: 18px;
  }
  .table {
    border: 1px solid #ddd;
    /*为表格设置合并边框模型*/
    border-collapse: collapse;
    /*列宽由表格宽度和列宽度设定*/
    table-layout: fixed;
  }
  .table td {
    /*允许在单词内换行。*/
    word-break: break-word;
    /*设置宽度*/
    width: 100%;
  }
</style>
