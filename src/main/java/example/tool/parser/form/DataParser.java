package example.tool.parser.form;

import example.tool.common.Common;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2017/6/16.
 */
public class DataParser {

    private static Logger logger = LoggerFactory.getLogger(DataParser.class);

    public static Object dataParse(String type, String data) {
        Object resultData = new Object();
        switch (type) {
            case "java.lang.String":
                resultData = data;
                break;
            case "int":
                resultData = Integer.parseInt(data);
                break;
            case "byte":
                resultData = Byte.parseByte(data);
                break;
            case "short":
                resultData = Short.parseShort(data);
                break;
            case "long":
                resultData = Long.parseLong(data);
                break;
            case "char":
                resultData = data.toCharArray()[0];
                break;
            case "float":
                resultData = Float.parseFloat(data);
                break;
            case "double":
                resultData = Double.parseDouble(data);
                break;
            case "boolean":
                resultData = Boolean.parseBoolean(data);
                break;
            default:
                resultData = data;
                break;
        }
        return resultData;
    }


    /**
     * 接收数据后转换数据类型
     *
     * @param obj
     * @return
     */
    public static Object changeDataType(Object obj) {
        String target = String.valueOf(obj);
        if (target.equals(Common.TRUE) || target.equals(Common.FALSE)) {
            //布尔类型数据
            return Boolean.parseBoolean(target);

        } else if (target.matches("^[0-9]*$")) {
            //整型数据
            try {
                return Integer.parseInt(target);

            } catch (Exception e) {
                //转换出错则直接返回string类型数据，可能由于int的位数不够导致的
                //DataParser.logger.error("parse int error", e);
                return target;
            }
        } else {
            return target;
        }
    }
}


