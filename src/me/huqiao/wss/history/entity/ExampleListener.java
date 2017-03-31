package me.huqiao.wss.history.entity;

import me.huqiao.wss.util.threadlocal.ThreadLocalUtil;
import me.huqiao.wss.util.web.LoginInfo;

import org.hibernate.envers.RevisionListener;

public class ExampleListener implements RevisionListener {
	
    public void newRevision(Object revisionEntity) {
        TestRevisionEntity exampleRevEntity = (TestRevisionEntity) revisionEntity;
        LoginInfo loginInfo = (LoginInfo)ThreadLocalUtil.loginInfoThreadLocal.get();
        if(loginInfo==null){
        	return;
        }
        exampleRevEntity.setUsername(loginInfo.getUser().getUsername());
    }
}