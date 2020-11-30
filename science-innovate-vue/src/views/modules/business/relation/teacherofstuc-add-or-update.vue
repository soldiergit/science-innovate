<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
             label-width="80px"
             v-loading="dataFormLoading"
             element-loading-text="拼命加载中"
             element-loading-spinner="el-icon-loading">
    <el-form-item label="教师id" prop="teaId">
      <el-input v-model="dataForm.teaId" placeholder="教师id"></el-input>
    </el-form-item>
    <el-form-item label="学生竞赛id" prop="scId">
      <el-input v-model="dataForm.scId" placeholder="学生竞赛id"></el-input>
    </el-form-item>
    <el-form-item label="赛事id" prop="matchId">
      <el-input v-model="dataForm.matchId" placeholder="赛事id"></el-input>
    </el-form-item>
    <el-form-item label="教师类型:1-第一指导老师 2-其它指导老师" prop="type">
      <el-input v-model="dataForm.type" placeholder="教师类型:1-第一指导老师 2-其它指导老师"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()" :loading="addLoading">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        addLoading: false,
        dataFormLoading: false,
        dataForm: {
          toscId: 0,
          teaId: '',
          scId: '',
          matchId: '',
          type: ''
        },
        dataRule: {
          teaId: [
            { required: true, message: '教师id不能为空', trigger: 'blur' }
          ],
          scId: [
            { required: true, message: '学生竞赛id不能为空', trigger: 'blur' }
          ],
          matchId: [
            { required: true, message: '赛事id不能为空', trigger: 'blur' }
          ],
          type: [
            { required: true, message: '教师类型:1-第一指导老师 2-其它指导老师不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.toscId = id || 0
        this.visible = true
        this.dataFormLoading = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.toscId) {
            this.$http({
              url: this.$http.adornUrl(`/business/teacherofstuc/info/${this.dataForm.toscId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.teaId = data.teacherOfStuc.teaId
                this.dataForm.scId = data.teacherOfStuc.scId
                this.dataForm.matchId = data.teacherOfStuc.matchId
                this.dataForm.type = data.teacherOfStuc.type
                this.dataFormLoading = false
              } else {
                this.dataFormLoading = false
              }
            })
          } else {
            this.dataFormLoading = false
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.addLoading = true
            this.$http({
              url: this.$http.adornUrl(`/business/teacherofstuc/${!this.dataForm.toscId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'toscId': this.dataForm.toscId || undefined,
                'teaId': this.dataForm.teaId,
                'scId': this.dataForm.scId,
                'matchId': this.dataForm.matchId,
                'type': this.dataForm.type
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.addLoading = false
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
                this.addLoading = true
              }
            })
          }
        })
      }
    }
  }
</script>
