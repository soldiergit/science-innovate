<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
      <el-date-picker
      @change="getDataList"
      v-model="dataForm.checkTime"
      align="right"
      type="year"
      placeholder="请选择入职时间">
      </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-input v-model="dataForm.teacherName" placeholder="教师名" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
        <el-button v-if="isAuth('business:teacherinfo:delete')" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
        <el-tooltip content="请勾选要导出的教师信息，不勾选则导出所有！" placement="top">
          <el-button type="warning" icon="el-icon-download" @click="exportData()">导出数据</el-button>
        </el-tooltip>
      </el-form-item>
    </el-form>
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      @selection-change="selectionChangeHandle"
      style="width: 100%;">
      <el-table-column
        type="selection"
        header-align="center"
        align="center"
        width="50">
      </el-table-column>
      <el-table-column
        prop="teacherCode"
        header-align="center"
        align="center"
        label="工号">
      </el-table-column>
      <el-table-column
        prop="name"
        header-align="center"
        align="center"
        label="姓名">
      </el-table-column>
      <el-table-column
        prop="sex"
        header-align="center"
        align="center"
        label="性别">
      </el-table-column>
      <el-table-column
        prop="birthDay"
        header-align="center"
        align="center"
        label="出生日期">
        <template slot-scope="scope">
          <el-tag v-text="scope.row.birthDay.substring(0,10)" size="small"></el-tag>
        </template>
      </el-table-column>
      <el-table-column
        prop="entryTime"
        header-align="center"
        align="center"
        label="入职时间">
        <template slot-scope="scope">
          <el-tag v-text="scope.row.entryTime.substring(0,10)" size="small"></el-tag>
        </template>
      </el-table-column>
      <el-table-column
        prop="highEdu"
        header-align="center"
        align="center"
        label="最高学历">
      </el-table-column>
      <el-table-column
        prop="firstEdu"
        header-align="center"
        align="center"
        label="第一学历">
      </el-table-column>
      <el-table-column
        prop="technicalPost"
        header-align="center"
        align="center"
        label="专业技术职称">
      </el-table-column>
      <el-table-column
        prop="administerPost"
        header-align="center"
        align="center"
        label="行政职务">
      </el-table-column>
      <el-table-column
        prop="resume"
        header-align="center"
        align="center"
        label="简历">
      </el-table-column>
      <el-table-column
        prop="other"
        header-align="center"
        align="center"
        label="主要从事工作及研究方向">
      </el-table-column>
      <el-table-column
        prop="createTime"
        header-align="center"
        align="center"
        label="创建时间">
        <template slot-scope="scope">
          <el-tag v-text="scope.row.createTime.substring(0,10)" size="small"></el-tag>
        </template>
      </el-table-column>
      <el-table-column
        prop="updateTime"
        header-align="center"
        align="center"
        label="修改时间">
        <template slot-scope="scope">
          <el-tag v-text="scope.row.updateTime.substring(0,10)" size="small"></el-tag>
        </template>
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="detailHandle(scope.row.teacherId)">详情</el-button>
          <el-button type="text" style="color: red" size="small" @click="deleteHandl(scope.row.teacherId, scope.row.userId)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="sizeChangeHandle"
      @current-change="currentChangeHandle"
      :current-page="pageIndex"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="pageSize"
      :total="totalPage"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
    <!--<add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>-->
    <detail v-if="detailVisible" ref="detail" @refreshDataList="getDataList"></detail>
    <error-data v-if="errorDataVisible" ref="errorData" @refreshDataList="getDataList"></error-data>
  </div>
</template>

