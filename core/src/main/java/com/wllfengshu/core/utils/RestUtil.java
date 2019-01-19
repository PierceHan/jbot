package com.wllfengshu.core.utils;

/**
 * 生成rest层文件
 */
public class RestUtil {

    public static String genRest(String tableNameFUDTU,String serviceClassName,String entityClassName,String restPack){
        StringBuffer sb=new StringBuffer();
        sb.append(genHead(tableNameFUDTU,restPack,serviceClassName,entityClassName));
        sb.append(genMember(tableNameFUDTU));
        sb.append(genInsert(tableNameFUDTU));
        sb.append(genDelete(tableNameFUDTU));
        sb.append(genUpdate(tableNameFUDTU));
        sb.append(genSelect(tableNameFUDTU));
        sb.append(genSelectList());
        sb.append(genTail());
        return sb.toString();
    }

    /**
     * 生成头
     * @return
     */
    private static String genHead(String tableNameFUDTU,String restPack,String serviceClassName,String entityClassName){
        StringBuffer sb=new StringBuffer();
        sb.append("package "+restPack+";\r\n\r\n");
        sb.append("import "+serviceClassName+";\r\n");
        sb.append("import "+entityClassName+";\r\n");
        sb.append("import io.swagger.annotations.*;\r\n");
        sb.append("import org.slf4j.Logger;\r\n");
        sb.append("import org.slf4j.LoggerFactory;\r\n");
        sb.append("import org.springframework.beans.factory.annotation.Autowired;\r\n");
        sb.append("import org.springframework.web.bind.annotation.*;\r\n");
        sb.append("import javax.servlet.http.HttpServletRequest;\r\n");
        sb.append("import javax.servlet.http.HttpServletResponse;\r\n");
        sb.append("import java.util.*;\r\n\r\n");
        sb.append("@RestController\r\n");
        sb.append("@RequestMapping(\"/"+tableNameFUDTU+"s\")\r\n");
        sb.append("public class "+tableNameFUDTU+"Rest {\r\n\r\n");
        return sb.toString();
    }

    /**
     * 生成尾
     * @return
     */
    private static String genTail(){
        return "\r\n}\r\n";
    }

    /**
     * 生成成员变量
     * @return
     */
    private static String genMember(String tableNameFUDTU){
        StringBuffer sb=new StringBuffer();
        sb.append("\t@Autowired\r\n");
        sb.append("\tprivate "+tableNameFUDTU+"Service service;\r\n\r\n");
        sb.append("\tprivate Logger logger = LoggerFactory.getLogger(getClass());\r\n\r\n");
        return sb.toString();
    }

    /**
     * 生成插入语句
     * @return
     */
    private static String genInsert(String tableNameFUDTU){
        StringBuffer sb=new StringBuffer();
        sb.append("\t@ApiOperation(value = \"插入\",httpMethod = \"POST\")\r\n");
        sb.append("\t@ApiResponses({\r\n");
        sb.append("\t\t@ApiResponse(code=400, message=\"IllegalParam\")\r\n");
        sb.append("\t})\r\n");
        sb.append("\t@RequestMapping(value = \"/"+tableNameFUDTU+"\",method = RequestMethod.POST)\r\n");
        sb.append("\tpublic Map<String, Object> insert(\r\n");
        sb.append("\t\t\tHttpServletRequest request,\r\n");
        sb.append("\t\t\tHttpServletResponse response,\r\n");
        sb.append("\t\t\t@RequestBody "+tableNameFUDTU+" entity){\r\n");
        sb.append("\t\treturn service.insert(entity);\r\n");
        sb.append("\t}\r\n\r\n");
        return sb.toString();
    }

