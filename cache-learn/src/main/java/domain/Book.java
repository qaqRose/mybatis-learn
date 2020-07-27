package domain;

import java.io.Serializable;

/**
 * @author: QXQ
 * @time: 2020/7/26 14:58
 * @desc: 图书类实体
 */
public class Book implements Serializable {
    private Integer id;
    private String name;
    private Integer typeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bookTypeId=" + typeId +
                '}';
    }
}
