import request from '@/utils/request'

// 查询角色管理列表
export function listRole(query) {
  return request({
    url: '/sjwflowsys/role/list',
    method: 'get',
    params: query
  })
}

// 查询角色管理详细
export function getRole(id) {
  return request({
    url: '/sjwflowsys/role/' + id,
    method: 'get'
  })
}

// 新增角色管理
export function addRole(data) {
  return request({
    url: '/sjwflowsys/role',
    method: 'post',
    data: data
  })
}

// 修改角色管理
export function updateRole(data) {
  return request({
    url: '/sjwflowsys/role',
    method: 'put',
    data: data,
  })
}

// 删除角色管理
export function delRole(id) {
  return request({
    url: '/sjwflowsys/role/' + id,
    method: 'delete'
  })
}

// 导出角色管理
export function exportRole(query) {
  return request({
    url: '/sjwflowsys/role/export',
    method: 'get',
    params: query
  })
}

// 查询角色树
export function getRoleTree(query) {
  return request({
    url: '/sjwflowsys/role/listTree',
    method: 'get',
    params: query
  })
}

// 根据节点角色部门树
export function getRoleTreebyNode(query) {
  return request({
    url: '/sjwflowsys/role/listTreeNode',
    method: 'get',
    params: query
  })
}

export function delUserRole(id) {
  return request({
    url: '/sjwflowsys/userrole/' + id,
    method: 'delete'
  })
}

export function getRoleData(id) {
  return request({
    url: '/sjwflowsys/roledata/getDeptIDs',
    method: 'post',
    params: { "roleid": id }
  })
}

export function insertUserRoles(data) {
  return request({
    url: "/sjwflowsys/role/insertUserRoles",
    method: "post",
    data: data,
  })
}