import request from '@/utils/request'

export function listSupplierPlatform(query) {
  return request({
    url: '/supplierplatform/list',
    method: 'get',
    params: query
  })
}

export function createSupplierPlatform(data) {
  return request({
    url: '/supplierplatform/create',
    method: 'post',
    data
  })
}

export function readSupplierPlatform(data) {
  return request({
    url: '/supplierplatform/read',
    method: 'get',
    data
  })
}

export function updateSupplierPlatform(data) {
  return request({
    url: '/supplierplatform/update',
    method: 'post',
    data
  })
}

export function deleteSupplierPlatform(data) {
  return request({
    url: '/supplierplatform/delete',
    method: 'post',
    data
  })
}
