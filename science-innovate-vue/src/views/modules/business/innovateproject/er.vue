<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input prefix-icon="el-icon-search" v-model="dataForm.key" placeholder="项目名称或项目组成员" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-date-picker
          v-model="dataForm.checkTime"
          align="right"
          type="year"
          placeholder="请选择年度">
        </el-date-picker>
        <el-select v-model="dataForm.grade" clearable placeholder="请选择立项级别">
          <el-option v-for="item in competitionGradeList" :key="item.value" :label="item.label" :value="item.value"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
        <el-button v-if="isAuth('business:innovateproject:delete')" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
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
        prop="money"
        header-align="center"
        align="center"
        label="经费/万元">
      </el-table-column>
      <el-table-column
        prop="entryStu"
        header-align="center"
        align="center"
        label="项目组成员"
        :show-overflow-tooltip="true">
      </el-table-column>
      <el-table-column
        prop="awardTime"
        header-align="center"
        align="center"
        label="立项时间">
        <template slot-scope="scope">
          <el-tag v-text="scope.row.awardTime.substring(0,4) + '年'" size="small"></el-tag>
        </template>
      </el-table-column>
      <el-table-column
        prop="grade"
        sortable
        header-align="center"
        align="center"
        label="立项级别"
        :show-overflow-tooltip="true">
        <template slot-scope="scope">
          <template v-for="item in competitionGradeList">
            <span v-if="scope.row.grade === item.value" v-text="item.label"></span>
          </template>
        </template>
      </el-table-column>
      <el-table-column
        prop="personName"
        header-align="center"
        align="center"
        label="第一指导老师">
        <template slot-scope="scope">
          <el-tag v-text="scope.row.personInfo.name" size="small" type="danger"></el-tag>
        </template>
      </el-table-column>
      <el-table-column
        prop="membersName"
        header-align="center"
        align="center"
        label="其它指导老师"
        :show-overflow-tooltip="true">
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="detailHandle(scope.row.ipId)">详情</el-button>
          <el-button type="text" style="color: red" size="small" @click="deleteHandl(scope.row.ipId)">删除</el-button>
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
    <!-- 弹窗 详情 -->
    <detail v-if="detailVisible" ref="detail" @refreshDataList="getDataList"></detail>
  </div>
</template>

<script>
  import Detail from './details'
  export default {
    data () {
      return {
        dataForm: {
          key: '',
          checkTime: '',
          grade: '',
          rank: ''
        },
        competitionGradeList: this.$store.state.user.competitionGradeEntities,
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
        detailVisible: false
      }
    },
    components: {
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
          url: this.$http.adornUrl('/business/innovateproject/list'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'key': this.dataForm.key,
            'userId': this.$store.state.user.id,
            'checkTime': this.dataForm.checkTime ? this.dataForm.checkTime.getFullYear() : null,
            'grade': this.dataForm.grade
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
      // 删除
      deleteHandle (id) {
        var ids = id ? [id] : this.dataListSelections.map(item => {
          return item.ipId
        })
        this.$confirm(`确定进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/business/innovateproject/delete'),
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
