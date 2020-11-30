<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input prefix-icon="el-icon-search" v-model="dataForm.key" placeholder="成果名称" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-date-picker
          v-model="dataForm.checkTime"
          align="right"
          type="year"
          placeholder="请选择年度">
        </el-date-picker>
        <el-select v-model="dataForm.grade" clearable placeholder="请选择获奖级别">
          <el-option v-for="item in competitionGradeList" :key="item.value" :label="item.label" :value="item.value"></el-option>
        </el-select>
        <el-select v-model="dataForm.rank" clearable placeholder="请选择获奖等次">
          <el-option v-for="item in competitionRankList" :key="item.value" :label="item.label" :value="item.value"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
        <!--<el-button v-if="isAuth('business:teachingachievements:save')" type="primary" @click="addOrUpdateHandle()">新增</el-button>-->
        <el-button v-if="isAuth('business:teachingachievements:delete')" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
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
        label="成果名称">
      </el-table-column>
      <el-table-column
        prop="awardTime"
        header-align="center"
        align="center"
        label="获奖时间">
        <template slot-scope="scope">
          <el-tag v-text="scope.row.awardTime.substring(0,4) + '年'" size="small"></el-tag>
        </template>
      </el-table-column>
      <el-table-column
        prop="grade"
        sortable
        header-align="center"
        align="center"
        label="获奖级别"
        :show-overflow-tooltip="true">
        <template slot-scope="scope">
          <template v-for="item in competitionGradeList">
            <span v-if="scope.row.grade === item.value" v-text="item.label"></span>
          </template>
        </template>
      </el-table-column>
      <el-table-column
        prop="rank"
        sortable
        header-align="center"
        align="center"
        label="获奖等次"
        :show-overflow-tooltip="true">
        <template slot-scope="scope">
          <template v-for="item in competitionRankList">
            <span v-if="scope.row.rank === item.value" v-text="item.label"></span>
          </template>
        </template>
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
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="detailHandle(scope.row.taId)">详情</el-button>
          <!--<el-button type="text" size="small" @click="addOrUpdateHandle(scope.row.taId)">修改</el-button>-->
          <el-button type="text" style="color: red" size="small" @click="deleteHandl(scope.row.taId)">删除</el-button>
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
          grade: '',
          rank: ''
        },
        competitionGradeList: this.$store.state.user.competitionGradeEntities,
        competitionRankList: this.$store.state.user.competitionRankEntities,
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
          url: this.$http.adornUrl('/business/teachingachievements/list'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'userId': this.$store.state.user.id,
            'key': this.dataForm.key,
            'checkTime': this.dataForm.checkTime ? this.dataForm.checkTime.getFullYear() : null,
            'grade': this.dataForm.grade,
            'rank': this.dataForm.rank
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
          return item.taId
        })
        this.$confirm(`确定进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/business/teachingachievements/delete'),
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
