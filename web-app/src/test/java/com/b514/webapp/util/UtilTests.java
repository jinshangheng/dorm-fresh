package com.b514.webapp.util;

import com.b514.webapp.exception.NoCustomConvert;
import com.b514.webapp.model.WebVO;
import com.b514.webapp.model.param.TestParam;
import com.b514.webapp.model.vo.TestVO;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class UtilTests {
    @Test
    void modelUtil() {
        TestParam t = new TestParam();
        TestVO v = new TestVO();
        v.setTest1("foo");
        v.setTest2("bar");
        v.RespCode = "res";
        v.RespInfo = "info";
        System.out.println(Models.toString(v));
        Models.convert(v, t);
        System.out.println(Models.toString(t));
    }
}
