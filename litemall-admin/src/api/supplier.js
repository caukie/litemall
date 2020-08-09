import request from '@/utils/request'

export function listSupplier(query) {
  return request({
    url: '/supplier/list',
    method: 'get',
    params: query
  })
}

export function createSupplier(data) {
  return request({
    url: '/supplier/create',
    method: 'post',
    data
  })
}

export function readSupplier(data) {
  return request({
    url: '/supplier/read',
    method: 'get',
    data
  })
}

export function updateSupplier(data) {
  return request({
    url: '/supplier/update',
    method: 'post',
    data
  })
}

export function deleteSupplier(data) {
  return request({
    url: '/supplier/delete',
    method: 'post',
    data
  })
}

export function listSupplierPlatform(query) {
  return request({
    url: '/supplierplatform/list',
    method: 'get',
    params: query
  })
}
