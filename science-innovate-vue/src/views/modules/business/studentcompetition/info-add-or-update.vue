<template>
  <el-dialog
    width="60%"
    :title="!dataForm.scId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-row :gutter="20">
      <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
               label-width="16rem"
               style="width: 94%; margin: 0 auto"
               v-loading="dataFormLoading"
               element-loading-text="拼命加载中"
               element-loading-spinner="el-icon-loading">
        <el-col :span="24">
          <el-form-item label="所属赛事" prop="matchId">
            <el-select v-model="dataForm.matchId" style="width:50%" filterable placeholder="请选择">
              <el-option v-for="item in matchList"
                         :key="item.matchId"
                         :label="item.name"
                         :value="item.matchId">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="第一指导老师" prop="firstTeacherId">
            <el-select v-model="dataForm.firstTeacherId" style="width:50%" filterable placeholder="请选择">
              <el-option v-for="item in teacherList"
                         :key="item.teacherId"
                         :label="item.name"
                         :value="item.teacherId"
                         :disabled="item.disabled">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="其它指导老师" prop="otherTeachersId">
            <el-select v-model="dataForm.otherTeachersId" style="width:50%" multiple filterable placeholder="请选择">
              <el-option v-for="item in teacherList"
                         :key="item.teacherId"
                         :label="item.name"
                         :value="item.teacherId">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="作品名称" prop="worksName">
            <el-input v-model="dataForm.worksName" placeholder="作品名称"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="参赛学生" prop="entryStu">
            <el-input v-model="dataForm.entryStu" placeholder="请输入参赛学生姓名 多个用','分隔开"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="获奖级别" prop="grade">
            <el-select v-model="dataForm.grade" placeholder="请选择">
              <el-option v-for="item in competitionGradeList" :key="item.value" :label="item.label"
                         :value="item.value"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="获奖等次" prop="rank">
            <el-select v-model="dataForm.rank" placeholder="请选择">
              <el-option v-for="item in competitionRankList" :key="item.value" :label="item.label" :value="item.value"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="获奖时间" prop="prizeTime">
            <!--时间：后台是date类型的写法-->
            <el-date-picker
              value-format="yyyy-MM-dd HH:mm:ss"
              type="year"
              v-model="dataForm.prizeTime"
              placeholder="选择获奖时间">
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="获奖证书" prop="fileList">
            <el-upload
              class="upload-demo"
              :data="upLoadData"
              :action="upLoadUrl"
              :before-remove="beforeRemove"
              :on-remove="upLoadRemove"
              :on-success="upLoadSuccess"
              :file-list="fileList">
              <el-button size="small" type="primary">上传证书</el-button>
            </el-upload>
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
  import {clearLoginInfo, transferFileToData, transferDataToFile, Attach} from '@/utils'

  export default {
    data () {
      var validateAttach = (rule, value, callback) => {
        if (this.fileList.length === 0) {
          callback(new Error('获奖证书等证明材料不能为空'))
        } else {
          let notNull = false
          for (let index = 0; index < this.fileList.length; index++) {
            if (this.fileList[index].isDel === 0) {
              notNull = true
            }
          }
          if (notNull) {
            callback()
          } else {
            callback(new Error('获奖证书等证明材料不能为空'))
          }
        }
      }
      return {
        visible: false,
        addLoading: false,
        dataFormLoading: false,
        competitionGradeList: this.$store.state.user.competitionGradeEntities,
        competitionRankList: this.$store.state.user.competitionRankEntities,
        matchList: [],
        teacherList: [],
        // 文件上传路径
        upLoadUrl: this.$http.adornUrl(`/business/sysattach/upload`),
        // 文件上传携带的数据
        upLoadData: {
          'userId': this.$store.state.user.id,
          'fileType': '/SC/',
          'token': this.$cookie.get('token')
        },
        // 附件对象集合
        fileList: [],
        dataForm: {
          scId: 0,
          worksName: '',
          entryStu: '',
          grade: '',
          rank: '',
          matchId: '',
          firstTeacherId: '',
          otherTeachersId: [],
          prizeTime: ''
        },
        dataRule: {
          worksName: [
            {required: true, message: '作品名称不能为空', trigger: 'blur'}
          ],
          entryStu: [
            {required: true, message: '参赛学生不能为空', trigger: 'blur'}
          ],
          grade: [
            {required: true, message: '获奖级别不能为空', trigger: 'blur'}
          ],
          rank: [
            {required: true, message: '获奖等次不能为空', trigger: 'blur'}
          ],
          matchId: [
            {required: true, message: '所属赛事不能为空', trigger: 'blur'}
          ],
          firstTeacherId: [
            {required: true, message: '第一指导老师不能为空', trigger: 'blur'}
          ],
          prizeTime: [
            {required: true, message: '获奖时间不能为空', trigger: 'blur'}
          ],
          fileList: [
            {required: true, validator: validateAttach, trigger: 'blur'}
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.scId = id || 0
        this.visible = true
        this.dataFormLoading = true
        this.$nextTick(() => {
          // 重置表单 如果想让resetFields方法有效果，必须要在el-form-item上增加prop字段，且和v-model的属性对应
          this.$refs['dataForm'].resetFields()
          this.matchList = []
          this.teacherList = []
          // 可选择的指导老师永远为全体教师
          this.teacherList = this.$store.state.user.allTeacherSelectList
          this.fileList = []
          // 获取可被选择的学生赛事
          this.$http({
            url: this.$http.adornUrl('/business/matchinfo/select'),
            method: 'get',
            params: this.$http.adornParams({
              'userId': this.$store.state.user.id,
              'matchType': 1
            })
          }).then(({data}) => {
            if (data && data.code === 0) {
              if (data.matchList.length <= 0) {
                this.$confirm('目前系统未查询到任何学生赛事，请前往赛事管理添加！是否前往？', '提示', {
                  confirmButtonText: '立即前往',
                  cancelButtonText: '取消',
                  type: 'error'
                }).then(() => {
                  this.dataFormLoading = false
                  this.visible = false
                  this.$router.push({name: 'business-basics/matchinfo'})
                }).catch(() => {
                  this.dataFormLoading = false
                  this.visible = false
                  this.$message({
                    type: 'info',
                    message: '已取消前往'
                  })
                })
              }
              this.matchList = data.matchList
              this.dataForm.firstTeacherId = data.teacherId
            } else {
              this.matchList = []
            }
          }).then(() => {
            // 修改
            if (this.dataForm.scId) {
              this.$http({
                url: this.$http.adornUrl(`/business/studentcompetition/info/${this.dataForm.scId}`),
                method: 'get',
                params: this.$http.adornParams()
              }).then(({data}) => {
                if (data && data.code === 0) {
                  this.dataForm.worksName = data.studentCompetition.worksName
                  this.dataForm.entryStu = data.studentCompetition.entryStu
                  this.dataForm.grade = data.studentCompetition.grade
                  this.dataForm.rank = data.studentCompetition.rank
                  this.dataForm.prizeTime = data.studentCompetition.prizeTime
                  this.dataForm.matchId = data.studentCompetition.matchId
                  this.dataForm.firstTeacherId = data.studentCompetition.firstTeacherId
                  this.dataForm.otherTeachersId = data.studentCompetition.otherTeachersId
                  // 附件信息
                  this.fileList = transferDataToFile(data.studentCompetition.sysAttachEntityList)
                  this.dataFormLoading = false
                } else {
                  this.dataFormLoading = false
                }
              })
            } else {
              this.dataFormLoading = false
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
              url: this.$http.adornUrl(`/business/studentcompetition/${!this.dataForm.scId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'scId': this.dataForm.scId || undefined,
                'worksName': this.dataForm.worksName,
                'entryStu': this.dataForm.entryStu,
                'grade': this.dataForm.grade,
                'rank': this.dataForm.rank,
                'prizeTime': this.dataForm.prizeTime,
                'matchId': this.dataForm.matchId,
                'firstTeacherId': this.dataForm.firstTeacherId,
                'otherTeachersId': this.dataForm.otherTeachersId,
                'sysAttachEntityList': transferFileToData(this.fileList)
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
      // 移除附件
      beforeRemove (file, fileList) {
        return this.$confirm(`确定移除 ${file.name}？`)
      },
      // 移除附件
      upLoadRemove (file, fileList) {
        this.$http({
          url: this.$http.adornUrl('/business/sysattach/deleteFile'),
          method: 'post',
          params: this.$http.adornParams({
            'filePath': file.url
          })
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.fileList = fileList
          } else {
            this.$message.error(data.msg)
          }
        })
      },
      // 文件上传成功后回调
      upLoadSuccess (data) {
        if (data && data.code === 0) {
          // 将回调的后台文件对象转为前端的文件对象
          this.fileList.push(new Attach(data.sysAttachEntity))
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
      }
    }
  }
</script>
