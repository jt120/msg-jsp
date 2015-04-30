package com.jt.msg.dao;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * since 2015/4/30.
 */
public class FilterTest {

    @Test
    public void test() throws Exception {
        String s = "dzm150427233435473d1143\t36\n" +
                "dut150428004121900d1260\t43\n" +
                "cmg150428100807429d1591\t38\n" +
                "cmg150428101820290d15c5\t38\n" +
                "xxd150428104559994d1677\t20\n" +
                "dut150428113218552d1778\t38\n" +
                "dut150428113401282d1785\t38\n" +
                "cmg150428115319689d1848\t33\n" +
                "dzm150428130216988d1a15\t28\n" +
                "cmg150428131957554d1a93\t33\n" +
                "xxd150428132502894d1ab7\t20\n" +
                "ztd150428134701437d1b53\t34\n" +
                "cmg150428134701436d1b52\t34\n" +
                "bja150428135004596d1b75\t24\n" +
                "xxd150428151619219d1d5f\t20\n" +
                "cmg150428154438419d1df4\t40\n" +
                "cmg150428154917577d1e06\t40\n" +
                "cmg150428155004299d1e0e\t45\n" +
                "ybe150428155004258d1e0d\t31\n" +
                "cmg150428155215521d1e1d\t40\n" +
                "cmg150428155457023d1e30\t40\n" +
                "dzb150428164132280d102b\t36\n" +
                "ybe150428174059409d10fd\t34\n" +
                "ybe150428175221820d114c\t47\n" +
                "dzm150428182937727d121b\t28\n" +
                "xxd150428185152683d126b\t20\n" +
                "dzb150428194226488d1050\t42\n" +
                "dzm150428202158986d1074\t39\n" +
                "ybe150428202159026d1075\t45\n" +
                "ztd150428220711225d12e5\t33\n" +
                "dut150428222334777d1355\t37\n" +
                "dzm150428224958971d13fd\t32\n" +
                "sxg150428230117836d1453\t10\n" +
                "dzm150429000220200d1587\t38\n" +
                "xxd150429082614443d17e9\t20\n" +
                "dut150429083040998d17fe\t45\n" +
                "dut150429083041037d17ff\t41\n" +
                "jca150429111331890d1b61\t42\n" +
                "cmg150429111422318d1b6d\t45\n";
        String src = "bja150428135004596d1b75\n" +
                "cmg150428100807429d1591\n" +
                "cmg150428101820290d15c5\n" +
                "cmg150428134701436d1b52\n" +
                "cmg150428154438419d1df4\n" +
                "cmg150428154917577d1e06\n" +
                "cmg150428155215521d1e1d\n" +
                "cmg150428155457023d1e30\n" +
                "dut150428113218552d1778\n" +
                "dut150429083040998d17fe\n" +
                "dut150429083041037d17ff\n" +
                "dzm150428224958971d13fd\n" +
                "dzm150429000220200d1587\n" +
                "jca150429111331890d1b61\n" +
                "sxg150428230117836d1453\n" +
                "xxd150428104559994d1677\n" +
                "xxd150428132502894d1ab7\n" +
                "xxd150428151619219d1d5f\n" +
                "xxd150428185152683d126b\n" +
                "xxd150429082614443d17e9\n" +
                "ybe150428174059409d10fd\n" +
                "ybe150429111422318d1b6c\n" +
                "ztd150428134701437d1b53\n" +
                "ztd150428220711225d12e5\n";
        String[] split = s.split("\n");
        String[] split1 = src.split("\n");
        Set<String> set = new HashSet<String>();
        for (String is : split1) {
            set.add(is);
        }
        for (String tmp : split) {
            String[] split2 = tmp.split("\t");
            if (set.contains(split2[0])) {
                System.out.println(tmp);
            }
        }
    }
}
