<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="教师id" prop="teaId">
      <el-input v-model="dataForm.teaId" placeholder="教师id"></el-input>
    </el-form-item>
    <el-form-item label="科研成果id" prop="rgId">
      <el-input v-model="dataForm.rgId" placeholder="科研成果id"></el-input>
    </el-form-item>
    <el-form-item label="教师类型:1-主持人 2-成员" prop="type">
      <el-input v-model="dataForm.type" placeholder="教师类型:1-主持人 2-成员"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          torgId: 0,
          teaId: '',
          rgId: '',
          type: ''
        },
        dataRule: {
          teaId: [
            { required: true, message: '教师id不能为空', trigger: 'blur' }
          ],
          rgId: [
            { required: true, message: '科研成果id不能为空', trigger: 'blur' }
          ],
          type: [
            { required: true, message: '教师类型:1-主持人 2-成员不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.torgId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.torgId) {
            this.$http({
              url: this.$http.adornUrl(`/business/teacherofrg/info/${this.dataForm.torgId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.teaId = data.teacherOfRg.teaId
                this.dataForm.rgId = data.teacherOfRg.rgId
                this.dataForm.type = data.teacherOfRg.type
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
              url: this.$http.adornUrl(`/business/teacherofrg/${!this.dataForm.torgId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'torgId': this.dataForm.torgId || undefined,
                'teaId': this.dataForm.teaId,
                'rgId': this.dataForm.rgId,
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