<script>
  // import AddOrUpdate from './info-add-or-update'
  import Detail from './details'
  import ErrorData from './bad-data'
  export default {
    data () {
      return {
        dataForm: {
          teacherName: '',
          checkTime: ''
        },
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
        // addOrUpdateVisible: false,
        detailVisible: false,
        errorDataVisible: false,
        uploadActionUrl: this.$http.adornUrl(`/business/teacherinfo/import?token=${this.$cookie.get('token')}`),
        upLoadData: {
          userId: this.$store.state.user.id
        }
      }
    },
    components: {
      // AddOrUpdate,
      Detail,
      ErrorData
    },
    activated () {
      this.getDataList()
    },
    methods: {
      // 获取数据列表
      getDataList () {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/business/teacherinfo/list'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'teacherName': this.dataForm.teacherName,
            'checkTime': this.dataForm.checkTime ? this.dataForm.checkTime.getFullYear() : null
          })
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.dataList = data.page.list
            this.totalPage = data.page.totalCount
          } else {
            this.dataList = []
            this.totalPage = 0
          }
          this.dataListLoading = false
        })
      },
      // 每页数
      sizeChangeHandle (val) {
        this.pageSize = val
        this.pageIndex = 1
        this.getDataList()
      },
      // 当前页
      currentChangeHandle (val) {
        this.pageIndex = val
        this.getDataList()
      },
      // 多选
      selectionChangeHandle (val) {
        this.dataListSelections = val
      },
      // 新增 / 修改
      // addOrUpdateHandle (id) {
      //   this.addOrUpdateVisible = true
      //   this.$nextTick(() => {
      //     this.$refs.addOrUpdate.init(id)
      //   })
      // },
      // 删除
      deleteHandle (id, userId) {
        // 教师id
        var ids = id ? [id] : this.dataListSelections.map(item => {
          return item.teacherId
        })
        // 教师对应的userId
        var userIds = userId ? [userId] : this.dataListSelections.map(item => {
          return item.userId
        })
        this.$confirm(`确定进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/business/teacherinfo/delete'),
            method: 'post',
            // data: this.$http.adornData(ids, false)
            data: this.$http.adornData({
              'ids': ids,
              'userIds': userIds
            }, false)
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.getDataList()
                }
              })
            } else {
              this.$message.error(data.msg)
            }
          })
        })
      },
      // 导入教师信息
      handleBeforeUpload () {
        // 上传文件之前的钩子
        this.dataListLoading = true
      },
      handleSuccess (res, file) {
        // 文件上传成功时的钩子
        this.dataListLoading = false
        // 回调
        if (res && res.code === 0) {
          this.$message({
            message: '数据导入成功!',
            type: 'success',
            duration: 1500,
            onClose: () => {
              // 提示完渲染页面
              this.getDataList()
            }
          })
        } else if (res.code === 444) {
          // 当有教师信息重复导入时
          this.errorDataVisible = true
          this.$nextTick(() => {
            this.$refs.errorData.init(res.errorData)
          })
        } else {
          this.$message.error(res.msg)
        }
        // 上传成功之后清除历史记录;否则只能上传一次
        this.$refs.upload.clearFiles()
      },
      handleError () {
        // 文件上传失败时的钩子
        this.dataListLoading = false
        this.$message({
          message: '数据导入失败!',
          type: 'error',
          duration: 1500
        })
      },
      // 下载模板
      downloadExcelTemplate () {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/business/teacherinfo/downloadExcelTemplate'),
          method: 'post',
          responseType: 'blob'
        }).then((res) => {
          this.dataListLoading = false
          // 将文件流转成blob形式
          const blob = new Blob([res.data], {type: 'application/vnd.ms-excel'})
          let filename = '教师信息导入模板.xls'
          // 创建一个超链接，将文件流赋进去，然后实现这个超链接的单击事件
          const elink = document.createElement('a')
          elink.download = filename
          elink.style.display = 'none'
          elink.href = URL.createObjectURL(blob)
          document.body.appendChild(elink)
          elink.click()
          URL.revokeObjectURL(elink.href) // 释放URL 对象
          document.body.removeChild(elink)
        }).catch(() => {
          this.dataListLoading = false
          this.$message.error('导出失败!')
        })
      },
      // 导出数据
      exportData () {
        this.dataListLoading = true
        var ids = this.dataListSelections.map(item => {
          return item.teacherId
        })
        this.$http({
          url: this.$http.adornUrl('/business/teacherinfo/export'),
          method: 'post',
          data: this.$http.adornData(ids, false),
          responseType: 'blob'
        }).then((res) => {
          this.dataListLoading = false
          // 将文件流转成blob形式
          const blob = new Blob([res.data], {type: 'application/vnd.ms-excel'})
          let filename = '教师信息.xls'
          // 创建一个超链接，将文件流赋进去，然后实现这个超链接的单击事件
          const elink = document.createElement('a')
          elink.download = filename
          elink.style.display = 'none'
          elink.href = URL.createObjectURL(blob)
          document.body.appendChild(elink)
          elink.click()
          URL.revokeObjectURL(elink.href) // 释放URL 对象
          document.body.removeChild(elink)
        }).catch(() => {
          this.dataListLoading = false
          this.$message.error('导出失败!')
        })
      },
      // 详情
      detailHandle (id) {
        this.detailVisible = true
        this.$nextTick(() => {
          this.$refs.detail.init(id)
        })
      }
    }
  }
</script>
