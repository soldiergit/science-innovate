<template>
  <el-dialog
    :close-on-click-modal="false"
    :visible.sync="visible"
    width="90%">
    <template>
      <el-alert
        title="以下工号的教师已存在，请核对！其它工号教师已成功保存，无需再次上传！"
        type="error"
        center
        show-icon>
      </el-alert>
      <el-table
        :data="dataList"
        border
        v-loading="dataListLoading"
        id="out-table">
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
      </el-table>
    </template>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="warning" icon="el-icon-download" @click="exportXlsxHandle">导出</el-button>
    </span>
  </el-dialog>
</template>

<script>
  import FileSaver from 'file-saver'
  import XLSX from 'xlsx'

  export default {
    data () {
      return {
        visible: false,
        addLoading: false,
        dataListLoading: false,
        dataList: []
      }
    },
    methods: {
      init (errorData) {
        this.dataListLoading = true
        this.dataList = errorData || []
        this.visible = true
        this.$nextTick(() => {
          this.dataListLoading = false
        })
      },
      exportXlsxHandle () {
        this.$confirm(`确定要进行[导出]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          var msg = '操作成功'
          var type = 'success'
          var wb = XLSX.utils.table_to_book(document.querySelector('#out-table'))
          var wbout = XLSX.write(wb, {bookType: 'xlsx', bookSST: true, type: 'array'})
          try {
            FileSaver.saveAs(new Blob([wbout], {type: 'application/octet-stream'}), '重复导入教师信息.xlsx')
          } catch (e) {
            if (typeof console !== 'undefined') console.log(e, wbout)
            msg = '操作失败'
            type = 'error'
          }
          this.$message({
            message: msg,
            type: type,
            duration: 1500
          })
          // 关闭页面
          this.visible = false
          return wbout
        })
      }
    }
  }
</script>
