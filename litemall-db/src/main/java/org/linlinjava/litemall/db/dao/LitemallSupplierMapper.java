package org.linlinjava.litemall.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.linlinjava.litemall.db.domain.LitemallSupplier;
import org.linlinjava.litemall.db.domain.LitemallSupplierExample;

public interface LitemallSupplierMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_supplier
     *
     * @mbg.generated
     */
    long countByExample(LitemallSupplierExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_supplier
     *
     * @mbg.generated
     */
    int deleteByExample(LitemallSupplierExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_supplier
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_supplier
     *
     * @mbg.generated
     */
    int insert(LitemallSupplier record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_supplier
     *
     * @mbg.generated
     */
    int insertSelective(LitemallSupplier record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_supplier
     *
     * @mbg.generated
     */
    LitemallSupplier selectOneByExample(LitemallSupplierExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_supplier
     *
     * @mbg.generated
     */
    LitemallSupplier selectOneByExampleSelective(@Param("example") LitemallSupplierExample example, @Param("selective") LitemallSupplier.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_supplier
     *
     * @mbg.generated
     */
    List<LitemallSupplier> selectByExampleSelective(@Param("example") LitemallSupplierExample example, @Param("selective") LitemallSupplier.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_supplier
     *
     * @mbg.generated
     */
    List<LitemallSupplier> selectByExample(LitemallSupplierExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_supplier
     *
     * @mbg.generated
     */
    LitemallSupplier selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") LitemallSupplier.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_supplier
     *
     * @mbg.generated
     */
    LitemallSupplier selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_supplier
     *
     * @mbg.generated
     */
    LitemallSupplier selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_supplier
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") LitemallSupplier record, @Param("example") LitemallSupplierExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_supplier
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") LitemallSupplier record, @Param("example") LitemallSupplierExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_supplier
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(LitemallSupplier record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_supplier
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(LitemallSupplier record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_supplier
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") LitemallSupplierExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_supplier
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}