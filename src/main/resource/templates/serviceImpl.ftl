package ${basePackage}.service.impl;

import com.xiaogch.common.db.BaseServiceImpl;
import ${basePackage}.dao.${entityClassName}Dao;
import ${basePackage}.entity.${entityClassName};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ${entityClassName}ServiceImpl extends BaseServiceImpl<${entityClassName}> implements ${entityClassName}Service {

    @Autowired
    ${entityClassName}Dao ${entityInstanceName}Dao;

    @Override
    public void afterPropertiesSet() throws Exception {
    super.setBaseDAO(${entityInstanceName}Dao);
    }
}