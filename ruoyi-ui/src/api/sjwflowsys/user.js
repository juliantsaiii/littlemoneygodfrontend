import request from '@/utils/request'

// 查询业务平台用户管理列表
export function listUser(query) {
  return request({
    url: '/sjwflowsys/user/list',
    method: 'get',
    params: query
  })
}

// 查询业务平台用户管理详细
export function getUser(id) {
  return request({
    url: '/sjwflowsys/user/' + id,
    method: 'get'
  })
}

// 新增业务平台用户管理
export function addUser(data) {
  return request({
    url: '/sjwflowsys/user',
    method: 'post',
    data: data
  })
}

// 修改业务平台用户管理
export function updateUser(data) {
  return request({
    url: '/sjwflowsys/user',
    method: 'put',
    data: data
  })
}

// 删除业务平台用户管理
export function delUser(id) {
  return request({
    url: '/sjwflowsys/user/' + id,
    method: 'delete'
  })
}

// 导出业务平台用户管理
export function exportUser(query) {
  return request({
    url: '/sjwflowsys/user/export',
    method: 'get',
    params: query
  })
}