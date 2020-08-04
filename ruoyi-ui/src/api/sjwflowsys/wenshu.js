import request from '@/utils/request'

// 查询文书管理列表
export function listWenshu(query) {
  return request({
    url: '/sjwflowsys/wenshu/list',
    method: 'get',
    params: query
  })
}

// 查询文书管理详细
export function getWenshu(id) {
  return request({
    url: '/sjwflowsys/wenshu/' + id,
    method: 'get'
  })
}

// 新增文书管理
export function addWenshu(data) {
  return request({
    url: '/sjwflowsys/wenshu',
    method: 'post',
    data: data
  })
}

// 修改文书管理
export function updateWenshu(data) {
  return request({
    url: '/sjwflowsys/wenshu',
    method: 'put',
    data: data
  })
}

// 删除文书管理
export function delWenshu(id) {
  return request({
    url: '/sjwflowsys/wenshu/' + id,
    method: 'delete'
  })
}

// 导出文书管理
export function exportWenshu(query) {
  return request({
    url: '/sjwflowsys/wenshu/export',
    method: 'get',
    params: query
  })
}