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
    <el-form-item label="论文id" prop="apId">
      <el-input v-model="dataForm.apId" placeholder="论文id"></el-input>
    </el-form-item>
    <el-form-item label="作者类型:1-主持人 2-成员" prop="type">
      <el-input v-model="dataForm.type" placeholder="作者类型:1-主持人 2-成员"></el-input>
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
          toapId: 0,
          teaId: '',
          apId: '',
          type: ''
        },
        dataRule: {
          teaId: [
            { required: true, message: '教师id不能为空', trigger: 'blur' }
          ],
          apId: [
            { required: true, message: '论文id不能为空', trigger: 'blur' }
          ],
          type: [
            { required: true, message: '作者类型:1-主持人 2-成员不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.toapId = id || 0
        this.visible = true
        this.dataFormLoading = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.toapId) {
            this.$http({
              url: this.$http.adornUrl(`/business/teacherofap/info/${this.dataForm.toapId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.teaId = data.teacherOfAp.teaId
                this.dataForm.apId = data.teacherOfAp.apId
                this.dataForm.type = data.teacherOfAp.type
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
              url: this.$http.adornUrl(`/business/teacherofap/${!this.dataForm.toapId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'toapId': this.dataForm.toapId || undefined,
                'teaId': this.dataForm.teaId,
                'apId': this.dataForm.apId,
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
