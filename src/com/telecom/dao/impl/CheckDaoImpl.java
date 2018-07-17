package com.telecom.dao.impl;

import org.springframework.stereotype.Repository;

import com.telecom.dao.CheckDao;
import com.telecom.entity.Check;

/**
 * Dao实现类 - 审核
 */

@Repository("checkDaoImpl")
public class CheckDaoImpl extends BaseDaoImpl<Check, String> implements CheckDao {

}
