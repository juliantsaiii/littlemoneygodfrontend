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
    data: data,
  })
}

// 修改业务平台用户管理
export function updateUser(data, carrywaitlist) {
  return request({
    url: '/sjwflowsys/user',
    method: 'put',
    data: data,
    params: { "carrywaitlist": carrywaitlist }

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

// 重置密码
export function resetPassword(id) {
  return request({
    url: '/sjwflowsys/user/restpassword',
    method: 'put',
    params: { id: id }
  })
}

// 新增用户签名
export function addleadersignature(id) {
  return request({
    url: '/sjwflowsys/leadersignature/add',
    method: 'post',
    params: { "userid": id },
  })
}


//根据角色id查找用户
export function getUserByRole(id) {
  return request({
    url: '/sjwflowsys/user/getUserByRole',
    method: "post",
    params: { "roleid": id }
  })
}

