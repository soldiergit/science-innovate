<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="教师id" prop="teaId">
      <el-input v-model="dataForm.teaId" placeholder="教师id"></el-input>
    </el-form-item>
    <el-form-item label="大创项目id" prop="ipId">
      <el-input v-model="dataForm.ipId" placeholder="大创项目id"></el-input>
    </el-form-item>
    <el-form-item label="教师类型:1-第一指导老师 2-第二指导老师" prop="type">
      <el-input v-model="dataForm.type" placeholder="教师类型:1-第一指导老师 2-第二指导老师"></el-input>
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
          toipId: 0,
          teaId: '',
          ipId: '',
          type: ''
        },
        dataRule: {
          teaId: [
            { required: true, message: '教师id不能为空', trigger: 'blur' }
          ],
          ipId: [
            { required: true, message: '大创项目id不能为空', trigger: 'blur' }
          ],
          type: [
            { required: true, message: '教师类型:1-第一指导老师 2-第二指导老师不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.toipId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.toipId) {
            this.$http({
              url: this.$http.adornUrl(`/business/teacherofip/info/${this.dataForm.toipId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.teaId = data.teacherOfIp.teaId
                this.dataForm.ipId = data.teacherOfIp.ipId
                this.dataForm.type = data.teacherOfIp.type
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
              url: this.$http.adornUrl(`/business/teacherofip/${!this.dataForm.toipId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'toipId': this.dataForm.toipId || undefined,
                'teaId': this.dataForm.teaId,
                'ipId': this.dataForm.ipId,
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
