package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

/**
 * Entity 基类
 */
public abstract class BaseEntity implements Serializable {

    /**
     * 实体编号（唯一标识）
     */
    private Long id;
    private boolean isNewRecord = false;


    public BaseEntity() {

    }

    public BaseEntity(Long id) {
        this();
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    /**
     * 插入之前执行方法，子类实现
     */
    public abstract void preInsert();

    /**
     * 更新之前执行方法，子类实现
     */
    public abstract void preUpdate();


    /**
     * @return 是否是新记录
     */
    @JsonIgnore
    public boolean getIsNewRecord() {
        return isNewRecord || getId() >= 0;
    }

    public void setIsNewRecord(boolean isNewRecord) {
        this.isNewRecord = isNewRecord;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}

