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
            项目结题基本信息
          </th>
        </tr>
        <tr align='center'>
          <td colspan="8" style="height: 1.2rem"></td>
        </tr>
        <tr align='center'>
          <th colspan="2">结题时间</th>
          <td colspan="2"><span v-if="dataForm.finishTime" v-text="dataForm.finishTime.substring(0,10)"></span></td>
          <th colspan="2">备注</th>
          <td colspan="2"><span v-text="dataForm.remarks"></span></td>
        </tr>

        <!--项目立项信息开始-->
        <tr align='center'>
          <td colspan="8" style="height: 1.2rem"></td>
        </tr>
        <tr class="contents" align="center">
          <th colspan="8">项目立项信息</th>
        </tr>
        <tr align='center'>
          <td colspan="8" style="height: 1.2rem"></td>
        </tr>
        <tr style="height: 2.5rem" align='center'>
          <th colspan="1">立项项目名称</th>
          <td colspan="7"><el-tag style="font-size: 1.1rem; font-weight: bolder" v-text="projectCreateEntity.name" size="small"></el-tag></td>
        </tr>
        <tr align='center'>
          <th>项目编号</th>
          <td><span v-text="projectCreateEntity.number"></span></td>
          <th>项目经费</th>
          <td><span v-text="projectCreateEntity.money+'/万元'"></span></td>
          <th>项目类型</th>
          <td v-for="item in typeList" v-if="item.value === projectCreateEntity.type">
            <span v-text="item.label"></span>
          </td>
          <th>项目类别</th>
          <td v-for="item in projectCategoryList" v-if="item.value === projectCreateEntity.category">
            <span v-text="item.label"></span>
          </td>
        </tr>
        <tr align='center'>
          <th>项目级别</th>
          <td v-for="item in projectLevelList" v-if="item.value === projectCreateEntity.level">
            <span v-text="item.label"></span>
          </td>
          <th>立项时间</th>
          <td><span v-if="projectCreateEntity.createTime" v-text="projectCreateEntity.createTime.substring(0,10)"></span></td>
          <th>开始时间</th>
          <td><span v-if="projectCreateEntity.startTime" v-text="projectCreateEntity.startTime.substring(0,10)"></span></td>
          <th>结束时间</th>
          <td><span v-if="projectCreateEntity.endTime" v-text="projectCreateEntity.startTime.substring(0,10)"></span></td>
        </tr>
        <!--项目立项信息开始-->

        <!--结题教师信息开始-->
        <template v-if="teacherInfoEntity">
          <tr align='center'>
            <td colspan="8" style="height: 1.2rem"></td>
          </tr>
          <tr class="contents" align="center">
            <th colspan="8">结题教师信息</th>
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
            <td v-text="teacherInfoEntity.teacherCode"></td>
            <td v-text="teacherInfoEntity.name"></td>
            <td v-text="teacherInfoEntity.sex"></td>
            <td v-if="teacherInfoEntity.birthDay" v-text="teacherInfoEntity.birthDay.substring(0,10)"></td>
            <td v-if="teacherInfoEntity.entryTime" v-text="teacherInfoEntity.entryTime.substring(0,10)"></td>
            <td colspan="3" v-text="teacherInfoEntity.administerPost"></td>
          </tr>
          <tr align='center'>
            <th colspan="2">最高学历</th>
            <th colspan="2">第一学历</th>
            <th colspan="4">专业技术职称</th>
          </tr>
          <tr align="center">
            <td colspan="2" v-text="teacherInfoEntity.highEdu"></td>
            <td colspan="2" v-text="teacherInfoEntity.firstEdu"></td>
            <td colspan="4" v-text="teacherInfoEntity.technicalPost"></td>
          </tr>
          <tr align="center">
            <th colspan="2">简历</th>
            <td colspan="6" v-text="teacherInfoEntity.resume"></td>
          </tr>
          <tr align="center">
            <th colspan="2">主要从事工作及研究方向</th>
            <td colspan="6" v-text="teacherInfoEntity.other"></td>
          </tr>
        </template>
        <!--结题教师信息结束-->

        <!--项目立项负责人开始-->
        <template v-if="projectCreateEntity.personInfo">
          <tr align='center'>
            <td colspan="8" style="height: 1.2rem"></td>
          </tr>
          <tr class="contents" align="center">
            <th colspan="8">项目立项负责人</th>
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
            <td v-text="projectCreateEntity.personInfo.teacherCode"></td>
            <td v-text="projectCreateEntity.personInfo.name"></td>
            <td v-text="projectCreateEntity.personInfo.sex"></td>
            <td v-if="projectCreateEntity.personInfo.birthDay" v-text="projectCreateEntity.personInfo.birthDay.substring(0,10)"></td>
            <td v-if="projectCreateEntity.personInfo.birthDay" v-text="projectCreateEntity.personInfo.entryTime.substring(0,10)"></td>
            <td colspan="3" v-text="projectCreateEntity.personInfo.administerPost"></td>
          </tr>
          <tr align='center'>
            <th colspan="2">最高学历</th>
            <th colspan="2">第一学历</th>
            <th colspan="4">专业技术职称</th>
          </tr>
          <tr align="center">
            <td colspan="2" v-text="projectCreateEntity.personInfo.highEdu"></td>
            <td colspan="2" v-text="projectCreateEntity.personInfo.firstEdu"></td>
            <td colspan="4" v-text="projectCreateEntity.personInfo.technicalPost"></td>
          </tr>
          <tr align="center">
            <th colspan="2">简历</th>
            <td colspan="6" v-text="projectCreateEntity.personInfo.resume"></td>
          </tr>
          <tr align="center">
            <th colspan="2">主要从事工作及研究方向</th>
            <td colspan="6" v-text="projectCreateEntity.personInfo.other"></td>
          </tr>
        </template>
        <!--项目立项负责人结束-->

        <!--项目成员开始-->
        <tr align='center'>
          <td colspan="8" style="height: 1.2rem"></td>
        </tr>
        <tr class="contents"><th colspan="8">项目立项成员信息</th></tr>
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
        <template v-for="memberInfo in projectCreateEntity.memberInfoList">
          <tr align="center">
            <td v-if="memberInfo" v-text="memberInfo.teacherCode"></td>
            <td v-text="memberInfo.name"></td>
            <td v-text="memberInfo.sex"></td>
            <td v-if="memberInfo.entryTime" v-text="memberInfo.entryTime.substring(0,10)"></td>
            <td v-text="memberInfo.administerPost"></td>
            <td v-text="memberInfo.highEdu"></td>
            <td v-text="memberInfo.firstEdu"></td>
            <td v-text="memberInfo.technicalPost"></td>
          </tr>
        </template>
        <!--项目成员结束-->

        <!--附件开始-->
        <tr align='center'>
          <td colspan="8" style="height: 1.2rem"></td>
        </tr>
        <tr align="center" class="contents">
          <th colspan="8">项目全部附件</th>
        </tr>
        <tr align='center'>
          <td colspan="8" style="height: 1.2rem"></td>
        </tr>
        <tr align='center'>
          <th colspan="4">附件名</th>
          <th colspan="2">附件类型</th>
          <th colspan="2">操作</th>
        </tr>
        <template>
          <tr v-for="item in fileList"
              align="center">
            <td colspan="4" v-text="item.attachName"></td>
            <td colspan="2"><el-tag v-text="'项目结题附件'" type="success" size="small"></el-tag></td>
            <td colspan="2"><el-button @click="attachDown(item)">下载</el-button></td>
          </tr>
          <tr v-for="item in projectCreateEntity.sysAttachEntityList"
              align="center">
            <td colspan="4" v-text="item.attachName"></td>
            <td colspan="2"><el-tag v-text="'项目立项附件'" type="danger" size="small"></el-tag></td>
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
        // 项目立项信息
        projectCreateEntity: {},
        // 结题教师信息
        teacherInfoEntity: {},
        typeList: [
          {value: 1, label: '科研类'},
          {value: 2, label: '教改类'}
        ],
        projectCategoryList: this.$store.state.user.projectCategoryEntities,
        projectLevelList: this.$store.state.user.projectLevelEntities,
        dataForm: {
          pfId: 0,
          pcId: '',
          finishTime: '',
          remarks: ''
        }
      }
    },
    methods: {
      init (id) {
        this.visible = true
        this.dataFormLoading = true
        this.dataForm.pfId = id || 0
        this.fileList = []
        this.projectCreateEntity = {}
        this.teacherInfoEntity = {}
        if (this.dataForm.pfId) {
          this.$http({
            url: this.$http.adornUrl(`/business/projectfinish/details/${this.dataForm.pfId}`),
            method: 'get',
            params: this.$http.adornParams()
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.dataForm.pcId = data.projectFinish.pcId
              this.dataForm.finishTime = data.projectFinish.finishTime
              this.dataForm.remarks = data.projectFinish.remarks
              // 结题附件信息
              this.fileList = data.projectFinish.sysAttachEntityList
              // 项目立项信息
              this.projectCreateEntity = data.projectFinish.projectCreateEntity
              // 结题教师信息
              this.teacherInfoEntity = data.projectFinish.teacherInfoEntity
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
