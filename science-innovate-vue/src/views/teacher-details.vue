<template>
  <el-dialog
    width="70%"
    v-loading="dataFormLoading"
    :close-on-click-modal="false"
    :visible.sync="visible"
    :append-to-body="true"
    @open="activeName = 'teacherInfoEntity'">
    <!--重置显示的pane 每次都显示教师信息页面-->
    <el-tabs v-model="activeName" type="border-card" @tab-click="handleClick">
      <el-tab-pane label="教师基本信息" name="teacherInfoEntity">
        <table border="1" cellspacing="0" width="100%" class="table">
          <tr align='center'>
            <td colspan="7" style="height: 1.2rem"></td>
          </tr>
          <tr class="contents" align="center">
            <th colspan="7">个人基本信息</th>
          </tr>
          <tr align='center'>
            <td colspan="7" style="height: 1.2rem"></td>
          </tr>
          <tr align='center' style="height: 2.5rem">
            <th>姓名</th>
            <td><span v-text="teacherInfoEntity.name"></span></td>
            <th>工号</th>
            <td><span v-text="teacherInfoEntity.teacherCode"></span></td>
            <th>性别</th>
            <td><span v-text="teacherInfoEntity.sex"></span></td>
            <th rowspan="4">
              <span>
                <img v-if="src" align='center' :src="src" style="width: 150px;height: 150px"/>
                <img v-else align='center' src="~@/assets/img/avatar.png" style="width: 150px;height: 150px"/>
              </span></th>
          </tr>

          <tr align='center' style="height: 2.5rem">
            <th>出生年月</th>
            <td><span v-if="teacherInfoEntity.birthDay" v-text="teacherInfoEntity.birthDay.substring(0,10)"></span></td>

            <th>入职时间</th>
            <td><span v-text="teacherInfoEntity.entryTime"></span></td>

            <th>最高学历</th>
            <td><span v-text="teacherInfoEntity.highEdu"></span></td>
          </tr>
          <tr align='center' style="height: 2.5rem">
            <th>第一学历</th>
            <td colspan="2"><span v-text="teacherInfoEntity.firstEdu"></span></td>

            <th>专业技术职称</th>
            <td colspan="2"><span v-text="teacherInfoEntity.technicalPost"></span></td>
          </tr>
          <tr align="center" style="height: 2.5rem">
            <th>行政职务</th>
            <td colspan="2"><span v-text="teacherInfoEntity.administerPost"></span></td>

            <th>简历</th>
            <td colspan="2"><span v-text="teacherInfoEntity.resume"></span></td>
          </tr>
          <tr align="center" style="height: 2.5rem">
            <th>主要从事工作及研究方向</th>
            <td colspan="6"><span v-text="teacherInfoEntity.other"></span></td>
          </tr>
        </table>
      </el-tab-pane>
      <el-tab-pane label="学术论文" name="academicPapersDataList">
        <el-table
          :data="academicPapersDataList"
          border
          v-loading="dataListLoading"
          style="width: 100%;"
          id="academicPapersDataList">
          <el-table-column
            prop="name"
            header-align="center"
            align="center"
            label="论文名称"
            :show-overflow-tooltip="true">
          </el-table-column>
          <el-table-column
            prop="periodicalName"
            header-align="center"
            align="center"
            label="发布期刊名称"
            :show-overflow-tooltip="true">
          </el-table-column>
          <el-table-column
            prop="projectName"
            header-align="center"
            align="center"
            label="归属项目名及编号"
            :show-overflow-tooltip="true">
          </el-table-column>
          <el-table-column
            prop="authorType"
            header-align="center"
            align="center"
            label="作者类型">
            <template slot-scope="scope">
              <template v-for="item in authorTypeList">
                <el-tag v-if="scope.row.authorType === item.value" size="small" v-text="item.label"></el-tag>
              </template>
            </template>
          </el-table-column>
          <el-table-column
            prop="paperType"
            header-align="center"
            align="center"
            label="论文类型">
            <template slot-scope="scope">
              <template v-for="item in paperTypeList">
                <el-tag v-if="scope.row.paperType === item.value" size="small" v-text="item.label"></el-tag>
              </template>
            </template>
          </el-table-column>
          <el-table-column
            prop="grade"
            sortable
            header-align="center"
            align="center"
            label="论文等级"
            :show-overflow-tooltip="true">
            <template slot-scope="scope">
              <template v-for="item in papersGradeList">
                <span v-if="scope.row.grade === item.value" v-text="item.label"></span>
              </template>
            </template>
          </el-table-column>
          <el-table-column
            prop="publishTime"
            header-align="center"
            align="center"
            label="发表时间">
            <template slot-scope="scope">
              <el-tag v-text="scope.row.publishTime.substring(0,4) + '年'" size="small"></el-tag>
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
            prop="type"
            header-align="center"
            align="center"
            label="教师类型">
            <template slot-scope="scope">
              <el-tag v-if="scope.row.type === 1" type="primary" size="small">主持人</el-tag>
              <el-tag v-if="scope.row.type === 2" type="danger" size="small">成员</el-tag>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane label="学生竞赛" name="studentCompetitionDataList">
        <el-table
          :data="studentCompetitionDataList"
          border
          v-loading="dataListLoading"
          style="width: 100%;"
          id="studentCompetitionDataList">
          <el-table-column
            prop="matchName"
            header-align="center"
            align="center"
            label="赛事名称"
            :show-overflow-tooltip="true">
            <template slot-scope="scope">
              <span v-text="scope.row.matchInfoEntity.name" size="small"></span>
            </template>
          </el-table-column>
          <el-table-column
            prop="worksName"
            header-align="center"
            align="center"
            label="作品名称"
            :show-overflow-tooltip="true">
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
            prop="prizeTime"
            header-align="center"
            align="center"
            label="获奖时间">
            <template slot-scope="scope">
              <el-tag v-text="scope.row.prizeTime.substring(0,4) + '年'" size="small"></el-tag>
            </template>
          </el-table-column>
          <el-table-column
            prop="entryStu"
            header-align="center"
            align="center"
            label="参赛学生"
            :show-overflow-tooltip="true">
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
            prop="type"
            header-align="center"
            align="center"
            label="指导老师类型">
            <template slot-scope="scope">
              <el-tag v-if="scope.row.type === 1" type="primary" size="small">第一指导老师</el-tag>
              <el-tag v-if="scope.row.type === 2" type="danger" size="small">其它指导老师</el-tag>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane label="教师竞赛" name="teacherCompetitionDataList">
        <el-table
          :data="teacherCompetitionDataList"
          border
          v-loading="dataListLoading"
          style="width: 100%;"
          id="teacherCompetitionDataList">
          <el-table-column
            prop="matchName"
            header-align="center"
            align="center"
            label="赛事名称"
            :show-overflow-tooltip="true">
            <template slot-scope="scope">
              <span v-text="scope.row.matchInfoEntity.name" size="small"></span>
            </template>
          </el-table-column>
          <el-table-column
            prop="name"
            header-align="center"
            align="center"
            label="竞赛名称">
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
            prop="prizeTime"
            header-align="center"
            align="center"
            label="获奖时间">
            <template slot-scope="scope">
              <el-tag v-text="scope.row.prizeTime.substring(0,4) + '年'" size="small"></el-tag>
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
            prop="type"
            header-align="center"
            align="center"
            label="教师类型">
            <template slot-scope="scope">
              <el-tag v-if="scope.row.type === 1" type="primary" size="small">队长</el-tag>
              <el-tag v-if="scope.row.type === 2" type="danger" size="small">成员</el-tag>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane label="项目立项" name="projectCreateDataList">
        <el-table
          :data="projectCreateDataList"
          border
          v-loading="dataListLoading"
          style="width: 100%;"
          id="projectCreateDataList">
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
            prop="teacherType"
            header-align="center"
            align="center"
            label="教师类型">
            <template slot-scope="scope">
              <el-tag v-if="scope.row.teacherType === 1" type="primary" size="small">主持人</el-tag>
              <el-tag v-if="scope.row.teacherType === 2" type="danger" size="small">成员</el-tag>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane label="项目结题" name="projectFinishDataList">
        <el-table
          :data="projectFinishDataList"
          border
          v-loading="dataListLoading"
          style="width: 100%;"
          id="projectFinishDataList">
          <el-table-column
            prop="pcId"
            header-align="center"
            align="center"
            label="立项项目">
            <template slot-scope="scope">
              <el-tag v-text="scope.row.projectCreateEntity.name" size="small"></el-tag>
            </template>
          </el-table-column>
          <el-table-column
            prop="teaId"
            header-align="center"
            align="center"
            label="结题教师">
            <template slot-scope="scope">
              <el-tag v-text="scope.row.teacherInfoEntity.name" size="small"></el-tag>
            </template>
          </el-table-column>
          <el-table-column
            prop="finishTime"
            header-align="center"
            align="center"
            label="结题时间">
            <template slot-scope="scope">
              <el-tag v-text="scope.row.finishTime.substring(0,10)" size="small"></el-tag>
            </template>
          </el-table-column>
          <el-table-column
            prop="remarks"
            header-align="center"
            align="center"
            label="备注">
          </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane label="科研成果" name="researchGainDataList">
        <el-table
          :data="researchGainDataList"
          border
          v-loading="dataListLoading"
          style="width: 100%;"
          id="researchGainDataList">
          <el-table-column
            prop="name"
            header-align="center"
            align="center"
            label="成果名称">
          </el-table-column>
          <el-table-column
            prop="entryStu"
            header-align="center"
            align="center"
            label="参与学生"
            :show-overflow-tooltip="true">
          </el-table-column>
          <el-table-column
            prop="awardTime"
            header-align="center"
            align="center"
            label="获得时间">
            <template slot-scope="scope">
              <el-tag v-text="scope.row.awardTime.substring(0,4) + '年'" size="small"></el-tag>
            </template>
          </el-table-column>
          <el-table-column
            prop="gain"
            header-align="center"
            align="center"
            label="成果类型">
            <template slot-scope="scope">
              <template v-for="item in gainTypeList">
                <el-tag v-if="scope.row.gain === item.value" size="small" v-text="item.label"></el-tag>
              </template>
            </template>
          </el-table-column>
          <el-table-column
            prop="grade"
            sortable
            header-align="center"
            align="center"
            label="成果级别"
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
            label="成果等次"
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
            prop="type"
            header-align="center"
            align="center"
            label="教师类型">
            <template slot-scope="scope">
              <el-tag v-if="scope.row.type === 1" type="primary" size="small">主持人</el-tag>
              <el-tag v-if="scope.row.type === 2" type="danger" size="small">成员</el-tag>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane label="大创项目" name="innovateProjectDataList">
        <el-table
          :data="innovateProjectDataList"
          border
          v-loading="dataListLoading"
          style="width: 100%;"
          id="innovateProjectDataList">
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
            prop="type"
            header-align="center"
            align="center"
            label="教师类型">
            <template slot-scope="scope">
              <el-tag v-if="scope.row.type === 1" type="primary" size="small">第一著作权人</el-tag>
              <el-tag v-if="scope.row.type === 2" type="danger" size="small">成员</el-tag>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane label="自编教材管理" name="teachingMaterialDataList">
        <el-table
          :data="teachingMaterialDataList"
          border
          v-loading="dataListLoading"
          style="width: 100%;"
          id="teachingMaterialDataList">
          <el-table-column
            prop="name"
            header-align="center"
            align="center"
            label="教材名称">
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
            prop="press"
            header-align="center"
            align="center"
            label="出版社">
          </el-table-column>
          <el-table-column
            prop="publishTime"
            header-align="center"
            align="center"
            label="出版时间">
            <template slot-scope="scope">
              <el-tag v-text="scope.row.publishTime.substring(0,10)" size="small"></el-tag>
            </template>
          </el-table-column>
          <el-table-column
            prop="remarks"
            header-align="center"
            align="center"
            label="备注">
          </el-table-column>
          <el-table-column
            prop="type"
            header-align="center"
            align="center"
            label="教师类型">
            <template slot-scope="scope">
              <el-tag v-if="scope.row.type === 1" type="primary" size="small">主编</el-tag>
              <el-tag v-if="scope.row.type === 2" type="danger" size="small">成员</el-tag>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane label="教学成果" name="teachingAchievementsDataList">
        <el-table
          :data="teachingAchievementsDataList"
          border
          v-loading="dataListLoading"
          style="width: 100%;"
          id="teachingAchievementsDataList">
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
            prop="type"
            header-align="center"
            align="center"
            label="教师类型">
            <template slot-scope="scope">
              <el-tag v-if="scope.row.type === 1" type="primary" size="small">主持人</el-tag>
              <el-tag v-if="scope.row.type === 2" type="danger" size="small">成员</el-tag>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane label="教学荣誉" name="teachingHonorDataList">
        <el-table
          :data="teachingHonorDataList"
          border
          v-loading="dataListLoading"
          style="width: 100%;"
          id="teachingHonorDataList">
          <el-table-column
            prop="name"
            header-align="center"
            align="center"
            label="荣誉称号">
          </el-table-column>
          <el-table-column
            prop="grantUnit"
            header-align="center"
            align="center"
            label="授予单位">
          </el-table-column>
          <el-table-column
            prop="honorTime"
            header-align="center"
            align="center"
            label="授予时间">
            <template slot-scope="scope">
              <el-tag v-text="scope.row.honorTime.substring(0,10)" size="small"></el-tag>
            </template>
          </el-table-column>
          <el-table-column
            prop="grade"
            sortable
            header-align="center"
            align="center"
            label="授予等级"
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
            label="授予等次"
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
            prop="type"
            header-align="center"
            align="center"
            label="教师类型">
            <template slot-scope="scope">
              <el-tag v-if="scope.row.type === 1" type="primary" size="small">主持人</el-tag>
              <el-tag v-if="scope.row.type === 2" type="danger" size="small">成员</el-tag>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
    </el-tabs>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">关闭</el-button>
      <el-button type="warning" icon="el-icon-download" @click="exportXlsxHandle">导出数据</el-button>
    </span>
  </el-dialog>
