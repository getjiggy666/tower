package com.telecom.dao.impl;

import com.telecom.dao.LogDao;
import com.telecom.entity.Log;

import org.springframework.stereotype.Repository;

/**
 * Dao实现类 - 日志
 */

@Repository("logDaoImpl")
public class LogDaoImpl extends BaseDaoImpl<Log, String> implements LogDao {

}