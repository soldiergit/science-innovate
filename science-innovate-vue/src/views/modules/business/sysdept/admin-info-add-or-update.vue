<template><!--新增/修改 部门管理员-->
  <el-dialog
    :title="!dataForm.deptAdminId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="sysUserEntity" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
             label-width="80px"
             v-loading="dataFormLoading"
             element-loading-text="拼命加载中"
             element-loading-spinner="el-icon-loading">
      <el-form-item label="所属部门">
        <el-tag size="small" type="success" v-text="deptName"></el-tag>
      </el-form-item>
      <el-form-item label="部门类型">
        <el-tag v-if="deptType === 0" size="small" type="success">二级学院</el-tag>
        <el-tag v-else-if="deptType === 1" size="small" type="info">教研室/实验室</el-tag>
      </el-form-item>
      <el-form-item label="用户名" prop="username">
        <el-input v-model="sysUserEntity.username" placeholder="登录帐号"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password" :class="{ 'is-required': !sysUserEntity.id }">
        <el-input v-model="sysUserEntity.password" type="password" placeholder="密码"></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="comfirmPassword" :class="{ 'is-required': !sysUserEntity.id }">
        <el-input v-model="sysUserEntity.comfirmPassword" type="password" placeholder="确认密码"></el-input>
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="sysUserEntity.email" placeholder="邮箱"></el-input>
      </el-form-item>
      <el-form-item label="手机号" prop="mobile">
        <el-input v-model="sysUserEntity.mobile" placeholder="手机号"></el-input>
      </el-form-item>
      <el-form-item label="状态" size="mini" prop="status">
        <el-radio-group v-model="sysUserEntity.status">
          <el-radio :label="0">禁用</el-radio>
          <el-radio :label="1">正常</el-radio>
        </el-radio-group>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()" :loading="addLoading">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  import { isEmail } from '@/utils/validate'
  export default {
    data () {
      var validatePassword = (rule, value, callback) => {
        if (!this.sysUserEntity.id && !/\S/.test(value)) {
          callback(new Error('密码不能为空'))
        } else {
          callback()
        }
      }
      var validateComfirmPassword = (rule, value, callback) => {
        if (!this.sysUserEntity.id && !/\S/.test(value)) {
          callback(new Error('确认密码不能为空'))
        } else if (this.sysUserEntity.password !== value) {
          callback(new Error('确认密码与密码输入不一致'))
        } else {
          callback()
        }
      }
      var validateEmail = (rule, value, callback) => {
        if (!isEmail(value)) {
          callback(new Error('邮箱格式错误'))
        } else {
          callback()
        }
      }
      return {
        visible: false,
        addLoading: false,
        dataFormLoading: false,
        deptName: '',
        deptType: '',
        dataForm: {
          deptAdminId: 0,
          deptId: 0,
          userId: 0
        },
        // 用户信息
        sysUserEntity: {
          id: 0,
          username: '',
          password: '',
          comfirmPassword: '',
          salt: '',
          email: '',
          mobile: '',
          roleIdList: [],
          createUserId: 0,
          status: 1
        },
        dataRule: {
          username: [
            { required: true, message: '用户名不能为空', trigger: 'blur' }
          ],
          password: [
            { validator: validatePassword, trigger: 'blur' }
          ],
          comfirmPassword: [
            { validator: validateComfirmPassword, trigger: 'blur' }
          ],
          email: [
            { required: true, message: '邮箱不能为空', trigger: 'blur' },
            { validator: validateEmail, trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id, name, type) {
        this.dataForm.deptId = id || 0
        this.deptName = name || ''
        this.deptType = type
        this.visible = true
        this.dataFormLoading = true
        this.$nextTick(() => {
          // 重置表单 如果想让resetFields方法有效果，必须要在el-form-item上增加prop字段，且和v-model的属性对应
          this.$refs['dataForm'].resetFields()
          this.dataForm.deptAdminId = 0
          this.sysUserEntity.roleIdList = []
          // this.sysUserEntity.roleIdList.push(type + 2)
          // 角色为二级学院管理员
          this.sysUserEntity.roleIdList.push(2)
          if (this.dataForm.deptId) {
            this.$http({
              url: this.$http.adornUrl(`/business/sysdeptadmininfo/getByDeptId/${this.dataForm.deptId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                if (data.sysDeptAdminInfo != null) {
                  this.dataForm.deptAdminId = data.sysDeptAdminInfo.deptAdminId
                  this.dataForm.userId = data.sysDeptAdminInfo.userId
                  this.sysUserEntity.id = data.sysDeptAdminInfo.sysUserEntity.userId
                  this.sysUserEntity.username = data.sysDeptAdminInfo.sysUserEntity.username
                  this.sysUserEntity.salt = data.sysDeptAdminInfo.sysUserEntity.salt
                  this.sysUserEntity.email = data.sysDeptAdminInfo.sysUserEntity.email
                  this.sysUserEntity.mobile = data.sysDeptAdminInfo.sysUserEntity.mobile
                  this.sysUserEntity.status = data.sysDeptAdminInfo.sysUserEntity.status
                }
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
            if (!this.dataForm.deptAdminId) {
              // 新增 直接把当前用户id传过去，就不用后台再查一次来了
              this.sysUserEntity.createUserId = this.$store.state.user.id
              this.$http({
                url: this.$http.adornUrl(`/business/sysdeptadmininfo/save`),
                method: 'post',
                data: this.$http.adornData({
                  'deptAdminId': this.dataForm.deptAdminId || undefined,
                  'deptId': this.dataForm.deptId,
                  'userId': this.dataForm.userId,
                  'sysUserEntity': this.sysUserEntity
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
            } else {
              // 如果是更新 直接更新用户信息  因为中间表信息不变了
              this.$http({
                url: this.$http.adornUrl(`/sys/user/update`),
                method: 'post',
                data: this.$http.adornData({
                  'userId': this.sysUserEntity.id,
                  'username': this.sysUserEntity.username,
                  'password': this.sysUserEntity.password,
                  'salt': this.sysUserEntity.salt,
                  'email': this.sysUserEntity.email,
                  'mobile': this.sysUserEntity.mobile,
                  'status': this.sysUserEntity.status,
                  'roleIdList': this.sysUserEntity.roleIdList
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
          }
        })
      }
    }
  }
</script>
