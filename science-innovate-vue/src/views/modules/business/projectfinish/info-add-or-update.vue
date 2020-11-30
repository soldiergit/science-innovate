<template>
  <el-dialog
    :title="!dataForm.pfId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
             label-width="80px"
             v-loading="dataFormLoading"
             element-loading-text="拼命加载中"
             element-loading-spinner="el-icon-loading">
      <el-form-item label="结题项目" prop="pcId">
        <el-select v-model="dataForm.pcId" filterable placeholder="请选择">
          <el-option v-for="item in projectCreateList"
                     :key="item.pcId"
                     :label="item.name"
                     :value="item.pcId">
          </el-option>
        </el-select>
      </el-form-item>
      <!--<el-form-item label="结题教师id" prop="teaId">-->
      <!--<el-input v-model="dataForm.teaId" placeholder="结题教师id"></el-input>-->
      <!--</el-form-item>-->
      <el-form-item label="结题时间" prop="finishTime">
        <el-date-picker
          value-format="yyyy-MM-dd HH:mm:ss"
          type="date"
          v-model="dataForm.finishTime"
          :picker-options="pickerOptions"
          placeholder="选择结题时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="备注" prop="remarks">
        <el-input
          type="textarea"
          :rows="5"
          placeholder="请输入内容"
          v-model="dataForm.remarks">
        </el-input>
      </el-form-item>
      <el-form-item label="结题材料" prop="fileList">
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
      var validateAttach = (rule, value, callback) => {
        if (this.fileList.length === 0) {
          callback(new Error('证明材料不能为空'))
        } else {
          let notNull = false
          for (let index = 0; index < this.fileList.length; index++) {
            if (this.fileList[index].isDel === 0) {
              notNull = true
            }
          }
          if (notNull) {
            callback()
          } else {
            callback(new Error('证明材料不能为空'))
          }
        }
      }
      return {
        visible: false,
        addLoading: false,
        dataFormLoading: false,
        // 文件上传路径
        upLoadUrl: this.$http.adornUrl(`/business/sysattach/upload`),
        // 文件上传携带的数据
        upLoadData: {
          'userId': this.$store.state.user.id,
          'fileType': '/PF/',
          'token': this.$cookie.get('token')
        },
        // 附件对象集合
        fileList: [],
        // 待结题项目集合
        projectCreateList: [],
        // 只能选择今天以及今天以前的日期
        pickerOptions: {
          disabledDate (time) {
            return time.getTime() > Date.now() - 8.64e6
          }
        },
        dataForm: {
          pfId: 0,
          pcId: '',
          // teaId: '',
          finishTime: '',
          remarks: ''
        },
        dataRule: {
          pcId: [
            {required: true, message: '结题项目不能为空', trigger: 'blur'}
          ],
          // teaId: [
          //   { required: true, message: '结题教师id不能为空', trigger: 'blur' }
          // ],
          finishTime: [
            {required: true, message: '结题时间不能为空', trigger: 'blur'}
          ],
          // remarks: [
          //   {required: true, message: '备注不能为空', trigger: 'blur'}
          // ],
          fileList: [
            {required: true, validator: validateAttach, trigger: 'blur'}
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.pfId = id || 0
        this.visible = true
        this.dataFormLoading = true
        this.$nextTick(() => {
          // 重置表单 如果想让resetFields方法有效果，必须要在el-form-item上增加prop字段，且和v-model的属性对应
          this.$refs['dataForm'].resetFields()
          this.fileList = []
          this.projectCreateList = []
          // 教师以负责人或成员的身份查询未结题项目
          this.$http({
            url: this.$http.adornUrl(`/business/projectcreate/myproject/${this.$store.state.user.id}`),
            method: 'get',
            params: this.$http.adornParams()
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.projectCreateList = data.projectCreateEntities
            } else {
              this.$alert(data.msg, '提示', {
                confirmButtonText: '确定',
                type: 'error'
              }).then(() => {
                this.addLoading = false
                this.visible = false
              })
            }
          }).then(() => {
            if (this.dataForm.pfId) {
              this.$http({
                url: this.$http.adornUrl(`/business/projectfinish/info/${this.dataForm.pfId}`),
                method: 'get',
                params: this.$http.adornParams()
              }).then(({data}) => {
                if (data && data.code === 0) {
                  this.dataForm.pcId = data.projectFinish.pcId
                  // this.dataForm.teaId = data.projectFinish.teaId
                  this.dataForm.finishTime = data.projectFinish.finishTime
                  this.dataForm.remarks = data.projectFinish.remarks
                  // 附件信息
                  this.fileList = transferDataToFile(data.projectFinish.sysAttachEntityList)
                  this.dataFormLoading = false
                } else {
                  this.dataFormLoading = false
                }
              })
            } else {
              this.dataFormLoading = false
            }
          })
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.addLoading = true
            this.$http({
              url: this.$http.adornUrl(`/business/projectfinish/${!this.dataForm.pfId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'pfId': this.dataForm.pfId || undefined,
                // 被结题项目立项id
                'pcId': this.dataForm.pcId,
                // 'teaId': this.dataForm.teaId,
                'finishTime': this.dataForm.finishTime,
                'remarks': this.dataForm.remarks,
                // 项目结题人用户id
                'userId': this.$store.state.user.id,
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
                this.$router.push({name: 'login'})
              }
            })
          })
        }
      }
    }
  }
</script>
