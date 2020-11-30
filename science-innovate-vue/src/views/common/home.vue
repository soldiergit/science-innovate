<template>
  <div class="mod-demo-echarts">
    <el-row :gutter="20">
      <el-col :span="8">
        <el-card>
          <div class="content">
            <div class="item">
              <img src="~@/assets/img/college.png" class="itemImage"/>
              <div class="itemText">
                <p>总二级学院数(个)</p>
                <p>{{ bigDataVo.allCollegeSum }}</p>
              </div>
            </div>
            <div class="item">
              <img src="~@/assets/img/lab.png" class="itemImage"/>
              <div class="itemText">
                <p>教研室数(个)</p>
                <p>{{ bigDataVo.allLabSum }}</p>
              </div>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card>
          <div class="content">
            <div class="item">
              <img src="~@/assets/img/business.png" class="itemImage"/>
              <div class="itemText">
                <p>业绩总数(个)</p>
                <p class="sky">{{ bigDataVo.allBusinessSum }}</p>
              </div>
            </div>
            <div class="item">
              <img src="~@/assets/img/add.png" class="itemImage"/>
              <div class="itemText">
                <p>当月新增数(个)</p>
                <p class="sky">{{ bigDataVo.businessNewAddMonthSum }}</p>
              </div>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card>
          <div class="content">
            <div class="item">
              <img src="~@/assets/img/users.png" class="itemImage"/>
              <div class="itemText">
                <p>用户总数(人)</p>
                <p class="org">{{ bigDataVo.allUserSum }}</p>
              </div>
            </div>
            <div class="item">
              <img src="~@/assets/img/teachers.png" class="itemImage"/>
              <div class="itemText">
                <p>教师总数(人)</p>
                <p class="org">{{ bigDataVo.allTeacherSum }}</p>
              </div>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="24">
        <el-card>
          <div id="teacherPerformance" class="chart-box"></div>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card>
          <div id="businessType" class="chart-box"></div>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card>
          <div id="businessArea" class="chart-box"></div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import echarts from 'echarts'

  export default {
    data () {
      return {
        teacherPerformance: null,
        businessType: null,
        businessArea: null,
        bigDataVo: {
          // 总二级学院数
          allCollegeSum: 0,
          // 教研室数
          allLabSum: 0,
          // 业绩总数
          allBusinessSum: 0,
          // 当月新增数
          businessNewAddMonthSum: 0,
          // 用户总数
          allUserSum: 0,
          // 教师总数
          allTeacherSum: 0,
          // 学术论文数
          allAcademicPapersSum: 0,
          // 学生竞赛数
          allStudentCompetitionSum: 0,
          // 教师竞赛数
          allTeacherCompetitionSum: 0,
          // 项目立项数
          allProjectCreateSum: 0,
          // 项目结题数
          allProjectFinishSum: 0,
          // 科研成果数
          allResearchGainSum: 0,
          // 大创项目数
          allInnovateProjectSum: 0,
          // 自编教材管理数
          allTeachingMaterialSum: 0,
          // 教学成果数
          allTeachingAchievementsSum: 0,
          // 教学荣誉数
          allTeachingHonorSum: 0
        }
      }
    },
    activated () {
      this.getBigData()
      // 由于给echart添加了resize事件, 在组件激活时需要重新resize绘画一次, 否则出现空白bug
      if (this.teacherPerformance) {
        this.teacherPerformance.resize()
      }
      if (this.businessType) {
        this.businessType.resize()
      }
      if (this.businessArea) {
        this.businessArea.resize()
      }
    },
    methods: {
      // 获取后台数据
      getBigData () {
        this.$http({
          url: this.$http.adornUrl('/business/bigdata/allSum'),
          method: 'get',
          params: this.$http.adornParams()
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.bigDataVo = data.bigData
            this.initTeacherPerformance()
            this.initBusinessType()
            this.initBusinessArea()
          }
        })
      },
      // 教师业绩-柱状图
      initTeacherPerformance () {
        var option = {
          title: {
            text: '教师业绩',
            left: 'center',
            top: 20
          },
          tooltip: {
            trigger: 'item',
            formatter: '{a}<br/>{b}<br/>{c}个'
          },
          calculable: true,
          xAxis: [{
            type: 'category',
            data: ['学术论文', '学生竞赛', '教师竞赛', '项目立项', '项目结题', '科研成果', '大创项目', '自编教材管理', '教学成果', '教学荣誉']}
          ],
          yAxis: [{
            type: 'value',
            splitLine: {
              'show': false
            },
            axisLabel: {
              formatter: function (value) {
                return value + ''
              }
            }}],
          series: [{
            name: '教师业绩',
            type: 'bar',
            barWidth: 20,
            data: [this.bigDataVo.allAcademicPapersSum, this.bigDataVo.allStudentCompetitionSum, this.bigDataVo.allTeacherCompetitionSum,
              this.bigDataVo.allProjectCreateSum, this.bigDataVo.allProjectFinishSum, this.bigDataVo.allResearchGainSum,
              this.bigDataVo.allInnovateProjectSum, this.bigDataVo.allTeachingMaterialSum, this.bigDataVo.allTeachingAchievementsSum,
              this.bigDataVo.allTeachingHonorSum
            ]}]
        }
        this.teacherPerformance = echarts.init(document.getElementById('teacherPerformance'))
        this.teacherPerformance.setOption(option)
        window.addEventListener('resize', () => {
          this.teacherPerformance.resize()
        })
      },
      // 业务类型-饼状图
      initBusinessType () {
        var option = {
          // 背景颜色
          backgroundColor: '#2c343c',
          title: {
            text: '业务类型',
            left: 'center',
            top: 20,
            textStyle: {color: '#ccc'}
          },
          tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
          },
          visualMap: {
            show: false,
            min: 80,
            max: 600,
            inRange: {colorLightness: [0, 1]}
          },
          legend: {
            type: 'scroll',
            orient: 'vertical',
            right: 10,
            top: 20,
            bottom: 20,
            data: ['学术论文', '学生竞赛', '教师竞赛', '项目立项', '项目结题', '科研成果', '大创项目', '自编教材管理', '教学成果', '教学荣誉'],
            textStyle: {color: '#ccc'}
          },
          series: [{
            name: '业务类型',
            type: 'pie',
            radius: '55%',
            center: ['50%', '50%'],
            avoidLabelOverlap: false,
            data: [
              {value: this.bigDataVo.allAcademicPapersSum, name: '学术论文'},
              {value: this.bigDataVo.allStudentCompetitionSum, name: '学生竞赛'},
              {value: this.bigDataVo.allTeacherCompetitionSum, name: '教师竞赛'},
              {value: this.bigDataVo.allProjectCreateSum, name: '项目立项'},
              {value: this.bigDataVo.allProjectFinishSum, name: '项目结题'},
              {value: this.bigDataVo.allResearchGainSum, name: '科研成果'},
              {value: this.bigDataVo.allInnovateProjectSum, name: '大创项目'},
              {value: this.bigDataVo.allTeachingMaterialSum, name: '自编教材管理'},
              {value: this.bigDataVo.allTeachingAchievementsSum, name: '教学成果'},
              {value: this.bigDataVo.allTeachingHonorSum, name: '教学荣誉'}
            ].sort(function (a, b) { return a.value - b.value }),
            roseType: 'radius',
            itemStyle: {
              emphasis: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }}],
          color: ['#F3DB5D', '#009AFF', '#F77474', '#28DCCF', '#FF5937']
        }
        this.businessType = echarts.init(document.getElementById('businessType'))
        this.businessType.setOption(option)
        window.addEventListener('resize', () => {
          this.businessType.resize()
        })
      },
      // 业务类型-业务数据折线图
      initBusinessArea () {
        var option = {
          backgroundColor: new echarts.graphic.RadialGradient(0.3, 0.3, 0.8, [
            { offset: 0, color: '#f7f8fa' },
            { offset: 1, color: '#cdd0d5' }
          ]),
          title: {
            left: 'center',
            text: '业务数据折线图'
          },
          xAxis: {
            type: 'category',
            data: ['学术论文', '学生竞赛', '教师竞赛', '项目立项', '项目结题', '科研成果', '大创项目', '自编教材管理', '教学成果', '教学荣誉']
          },
          yAxis: {type: 'value'},
          series: [{
            type: 'line',
            smooth: true,
            data: [
              {value: this.bigDataVo.allAcademicPapersSum, name: '学术论文'},
              {value: this.bigDataVo.allStudentCompetitionSum, name: '学生竞赛'},
              {value: this.bigDataVo.allTeacherCompetitionSum, name: '教师竞赛'},
              {value: this.bigDataVo.allProjectCreateSum, name: '项目立项'},
              {value: this.bigDataVo.allProjectFinishSum, name: '项目结题'},
              {value: this.bigDataVo.allResearchGainSum, name: '科研成果'},
              {value: this.bigDataVo.allInnovateProjectSum, name: '大创项目'},
              {value: this.bigDataVo.allTeachingMaterialSum, name: '自编教材管理'},
              {value: this.bigDataVo.allTeachingAchievementsSum, name: '教学成果'},
              {value: this.bigDataVo.allTeachingHonorSum, name: '教学荣誉'}
            ]}]
        }
        this.businessArea = echarts.init(document.getElementById('businessArea'))
        this.businessArea.setOption(option)
        window.addEventListener('resize', () => {
          this.businessArea.resize()
        })
      }
    }
  }
</script>

<style lang="scss">
  .mod-demo-echarts {
    > .el-row {
      margin-top: -10px;
      margin-bottom: -10px;
      .el-col {
        padding-top: 10px;
        padding-bottom: 10px;
      }
    }
    .chart-box {
      min-height: 400px;
    }
    * {
      margin: 0;
      padding: 0;
      list-style: none;
      text-decoration: none;
    }
    .content {
      height: 110px;
      width: 100%;
      float: left;
      background-color: #034c6a;
      margin-right: 1.3%;
    }
    .item {
      float: left;
      width: 50%;
      height: 100%;
    }
    .itemImage {
      float: left;
      width: 40px;
      height: 40px;
      margin-left: 5%;
      margin-top: 35px;
    }
    .itemText {
      float: left;
      margin-top: 15px;
      margin-left: 5%;
      text-align: center;
    }
    p {
      line-height: 35px;
    }
    p:nth-child(1) {
      font-size: 13px;
      color: #ffffff;
    }
    p:nth-child(2) {
      font-size: 28px;
      color: #ffff43;
      font-weight: 600;
    }
    .sky {
      color: #1296db !important;
    }

    .org {
      color: #ff4e4e !important;
    }
  }
</style>
