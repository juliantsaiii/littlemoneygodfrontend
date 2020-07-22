import request from '@/utils/request'

// 查询运维记录列表
export function listProblemlist(query) {
  return request({
    url: '/sjwflowbusiness/problemlist/list',
    method: 'get',
    params: query
  })
}

// 查询运维记录详细
export function getProblemlist(id) {
  return request({
    url: '/sjwflowbusiness/problemlist/' + id,
    method: 'get'
  })
}

// 新增运维记录
export function addProblemlist(data) {
  return request({
    url: '/sjwflowbusiness/problemlist',
    method: 'post',
    data: data
  })
}

// 修改运维记录
export function updateProblemlist(data) {
  return request({
    url: '/sjwflowbusiness/problemlist',
    method: 'put',
    data: data
  })
}

// 删除运维记录
export function delProblemlist(id) {
  return request({
    url: '/sjwflowbusiness/problemlist/' + id,
    method: 'delete'
  })
}

// 导出运维记录
export function exportProblemlist(query) {
  return request({
    url: '/sjwflowbusiness/problemlist/export',
    method: 'get',
    params: query
  })
}


// 查询运维类型统计
export function listServiceType() {
  return request({
    url: '/sjwflowbusiness/problemlist/selectServiceTypeCount',
    method: 'post'
  })
}
