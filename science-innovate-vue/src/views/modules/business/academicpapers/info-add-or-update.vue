<template>
  <el-dialog
    :title="!dataForm.apId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
             label-width="80px"
             v-loading="dataFormLoading"
             element-loading-text="拼命加载中"
             element-loading-spinner="el-icon-loading">
      <el-form-item label="论文名称" prop="name">
        <el-input v-model="dataForm.name" placeholder="论文名称"></el-input>
      </el-form-item>
      <el-form-item label="负责人" prop="personId">
        <el-select v-model="dataForm.personId" style="width:50%" filterable placeholder="请选择">
          <el-option v-for="item in notChoiceDataList"
                     :key="item.teacherId"
                     :label="item.name"
                     :value="item.teacherId"
                     :disabled="item.disabled">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="期刊名称" prop="periodicalName">
        <el-input v-model="dataForm.periodicalName" placeholder="发布期刊名称"></el-input>
      </el-form-item>
      <el-form-item label="归属项目名及编号" prop="projectName">
        <el-input v-model="dataForm.projectName" placeholder="归属项目名及编号"></el-input>
      </el-form-item>
      <el-form-item label="作者类型" prop="authorType">
        <el-select v-model="dataForm.authorType" placeholder="请选择">
          <el-option v-for="item in authorTypeList" :key="item.value" :label="item.label" :value="item.value"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="论文类型" prop="paperType">
        <el-select v-model="dataForm.paperType" placeholder="请选择">
          <el-option v-for="item in paperTypeList" :key="item.value" :label="item.label" :value="item.value"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="论文等级" prop="grade">
        <el-select v-model="dataForm.grade" placeholder="请选择">
          <el-option v-for="item in papersGradeList" :key="item.value" :label="item.label" :value="item.value"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="发表时间" prop="publishTime">
        <el-date-picker
          value-format="yyyy-MM-dd HH:mm:ss"
          type="year"
          v-model="dataForm.publishTime"
          :picker-options="pickerOptions"
          placeholder="选择论文发表时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="项目成员" prop="choiceDataList">
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
      <el-form-item label="文件上传" prop="fileList">
        <el-upload
          class="upload-demo"
          :data="upLoadData"
          :action="upLoadUrl"
          :before-remove="beforeRemove"
          :on-remove="upLoadRemove"
          :on-success="upLoadSuccess"
          :file-list="fileList">
          <el-button size="small" type="primary">上传附件</el-button>
        </el-upload>
      </el-form-item>
    </el-form>
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
      // var validateMembers = (rule, value, callback) => {
      //   if (this.choiceDataList.length === 0) {
      //     callback(new Error('项目成员不能为空'))
      //   } else {
      //     callback()
      //   }
      // }
      var validateAttach = (rule, value, callback) => {
        if (this.fileList.length === 0) {
          callback(new Error('证明材料不能为空'))
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
            callback(new Error('证明材料不能为空'))
          }
        }
      }
      return {
        visible: false,
        addLoading: false,
        dataFormLoading: false,
        // 文件上传路径
        upLoadUrl: this.$http.adornUrl(`/business/sysattach/upload`),
        // 文件上传携带的数据
        upLoadData: {
          'userId': this.$store.state.user.id,
          'fileType': '/AP/',
          'token': this.$cookie.get('token')
        },
        // 附件对象集合
        fileList: [],
        // el-transfer会自动从数据源中分离数据：即未选成员应该永远为全体教师；已选择成员数组只存放key 即teacherId，不能多家其它，label等
        choiceDataList: [], // 已选择成员
        notChoiceDataList: [], // 未选择成员
        // Transfer数据源的字段别名
        teacherListTransferProps: {
          key: 'teacherId',
          label: 'name'
        },
        // 只能选择今天以及今天以前的日期
        pickerOptions: {
          disabledDate (time) {
            return time.getTime() > Date.now() - 8.64e6
          }
        },
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
        papersGradeList: this.$store.state.user.papersGradeEntities,
        dataForm: {
          apId: 0,
          personId: '',
          name: '',
          periodicalName: '',
          projectName: '',
          authorType: '',
          paperType: '',
          grade: '',
          publishTime: ''
        },
        dataRule: {
          name: [
            {required: true, message: '论文名称不能为空', trigger: 'blur'}
          ],
          periodicalName: [
            {required: true, message: '发布期刊名称不能为空', trigger: 'blur'}
          ],
          // projectName: [
          //   {required: true, message: '归属项目名及编号不能为空', trigger: 'blur'}
          // ],
          authorType: [
            {required: true, message: '作者类型不能为空', trigger: 'blur'}
          ],
          paperType: [
            {required: true, message: '论文类型不能为空', trigger: 'blur'}
          ],
          grade: [
            {required: true, message: '论文等级不能为空', trigger: 'blur'}
          ],
          publishTime: [
            {required: true, message: '发表时间不能为空', trigger: 'blur'}
          ],
          personId: [
            {required: true, message: '项目负责人不能为空', trigger: 'blur'}
          ],
          // choiceDataList: [
          //   {required: true, validator: validateMembers, trigger: 'blur'}
          // ],
          fileList: [
            {required: true, validator: validateAttach, trigger: 'blur'}
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.apId = id || 0
        this.visible = true
        this.dataFormLoading = true
        this.$nextTick(() => {
          // 重置表单 如果想让resetFields方法有效果，必须要在el-form-item上增加prop字段，且和v-model的属性对应
          this.$refs['dataForm'].resetFields()
          this.fileList = []
          this.choiceDataList = []
          // 未选成员永远为全体教师
          this.notChoiceDataList = this.$store.state.user.allTeacherSelectList
          if (!this.dataForm.apId) {
            // 新增时 已选成员为空
            this.choiceDataList = []
            this.dataFormLoading = false
          } else {
            this.$http({
              url: this.$http.adornUrl(`/business/academicpapers/info/${this.dataForm.apId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.name = data.academicPapers.name
                this.dataForm.periodicalName = data.academicPapers.periodicalName
                this.dataForm.projectName = data.academicPapers.projectName
                this.dataForm.authorType = data.academicPapers.authorType
                this.dataForm.paperType = data.academicPapers.paperType
                this.dataForm.grade = data.academicPapers.grade
                this.dataForm.publishTime = data.academicPapers.publishTime
                // 已加入项目成员的teacherId
                this.dataForm.personId = data.academicPapers.personId
                this.choiceDataList = data.academicPapers.memberIdList
                // 附件信息
                this.fileList = transferDataToFile(data.academicPapers.sysAttachEntityList)
                this.dataFormLoading = false
              } else {
                this.dataFormLoading = false
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.addLoading = true
            this.$http({
              url: this.$http.adornUrl(`/business/academicpapers/${!this.dataForm.apId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'apId': this.dataForm.apId || undefined,
                'name': this.dataForm.name,
                'periodicalName': this.dataForm.periodicalName,
                'projectName': this.dataForm.projectName,
                'authorType': this.dataForm.authorType,
                'paperType': this.dataForm.paperType,
                'grade': this.dataForm.grade,
                'publishTime': this.dataForm.publishTime,
                'personId': this.dataForm.personId,
                'memberIdList': this.choiceDataList,
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
                this.$router.push({ name: 'login' })
              }
            })
          })
        }
      }
    }
  }
</script>
<style>
  #transferWW .el-transfer .el-transfer-panel{
    width: 40%
  }
</style>
