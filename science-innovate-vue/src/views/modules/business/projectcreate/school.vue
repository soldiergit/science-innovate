<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input prefix-icon="el-icon-search" v-model="dataForm.key" placeholder="项目名称" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-date-picker
          v-model="dataForm.checkTime"
          align="right"
          type="year"
          placeholder="请选择年度">
        </el-date-picker>
        <el-select v-model="dataForm.type" clearable placeholder="请选择项目类型">
          <el-option v-for="item in typeList" :key="item.value" :label="item.label" :value="item.value"></el-option>
        </el-select>
        <el-select v-model="dataForm.category" clearable placeholder="请选择项目类别">
          <el-option v-for="item in projectCategoryList" :key="item.value" :label="item.label" :value="item.value"></el-option>
        </el-select>
        <el-select v-model="dataForm.level" clearable placeholder="请选择项目级别">
          <el-option v-for="item in projectLevelList" :key="item.value" :label="item.label" :value="item.value"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
        <el-button v-if="isAuth('business:projectcreate:delete')" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除
        </el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      @selection-change="selectionChangeHandle"
      style="width: 100%;">
      <el-table-column
        type="selection"
        header-align="center"
        align="center"
        width="50">
      </el-table-column>
      <el-table-column
        type="index"
        header-align="center"
        align="center"
        width="50">
      </el-table-column>
      <el-table-column
        prop="name"
        header-align="center"
        align="center"
        label="项目名称"
        :show-overflow-tooltip="true">
      </el-table-column>
      <el-table-column
        prop="personName"
        header-align="center"
        align="center"
        label="主持人">
        <template slot-scope="scope">
          <el-tag v-text="scope.row.personInfo.name" size="small" type="danger"></el-tag>
        </template>
      </el-table-column>
      <el-table-column
        prop="membersName"
        header-align="center"
        align="center"
        label="成员"
        :show-overflow-tooltip="true">
      </el-table-column>
      <el-table-column
        prop="number"
        header-align="center"
        align="center"
        label="项目编号"
        :show-overflow-tooltip="true">
      </el-table-column>
      <el-table-column
        prop="type"
        header-align="center"
        align="center"
        label="项目类型">
        <template slot-scope="scope">
          <template v-for="item in typeList">
            <el-tag v-if="scope.row.type === item.value" size="small" v-text="item.label"></el-tag>
          </template>
        </template>
      </el-table-column>
      <el-table-column
        prop="category"
        header-align="center"
        align="center"
        label="项目类别"
        :show-overflow-tooltip="true">
        <template slot-scope="scope">
          <template v-for="item in projectCategoryList">
            <span v-if="scope.row.category === item.value" v-text="item.label"></span>
          </template>
        </template>
      </el-table-column>
      <el-table-column
        prop="level"
        header-align="center"
        align="center"
        label="项目级别"
        :show-overflow-tooltip="true">
        <template slot-scope="scope">
          <template v-for="item in projectLevelList">
            <span v-if="scope.row.level === item.value" v-text="item.label"></span>
          </template>
        </template>
      </el-table-column>
      <el-table-column
        prop="money"
        header-align="center"
        align="center"
        label="经费/万元">
      </el-table-column>
      <el-table-column
        prop="createTime"
        header-align="center"
        align="center"
        label="立项时间">
        <template slot-scope="scope">
          <el-tag v-text="scope.row.createTime.substring(0,4) + '年'" size="small"></el-tag>
        </template>
      </el-table-column>
      <el-table-column
        prop="startTime"
        header-align="center"
        align="center"
        label="开始时间">
        <template slot-scope="scope">
          <el-tag v-text="scope.row.startTime.substring(0,10)" size="small"></el-tag>
        </template>
      </el-table-column>
      <el-table-column
        prop="endTime"
        header-align="center"
        align="center"
        label="结束时间">
        <template slot-scope="scope">
          <el-tag v-text="scope.row.endTime.substring(0,10)" size="small"></el-tag>
        </template>
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="detailHandle(scope.row.pcId)">详情</el-button>
          <el-button type="text" style="color: red" size="small" @click="deleteHandl(scope.row.pcId)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="sizeChangeHandle"
      @current-change="currentChangeHandle"
      :current-page="pageIndex"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="pageSize"
      :total="totalPage"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
    <detail v-if="detailVisible" ref="detail" @refreshDataList="getDataList"></detail>
  </div>
</template>

<script>
  import AddOrUpdate from './info-add-or-update'
  import Detail from './details'

  export default {
    data () {
      return {
        dataForm: {
          key: '',
          checkTime: '',
          type: '',
          category: '',
          level: ''
        },
        typeList: [
          {value: 1, label: '科研类'},
          {value: 2, label: '教改类'}
        ],
        projectCategoryList: this.$store.state.user.projectCategoryEntities,
        projectLevelList: this.$store.state.user.projectLevelEntities,
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
        addOrUpdateVisible: false,
        detailVisible: false
      }
    },
    components: {
      AddOrUpdate,
      Detail
    },
    activated () {
      this.getDataList()
    },
    methods: {
      // 获取数据列表
      getDataList () {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/business/projectcreate/list'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'key': this.dataForm.key,
            'userId': this.$store.state.user.id,
            'checkTime': this.dataForm.checkTime ? this.dataForm.checkTime.getFullYear() : null,
            'type': this.dataForm.type,
            'category': this.dataForm.category,
            'level': this.dataForm.level
          })
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.dataList = data.page.list
            this.totalPage = data.page.totalCount
          } else {
            this.dataList = []
            this.totalPage = 0
          }
          this.dataListLoading = false
        })
      },
      // 每页数
      sizeChangeHandle (val) {
        this.pageSize = val
        this.pageIndex = 1
        this.getDataList()
      },
      // 当前页
      currentChangeHandle (val) {
        this.pageIndex = val
        this.getDataList()
      },
      // 多选
      selectionChangeHandle (val) {
        this.dataListSelections = val
      },
      // 新增 / 修改
      addOrUpdateHandle (id) {
        this.addOrUpdateVisible = true
        this.$nextTick(() => {
          this.$refs.addOrUpdate.init(id)
        })
      },
      // 删除
      deleteHandle (id) {
        var ids = id ? [id] : this.dataListSelections.map(item => {
          return item.pcId
        })
        this.$confirm(`确定进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/business/projectcreate/delete'),
            method: 'post',
            data: this.$http.adornData(ids, false)
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
        })
      },
      // 详情
      detailHandle (id) {
        this.detailVisible = true
        this.$nextTick(() => {
          this.$refs.detail.init(id)
        })
      }
    }
  }
</script>
