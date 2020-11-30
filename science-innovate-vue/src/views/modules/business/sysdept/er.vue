<template><!--系统管理员不应该进入这个页面-->
  <div class="mod-dept">
    <el-form :inline="true" :model="dataForm">
      <el-form-item>
        <el-button v-if="isAuth('business:sysdept:save')" type="primary" @click="deptInfoAddOrUpdateHandle()">新增</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="dataList"
      row-key="deptId"
      border
      style="width: 100%;">
      <el-table-column
        prop="name"
        header-align="center"
        min-width="150"
        label="部门名称">
      </el-table-column>
      <el-table-column
        prop="parentName"
        header-align="center"
        align="center"
        width="120"
        label="上级部门">
      </el-table-column>
      <el-table-column
        prop="personName"
        header-align="center"
        align="center"
        label="部门负责人">
      </el-table-column>
      <el-table-column
        prop="phone"
        header-align="center"
        align="center"
        label="部门电话">
      </el-table-column>
      <el-table-column
        prop="type"
        header-align="center"
        align="center"
        label="类型">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.type === 0" size="small" type="success">二级学院</el-tag>
          <el-tag v-else-if="scope.row.type === 1" size="small" type="info">教研室/实验室</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <!--教研室，暂不设置管理员-->
          <el-button v-if="scope.row.type === 8888" type="text" size="small" @click="deptAdminAddOrUpdateHandle(scope.row.deptId, scope.row.name, scope.row.type)">管理员</el-button>
          <el-button type="text" size="small" @click="deptInfoAddOrUpdateHandle(scope.row.deptId)">修改</el-button>
          <el-button type="text" style="color: red" size="small" @click="deleteHandl(scope.row.deptId)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 弹窗, 新增 / 修改 -->
    <dept-admin-add-or-update v-if="deptAdminAddOrUpdateVisible" ref="deptAdminAddOrUpdate" @refreshDataList="getDataList"></dept-admin-add-or-update>
    <er-dept-info-add-or-update v-if="erDeptInfoAddOrUpdateVisible" ref="erDeptInfoAddOrUpdate" @refreshDataList="getDataList"></er-dept-info-add-or-update>
  </div>
</template>

<script>
  import DeptAdminAddOrUpdate from './admin-info-add-or-update'
  import ErDeptInfoAddOrUpdate from './lab-info-add-or-update'
  import { treeDataTranslate } from '@/utils'
  export default {
    data () {
      return {
        dataForm: {},
        dataList: [],
        deptAdminAddOrUpdateVisible: false,
        erDeptInfoAddOrUpdateVisible: false
      }
    },
    components: {
      DeptAdminAddOrUpdate,
      ErDeptInfoAddOrUpdate
    },
    activated () {
      this.getDataList()
    },
    methods: {
      // 获取数据列表
      getDataList () {
        const loading = this.$loading({
          lock: true,
          text: '数据疯狂查询中...',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.8)'
        })
        this.$http({
          url: this.$http.adornUrl('/business/sysdept/treelist'),
          method: 'get',
          params: this.$http.adornParams({
            'userId': this.$store.state.user.id
          })
        }).then(({data}) => {
          // 转为树型数据
          this.dataList = treeDataTranslate(data, 'deptId')
        }).then(() => {
          setTimeout(() => {
            // 关闭loading和页面
            loading.close()
          }, 5000)
        })
      },
      // 部门管理员 新增 / 修改
      deptAdminAddOrUpdateHandle (id, name, type) {
        this.deptAdminAddOrUpdateVisible = true
        this.$nextTick(() => {
          this.$refs.deptAdminAddOrUpdate.init(id, name, type)
        })
      },
      // 部门信息新增 / 修改
      deptInfoAddOrUpdateHandle (id) {
        this.erDeptInfoAddOrUpdateVisible = true
        this.$nextTick(() => {
          this.$refs.erDeptInfoAddOrUpdate.init(id)
        })
      },
      // 删除
      deleteHandle (id) {
        this.$confirm(`确定对[id=${id}]进行[删除]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl(`/business/sysdept/delete/${id}`),
            method: 'post',
            data: this.$http.adornData()
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.getDataList()
                }
              })
            } else {
              this.$message.error(data.msg)
            }
          })
        }).catch(() => {})
      }
    }
  }
</script>
