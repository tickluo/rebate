package model;

import javax.validation.constraints.Past;
import java.util.Date;

public abstract class DataEntity extends BaseEntity {

    /**
     * 创建日期
     */
    @Past
    private Date createTime;
    /**
     * 更新日期
     */

    private Date updateTime;

    public DataEntity() {
        super();
    }

    public DataEntity(Long id) {
        super(id);
    }

    /**
     * 插入之前执行方法，需要手动调用
     */
    @Override
    public void preInsert() {
        this.updateTime = new Date();
        this.createTime = this.updateTime;
    }

    /**
     * 更新之前执行方法，需要手动调用
     */
    @Override
    public void preUpdate() {
        this.updateTime = new Date();
    }

    public Date getCreateDate() {
        return createTime == null ? null : (Date) createTime.clone();
    }

    public void setCreateDate(Date createDate) {
        this.createTime = createTime == null ? null : (Date) createTime.clone();
    }

    public Date getUpdateDate() {
        return updateTime == null ? null : (Date) updateTime.clone();
    }

    public void setUpdateDate(Date updateDate) {
        this.updateTime = updateTime == null ? null : (Date) updateTime.clone();
    }
}
