package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.LitemallSupplierPlatformMapper;
import org.linlinjava.litemall.db.domain.LitemallSupplierPlatform.Column;
import org.linlinjava.litemall.db.domain.LitemallSupplierPlatform;
import org.linlinjava.litemall.db.domain.LitemallSupplierPlatformExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallSupplierPlatformService {
    @Resource
    private LitemallSupplierPlatformMapper supplierPlatformMapper;
    private Column[] columns = new Column[]{Column.id, Column.name, Column.desc, Column.addTime, Column.updateTime};

    public List<LitemallSupplierPlatform> query(Integer page, Integer limit, String sort, String order) {
        LitemallSupplierPlatformExample example = new LitemallSupplierPlatformExample();
        example.or().andDeletedEqualTo(false);
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }
        PageHelper.startPage(page, limit);
        return supplierPlatformMapper.selectByExampleSelective(example, columns);
    }

    public List<LitemallSupplierPlatform> query(Integer page, Integer limit) {
        return query(page, limit, null, null);
    }

    public LitemallSupplierPlatform findById(Integer id) {
        return supplierPlatformMapper.selectByPrimaryKey(id);
    }

    public List<LitemallSupplierPlatform> querySelective(String id, String name, Integer page, Integer size, String sort, String order) {
        LitemallSupplierPlatformExample example = new LitemallSupplierPlatformExample();
        LitemallSupplierPlatformExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(id)) {
            criteria.andIdEqualTo(Integer.valueOf(id));
        }
        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return supplierPlatformMapper.selectByExample(example);
    }

    public int updateById(LitemallSupplierPlatform brand) {
        brand.setUpdateTime(LocalDateTime.now());
        return supplierPlatformMapper.updateByPrimaryKeySelective(brand);
    }

    public void deleteById(Integer id) {
        supplierPlatformMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(LitemallSupplierPlatform brand) {
        brand.setAddTime(LocalDateTime.now());
        brand.setUpdateTime(LocalDateTime.now());
        supplierPlatformMapper.insertSelective(brand);
    }

    public List<LitemallSupplierPlatform> all() {
        LitemallSupplierPlatformExample example = new LitemallSupplierPlatformExample();
        example.or().andDeletedEqualTo(false);
        return supplierPlatformMapper.selectByExample(example);
    }
}