</template>

<script>
  import FileSaver from 'file-saver'
  import XLSX from 'xlsx'

  export default {
    data () {
      return {
        visible: false,
        dataFormLoading: false,
        dataListLoading: false,
        // 默认选中教师信息
        activeName: 'teacherInfoEntity',
        // 教师信息
        src: '',
        teacherInfoEntity: {
          teacherId: 0,
          userId: 0,
          teacherCode: '',
          name: '',
          sex: '',
          birthDay: '',
          entryTime: '',
          highEdu: '',
          firstEdu: '',
          technicalPost: '',
          administerPost: '',
          resume: '',
          other: ''
        },
        // 学术论文
        academicPapersDataList: [],
        // 学生竞赛
        studentCompetitionDataList: [],
        // 教师竞赛
        teacherCompetitionDataList: [],
        // 项目立项
        projectCreateDataList: [],
        // 项目结题
        projectFinishDataList: [],
        // 科研成果
        researchGainDataList: [],
        // 大创项目
        innovateProjectDataList: [],
        // 自编教材管理
        teachingMaterialDataList: [],
        // 教学成果
        teachingAchievementsDataList: [],
        // 教学荣誉
        teachingHonorDataList: [],
        // 记录标签页是否被选中过
        academicPapersClick: false,
        studentCompetitionClick: false,
        teacherCompetitionClick: false,
        projectCreateClick: false,
        projectFinishClick: false,
        researchGainClick: false,
        innovateProjectClick: false,
        teachingMaterialClick: false,
        teachingAchievementsClick: false,
        teachingHonorClick: false,
        authorTypeList: [
          {value: 1, label: '第一作者'},
          {value: 2, label: '通讯作者'},
          {value: 3, label: '独立作者'},
          {value: 4, label: '其他'}
        ],
        paperTypeList: [
          {value: 1, label: '教改类'},
          {value: 2, label: '科研类'}
        ],
        gainTypeList: this.$store.state.user.gainTypeEntities,
        papersGradeList: this.$store.state.user.papersGradeEntities,
        competitionGradeList: this.$store.state.user.competitionGradeEntities,
        competitionRankList: this.$store.state.user.competitionRankEntities,
        typeList: [
          {value: 1, label: '科研类'},
          {value: 2, label: '教改类'}
        ],
        projectCategoryList: this.$store.state.user.projectCategoryEntities,
        projectLevelList: this.$store.state.user.projectLevelEntities,
        pageIndex: 1,
        pageSize: 1000
      }
    },
    methods: {
      init (id) {
        this.visible = true
        this.dataFormLoading = true
        this.teacherInfoEntity.userId = id || 0
        // 重置数据
        this.academicPapersDataList = []
        this.studentCompetitionDataList = []
        this.teacherCompetitionDataList = []
        this.projectCreateDataList = []
        this.projectFinishDataList = []
        this.researchGainDataList = []
        this.innovateProjectDataList = []
        this.teachingMaterialDataList = []
        this.teachingAchievementsDataList = []
        this.teachingHonorDataList = []
        this.academicPapersClick = false
        this.studentCompetitionClick = false
        this.teacherCompetitionClick = false
        this.projectCreateClick = false
        this.projectFinishClick = false
        this.researchGainClick = false
        this.innovateProjectClick = false
        this.teachingMaterialClick = false
        this.teachingAchievementsClick = false
        this.teachingHonorClick = false
        if (this.teacherInfoEntity.userId) {
          this.$http({
            url: this.$http.adornUrl(`/business/teacherinfo/myInfo/${this.teacherInfoEntity.userId}`),
            method: 'get',
            params: this.$http.adornParams()
          }).then(({data}) => {
            if (data && data.code === 0) {
              // this.teacherInfoEntity.userId = data.teacherInfo.userId
              this.teacherInfoEntity.teacherId = data.teacherInfo.teacherId
              this.teacherInfoEntity.teacherCode = data.teacherInfo.teacherCode
              this.teacherInfoEntity.name = data.teacherInfo.name
              this.teacherInfoEntity.sex = data.teacherInfo.sex
              this.teacherInfoEntity.imgPath = data.teacherInfo.imgPath
              this.teacherInfoEntity.birthDay = data.teacherInfo.birthDay
              this.teacherInfoEntity.entryTime = data.teacherInfo.entryTime
              this.teacherInfoEntity.highEdu = data.teacherInfo.highEdu
              this.teacherInfoEntity.firstEdu = data.teacherInfo.firstEdu
              this.teacherInfoEntity.technicalPost = data.teacherInfo.technicalPost
              this.teacherInfoEntity.administerPost = data.teacherInfo.administerPost
              this.teacherInfoEntity.resume = data.teacherInfo.resume
              this.teacherInfoEntity.other = data.teacherInfo.other
              // 获取照片
              this.getPortrait()
              this.dataFormLoading = false
            }
          })
        } else {
          this.dataFormLoading = false
        }
      },
      getPortrait () {
        // 获取照片
        if (this.teacherInfoEntity.imgPath) {
          this.src = this.$http.adornUrl(`/business/teacherinfo/portrait?imgPath=${this.teacherInfoEntity.imgPath}`)
        }
      },
      handleClick (tab, event) {
        let tabName = tab.name
        let tabIndex = tab.index
        // 如果标签页没被点击过 则渲染数据
        if (!this.academicPapersClick && (tabName === 'academicPapersDataList' || tabIndex === 1)) {
          // console.log('学术论文')
          this.academicPapersClick = true
          this.getAcademicPapersDataList()
        } else if (!this.studentCompetitionClick && (tabName === 'studentCompetitionDataList' || tabIndex === 2)) {
          // console.log('学生竞赛')
          this.studentCompetitionClick = true
          this.getStudentCompetitionDataList()
        } else if (!this.teacherCompetitionClick && (tabName === 'teacherCompetitionDataList' || tabIndex === 3)) {
          // console.log('教师竞赛')
          this.teacherCompetitionClick = true
          this.getTeacherCompetitionDataList()
        } else if (!this.projectCreateClick && (tabName === 'projectCreateDataList' || tabIndex === 4)) {
          // console.log('项目立项')
          this.projectCreateClick = true
          this.getProjectCreateDataList()
        } else if (!this.projectFinishClick && (tabName === 'projectFinishDataList' || tabIndex === 5)) {
          // console.log('项目结题')
          this.projectFinishClick = true
          this.getProjectFinishDataList()
        } else if (!this.researchGainClick && (tabName === 'researchGainDataList' || tabIndex === 6)) {
          // console.log('科研成果')
          this.researchGainClick = true
          this.getPatentAchievementsDataList()
        } else if (!this.innovateProjectClick && (tabName === 'innovateProjectDataList' || tabIndex === 7)) {
          // console.log('大创项目')
          this.innovateProjectClick = true
          this.getSoftwareCopyrightDataList()
        } else if (!this.teachingMaterialClick && (tabName === 'teachingMaterialDataList' || tabIndex === 8)) {
          // console.log('自编教材管理')
          this.teachingMaterialClick = true
          this.getTeachingMaterialDataList()
        } else if (!this.teachingAchievementsClick && (tabName === 'teachingAchievementsDataList' || tabIndex === 9)) {
          // console.log('教学成果')
          this.teachingAchievementsClick = true
          this.getTeachingAchievementsDataList()
        } else if (!this.teachingHonorClick && (tabName === 'teachingHonorDataList' || tabIndex === 10)) {
          // console.log('教学荣誉')
          this.teachingHonorClick = true
          this.getTeachingHonorDataList()
        }
      },
      // 获取教师学术论文数据
      getAcademicPapersDataList () {
        if (this.academicPapersDataList === null || this.academicPapersDataList.length === 0) {
          this.dataListLoading = true
          this.$http({
            url: this.$http.adornUrl('/business/academicpapers/myList'),
            method: 'get',
            params: this.$http.adornParams({
              'teaId': this.teacherInfoEntity.teacherId
            })
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.academicPapersDataList = data.list
            } else {
              this.academicPapersDataList = []
            }
            this.dataListLoading = false
          })
        }
      },
      // 学生竞赛
      getStudentCompetitionDataList () {
        if (this.studentCompetitionDataList === null || this.studentCompetitionDataList.length === 0) {
          this.dataListLoading = true
          this.$http({
            url: this.$http.adornUrl('/business/studentcompetition/myList'),
            method: 'get',
            params: this.$http.adornParams({
              'teaId': this.teacherInfoEntity.teacherId
            })
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.studentCompetitionDataList = data.list
            } else {
              this.studentCompetitionDataList = []
            }
            this.dataListLoading = false
          })
        }
      },
      // 教师竞赛
      getTeacherCompetitionDataList () {
        if (this.teacherCompetitionDataList === null || this.teacherCompetitionDataList.length === 0) {
          this.dataListLoading = true
          this.$http({
            url: this.$http.adornUrl('/business/teachercompetition/myList'),
            method: 'get',
            params: this.$http.adornParams({
              'teaId': this.teacherInfoEntity.teacherId
            })
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.teacherCompetitionDataList = data.list
            } else {
              this.teacherCompetitionDataList = []
            }
            this.dataListLoading = false
          })
        }
      },
      // 项目立项
      getProjectCreateDataList () {
        if (this.projectCreateDataList === null || this.projectCreateDataList.length === 0) {
          this.dataListLoading = true
          this.$http({
            url: this.$http.adornUrl('/business/projectcreate/myList'),
            method: 'get',
            params: this.$http.adornParams({
              'teaId': this.teacherInfoEntity.teacherId
            })
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.projectCreateDataList = data.list
            } else {
              this.projectCreateDataList = []
            }
            this.dataListLoading = false
          })
        }
      },
      // 项目结题
      getProjectFinishDataList () {
        if (this.projectFinishDataList === null || this.projectFinishDataList.length === 0) {
          this.dataListLoading = true
          this.$http({
            url: this.$http.adornUrl('/business/projectfinish/list'),
            method: 'get',
            params: this.$http.adornParams({
              'page': this.pageIndex,
              'limit': this.pageSize,
              'teaId': this.teacherInfoEntity.teacherId
            })
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.projectFinishDataList = data.page.list
            } else {
              this.projectFinishDataList = []
            }
            this.dataListLoading = false
          })
        }
      },
      // 科研成果
      getPatentAchievementsDataList () {
        if (this.researchGainDataList === null || this.researchGainDataList.length === 0) {
          this.dataListLoading = true
          this.$http({
            url: this.$http.adornUrl('/business/researchgain/myList'),
            method: 'get',
            params: this.$http.adornParams({
              'teaId': this.teacherInfoEntity.teacherId
            })
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.researchGainDataList = data.list
            } else {
              this.researchGainDataList = []
            }
            this.dataListLoading = false
          })
        }
      },
      // 大创项目
      getSoftwareCopyrightDataList () {
        if (this.innovateProjectDataList === null || this.innovateProjectDataList.length === 0) {
          this.dataListLoading = true
          this.$http({
            url: this.$http.adornUrl('/business/innovateproject/myList'),
            method: 'get',
            params: this.$http.adornParams({
              'teaId': this.teacherInfoEntity.teacherId
            })
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.innovateProjectDataList = data.list
            } else {
              this.innovateProjectDataList = []
            }
            this.dataListLoading = false
          })
        }
      },
      // 自编教材管理
      getTeachingMaterialDataList () {
        if (this.teachingMaterialDataList === null || this.teachingMaterialDataList.length === 0) {
          this.dataListLoading = true
          this.$http({
            url: this.$http.adornUrl('/business/teachingmaterial/myList'),
            method: 'get',
            params: this.$http.adornParams({
              'teaId': this.teacherInfoEntity.teacherId
            })
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.teachingMaterialDataList = data.list
            } else {
              this.teachingMaterialDataList = []
            }
            this.dataListLoading = false
          })
        }
      },
      // 教学成果
      getTeachingAchievementsDataList () {
        if (this.teachingAchievementsDataList === null || this.teachingAchievementsDataList.length === 0) {
          this.dataListLoading = true
          this.$http({
            url: this.$http.adornUrl('/business/teachingachievements/myList'),
            method: 'get',
            params: this.$http.adornParams({
              'teaId': this.teacherInfoEntity.teacherId
            })
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.teachingAchievementsDataList = data.list
            } else {
              this.teachingAchievementsDataList = []
            }
            this.dataListLoading = false
          })
        }
      },
      // 教学荣誉
      getTeachingHonorDataList () {
        if (this.teachingHonorDataList === null || this.teachingHonorDataList.length === 0) {
          this.dataListLoading = true
          this.$http({
            url: this.$http.adornUrl('/business/teachinghonor/myList'),
            method: 'get',
            params: this.$http.adornParams({
              'teaId': this.teacherInfoEntity.teacherId
            })
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.teachingHonorDataList = data.list
            } else {
              this.teachingHonorDataList = []
            }
            this.dataListLoading = false
          })
        }
      },
      // 导出档案信息
      exportXlsxHandle () {
        this.$confirm(`确定要进行[导出]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          const loading = this.$loading({
            lock: true,
            text: '数据导出中...',
            spinner: 'el-icon-loading',
            background: 'rgba(0, 0, 0, 0.8)'
          })
          // 如果标签页没被点击过 则渲染数据 否则excel数据会为空
          if (!this.academicPapersClick) this.getAcademicPapersDataList()
          if (!this.studentCompetitionClick) this.getStudentCompetitionDataList()
          if (!this.teacherCompetitionClick) this.getTeacherCompetitionDataList()
          if (!this.projectCreateClick) this.getProjectCreateDataList()
          if (!this.projectFinishClick) this.getProjectFinishDataList()
          if (!this.researchGainClick) this.getPatentAchievementsDataList()
          if (!this.innovateProjectClick) this.getSoftwareCopyrightDataList()
          if (!this.teachingMaterialClick) this.getTeachingMaterialDataList()
          if (!this.teachingAchievementsClick) this.getTeachingAchievementsDataList()
          if (!this.teachingHonorClick) this.getTeachingHonorDataList()
          setTimeout(() => {
            var msg = '操作成功'
            var type = 'success'
            var workbook = XLSX.utils.book_new()
            // 学术论文
            var academicPapersDataList = XLSX.utils.table_to_sheet(document.querySelector('#academicPapersDataList'))
            XLSX.utils.book_append_sheet(workbook, academicPapersDataList, '学术论文')
            // 学生竞赛
            var studentCompetitionDataList = XLSX.utils.table_to_sheet(document.querySelector('#studentCompetitionDataList'))
            XLSX.utils.book_append_sheet(workbook, studentCompetitionDataList, '学生竞赛')
            // 教师竞赛
            var teacherCompetitionDataList = XLSX.utils.table_to_sheet(document.querySelector('#teacherCompetitionDataList'))
            XLSX.utils.book_append_sheet(workbook, teacherCompetitionDataList, '教师竞赛')
            // 项目立项
            var projectCreateDataList = XLSX.utils.table_to_sheet(document.querySelector('#projectCreateDataList'))
            XLSX.utils.book_append_sheet(workbook, projectCreateDataList, '项目立项')
            // 项目结题
            var projectFinishDataList = XLSX.utils.table_to_sheet(document.querySelector('#projectFinishDataList'))
            XLSX.utils.book_append_sheet(workbook, projectFinishDataList, '项目结题')
            // 科研成果
            var researchGainDataList = XLSX.utils.table_to_sheet(document.querySelector('#researchGainDataList'))
            XLSX.utils.book_append_sheet(workbook, researchGainDataList, '科研成果')
            // 大创项目
            var innovateProjectDataList = XLSX.utils.table_to_sheet(document.querySelector('#innovateProjectDataList'))
            XLSX.utils.book_append_sheet(workbook, innovateProjectDataList, '大创项目')
            // 自编教材管理
            var teachingMaterialDataList = XLSX.utils.table_to_sheet(document.querySelector('#teachingMaterialDataList'))
            XLSX.utils.book_append_sheet(workbook, teachingMaterialDataList, '自编教材管理')
            // 教学成果
            var teachingAchievementsDataList = XLSX.utils.table_to_sheet(document.querySelector('#teachingAchievementsDataList'))
            XLSX.utils.book_append_sheet(workbook, teachingAchievementsDataList, '教学成果')
            // 教学荣誉
            var teachingHonorDataList = XLSX.utils.table_to_sheet(document.querySelector('#teachingHonorDataList'))
            XLSX.utils.book_append_sheet(workbook, teachingHonorDataList, '教学荣誉')
            var wbout = XLSX.write(workbook, {bookType: 'xlsx', bookSST: true, type: 'array'})
            try {
              FileSaver.saveAs(new Blob([wbout], {type: 'application/octet-stream'}), '个人档案信息.xlsx')
            } catch (e) {
              if (typeof console !== 'undefined') console.log(e, wbout)
              msg = '操作失败'
              type = 'error'
            }
            this.$message({
              message: msg,
              type: type,
              duration: 1500
            })
            // 关闭loading和页面
            loading.close()
            this.visible = false
            return wbout
          }, 5000)
        })
      }
    }
  }
</script>
<style>
  .contents {
    height: 60px;
    font-size: 18px;
  }

  .table {
    border: 1px solid #ddd;
    /*为表格设置合并边框模型*/
    border-collapse: collapse;
    /*列宽由表格宽度和列宽度设定*/
    table-layout: fixed;
  }

  .table td {
    /*允许在单词内换行。*/
    word-break: break-word;
    /*设置宽度*/
    width: 100%;
  }
</style>
