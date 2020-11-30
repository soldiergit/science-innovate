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
    <el-form-item label="大创项目id" prop="scId">
      <el-input v-model="dataForm.scId" placeholder="大创项目id"></el-input>
    </el-form-item>
    <el-form-item label="教师类型:1-第一著作权人 2-成员" prop="type">
      <el-input v-model="dataForm.type" placeholder="教师类型:1-第一著作权人 2-成员"></el-input>
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
          type: ''
        },
        dataRule: {
          teaId: [
            { required: true, message: '教师id不能为空', trigger: 'blur' }
          ],
          scId: [
            { required: true, message: '大创项目id不能为空', trigger: 'blur' }
          ],
          type: [
            { required: true, message: '教师类型:1-第一著作权人 2-成员不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.toscId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.toscId) {
            this.$http({
              url: this.$http.adornUrl(`/business/teacherofsc/info/${this.dataForm.toscId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.teaId = data.tbTeacherOfSc.teaId
                this.dataForm.scId = data.tbTeacherOfSc.scId
                this.dataForm.type = data.tbTeacherOfSc.type
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/business/teacherofsc/${!this.dataForm.toscId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'toscId': this.dataForm.toscId || undefined,
                'teaId': this.dataForm.teaId,
                'scId': this.dataForm.scId,
                'type': this.dataForm.type
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
