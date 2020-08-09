package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.LitemallSupplierMapper;
import org.linlinjava.litemall.db.domain.LitemallSupplier.Column;
import org.linlinjava.litemall.db.domain.LitemallSupplierExample;
import org.linlinjava.litemall.db.domain.LitemallSupplier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallSupplierService {
    @Resource
    private LitemallSupplierMapper supplierMapper;
    private Column[] columns = new Column[]{Column.id, Column.name, Column.desc, Column.floorPrice, Column.actPrice, Column.addTime, Column.updateTime};

    public List<LitemallSupplier> query(Integer page, Integer limit, String sort, String order) {
        LitemallSupplierExample example = new LitemallSupplierExample();
        example.or().andDeletedEqualTo(false);
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }
        PageHelper.startPage(page, limit);
        return supplierMapper.selectByExampleSelective(example, columns);
    }

    public List<LitemallSupplier> query(Integer page, Integer limit) {
        return query(page, limit, null, null);
    }

    public LitemallSupplier findById(Integer id) {
        return supplierMapper.selectByPrimaryKey(id);
    }

    public List<LitemallSupplier> querySelective(String id, String name, Integer page, Integer size, String sort, String order) {
        LitemallSupplierExample example = new LitemallSupplierExample();
        LitemallSupplierExample.Criteria criteria = example.createCriteria();

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
        return supplierMapper.selectByExample(example);
    }

    public int updateById(LitemallSupplier brand) {
        brand.setUpdateTime(LocalDateTime.now());
        return supplierMapper.updateByPrimaryKeySelective(brand);
    }

    public void deleteById(Integer id) {
        supplierMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(LitemallSupplier brand) {
        brand.setAddTime(LocalDateTime.now());
        brand.setUpdateTime(LocalDateTime.now());
        supplierMapper.insertSelective(brand);
    }

    public List<LitemallSupplier> all() {
        LitemallSupplierExample example = new LitemallSupplierExample();
        example.or().andDeletedEqualTo(false);
        return supplierMapper.selectByExample(example);
    }
}
