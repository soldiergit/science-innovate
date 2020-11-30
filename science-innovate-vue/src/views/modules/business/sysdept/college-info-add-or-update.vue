<template><!--学校管理员新增/修改 二级学院-->
  <el-dialog
    :title="!dataForm.deptId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
             label-width="80px"
             v-loading="dataFormLoading"
             element-loading-text="拼命加载中"
             element-loading-spinner="el-icon-loading">
      <el-form-item label="类型" prop="type">
        <el-radio-group v-model="dataForm.type">
          <el-radio v-for="(type, index) in dataForm.typeList" :label="index" :key="index">{{ type }}</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item v-show="dataForm.type === 1" label="上级部门" prop="parentName">
        <!--https://element.eleme.cn/#/zh-CN/component/popover#events-->
        <!--Popover 弹出框嵌套一个tree列表-->
        <!--placement：出现位置-->
        <el-popover
          ref="deptListPopover"
          placement="bottom-start"
          trigger="click">
          <!--https://element.eleme.cn/#/zh-CN/component/tree-->
          <el-tree
            :data="deptList"
            :props="deptListTreeProps"
            node-key="deptId"
            ref="deptListTree"
            @current-change="deptListTreeCurrentChangeHandle"
            :default-expand-all="true"
            :highlight-current="true"
            :expand-on-click-node="false">
          </el-tree>
        </el-popover>
        <el-input v-model="dataForm.parentName" v-popover:deptListPopover :readonly="true" placeholder="点击选择上级部门"
                  class="dept-list__input"></el-input>
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
                filter-placeholder="请输入教师姓名"
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
  import { treeDataTranslate } from '@/utils'
  export default {
    data () {
      return {
        visible: false,
        addLoading: false,
        dataFormLoading: false,
        dataForm: {
          deptId: 0,
          type: 0,
          typeList: ['二级学院', '教研室/实验室'],
          parentId: 0,
          parentName: '',
          personId: '',
          name: '',
          phone: ''
        },
        dataRule: {
          parentId: [
            { required: true, message: '上级部门不能为空', trigger: 'blur' }
          ],
          name: [
            { required: true, message: '部门名称不能为空', trigger: 'blur' }
          ]
        },
        deptList: [],
        // Tree数据源的字段别名
        deptListTreeProps: {
          label: 'name',
          children: 'children'
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
          this.deptList = []
          this.choiceDataList = []
          this.notChoiceDataList = []
          // 获取可被选择的菜单
          this.$http({
            url: this.$http.adornUrl('/business/sysdept/collegeSelect'),
            method: 'get',
            params: this.$http.adornParams()
          }).then(({data}) => {
            // 树数据转换
            this.deptList = treeDataTranslate(data.deptList, 'deptId')
          }).then(() => {
            // 获取教师信息
            this.$http({
              url: this.$http.adornUrl('/business/teacherinfo/adminSelect'),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              this.teacherList = data.teacherList
            }).then(() => {
              // 未选成员永远为全部教师
              this.notChoiceDataList = this.teacherList
              if (!this.dataForm.deptId) {
                // 新增时 已选成员为空
                this.choiceDataList = []
                this.deptListTreeSetCurrentNode()
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
                    this.dataForm.type = data.dept.type
                    this.dataForm.parentId = data.dept.parentId
                    this.dataForm.personId = data.dept.personId
                    this.dataForm.name = data.dept.name
                    this.dataForm.phone = data.dept.phone
                    this.deptListTreeSetCurrentNode()
                  }
                })
                // 查询已加入部门成员的teacherId
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
        })
      },
      // 菜单树被用户点击选中
      deptListTreeCurrentChangeHandle (data, node) {
        this.dataForm.parentId = data.deptId
        this.dataForm.parentName = data.name
      },
      // 菜单树设置当前选中节点
      deptListTreeSetCurrentNode () {
        this.$refs.deptListTree.setCurrentKey(this.dataForm.parentId)
        this.dataForm.parentName = (this.$refs.deptListTree.getCurrentNode() || {})['name']
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
                'type': this.dataForm.type,
                'parentId': this.dataForm.type === 0 ? 0 : this.dataForm.parentId,
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

<style lang="scss">
  .mod-dept {
    .dept-list__input,
    .icon-list__input {
      > .el-input__inner {
        cursor: pointer;
      }
    }
    &__icon-popover {
      width: 458px;
      overflow: hidden;
    }
    &__icon-inner {
      width: 478px;
      max-height: 258px;
      overflow-x: hidden;
      overflow-y: auto;
    }
    &__icon-list {
      width: 458px;
      padding: 0;
      margin: -8px 0 0 -8px;
      > .el-button {
        padding: 8px;
        margin: 8px 0 0 8px;
        > span {
          display: inline-block;
          vertical-align: middle;
          width: 18px;
          height: 18px;
          font-size: 18px;
        }
      }
    }
    .icon-list__tips {
      font-size: 18px;
      text-align: center;
      color: #e6a23c;
      cursor: pointer;
    }
  }
  #transferWW .el-transfer .el-transfer-panel{
    width: 40%
  }
</style>
