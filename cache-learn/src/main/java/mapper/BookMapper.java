package mapper;

import domain.Book;
import org.apache.ibatis.annotations.Param;

/**
 * @author: QXQ
 * @time: 2020/7/26 15:03
 * @desc: 图书数据操作映射类
 */
public interface BookMapper {
    Book getById(@Param("id") Integer id);
}