    /**
     * 生成删除语句
     * @return
     */
    private static String genDelete(String tableNameFUDTU){
        StringBuffer sb=new StringBuffer();
        sb.append("\t@ApiOperation(value = \"删除\",httpMethod = \"DELETE\")\r\n");
        sb.append("\t@ApiImplicitParams({\r\n");
        sb.append("\t\t@ApiImplicitParam(name = \"id\", value = \"ID\", required = true, dataType = \"int\",paramType = \"path\")\r\n");
        sb.append("\t})\r\n");
        sb.append("\t@ApiResponses({\r\n");
        sb.append("\t\t@ApiResponse(code=400, message=\"IllegalParam\")\r\n");
        sb.append("\t})\r\n");
        sb.append("\t@RequestMapping(value = \"/"+tableNameFUDTU+"/{id}\",method = RequestMethod.DELETE)\r\n");
        sb.append("\tpublic Map<String, Object> delete(\r\n");
        sb.append("\t\t\t@PathVariable(\"id\") Integer id,\r\n");
        sb.append("\t\t\tHttpServletRequest request,\r\n");
        sb.append("\t\t\tHttpServletResponse response){\r\n");
        sb.append("\t\treturn service.delete"+tableNameFUDTU+"(id);\r\n");
        sb.append("\t}\r\n\r\n");
        return sb.toString();
    }

    /**
     * 生成更新语句
     * @return
     */
    private static String genUpdate(String tableNameFUDTU){
        StringBuffer sb=new StringBuffer();
        sb.append("\t@ApiOperation(value = \"修改\",httpMethod = \"PUT\")\r\n");
        sb.append("\t@ApiResponses({\r\n");
        sb.append("\t\t@ApiResponse(code=400, message=\"IllegalParam\")\r\n");
        sb.append("\t})\r\n");
        sb.append("\t@RequestMapping(value = \"/"+tableNameFUDTU+"\",method = RequestMethod.PUT)\r\n");
        sb.append("\tpublic Map<String, Object> update(\r\n");
        sb.append("\t\t\tHttpServletRequest request,\r\n");
        sb.append("\t\t\tHttpServletResponse response,\r\n");
        sb.append("\t\t\t@RequestBody "+tableNameFUDTU+" entity){\r\n");
        sb.append("\t\treturn service.update(entity);\r\n");
        sb.append("\t}\r\n\r\n");
        return sb.toString();
    }

    /**
     * 生成查询语句（单条）
     * @return
     */
    private static String genSelect(String tableNameFUDTU){
        StringBuffer sb=new StringBuffer();
        sb.append("\t@ApiOperation(value = \"按ID查询\",httpMethod = \"GET\")\r\n");
        sb.append("\t@ApiImplicitParams({\r\n");
        sb.append("\t\t@ApiImplicitParam(name = \"id\", value = \"ID\", required = true, dataType = \"int\",paramType = \"path\")\r\n");
        sb.append("\t})\r\n");
        sb.append("\t@ApiResponses({\r\n");
        sb.append("\t\t@ApiResponse(code=400, message=\"IllegalParam\")\r\n");
        sb.append("\t})\r\n");
        sb.append("\t@RequestMapping(value = \"/"+tableNameFUDTU+"/{id}\",method = RequestMethod.GET)\r\n");
        sb.append("\tpublic Map<String, Object> select(\r\n");
        sb.append("\t\t\t@PathVariable(\"id\") Integer id,\r\n");
        sb.append("\t\t\tHttpServletRequest request,\r\n");
        sb.append("\t\t\tHttpServletResponse response){\r\n");
        sb.append("\t\treturn service.select(id);\r\n");
        sb.append("\t}\r\n\r\n");
        return sb.toString();
    }

    /**
     * 生成查询语句（多条）
     * @return
     */
    private static String genSelectList(){
        StringBuffer sb=new StringBuffer();
        sb.append("\t@ApiOperation(value = \"查询\",httpMethod = \"GET\")\r\n");
        sb.append("\t@ApiImplicitParams({\r\n");
        sb.append("\t})\r\n");
        sb.append("\t @ApiResponses({\r\n");
        sb.append("\t\t@ApiResponse(code=400, message=\"IllegalParam\")\r\n");
        sb.append("\t})\r\n");
        sb.append("\t@RequestMapping(value = \"/\",method = RequestMethod.GET)\r\n");
        sb.append("\tpublic Map<String, Object> selects(\r\n");
        sb.append("\t\t\tHttpServletRequest request,\r\n");
        sb.append("\t\t\tHttpServletResponse response){\r\n");
        sb.append("\t\t\tMap<String,Object> params = new HashMap<>();\r\n");
        sb.append("\t\treturn service.selects(params);\r\n");
        sb.append("\t}\r\n");
        return sb.toString();
    }
}
