<template>
  <el-dialog
    :title="!dataForm.matchId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
             label-width="80px"
             v-loading="dataFormLoading"
             element-loading-text="拼命加载中"
             element-loading-spinner="el-icon-loading">
    <el-form-item label="主办单位" prop="organizer">
      <el-input v-model="dataForm.organizer" placeholder="主办单位"></el-input>
    </el-form-item>
    <el-form-item label="承办单位" prop="contractor">
      <el-input v-model="dataForm.contractor" placeholder="承办单位"></el-input>
    </el-form-item>
    <el-form-item label="赛事名称" prop="name">
      <el-input v-model="dataForm.name" placeholder="赛事名称"></el-input>
    </el-form-item>
    <el-form-item label="赛事等级" prop="grade">
      <el-select v-model="dataForm.grade" placeholder="请选择">
        <el-option v-for="item in competitionGradeList" :key="item.value" :label="item.label" :value="item.value">
        </el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="赛事类型" prop="type">
      <el-select v-model="dataForm.type" placeholder="请选择">
        <el-option v-for="item in typeList" :key="item.value" :label="item.label" :value="item.value">
        </el-option>
      </el-select>
    </el-form-item>
      <el-form-item label="文件上传" prop="fileList">
        <el-upload
          class="upload-demo"
          :data="upLoadData"
          :action="upLoadUrl"
          :before-remove="beforeRemove"
          :on-remove="upLoadRemove"
          :on-success="upLoadSuccess"
          :file-list="fileList">
          <el-button size="small" type="primary">上传附件</el-button>
        </el-upload>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()" :loading="addLoading">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  import {clearLoginInfo, transferFileToData, transferDataToFile, Attach} from '@/utils'

  export default {
    data () {
      return {
        visible: false,
        addLoading: false,
        dataFormLoading: false,
        // 文件上传路径
        upLoadUrl: this.$http.adornUrl(`/business/sysattach/upload`),
        // 文件上传携带的数据
        upLoadData: {
          'userId': this.$store.state.user.id,
          'fileType': '/MATCH/',
          'token': this.$cookie.get('token')
        },
        // 附件对象集合
        fileList: [],
        dataForm: {
          matchId: 0,
          organizer: '',
          contractor: '',
          name: '',
          grade: '',
          type: ''
        },
        competitionGradeList: this.$store.state.user.competitionGradeEntities,
        typeList: [
          {value: 1, label: '学生竞赛'},
          {value: 2, label: '教师竞赛'}
        ],
        dataRule: {
          organizer: [
            { required: true, message: '主办单位不能为空', trigger: 'blur' }
          ],
          contractor: [
            { required: true, message: '承办单位不能为空', trigger: 'blur' }
          ],
          name: [
            { required: true, message: '赛事名称不能为空', trigger: 'blur' }
          ],
          grade: [
            { required: true, message: '赛事等级不能为空', trigger: 'blur' }
          ],
          type: [
            { required: true, message: '赛事类型不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.matchId = id || 0
        this.visible = true
        this.dataFormLoading = true
        this.$nextTick(() => {
          // 重置表单 如果想让resetFields方法有效果，必须要在el-form-item上增加prop字段，且和v-model的属性对应
          this.fileList = []
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.matchId) {
            this.$http({
              url: this.$http.adornUrl(`/business/matchinfo/info/${this.dataForm.matchId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.organizer = data.matchInfo.organizer
                this.dataForm.contractor = data.matchInfo.contractor
                this.dataForm.name = data.matchInfo.name
                this.dataForm.grade = data.matchInfo.grade
                this.dataForm.type = data.matchInfo.type
                // 附件信息
                this.fileList = transferDataToFile(data.matchInfo.sysAttachEntityList)
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
              url: this.$http.adornUrl(`/business/matchinfo/${!this.dataForm.matchId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'matchId': this.dataForm.matchId || undefined,
                'organizer': this.dataForm.organizer,
                'contractor': this.dataForm.contractor,
                'name': this.dataForm.name,
                'grade': this.dataForm.grade,
                'type': this.dataForm.type,
                'sysAttachEntityList': transferFileToData(this.fileList)
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
      },
      // 移除附件
      beforeRemove (file, fileList) {
        return this.$confirm(`确定移除 ${file.name}？`)
      },
      // 移除附件
      upLoadRemove (file, fileList) {
        this.$http({
          url: this.$http.adornUrl('/business/sysattach/deleteFile'),
          method: 'post',
          params: this.$http.adornParams({
            'filePath': file.url
          })
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.fileList = fileList
          } else {
            this.$message.error(data.msg)
          }
        })
      },
      // 文件上传成功后回调
      upLoadSuccess (data) {
        if (data && data.code === 0) {
          // 将回调的后台文件对象转为前端的文件对象
          this.fileList.push(new Attach(data.sysAttachEntity))
        } else {
          this.$alert(data.msg, '提示', {
            confirmButtonText: '确定',
            type: 'error'
          }).then(() => {
            this.$http({
              url: this.$http.adornUrl('/sys/logout'),
              method: 'post',
              data: this.$http.adornData()
            }).then(({data}) => {
              if (data && data.code === 0) {
                clearLoginInfo()
                this.$router.push({ name: 'login' })
              }
            })
          })
        }
      }
    }
  }
</script>
