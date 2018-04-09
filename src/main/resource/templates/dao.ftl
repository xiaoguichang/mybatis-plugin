package ${basePackage}.dao;

import com.xiaogch.common.db.BaseDAO;
import ${basePackage}.entity.${entityClassName};
import org.springframework.stereotype.Repository;

@Repository
public interface ${entityClassName}Dao extends BaseDAO<Integer , ${entityClassName}> {

}
