<template><!--二级学院管理员新增/修改 教研室/实验室 -->
  <el-dialog
    :title="!dataForm.deptId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
             label-width="80px"
             v-loading="dataFormLoading"
             element-loading-text="拼命加载中"
             element-loading-spinner="el-icon-loading">
      <el-form-item label="部门类型">
        <el-tag size="small" type="info" v-text="'教研室/实验室'"></el-tag>
      </el-form-item>
      <el-form-item label="上级部门">
        <el-tag size="small" type="success" v-text="parentDeptInfo.name"></el-tag>
      </el-form-item>
      <el-form-item label="负责人">
        <el-select v-model="dataForm.personId" filterable placeholder="请选择部门负责人">
          <el-option
            v-for="item in teacherList"
            :key="item.teacherId"
            :label="item.name"
            :value="item.teacherId">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="部门名称" prop="name">
        <el-input v-model="dataForm.name" placeholder="部门名称"></el-input>
      </el-form-item>
      <el-form-item label="部门电话" prop="phone">
        <el-input v-model="dataForm.phone" placeholder="部门电话"></el-input>
      </el-form-item>
      <el-form-item label="部门成员">
        <!--穿梭层 :data显示左边数据，数据源 v-model显示右边数据-->
        <!--el-transfer会自动从数据源中分离数据-->
        <div id="transferWW">
          <el-transfer
            filterable
            filter-placeholder="请教师姓名"
            :titles="['未选择成员', '已选择成员']"
            :props=teacherListTransferProps
            :data="notChoiceDataList"
            v-model="choiceDataList">
          </el-transfer>
        </div>
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
          deptId: 0,
          personId: '',
          name: '',
          phone: ''
        },
        // 当前用户（二级学院管理员）管理的部门信息
        parentDeptInfo: {},
        dataRule: {
          name: [
            { required: true, message: '部门名称不能为空', trigger: 'blur' }
          ]
        },
        // el-transfer会自动从数据源中分离数据：即未选成员应该永远为全体教师；已选择成员数组只存放key 即teacherId，不能多家其它，label等
        choiceDataList: [], // 已选择成员
        notChoiceDataList: [], // 未选择成员
        // Transfer数据源的字段别名
        teacherListTransferProps: {
          key: 'teacherId',
          label: 'name'
        },
        teacherList: []
      }
    },
    methods: {
      init (id) {
        this.dataForm.deptId = id || 0
        this.visible = true
        this.dataFormLoading = true
        this.$nextTick(() => {
          // 重置表单 如果想让resetFields方法有效果，必须要在el-form-item上增加prop字段，且和v-model的属性对应
          this.$refs['dataForm'].resetFields()
          this.choiceDataList = []
          this.notChoiceDataList = []
          this.teacherList = []
          // 二级学院管理员：查询该二级学院下的所有教师
          this.$http({
            url: this.$http.adornUrl('/business/teacherofdept/mydepttealist'),
            method: 'get',
            params: this.$http.adornParams({
              'userId': this.$store.state.user.id
            })
          }).then(({data}) => {
            this.teacherList = data.teacherList
            this.parentDeptInfo = data.parentDeptInfo
          }).then(() => {
            // 未选成员永远为当前部门全部教师
            this.notChoiceDataList = this.teacherList
            if (!this.dataForm.deptId) {
              // 新增时 已选成员为空
              this.choiceDataList = []
              this.dataFormLoading = false
            } else {
              // 修改
              this.$http({
                url: this.$http.adornUrl(`/business/sysdept/info/${this.dataForm.deptId}`),
                method: 'get',
                params: this.$http.adornParams()
              }).then(({data}) => {
                if (data && data.code === 0) {
                  this.dataForm.id = data.dept.deptId
                  this.dataForm.personId = data.dept.personId
                  this.dataForm.name = data.dept.name
                  this.dataForm.phone = data.dept.phone
                }
              })
              // 查询已加入部门(教研室/实验室id)成员的teacherId
              this.$http({
                url: this.$http.adornUrl('/business/teacherofdept/choiceteaids'),
                method: 'get',
                params: this.$http.adornParams({
                  'deptId': this.dataForm.deptId
                })
              }).then(({data}) => {
                if (data && data.code === 0) {
                  this.choiceDataList = data.data
                }
                this.dataFormLoading = false
              })
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
              url: this.$http.adornUrl(`/business/sysdept/${!this.dataForm.deptId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'deptId': this.dataForm.deptId || undefined,
                // 部门类型永远都是教研室/实验室
                'type': 1,
                // 永远为当前用户（二级学院管理员）管理的部门id
                'parentId': this.parentDeptInfo.deptId,
                'personId': this.dataForm.personId,
                'name': this.dataForm.name,
                'phone': this.dataForm.phone,
                'memberIdList': this.choiceDataList
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
<style>
  #transferWW .el-transfer .el-transfer-panel{
    width: 40%
  }
</style>
