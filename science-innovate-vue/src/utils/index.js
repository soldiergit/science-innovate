import Vue from 'vue'
import router from '@/router'
import store from '@/store'

/**
 * 获取uuid
 */
export function getUUID () {
  return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, c => {
    return (c === 'x' ? (Math.random() * 16 | 0) : ('r&0x3' | '0x8')).toString(16)
  })
}

/**
 * 是否有权限
 * @param {*} key
 */
export function isAuth (key) {
  return JSON.parse(sessionStorage.getItem('permissions') || '[]').indexOf(key) !== -1 || false
}

/**
 * 树形数据转换
 * @param {*} data
 * @param {*} id
 * @param {*} pid
 */
export function treeDataTranslate (data, id = 'id', pid = 'parentId') {
  var res = []
  var temp = {}
  for (var i = 0; i < data.length; i++) {
    temp[data[i][id]] = data[i]
  }
  for (var k = 0; k < data.length; k++) {
    if (temp[data[k][pid]] && data[k][id] !== data[k][pid]) {
      if (!temp[data[k][pid]]['children']) {
        temp[data[k][pid]]['children'] = []
      }
      if (!temp[data[k][pid]]['_level']) {
        temp[data[k][pid]]['_level'] = 1
      }
      data[k]['_level'] = temp[data[k][pid]]._level + 1
      temp[data[k][pid]]['children'].push(data[k])
    } else {
      res.push(data[k])
    }
  }
  return res
}

/**
 * 清除登录信息
 */
export function clearLoginInfo () {
  Vue.cookie.delete('token')
  store.commit('resetStore')
  router.options.isAddDynamicMenuRoutes = false
}

/* =====================================文件上传相关=============================================================== */
/**
 * 文件实体类
 */
export class Attach {
  constructor (file) {
    this.name = file.attachName
    this.url = file.attachPath
    this.isDel = file.isDel
    this.file = file
  }
}

/**
 * 文件转换为实体，保存到后台
 * @param fileList
 * @returns {Array}
 */
export function transferFileToData (fileList) {
  let tmp = []
  fileList.forEach(value => {
    tmp.push(value.file)
  })
  return tmp
}

/**
 * 实体转换为文件，回显到前端
 * @param fileList
 * @returns {Array}
 */
export function transferDataToFile (fileList) {
  let tmp = []
  fileList.forEach(value => {
    tmp.push(new Attach(value))
  })
  return tmp
}

/**
 * 将一个教师集合里各个教师的名称合并成一个字符串，回显到前端
 * @param teacherList
 * @returns {*}
 */
export function transferTeacherName (teacherList) {
  if (teacherList.length === 0) {
    return '暂无'
  }
  return teacherList.map(function (item, index) {
    return item.name
  }).join(',')
}
