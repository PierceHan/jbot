package com.wllfengshu.core.work.javaHandle;

import com.wllfengshu.common.utils.FileUtil;
import com.wllfengshu.common.utils.StringUtil;
import com.wllfengshu.core.model.RequestModel;
import com.wllfengshu.core.model.TableModel;
import com.wllfengshu.core.utils.ServiceUtil;

/**
 * 处理service文件
 * @author wllfengshu
 */
public class ServiceHandle {

    public static void start(RequestModel requestModel){
        //1、生成对应的service文件
        genFile(requestModel);
}

    private static void genFile(RequestModel requestModel){
        for (TableModel t:requestModel.getTableModels()) {
            String service= ServiceUtil.genService(t.getTableNameFUDTU(),t.getEntityClassName(),requestModel.getServicePack());
            FileUtil.createFile(requestModel.getJavaPath()+"/"+StringUtil.spotToSlash(t.getServiceClassName())+".java",service);
        }
    }
}
