<template>
  <el-dialog
    width="60%"
    :title="'我的个人信息修改'"
    :close-on-click-modal="false"
    :visible.sync="visible"
    :append-to-body="true">
    <el-row :gutter="20">
      <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
               label-width="16rem"
               style="width: 94%; margin: 0 auto"
               v-loading="dataFormLoading"
               element-loading-text="拼命加载中"
               element-loading-spinner="el-icon-loading">
        <el-col :span="24">
          <el-form-item label="证件照" prop="imgPath">
            <el-upload
              class="avatar-uploader"
              accept=".jpg,.jpeg,.png,.JPG,.JPEG,.PNG"
              :data="portraitUpLoadData"
              :action="portraitUpLoadUrl"
              :show-file-list="false"
              :on-success="handlePortraitSuccess">
              <img v-if="dataForm.imgPath" :src="src" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
              <div slot="tip" class="el-upload__tip">温馨提示：点击图片进行修改</div>
            </el-upload>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="工号" prop="teacherCode">
            <el-input v-model="dataForm.teacherCode" placeholder="工号" :disabled="true"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="姓名" prop="name">
            <el-input v-model="dataForm.name" placeholder="姓名" :disabled="true"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="性别" prop="sex">
            <el-select v-model="dataForm.sex" placeholder="请选择">
              <el-option
                v-for="item in sexList"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="出生日期" prop="birthDay">
            <el-date-picker
              value-format="yyyy-MM-dd HH:mm:ss"
              type="date"
              v-model="dataForm.birthDay"
              placeholder="选择日期">
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="入职时间" prop="entryTime">
            <el-date-picker
              value-format="yyyy-MM-dd HH:mm:ss"
              type="date"
              v-model="dataForm.entryTime"
              placeholder="选择日期">
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="最高学历" prop="highEdu">
            <el-input v-model="dataForm.highEdu" placeholder="最高学历"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="第一学历" prop="firstEdu">
            <el-input v-model="dataForm.firstEdu" placeholder="第一学历"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="专业技术职称" prop="technicalPost">
            <el-input v-model="dataForm.technicalPost" placeholder="专业技术职称" type="textarea" :rows="3"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="行政职务" prop="administerPost">
            <el-input v-model="dataForm.administerPost" placeholder="行政职务"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="简历" prop="resume">
            <el-input v-model="dataForm.resume" placeholder="简历" type="textarea" :rows="4"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="主要从事工作及研究方向" prop="other">
            <el-input v-model="dataForm.other" placeholder="主要从事工作及研究方向" type="textarea" :rows="4"></el-input>
          </el-form-item>
        </el-col>
      </el-form>
    </el-row>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()" :loading="addLoading">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  import {clearLoginInfo} from '@/utils'

  export default {
    data () {
      return {
        visible: false,
        addLoading: false,
        dataFormLoading: false,
        src: '',
        // 教师头像上传路径
        portraitUpLoadUrl: this.$http.adornUrl(`/business/sysattach/uploadPortrait`),
        // 教师头像上传携带的数据
        portraitUpLoadData: {},
        dataForm: {
          teacherId: 0,
          userId: 0,
          teacherCode: '',
          name: '',
          sex: '',
          birthDay: '',
          entryTime: '',
          imgPath: '',
          highEdu: '',
          firstEdu: '',
          technicalPost: '',
          administerPost: '',
          resume: '',
          other: '',
          createTime: '',
          updateTime: ''
        },
        sexList: [
          {value: '男', label: '男'},
          {value: '女', label: '女'}
        ],
        dataRule: {
          imgPath: [
            {required: true, message: '证件照不能为空', trigger: 'blur'}
          ],
          teacherCode: [
            {required: true, message: '工号不能为空', trigger: 'blur'}
          ],
          name: [
            {required: true, message: '姓名不能为空', trigger: 'blur'}
          ],
          sex: [
            {required: true, message: '性别不能为空', trigger: 'blur'}
          ],
          birthDay: [
            {required: true, message: '出生日期不能为空', trigger: 'blur'}
          ],
          entryTime: [
            {required: true, message: '入职时间不能为空', trigger: 'blur'}
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.userId = id || 0
        this.dataForm.teacherId = 0
        this.visible = true
        this.dataFormLoading = true
        this.$nextTick(() => {
          // 重置表单 如果想让resetFields方法有效果，必须要在el-form-item上增加prop字段，且和v-model的属性对应
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.userId) {
            this.$http({
              url: this.$http.adornUrl(`/business/teacherinfo/myInfo/${this.dataForm.userId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.teacherId = data.teacherInfo.teacherId
                this.dataForm.teacherCode = data.teacherInfo.teacherCode
                this.dataForm.name = data.teacherInfo.name
                this.dataForm.sex = data.teacherInfo.sex
                this.dataForm.birthDay = data.teacherInfo.birthDay
                this.dataForm.entryTime = data.teacherInfo.entryTime
                this.dataForm.imgPath = data.teacherInfo.imgPath
                this.dataForm.highEdu = data.teacherInfo.highEdu
                this.dataForm.firstEdu = data.teacherInfo.firstEdu
                this.dataForm.technicalPost = data.teacherInfo.technicalPost
                this.dataForm.administerPost = data.teacherInfo.administerPost
                this.dataForm.resume = data.teacherInfo.resume
                this.dataForm.other = data.teacherInfo.other
                this.dataForm.createTime = data.teacherInfo.createTime
                this.dataForm.updateTime = data.teacherInfo.updateTime
                this.getPortrait()
                this.portraitUpLoadData = {
                  'teacherCode': data.teacherInfo.teacherCode,
                  'name': data.teacherInfo.name,
                  'token': this.$cookie.get('token')
                }
                this.dataFormLoading = false
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
              url: this.$http.adornUrl('/business/teacherinfo/update'),
              method: 'post',
              data: this.$http.adornData({
                'teacherId': this.dataForm.teacherId || undefined,
                // 当前教师的userId，不需要更新==》updateById 更新字段为null 不更新
                'userId': undefined,
                // 当前教师的创建者id
                'createUserId': this.dataForm.userId,
                'teacherCode': this.dataForm.teacherCode,
                'name': this.dataForm.name,
                'sex': this.dataForm.sex,
                'imgPath': this.dataForm.imgPath,
                'birthDay': this.dataForm.birthDay,
                'entryTime': this.dataForm.entryTime,
                'highEdu': this.dataForm.highEdu,
                'firstEdu': this.dataForm.firstEdu,
                'technicalPost': this.dataForm.technicalPost,
                'administerPost': this.dataForm.administerPost,
                'resume': this.dataForm.resume,
                'other': this.dataForm.other
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
      getPortrait () {
        // 获取照片
        if (this.dataForm.imgPath) {
          this.src = this.$http.adornUrl(`/business/teacherinfo/portrait?imgPath=${this.dataForm.imgPath}`)
        }
      },
      handlePortraitSuccess (res, file) {
        this.dataForm.imgPath = res.savePath
        this.getPortrait()
      }
    }
  }
</script>

<style>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
